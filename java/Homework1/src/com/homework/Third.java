// On Github if needed just in case: https://github.com/woosal1337/Medipol-JAVA/tree/master/Homework1/src/com/homework

package com.homework;

public class Third {
    public static void main(String[] args) {

        // Summing all the 3 & 5 divisibles to this declared variable:
        int sum = 0;

        // Iterating through 1 -> 50:
        for (int i = 1; i <= 50; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                // Printing every integer that are divisible by 3 and 5:
                System.out.println(i);
                // Summing those integers to my (int) sum variable:
                sum += i;
            }
        }
        // Printing out the total sum at the end:
        System.out.println("The total sum of the integers that are divisible by either 3 or 5 starting from 1 to 50 is: " + sum);
    }
}
