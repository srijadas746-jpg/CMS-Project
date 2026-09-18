package com.cms.util;

import com.cms.model.Course;
import com.cms.model.Student;

public class Validator {

    /**
     * Checks if a score is within the valid range (0 to 100).
     * @param score The score to validate.
     * @return true if the score is valid, false otherwise.
     */
    public static boolean isScoreValid(double score) {
        return score >= 0 && score <= 100;
    }

    /**
     * Checks if a course schedule time string is non-empty.
     * (A production system would use more complex regex for time format validation.)
     * @param schedule The schedule time string.
     * @return true if the schedule is not null or empty.
     */
    public static boolean isScheduleValid(String schedule) {
        return schedule != null && !schedule.trim().isEmpty();
    }
    
    /**
     * Checks if a string ID is valid (non-null and 5 characters long).
     * @param id The ID string (student or course ID).
     * @return true if the ID is valid.
     */
    public static boolean isIdValid(String id) {
        return id != null && id.trim().length() == 5;
    }

    /**
     * Converts string input to double safely, handling potential NumberFormatException.
     * @param input The string to convert.
     * @return The double value, or -1.0 if conversion fails.
     */
    public static double safeParseDouble(String input) {
        try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            return -1.0; // Indicate failure
        }
    }
}