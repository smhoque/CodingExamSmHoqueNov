package math;

/**
 * Created by mrahman on 12/16/16.
 */
public class Factorial {

    public static void main(String[] args) {
        /*
         * Factorial of 5! = 5 x 4 X 3 X 2 X 1 = 120.
         * Write a java program to find Factorial of a given number using Recursion as well as Iteration.
         *
         */

       /* System.out.println("Using Recursion");
        int n = 7;
        int result = factorial(n);
        System.out.println("The factorial of 7 is " + result);
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }*/
        System.out.println("Using Iteration:");
        int n = 7;
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }

        int num = 9;
        int factorialnum = factorial(num);
        System.out.println("The factorial of "+num+ " is "+factorialnum);


    }
    public static int factorial(int num){

        if (num > 1)
            return num*factorial(num-1);
        else
            return 1;
    }
}
