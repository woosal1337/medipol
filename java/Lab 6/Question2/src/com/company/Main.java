package com.company;

public class Main { /* Constructors ahs same name as class*/

    int empId;
    String empName;

    //parameterized constructor with two parameters of different data type
    Main(int id, String name){
        this.empId = id;
        this.empName = name;
    }
    void info(){
        System.out.println("Id: "+empId+" Name: "+empName);
    }

    public static void main(String args[]){  /* Creating objects will automatically
   calls the constructors*/
        Main obj1 = new Main(10245,"Alex");
        Main obj2 = new Main(92232,"Bob");
        obj1.info();
        obj2.info();
    }
}


/*
Name: Vusal Ismayilov
Student ID: 64190012
Department: Computer Engineering

 */