package com.cms.service;

// This class is now public and lives in its own file, matching the file name.
public class CourseConflictException extends Exception {
    private static final long serialVersionUID = 1L;

    public CourseConflictException(String message) {
        super(message);
    }
}