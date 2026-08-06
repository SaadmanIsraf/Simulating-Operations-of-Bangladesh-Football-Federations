package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class SubmitMatchReportManager {

    private static final String FILE_NAME = "submitmatchreport.bin";

    private static ArrayList<SubmitMatchReport> matchReportList =
            new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<SubmitMatchReport> getMatchReportList() {
        return matchReportList;
    }

    public static void addMatchReport(SubmitMatchReport matchReport) {
        matchReportList.add(matchReport);
    }

    public static void removeMatchReport(SubmitMatchReport matchReport) {
        matchReportList.remove(matchReport);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(matchReportList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            matchReportList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                matchReportList =
                        (ArrayList<SubmitMatchReport>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            matchReportList = new ArrayList<>();
        }
    }
}
