package design;

/**
 * Created by riponctg on 12/21/2016.
 */
public abstract class EmployeeType implements Employee {
    public abstract void PermenentEmployee();

    public void TemporaryEmployee() {
        int WorkDuration = 6;
        if (WorkDuration > 0 && WorkDuration == 6) {
            System.out.println("CNN temporary employee!");
        } else if (WorkDuration > 6) {
            System.out.println("CNN permenent employee");
        } else
            System.out.println("CNN new employee");

    }

}