package UseDataStructure;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by riponctg on 12/14/2016.
 */
public class UseMap {
    public static void main(String[] args) {
        Map<String, String> list = new LinkedHashMap<String, String>();
        list.put("USA", "DC");
        list.put("CANADA", "Montreal");
        list.put("UK", "London");

        for(Map.Entry state:list.entrySet()){
            System.out.println(state.getKey() + " " + state.getValue());
        }
    }
}