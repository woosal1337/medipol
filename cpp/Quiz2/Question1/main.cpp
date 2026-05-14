#include <iostream>
using namespace std;

int main()
{
    int I=10, J=15, *K, *W; // Assume that address of I is 0x123AF0 and address of J is 0x90A4B8
    K=&I; // Saving the hexadecimal value of the position of I to the declared pointer K
    W=&J; // Saving the hexadecimal value of the position of J to the declared pointer W
    cout<< " I = " << I << " J = " << J << endl << " *K " << (*K) << " W = " << W << endl;
    K=W; // Swapping the RAM positions of K and the W accordingly
    cout<< " I = " << I<< " J = " << &J << endl << " **&K " << **(&K) << " *W " << (*W) << endl;
}



// As an import we have given a .h library imported, whereas as <iostream> is already recognized by C++ and can be used instantly as #include <iostream>
// The double quotes were in a wrong format, and were replaced with " " each in order to fix the small syntax error
// The main function of main() in C++ is started with "int main()" instead of "void main()" so, I had to replace them at the same time.
// The expected output of the code in the first cout was the real value of I, J, followed by the pointer values of J, and I
// The next expected output is as we change the saved RAM values between the K and W, the outputs should also change according to them
