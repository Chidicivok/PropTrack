PropTrack – Property Sales Management System

PropTrack is a Java Swing desktop application designed to manage property sales records, customer details, estate agent information, mortgage-related calculations, and property sales reporting.

The system was developed using Object-Oriented Programming principles, file-based persistence, custom exception handling, and JUnit testing.



Features
- Add property sales records
- View properties in a JTable
- Search property by reference number
- Update estate agent name
- Delete/cancel property records
- Count townhouse sales
- Calculate deposit amount
- Calculate loan amount
- Calculate monthly instalments
- Calculate total payment and total interest
- Calculate agent commission
- Dashboard with summary statistics
- File-based data persistence
- JUnit tests for business calculations

Technologies Used
- Java
- Java Swing
- JUnit 4
- Object Serialization
- NetBeans IDE
- Git & GitHub

Architecture
The project uses a simple two-package structure:


Project Background

PropTrack is a redesigned and enhanced redevelopment of an earlier academic Java Swing property management assignment originally created during university coursework.

The original implementation focused on GUI-based data entry, object serialization, and basic property calculations. This version modernizes and expands the system with stronger validation, JTable-based record viewing, dashboard analytics, custom exception handling, JUnit testing and  improved persistence handling


Structure 
PropTrack/
│
├── screenshots/
│
├── src/
│   └── vut/
│       ├── data/
│       └── gui/
│
├── test/
│   └── vut/
│       └── data/
│
├── property.dat
│
└── README.md