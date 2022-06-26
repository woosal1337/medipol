package com.woosal;

class Triangle extends GeometricObject implements Comparable<Triangle> {
    private int side1;
    private int side2;
    private int side3;
    private String color;
    private boolean isFilled;

    public Triangle(int side1, int side2, int side3, String color, boolean filled) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.color = color;
        this.isFilled = filled;
    }

    public int getSide1() {
        return side1;
    }

    public int getSide2() {
        return side2;
    }

    public int getSide3() {
        return side3;
    }

    public String getColor() {
        return color;
    }

    public boolean getIsFilled() {
        return isFilled;
    }

    public double getPerimeter() {
        return (side1 + side2 + side3);
    }

    public double getArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.round(Math.sqrt(s * (s - side1) * (s - side2) * (s - side3)));
    }

    public int compareTo(Triangle tri) {
        if (getArea() == tri.getArea())
            return 0;
        else if (getArea() > tri.getArea())
            return 1;
        else
            return -1;
    }

    public boolean equals(Triangle tri) {
        if (side1 == tri.getSide1() && side2 == tri.getSide2() && side3 == tri.getSide3() && color.equals(tri.getColor()) && isFilled == tri.getIsFilled())
            return true;
        return false;

    }

    public String toString() {
        return "Triangle Color: " + color + "\tisFilled: " + isFilled + "\t Side 1: " + side1 + "\tSide 2: " + side2 + "\tSide 3: " + side3 + "\tPerimeter: " + getPerimeter()
                + "\tArea: " + getArea() + "\n";
    }
}

// driver class
public class TriangleTest {
    public static void main(String[] args) {

        java.util.ArrayList<Triangle> list = new java.util.ArrayList<Triangle>();

        Triangle t1 = new Triangle(20, 20, 20, "Black", false);

        Triangle t2 = new Triangle(15, 15, 15, "Green", true);

        Triangle t3 = new Triangle(12, 12, 12, "Green", true);

        Triangle t4 = new Triangle(25, 25, 25, "Blue", false);

        Triangle t5 = new Triangle(8, 9, 10, "Yellow", true);

        list.add(t1);

        list.add(t2);

        list.add(t3);

        list.add(t4);

        list.add(t5);

        System.out.println("\nDisplaying the triangle info:\n");
        list.forEach(tri -> System.out.println(tri));

        System.out.println("\nSorting the Triangles:\n");
        java.util.Collections.sort(list);

        list.forEach(tri -> System.out.println(tri));
    }
}