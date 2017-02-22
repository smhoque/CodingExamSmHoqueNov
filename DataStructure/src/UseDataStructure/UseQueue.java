package UseDataStructure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by riponctg on 12/14/2016.
 */
public class UseQueue {
    public static void main(String[] args) {
        Queue<String> list = new LinkedList<>();
        list.add("GA");
        list.add("MS");
        list.add("CA");
        list.add("GA");

     /*   System.out.println("Not possible Using for loop");
        for(int i=0; i<list.size(); i++){
            System.out.println(i);
        }*/
        System.out.println("Using enhance for loop");
        for (String name : list) {
            System.out.println(name);
        }
        System.out.println("Using Iterator");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());

        }

    }
}
