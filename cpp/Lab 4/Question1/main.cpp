#include <iostream>
using namespace std;

int main() {
    //int i = 10;

    for (int i = 10; i > 5; i=i-3) {

        if (i == 7) {
            continue;
        } else {
            for (int j = 0; j < 5; j=j+2) {
                if (i + j > 7) {
                    cout << "##";
                } else {
                    cout << "@@";
                }
                cout << "\n";
            }
        }

    }



    return 0;
}
