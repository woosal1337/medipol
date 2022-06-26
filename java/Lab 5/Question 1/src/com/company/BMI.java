package com.company;

// Name - Vusal Ismayilov
// ID - 64190012
// Department - Computer Engineering
// Question 1

public class BMI {

    String name;
    int age;
    double weight;
    double height;

    public BMI(String name, int age, double weight, double height) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }


    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }


    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double bmiIndex() {
        return weight / (height * height);
    }

    public String bmiStatus() {
        double bmiIndex = this.bmiIndex();
        if (bmiIndex > 25.0) {
            return "OVERWEIGHT";
        } else if (bmiIndex > 18.5 && bmiIndex < 24.9) {
            return "NORMAL";
        } else {
            return "UNDERWEIGHT";
        }
    }

    @Override
    public String toString() {
        return ("Name - " + this.getName() +
                "\nHeight -  " + this.getHeight() +
                "\n Weight - " + this.getWeight() +
                "\n Age " + this.getAge() +
                "\n BMI Index " + this.bmiIndex() +
                "\n Person Status is " + this.bmiStatus());
    }

    public static void main(String[] args) {
        BMI person1 = new BMI("Vusal", 18, 53.0, 1.73);
        BMI person2 = new BMI("Jeff", 42, 69.0, 1.85);
        System.out.println(person1.toString());
        System.out.println("\n");
        System.out.println(person2.toString());
    }
}