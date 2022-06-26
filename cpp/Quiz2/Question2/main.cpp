#include <iostream>
using namespace std;

void swap(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}

void OddEven(int arr[], int size)
{
    int leftSize = 0, rightSize = size-1;
    while (leftSize < rightSize)
    {
        while (arr[leftSize] % 2 == 0 && leftSize < rightSize)
            leftSize += 1;

        while (arr[rightSize] % 2 == 1 && leftSize < rightSize)
            rightSize -= 1;

        if (leftSize < rightSize)
        {
            swap(&arr[leftSize], &arr[rightSize]);
            leftSize += 1;
            rightSize -= 1;
        }
    }
}

int main()
{
    // Declaring right exactly 10 numbers in the list of the array that holds 10 data inside it.
    int arr[10] = {3, 10, 11, 26, 12, 14, 3, 9, 15, 4};
    int arrSize = sizeof(arr)/sizeof(arr[0]);
    int i = 0;

    OddEven(arr, arrSize);

    reverse(arr, arr + arrSize);

    for (i = 0; i < arrSize; i++)
        cout << arr[i] << " ";

    return 0;
}