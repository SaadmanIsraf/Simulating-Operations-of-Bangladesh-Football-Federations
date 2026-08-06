package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class VARReportManager {

    private static final String FILE_NAME = "varreports.bin";

    private static ArrayList<VARReport> varReportList =
            new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<VARReport> getVarReportList() {
        return varReportList;
    }

    public static void addVarReport(VARReport varReport) {
        varReportList.add(varReport);
    }

    public static void removeVarReport(VARReport varReport) {
        varReportList.remove(varReport);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(varReportList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            varReportList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                varReportList =
                        (ArrayList<VARReport>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            varReportList = new ArrayList<>();
        }
    }
}
