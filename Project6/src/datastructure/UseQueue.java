package datastructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by riponctg on 3/27/2016.
 */
public class UseQueue {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        queue.add("MA");
        queue.add("MS");
        queue.add("AL");

       // System.out.println(queue.peek());
        System.out.println(queue.remove());
        System.out.println(queue.peek());

    }
}
