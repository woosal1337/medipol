#include<iostream>

using namespace std;

class Rational {
    int numerator;
    int denominator;
public:
    void getInput() {

        char slash = '\\';
        cout << "Enter a Rational number in the form A/B:";
        cin >> numerator >> slash >> denominator;
    }

    void operator+(Rational);

    void operator-(Rational);

    void operator*(Rational);

    void operator/(Rational);
};

void Rational::operator+(Rational c1) {
    Rational temp;
    temp.numerator = (numerator * c1.denominator) + (c1.numerator * denominator);
    temp.denominator = denominator * c1.denominator;
    cout << "\nRational Addition: " << temp.numerator << "\\" << temp.denominator;
}

void Rational::operator-(Rational c1) {
    Rational temp;
    temp.numerator = (numerator * c1.denominator) - (c1.numerator * denominator);
    temp.denominator = denominator * c1.denominator;
    cout << "\nRational Subtraction: " << temp.numerator << "\\" << temp.denominator;
}

void Rational::operator*(Rational c1) {
    Rational temp;
    temp.numerator = numerator * c1.numerator;
    temp.denominator = denominator * c1.denominator;
    cout << "\nRational Multiplication: " << temp.numerator << "\\" << temp.denominator;
}

void Rational::operator/(Rational c1) {
    Rational temp;
    temp.numerator = numerator * c1.denominator;
    temp.denominator = c1.numerator * denominator;
    cout << "\nRational Division: " << temp.numerator << "\\" << temp.denominator;
}


int main() {

    Rational x, y;
    cout << endl << "Please enter the credentials for X:";
    x.getInput();

    cout << endl << "Please enter the credentials for Y:";
    y.getInput();

    x + y;
    x - y;
    x * y;
    x / y;

    cout << "\n";
    return 0;
}