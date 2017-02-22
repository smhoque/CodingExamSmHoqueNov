package design;

public class CnnEmployee {

    /**
     * CnnEmployee class has a main methods where you will be able to create Object from
     * EmployeeInfo class to use fields and attributes.Demonstrate as many methods as possible
     * to use with proper business work flow.Think as a Software Architect, Product Designer and
     * as a Software Developer.(employee.info.system) package iyou need to elaborats given as an outline,e
     * more to design an application that will meet for fortune 500 Employee Information
     * Services.
     * <p>
     * Use any databases[MongoDB, Oracle, MySql] to store data and retrieve data.
     **/
    public static void main(String[] args) {

        System.out.println("Company details");
        System.out.println("Company Name: " + EmployeeInfo.getCompanyName());
        System.out.println("Company CEO: " + EmployeeInfo.getCompanyCEO());
        System.out.println("Company Founder " + EmployeeInfo.getCompanyFounder());

        EmployeeInfo employeeId = new EmployeeInfo(111);
        EmployeeInfo employeeNameDepart = new EmployeeInfo("John", "IT");
        employeeId.DisplayEmployeeId();
        employeeNameDepart.DisplayEmployeeNameAndDepartment();


        EmployeeInfo.calculateEmployeBonus();
        EmployeeInfo.calculateEmployePension();
        Employee employee = new EmployeeInfo();
        employee.calculateSalary();
        employee.benefitLayout();


        EmployeeType e = new EmployeeInfo();
        e.benefitLayout();
        e.calculateSalary();
        e.PermenentEmployee();
        System.out.println();

    }

}
