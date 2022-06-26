package com.company;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String [] pets= {"dog", "cat", "Parrot"}; /* Here I have an array having
		string type elements, I would get an exception out of bound Exception */

        for (int i = 0; i < pets.length; i++) {
            System.out.println(pets[i]);
        }
         /*Array has three indices 0, 1, 2, However,
		I want the system to print index 3 of array pets which is out of bound,
		does not exist  */
    }

}

/*
I have solved the issue as the index 3 showing the 4th element of the array,
which actually does not exist. By looping through the length of the array
and writing them 1-by-1 to the terminal has removed this exception.
 */


/*

Name: Vusal Ismayilov
Student ID: 64190012
Department: Computer Engineering

 */