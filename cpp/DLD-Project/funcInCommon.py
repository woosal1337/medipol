booleans = {"1":"a'b+c'ab'+ca'+c'd'b'","2":"da'b + c'ab' + ca' + c'd'b'","4":"a' + c'b'","5":"c'a'b + c'ab' + d'a'b + c'd'a'","6":"c'd'a' + c'b' + a'b' + cda'","9":"c'a'b + c'ab' + d'a' + ca'b'","10":"c'ab' + c'a'b + ca'b' + d'a'b","13":"c'd'b' + cd'a'","14":"c'b' + a'b + da'","17":"c'da'b + c'ab' + cd'a' + ca'b' + c'd'b'"}

letters = ["a'","a","b'","b","c'","c","d'","d"]
# sorting by alphabetical order
for key in booleans:
	boolean = booleans[key].split("+")
	for i in range(len(boolean)):
		shorted = ""
		for letter in letters:
			if letter in boolean[i] and not letter+"'" in boolean[i]:
				shorted +=letter
		boolean[i] = shorted
	booleans[key] = boolean

print("All operations:\n",booleans)

op_dict = {}
operations = []

for key in booleans:
	boolean = booleans[key]
	for operation in boolean:
		operations.append(operation)
		if not  operation in op_dict:
			op_dict[operation] = key
		else:
			new_key = op_dict[operation]
			new_key += f"-{key}"
			op_dict[operation] = new_key


print("\nCommon operations:\n",op_dict)


sum_1 = 0
for key in booleans:
	sum_1 +=len(booleans[key])
sum_2 = 0
for key in op_dict:
	sum_2 += 1
print(f"\nNumber of opeartions are reduced from {sum_1} to {sum_2}")
