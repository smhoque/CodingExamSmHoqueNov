package UsingSorting;

import java.util.Random;

/**
 * Created by riponctg on 12/14/2016.
 */
public class Math {
    public static void main(String[] args) {

        int[] array = new int[10];

        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10);
        }
        for (int j = 0; j < array.length; j++) {
            System.out.println(array[j]);
        }
    }
}

