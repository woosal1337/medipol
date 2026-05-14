package com.company;

import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<String> medipolLinks = findLinks("https://www.medipol.edu.tr/en/");
        ArrayList<String> ituLinks = findLinks("http://www.itu.edu.tr");
        ArrayList<String> bilgiinks = findLinks("http://www.bilgi.edu.tr");
        ArrayList<String> ticaretLinks = findLinks("http://www.ticaret.edu.tr");
        ArrayList<String> khasLinks = findLinks("http://www.khas.edu.tr");
        ArrayList<String> dogusinks = findLinks("http://www.dogus.edu.tr");
        ArrayList<String> ozyeginLinks = findLinks("http://www.ozyegin.edu.tr");
        ArrayList<String> sabanciLinks = findLinks("http://www.sabanciuniv.edu.tr");
        ArrayList<String> yildizLinks = findLinks("http://www.yildiz.edu.tr");

        ArrayList<String> allWebsites = new ArrayList<String>();
        allWebsites.add("https://www.medipol.edu.tr/en/");
        allWebsites.add("http://www.itu.edu.tr");
        allWebsites.add("http://www.bilgi.edu.tr");
        allWebsites.add("http://www.ticaret.edu.tr");
        allWebsites.add("http://www.khas.edu.tr");
        allWebsites.add("http://www.dogus.edu.tr");
        allWebsites.add("http://www.ozyegin.edu.tr");
        allWebsites.add("http://www.sabanciuniv.edu.tr");
        allWebsites.add("http://www.yildiz.edu.tr");


        // Getting the images:

        try {
            File myWriteFile = new File("WebImages.txt");
            if (myWriteFile.createNewFile()) {
                System.out.println("File created: " + myWriteFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriteFile = new FileWriter("WebImages.txt");

            for (int i = 0; i < allWebsites.size(); i++) {
                Document doc = Jsoup.connect(allWebsites.get(i)).get();
                Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif|vsf|svg)]");
                for (Element image : images) {
                    System.out.println(allWebsites.get(i) + image.attr("src"));
                    myWriteFile.write(allWebsites.get(i) + image.attr("src") + "\n");
                }
            }
            myWriteFile.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        // Getting the emails:

        try {
            File myWriteFile = new File("EmailAddresses.txt");
            if (myWriteFile.createNewFile()) {
                System.out.println("File created: " + myWriteFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriteFile = new FileWriter("EmailAddresses.txt");

            for (int j = 0; j < allWebsites.size(); j++) {
                Document doc = Jsoup.connect(allWebsites.get(j)).get();
                Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif|vsf|svg)]");

                Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
                Matcher matcher = p.matcher(doc.text());
                Set<String> emails = new HashSet<String>();
                while (matcher.find()) {
                    emails.add(matcher.group());
                }

                ArrayList<String> allMails = new ArrayList<String>();
                Set<String> links = new HashSet<String>();

                Elements elements = doc.select("a[href]");
                for (Element e : elements) {
                    links.add(e.attr("href"));
                    allMails.add(e.attr("href"));
                }


                myWriteFile.write(emails + "\n");

            }
            myWriteFile.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    private static ArrayList<String> findLinks(String url) throws IOException {

        Set<String> links = new HashSet<>();
        ArrayList<String> theLinks = new ArrayList<String>();
        ArrayList<String> urlLinks = new ArrayList<String>();

        Document doc = Jsoup.connect(url).get();

        Elements elements = doc.select("a[href]");
        for (Element element : elements) {
            links.add(element.attr("href"));
            theLinks.add(element.attr("href"));
        }

        for (int i = 0; i < theLinks.size(); i++) {

            if (theLinks.get(i).contains("https://")) {
                urlLinks.add(theLinks.get(i));
            }
        }

        return urlLinks;
    }
}


/*
        Find the images' links


//public class Main {
//    public static void main( String[] args ) throws IOException{
//        Document doc = Jsoup.connect("https://www.medipol.edu.tr/").get();
//        Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
//        for (Element image : images) {
//            System.out.println("src : " + image.attr("src"));
//        }
//
//    }
//}

 */