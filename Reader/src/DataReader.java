import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by riponctg on 1/20/2017.
 */
public class DataReader {
    public static void main(String[] args) {
        FileReader fr = null;
        BufferedReader br = null;

        String path = "C:\\Users\\riponctg\\Nasa.txt";

        try {
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
            }

        } finally {
            if (br != null) {
                br = null;
            }
            if (fr != null) {
                fr = null;
            }
        }
    }
}
