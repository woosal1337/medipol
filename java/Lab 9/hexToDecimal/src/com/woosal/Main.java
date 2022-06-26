package com.woosal;

public class Main {
    public static int getDecimal(String hex) {
        String digits = "0123456789ABCDEF";
        hex = hex.toUpperCase();
        int val = 0;
        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            int d = digits.indexOf(c);
            val = 16 * val + d;
        }
        return val;
    }

    public static void main(String args[]) {
        System.out.println("Hex valued 'A' converted to decimal is: " + getDecimal("A"));
        System.out.println("Hex valued 'B' converted to decimal is: " + getDecimal("B"));
        System.out.println("Hex valued 'C' converted to decimal is: " + getDecimal("C"));
        System.out.println("Hex valued 'D' converted to decimal is: " + getDecimal("D"));
        System.out.println("Hex valued 'E' converted to decimal is: " + getDecimal("E"));
        System.out.println("Hex valued 'F' converted to decimal is: " + getDecimal("F"));
        System.out.println("Hex valued 'ABCDEF' converted to decimal is: " + getDecimal("ABCDEF"));
    }
}

/*
Vusal Ismayilov - 64190012 - Computer Engineering - Lab Hex to Decimal
*/