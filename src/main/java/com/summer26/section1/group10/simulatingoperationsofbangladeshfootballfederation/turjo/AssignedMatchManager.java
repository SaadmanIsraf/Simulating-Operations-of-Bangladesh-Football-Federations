package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class AssignedMatchManager {

    private static final String FILE_NAME = "assignedmatches.bin";

    private static ArrayList<AssignedMatch> assignedMatchList =
            new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<AssignedMatch> getAssignedMatchList() {
        return assignedMatchList;
    }

    public static void addAssignedMatch(AssignedMatch assignedMatch) {
        assignedMatchList.add(assignedMatch);
    }

    public static void removeAssignedMatch(AssignedMatch assignedMatch) {
        assignedMatchList.remove(assignedMatch);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(assignedMatchList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            assignedMatchList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                assignedMatchList =
                        (ArrayList<AssignedMatch>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            assignedMatchList = new ArrayList<>();
        }
    }
}
