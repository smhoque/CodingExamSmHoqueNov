package UsingSorting;

/**
 * Created by riponctg on 12/14/2016.
 */
public class PrimeNumber {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int numberOfPrimeNumbers = 0;

        for (int i = 2; i <= 100000; i++) {
            if (isCheckingByCube(i)) {
                System.out.println(i);
                numberOfPrimeNumbers++;
            }
        }
        System.out.println("Total prime number up to this point: " + numberOfPrimeNumbers);


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);

    }

    public static boolean isPrint(int n) {
        for (int j = 2; j < n; j++) {
            if (n % j == 0)
                return false;
        }
        return true;
    }

    public static boolean isCheckingByCube(int n) {
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }


}
