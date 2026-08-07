package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class LogVARReportManager {

    private static final String FILE_NAME = "LogVARReport.bin";

    private static ArrayList<LogVARReport> reportList = new ArrayList<>();

    public static void addReport(LogVARReport report) {
        reportList.add(report);
    }

    public static void removeReport(LogVARReport report) {
        reportList.remove(report);
    }

    public static ArrayList<LogVARReport> getReportList() {
        return reportList;
    }

    public static void saveToFile() {

        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE_NAME));

            oos.writeObject(reportList);

            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            reportList = new ArrayList<>();
            return;
        }

        try {
            ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(FILE_NAME));

            reportList = (ArrayList<LogVARReport>) ois.readObject();

            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            reportList = new ArrayList<>();
            e.printStackTrace();
        }
    }
}
