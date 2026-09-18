package com.cms.service;

import com.cms.model.Course;
import com.cms.model.EnrollmentRecord;
import com.cms.model.Student;
import com.cms.util.FileIO;

import java.util.HashMap;
import java.util.Map;



public class CourseService {

    // HashMap for O(1) retrieval performance based on ID (key)
    private Map<String, Student> studentMap;
    private Map<String, Course> courseMap;
    
    private static final String STUDENT_FILE = "students.ser";
    private static final String COURSE_FILE = "courses.ser";

    @SuppressWarnings("unchecked")
    public CourseService() {
        // Load data from files at startup. Use FileIO to handle potential errors.
        studentMap = (Map<String, Student>) FileIO.loadData(STUDENT_FILE);
        if (studentMap == null) {
            studentMap = new HashMap<>();
        }

        courseMap = (Map<String, Course>) FileIO.loadData(COURSE_FILE);
        if (courseMap == null) {
            courseMap = new HashMap<>();
        }
    }

    // --- Persistence Method ---
    public void saveAllData() {
        FileIO.saveData(studentMap, STUDENT_FILE);
        FileIO.saveData(courseMap, COURSE_FILE);
    }

    // --- Functional Module 1: Student/Course Management (CRUD) ---
    public void addStudent(Student student) {
        studentMap.put(student.getStudentId(), student);
    }

    public Student getStudent(String studentId) {
        return studentMap.get(studentId);
    }

    public Map<String, Student> getAllStudents() {
        return studentMap;
    }
    
    public void addCourse(Course course) {
        courseMap.put(course.getCourseId(), course);
    }

    public Course getCourse(String courseId) {
        return courseMap.get(courseId);
    }

    // --- Functional Module 3: Scheduling & Conflict Checker ---
    private boolean checkConflict(Student student, Course newCourse) {
        for (EnrollmentRecord record : student.getEnrolledCourses()) {
            // Retrieve the full Course object for the enrolled record
            Course enrolledCourse = courseMap.get(record.getCourseId());
            
            // Critical logic: Check if the new course time clashes with any existing course
            if (enrolledCourse != null && enrolledCourse.getScheduleTime().equals(newCourse.getScheduleTime())) {
                return true; // Conflict detected
            }
        }
        return false;
    }

    // --- Functional Module 1: Enrollment/Registration Logic ---
    public void enrollStudent(String studentId, String courseId) throws CourseConflictException {
        Student student = studentMap.get(studentId);
        Course course = courseMap.get(courseId);

        if (student == null) {
            System.out.println("[ERROR] Student ID not found.");
            return;
        }
        if (course == null) {
            System.out.println("[ERROR] Course ID not found.");
            return;
        }

        // 1. Check if already enrolled
        for (EnrollmentRecord record : student.getEnrolledCourses()) {
            if (record.getCourseId().equals(courseId)) {
                System.out.println("[ERROR] Student " + studentId + " is already enrolled in " + courseId + ".");
                return;
            }
        }

        // 2. Check for time conflict
        if (checkConflict(student, course)) {
            // Throw custom exception for MainApp to handle gracefully
            throw new CourseConflictException("Time conflict detected with another course at " + course.getScheduleTime());
        }

        // 3. Successful enrollment
        student.addCourse(new EnrollmentRecord(courseId));
        System.out.println("[SUCCESS] Student " + studentId + " enrolled in " + courseId + " successfully.");
    }
}