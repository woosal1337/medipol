#include <iostream>
using namespace std;


// 1) Two different classes inheriting each other were defined below:
class theBase {

public:
    
    void Function1() { cout << "This is a base class function 1\n"; }
    virtual void Function2() {
        cout << "This is a base class function 2\n"; 
    }
    
    theBase(int r = 0, int i = 0) { 
        real = r;   
        imag = i; 
    }

    void printResult() {
        cout << "The real sum is " << real << ", and the imaginary sum is " << imag << "." << endl;
    }

private:
    // 2) One variable and more than one virtual functions were defined:
    int real, imag;
    
friend theBase operator << (theBase const&, theBase const&);

};

// 3) The overload operator function was defined below: 
theBase operator << (theBase const& c1, theBase const& c2)
{
    return theBase(c1.real + c2.real, c1.imag + c2.imag);
}

// 2nd Class, which inherits the first class:
class theDerived : public theBase {

public:
    void Function1() { 
        cout << "This is a derived class function 1\n"; 
    }
    
    void Function2() { 
        cout << "This is a derived class function 2\n"; 
    }
};

int main()
{
    // 4) A main function storing both real and virtual functions 
    // while being assigned to the custom given input values, 
    // and overloaded by the "<<" operator were all
    // defined and ran below:
    theBase c1(10, 15), c2(20, 25);
    theBase cSum = c1 << c2;
    cSum.printResult();

    theBase* p;
    theDerived obj1;
    p = &obj1;
    p->Function1();
    p->Function2();
}