package com.company;

class Person {

    protected String name;
    protected String address;
    protected String phone;
    protected String email;

    public Person() {
    }

    public Person(String name, String address, String phone, String email) {

        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmaiil() {
        return email;
    }

    public void setEmaiil(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + "]";
    }


}

class Student extends Person {

    protected String status;

    public Student() {
    }

    public Student(String name, String address, String phone, String emaiil, String status) {
        super(name, address, phone, emaiil);
        this.status = status;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student [status=" + status + ", name=" + name + ", address=" + address + ", phone=" + phone
                + ", email=" + email + "]";
    }
}

class Employee extends Person {

    protected String office;
    protected double salary;
    protected String hiringDate;

    public Employee() {

    }

    public Employee(String name, String address, String phone, String emaiil, String office, double salary, String hiringDate) {
        super(name, address, phone, emaiil);
        this.office = office;
        this.salary = salary;
        this.hiringDate = hiringDate;

    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
    }

    @Override
    public String toString() {
        return "Employee [office=" + office + ", salary=" + salary + ", hiringDate=" + hiringDate + ", name=" + name
                + ", address=" + address + ", phone=" + phone + ", email=" + email + "]";
    }

}

class Faculty extends Employee {

    protected String officeHours;
    protected String rank;

    public Faculty() {

    }

    public Faculty(String name, String address, String phone, String emaiil, String office, double salary, String hiringDate, String officeHours, String rank) {
        super(name, address, phone, emaiil, office, salary, hiringDate);
        this.officeHours = officeHours;
        this.rank = rank;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Faculty [officeHours=" + officeHours + ", rank=" + rank + ", office=" + office + ", salary=" + salary
                + ", hiringDate=" + hiringDate + ", name=" + name + ", address=" + address + ", phone=" + phone
                + ", email=" + email + "]";
    }

}

class Staff extends Employee {

    protected String title;

    public Staff() {

    }

    public Staff(String name, String address, String phone, String emaiil, String office, double salary,
                 String hiringDate, String title) {
        super(name, address, phone, emaiil, office, salary, hiringDate);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Staff [title=" + title + ", office=" + office + ", salary=" + salary + ", hiringDate=" + hiringDate
                + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + "]";
    }


}

public class Main {

    public static void main(String[] args) {

        Student aStudent = new Student();
        aStudent.setName("Ferris Bueller");
        aStudent.setAddress("123 Main St.");
        aStudent.setPhone("512-174-1212");
        aStudent.setEmaiil("buellerf@maiil.utexas.edu");
        aStudent.setStatus("freshman");
        System.out.println(aStudent);

        Staff aStaff = new Staff("Jane Doe", "789 First Ave",
                "512-442-1411", "doej@mail.utexas.edu",
                "MAI 3.56", 60000.00, "01 Sep 1991",
                "Manager");

        System.out.println(aStaff.getName() + " has an office at " +
                aStaff.getOffice());

        aStaff.setTitle("Senior Manager");

        System.out.println(aStaff);
    }
}