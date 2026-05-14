import cv2
import math
from PIL import Image


class HoughTransform:

    def __init__(self):

        self.cap = cv2.VideoCapture(2)

        while self.cap.isOpened():

            self.ret, self.img = self.cap.read()

            if self.ret:
                # img = cv2.imread('input/lena.png', 0)
                self.edges = cv2.Canny(self.img, 100, 200)
                cv2.imwrite('output/image_edges.png', self.edges)
                cv2.imwrite('output/cam.png', self.img)

                self.im = Image.open("output/cam.png").convert("L")
                self.houghed_img, self.r_axis, self.d_rho, self.d_theta = self.hough_lines_acc(self.im)
                self.houghed_img.save("output/image_random.bmp")

                self.peaks, self.rhos, self.thetas = self.hough_peaks(self.houghed_img, self.r_axis, self.d_rho,
                                                                      self.d_theta)

                cv2.imshow('Input image', self.edges)

            else:
                break

        self.cap.release()
        cv2.destroyAllWindows()

    def hough_lines_acc(self, img):
        '''

        :param img:
        :return:
        '''

        self.img = self.im.load()
        self.w, self.h = self.im.size

        self.theta_axis = self.w
        self.r_axis = self.h
        self.r_axis = int(self.r_axis / 2) * 2

        self.hough_lines = Image.new("L", (self.theta_axis, self.r_axis), 0)
        self.pixel_hough_lines = self.hough_lines.load()

        self.max_radius = math.hypot(self.w, self.h)
        self.d_theta = math.pi / self.theta_axis
        self.d_rho = self.max_radius / (self.r_axis / 2)

        for x in range(0, self.w):
            for y in range(0, self.h):

                self.threshold = 0
                self.col = self.img[x, y]
                if self.col <= self.threshold:

                    for vx in range(0, self.theta_axis):
                        self.theta = self.d_theta * vx
                        self.rho = x * math.cos(self.theta) + y * math.sin(
                            self.theta)
                        vy = self.r_axis / 2 + int(self.rho / self.d_rho + 0.5)
                        self.pixel_hough_lines[vx, vy] += 1

        return self.hough_lines, self.r_axis, self.d_rho, self.d_theta

    def hough_peaks(self, hough_lines, r_axis, d_rho, d_theta):
        '''

        :param hough_lines:
        :param r_axis:
        :param d_rho:
        :param d_theta:
        :return:
        '''

        self.w, self.h = self.hough_lines.size
        self.pixel_hough_lines = self.hough_lines.load()
        self.max_number = 9
        self.ignore_radius = 10
        self.peaks = [0] * self.max_number
        self.rhos = [0] * self.max_number
        self.thetas = [0] * self.max_number

        for u in range(0, self.max_number):

            print('u:', u)
            self.value = 0
            self.xposition = 0
            self.yposition = 0

            for x in range(0, self.w):
                for y in range(0, self.h):

                    if self.pixel_hough_lines[x, y] > self.value:
                        self.value = self.pixel_hough_lines[x, y]
                        self.xposition = x
                        self.yposition = y

            self.peaks[u] = self.value
            self.rhos[u] = (self.yposition - r_axis / 2) * d_rho
            self.thetas[u] = self.xposition * d_theta

            self.pixel_hough_lines[self.xposition, self.yposition] = 0

            radius = self.ignore_radius

            for vx2 in range(-radius, radius):
                for vy2 in range(-radius, radius):
                    x2 = self.xposition + vx2
                    y2 = self.yposition + vy2

                    if not (x2 < 0 or x2 >= self.w):
                        if not (y2 < 0 or y2 >= self.h):
                            self.pixel_hough_lines[x2, y2] = 0

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
            self.a = math.cos(self.thetas[t])
            self.b = math.sin(self.thetas[t])
            self.x = self.a * self.rhos[t]
            self.y = self.b * self.rhos[t]
            pt1 = (int(self.x + 1000 * (-self.b)), int(self.y + 1000 * self.a))
            pt2 = (int(self.x - 1000 * (-self.b)), int(self.y - 1000 * self.a))
            cv2.line(frame, pt1, pt2, (125, 255, 125), 3, cv2.LINE_AA)
            cv2.imshow('lines', frame)
            cv2.imwrite('output/final.png', frame)

            return frame


demo = HoughTransform()
