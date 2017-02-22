package datastructure;

import java.util.Iterator;
import java.util.Stack;

public class UseStack {

    public static void main(String[] args) {
        /*
         * Demonstrate how to use Stack that includes push,peek,search,pop elements.
		 * Use For Each loop and while loop with Iterator to retrieve data.
		 * 
		 */
        Stack<String> list = new Stack<String>();
        list.add("Selinium");
        list.add("QTP");
        list.add("Performance");
        list.add("DBA");

        System.out.println(list.search(2));
        System.out.println(list.peek());
        System.out.println(list.push("IOS"));
        System.out.println(list.pop());

        for (String subject : list) {
            System.out.println(subject);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}
