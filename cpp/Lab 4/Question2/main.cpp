// Name: Vusal Ismayilov    Student ID: 64190012    Faculty: Computer Engineering

#include <iostream>
using namespace std;

int main() {

    // Unfortunately sizeOf() function does not work in my IDE, so I had to declare and integer,
    // and count every iteration till 250, so if needed to stop it.

    bool isRun = true;

    int userList[250];
    int userIntInput;
    int totalUserInput = 0;
    int theMaxValue;
    int numberOf250 = 0;

    while (isRun) {
        cout << "Enter a value between 100 & 500: ";
        cin >> userIntInput;


        // Ending the while loop if either the userInput is 450, or array is exceeded 250
        if (userIntInput == 450 || totalUserInput == 250) {
            totalUserInput++;
            theMaxValue = userList[0];
            for (int i=0; i<totalUserInput; i++) {
                if (userList[i] > theMaxValue) {
                    theMaxValue = userList[i];
                }
            }


            cout << "The number of correct values: " << totalUserInput << "\n";
            cout << "The maximum value: " << theMaxValue << "\n";
            cout << "The number of times 250 was repeated: " << numberOf250 << "\n";
            isRun = false;
        }


        // Reading and if suits to the case add it to the list
        if (userIntInput < 500 && userIntInput > 100) {
            userList[totalUserInput] = userIntInput;
            if (userIntInput == 250) {
                numberOf250++;
            }
            totalUserInput++;
        } else {
            cout << "###  wrong value  ###" << "\n";
        }

    }

    return 0;
}
