package datastructure;

import java.util.Stack;

/**
 * Created by riponctg on 3/27/2016.
 */
public class UseStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        stack.push("CO");
        stack.push("AZ");
        stack.push("NJ");

        //System.out.println(stack.pop());
        System.out.println(stack.peek());


    }
}
