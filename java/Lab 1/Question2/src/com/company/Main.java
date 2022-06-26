package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String charInput = userInput.next();

        if (charInput.equalsIgnoreCase("a") || charInput.equalsIgnoreCase("e") || charInput.equalsIgnoreCase("i") || charInput.equalsIgnoreCase("o") || charInput.equalsIgnoreCase("u")) {
            System.out.println(charInput + " is a vowel.");
        } else {
            System.out.println(charInput + " is a consonant.");
        }
    }
}
