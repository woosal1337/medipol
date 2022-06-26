// Name : Mahmoud suhel mahmoud alkifiri
// Student Number: o150119531
//the purpose of this program is to draw one of the 3 shapes(Line, Parabola, or circle) and it
//will depend on the certain parameters and inputs of the shapes using the mathematical and geometrical formulas that each have
package com.company;

import java.util.Scanner;

public class HW3_o150119531 {
    // Declaring the scanner here to use in the rest of the functions below
    static Scanner theScanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Taking the first one time user input here
        System.out.println("Which shape would you like to draw?\n1. Line \n2. Parabola \n3. Circle\n4. Exit");
        int userChoiceInput = theScanner.nextInt();

        // Based on the given input running the according function below
        while (0 < userChoiceInput && userChoiceInput < 5) {
            if (userChoiceInput == 1) {
                Line();
            } else if (userChoiceInput == 2) {
                Parabola();
            } else if (userChoiceInput == 3) {
                Circle();
            } else {
                System.exit(0);
            }
        }
    }

    // This is where the drawing the line function is called
    public static void Line() {

        // Getting the user input here again to complete the line function
        System.out.println("Line formula is y = ax + b");
        System.out.print("Please enter coefficients a and b: ");
        double a = theScanner.nextDouble();
        double b = theScanner.nextDouble();

        // Based on the function above with the given user input
        // Both for X and Y axis, drawing the graph and in the needed palces graphing
        // the stars
        for (int i = 10; i >= -10; i--) {
            for (int j = -10; j <= 10; j++) {
                double y = a * j + b;

                if (y == i) {
                    System.out.print("*");
                } else if (i == 0 && j == 0) {
                    System.out.print("+");
                } else if (i == 0) {
                    System.out.print("-");
                } else if (j == 0) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        // Drawing the graph is done, so function here will call the user to give
        // another input to run the function again.
        // A kind of recursive function happens here.
        System.out.println("Which shape would you like to draw?\n1. Line \n2. Parabola \n3. Circle\n4. Exit");
        int userChoiceInput = theScanner.nextInt();

        while (0 < userChoiceInput && userChoiceInput < 5) {
            if (userChoiceInput == 1) {
                Line();
            } else if (userChoiceInput == 2) {
                Parabola();
            } else if (userChoiceInput == 3) {
                Circle();
            } else {
                System.exit(0);
            }
        }
    }

    // Drawing Parabola related function happens below
    public static void Parabola() {

        // Getting the user input as well to prepare the parabola function
        System.out.println("Parabola formula is y = ax^2 + bx + c");
        System.out.print("Please enter parabola's coefficients a, b and c: ");
        double a = theScanner.nextDouble();
        double b = theScanner.nextDouble();
        double c = theScanner.nextDouble();

        // Again looping through both X and Y axis to draw the final parabola below:
        for (int i = 10; i >= -10; i--) {
            for (int j = -10; j <= 10; j++) {
                double y = a * j * j + b * j + c;

                if (y == i) {
                    System.out.print("*");
                } else if (i == 0 && j == 0) {
                    System.out.print("+");
                } else if (i == 0) {
                    System.out.print("-");
                } else if (j == 0) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        // Drawing the graph is done, so function here will call the user to give
        // another input to run the function again.
        // A kind of recursive function happens here.
        System.out.println("Which shape would you like to draw?\n1. Line \n2. Parabola \n3. Circle\n4. Exit");
        int userChoiceInput = theScanner.nextInt();

        while (0 < userChoiceInput && userChoiceInput < 5) {
            if (userChoiceInput == 1) {
                Line();
            } else if (userChoiceInput == 2) {
                Parabola();
            } else if (userChoiceInput == 3) {
                Circle();
            } else {
                System.exit(0);
            }
        }
    }

    // Drawing Circle related function happens below
    public static void Circle() {

        // Here as well getting the user input for the a, b, and the radius to comlete
        // the circle function and iterate
        // below through the loops to draw the circle
        System.out.println("Circle formula is (x-a)^2 + (y-b)^2 = r^2 ");
        System.out.print("Please enter circle's coefficients a, b and radius: ");
        double a = theScanner.nextDouble();
        double b = theScanner.nextDouble();
        double r = theScanner.nextDouble();

        // Drawing the graph and iterating through the given user input, and the loops
        // to draw the circle happens below:
        for (int i = 10; i >= -10; i--) {
            for (int j = -10; j <= 10; j++) {
                if ((j - a) * (j - a) + (i - b) * (i - b) == r * r) {
                    System.out.print("*");
                } else if (i == 0 && j == 0) {
                    System.out.print("+");
                } else if (i == 0) {
                    System.out.print("-");
                } else if (j == 0) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        // Drawing the graph is done, so function here will call the user to give
        // another input to run the function again.
        // A kind of recursive function happens here.
        System.out.println("Which shape would you like to draw?\n1. Line \n2. Parabola \n3. Circle\n4. Exit");
        int userChoiceInput = theScanner.nextInt();

        while (0 < userChoiceInput && userChoiceInput < 5) {
            if (userChoiceInput == 1) {
                Line();
            } else if (userChoiceInput == 2) {
                Parabola();
            } else if (userChoiceInput == 3) {
                Circle();
            } else {
                System.exit(0);
            }
        // A special function for the 4th case (Exit) was not created as System.exit(0) 
        // exits the code instantly does not matter where it was used.
        }
    }
}
