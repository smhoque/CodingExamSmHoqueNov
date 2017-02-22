package UseDataStructure;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by riponctg on 12/15/2016.
 */
public class UseArray {
    public static void main(String[] args) {
        int[] array = new int[5];

        System.out.println("Please enter number: ");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < array.length; i++) {
            array[i] = sc.nextInt();
        }

        System.out.println("Number of elements are:");

        for (int j = 0; j < array.length; j++) {
            System.out.println(array[j]);
        }

        try {
            if (sc != null) {
                sc = null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (sc != null) {
                sc = null;
            }
        }
    }
}


