package com.example;

class Bike {
    void run() {
        System.out.println("Running");
    }
}

class Splendor extends Bike {
    void run() {
        System.out.println("Running at 60KM safely.");
    }

    public static void main(String args[]) {
        Bike b = new Splendor();
        b.run();
    }
}