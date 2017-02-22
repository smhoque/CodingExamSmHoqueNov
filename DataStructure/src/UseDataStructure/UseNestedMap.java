package UseDataStructure;

import java.util.*;

/**
 * Created by riponctg on 12/14/2016.
 */
public class UseNestedMap {
    public static void main(String[] args) {
        List<String> CitiOfUSA = new ArrayList<>();
        CitiOfUSA.add("GA");
        CitiOfUSA.add("MD");
        CitiOfUSA.add("FL");

        List<String> CitiOfCanada = new ArrayList<>();
        CitiOfCanada.add("ON");
        CitiOfCanada.add("NV");
        CitiOfCanada.add("NT");

        List<String> CitiOfLondon = new ArrayList<String>();
        CitiOfLondon.add("Cathedral city");
        CitiOfLondon.add("London");
        CitiOfLondon.add("Bermingham");

        Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
        map.put("USA", CitiOfUSA);
        map.put("CANADA", CitiOfCanada);
        map.put("UK", CitiOfLondon);

        for (Map.Entry citi : map.entrySet()) {
            System.out.println(citi.getKey() + " " + citi.getValue());
        }
    }
}
