#include <iostream>

using namespace std;

int main() {

    // Declaring the array of 500 max integers:
    int theArray[500];
    int zero19 = 0;
    int twenty39 = 0;
    int forty59 = 0;
    int sixty79 = 0;
    int eighty100 = 0;
    string theStars = "******";

    // Taking the input and appending to the main list:
    int a; // User input
    int theLoop = 0;
    bool isOn = true;
    while (isOn) {
        cin >> a;

        if (a > 0 && a < 100) {
            theArray[theLoop] = a;
            theLoop += 1;
        }

        if (theLoop == 500 || a < 0) {
            isOn = false;
        }
    }

    // Reading the whole array and sorting it:
    for (int i = 0; i < theLoop; i++) {
        cout << theArray[i];

        if (theArray[i] > 0 && theArray[i] <= 19) {
            zero19 += 1;
        } else if (theArray[i] >= 20 && theArray[i] <= 39) {
            twenty39 += 1;
        } else if (theArray[i] >= 40 && theArray[i] <= 59) {
            forty59 += 1;
        } else if (theArray[i] >= 60 && theArray[i] <= 79) {
            sixty79 += 1;
        } else {
            if (theArray[i] >= 80 && theArray[i] <= 100) {
                eighty100 += 1;
            }
        }

    }

    cout << endl << endl;


    // Printing out the results in stars:
    for (int i = 0; i < zero19; i++) {
        cout << theStars << endl;
    }
    cout << "0-19" << endl << endl;

    for (int i = 0; i < twenty39; i++) {
        cout << theStars << endl;
    }

    cout << "20-39" << endl << endl;


    for (int i = 0; i < forty59; i++) {
        cout << theStars << endl;
    }

    cout << "40-59" << endl << endl;

    for (int i = 0; i < sixty79; i++) {
        cout << theStars << endl;
    }

    cout << "60-79" << endl << endl;

    for (int i = 0; i < eighty100; i++) {
        cout << theStars << endl;
    }

    cout << "80-100" << endl << endl;

    return 0;

    // New functions and arrays could be added, however, this is much more simple and efficient.
}
