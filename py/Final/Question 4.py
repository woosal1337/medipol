# 4.1)

from sklearn import datasets, model_selection, metrics, svm
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC

iris = datasets.load_iris()

X = iris.data
y = iris.target
clf = svm.LinearSVC()
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3)

kf = model_selection.KFold(n_splits=5, shuffle=True)

for train_index, test_index in kf.split(X):
    svm_cl = SVC(C=3, gamma='scale', probability=True)
    svm_cl.fit(X_train, y_train)
    pred = svm_cl.predict(X_test)
    acc = metrics.accuracy_score(y_test, pred)
    print(acc)


# 4.2
# a) Column of the first should be equal to the row of the second matrix. So, it was changed accordingly.
import numpy as np
A = np.array([[1,2,3,4],[2,3,4,0]])
y = np.dot(A, A.reshape(4, 2))
print(y)

# b) The shapes of the numpy arrays are different here as well. Their shapes should be equal to each other in order to perform an
# operation on them. 4 was added to [6] and a number was added to the first array to make them
# the same in size. The other would be actually removing the values from the y array. Either adding values to array x,
# or removing the ones from the y array.
import numpy as np
x = np.array([[2, 3],[4, 6]])
y = np.array([[1,2],[3,4]])
print (x+y)

# c) The import is wrong here. `as plt` was added to the import statement as alias so it can be used directly in the code.
import matplotlib.pyplot as plt
import imageio
im = imageio.imread('lena.ppm')
plt.imshow(im)