package com.company;

class Base {
    Base() {
        System.out.println("Here the Base Class has been called.");
    }
}

class Derived extends Base {
    Derived() {
        System.out.println("Here the Derived Class has been called.");
    }
}

public class Main {
    public static void main(String[] args) {
        Derived d = new Derived();
    }
}

/*

Name: Vusal Ismayilov
Student ID: 64190012
Department: Computer Engineering

 */