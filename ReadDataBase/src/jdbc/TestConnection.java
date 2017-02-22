package jdbc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by riponctg on 12/28/2016.
 */
public class TestConnection {
    public static void main(String[] args) throws  Exception{
        ConnectDB connectDB = new ConnectDB();
        connectDB.InsertDataFromStringToMySql("sakila", "language", "name");
        List<String> list = new ArrayList<>();
        list = connectDB.readDataBase("language", "name");
        for(String st:list){
            System.out.println(st);
        }

    }
}
