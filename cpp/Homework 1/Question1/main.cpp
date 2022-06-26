//---------------------------------------------------------------------
//
// Name:    Vusal Ismayilov
//
// Course:  Computer Engineering, 2nd Year
//
// Student ID: 64190012
//
// Purpose: Given code below is going to take a user input in integer
// format, and then based on that year will create the calendar and
// show it as an output.
//
//---------------------------------------------------------------------

#include <iostream>
using namespace std;

int dayDate(int day, int month, int year) {

    static int t[] = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
    year -= month < 3;
    return ((year + year / 4 - year / 100 + year / 400 + t[month - 1] + day) % 7);
}

string getMonth(int monthNumber) {
    // Creating an array of the months for the further loop to call
    string months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    return (months[monthNumber]);
}

int getDays(int monthNumber, int year) {

    // Iterating through each month, and returning the dates based on the loops
    // Starting with the January (first index of the calendar), it goes till December

    if (monthNumber == 0)
        return (31);

    if (monthNumber == 1) {
        if (year % 400 == 0 ||
            (year % 4 == 0 && year % 100 != 0))
            return (29);
        else
            return (28);
    }

    if (monthNumber == 2)
        return (31);

    if (monthNumber == 3)
        return (30);

    if (monthNumber == 4)
        return (31);

    if (monthNumber == 5)
        return (30);

    if (monthNumber == 6)
        return (31);

    if (monthNumber == 7)
        return (31);

    if (monthNumber == 8)
        return (30);

    if (monthNumber == 9)
        return (31);

    if (monthNumber == 10)
        return (30);

    if (monthNumber == 11)
        return (31);
}

// Showing the given year in calendar:
void showCalendar(int year) {
    int days;

    // Index of the day from 0 to 6
    int current = dayDate(1, 1, year);

    for (int i = 0; i < 12; i++) {
        days = getDays(i, year);

        // Showing the Month Name here:

        printf("\n             %s             \n",
               getMonth(i).c_str());

        // Print the columns
        printf("  Sun  Mon  Tue  Wed  Thu  Fri  Sat\n ____________________________________ \n");

        // Print appropriate spaces
        int k;
        for (k = 0; k < current; k++)
            printf("     ");

        for (int j = 1; j <= days; j++) {
            printf("%5d", j);

            if (++k > 6) {
                k = 0;
                printf("\n");
            }
        }

        if (k)
            printf("\n");

        current = k;
    }

    return;
}

int main() {
    int year;
    cout << "Which year's calendar would you like to know? \n";
    cin >> year;
    showCalendar(year);

    return (0);
}