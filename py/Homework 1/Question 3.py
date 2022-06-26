# The description of the problem and proposed solution:
### Problem:

"""
Write  a  Python  function,  named  “find_fibonacci”,  which  accepts  a  list  as  input. The function will find and
return the elements that satisfy the Fibonacci property:

a(k+2) = a(k) + a(k+1)

Example :  Input list : [2, 8, 4, 6, 1, 7, 8, 4, 7, 9, 4, 13]
           Returned list :  [[6,1,7], [1,7,8], [9,4,13]]
"""

### Solution:
"""
In order to solve this algorithm, there are a few checks required for the given input. Firstly, whether the input value
is a list type or not. If yes, then this check is passed successfully. Then, the length of the list is needed. As 
Fibonacci requires at least 3 elements in a list to be checked, as long as the length of the list is longer than 3, then
this check will also be passed, finally leaving to pass to the main algorithm. Whole list, according to the length of it
going to be divided into 3 value partitions. If the list consists of 3 values, then there is only 1 condition to be
checked. The longer the list becomes, the more the options also become accordingly. In this case, this increase is going
to be in a linear pattern. So, 3, 4, 5 values in each list going to have 1, 2, and 3 distinct conditions to be checked
accordingly. While partitioning the whole given input into all of the possible consecutive values of 3 value-lists, 
each of them are being checked whether they satisfy the fibonacci series logic or not. If yes, then that partition of
list is being appended to the list type of value declared as fibonacci_list, which is being returned at the end of the
whole function. Else, it just skips the iteration. 
"""


# Code:
def find_fibonacci(inp=[2, 8, 4, 6, 1, 7, 8, 4, 7, 9, 4, 13]):
    if isinstance(inp, list):  # checking if the input value is a list type object or not

        if len(inp) >= 3:  #  checking if the length of the list is longer than 3 or not

            fibonacci_list = list()

            for i in range(len(inp) - 2):  # iteration exact number of times to retrieve all of the possible
                # combinations
                curr_list = inp[i:i+3]  # current iteration partition list

                if curr_list[0] + curr_list[1] == curr_list[-1]:  # checking whether that partition of list suits
                    # fibonacci series or not
                    fibonacci_list.append(curr_list)

            return fibonacci_list

        else:
            raise Exception(f"{inp} has a length shorter than 3! Try adding it up to at least 3!")

    else:
        raise Exception(f"{inp} is not a list type!")


print(find_fibonacci())

# Output
"""
/home/woosal/anaconda3/bin/python3 "/media/woosal/1337/GitHub/medipol/py/Homework 1/Question 3.py"
[[6, 1, 7], [1, 7, 8], [9, 4, 13]]

Process finished with exit code 0
"""

"""
author @woosal1337
https://github.com/woosal1337/medipol/blob/main/py/Homework%201/Question%203.py
"""
