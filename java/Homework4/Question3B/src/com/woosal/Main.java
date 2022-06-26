package com.woosal;

import java.io.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {


    public static void main(String args[]) throws IOException {
        Dictionary dictionary = new Hashtable();

        File filePath = new File("D:\\GitHub\\Medipol-JAVA\\Homework4\\Question3B\\test");

        if (!filePath.exists()) {
            System.out.println(args[0] + "Does not exists.");
            return;
        }
        listFunc(filePath, "", dictionary);

        System.out.println(dictionary);
    }


    public static void listFunc(File inputPath, String customString, Dictionary dictionary) throws IOException {
        File[] theContent = inputPath.listFiles();

        if (theContent != null) {
            for (int i = 0; i < theContent.length; i++)
                if (theContent[i].isDirectory()) {
                    listFunc(theContent[i], customString + "  ", dictionary);
                } else {

                    // Checking if the found file is a .txt file:
                    if (theContent[i].getName().toLowerCase().endsWith((".txt"))) {

                        BufferedReader br = new BufferedReader(new FileReader(theContent[i]));

                        String str = br.readLine();

                        str = str.replaceAll("[^a-zA-Z0-9]", " ");
                        String[] strContent = str.split(" ");

                        for (int x = 0; x < strContent.length; x++) {
                            dictionary.put(strContent[x], theContent[i]);
                        }

                        br.close();
                        // System.out.println(theContent[i]); Gives us the .txt files' path/directory
                    }
                }
        }

    }
}