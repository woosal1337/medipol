package com.company;

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args)
    {

        // Get the array
        int arr[][]
                = { { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 } };

        // Print the array with the help of loop
        for (int i = 0; i < arr.length; i++) {

            System.out.print("[");
            for (int j = 1; j < arr[1].length; j++) {
                System.out.print(" " + arr[i][j] + ", ");
            }
            System.out.print("], ");
        }
    }
}

// Name: Vusal Ismayilov    ID:64190012    Lab2: Question 3
// Syntax error was at line 23, where Out was supposed to be out, and Print was supposed to be print