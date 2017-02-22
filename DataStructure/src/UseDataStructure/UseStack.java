package UseDataStructure;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by riponctg on 12/14/2016.
 */
public class UseStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        stack.add("NY");
        stack.add("FL");
        stack.add("GA");
        stack.add("CA");

        //System.out.println(stack.get(1));
       //System.out.println(stack.push("NN"));
        System.out.println(stack.peek());

        Iterator iterator = stack.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
