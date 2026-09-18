package com.cms.app;

import com.cms.model.Course;
import com.cms.model.LabCourse;
import com.cms.model.Student;
import com.cms.service.CourseService;
import com.cms.service.GradeService;
import com.cms.service.CourseConflictException;
import com.cms.util.Validator;

import java.util.Map;
import java.util.Scanner;

public class MainApp {
    
    private static Scanner scanner = new Scanner(System.in);
    private static CourseService courseService;
    private static GradeService gradeService;

    public static void main(String[] args) {
        // Initialize Services
        courseService = new CourseService();
        gradeService = new GradeService(courseService);
        
        System.out.println("====================================================");
        System.out.println("  VITyarthi University Course Management System (CMS)");
        System.out.println("====================================================");

        boolean running = true;
        while (running) {
            displayMenu();
            String choice = scanner.nextLine();

            // Error Handling: Use try-catch blocks to handle input issues
            try {
                switch (choice) {
                    case "1":
                        addStudent();
                        break;
                    case "2":
                        addCourse();
                        break;
                    case "3":
                        enrollStudent();
                        break;
                    case "4":
                        recordScores();
                        break;
                    case "5":
                        viewStudentDetails();
                        break;
                    case "6":
                        // Exit operation
                        running = false;
                        break;
                    default:
                        System.out.println("[WARNING] Invalid choice. Please select 1-6.");
                }
            } catch (Exception e) {
                // Catch any unexpected exceptions and print an error message
                System.err.println("[CRITICAL ERROR] An unexpected error occurred: " + e.getMessage());
                // For maintainability, print stack trace
                // e.printStackTrace(); 
            }
        }
        
        // Final action: Save all persistent data before exiting
        courseService.saveAllData();
        System.out.println("\n[SYSTEM] Data saved. Thank you for using NutriPlanner CMS.");
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add New Student");
        System.out.println("2. Add New Course (or Lab Course)");
        System.out.println("3. Enroll Student in Course (Functional Module 1)");
        System.out.println("4. Record Scores/Calculate Grade (Functional Module 2)");
        System.out.println("5. View Student Enrollment Report (Functional Module 3 - Reporting)");
        System.out.println("6. Exit & Save Data");
        System.out.print("Enter choice: ");
    }
    
    // --- Functional Implementation Methods ---

    private static void addStudent() {
        System.out.print("Enter Student ID (5 chars): ");
        String id = scanner.nextLine();
        if (!Validator.isIdValid(id)) {
            System.out.println("[ERROR] Invalid Student ID format.");
            return;
        }
        
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Major: ");
        String major = scanner.nextLine();

        courseService.addStudent(new Student(id, name, major));
        System.out.println("[SUCCESS] Student " + name + " added.");
    }

    private static void addCourse() {
        System.out.print("Enter Course ID (5 chars): ");
        String id = scanner.nextLine();
        if (!Validator.isIdValid(id)) {
            System.out.println("[ERROR] Invalid Course ID format.");
            return;
        }

        System.out.print("Is this a Lab Course? (yes/no): ");
        boolean isLab = scanner.nextLine().trim().equalsIgnoreCase("yes");
        
        // --- FIX Applied Here ---
        // (Optional: You could insert scanner.nextLine() here if the issue persisted, but 
        //  since the previous line uses nextLine() it shouldn't be needed in theory.)
        // --- FIX END ---
        
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine(); // This should now wait for input
        
        System.out.print("Enter Credits (e.g., 3): ");
        // CORRECTED LOGIC: Read line once, then validate and parse.
        String creditInput = scanner.nextLine();
        int credits = 0;
        double parsedCredits = Validator.safeParseDouble(creditInput);
        
        if (parsedCredits > 0) {
             credits = (int)parsedCredits;
        } else {
             System.out.println("[WARNING] Invalid credit input. Setting credits to 0.");
        }


        System.out.print("Enter Schedule Time (e.g., MWF 10:00): ");
        String time = scanner.nextLine();

        Course newCourse;
        if (isLab) {
            System.out.print("Enter Lab Location: ");
            String location = scanner.nextLine();
            newCourse = new LabCourse(id, name, credits, time, location); // Demonstrates Inheritance
        } else {
            newCourse = new Course(id, name, credits, time);
        }

        courseService.addCourse(newCourse);
        System.out.println("[SUCCESS] Course " + name + " added.");
    }
    
    private static void enrollStudent() {
        System.out.print("Enter Student ID to enroll: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();

        try {
            // The service method handles all the core logic and throws a custom exception
            courseService.enrollStudent(studentId, courseId);
        } catch (CourseConflictException e) {
            // Specific Error Handling for Functional Module 3 (Conflict Check)
            System.err.println("[ENROLLMENT FAILED] " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[ENROLLMENT FAILED] Could not process request: " + e.getMessage());
        }
    }

    private static void recordScores() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        
        System.out.print("Enter Midterm Score (0-100): ");
        double midterm = Validator.safeParseDouble(scanner.nextLine());
        System.out.print("Enter Final Score (0-100): ");
        double finalScore = Validator.safeParseDouble(scanner.nextLine());
        
        if (!Validator.isScoreValid(midterm) || !Validator.isScoreValid(finalScore)) {
            System.out.println("[ERROR] Scores must be between 0 and 100.");
            return;
        }

        gradeService.recordScores(studentId, courseId, midterm, finalScore);
    }
    
    private static void viewStudentDetails() {
        System.out.print("Enter Student ID for report: ");
        String studentId = scanner.nextLine();
        Student student = courseService.getStudent(studentId);

        if (student == null) {
            System.out.println("[ERROR] Student ID " + studentId + " not found.");
            return;
        }

        System.out.println("\n--- Student Enrollment Report: " + student.getName() + " (" + student.getStudentId() + ") ---");
        System.out.println("Major: " + student.getMajor());
        System.out.println("Courses Enrolled:");

        if (student.getEnrolledCourses().isEmpty()) {
            System.out.println("  (No courses currently enrolled.)");
            return;
        }

        for (int i = 0; i < student.getEnrolledCourses().size(); i++) {
            com.cms.model.EnrollmentRecord record = student.getEnrolledCourses().get(i);
            Course course = courseService.getCourse(record.getCourseId());
            
            System.out.println((i + 1) + ". " + record.getCourseId() + " - " + course.getName());
            System.out.println("   Schedule: " + course.getScheduleTime());
            System.out.println("   Midterm: " + (record.getMidtermScore() == -1.0 ? "N/A" : record.getMidtermScore()));
            System.out.println("   Final: " + (record.getFinalScore() == -1.0 ? "N/A" : record.getFinalScore()));
            System.out.println("   Final Grade: " + record.getFinalGrade());
            System.out.println("   --------------------");
        }
    }
}