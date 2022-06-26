# The description of the problem and proposed solution:
### Problem:

"""
Write a program that finds the common substrings between two strings X and Y.Your  program  should  be  case-insensitive
(i.e.,  letter  ‘a’  and  letter  ‘A’  are equivalent), and multiple occurrences of a substring should be counted only
once. For example, the strings "araba" and "BABA" have 5 common substrings: (‘a’, ‘b’, ‘ab’, ‘ba’, ‘aba’). Your program
should print all the common substrings and their total count.
"""

### Solution:
"""
Before all, as the sub-strings are case-insensitive, lower() function is used for each of the strings to have the same
format for both of the inputs before moving further. The logic works in a way that each string is being iterated twice 
with Big O(n^2) notation and all of the possible string combinations are being saved to a set() value, so no the same
values (duplicates) would be added to the set() as this built-in function keeps one of each only values once. After all 
of the possible combinations found for these 2 inputs, a list comprehension is being used to iterate over those returned
set()'s and compare whether 2 the same strings exist in common or not.
"""


# Code:
def find_subsets(input_string):
    sub_strings = set()

    for i in range(len(input_string) + 1):
        for j in range(len(input_string) + 1):
            sub_strings.add(input_string[i:j])

    sub_strings.remove("")
    return sub_strings


def main(str_1, str_2):
    return [i for i in find_subsets(str_1.lower()) if i in find_subsets(str_2.lower())]


if __name__ == "__main__":
    print(main('araba', 'BABA'))

# Output
"""
/usr/bin/python3.8 "/media/woosal/1337/GitHub/medipol/py/Homework 3/Question 1.py"
['ba', 'ab', 'b', 'aba', 'a']

Process finished with exit code 0

"""

"""
author @woosal1337
https://github.com/woosal1337/medipol/blob/main/py
"""
