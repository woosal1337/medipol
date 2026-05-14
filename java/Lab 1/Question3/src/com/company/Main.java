package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter a character: ");
        char charInput = userInput.next().charAt(0);
        System.out.println("ASCII conversion of " + charInput + " is " + ((int)charInput));
    }
}
