package com.cms.model;

import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String courseId;
    private String name;
    private int credits;
    private String scheduleTime; // e.g., "MWF 10:00"

    public Course(String courseId, String name, int credits, String scheduleTime) {
        this.courseId = courseId;
        this.name = name;
        this.credits = credits;
        this.scheduleTime = scheduleTime;
    }

    // --- Encapsulation: Getters and Setters ---
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    // --- Polymorphism/Display Method ---
    public void displayCourseDetails() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Name: " + name);
        System.out.println("Credits: " + credits);
        System.out.println("Schedule: " + scheduleTime);
    }
}