package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class MatchScheduleManager {

    private static final String FILE_NAME = "matchschedule.bin";

    private static ArrayList<MatchSchedule> scheduleList = new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<MatchSchedule> getScheduleList() {
        return scheduleList;
    }

    public static void addSchedule(MatchSchedule schedule) {
        scheduleList.add(schedule);
    }

    public static void removeSchedule(MatchSchedule schedule) {
        scheduleList.remove(schedule);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(
                             new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(scheduleList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            scheduleList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            scheduleList =
                    (ArrayList<MatchSchedule>) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {

            scheduleList = new ArrayList<>();
            e.printStackTrace();
        }
    }
}