# 🎓 VITyarthi University Course Management System (CMS)

## Project Overview
This is a Java Command Line Interface (CLI) application developed for the VITyarthi project submission. The system automates course registration, manages student enrollments, and performs grade calculations, specifically designed to demonstrate core Object-Oriented Programming (OOP) principles and robust data management.

## Key Features (Functional Requirements)
1.  **Student & Course Management:** Full CRUD (Create, Read, Update) operations for student and course records.
2.  **Conflict-Free Enrollment:** [Functional Module 3] Automatically checks course schedules (`MWF 10:00` vs `TTH 11:30`) during registration to prevent time overlaps, using custom exception handling for robust error management.
3.  **Weighted Grade Calculation:** [Functional Module 2] Records mid-term and final scores and calculates a final letter grade based on a weighted average (40% Midterm, 60% Final).

## Technologies & Core Concepts Used
| Concept | Implementation in CMS | Requirement |
| :--- | :--- | :--- |
| **Language** | Java (JDK 8+) | Programming Constraint |
| **Data Structure** | `HashMap` | Non-Functional Requirement (Performance) |
| **OOP** | `Course` class extended by `LabCourse` | Inheritance/Polymorphism |
| **Persistence** | Java Serialization (`ObjectOutputStream`/`ObjectInputStream`) | Non-Functional Requirement (Reliability) |
| **Error Handling** | `try-catch` blocks and `CourseConflictException` | Non-Functional Requirement |

## Steps to Install & Run
This process assumes you have Java JDK installed and are running commands from the **CMS_Project** root directory.

1.  **Compile the Project:** Navigate *into* the `src` folder and compile all Java files into the `bin` directory.
    ```bash
    cd src
    javac -d ../bin com/cms/model/*.java com/cms/service/*.java com/cms/util/*.java com/cms/app/*.java
    ```
2.  **Run the Application:** Navigate back to the project root and execute the main class, setting the classpath (`-cp bin`).
    ```bash
    cd ..
    java -cp bin com.cms.app.MainApp
    ```

## Testing Instructions
1.  Use Menu Option **1** and **2** to add test Students and Courses.
2.  Use Option **3** to enroll a student in a course, and then attempt to enroll the same student in a course with a **conflicting schedule time** (e.g., both at `MWF 10:00`) to verify the custom exception handling.
3.  Use Option **4** to record scores and trigger the final grade calculation.
4.  Use Option **5** to view the student report (where you took your required screenshot).
