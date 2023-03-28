import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Admin.getAdmin("Reza A", "09370000000");

        new FullTimeEmployee("Sana R", "025", 2023, 8000);
        new PartTimeEmployee("Amir M", "09170000000", 2020, 30);
        new ProjectEmployee("Hana A", "09120000000", 2021, 1500);
        new FullTimeEmployee("Hamed E", "09950000000", 2023, 10000);
        new FullTimeEmployee("Arman F", "09210000000", 2023, 6500);

        System.out.println(Admin.getAdmin() + "\n");

        for (Employee employee : Admin.getAdmin().getEmployeeList())
            System.out.println(employee + "\n");

        Admin.getAdmin().getEmployeeList().get(0).work(36);
        Admin.getAdmin().getEmployeeList().get(1).work(200);
        Admin.getAdmin().getEmployeeList().get(2).work(10);
        Admin.getAdmin().getEmployeeList().get(3).work(89);
        Admin.getAdmin().getEmployeeList().get(4).work(24);

        System.out.println("-------------SALARY------------\n");

        for (Employee employee : Admin.getAdmin().getEmployeeList()) {
            System.out.println(employee);
            System.out.println("This Employee Salary is: " + employee.getSalary() + "\n");
        }

        System.out.println("-------------AFTER------------\n");

        for (Employee employee : Admin.getAdmin().getEmployeeList()) {
            System.out.println(employee);
            System.out.println("This Employee Salary is: " + employee.getSalary() + "\n");
        }
    }
}

//////////////////////////////////////////////////////////

abstract class User {
    private String name;
    private String phoneNumber;

    public User(String name, String phoneNumber) {
        this.name = name;
        this.setPhoneNumber(phoneNumber);
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    private void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 11)
            this.phoneNumber = phoneNumber;
        else
            this.phoneNumber = "not specified";
    }

    @Override
    public String toString() {
        return "name : " + this.name + "\nPhone : " + phoneNumber;
    }
}

//////////////////////////////////////////////////////////

abstract class Employee extends User {

    private int dateOfJoin;

    public Employee(String name, String phoneNumber, int dateOfJoin) {
        super(name, phoneNumber);
        this.dateOfJoin = dateOfJoin;
        Admin.getAdmin().addToAllEmployees(this);
    }

    public abstract void work(int workUnit);

    public abstract double getSalary();

    public int getDateOfJoin() {
        return this.dateOfJoin;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDate of Join : " + dateOfJoin;
    }
}

//////////////////////////////////////////////////////////

class FullTimeEmployee extends Employee {

    private int unpaidDays;
    private final double monthlySalary;

    public FullTimeEmployee(String name, String phoneNumber, int dateOfJoin, double monthlySalary) {
        super(name, phoneNumber, dateOfJoin);
        this.monthlySalary = monthlySalary;
        this.unpaidDays = 0;
    }

    public int getUnpaidDays() {
        return unpaidDays;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    @Override
    public void work(int workUnit) {
        this.unpaidDays += workUnit;
    }

    @Override
    public double getSalary() {
        if (unpaidDays >= 30){
            int months = unpaidDays / 30;
            unpaidDays %= 30;
            return months * monthlySalary;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "FullTime Employee\n" + super.toString() + "\nUnpaid Days : "
                + this.unpaidDays + "\nMonthly Salary : " + this.monthlySalary;
    }
}

//////////////////////////////////////////////////////////

class PartTimeEmployee extends Employee {

    private int unpaidHours;
    private final double hourlySalary;

    public PartTimeEmployee(String name, String phoneNumber, int dateOfJoin, double hourlySalary) {
        super(name, phoneNumber, dateOfJoin);
        this.hourlySalary = hourlySalary;
        this.unpaidHours = 0;
    }

    public int getUnpaidHours() {
        return this.unpaidHours;
    }

    public double getHourlySalary() {
        return this.hourlySalary;
    }

    @Override
    public void work(int workUnit) {
        this.unpaidHours += workUnit;
    }

    @Override
    public double getSalary() {
        double salary = this.unpaidHours * this.hourlySalary;
        this.unpaidHours = 0;
        return salary;
    }

    @Override
    public String toString() {
        return "PartTime Employee\n" + super.toString() + "\nUnpaid Hours : "
                + this.unpaidHours + "\nHourly Salary : " + this.hourlySalary;
    }
}

//////////////////////////////////////////////////////////

class ProjectEmployee extends Employee {

    private int unpaidProject;
    private final double projectSalary;

    public ProjectEmployee(String name, String phoneNumber, int dateOfJoin, double projectSalary) {
        super(name, phoneNumber, dateOfJoin);
        this.projectSalary = projectSalary;
        this.unpaidProject = 0;
    }

    public int getUnpaidProject() {
        return this.unpaidProject;
    }

    public double getProjectSalary() {
        return this.projectSalary;
    }

    @Override
    public void work(int workUnit) {
        this.unpaidProject += workUnit;
    }

    @Override
    public double getSalary() {
        double salary = this.unpaidProject * this.projectSalary;
        this.unpaidProject = 0;
        return salary;
    }

    @Override
    public String toString() {
        return "Project Employee\n" + super.toString() + "\nUnpaid Projects : "
                + this.unpaidProject + "\nProject Salary : " + this.projectSalary;
    }
}

class Admin extends User {

    private static Admin admin;
    private ArrayList<Employee> employeeList = new ArrayList<>();

    private Admin(String name, String phoneNumber) {
        super(name, phoneNumber);
    }

    public static Admin getAdmin(String name, String phoneNumber) {
        if (admin == null)
            admin = new Admin(name, phoneNumber);

        return admin;
    }

    public static Admin getAdmin() {
        return admin;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addToAllEmployees(Employee employee) {
        employeeList.add(employee);
    }

    @Override
    public String toString() {
        return "Admin\n" + super.toString();
    }
}
