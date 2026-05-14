# 4.A

# a)
#list1 = [0, 2, -1.0, 4/2]
#list2 = [list1[x] for x in list1]

#list1 = [0, 2, -1.0, 4/2]
#list2 = [list1[x] for x in list1]

# This will be an issue because the values in list1 are being used as indices, which is wrong as there are float values
# in the list, which are tried to be used as the indices. Index values must be integers. It can be solved in the following
# way:
list1 = [0, 2, -1.0, 4/2]
list2 = [list1[x] for x in range(len(list1))]
# While taking the length of the list1, iterating through its indices this way is done.

# b)
#Student_grades = { {"Ali"} : 90, {"veli"} : 60}

# In this example, dictionaries are tried to be assigned within the dictionaries, however, not the keys but the dictionaries
# themselves instantly, which is wrong and might let Python understand it as a set. In order to fix this issue, the following
# code can be used respectfully:

Student_grades = { "Ali" : 90, "veli" : 60}

# c)
#Grade  = input("please enter your exam score") ; norm_grade =int(Grade/100*10)

# In this particular example, Python does always get the inpu argument in a string format, which means it must be converted
# to any of the float or integer values before doing mathematical operations on them. In order to fix it, the int() conversion
# function should be used before the mathematical operation is done as following:

Grade  = input("please enter your exam score") ; norm_grade =int(int(Grade)/100*10)

# If grades are not integers, but they also include some additional decimal values, then instead of int(), float() function
# would be preferred for a much more precise output.

# d)
#Pstr = "Midterm"; Pstr.append((" is ", "today"))
# String are not mutable, which means you can not append or remove anything from the string values. If the value was a list,
# for instance, which is considered to be a mutable value, then this operation could be done, however, if string would like
# to be modified, then there are a few to do it, one of the most used and most efficient ways is as follows, which is also called
# as string slicing.

Pstr = "Midterm"
Pstr = Pstr + " is" + " today"

# 4.B
# [2, 7, 8, 5, 10, 6, 3]
# [2, 3, 8, 5, 10, 6, 7]
# [2, 3, 5, 8, 10, 6, 7]
# [2, 3, 5, 6, 10, 8, 7]

# are the lists after each major passes.