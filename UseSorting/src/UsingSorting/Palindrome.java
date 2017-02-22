package UsingSorting;

import java.util.Scanner;

/**
 * Created by riponctg on 12/16/2016.
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println("PLease enter a word:");

        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        String reverse = "";
        for (int i = st.length() - 1; i >= 0; i--) {
            reverse = reverse + st.charAt(i);
        }
        if (st.equals(reverse)) {
            System.out.println(st + " is Palindrome");
        } else
            System.out.println(st + " is not Palindrome");
    }
}
