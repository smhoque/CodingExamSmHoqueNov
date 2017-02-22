package reader;

import databases.ConnectDB;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {

    public static void main(String[] args) {
        /*
         * User API to read the below textFile and print to console.
		 * Use BufferedReader class. 
		 * Use try....catch block to handle Exception.

		 *
		 * Use any databases[MongoDB, Oracle, MySql] to store data and retrieve data.
		 *
		 */
        ConnectDB connectDB = new ConnectDB();

        BufferedReader br = null;
        FileReader fr = null;
        String textFile = "C:/Users/riponctg/IdeaProjects/CodingExamNov2016/Nov2016CodingTest/src/data/self-driving-car";
        //String textFile = System.getProperty("user.dir") + "/src/data/self-driving-car.txt";

        try {
            fr = new FileReader(textFile);
        } catch (FileNotFoundException ex) {
            System.out.println("PLease fix the path");
        }


        try {
            br = new BufferedReader(fr);
            String text = null;
            if ((text = br.readLine()) != null) {
                System.out.println(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br = null;
            }
            if (fr != null) {
                fr = null;
            }
        }
        //connectDB.InsertDataFromStringToMySql("core_java", "text", "text");
    }


}









