# The description of the problem and proposed solution:
### Problem:

"""
Write Python code that uses nested loops to display the following pattern:

 	 	 	 	 	 	 	 	1
 	 	 	 	 	 	 	1	2	1
 	 	 	 	 	 	1	2	4	2	1
 	 	 	 	 	1	2	4	8	4	2	1
 	 	 	 	1	2	4	8	16	8	4	2	1
 	 	 	1	2	4	8	16	32	16	8	4	2	1
 	 	1	2	4	8	16	32	64	32	16	8	4	2	1
 	1	2	4	8	16	32	64	128	64	32	16	8	4	2	1
"""

### Solution:
"""
While having multiple ways to solve this challenge, simple nested loops were used to solve it in this case. While the
pattern is all about being the same algorithm for all of the rows. So, a simple according number of spaces were put in
each line of iteration. The whole pyramid was cut into 2 pieces: the left hand-side, and the right hand-side. However,
the middle value was included on the left hand-side. In order to complete the right side of they pyramid in each row of
the iteration, the same loop, which was used to fill the left side was used, however, iterated in a reversed order as 
the current algorithm is asking all about it.
"""

# Code:
for i in range(9):

    for j in range(9 - i):  # by considering the iteration number, this loop puts according number of space in each line
        print(" ", end="\t")

    for x in range(1, i):  # directly range(i) could be used, however, if the i would be equal to 1, then it would write
        # another 2 ^ 0 = 1 right next to the first line, so it was avoided by adding 1 for the starting value. As the
        # range(1,1) would not really run anything, only one of 1 would be left for the first line.
        print(2 ** (x - 1), end="\t")

    for z in range(i, 0, -1):  # directly range(i) could be used, however, if the i would be equal to 1, then it would
        # write another 2 ^ 0 = 1 right next to the first line, so it was avoided by adding 1 for the starting value. As
        # the range(1,1) would not really run anything, only one of 1 would be left for the first line.
        print(2 ** (z - 1), end="\t")

    print()  # in each iteration this print() statement by default leaves

# Output
"""
/home/woosal/anaconda3/bin/python3 "/media/woosal/1337/GitHub/medipol/py/Homework 1/Question 2.py"
 	 	 	 	 	 	 	 	1	
 	 	 	 	 	 	 	1	2	1	
 	 	 	 	 	 	1	2	4	2	1	
 	 	 	 	 	1	2	4	8	4	2	1	
 	 	 	 	1	2	4	8	16	8	4	2	1	
 	 	 	1	2	4	8	16	32	16	8	4	2	1	
 	 	1	2	4	8	16	32	64	32	16	8	4	2	1	
 	1	2	4	8	16	32	64	128	64	32	16	8	4	2	1	

Process finished with exit code 0
"""

"""
author @woosal1337
https://github.com/woosal1337/medipol/blob/main/py/Homework%201/Question%202.py
"""
