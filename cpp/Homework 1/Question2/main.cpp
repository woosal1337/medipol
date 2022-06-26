//---------------------------------------------------------------------
//
// Name:    Vusal Ismayilov
//
// Course:  Computer Engineering, 2nd Year
//
// Student ID: 64190012
//
// Purpose: Given an unknown / random number of different arrays, each
// holding also random values ranging from 1 to 100 and randomly generated
// all of the biggest values of each array is being also saved and sorted
// and shown at the end
//
//---------------------------------------------------------------------

#include <iostream>
#include <time.h>
#include <algorithm>

using namespace std;

// A function to find the greatest value of the randomly generated arrays.
int theBiggest(int arr[]) {
    int i, n;
    n = 10;

    // Loop to store largest number to arr[0]
    for (i = 1; i < n; ++i) {
        if (arr[0] < arr[i])
            arr[0] = arr[i];
    }
    return arr[0];
}


int main() {

    int i, n, j, biggestArrValue, randomIteration, a[10], finalArr[100];
    time_t t;

    // Initializing a custom seed depending on the current time.
    srand((unsigned) time(&t));
    randomIteration = rand() % 100;

    for (j = 0; j < randomIteration; j++) {
        // Assigning the new arrays, and random values to them
        for (i = 0; i < 10; i++) {
            a[i] = rand() % 100;
        }

        // Finding the greatest value of the randomly generated array
        // and assigning it to the biggestArrValue
        biggestArrValue = theBiggest(a);

        // To keep the progress, all the greatest values of the randomly generated
        // lists are being appended to the array named finalArr
        finalArr[j] = biggestArrValue;
    }


    // Sorting out the final array
    sort(begin(finalArr), end(finalArr), greater<>());


    // Printing out the final/sorted result below
    for (int x = 0; x < randomIteration; x++) {
        cout << finalArr[x] << "\n";
    }

    return (0);
}