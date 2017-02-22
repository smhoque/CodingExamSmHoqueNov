package jdbc;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by riponctg on 12/28/2016.
 */

public class ConnectMongoDB {
    MongoDatabase mdb = null;

    public MongoDatabase connectMongo(String databaseName){
        MongoClient mongoClient = new MongoClient();
        mdb = mongoClient.getDatabase(databaseName);
        System.out.println("DataBase is connected");

        return mdb;

    }
    public void insert(String databaseName, String collectionName, String newsType, String writerName, String date){
        mdb= connectMongo(databaseName);
        MongoCollection mCollection = mdb.getCollection(collectionName);
        Document doc = new Document().append("news", newsType).append("writer", writerName).append("Date", date);
        mCollection.insertOne(doc);
    }
    public List<Document> getMongoDBdata(String DatabaseName, String CollectionName){
        List<Document> list = new ArrayList<Document>();
        mdb = connectMongo(DatabaseName);
        MongoCollection coll = mdb.getCollection( CollectionName);
        List<Document> docList = ((List<Document>)coll.find().into(new ArrayList<Document>()));
        for(Document doc:docList){
            list.add(doc);
        }

        return list;
    }


}
