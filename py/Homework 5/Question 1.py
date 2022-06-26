import numpy as np
import matplotlib.pyplot as plt
from sklearn.datasets import load_breast_cancer
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.svm import SVC
from sklearn.metrics import accuracy_score, roc_curve

data = load_breast_cancer()
X = data.data
Y = data.target
X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=0.3)
Y_train = np.reshape(Y_train, (-1, 1))
Y_test = np.reshape(Y_test, (-1, 1))
svm_cl = SVC(C=3, gamma='scale', probability=True)
bayes_cl = GaussianNB(var_smoothing=1e-9)

svm_cl.fit(X_train, Y_train)
Y_pred_svm = svm_cl.predict(X_test)
Y_prob_svm = svm_cl.predict_proba(X_test)[:, 1]

bayes_cl.fit(X_train, Y_train)
Y_pred_bayes = bayes_cl.predict(X_test)
Y_prob_bayes = bayes_cl.predict_proba(X_test)[:, 1]
print(f"SVM Prediction Accuracy: {accuracy_score(Y_test, Y_pred_svm)}")
fpr, tpr, thresholds = roc_curve(Y_test, Y_prob_svm)
plt.plot(fpr, tpr)
print(f"BAYES Prediction Accuracy: {accuracy_score(Y_test, Y_pred_bayes)}")
fpr, tpr, thresholds = roc_curve(Y_test, Y_prob_bayes)
plt.plot(fpr, tpr)
