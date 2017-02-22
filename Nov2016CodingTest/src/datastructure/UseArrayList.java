package datastructure;

import com.mongodb.client.MongoDatabase;
import databases.ConnectDB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UseArrayList {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        /*
         * Demonstrate how to use ArrayList that includes add,peek,remove,retrieve elements.
		 * Use For Each loop and while loop with Iterator to retrieve data.
		 * Store all the sorted data into one of the databases.
		 * 
		 */


        ArrayList<String> list = new ArrayList<String>();
        list.add("Trio");
        list.add("Supreme");
        list.add("Peparoni");
        list.add("Chees");

        System.out.println(list.remove(2));
        for (String pizza : list)
            System.out.println(pizza);

        MongoDatabase mongoDatabase = ConnectDB.connectMongoDB();
        //Properties properties = ConnectDB.loadProperties("ArrayList", "ArrayList", "list");
    }
}
