// On Github if needed just in case: https://github.com/woosal1337/Medipol-JAVA/tree/master/Homework1/src/com/homework

package com.homework;

public class Second {
    public static void main(String[] args) {
        String s1 = "Welcome to Java";
        String s2 = s1;
        String s3 = new String("Welcome to Java");
        String s4 = "Welcome to Java";

        // Printing out all the needs:
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
        System.out.println(s1.equals(s2));
        System.out.println(s2.equals(s3));
        System.out.println(s1.compareTo(s2));
        System.out.println(s2.compareTo(s3));
        System.out.println(s1 == s4);
        System.out.println(s1.charAt(0));
        System.out.println(s1.indexOf("to"));
        System.out.println(s1.lastIndexOf("a"));
        System.out.println(s1.lastIndexOf("0", 15));
        System.out.println(s1.length());
        System.out.println(s1.substring(5));
        System.out.println(s1.substring(5, 11));
        System.out.println(s1.startsWith("Wel"));

        // The last word in the string
        // Listing all the words inside the string into a list, and the finding the latest indexed word:
        s1 = s1.trim();
        String[] s1Words = s1.split(" ");
        System.out.println(s1Words[s1Words.length - 1]);
    }
}
