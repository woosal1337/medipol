#include <iostream>
using namespace std;

class Point {

public:
    
    Point(int a = 0, int b = 0) {
        x = a;
        y = b;
    }

    int getX() const {
        return x;
    }

    int getY() const {
        return y;
    }

protected:
    
    int x, y;
};

ostream& operator << (ostream& out, const Point& s) {
    
    out << "Point (" << s.getX() << "," << s.getY() << ")" << endl;
    return out;
}


class Circle : public Point {

protected:
   
    double r;
    int x;
    int y;

public:
    
    Circle(double r, int x, int y) : Point(x, y) {
        this->r = r;
        this->x = x;
        this->y = y;
    }
    
    void setLength(double rad) {
        r = rad;
    }
    
    double getLength() const {
        // The circumference of the circle is 2*Pi*r, so:
        return r * 2 * 3.14;
    }
    
    double getArea() const {
        // The area of the circle is 2*Pi*r, so:
        return 3.14 * r * r;
    }

    double getX() const {
        return x;
    }

    double getY() const {
        return y;
    }
};

class Cylinder : public Circle {

protected:
    
    double h;
    double r;

public:
    
    Cylinder(double h, double r) : Circle(r, x, y) {
        this->h = h;
        this->r = r;
    }
    
    void setHeight(double height) {
        h = height;
    }
    
    double getHeight() const {
        return h;
    }
    
    double getArea() const {
        return (2 * 3.14 * r * h) + (2 * 3.14 * r * r);
    }
    
    double getVolume() const {
        return 3.14 * r * r * h;
    }

    double getRadius() const {
        return r;
    }
};


class Square : public Point {

protected:

    double side;

public:
    
    Square(double side, int x, int y) : Point(x, y) {
        this->side = side;
    }
    
    void setLength(double l) {
        side = l;
    }
    
    double getLength() const {
        return side;
    }
    
    double getArea() const {
        return side * side;
    }
};

ostream& operator << (ostream& out, const Square& s) {
    
    out << "Square on (" << s.getX() << "," << s.getY() << ")" << endl;
    out << "Area: " << s.getArea() << endl;
    return out;
}

class Cube : public Square {

public:
    
    Cube(double side, int x, int y) : Square(side, x, y) {
    }
    
    void setHeight(double h) {
        side = h;
    }
    
    double getHeight() const {
        return side;
    }
    
    double getArea() const {
        return 6 * side * side;
    }
    
    double getVolume() const {
        return side * side * side;
    }
};

ostream& operator << (ostream& out, const Cube& s) {
   
    out << "Cube on (" << s.getX() << "," << s.getY() << ")" << endl;
    
    out << "Area: " << s.getArea() << endl;
    
    out << "Volume: " << s.getVolume() << endl;
    
    return out;
}

int main() {
    // Inputs are as same as they were used in the class file
    Point p(11, 22);
    cout << p << endl;
    Square square1(4.5, 72, 29);
    cout << square1 << endl;
    Square square2(10, 5, 5);
    cout << square2 << endl;
    Cube cube1(5, 10, 12);
    cout << cube1 << endl;
    Circle circle1(5, 0, 0);
    cout << "Circle on " << "(" << circle1.getX() << "," << circle1.getY() << ")" << "\nCircle circumference: "<< circle1.getLength() << "\nArea:"<< circle1.getArea() << endl;
    // As cylinder is the circle extended and has the same circle values as Circle() class, they both will have the same X and Y coordinates of the circle.
    Cylinder cylinder1(5, 5);
    cout << "\nCylinder surface area: " << cylinder1.getArea() << "\nCylinder volume: " << cylinder1.getVolume() << endl;

    return 0;
}