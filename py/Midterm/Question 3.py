def hexToBinary(n):
    hex_to_bin_list = {'0':'0000', '1':'0001', '2':'0010', '3':'0011', '4':'0100', '5':'0101', '6':'0110', '7':'0111', '8':'1000', '9':'1001', 'a':'1010', 'b':'1011', 'c':'1100', 'd':'1101', 'e':'1110', 'f':'1111'}

    if isinstance(n, str):
        n = n.lower()
        for i in n.lower():
            if i not in hex_to_bin_list.keys():
                return "Not a hexadecimal value!"
        bin_number = ""
        for i in n:
            bin_number += hex_to_bin_list[f"{i}"]
        return bin_number
    else:
        return "Not a hexadecimal value!"

if __name__ == "__main__":
    print(hexToBinary(input()))