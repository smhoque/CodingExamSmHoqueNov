package UseDataStructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by riponctg on 12/14/2016.
 */
public class UseArrayList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("VA");
        list.add("MD");
        list.add("DC");
        list.add("PA");

        System.out.print(list.remove(2));

        System.out.println("Using for loop");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));

        }
        System.out.println("Using for each loop");
        for (String state : list) {
            System.out.println(state);

        }
        System.out.println("Using Iterator");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

