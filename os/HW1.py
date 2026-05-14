from datetime import datetime as dt
import random
from threading import Thread


class CustomThread:

    def __init__(self, thread_name):
        """

        :param thread_name:
        """
        self.thread_name = thread_name
        self.array = []
        self.start_time, self.end_time = 0, 0
        self.is_done = False


class FirstQuestion(CustomThread):

    def __init__(self, thread_name):
        """

        :param thread_name:
        """
        super().__init__(thread_name)
        self.thread = Thread(target=self.run)
        self.thread.start()

    def run(self):
        """

        :return:
        """
        self.start_time = dt.now()
        array = []
        size = random.randint(10000, 20000)
        for i in range(size):
            array.append(random.randint(1, 40000))
        self.array = sorted(array)
        self.end_time = dt.now()
        self.is_done = True


class SecondThirdFourthQuestions(CustomThread):

    def __init__(self, thread1: FirstQuestion, thread2: FirstQuestion, thread_name):
        """

        :param thread1:
        :param thread2:
        :param thread_name:
        """
        super().__init__(thread_name)
        self.thread1 = thread1
        self.thread2 = thread2
        self.thread = Thread(target=self.run)
        self.thread.start()

    def run(self):
        """

        :return:
        """
        self.start_time = dt.now()
        array1 = self.thread1.array
        array2 = self.thread2.array
        self.array = sorted(array1 + array2)
        self.end_time = dt.now()
        self.is_done = True


class AllHomework:

    def __init__(self):

        self.start_time = dt.now()
        self.total_time = {}
        self.Q1THREADS = []
        self.Q2THREADS = []
        self.Q3THREADS = []
        self.Q4THREADS = []
        self.serially_sorted_array = []
        self.sorted_array_len = 0

        if self.Q1Process(): pass
        if self.Q2Process(): pass
        if self.Q3Process(): pass
        if self.Q4Process(): pass

        self.end_time = dt.now()

        if self.Q5Process(): pass
        if self.Q6Process(): pass
        if self.Q7Process(): pass

    def Q7Process(self):
        """

        :return:
        """
        start_time = dt.now()
        for i in range(self.sorted_array_len):
            self.serially_sorted_array.append(random.randint(0, 40000))

        self.serially_sorted_array = sorted(self.serially_sorted_array)
        end_time = dt.now()
        file = open("SortedSerially.txt", "a+")
        file.write(str(self.serially_sorted_array))
        print(
            f"\nManually Sorted Array\nStart Time: {start_time}\nEnd Time: {end_time}\nDuration: {end_time - start_time}")

    def Q6Process(self):
        """

        :return:
        """
        self.sorted_array_len = len(self.Q4THREADS[0].array)
        print("\nSize of the sorted array", self.sorted_array_len)
        print("Total Time: ", self.end_time - self.start_time)
        return True

    def Q5Process(self):
        """

        :return:
        """
        file = open("SortedUsingMultipleThreads.txt", "a+")
        for thread in self.Q4THREADS:
            file.write(str(thread.array))
        return True

    def check_is_done(self, thread):
        """

        :param thread:
        :return:
        """
        return thread.is_done

    def Q1Process(self):
        """

        :return:
        """
        for i in range(8):
            self.Q1THREADS.append(FirstQuestion(f"Thread T{i + 1}"))

        i = 0
        while i < len(self.Q1THREADS):
            thread = self.Q1THREADS[i]
            if not self.check_is_done(thread):
                i -= 1
            i += 1
        for thread in self.Q1THREADS:
            print(
                f"Thread: {thread.thread_name}\nStart: {thread.start_time}\nEnd: {thread.end_time}\nDuration: {thread.end_time - thread.start_time}")
            print()
            self.total_time[thread.thread_name] = thread.end_time - thread.start_time
        return True

    def Q2Process(self):
        """

        :return:
        """
        for i in range(0, len(self.Q1THREADS), 2):
            self.Q2THREADS.append(
                SecondThirdFourthQuestions(self.Q1THREADS[i], self.Q1THREADS[i + 1], f"Thread M{i + 1}{i + 2}"))

        i = 0
        while i < len(self.Q2THREADS):
            thread = self.Q2THREADS[i]
            if not self.check_is_done(thread):
                i -= 1
            i += 1

        for thread in self.Q2THREADS:
            print(
                f"Thread: {thread.thread_name}\nStart: {thread.start_time}\nEnd: {thread.end_time}\nDuration: {thread.end_time - thread.start_time}")
            print()
            self.total_time[thread.thread_name] = thread.end_time - thread.start_time

        return True

    def Q3Process(self):
        """

        :return:
        """
        m_index = 1
        for i in range(0, len(self.Q2THREADS), 2):
            self.Q3THREADS.append(
                SecondThirdFourthQuestions(self.Q2THREADS[i], self.Q2THREADS[i + 1], f"Thread M{m_index}"))
            m_index += 1

        i = 0
        while i < len(self.Q3THREADS):
            thread = self.Q3THREADS[i]
            if not self.check_is_done(thread):
                i -= 1
            i += 1

        for thread in self.Q3THREADS:
            print(
                f"Thread: {thread.thread_name}\nStart: {thread.start_time}\nEnd: {thread.end_time}\nDuration: {thread.end_time - thread.start_time}")
            print()
            self.total_time[thread.thread_name] = thread.end_time - thread.start_time
        return True

    def Q4Process(self):
        """

        :return:
        """
        self.Q4THREADS.append(SecondThirdFourthQuestions(self.Q3THREADS[0], self.Q3THREADS[1], "Thread M0"))

        i = 0
        while i < len(self.Q4THREADS):
            thread = self.Q3THREADS[i]
            if not self.check_is_done(thread):
                i -= 1
            i += 1

        for thread in self.Q4THREADS:
            print(
                f"Thread: {thread.thread_name}\nStart: {thread.start_time}\nEnd: {thread.end_time}\nDuration: {thread.end_time - thread.start_time}")
            print()
            self.total_time[thread.thread_name] = thread.end_time - thread.start_time
        return True


