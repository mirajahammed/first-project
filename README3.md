About the Project
This is a simple Java program I developed to demonstrate multithreading and thread synchronization.
The idea was to create two threads that run at the same time: one prints numbers from 1 to 10, and the other prints the squares of numbers from 1 to 10.
Both threads share a counter, which helps show how multiple threads can safely work with a shared resource using synchronization.

How It Works
SharedCounter: A class I created to keep track of a counter. The incrementAndGet() method is synchronized so that two threads don’t update it at the same time and cause inconsistencies.
NumberPrinter: A thread that prints numbers 1 through 10. It updates the shared counter every time it prints a number.
SquarePrinter: A thread that prints the square of numbers 1 through 10. It also updates the shared counter with each print.
Both threads sleep for 500 milliseconds between prints, so you can see the interleaving of their outputs.
At the end, the main thread waits for both threads to finish using join(), then prints a message saying both threads are done.

What I Learned While Developing This
How to create threads in Java using both Thread class and Runnable interface.
How to synchronize shared resources to prevent race conditions.
How thread.sleep() works to control execution speed.
How to use join() to make sure threads finish before continuing in the main program.

UML DIAGRAM:
+--------------------+
|   SharedCounter    |
+--------------------+
| - counter: int     |
+--------------------+
| + incrementAndGet(): int |
+--------------------+

          ^
          |
          |
+--------------------+             +--------------------+
|   NumberPrinter    |             |   SquarePrinter    |
+--------------------+             +--------------------+
| - sharedCounter: SharedCounter |  | - sharedCounter: SharedCounter |
+--------------------+             +--------------------+
| + run(): void      |             | + run(): void      |
+--------------------+             +--------------------+
          ^                                 ^
          |                                 |
          |                                 |
+--------------------+                      |
| MultithreadingDemo  |<--------------------+
+--------------------+
| + main(args: String[]): void |
+--------------------+
