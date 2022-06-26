import math

def check_prime(n):
    if n < 2:
        return False
    elif n == 2:
        return True
    else:
        for i in range(2, n):
            if n % i == 0:
                return False
        return True

def check_power_2(x):
    return math.log10(x) / math.log10(2)

def main(inp_list):
    if isinstance(inp_list, list):
        # Find the negative values and remove them
        inp_list = [i for i in inp_list if i >= 0]

        # Extract the prime numbers
        y = []
        for i in inp_list:
            if check_prime(i):
                y.append(i)

        # Indices in the list which are the power of 2
        powers_of_2 = [i for i in inp_list if check_prime(i)]

        # Sum of the absolute values
        sum_of_absolute = 0
        for i in inp_list:
            if i >= 0:
                sum_of_absolute += i

        # Dictionary with the values and their corresponding indices
        D = {}
        for j in inp_list:
            D[j] = [i for i, x in enumerate(inp_list) if x == j]
        return inp_list
    else:
        return "Please use a list input"

if __name__ == "__main__":
    print(main([1, 1, 7, 6, 8, 5, -5, -10, 3]))