if __name__ == "__main__":
    FinalHomework = AllHomework()

# Sample Output

# Thread: Thread T1
# Start: 2021-12-25 19:18:12.970858
# End: 2021-12-25 19:18:13.008321
# Duration: 0:00:00.037463
#
# Thread: Thread T2
# Start: 2021-12-25 19:18:12.981163
# End: 2021-12-25 19:18:13.020656
# Duration: 0:00:00.039493
#
# Thread: Thread T3
# Start: 2021-12-25 19:18:12.986412
# End: 2021-12-25 19:18:13.004481
# Duration: 0:00:00.018069
#
# Thread: Thread T4
# Start: 2021-12-25 19:18:12.991631
# End: 2021-12-25 19:18:13.027447
# Duration: 0:00:00.035816
#
# Thread: Thread T5
# Start: 2021-12-25 19:18:12.996879
# End: 2021-12-25 19:18:13.031676
# Duration: 0:00:00.034797
#
# Thread: Thread T6
# Start: 2021-12-25 19:18:13.031755
# End: 2021-12-25 19:18:13.046551
# Duration: 0:00:00.014796
#
# Thread: Thread T7
# Start: 2021-12-25 19:18:13.037047
# End: 2021-12-25 19:18:13.061990
# Duration: 0:00:00.024943
#
# Thread: Thread T8
# Start: 2021-12-25 19:18:13.051830
# End: 2021-12-25 19:18:13.071361
# Duration: 0:00:00.019531
#
# Thread: Thread M12
# Start: 2021-12-25 19:18:13.071649
# End: 2021-12-25 19:18:13.072468
# Duration: 0:00:00.000819
#
# Thread: Thread M34
# Start: 2021-12-25 19:18:13.072586
# End: 2021-12-25 19:18:13.073505
# Duration: 0:00:00.000919
#
# Thread: Thread M56
# Start: 2021-12-25 19:18:13.073662
# End: 2021-12-25 19:18:13.074547
# Duration: 0:00:00.000885
#
# Thread: Thread M78
# Start: 2021-12-25 19:18:13.076451
# End: 2021-12-25 19:18:13.077293
# Duration: 0:00:00.000842
#
# Thread: Thread M1
# Start: 2021-12-25 19:18:13.077496
# End: 2021-12-25 19:18:13.079165
# Duration: 0:00:00.001669
#
# Thread: Thread M2
# Start: 2021-12-25 19:18:13.079314
# End: 2021-12-25 19:18:13.081181
# Duration: 0:00:00.001867
#
# Thread: Thread M0
# Start: 2021-12-25 19:18:13.081364
# End: 2021-12-25 19:18:13.085636
# Duration: 0:00:00.004272
#
#
# Size of the sorted array 120703
# Total Time:  0:00:00.114967
#
# Manually Sorted Array
# Start Time: 2021-12-25 19:18:13.097464
# End Time: 2021-12-25 19:18:13.191948
# Duration: 0:00:00.094484
#
# Process finished with exit code 0
