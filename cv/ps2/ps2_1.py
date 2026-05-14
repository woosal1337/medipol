import cv2
import math
from math import hypot, pi, cos, sin
from PIL import Image


class HoughTransform:
    def __init__(self):
        img = cv2.imread('input/lena.png', 0)
        self.edges = cv2.Canny(img, 100, 200)
        cv2.imwrite('output/image_edges.png', self.edges)

        self.im = Image.open("input/lena.png").convert("L")
        self.houghed_img, self.r_axis, self.d_rho, self.d_theta = self.hough_lines_acc(self.im)
        self.houghed_img.save("output/image_houghspace.bmp")

        self.peaks, self.rhos, self.thetas = self.hough_peaks(self.houghed_img, self.r_axis, self.d_rho, self.d_theta)

        cv2.imshow('Input image', self.edges)

        self.hough_lines_draw(self.edges)

        cv2.waitKey(0)
        cv2.destroyAllWindows()

    def hough_lines_acc(self, img):
        '''

        :param img:
        :return:
        '''

        img = self.im.load()
        w, h = self.im.size

        self.theta_axis = w
        self.r_axis = h
        self.r_axis = int(self.r_axis / 2) * 2

        hough_lines = Image.new("L", (self.theta_axis, self.r_axis), 0)
        pixel_hough_lines = hough_lines.load()

        max_radius = hypot(w, h)
        d_theta = pi / self.theta_axis
        d_rho = max_radius / (self.r_axis / 2)

        # Accumulator
        for x in range(0, w):
            for y in range(0, h):

                threshold = 0
                col = img[x, y]
                if col <= threshold:

                    for vx in range(0, self.theta_axis):
                        theta = d_theta * vx
                        rho = x * cos(theta) + y * sin(
                            theta)
                        vy = self.r_axis / 2 + int(rho / d_rho + 0.5)
                        pixel_hough_lines[vx, vy] += 1

        return hough_lines, self.r_axis, d_rho, d_theta

    def hough_peaks(self, hough_lines, r_axis, d_rho, d_theta):
        '''

        :param hough_lines:
        :param r_axis:
        :param d_rho:
        :param d_theta:
        :return:
        '''

        w, h = hough_lines.size
        pixel_hough_lines = hough_lines.load()
        max_number = 9
        ignore_radius = 10
        self.peaks = [0] * max_number
        self.rhos = [0] * max_number
        self.thetas = [0] * max_number

        for u in range(0, max_number):

            print('u:', u)
            value = 0
            xposition = 0
            yposition = 0

            for x in range(0, w):
                for y in range(0, h):

                    if pixel_hough_lines[x, y] > value:
                        value = pixel_hough_lines[x, y]
                        xposition = x
                        yposition = y

            self.peaks[u] = value
            self.rhos[u] = (yposition - r_axis / 2) * d_rho
            self.thetas[u] = xposition * d_theta

            pixel_hough_lines[xposition, yposition] = 0

            radius = ignore_radius

            for vx2 in range(-radius, radius):
                for vy2 in range(-radius, radius):
                    x2 = xposition + vx2
                    y2 = yposition + vy2

                    if not (x2 < 0 or x2 >= w):
                        if not (y2 < 0 or y2 >= h):
                            pixel_hough_lines[x2, y2] = 0

        print('max', self.peaks)
        print('rho', self.rhos)
        print('theta', self.thetas)

        return self.peaks, self.rhos, self.thetas

    def hough_lines_draw(self, frame):
        '''

        :param frame:
        :return:
        '''

        for t in range(0, len(self.peaks)):
            a = math.cos(self.thetas[t])
            b = math.sin(self.thetas[t])
            x = a * self.rhos[t]
            y = b * self.rhos[t]
            pt1 = (int(x + 1000 * (-b)), int(y + 1000 * a))
            pt2 = (int(x - 1000 * (-b)), int(y - 1000 * a))
            cv2.line(frame, pt1, pt2, (125, 255, 125), 3, cv2.LINE_AA)
            cv2.imshow('lines', frame)
            cv2.imwrite('output/final.png', frame)


demo = HoughTransform()
