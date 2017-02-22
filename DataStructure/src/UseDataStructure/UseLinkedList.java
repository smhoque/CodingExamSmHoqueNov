package UseDataStructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by riponctg on 12/14/2016.
 */
public class UseLinkedList {
    public static void main(String[] args) {
        LinkedList<String> linkedlist = new LinkedList<String>();
        linkedlist.add("DC");
        linkedlist.add("CA");
        linkedlist.add("MD");
        linkedlist.add("PA");

        System.out.println("Using Iterator");
        Iterator iterator = linkedlist.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("Using enhance for loop");
        for (String state : linkedlist) {
            System.out.println(state);
        }
    }
}
