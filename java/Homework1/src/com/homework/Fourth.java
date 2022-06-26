// On Github if needed just in case: https://github.com/woosal1337/Medipol-JAVA/tree/master/Homework1/src/com/homework

package com.homework;

public class Fourth {

    // Declaring the encryption function here:
    public static void encrypt(String message, int key) {
        // Declaring the string for the final result
        String encryptedText = "";
        // Declaring integer to get the value of the character in ASCII
        int z;

        // Looping through the message and encrypting every character (char)
        for (int i = 0; i < message.length(); i++) {
            z = message.charAt(i);
            char ch;

            // If the sum will overpass the range of ASCII it subtracts 127:
            if (z + key > 126) {
                ch = (char) (32 + (z + key) - 127);
                encryptedText += ch;
            } else {
                ch = (char) (z + key);
                encryptedText += ch;
            }
        }
        // Message has been decrypted and printing out:
        System.out.println("Encrypted message for " + "'" + message + "'" + " is " + encryptedText);
        // Can be commented to be separately decrypted as soon as the encryption is done:
        decrypt(encryptedText, key);
    }

    // Declaring the decryption function here:
    public static void decrypt(String message, int key) {
        // Declaring the string for the final result
        String decryptedText = "";
        // Declaring integer to get the value of the character in ASCII
        int z;

        // Looping through the message and encrypting every character (char)
        for (int i = 0; i < message.length(); i++) {
            z = message.charAt(i);
            char ch;

            // If the sum will overpass the range of ASCII it subtracts 127:

            if (z - key < 0) {
                ch = (char) ((z - key) - 32 + 127);
                decryptedText += ch;
            } else {
                ch = (char) (z - key);
                decryptedText += ch;
            }

        }
        // Message has been decrypted and printing out:
        System.out.println("Encrypted message for " + "'" + message + "'" + " is " + decryptedText);
    }


    public static void main(String[] args) {
        String msg1 = "Lost time is never found again.";
        String msg2 = "Use it or lose it.";

        // Just to show the "Hey" example over the function, which was mentioned in the question for demo
        //encrypt("Hey", 10);

        // msg1 and msg2 encryption and decryption processes happen here:
        encrypt(msg1, 15);
        encrypt(msg2, 15);
    }
}
