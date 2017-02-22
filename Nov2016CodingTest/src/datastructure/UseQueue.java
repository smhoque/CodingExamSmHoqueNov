package datastructure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class UseQueue {

    public static void main(String[] args) {
        /*
         * Demonstrate how to use Queue that includes add,peek,remove,pool elements.
		 * Use For Each loop and while loop with Iterator to retrieve data.
		 * 
		 */

        Queue<String> list = new LinkedList<>();
        list.add("USA");
        list.add("CANADA");
        list.add("LONDON");
        list.add("GERMANY");

        System.out.println(list.peek());
        System.out.print(list.remove(2));
        System.out.print(list.poll());

        for (String name : list) {
            System.out.println(name);

        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
