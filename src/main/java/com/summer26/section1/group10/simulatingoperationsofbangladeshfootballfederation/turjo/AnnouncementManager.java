package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class AnnouncementManager {

    private static final String FILE_NAME = "announcements.bin";

    private static ArrayList<Announcement> announcementList =
            new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<Announcement> getAnnouncementList() {
        return announcementList;
    }

    public static void addAnnouncement(Announcement announcement) {
        announcementList.add(announcement);
    }

    public static void removeAnnouncement(Announcement announcement) {
        announcementList.remove(announcement);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(announcementList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            announcementList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                announcementList =
                        (ArrayList<Announcement>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            announcementList = new ArrayList<>();
        }
    }
}
