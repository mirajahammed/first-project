1.Overview

This is a simple Java program that demonstrates encapsulation and object-oriented programming concepts.
It allows creating and managing multiple bank accounts safely, ensuring data integrity by using private fields and setter methods for validation.

This is an edited version of the original code, with improved balance validation and clear output formatting.

2.Features

Encapsulates account details (accountNumber, accountHolderName, balance) as private fields.

Uses getter and setter methods to access and update fields safely.

Prevents negative balance updates with validation in the setter.

Provides a method to display account information.

Demonstrates the creation and management of multiple bank account objects.

3.Classes

BankAccount

Fields: accountNumber, accountHolderName, balance

Methods:

Constructor to initialize account details

get and set methods for each field

setBalance() validates non-negative balances

displayAccountInfo() prints account details

Main2

Creates multiple BankAccount objects

Updates balances using setters

Displays all account information


4.output

Account Number: 1
Account Holder: Yasin Alom
Balance: 12000.0

Error: Balance cannot be negative. Value not updated.

Account Number: 2
Account Holder: Habibullah
Balance: 18016.0

Account Number: 3
Account Holder: Arman Alif
Balance: 30000.0
 
 5.UML DIAGRAM
 -------------------------------
|        BankAccount          |
-------------------------------
| - accountNumber: int        |
| - accountHolderName: String |
| - balance: double           |
-------------------------------
| + BankAccount(accountNumber: int, accountHolderName: String, balance: double) |
| + getAccountNumber(): int                                                |
| + setAccountNumber(accountNumber: int): void                              |
| + getAccountHolderName(): String                                         |
| + setAccountHolderName(accountHolderName: String): void                  |
| + getBalance(): double                                                   |
| + setBalance(balance: double): void                                      |
| + displayAccountInfo(): void                                             |
-------------------------------

-------------------------------
|          Main2              |
-------------------------------
| + main(args: String[]): void  |
-------------------------------

