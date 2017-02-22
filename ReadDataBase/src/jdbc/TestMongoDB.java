package jdbc;

import com.mongodb.util.JSON;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by riponctg on 12/28/2016.
 */
public class TestMongoDB {
    public static void main(String[] args) {
        ConnectMongoDB mongo = new ConnectMongoDB();
       mongo.connectMongo("PeopleNTech");
        List<Document> list = new ArrayList<Document>();
        list = mongo.getMongoDBdata("PeopleNTech", "NewsContent");

        for(Document doc:list){
            System.out.println(JSON.parse(doc.toJson()));
        }
    }
}
