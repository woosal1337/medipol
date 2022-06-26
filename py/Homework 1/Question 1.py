# The description of the problem and proposed solution:
### Problem:

"""
Write a Python function that finds and prints all the prime numbers, pn, that satisfy the following equation:
pn = 2^k + 3^l
for some non-negative integers k,l, such that  k≤16, l≤16.
"""

### Solution:
"""
As it has been given in the equation in the description, there is a specific range of values, which are required to be
checked whether they are prime or not. That is why, I have divided whole code into 2 parts. First part to get all of the
possible values within the given range, which suits also the provided formula. function pn() was created, which checks
all of the possible variations where the range for k start from 0 to 16 (included), and literally the same for l. That
makes a total of 17 * 17 variation, making up 289 cases. In order to avoid the case that some of them might be the same,
set() was used, which is a built-in Python function that saves one of each value, which at the end makes the list of 
values all completely unique. After accomplishing the challenge of fetching all of the required values in the set, a 
prime number checking function was written. By using a simple mathematical formula, in the first condition, it does also
check the value to be greater than 1. If the value is being greater than 1, then the number being divided by 2, and 
rounding that value to the ground. Starting from 2, to that range of number the general value is being divided to in
order to check whether any result can be an integer or not. If any value within those divisions can be an integer, then
that value is not prime, else it is.
"""


# Code:
def pn(k=16, l=16):
    unique_values = set()

    for i in range(k + 1):  # as the value in the range() function is excluded, "1" was added to it to include it
        for j in range(l + 1):  # as the value in the range() function is excluded, "1" was added to it to include it
            unique_values.add(2 ** i + 3 ** j)

    for i in sorted(unique_values):
        if is_prime(i):
            print(i)


def is_prime(n):
    if n > 1:  # checking the input number to be positive and also greater than 1 to avoid the 1 being also checked as
        # as 1 is not considered to be a prime number at all
        for i in range(2, int(n / 2) + 1):

            if n % i == 0:
                return False

        return True  # if no value above was fully divisible to the original value, then return prime number True

    else:
        return False


pn()

# Output
"""
/usr/bin/python3.8 "/media/woosal/1337/GitHub/medipol/py/Homework 1/Question 1.py"
2
3
5
7
11
13
17
19
29
31
41
43
59
67
73
83
89
97
113
131
137
251
257
283
307
337
499
521
593
733
761
857
1033
1051
1753
2129
2203
2251
2699
2777
4099
4177
4339
6563
6569
6577
6689
8219
8273
8609
10657
14753
16411
19687
19699
20707
32771
36067
59051
59053
59113
59561
65537
65539
65563
65617
67723
177211
181243
531457
531569
539633
596977
1594331
1594339
1594387
1594451
4782971
4782977
4783993
14348909
14348923
14357099
14365291
14414443
43048769

Process finished with exit code 0
"""

"""
author @woosal1337
https://github.com/woosal1337/medipol/blob/main/py/Homework%201/Question%201.py
"""
