package datastructure;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by riponctg on 3/27/2016.
 */
public class UseMap {
    public static void main(String[] args) {
        Map<String,String> map = new LinkedHashMap<String, String>();
        map.put("USA", "NY");
        map.put("Australia", "Sydney");
        map.put("Canada", "Montreal");

        for(Map.Entry<String, String> cursor:map.entrySet()){
            System.out.println("Key: " + cursor.getKey() + " " + "Value is: " + cursor.getValue());
        }
    }
}
