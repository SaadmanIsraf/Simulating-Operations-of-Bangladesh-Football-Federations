package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility;



import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Generates the next unique ID for any domain class by reflecting over its
 * ID field and scanning existing records in a .bin file.
 * Works for any entity: Player, Club, Match, Referee, Official, etc.
 */
public class DatabaseAccessor {

    /** Use when the ID field is an int. */
    public static Integer generateNewUniqueId(String fileName, String fieldName) {

        ArrayList<Object> objectList = BinaryFileUtility.readObjects(fileName);

        if (objectList == null || objectList.isEmpty()) {
            return 1;
        }

        int maxId = 0;

        try {
            for (Object object : objectList) {
                Field field = object.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);

                int id = (Integer) field.get(object);

                if (id > maxId) {
                    maxId = id;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return maxId + 1;
    }

    /** Use when the ID field is a String containing a numeric value. */
    public static String generateNewUniqueId(String fileName, String fieldName, String unused) {

        ArrayList<Object> objectList = BinaryFileUtility.readObjects(fileName);

        if (objectList == null || objectList.isEmpty()) {
            return "1";
        }

        int maxId = 0;

        try {
            for (Object object : objectList) {
                Field field = object.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);

                int id = Integer.parseInt(field.get(object).toString());

                if (id > maxId) {
                    maxId = id;
                }
            }
        } catch (NumberFormatException e1) {
            System.out.println("Exception in converting String to Integer");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.valueOf(maxId + 1);
    }

    /** Use to prefix an ID, e.g. "PLR-0007", "CLB-0012", "MATCH-0031". */
    public static String generatePrefixedId(String fileName, String fieldName, String prefix, int padWidth) {
        int nextNumeric = generateNewUniqueId(fileName, fieldName);
        return prefix + "-" + String.format("%0" + padWidth + "d", nextNumeric);
    }
}
