package Test;

/**
 * Created by riponctg on 1/22/2017.
 */
public class TestCal {
    public static void main(String[] args) {
        int prev = 0;
        int next = 1;
        int limit = 10;

        for(int i = 0; i<limit; i++){
            System.out.println(prev);

            prev = prev - next;
            next = next + prev;
        }

    }
}
