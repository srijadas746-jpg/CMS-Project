# Project Statement: University Course Management System (CMS)

## Problem Statement
The current university system relies on manual intervention for processes like course registration, scheduling, and grade tracking. This manual system is highly prone to human errors, resulting in scheduling conflicts, data inconsistency, and delays in reporting. The CMS project aims to automate these core functions to improve **Reliability** and **Performance**. [cite: 99]

## Scope of the Project
The project is implemented as a **Command Line Interface (CLI) application** using **Java**. [cite: 100] Its scope is limited to managing student and course data in memory and persisting it to files using Java Serialization. It focuses on core administrative logic, including validation and conflict detection.

**Exclusions from Scope:**
* Graphical User Interface (GUI).
* External database integration (JDBC).

## Target Users
* **System Administrator:** Primary user for managing student/course data and initiating enrollments. [cite: 102]
* **Faculty:** User role for recording and viewing student assessment scores.

## High-Level Features
1. **Conflict Prevention:** Automated checking of course schedules to prevent time overlaps during enrollment. [cite: 103]
2. **Weighted Grade Calculation:** Automated calculation of final grades based on weighted scores (e.g., 40/60).
3. **Data Persistence:** Reliable saving and loading of all application data using Java Serialization.
