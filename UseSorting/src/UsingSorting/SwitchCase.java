package UsingSorting;

/**
 * Created by riponctg on 12/15/2016.
 */
public class SwitchCase {
    public void showDay(int choice) {
        String day;

        switch (choice) {
            case 1:
                day = "Monday";
                System.out.println("Time to practice application lifecycle management(ALM)");
                break;
            case 2:
                day = "Tuesday";
                System.out.println("Time to practice disk operating System(DOS)");
                break;
            case 3:
                day = "WednesDay";
                System.out.println("Time to practice Unix operating system(UNIX)");
                break;
            case 4:
                day = "Thursday";
                System.out.println("Time to practice Structure Query language(SQL)");
                break;
            case 5:
                day = "Friday";
                System.out.println("Time to practice OOP concepts");
                break;
            case 6:
                day = "Saterday";
                System.out.println("Time to practice DataStructure");
                break;
            case 7:
                day = "Sunday";
                System.out.println("Time to practice Selenium");
                break;
            default:
                day = " ";
                System.out.println("Time to watch movie");
                break;
        }
    }
}
