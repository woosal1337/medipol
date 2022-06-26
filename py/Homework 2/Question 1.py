# The description of the problem and proposed solution:
### Problem:

"""
Write a function (binary_to_decimal()) that converts a binary number into a decimal number.  Write a test program that asks
the user to enter a binary number and displays the corresponding decimal value. Check whether the user enters only
0s and 1s, and warn the user to enter a correct binary number if he/she makes a mistake.
Example: input:
                "1101"  output: 13
        input:  "210"   "Not a binary number!"
"""

### Solution:
"""
Python has already a built-in input to any conversion algorithm, which is int(n, 2). However, the function below named 
as binary_to_decimal() is firstly checking whether the user input is a true binary or not while iterating through each of
the input values one by one and confirming that they are either 0s or 1s. Followed by, the whole string is reversed by 
using [::-1] the reason being that the regular calculations and conversion from binary to decimal is also done in a
reversed order as the calculations start from the right side and goes to the left side. While using the enumerate()
function all of the string input values are being tagged from 0 to the (length - 1) of the string. If 1 is the iterated
value, then the enumeration index is being used to add to the value 0 declared as `decimal`. And finally, that value is
returned when the string is being completely done throughout the iteration. The built-in method was also put just below 
to check the output values going to be whether the same or not.
"""


# Code:
def binary_to_decimal(n):
    if len(n) > 0:

        # Checking if the input binary number consists only of 1s and 0s
        for i in n:
            if i != "0" and i != "1":
                return "Not a binary number!"

        decimal = 0

        for i, j in enumerate(n[::-1]):
            if j == "1":
                decimal += 2 ** i

        return decimal

    else:
        return "Please enter correct binary number!"


if __name__ == "__main__":
    n = input("Please enter a binary number: ")
    print(binary_to_decimal(n))
    print(int(n, 2))

# Output
r"""
/usr/bin/python3.8 "/media/woosal/1337/GitHub/medipol/py/Homework 2/Question 1.py"
Please enter a binary number: 0101010101010010101
174741
174741

Process finished with exit code 0
"""

"""
author @woosal1337
https://github.com/woosal1337/medipol/blob/main/py
"""
