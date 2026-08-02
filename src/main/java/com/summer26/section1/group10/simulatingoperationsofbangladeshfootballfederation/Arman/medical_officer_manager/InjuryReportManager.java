package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.InjuryReport;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InjuryReportManager {

    private static final List<InjuryReport> injuryReportList = new ArrayList<>();

    private static final String FILE_NAME = "injury-reports.bin";

    static {
        loadFromFile();
    }

    public static List<InjuryReport> getInjuryReportList() {
        return injuryReportList;
    }

    public static void addReport(InjuryReport report) {
        injuryReportList.add(report);
    }

    private static void loadFromFile() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            injuryReportList.clear();
            injuryReportList.addAll((ArrayList<InjuryReport>) in.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load injury reports.");
        }
    }

    public static void saveToFile() {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            ArrayList<InjuryReport> tempList =
                    new ArrayList<>(injuryReportList);

            out.writeObject(tempList);

        } catch (IOException e) {
            System.out.println("Could not save injury reports.");
        }
    }
}