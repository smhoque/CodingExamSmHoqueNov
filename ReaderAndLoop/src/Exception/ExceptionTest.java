package Exception;

/**
 * Created by riponctg on 12/15/2016.
 */
public class ExceptionTest {
    public static void main(String[] args) {

        try{
            int num = 15/0;
            System.out.println(num);
        }catch (ArithmeticException ex){
            System.out.print("Cannot divided by 0");
        }
    }
}
