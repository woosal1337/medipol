#include <iostream>
#include <cstdio>

using namespace std;

int main() {
    int input1;
    int input2;

    cin >> input1;
    cin >> input2;

    for (input1; input1 < input2 + 1; input1++) {
        if (input1 == 1) {
            cout << "one" << endl;
        }
        if (input1 == 2) {
            cout << "two" << endl;
        }
        if (input1 == 3) {
            cout << "three" << endl;
        }
        if (input1 == 4) {
            cout << "four" << endl;
        }
        if (input1 == 5) {
            cout << "five" << endl;
        }
        if (input1 == 6) {
            cout << "six" << endl;
        }
        if (input1 == 7) {
            cout << "seven" << endl;
        }
        if (input1 == 8) {
            cout << "eight" << endl;
        }
        if (input1 == 9) {
            cout << "nine" << endl;
        }
        if (input1 > 9) {
            if (input1 % 2 == 0) {
                cout << "even" << endl;
            } else {
                cout << "odd" << endl;
            }
        }
    }

    return 0;
}
