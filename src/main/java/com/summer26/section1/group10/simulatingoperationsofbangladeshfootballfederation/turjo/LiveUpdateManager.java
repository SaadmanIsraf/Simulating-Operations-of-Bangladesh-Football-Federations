package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class LiveUpdateManager {

    private static final String FILE_NAME = "liveupdates.bin";

    private static ArrayList<LiveUpdate> liveUpdateList =
            new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<LiveUpdate> getLiveUpdateList() {
        return liveUpdateList;
    }

    public static void addLiveUpdate(LiveUpdate liveUpdate) {
        liveUpdateList.add(liveUpdate);
    }

    public static void removeLiveUpdate(LiveUpdate liveUpdate) {
        liveUpdateList.remove(liveUpdate);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(liveUpdateList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            liveUpdateList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                liveUpdateList = (ArrayList<LiveUpdate>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            liveUpdateList = new ArrayList<>();
        }
    }
}
