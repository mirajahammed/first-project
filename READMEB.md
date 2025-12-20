Introduction

This project is a desktop-based Bank Management System developed using Java Swing.
It provides a simple and interactive interface for users to manage their bank accounts, perform transactions, and for an admin to oversee all accounts.
The system demonstrates GUI design, event handling, object-oriented programming, and data persistence using serialization in Java.

Objective
The objective of this project is to create a fully functional banking application that allows users to create accounts, log in, deposit and withdraw money,
transfer funds, request loans, view account details, and track transactions. As the developer, I aimed to implement features that simulate real-world banking
operations while keeping the interface simple and user-friendly. The system also includes an admin panel to manage accounts and approve loans.

Features

User Functionality:
Account creation and login
Deposit and withdrawal of funds
Check balance and account details
Fund transfer between accounts
Loan request and tracking
Transaction history
Close account and logout

Admin Functionality:
View all accounts and their details
Unlock locked accounts
Freeze or unfreeze accounts
Approve pending loan requests
Admin login credentials: admin / admin123
Security Features:
Accounts get locked after 3 failed login attempts
Balance checks to prevent overdraft
Transaction logs maintained for last 10 actions

Persistence:
Account data is stored in a file (bank_data.dat) using serialization, so data persists across program restarts

Tools & Technologies
Programming Language: Java
GUI Library: Swing (JFrame, JPanel, JButton, JOptionPane)
Data Storage: File-based serialization
Event Handling: ActionListener
How the Program Works
When the program starts, the LoginFrame appears. Users can log in using their account ID and password or create a new account.
Admins can log in using the credentials admin / admin123.
After login, users are directed to the Dashboard, where they can perform banking operations like deposit, withdraw, fund transfer, check balance,
request loans, view transaction history, and close their account. Transaction logs are automatically updated and stored.
The Admin Dashboard allows viewing all accounts, unlocking accounts, freezing/unfreezing accounts, and approving pending loan requests. 
Admin actions also update the serialized data file, ensuring changes are persistent.

Code Structure
Account: Represents a bank account and stores user details, balance, loan status, and transaction logs.
BankService: Handles account creation, login, saving/loading data, and basic banking operations.
LoginFrame: GUI for user/admin login and navigation to register or dashboard.
RegisterFrame: GUI for creating new accounts.
Dashboard: User dashboard for banking operations and interactions.
AdminDashboard: Admin panel for managing accounts and approving loans.
BankManagementSystem: Main class to launch the application.

Concepts Demonstrated
GUI Development: Using Swing components to build interactive interfaces
Event-Driven Programming: ActionListener for handling user actions
Object-Oriented Programming: Classes, encapsulation, and modular design
Data Persistence: Saving and loading account data with serialization
Basic Security: Password checks, login attempts, and account locking.

Summary

This Bank Management System is a fully functional desktop application that simulates real-world banking operations.
I developed it to practice Java Swing, event handling, and object-oriented design. 
It provides both user and admin functionality, maintains transaction logs, and ensures data persists across sessions. The system is simple, intuitive, 
and demonstrates how desktop applications can manage complex operations in a structured way.
