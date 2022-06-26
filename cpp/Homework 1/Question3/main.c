//---------------------------------------------------------------------
//
// Name:    Vusal Ismayilov
//
// Course:  Computer Engineering, 2nd Year
//
// Student ID: 64190012
//
// Purpose: Given 2 dimensional array, which is set by the user,
// wanted algorithm where the array is supposed to be sorted out, and
// at the end show off in the output as asked algorithm.
//
//---------------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>

void swap(int *a, int *b) {
    int t = *a;
    *a = *b;
    *b = t;
}

int partition(int arr[], int low, int high) {
    int pivot = arr[high];
    int i = (low - 1);

    for (int j = low; j <= high - 1; j++) {
        if (arr[j] < pivot) {
            i++;
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);
    return (i + 1);
}

// Classic Quick Sort algorithm to sort the final function and assign the values to the two-dimensional array
void quickSort(int arr[], int low, int high) {
    if (low < high) {

        int pi = partition(arr, low, high);

        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}


int main() {
    int r, c, rc, num[30];
    printf("Please, enter the number of rows:");
    scanf("%d", &r);
    printf("Please, enter the number of columns:");
    scanf("%d", &c);
    rc = r * c;

    // All the data is given in the data.txt file
    FILE *myFile;
    int i;

    myFile = fopen("D:\\GitHub\\Medipol-C-CPP\\Homework 1\\Question3\\data.txt", "r");

    if (myFile == NULL) {
        printf("Error Reading File\n");
        exit(0);
    }

    for (i = 0; i < 30; i++) {
        fscanf(myFile, "%d,", &num[i]);
    }

    /*Thinking of an algorithm that after reading the file into an array, which was already done a few line above,
    //go over and sort the whole array in an ascending order, declare two-dimensional array, and by using the array
    slicing, append the values to the declared two-dimensional array.*/


    // Sorting out the read data.txt input
    int x, n;
    n = sizeof(num) / sizeof(num[0]);
    quickSort(num, 0, n - 1);


    // Showing the result in the output/CMD
    for (x = 0; x < 12; x++) {
        printf("%d ", num[x]);
        if (x == 5) {
            printf("\n");
        }
    }

    printf("\n");

    for (x = 29; x > 11; x--) {
        printf("%d ", num[x]);
        if (x == 24 || x == 18 || x == 12) {
            printf("\n");
        }
    }


    // Writing the result in the output.txt file
    FILE *fptr;
    fptr = fopen("D:\\GitHub\\Medipol-C-CPP\\Homework 1\\Question3\\output.txt", "w");

    for (x = 0; x < 12; x++) {
        fprintf(fptr,"%d ", num[x]);
        if (x == 5) {
            fprintf(fptr,"\n");
        }
    }

    fprintf(fptr,"\n");

    for (x = 29; x > 11; x--) {
        fprintf(fptr,"%d ", num[x]);
        if (x == 24 || x == 18 || x == 12) {
            fprintf(fptr,"\n");
        }
    }

    fclose(fptr);

    return 0;
}
