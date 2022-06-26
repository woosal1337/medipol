# The description of the problem and proposed solution:
### Problem:

"""
Write a recursive  Python  function  entitled  “combinations”  that  accepts  integer value n as input, and prints all
combinations of numbers from 1 to n having sum equal to n.

Example :  combinations(4) -> output: [4], [1,3], [2,2], [1,1,2], [1,1,1,1]
"""

### Solution:
"""
As the recursive functions require it, there supposed to be a line, which is the first generally, to end the function in
the last iterations and to prevent the function being infinite. So, as the first, a condition to break the recursion 
written. The function itself take 4 different arguments: i,n,output, and index. n is being decreased by the value of j
below, that is why eventually it hits 0, and prints the output[:index]. The only break point of the function is also it,
which is when the loop is entered. In the main function the value n was hard coded to be as 5, however, it can be also 
changed to any required one. The declared input output is the value n multiplied by None within a list creating n times
of None valued list.
"""


# Code:
def combinations(i, n, output, index):
    if n == 0:
        print(output[:index])

    for j in range(i, n + 1):
        output[index] = j

        combinations(j, n - j, output, index + 1)


if __name__ == '__main__':
    n = 5
    output = [None] * n

    combinations(1, n, output, 0)

# Output
"""
/usr/bin/python3.8 "/media/woosal/1337/GitHub/medipol/py/Homework 2/Question 3.py"
[1, 1, 1, 1, 1]
[1, 1, 1, 2]
[1, 1, 3]
[1, 2, 2]
[1, 4]
[2, 3]
[5]

Process finished with exit code 0
"""

"""
author @woosal1337
https://github.com/woosal1337/medipol/blob/main/py
"""
