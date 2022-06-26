# The description of the problem and proposed solution:
### Problem:

"""
A permutation of a list is a list that has all the same elements but possibly in a different order. Write a Python
function that takes two lists as input arguments and  checks  whether  the  two  lists  are  permutations  of  each
other. If they are permutations, the function returns True; otherwise, the function returns the total number of
different elements in the lists (repetitions are treated as separate elements).

Ex:    [10, 9, 11, 1] and [9,1,11,10] are permutations
       [10, 9, 1, 10] and [8,1,11,10] are NOT permutations; no. of differences = 4
"""

### Solution:
"""
As the previous solutions, in the beginning, L1 and L2 named two input arguments are taken to be passed to the function.
After which, the input values are checked whether they are lists or not by using the isinstance() method, followed by
the length of the input length. If the length of the lists are different, then there is also no point to check the
permutation function, whereas a "List length are different" included sentence being returned as a result. If all of the
listed requirements are matched, then by using the built-in method of the collections, Counter the values in the lists
are sorted in a way to be compared by the values inside it. Counter method is as same as counting these values within
the dictionaries, however, as it is a built-in function, it speeds up the process to be much more faster and storage 
efficient at the same time. The Counter function also does create the Counter type of values, which are comparable as
in dictionaries to each other. In case of the according values of keys do not match to that of the other dictionary, 
Counter() in this case, then it returns False, else True. If this condition return True, then that means these lists
are permutations indeed, so "L1 and L2 are permutations" is returned. However, if this condition fails, then another
loop is used right below the else condition, where by zipping two lists together both of the lists are iterated at the
same time with O(n) notation and compared to each other in each iteration. If the values are not equal to each other
then that means those values are different, hence, the above declared num_of_diff value is being increased by one. At 
the end of the iteration that declared value is just being returned. 
"""

# Code:
from collections import Counter


def permutation(L1, L2):
    try:

        if isinstance(L1, list) and isinstance(L2, list):

            if len(L1) == len(L2):

                if Counter(L1) == Counter(L2):

                    return f"{L1} and {L2} are permutations."

                else:
                    num_of_diff = 0

                    for i, j in zip(L1, L2):
                        if i != j:
                            num_of_diff += 1

                    return f"{L1} and {L2} are NOT permutations. Number of differences = {num_of_diff}"

            else:

                return "List length are different."

        else:
            return "Use lists as input."

    except Exception as e:
        print(f"{e} has occurred!")


if __name__ == "__main__":
    print(permutation([10, 9, 1, 10], [8, 1, 11, 10]))

# Output
"""
/usr/bin/python3.8 "/media/woosal/1337/GitHub/medipol/py/Homework 2/Question 2.py"
[10, 9, 1, 10] and [8, 1, 11, 10] are NOT permutations. Number of differences = 3

Process finished with exit code 0
"""

"""
author @woosal1337
https://github.com/woosal1337/medipol/blob/main/py
"""
