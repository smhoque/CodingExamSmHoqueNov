/*
 * Copyright (c) 2008-2016 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.operation

import com.mongodb.MongoCommandException
import com.mongodb.MongoExecutionTimeoutException
import com.mongodb.MongoNamespace
import com.mongodb.MongoWriteConcernException
import com.mongodb.OperationFunctionalSpecification
import com.mongodb.WriteConcern
import com.mongodb.client.model.CreateCollectionOptions
import com.mongodb.client.model.ValidationOptions
import com.mongodb.client.test.CollectionHelper
import org.bson.BsonArray
import org.bson.BsonBoolean
import org.bson.BsonDocument
import org.bson.BsonString
import org.bson.Document
import org.bson.codecs.DocumentCodec
import spock.lang.IgnoreIf

import static com.mongodb.ClusterFixture.disableMaxTimeFailPoint
import static com.mongodb.ClusterFixture.enableMaxTimeFailPoint
import static com.mongodb.ClusterFixture.executeAsync
import static com.mongodb.ClusterFixture.getBinding
import static com.mongodb.ClusterFixture.isDiscoverableReplicaSet
import static com.mongodb.ClusterFixture.serverVersionAtLeast
import static com.mongodb.client.model.Filters.gte
import static java.util.concurrent.TimeUnit.MILLISECONDS
import static java.util.concurrent.TimeUnit.SECONDS

class AggregateToCollectionOperationSpecification extends OperationFunctionalSpecification {

    def aggregateCollectionNamespace = new MongoNamespace(getDatabaseName(), 'aggregateCollectionName')

    def setup() {
        CollectionHelper.drop(aggregateCollectionNamespace)
        Document pete = new Document('name', 'Pete').append('job', 'handyman')
        Document sam = new Document('name', 'Sam').append('job', 'plumber')
        Document pete2 = new Document('name', 'Pete').append('job', 'electrician')
        getCollectionHelper().insertDocuments(new DocumentCodec(), pete, sam, pete2)
    }

    def 'should have the correct defaults'() {
        given:
        def pipeline = [new BsonDocument('$out', new BsonString(aggregateCollectionNamespace.collectionName))]

        when:
        AggregateToCollectionOperation operation = new AggregateToCollectionOperation(getNamespace(), pipeline)

        then:
        operation.getAllowDiskUse() == null
        operation.getMaxTime(MILLISECONDS) == 0
        operation.getPipeline() == pipeline
        operation.getBypassDocumentValidation() == null
        operation.getWriteConcern() == null
        operation.getCollation() == null
    }

    def 'should set optional values correctly'(){
        given:
        def pipeline = [new BsonDocument('$out', new BsonString(aggregateCollectionNamespace.collectionName))]

        when:
        AggregateToCollectionOperation operation = new AggregateToCollectionOperation(getNamespace(), pipeline, WriteConcern.MAJORITY)
                .allowDiskUse(true)
                .maxTime(10, MILLISECONDS)
                .bypassDocumentValidation(true)
                .collation(defaultCollation)

        then:
        operation.getAllowDiskUse()
        operation.getMaxTime(MILLISECONDS) == 10
        operation.getBypassDocumentValidation() == true
        operation.getWriteConcern() == WriteConcern.MAJORITY
        operation.getCollation() == defaultCollation
    }

    def 'should not accept an empty pipeline'() {
        when:
        new AggregateToCollectionOperation(getNamespace(), [])


        then:
        thrown(IllegalArgumentException)
    }

    def 'should not accept a pipeline without the last stage specifying an output-collection'() {
        when:
        new AggregateToCollectionOperation(getNamespace(), [new BsonDocument('$match', new BsonDocument('job', new BsonString('plumber')))])


        then:
        thrown(IllegalArgumentException)
    }

    @IgnoreIf({ !serverVersionAtLeast(2, 6) })
    def 'should be able to output to a collection'() {
        when:
        AggregateToCollectionOperation operation =
                new AggregateToCollectionOperation(getNamespace(),
                                                   [new BsonDocument('$out', new BsonString(aggregateCollectionNamespace.collectionName))])
        execute(operation, async);

        then:
        getCollectionHelper(aggregateCollectionNamespace).count() == 3

        where:
        async << [true, false]
    }

    @IgnoreIf({ !serverVersionAtLeast(2, 6) })
    def 'should be able to match then output to a collection'() {
        when:
        AggregateToCollectionOperation operation =
                new AggregateToCollectionOperation(getNamespace(),
                                                   [new BsonDocument('$match', new BsonDocument('job', new BsonString('plumber'))),
                                                    new BsonDocument('$out', new BsonString(aggregateCollectionNamespace.collectionName))])
        execute(operation, async);

        then:
        getCollectionHelper(aggregateCollectionNamespace).count() == 1

        where:
        async << [true, false]
    }

    @IgnoreIf({ !serverVersionAtLeast(2, 6) })
    def 'should throw execution timeout exception from execute'() {
        given:
        AggregateToCollectionOperation operation =
                new AggregateToCollectionOperation(getNamespace(),
                                                   [new BsonDocument('$match', new BsonDocument('job', new BsonString('plumber'))),
                                                    new BsonDocument('$out', new BsonString(aggregateCollectionNamespace.collectionName))])
                        .maxTime(1, SECONDS)
        enableMaxTimeFailPoint()

        when:
        execute(operation, async);

        then:
        thrown(MongoExecutionTimeoutException)

        cleanup:
        disableMaxTimeFailPoint()

        where:
        async << [true, false]
    }

    @IgnoreIf({ !serverVersionAtLeast(3, 4) || !isDiscoverableReplicaSet() })
    def 'should throw on write concern error'() {
        given:
        AggregateToCollectionOperation operation =
                new AggregateToCollectionOperation(getNamespace(),
                        [new BsonDocument('$out', new BsonString(aggregateCollectionNamespace.collectionName))],
                        new WriteConcern(5))

        when:
        async ? executeAsync(operation) : operation.execute(getBinding())

        then:
        def ex = thrown(MongoWriteConcernException)
        ex.writeConcernError.code == 100
        ex.writeResult.wasAcknowledged()

        where:
        async << [true, false]
    }

    @IgnoreIf({ !serverVersionAtLeast(3, 2) })
    def 'should support bypassDocumentValidation'() {
        given:
        def collectionOutHelper = getCollectionHelper(new MongoNamespace(getDatabaseName(), 'collectionOut'))
        collectionOutHelper.create('collectionOut', new CreateCollectionOptions().validationOptions(
                new ValidationOptions().validator(gte('level', 10))))
        getCollectionHelper().insertDocuments(BsonDocument.parse('{ level: 9 }'))

        when:
        def operation = new AggregateToCollectionOperation(getNamespace(), [BsonDocument.parse('{$out: "collectionOut"}')])
        execute(operation, async);

        then:
        thrown(MongoCommandException)

        when:
        execute(operation.bypassDocumentValidation(false), async)

        then:
        thrown(MongoCommandException)

        when:
        execute(operation.bypassDocumentValidation(true), async)

        then:
        notThrown(MongoCommandException)

        cleanup:
        collectionOutHelper?.drop()

        where:
        async << [true, false]
    }

    def 'should create the expected command'() {
        when:
        def pipeline = [BsonDocument.parse('{$out: "collectionOut"}')]
        def operation = new AggregateToCollectionOperation(getNamespace(), pipeline, WriteConcern.MAJORITY).bypassDocumentValidation(true)
        def expectedCommand = new BsonDocument('aggregate', new BsonString(getNamespace().getCollectionName()))
                .append('pipeline', new BsonArray(pipeline))

        if (includeBypassValidation) {
            expectedCommand.put('bypassDocumentValidation', BsonBoolean.TRUE)
        }
        if (includeWriteConcern) {
            expectedCommand.append('writeConcern', new BsonDocument('w', new BsonString('majority')))
        }
        if (includeCollation) {
            operation.collation(defaultCollation)
            expectedCommand.append('collation', defaultCollation.asDocument())
        }

        then:
        testOperation(operation, serverVersion, expectedCommand, false, BsonDocument.parse('{ok: 1}'))

        where:
        serverVersion | includeBypassValidation | includeWriteConcern | includeCollation | async
        [3, 4, 0]     | true                    | true                | true             | true
        [3, 4, 0]     | true                    | true                | true             | false
        [3, 2, 0]     | true                    | false               | false            | true
        [3, 2, 0]     | true                    | false               | false            | false
        [3, 0, 0]     | false                   | false               | false            | true
        [3, 0, 0]     | false                   | false               | false            | false

    }

    def 'should throw an exception when passing an unsupported collation'() {
        given:
        def pipeline = [BsonDocument.parse('{$out: "collectionOut"}')]
        def operation = new AggregateToCollectionOperation(getNamespace(), pipeline).collation(defaultCollation)

        when:
        testOperationThrows(operation, [3, 2, 0], async)

        then:
        def exception = thrown(IllegalArgumentException)
        exception.getMessage().startsWith('Collation not supported by server version:')

        where:
        async << [false, false]
    }

    @IgnoreIf({ !serverVersionAtLeast(3, 4) })
    def 'should support collation'() {
        given:
        getCollectionHelper().insertDocuments(BsonDocument.parse('{_id: 1, str: "foo"}'))
        def pipeline = [BsonDocument.parse('{$match: {str: "FOO"}}'),
                        new BsonDocument('$out', new BsonString(aggregateCollectionNamespace.collectionName))]
        def operation = new AggregateToCollectionOperation(getNamespace(), pipeline).collation(defaultCollation)
                .collation(caseInsensitiveCollation)

        when:
        execute(operation, async)

        then:
        getCollectionHelper(aggregateCollectionNamespace).count() == 1

        where:
        async << [true, false]
    }
}
