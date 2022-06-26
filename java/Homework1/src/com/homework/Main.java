// On Github if needed just in case: https://github.com/woosal1337/Medipol-JAVA/tree/master/Homework1/src/com/homework

package com.homework;

import java.util.Scanner;


// There was supposed to be only 5 different (1-5) inputs in the question, however, there are literally 6 including the Exit command. So, had to set the input ranging from 1 to 6.

public class Main {

    // Addition function to return the sum:
    public static void addition(int number1, int number2) {
        System.out.println("Result of " + number1 + " + " + number2 + " = " + (number1 + number2) + "\n");
    }

    // Subtraction function to return the result after the subtraction:
    public static void subtraction(int number1, int number2) {
        System.out.println("Result of " + number1 + " - " + number2 + " = " + (number1 - number2) + "\n");
    }

    // Multiplication function to return the the multiplied value of number1 and number2:
    public static void multiplication(int number1, int number2) {
        System.out.println("Result of " + number1 + " * " + number2 + " = " + (number1 * number2) + "\n");
    }

    // Required a number2 != 0 function (Same requirement is applied for the division function):
    public static void remainder(int number1, int number2) {
        while (number2 == 0) {
            System.out.println("Dividend can not be equal to 0! Try, again!");

            Scanner userInput = new Scanner(System.in);
            System.out.println("Please, enter the first number:");
            number1 = userInput.nextInt();
            System.out.println("Please, enter the second number:");
            number2 = userInput.nextInt();
        }

        System.out.println("Result of " + number1 + " % " + number2 + " = " + (number1 % number2) + "\n");
    }

    // Required a number2 != 0 function (Same requirement is applied for the remainder function):
    public static void division(int number1, int number2) {
        while (number2 == 0) {
            System.out.println("Dividend can not be equal to 0! Try, again!");

            Scanner userInput = new Scanner(System.in);
            System.out.println("Please, enter the first number:");
            number1 = userInput.nextInt();
            System.out.println("Please, enter the second number:");
            number2 = userInput.nextInt();
        }

        // Could be directly used the number1 / number2, but divison would round and return an integer result, I have added double to the second integer in order to make the result much more precise without roundings and etc.

        double theResult = number1 / (double) number2;
        System.out.println("Result of " + number1 + " / " + number2 + " = " + (theResult));
    }

    public static void main(String[] args) {
        // Here is the main menu

        Boolean isOn = true;
        while (isOn) {

            Scanner userInput = new Scanner(System.in);
            System.out.println("Select Operation:\n1:Addition\n2:Subtraction\n3:Multiplication\n4:Divison\n5:Remainder\n6:Exit\n");
            System.out.println("Enter your choice: ");
            int theMenuSelection = userInput.nextInt();

            if (1 <= theMenuSelection && theMenuSelection <= 6) {
                // If the user just wants to exit the program, asking here first so there is no need to get the user input and confuse the user:
                if (theMenuSelection == 6) {
                    break;
                }

                // If the input is not directing to the exit function, just continue here by getting the user inputs:
                System.out.println("Please, enter the first number:");
                int number1 = userInput.nextInt();
                System.out.println("Please, enter the second number:");
                int number2 = userInput.nextInt();

                // Calling the main functions will be happening here depending on the user input:
                if (theMenuSelection == 1) {
                    addition(number1, number2);
                } else if (theMenuSelection == 2) {
                    subtraction(number1, number2);
                } else if (theMenuSelection == 3) {
                    multiplication(number1, number2);
                } else if (theMenuSelection == 4) {
                    division(number1, number2);
                } else if (theMenuSelection == 5) {
                    remainder(number1, number2);
                }
            }
        }
    }
}
