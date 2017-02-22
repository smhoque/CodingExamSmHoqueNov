/*
 * Copyright (c) 2008-2014 MongoDB, Inc.
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

import com.mongodb.ExplainVerbosity
import com.mongodb.MongoExecutionTimeoutException
import com.mongodb.MongoNamespace
import com.mongodb.OperationFunctionalSpecification
import com.mongodb.ReadConcern
import com.mongodb.ReadPreference
import com.mongodb.client.model.Collation
import com.mongodb.connection.ConnectionDescription
import com.mongodb.connection.ServerVersion
import org.bson.BsonArray
import org.bson.BsonBoolean
import org.bson.BsonDocument
import org.bson.BsonInt32
import org.bson.BsonInt64
import org.bson.BsonString
import org.bson.Document
import org.bson.codecs.BsonDocumentCodec
import org.bson.codecs.DocumentCodec
import spock.lang.IgnoreIf

import static com.mongodb.ClusterFixture.collectCursorResults
import static com.mongodb.ClusterFixture.disableMaxTimeFailPoint
import static com.mongodb.ClusterFixture.enableMaxTimeFailPoint
import static com.mongodb.ClusterFixture.getBinding
import static com.mongodb.ClusterFixture.serverVersionAtLeast
import static java.util.concurrent.TimeUnit.MILLISECONDS
import static java.util.concurrent.TimeUnit.SECONDS

class AggregateOperationSpecification extends OperationFunctionalSpecification {

    def setup() {
        Document pete = new Document('name', 'Pete').append('job', 'handyman')
        Document sam = new Document('name', 'Sam').append('job', 'plumber')
        Document pete2 = new Document('name', 'Pete').append('job', 'electrician')
        getCollectionHelper().insertDocuments(new DocumentCodec(), pete, sam, pete2)
    }

    def 'should have the correct defaults'() {
        when:
        AggregateOperation operation = new AggregateOperation<Document>(getNamespace(), [], new DocumentCodec())

        then:
        operation.getAllowDiskUse() == null
        operation.getBatchSize() == null
        operation.getMaxTime(MILLISECONDS) == 0
        operation.getPipeline() == []
        operation.getUseCursor() == null
        operation.getReadConcern() == ReadConcern.DEFAULT
        operation.getCollation() == null
    }

    def 'should set optional values correctly'(){
        when:
        AggregateOperation operation = new AggregateOperation<Document>(getNamespace(), [], new DocumentCodec())
                .allowDiskUse(true)
                .batchSize(10)
                .maxTime(10, MILLISECONDS)
                .useCursor(true)
                .readConcern(ReadConcern.MAJORITY)
                .collation(defaultCollation)


        then:
        operation.getAllowDiskUse()
        operation.getBatchSize() == 10
        operation.getMaxTime(MILLISECONDS) == 10
        operation.getUseCursor()
        operation.getReadConcern() == ReadConcern.MAJORITY
        operation.getCollation() == defaultCollation
    }

    def 'should create the expected command'() {
        when:
        def pipeline = [new BsonDocument('$match', new BsonDocument('a', new BsonString('A')))]
        def operation = new AggregateOperation<Document>(helper.namespace, pipeline, new DocumentCodec())
                .allowDiskUse(true)
                .batchSize(10)
                .maxTime(10, MILLISECONDS)
                .useCursor(true)
                .readConcern(ReadConcern.MAJORITY)
                .collation(defaultCollation)

        def expectedCommand = new BsonDocument('aggregate', new BsonString(helper.namespace.getCollectionName()))
                .append('pipeline', new BsonArray(pipeline))
                .append('cursor', new BsonDocument('batchSize', new BsonInt32(10)))
                .append('maxTimeMS', new BsonInt64(10))
                .append('allowDiskUse', new BsonBoolean(true))
                .append('readConcern', new BsonDocument('level', new BsonString('majority')))
                .append('collation', defaultCollation.asDocument())

        then:
        testOperation(operation, [3, 4, 0], expectedCommand, async, helper.cursorResult)

        where:
        async << [true, false]
    }

    def 'should throw an exception when using an unsupported ReadConcern'() {
        given:
        def pipeline = [new BsonDocument('$match', new BsonDocument('a', new BsonString('A')))]
        def operation = new AggregateOperation<Document>(helper.namespace, pipeline, new DocumentCodec()).readConcern(readConcern)

        when:
        testOperationThrows(operation, [3, 0, 0], async)

        then:
        def exception = thrown(IllegalArgumentException)
        exception.getMessage().startsWith('ReadConcern not supported by server version:')

        where:
        [async, readConcern] << [[true, false], [ReadConcern.MAJORITY, ReadConcern.LOCAL]].combinations()
    }

    def 'should throw an exception when using an unsupported Collation'() {
        given:
        def pipeline = [new BsonDocument('$match', new BsonDocument('a', new BsonString('A')))]
        def operation = new AggregateOperation<Document>(helper.namespace, pipeline, new DocumentCodec())
                .collation(defaultCollation)

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
        def document = BsonDocument.parse('{_id: 1, str: "foo"}')
        getCollectionHelper().insertDocuments(document)
        def pipeline = [BsonDocument.parse('{$match: {str: "FOO"}}')]
        def operation = new AggregateOperation<BsonDocument>(namespace, pipeline, new BsonDocumentCodec())
                .collation(caseInsensitiveCollation)

        when:
        def result = executeAndCollectBatchCursorResults(operation, async)

        then:
        result == [document]

        where:
        async << [true, false]
    }

    def 'should be able to aggregate'() {
        when:
        AggregateOperation operation = new AggregateOperation<Document>(getNamespace(), [], new DocumentCodec()).useCursor(useCursor)
        def batchCursor = execute(operation, async)
        def results = collectCursorResults(batchCursor)*.getString('name')

        then:
        results.size() == 3
        results.containsAll(['Pete', 'Sam'])

        where:
        [async, useCursor] << [[true, false], useCursorOptions()].combinations()
    }

    def 'should be able to aggregate with pipeline'() {
        when:
        AggregateOperation operation = new AggregateOperation<Document>(getNamespace(),
                [new BsonDocument('$match', new BsonDocument('job', new BsonString('plumber')))], new DocumentCodec()).useCursor(useCursor)
        def batchCursor = execute(operation, async)
        def results = collectCursorResults(batchCursor)*.getString('name')

        then:
        results.size() == 1
        results == ['Sam']

        where:
        [async, useCursor] << [[true, false], useCursorOptions()].combinations()
    }

    @IgnoreIf({ !serverVersionAtLeast(2, 6) })
    def 'should allow disk usage'() {
        when:
        AggregateOperation operation = new AggregateOperation<Document>(getNamespace(), [], new DocumentCodec()).allowDiskUse(allowDiskUse)
        def cursor = operation.execute(getBinding())

        then:
        cursor.next()*.getString('name') == ['Pete', 'Sam', 'Pete']

        where:
        allowDiskUse << [null, true, false]
    }

    @IgnoreIf({ !serverVersionAtLeast(2, 6) })
    def 'should allow batch size'() {
        when:
        AggregateOperation operation = new AggregateOperation<Document>(getNamespace(), [], new DocumentCodec()).batchSize(batchSize)
        def cursor = operation.execute(getBinding())

        then:
        cursor.next()*.getString('name') == ['Pete', 'Sam', 'Pete']

        where:
        batchSize << [null, 0, 10]
    }

    @IgnoreIf({ !serverVersionAtLeast(2, 6) })
    def 'should throw execution timeout exception from execute'() {
        given:
        def operation = new AggregateOperation<Document>(getNamespace(), [], new DocumentCodec()).maxTime(1, SECONDS)
        enableMaxTimeFailPoint()

        when:
        execute(operation, async)

        then:
        thrown(MongoExecutionTimeoutException)

        cleanup:
        disableMaxTimeFailPoint()

        where:
        async << [true, false]
    }

    @IgnoreIf({ !serverVersionAtLeast(2, 6) })
    def 'should be able to explain an empty pipeline'() {
        given:
        def operation = new AggregateOperation(getNamespace(), [], new BsonDocumentCodec())
        operation = async ? operation.asExplainableOperationAsync(ExplainVerbosity.QUERY_PLANNER) :
                            operation.asExplainableOperation(ExplainVerbosity.QUERY_PLANNER)

        when:
        def result = execute(operation, async)

        then:
        result.containsKey('stages')

        where:
        async << [true, false]
    }

    @IgnoreIf({ !serverVersionAtLeast(3, 4) })
    def 'should be able to aggregate with collation'() {
        when:
        AggregateOperation operation = new AggregateOperation<Document>(getNamespace(),
                [BsonDocument.parse('{$match: {job : "plumber"}}')], new DocumentCodec()
        ).collation(options)
        def batchCursor = execute(operation, async)
        def results = collectCursorResults(batchCursor)*.getString('name')

        then:
        results.size() == 1
        results == ['Sam']

        where:
        [async, options] << [[true, false], [defaultCollation, null, Collation.builder().build()]].combinations()
    }

    def 'should use the ReadBindings readPreference to set slaveOK'() {
        when:
        def operation = new AggregateOperation(helper.namespace, [], new BsonDocumentCodec())

        then:
        testOperationSlaveOk(operation, [2, 4, 0], readPreference, async, helper.inlineResult)

        then:
        testOperationSlaveOk(operation, [2, 6, 0], readPreference, async, helper.cursorResult)

        where:
        [async, readPreference] << [[true, false], [ReadPreference.primary(), ReadPreference.secondary()]].combinations()
    }

    def helper = [
            dbName: 'db',
            namespace: new MongoNamespace('db', 'coll'),
            twoFourConnectionDescription: Stub(ConnectionDescription) {
                getServerVersion() >> new ServerVersion([2, 4, 0])
            },
            twoSixConnectionDescription : Stub(ConnectionDescription) {
                getServerVersion() >> new ServerVersion([2, 6, 0])
            },
            inlineResult: BsonDocument.parse('{ok: 1.0}').append('result', new BsonArrayWrapper([])),
            cursorResult: BsonDocument.parse('{ok: 1.0}')
                    .append('cursor', new BsonDocument('id', new BsonInt64(0)).append('ns', new BsonString('db.coll'))
                    .append('firstBatch', new BsonArrayWrapper([])))
    ]

    private static List<Boolean> useCursorOptions() {
        [null, true, false]
    }
}
