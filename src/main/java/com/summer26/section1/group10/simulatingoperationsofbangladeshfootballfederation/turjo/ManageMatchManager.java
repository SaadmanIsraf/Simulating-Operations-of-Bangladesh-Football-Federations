package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class ManageMatchManager {

    private static final String FILE_NAME = "managematches.bin";

    private static ArrayList<ManageMatch> matchList =
            new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<ManageMatch> getMatchList() {
        return matchList;
    }

    public static void addMatch(ManageMatch manageMatch) {
        matchList.add(manageMatch);
    }

    public static void removeMatch(ManageMatch manageMatch) {
        matchList.remove(manageMatch);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(matchList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            matchList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                matchList = (ArrayList<ManageMatch>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            matchList = new ArrayList<>();
        }
    }
}