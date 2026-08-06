package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class MatchScheduleManager {

    private static final String FILE_NAME = "matchschedule.bin";

    private static ArrayList<MatchSchedule> matchScheduleList =
            new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<MatchSchedule> getMatchScheduleList() {
        return matchScheduleList;
    }

    public static void addMatchSchedule(MatchSchedule matchSchedule) {
        matchScheduleList.add(matchSchedule);
    }

    public static void removeMatchSchedule(MatchSchedule matchSchedule) {
        matchScheduleList.remove(matchSchedule);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(matchScheduleList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            matchScheduleList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                matchScheduleList =
                        (ArrayList<MatchSchedule>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            matchScheduleList = new ArrayList<>();
        }
    }
}