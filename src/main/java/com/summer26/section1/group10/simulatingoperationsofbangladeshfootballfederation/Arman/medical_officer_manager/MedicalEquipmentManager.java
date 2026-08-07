package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MedicalEquipment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MedicalEquipmentManager {

    private static final List<MedicalEquipment> equipmentList = new ArrayList<>();
    private static final String FILE_NAME = "medical-equipment.bin";

    static {
        loadFromFile();
    }

    public static List<MedicalEquipment> getEquipmentList() {
        return equipmentList;
    }

    public static void addEquipment(MedicalEquipment equipment) {
        equipmentList.add(equipment);
    }

    @SuppressWarnings("unchecked")
    private static void loadFromFile() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            equipmentList.clear();
            equipmentList.addAll(
                    (ArrayList<MedicalEquipment>) in.readObject()
            );

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load medical equipment data.");
        }
    }

    public static void saveToFile() {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            ArrayList<MedicalEquipment> tempList =
                    new ArrayList<>(equipmentList);

            out.writeObject(tempList);

        } catch (IOException e) {
            System.out.println("Could not save medical equipment data.");
        }
    }
}