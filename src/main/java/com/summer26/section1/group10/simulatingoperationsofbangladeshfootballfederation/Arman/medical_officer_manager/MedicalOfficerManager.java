package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MedicalOfficer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MedicalOfficerManager {

    private static final List<MedicalOfficer> medicalOfficerList = new ArrayList<>();
    private static final String FILE_NAME = "medical-officers.bin";

    static {
        loadFromFile();
    }

    public static List<MedicalOfficer> getMedicalOfficerList() {
        return medicalOfficerList;
    }

    public static void addMedicalOfficer(MedicalOfficer medicalOfficer) {
        medicalOfficerList.add(medicalOfficer);
    }

    @SuppressWarnings("unchecked")
    private static void loadFromFile() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            medicalOfficerList.clear();
            medicalOfficerList.addAll(
                    (ArrayList<MedicalOfficer>) in.readObject()
            );

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load medical officer data.");
        }
    }

    public static void saveToFile() {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            ArrayList<MedicalOfficer> tempList =
                    new ArrayList<>(medicalOfficerList);

            out.writeObject(tempList);

        } catch (IOException e) {
            System.out.println("Could not save medical officer data.");
        }
    }
}