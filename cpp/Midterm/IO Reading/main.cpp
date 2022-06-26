#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main() {
    string myText;
    int a,b;
    // Create and open a text file
    ifstream MyFile("D:\\GitHub\\Medipol-C-CPP\\Midterm\\IO Files\\filename.txt");

//    while (getline (MyFile, myText)) {
//        // Output the text from the file
//        cout << myText;
//    }

    while (MyFile >> a >> b) {
        cout << a << " " << b << "\n";
    }


    // Close the file
    MyFile.close();
}