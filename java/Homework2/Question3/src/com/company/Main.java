///////////////////////////////////////////////////////
//
//    Name: Vusal Ismayilov
// 
//    ID: 64190012
// 
//    Faculty: Computer Engineering
//
///////////////////////////////////////////////////////

package com.company;

public class Main {
    public static void main(String[] args) {

        // First person1 instance based on the PersonAddress class
        PersonAdress person1 = new PersonAdress();
        person1.setpersonName("Vusal");
        person1.setpersonLastName("Ismayilov");
        person1.setpersonMail("woosal@protonmail.com");
        person1.setpersonNumber("+901234567899");
        System.out.println("User details are as following:\n" +
                person1.getpersonName() + " " +
                person1.getpersonLastName() + " " +
                person1.getpersonMail() + " " +
                person1.getpersonNumber() + "\n");

        // Person2 second new instance is created to compare at the end based on the solely names
        PersonAdress person2 = new PersonAdress();
        person2.setpersonName("Vusal");
        person2.setpersonLastName("Chalabi");
        person2.setpersonMail("woosal@protonmail.com");
        person2.setpersonNumber("+901234567899");
        System.out.println("User details are as following:\n" +
                person2.getpersonName() + " " +
                person2.getpersonLastName() + " " +
                person2.getpersonMail() + " " +
                person2.getpersonNumber() + "\n");

        // Checking whether the 2 given instances are equal based on the solely names
        System.out.println(person1.getpersonName() == person2.getpersonName());
        System.out.println(person1.getpersonLastName() == person2.getpersonLastName());
    }
}
