import cv2
import time

# (a)
cap = cv2.VideoCapture(0)

prev_frame_time, new_frame_time = 0, 0
width, height = int(cap.get(3)), int(cap.get(4))

fourcc = cv2.VideoWriter_fourcc(*'MJPG')
out = cv2.VideoWriter('./output/output.avi', fourcc, 10.0, (1280, 480))

recording_start = time.time()
isReleased = True

while True:

    if isReleased:
        if time.time() - recording_start >= 15:
            print("Video finished and saved! However, the stream will last unless you press 'q'!")
            out.release()
            isReleased = False

    ret, frame = cap.read()

    # (b)
    # Fps display
    new_frame_time = time.time()
    fps = 1 / (new_frame_time - prev_frame_time)
    prev_frame_time = new_frame_time
    fps = str(int(fps))

    # Gradient Magnitude
    gray_scale_img = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    x_grad = cv2.Sobel(gray_scale_img, cv2.CV_64F, 1, 0, ksize=3)
    y_grad = cv2.Sobel(gray_scale_img, cv2.CV_64F, 0, 1, ksize=3)

    abs_grad_x = cv2.convertScaleAbs(x_grad)
    abs_grad_y = cv2.convertScaleAbs(y_grad)
    gradient_magnitude = cv2.addWeighted(abs_grad_x, 0.5, abs_grad_y, 0.5, 0)
    gradient_magnitude = cv2.cvtColor(gradient_magnitude, cv2.COLOR_GRAY2RGB)

    # Displaying the fps was put here to avoid the Gradient Magnitude effect applied to it also
    cv2.putText(frame, f'{fps} fps ', (5, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (100, 255, 0), 1, cv2.LINE_AA)

    concatenated_image = cv2.hconcat([frame, gradient_magnitude])

    out.write(concatenated_image)
    cv2.imshow("Concatenated Image", concatenated_image)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()
