package design;

import java.util.Scanner;

public class EmployeeInfo extends EmployeeType implements Employee {

    /*This class can be implemented from Employee interface then add additional methods in EmployeeInfo class.
    * Also, Employee interface can be implemented into an abstract class.So create an Abstract class
    * then inherit that abstract class into EmployeeInfo class.Once you done with designing EmployeeInfo class,
    * go to CnnEmployee class to apply all the fields and attributes.
    *
    * Important: YOU MUST USE the
    * OOP(abstraction,Encapsulation, Inheritance and Polymorphism) concepts in every level possible.
    * Use all kind of keywords(super,this,static,final........)
    *
    */
    /*
         * declare few static and final fields and some non-static fields
         */

    private static final String companyFounder = "Bolton";
    private static final String companyCEO = "Micheal";
    private static String companyName = "CNN";
    public String companylocation;
    public int employeeId;
    public String employeeName;
    public String employeeDepartment;
    private int a;


    /*
     * you must have multiple constructor.
     * Must implement below constructor.
     */
    public EmployeeInfo() {
        this.companylocation = companylocation;
    }

    public EmployeeInfo(int employeeId) {
        this.employeeId = employeeId;

    }


    public EmployeeInfo(String employeeName, String employeeDepartment) {
        this.employeeName = employeeName;
        this.employeeDepartment = employeeDepartment;

    }

    public static String getCompanyName() {
        return companyName;
    }

    public static void setCompanyName(String companyName) {
        EmployeeInfo.companyName = companyName;
    }

    public static String getCompanyFounder() {
        return companyFounder;
    }

	/*
     * You must implement the logic for below 2 methods and
	 * following 2 methods are prototype as well for other methods need to be design,
	 * as you will come up with the new ideas.
	 */

    public static String getCompanyCEO() {
        return companyCEO;
    }

    /*
     * This methods should calculate Employee bonus based on salary and performance.
     * Then it will return the total yearly bonus. So you need to implement the logic.
     * Hints: 10% of the salary for best performance, 8% of the salary for average performance and so on.
     * You can set arbitrary number for performance.
     * So you probably need to send 2 arguments.
     *
     */
    public static void calculateEmployeBonus() {
        int bestPerformance = 10;
        int performance = 8;

        if (bestPerformance > performance) {
            System.out.println("Employee salary with Best performance bonus:" + (10000 + 10000 / 10));
        } else
            System.out.println("Employee salary with avverage performance bonus:" + (10000 + 10000 / 8));

    }

    /*
     * This methods should calculate Employee Pension based on salary and numbers of years with the company.
     * Then it will return the total pension. So you need to implement the logic.
     * Hints: pension will be 5% of the salary for 1 year, 10% for 2 years with the company and so on.
     * So you probably need to send 2 arguments.
     *
     */
    public static void calculateEmployePension() {
        int WorkingYearsA = 2;
        int WorkingYearB = 1;
        int salaryA = 50000;
        int salaryB = 40000;

        if (WorkingYearsA > WorkingYearB && salaryA > salaryB) {
            System.out.println("Total Employee pension:" + 50000 / 10);
        } else
            System.out.println("Total Employee pension:" + 4000 / 5);

    }

    public void setCompanylocation(String companylocation) {
        this.companylocation = companylocation;
    }

    public String getCompanylocation(String getCompanylocation) {
        return companylocation;
    }

    void DisplayEmployeeId() {
        System.out.println("Employee Id :" + employeeId);
    }

    void DisplayEmployeeNameAndDepartment() {
        System.out.println("Employee Name: " + employeeName + "\n" + "Employee Department: " + employeeDepartment);
    }

    @Override
    public void PermenentEmployee() {
        int PermenentemployeeDuration = 12;
        if (PermenentemployeeDuration == 12) {
            System.out.print("CNN permenenet employee");

        } else if (PermenentemployeeDuration != 12) {
            System.out.println("Not yet CNN Permenent employee");
        } else
            System.out.println("CNN New employee");
    }

    @Override
    public int employeeId() {
        int employeeId = 111;
        System.out.println(employeeId);
        return employeeId;
    }

    @Override
    public String employeeName() {
        String employeeName = "John";
        System.out.print("Employee Name:" + employeeName);
        return null;
    }

    @Override
    public void assignDepartment() {
        String department = "IT";
        System.out.println("CNN employee department of " + department);

    }

    @Override
    public void benefitLayout() {
        System.out.println("CNN employees benifit include: 1, Medical , 401K, Employee Discount, Time off, Educational Assistent");

    }

    public void showMedicalBenefits(int choice) {
        String benefits = " ";

        switch (choice) {
            case 1:
                benefits = "Health Insurance";
                System.out.println("$10 Generic co-pay\n" +
                        "$20 Formulary co-pay\n" +
                        "$35 Non-Formulary co-pay");
                break;
            case 2:
                benefits = "Dental Insurance";
                System.out.println("Twice-yearly oral exams, " +
                        "x-rays every 36 months, and preventative treatment are all 100% covered. Other procedures " +
                        "are covered under a 50-80% co-insurance feature of the plan design. For a complete summary of dental benefits,");
                break;
            case 3:
                benefits = "Vision Insurance";
                System.out.println("Cobham provides safety glasses free of charge to those \n" +
                        "employees who work in areas that require them.");
                break;
            case 4:
                benefits = "Flexible Spending Accounts (FSA)";
                System.out.print("FSAs allow employees to pay for certain medical expenses and/or childcare costs on a pre-tax basis. " +
                        "FSAs are one of the best tax breaks available. When you reduce your tax liability, you increase your disposable income.");
                break;
            default:
                benefits = "Educational Assistance";
                System.out.println("CNN offer a tuition reimbursement program that covers undergraduate and graduate programs.");
                break;
        }

    }

    @Override
    public void calculateSalary() {
        double WorkingHoure, Rate = 50, OverTime, Total;
        Scanner sc = new Scanner(System.in);
        WorkingHoure = sc.nextInt();

        if (WorkingHoure <= 32) {
            Total = WorkingHoure * Rate;
        } else if (WorkingHoure > 35 && WorkingHoure <= 41) {
            OverTime = WorkingHoure -= 35;
            Total = WorkingHoure * Rate + OverTime * 1.5;

        } else
            Total = WorkingHoure += 35;
        System.out.println(Total);

    }
}
