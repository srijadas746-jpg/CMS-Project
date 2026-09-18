package com.cms.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

    /**
     * Saves a serializable object (list or map) to a specified file path.
     * @param obj The object to be saved (must implement Serializable).
     * @param fileName The name of the file (e.g., "students.ser").
     */
    public static void saveData(Object obj, String fileName) {
        try (
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)
        ) {
            objectOut.writeObject(obj);
            System.out.println("\n[SYSTEM] Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.err.println("\n[ERROR] Failed to save data to file: " + e.getMessage());
            // Log the error for maintainability
            e.printStackTrace(); 
        }
    }

    /**
     * Loads a serializable object from a specified file path.
     * @param fileName The name of the file (e.g., "students.ser").
     * @return The deserialized object, or null if the file is not found or corrupted.
     */
    @SuppressWarnings("unchecked")
    public static Object loadData(String fileName) {
        try (
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn)
        ) {
            // Read the object from the file
            return objectIn.readObject();
        } catch (FileNotFoundException e) {
            // If the file doesn't exist, it means this is the first run, 
            // so return null (or an empty structure) to indicate no data loaded.
            System.out.println("[SYSTEM] Data file not found: " + fileName + ". Starting with empty data.");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            // Catches general I/O errors or if the class structure has changed
            System.err.println("[ERROR] Failed to load data from file: " + fileName + " - " + e.getMessage());
            // It's safer to return null if the data is corrupt
            return null;
        }
    }
}