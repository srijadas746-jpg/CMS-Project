package com.cms.model;

import java.io.Serializable;

public class EnrollmentRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private String courseId;
    private double midtermScore;
    private double finalScore;
    private String finalGrade; // e.g., "A", "B+", "F"

    public EnrollmentRecord(String courseId) {
        this.courseId = courseId;
        this.midtermScore = -1.0; // Use -1.0 to indicate score not yet recorded
        this.finalScore = -1.0;
        this.finalGrade = "N/A";
    }

    // --- Encapsulation: Getters and Setters ---
    public String getCourseId() {
        return courseId;
    }
    // Note: Setter for courseId is usually not needed after initialization

    public double getMidtermScore() {
        return midtermScore;
    }

    public void setMidtermScore(double midtermScore) {
        this.midtermScore = midtermScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }
}