package com.company;

class Main {
    public static void main(String args[]) {
        int totalSum = 0;
        for (int x = 0; x < 101; x++) {
            int temp;
            boolean isPrime = true;

            int num = x;
            for (int i = 2; i <= num / 2; i++) {
                temp = num % i;
                if (temp == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                totalSum += num;
            }

        }
        System.out.println("Total Sum of Prime numbers is: " + totalSum);
    }
}
