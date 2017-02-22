package Reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by riponctg on 12/15/2016.
 */
public class ReadText {
    public static void main(String[] args) {
        BufferedReader br = null;
        FileReader fr = null;

        String path = "C:/Users/riponctg/Nasa.txt";

        try {
            fr = new FileReader(path);

        } catch (FileNotFoundException ex) {
            System.out.println("Please fix the path");
        }
        try {
            br = new BufferedReader(fr);
            String text = "";
            if ((text = br.readLine()) != null) {
                System.out.println(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                fr = null;
            }
            if (br != null) {
                br = null;
            }
        }
    }
}
