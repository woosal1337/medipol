from collections import Counter


def lists_swap(L1, L2):
    number_of_swaps = 0

    for j in L2:
        i = L1.index(j)
        L1.pop(i)

        number_of_swaps += i

    return number_of_swaps


def permutation(L1, L2):
    try:

        if isinstance(L1, list) and isinstance(L2, list):

            if len(L1) == len(L2):

                if Counter(L1) == Counter(L2):

                    return f"Lists are permutations, number of swaps is {lists_swap(L1, L2)}"

                else:
                    return False

            else:
                return "List length are different."

        else:
            return "Use lists as input."

    except Exception as e:
        print(f"{e} has occurred!")


if __name__ == "__main__":
    print(permutation([10, 9, 11, 1, 12, 13, 15, 14], [9, 1, 11, 10, 13, 12, 14, 15]))
