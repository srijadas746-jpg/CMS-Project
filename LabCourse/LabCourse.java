package com.cms.model;

public class LabCourse extends Course {
    private static final long serialVersionUID = 1L;

    private String labLocation; // Additional field specific to LabCourse

    public LabCourse(String courseId, String name, int credits, String scheduleTime, String labLocation) {
        // Calls the constructor of the base Course class
        super(courseId, name, credits, scheduleTime);
        this.labLocation = labLocation;
    }

    // --- Encapsulation: Getter and Setter for labLocation ---
    public String getLabLocation() {
        return labLocation;
    }

    public void setLabLocation(String labLocation) {
        this.labLocation = labLocation;
    }

    // --- Polymorphism: Overriding the display method ---
    @Override
    public void displayCourseDetails() {
        // Reuses the display logic from the base Course class
        super.displayCourseDetails(); 
        System.out.println("Lab Location: " + labLocation);
    }
}