package com.company;

import java.util.Scanner;

// Class for the custom throw Exception
class notInRange extends Exception {
    notInRange(String s) {
        super(s);
    }
}


public class Main {

    // Function to validate the given user input
    static void validate(int userInput) throws notInRange {
        if (userInput > 1000 || userInput < 100) {
            throw new notInRange("NumberOutOfRangeException");
        }
    }


    public static void main(String[] args) {

        Scanner theScanner = new Scanner(System.in);

        int userInput = theScanner.nextInt();

        /* The only case the loop crashes and the code
        ends is the case where the user input is going
        to be exactly equal to -1000. For the rest of
        the cases, if the exception is thrown, the loop
        will still continue asking the user for the new
        inputs till the input is going to be exactly equal
        to -1000.
        */
        while (userInput != -1000) {

            /*
            Checking in the function whether the given input
            is withing the range of 100 < x < 1000.
             */
            try {
                validate(userInput);
            } /*
            If not in the range, throw the exception, else just
            keep asking the user for the new input.
            */ catch (Exception customException) {
                System.out.println("NumberOutOfRangeException" + customException);

            /* Keep asking the user for the new integer input and checking
            the new cases happen here.
            */
                userInput = theScanner.nextInt();
            }
        }
    }
}
