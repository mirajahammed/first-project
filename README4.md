Introduction

This project is a simple yet effective Java Swing GUI application designed to demonstrate how graphical
user interfaces, event handling, and custom drawing work in Java.
The application allows users to draw circles on a panel and clear them using buttons.

Objective
The main objectives of this project are:
To create a graphical window using Java Swing
To handle user interactions using ActionListener
To demonstrate custom drawing using the paintComponent() method
To show how a GUI updates dynamically based on user actions

Features
A main application window (JFrame)
Two interactive buttons:
Draw Circle → Draws a filled circle at a random position inside the drawing panel
Clear → Removes all drawn circles and restores a blank panel
A dedicated drawing panel with a white background
Real-time updates of the panel after each user interaction

Tools & Technologies Used
Programming Language: Java
GUI Framework: Java Swing
Event Handling: ActionListener
Graphics: Custom drawing using Graphics API

How the Program Works
When the program starts, a window appears with two buttons at the bottom and a drawing panel in the center.
Clicking the Draw Circle button generates a filled circle at a random location within the panel.
Each circle is stored internally so multiple circles can be displayed at the same time.
Clicking the Clear button removes all stored circles and refreshes the panel to a blank state.
The panel is updated using repaint() to ensure smooth and accurate rendering.

Concepts Demonstrated
Java Swing GUI development
JFrame and JPanel usage
Event handling with ActionListener
Overriding paintComponent() for custom drawing
Dynamic UI updates using repaint()

Summary

This project serves as a beginner-friendly example of Java Swing programming. It combines GUI creation, event handling, and 
custom graphics in a clean and understandable way. The application is ideal for students who are learning how interactive desktop applications work in Java.

UML DIAGRAM:
+----------------------------------------+
|           CircleDrawerApp               |
+----------------------------------------+
|                                        |
+----------------------------------------+
| + main(String[] args) : void            |
+----------------------------------------+
                |
                | creates
                v
+----------------------------------------+
|         CircleDrawerFrame               |
|        (extends JFrame)                 |
+----------------------------------------+
| - drawPanel : DrawPanel                 |
| - drawButton : JButton                  |
| - clearButton : JButton                 |
| - random : Random                       |
+----------------------------------------+
| + CircleDrawerFrame()                   |
+----------------------------------------+
                |
                | contains
                v
+----------------------------------------+
|              DrawPanel                  |
|        (extends JPanel)                 |
+----------------------------------------+
| - circles : ArrayList<Circle>           |
+----------------------------------------+
| + DrawPanel()                           |
| + addCircle(x:int, y:int, r:int) : void |
| + clearCircles() : void                 |
| # paintComponent(g:Graphics) : void     |
+----------------------------------------+
                |
                | uses
                v
+----------------------------------------+
|                Circle                  |
+----------------------------------------+
| - x : int                               |
| - y : int                               |
| - radius : int                          |
+----------------------------------------+
| + Circle(x:int, y:int, r:int)           |
+----------------------------------------+
