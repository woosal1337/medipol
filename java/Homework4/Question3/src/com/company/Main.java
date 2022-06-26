package com.company;

import java.io.File;

public class Main {

    public static void main(String args[]) {
        File filePath = new File("D:\\Steam\\steamapps\\common\\wallpaper_engine");

        if (!filePath.exists()) {
            System.out.println(args[0] + "Does not exists.");
            return;
        }
        listFunc(filePath, "");
    }

    public static void listFunc(File inputPath, String customString) {
        File theContent[] = inputPath.listFiles();


        if (theContent != null) {
            for (int i = 0; i < theContent.length; i++)
                if (theContent[i].isDirectory()) {
                    System.out.println(customString + "+--" + theContent[i].getName());

                    listFunc(theContent[i], customString + "  ");
                } else {
                    System.out.println(customString + "+--" + theContent[i].getName() + " " + theContent[i].length());
                }
        }
    }
}