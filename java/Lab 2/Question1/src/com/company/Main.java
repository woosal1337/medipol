package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        // User input
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the number you want to find the factorial of: ");
        int num = scan.nextInt();

        // Declaring the integer variable that will contain the factorial of the given input
        int finalNum = 1;

        // Looping through the given integer, and multiplying the finalNum but the numbers
        for (int i = 1; i <= num; i++) {
            finalNum *= i;
        }

        // Printing out the final result
        System.out.println(finalNum);
    }
}

// Name: Vusal Ismayilov  Student ID: 64190012  Faculty: Computer Engineering