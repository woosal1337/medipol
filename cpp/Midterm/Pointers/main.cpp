#include <iostream>

using namespace std;

int main() {
    int one = 1;
    int two = 2;
    int *temp, tempo;

    tempo = one;
    cout << &tempo << " " << &one << " " << &two << " " << &temp;

    cout << "\n\n\n\n";

    temp = &one;
    cout << *temp << " " << one << " " << two;




    return 0;
}
