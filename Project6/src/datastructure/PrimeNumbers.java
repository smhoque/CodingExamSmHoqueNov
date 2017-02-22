package datastructure;

/**
 * Created by riponctg on 3/27/2016.
 */
public class PrimeNumbers {
    public static void main(String args[]) {
        int limit = 15;
        int counter = 0;
        for (int i=2; i<limit; i++) {
            if (isPrime(i)) {
                System.out.println(isPrime(i));
                counter++;
            }
        }
        System.out.println("Number of prime numbers: "+counter);
    }


    public static boolean isPrime(int n){
        for (int i=2; i<n; i++){
            if(n%i==0)
                return false;

        }
        return true;

    }
}
