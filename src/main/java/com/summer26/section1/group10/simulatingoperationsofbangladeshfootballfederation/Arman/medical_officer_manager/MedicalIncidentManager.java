package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MatchDayMedicalIncident;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalIncidentManager {

    private static final List<MatchDayMedicalIncident> incidentList = new ArrayList<>();

    private static final String FILE_NAME = "medical-incidents.bin";

    static {
        loadFromFile();
    }

    public static List<MatchDayMedicalIncident> getIncidentList() {
        return incidentList;
    }

    public static void addIncident(MatchDayMedicalIncident incident) {
        incidentList.add(incident);
    }

    private static void loadFromFile() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            incidentList.clear();
            incidentList.addAll((ArrayList<MatchDayMedicalIncident>) in.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load medical incidents.");
        }
    }

    public static void saveToFile() {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            ArrayList<MatchDayMedicalIncident> tempList =
                    new ArrayList<>(incidentList);

            out.writeObject(tempList);

        } catch (IOException e) {
            System.out.println("Could not save medical incidents.");
        }
    }
}