package com.company;

public class Main {

    public static void main(String[] args) {
        // Declaring the arrays
        int firstArray[] = {10, 20, 30, 40, 50};

        // Copying the first array to another one
        int secondArray[] = firstArray;

        // Checking the secondArray if it is really equal to the firstArray
        for (int i = 0; i < secondArray.length; i++) {
            System.out.println(secondArray[i]);
        }
    }
}
