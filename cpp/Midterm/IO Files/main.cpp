#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main() {
    string myText;

    // Create and open a text file
    ofstream MyFile("D:\\GitHub\\Medipol-C-CPP\\Midterm\\IO Files\\filename.txt");

    MyFile << "IDK BRO" << "\n";

    // Close the file
    MyFile.close();
}