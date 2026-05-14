import numpy as np

random_np_array = np.random.randint(-10, 10, (4, 4))


# a)
def find_func_1(i, j):
    if random_np_array[i, j] == random_np_array[i - 1, j + 1]:
        return True
    return False


# b)
squared_negative_numbers = (random_np_array[random_np_array < 0]) ** 2
print(squared_negative_numbers)

# c) prints the rows where the order is increasing
sorted_array = random_np_array[np.argsort(random_np_array[:, 1])]
print(sorted_array[(random_np_array == sorted_array).all(1)])  # all(0) to find the rows where the order is broken

# d)
print(np.trace(random_np_array))


# e)
def find_func_2(i, j):
    if (i + j) == random_np_array[i, j]:
        return True
    return False
