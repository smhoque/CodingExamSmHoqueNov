package datastructure;

import java.util.Iterator;
import java.util.LinkedList;

public class UseLinkedList {

    public static void main(String[] args) {
        /*
         * Demonstrate how to use LinkedList that includes add,peek,remove,retrieve elements.
		 * Use For Each loop and while loop with Iterator to retrieve data.
		 *
		 */
        LinkedList<String> list = new LinkedList<String>();
        list.add("DC");
        list.add("GA");
        list.add("MA");
        list.add("PA");

        System.out.println(list.peek());
        System.out.println(list.remove(2));
        System.out.println("Using Each loop: ");
        for (String temp : list) {
            System.out.println(temp);
        }
        System.out.println("Using Iterator: ");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}
