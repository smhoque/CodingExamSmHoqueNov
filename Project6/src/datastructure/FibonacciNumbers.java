package datastructure;

/**
 * Created by riponctg on 3/27/2016.
 */
public class FibonacciNumbers {
    public static void main(String[] args) {
        int prev = 0;
        int next = 1;
        int limit = 10;

        for(int i=0; i<limit; i++){
            System.out.println(prev);
            prev = prev + next;
            next = prev - next;

        }
    }
}
