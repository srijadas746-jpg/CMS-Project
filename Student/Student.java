package com.cms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentId;
    private String name;
    private String major;
    // Data Structure: List to hold all courses a student is enrolled in
    private List<EnrollmentRecord> enrolledCourses; 

    public Student(String studentId, String name, String major) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        // Initialize the list when the Student object is created
        this.enrolledCourses = new ArrayList<>(); 
    }

    // --- Encapsulation: Getters and Setters ---
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<EnrollmentRecord> getEnrolledCourses() {
        return enrolledCourses;
    }

    // Note: No setter for enrolledCourses; we use a dedicated add method instead
    public void addCourse(EnrollmentRecord record) {
        this.enrolledCourses.add(record);
    }
}