package datastructure;

import databases.ConnectDB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UseMap {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        /*
		 * Demonstrate how to use Map that includes storing and retrieving elements.
		 * Add List<String> into a Map. Like, Map<String, List<string>> list = new HashMap<String, List<String>>();
		 * Use For Each loop and while loop with Iterator to retrieve data.
		 *
		 * Use any databases[MongoDB, Oracle, MySql] to store data and retrieve data.
		 */
        ConnectDB connectDB = new ConnectDB();

        List<String> ProgrammingBook = new ArrayList<String>();
        ProgrammingBook.add("The Pragmatic Programmer");
        ProgrammingBook.add("Code Complete");
        ProgrammingBook.add("Improving the Design of Existing Code");

        List<String> AccountingBook = new ArrayList<String>();
        AccountingBook.add("Budgeting and Decision Making");
        AccountingBook.add("Current Assets");
        AccountingBook.add("Liabilities and Equity");

        List<String> EconomicsBook = new ArrayList<String>();
        EconomicsBook.add("Economics Today");
        EconomicsBook.add("Principles in Action");
        EconomicsBook.add("Mishkin Economics of Money");


        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("Programming Books", ProgrammingBook);
        map.put("Accounting Books", AccountingBook);
        map.put("Economics Books", EconomicsBook);

        for (Map.Entry list : map.entrySet()) {
            System.out.println(list.getKey() + " " + list.getValue());
        }
        connectDB.InsertDataFromStringToMySql("Arraylist", "list", "list");

    }
}