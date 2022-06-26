#include <iostream>
using namespace std;

void xD(int* a, string* b, double* c) {
    cout << *a << endl;
    cout << *b <<  endl;
    cout << *c << " " << &c;
}

int main()
{
    // declare variables
    int* pointVar;
    string* helloVar;
    
    int b = 5;
    pointVar = &b;

    string a = "Hello, World!";
    helloVar = &a;


    double c = 5.55;
    double* doubleVar = &c;

    xD(pointVar, helloVar, doubleVar);
}