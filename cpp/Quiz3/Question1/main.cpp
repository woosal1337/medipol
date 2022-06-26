#include <iostream>

using namespace std;

struct Rational {
    int numerator, denominator;
};

Rational Add(Rational p, Rational r) {
    Rational ans;
    ans.numerator = (p.numerator * r.denominator) + (p.denominator * r.numerator);
    ans.denominator = (p.denominator * r.denominator);
    return ans;
}

Rational Subtract(Rational p, Rational r) {
    Rational ans1;
    ans1.numerator = (p.numerator * r.denominator) - (p.denominator * r.numerator);
    ans1.denominator = p.denominator * r.denominator;
    return ans1;
}

Rational Multiply(Rational p, Rational r) {
    Rational ans2;
    ans2.numerator = p.numerator * r.numerator ;
    ans2.denominator = p.denominator * r.denominator;
    return ans2;
}

Rational Divide(Rational p, Rational r) {
    Rational ans3;
    ans3.numerator = p.numerator * r.denominator;
    ans3.denominator = p.denominator * r.numerator;
    return ans3;
}

double Value(Rational r) {
    return (r.numerator / r.denominator);
}

void Input(Rational &r) {
    char slash = (char)"/";
    cout << "Enter a Rational number in the form A/B:";
    cin >> r.numerator >> slash >> r.denominator;
}

void Print(Rational r) {
    cout << r.numerator << "/" << r.denominator;
}

int main(void) {
    Rational x, y, z;
    x.numerator = 1;
    x.denominator = 2;
    y.numerator = 3;
    y.denominator = 4;
    z.numerator = 5;
    z.denominator = 6;
    double v;

    cout << "The rational number created by the constructor = ";
    Print(x);
    cout << endl;

    Input(x);
    cout << "The rational number input = ";
    Print(x);
    cout << endl;

    Input(y);
    z = Add(x, y);
    Print(x);
    cout << " + ";
    Print(y);
    cout << " = ";
    Print(z);
    cout << endl;

    z = Subtract(x, y);
    Print(x);
    cout << " - ";
    Print(y);
    cout << " = ";
    Print(z);
    cout << endl;

    z = Multiply(x, y);
    Print(x);
    cout << " * ";
    Print(y);
    cout << " = ";
    Print(z);
    cout << endl;

    z = Divide(x, y);
    Print(x);
    cout << " / ";
    Print(y);
    cout << " = ";
    Print(z);
    cout << endl;

    v = Value(z);
    cout << "The value of ";
    Print(z);
    cout << " = " << v << endl;

    return 0;
}