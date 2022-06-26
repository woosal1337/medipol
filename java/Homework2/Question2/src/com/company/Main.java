///////////////////////////////////////////////////////
//
//    Name: Vusal Ismayilov
// 
//    ID: 64190012
// 
//    Faculty: Computer Engineering
//
///////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    static Scanner scanner;

    public static void main(String[] args) {
        File inputFile = new File(".//src//inputs.txt");

        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("inputs.txt file not found");
            e.printStackTrace();
        }

        System.out.println("Reading data from the inputs.txt file placed in the src directory(package)");
        int rows, columns;

        rows = scanner.nextInt();
        columns = scanner.nextInt();

        int[][] a = readMatrix(rows, columns);
        rows = scanner.nextInt();
        columns = scanner.nextInt();

        int[][] b = readMatrix(rows, columns);
        rows = scanner.nextInt();
        columns = scanner.nextInt();

        int[][] c = readMatrix(rows, columns);

        System.out.println();
        System.out.println(" ******* Matrix A *******");
        printMatrix(a);

        System.out.println();
        System.out.println(" ******* Matrix B *******");
        printMatrix(b);

        System.out.println();
        System.out.println(" ******* Matrix C *******");
        printMatrix(c);

        System.out.println();
        System.out.println(" ******* Matrix D *******");
        printMatrix(d);
    }


    public static int[][] readMatrix(int rows, int columns) {
        int[][] result = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = scanner.nextInt();
            }
        }
        return result;
    }

    public static int[][] generateMatrix(int rows, int columns) {
        int[][] result = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = (int) (Math.random() * 100) + 1;
            }
        }
        return result;
    }

    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("%6d ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        if (a[0].length == b.length) {
            System.out.println("The operation cannot be performed");
            int[][] result = new int[a.length][a[0].length];
            return result;
        } else {
            int[][] result = new int[a.length][a[0].length];

            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int z = 0; z < b.length; z++)
                        result[i][j] += a[i][z] * b[z][j];
                }
            }

            return result;
        }
    }

    public static int[][] transpose(int[][] a) {
        int[][] result = new int[a.length][a[0].length];
        if (a.length != a[0].length) {
            System.out.println("Error!");
        } else {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    result[i][j] = a[j][i];
                }
            }
        }
        return result;
    }

    public static int minOfElements(int[][] a) {
        int min = a[0][0];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (min < a[i][j]) {
                    min = a[i][j];
                }
            }
        }
        return min;
    }

    public static int sumOfElements(int[][] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                sum += a[i][j];
            }
        }
        return sum;
    }

    public static int[][] add(int[][] a, int[][] b) {
        int[][] finalResult = new int[a.length][a[0].length];
        if (a.length != b.length || a[0].length != b[0].length) {
            System.out.println("Error!");
        } else {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    finalResult[i][j] = a[i][j] + b[i][j];
                }
            }
        }
        return finalResult;
    }

    public static int[][] subtract(int[][] a, int[][] b) {
        int[][] finalResult = new int[a.length][a[0].length];
        if (a.length != b.length || a[0].length != b[0].length) {
            System.out.println("Error!");
        } else {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    finalResult[i][j] = a[i][j] - b[i][j];
                }
            }
        }
        return finalResult;
    }


}