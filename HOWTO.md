# HOWTO.md (Activity 3)

## Project description
This is a Java Hospital Management System built using OOP.
It allows the user to enter details for a patient, doctor, and nurse, then create an appointment and generate a bill.

## How to run
1. Compile:
   javac *.java
2. Run:
   java HospitalMain

## Exceptions used
The program uses try/catch/finally to prevent crashing when the user enters wrong input.

1. InputMismatchException
- Used when the user types letters instead of numbers (age or amount).
- The program clears the wrong input and asks again.

2. Validation checks
- Empty text fields are not allowed.
- Age must be within a valid range.
- Amounts cannot be negative.

3. finally block
- The Scanner is closed in the finally block so the program ends safely.
