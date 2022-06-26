import numpy as np
import matplotlib.pyplot as plt
import matplotlib.pyplot as plt
from matplotlib import transforms


def y(m, n):
    final_array = []
    for i in m:
        for j in n:
            final_array.append(np.e ** (-2 * (i + j)) * np.sin(2 * i + (np.pi / 4)) + (np.e ** -(i + j)) * np.cos(
                3 * j + (np.pi / 5)) + 2)

    return np.array(final_array)


# a)


# b)
plt.imshow(y(m, n), interpolation='nearest')
plt.show()

# c)


# d)
fig = plt.figure()
ax = fig.add_subplot(111)

tr = transforms.Affine2D().rotate_deg(15)

ax.imshow(y(m, n), transform=tr + ax.transData)
plt.show()
