package com.company;

// Declaring the first main/parent classA here:
class classA {
    int a;
    String A;
}

// Declaring a child class that extends the parent classA:
class classB extends classA {
    int b;
    String B;
}

/* Declaring classC and classD, which are also child classes
within themselves, however, also extending the child classB
*/
class classC extends classB {
    int c;
    String C;
}

class classD extends classB {
    int d;
    String D;
}


public class Main {

    public static void main(String[] args) {
        classA thisIsClassA = new classA();
        thisIsClassA.a = 5;
        thisIsClassA.A =  "Five ";

        classB thisIsClassB = new classB();
        thisIsClassB.b = 10;
        thisIsClassB.B = " Ten ";

        classC thisIsClassC = new classC();
        thisIsClassC.c = 15;
        thisIsClassC.C = " Fifteen ";

        classD thisIsClassD = new classD();
        thisIsClassD.d = 20;
        thisIsClassD.D = " Twenty ";

        System.out.println(thisIsClassA.a + thisIsClassB.b  + thisIsClassC.c + thisIsClassD.d);
        System.out.println(thisIsClassA.A + thisIsClassB.B + thisIsClassC.C + thisIsClassD.D);
    }
}

/*
Name : Vusal Ismayilov
Student ID: 64190012
Department: Computer Engineering

Java Lab Assignment 5.0
*/