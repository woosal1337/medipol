# The description of the problem and proposed solution:
### Problem:

"""
Implement  and  test  the bucket sort algorithm  for  different  values  of n  and k, where the input is a random list
of integers of length n, and integer values are in the range [0, k-1]. Compute and plot the execution times vs. (n+k)
and show that the average complexity of bucket sort is O(n+k).
"""

### Solution:
"""
While using 2 different sorting algorithms combined together, firstly Insertion Sort algorithm is being used after which
it is passed to the Bucket sort function. 
"""

# Code:
import random


def insertion_sort(input_bucket):
    for i in range(1, len(input_bucket)):
        current_value = input_bucket[i]
        j = i - 1
        while j >= 0 and current_value < input_bucket[j]:
            input_bucket[j+1] = input_bucket[j]
            j -= 1
        input_bucket[j+1] = current_value


def bucket_sort(input_list):
    max_value = max(input_list)
    size = max_value / len(input_list)

    buckets_list = []
    for x in range(len(input_list)):
        buckets_list.append([])

    for i in range(len(input_list)):
        j = int(input_list[i] / size)
        if j != len(input_list):
            buckets_list[j].append(input_list[i])
        else:
            buckets_list[len(input_list) - 1].append(input_list[i])

    for z in range(len(input_list)):
        insertion_sort(buckets_list[z])

    final_output = []
    for x in range(len(input_list)):
        final_output = final_output + buckets_list[x]
    return final_output


def main():
    k = int(input())
    n = int(input())
    input_list = [random.randint(0, k) for i in range(n)]
    sorted_list = bucket_sort(input_list)
    print(sorted_list)

if __name__ == "__main__":
    main()

# Output
"""
/usr/bin/python3.8 "/media/woosal/1337/GitHub/medipol/py/Homework 3/Question 2.py"
10
10
[0, 2, 4, 4, 5, 5, 7, 7, 8, 10]

Process finished with exit code 0
"""

"""
author @woosal1337
https://github.com/woosal1337/medipol/blob/main/py
"""
