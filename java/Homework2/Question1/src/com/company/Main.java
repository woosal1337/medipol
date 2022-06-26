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

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int n = 1000, diceSum = 0;

        // Creating the dictionary to store all the value here only once
        HashMap<Integer, Integer> starTable = new HashMap<Integer, Integer>();


        // Iterating through 11 dices, N times, and summing them up by saving the result to the dictionary at the end
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 11; j++) {
                int randomDice = (int) (Math.random() * 6 + 1);
                diceSum += randomDice;
            }

            if (starTable.containsKey(diceSum)) {
                starTable.put(diceSum, starTable.get(diceSum) + 1);
            } else {
                starTable.put(diceSum, 1);
            }
            diceSum = 0;
        }

        // Reading the dictionary and writing the result in the output
        for (int i = 11; i < 67; i++) {

            // If key has a value greater than 0, that many of the stars will be printed out
            if (starTable.containsKey(i)) {
                System.out.print(i + ":");

                for (int j = 1; j < starTable.get(i) + 1; j++) {
                    System.out.print("*");
                }
                System.out.print("\n");

            // If the key has a value 0, I will not add it to the dictionary and waste my RAM, but instead just leave it blank
            } else {
                System.out.print(i + ":" + "\n");
            }
        }

    }
}
