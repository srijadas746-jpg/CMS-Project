package com.cms.service;

import com.cms.model.EnrollmentRecord;
import com.cms.model.Student;

public class GradeService {

    private final CourseService courseService;

    // Dependency Injection: Requires CourseService to look up students
    public GradeService(CourseService courseService) {
        this.courseService = courseService;
    }

    // --- Functional Module 2: Grade Calculation ---
    public void recordScores(String studentId, String courseId, double midterm, double finalScore) {
        Student student = courseService.getStudent(studentId);
        
        if (student == null) {
            System.out.println("[ERROR] Student ID not found for score recording.");
            return;
        }

        for (EnrollmentRecord record : student.getEnrolledCourses()) {
            if (record.getCourseId().equals(courseId)) {
                // Scores are recorded in the EnrollmentRecord object
                record.setMidtermScore(midterm);
                record.setFinalScore(finalScore);
                
                // Calculate and set the final grade immediately
                String finalGrade = calculateFinalGrade(midterm, finalScore);
                record.setFinalGrade(finalGrade);
                
                System.out.println("[SUCCESS] Scores recorded and final grade calculated for " + studentId + " in " + courseId + ": " + finalGrade);
                return;
            }
        }
        System.out.println("[ERROR] Student is not enrolled in course ID: " + courseId);
    }
    
    // Core Grade Calculation Logic (can be a separate interface for polymorphism)
    private String calculateFinalGrade(double midterm, double finalScore) {
        // Assume weights: Midterm (40%), Final (60%)
        double finalAvg = (midterm * 0.40) + (finalScore * 0.60);

        if (finalAvg >= 90) return "A";
        if (finalAvg >= 80) return "B";
        if (finalAvg >= 70) return "C";
        if (finalAvg >= 60) return "D";
        return "F";
    }
}