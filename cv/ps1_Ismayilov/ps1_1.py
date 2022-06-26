import cv2
import numpy as np

img = cv2.imread("./input/lena.png")
height, width, channels = img.shape
cv2.imshow("Original Image", img)

# (a)
cropped_from_center_image = img[int(height / 2) - int(height / 4):int(height / 2) + int(height / 4),
                            int(width / 2) - int(width / 4):int(width / 2) + int(width / 4)]

cv2.imwrite("./output/Cropped from Centre Image (a).png", cropped_from_center_image)
cv2.imshow("Cropped from Centre Image", cropped_from_center_image)

# (b)
red_wallpaper = cv2.imread("./input/red_wallpaper.png")
red_wallpaper = cv2.resize(red_wallpaper, (0, 0), fx=0.3, fy=0.3)
red_channel = red_wallpaper[:, :, 2]  # 3 channels (RGB) but OpenCV reads the RGB as BGR, reversed. That is why 2
# becomes R - Red.

cv2.imwrite("./output/Red Channel Image (b).png", red_channel)
cv2.imshow("Red Channel Image", red_channel)

# (c)
grayscale_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

cv2.imwrite("./output/Grayscale Image (c).png", grayscale_img)
cv2.imshow("Grayscale Image", grayscale_img)

# (d)
gray_scale_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

x_grad = cv2.Sobel(gray_scale_img, cv2.CV_64F, 1, 0, ksize=3)
y_grad = cv2.Sobel(gray_scale_img, cv2.CV_64F, 0, 1, ksize=3)

cv2.imshow("X Gradient", x_grad)
cv2.imwrite("./output/X Gradient (d).png", x_grad)

cv2.imshow("Y Gradient", y_grad)
cv2.imwrite("./output/Y Gradient (d).png", y_grad)

abs_grad_x = cv2.convertScaleAbs(x_grad)
abs_grad_y = cv2.convertScaleAbs(y_grad)
gradient_magnitude = cv2.addWeighted(abs_grad_x, 0.5, abs_grad_y, 0.5, 0)

cv2.imshow("Gradient Magnitude", gradient_magnitude)
cv2.imwrite("./output/Gradient Magnitude (d).png", gradient_magnitude)

# (d) Gradient Orientation
gradient_orientation = cv2.phase(x_grad, y_grad, angleInDegrees=True)
magnitude = cv2.magnitude(x_grad, y_grad)

_, mask = cv2.threshold(magnitude, 100, 255, cv2.THRESH_BINARY)

red = np.array([0, 0, 255])
cyan = np.array([255, 255, 0])
green = np.array([0, 255, 0])
purple = np.array([153, 0, 153])

gradient_orientation_map = np.zeros((gradient_orientation.shape[0], gradient_orientation.shape[1], 3), dtype=np.uint8)

gradient_orientation_map[(mask == 255) & (gradient_orientation < 90)] = red
gradient_orientation_map[(mask == 255) & (gradient_orientation > 90) & (gradient_orientation < 180)] = cyan
gradient_orientation_map[(mask == 255) & (gradient_orientation > 180) & (gradient_orientation < 270)] = green
gradient_orientation_map[(mask == 255) & (gradient_orientation > 270)] = purple

cv2.imshow("Gradient Orientation", gradient_orientation_map)
cv2.imwrite("./output/Gradient Orientation (d).png", gradient_orientation_map)

# (e)
kernels = [np.array([
    [0, 0, 1, 0, 0],
    [0, 1, 2, 1, 0],
    [1, 2, -16, 2, 1],
    [0, 1, 2, 1, 0],
    [0, 0, 1, 0, 0]
]),
    np.array([
        [-1, -1, -1],
        [-1, 8, -1],
        [-1, -1, -1]
    ]),
    np.array([
        [-1, -1, -1, -1, -1],
        [-1, 1, 2, 1, -1],
        [-1, 2, 3, 2, -1],
        [-1, 1, 2, 1, -1],
        [-1, -1, -1, -1, -1]
    ])
]

laplacian_of_gaussian = cv2.filter2D(img, -1, kernels[0])
laplacian_of_gaussian2 = cv2.filter2D(img, -1, kernels[1])
laplacian_of_gaussian3 = cv2.filter2D(img, -1, kernels[2])

cv2.imshow("Laplacian of Gaussian", laplacian_of_gaussian)
cv2.imwrite("./output/Laplacian of Gaussian (e).png", laplacian_of_gaussian)

cv2.imshow("Laplacian of Gaussian 2", laplacian_of_gaussian2)
cv2.imwrite("./output/Laplacian of Gaussian 2 (e).png", laplacian_of_gaussian2)

cv2.imshow("Laplacian of Gaussian 3", laplacian_of_gaussian3)
cv2.imwrite("./output/Laplacian of Gaussian 3 (e).png", laplacian_of_gaussian3)

cv2.waitKey(0)
cv2.destroyAllWindows()
