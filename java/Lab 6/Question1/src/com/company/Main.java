package com.company;

/* In this program we are checking the Student age
 * if the student age<12 and weight <40 then our program
 * should return that the student is not eligible for registration.
 */
public class Main {
    static void checkEligibilty(int stuage, int stuweight){
        if(stuage<12 && stuweight<40) {
            throw new ArithmeticException("Student is not eligible for registration");
        }
        else {
            System.out.println("Student Entry is Valid!!");
        }
    }

    public static void main(String args[]){
        System.out.println("Welcome to the Registration process!!");
        checkEligibilty(13, 41);
        System.out.println("Have a nice day..");
    }
}


/*
To solve this question, there is a condition declared above, which tells that
if the stage is less than 10 and stuweight is less than 40, then throw an exception
There are 2 ways to solve it. Firstly, either make the changes on the input and change the variables,
secondly we can change the required/checking function requirements.

 */

/*
Name: Vusal Ismayilov
Student ID: 64190012
Department: Computer Engineering

 */