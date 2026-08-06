package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.TreatmentPlan;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TreatmentPlanManager {

    private static final List<TreatmentPlan> treatmentPlanList = new ArrayList<>();
    private static final String FILE_NAME = "treatment-plans.bin";

    static {
        loadFromFile();
    }

    public static List<TreatmentPlan> getTreatmentPlanList() {
        return treatmentPlanList;
    }

    public static void addTreatmentPlan(TreatmentPlan treatmentPlan) {
        treatmentPlanList.add(treatmentPlan);
    }

    @SuppressWarnings("unchecked")
    private static void loadFromFile() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            treatmentPlanList.clear();
            treatmentPlanList.addAll(
                    (ArrayList<TreatmentPlan>) in.readObject()
            );

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load treatment plans.");
        }
    }

    public static void saveToFile() {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            ArrayList<TreatmentPlan> tempList =
                    new ArrayList<>(treatmentPlanList);

            out.writeObject(tempList);

        } catch (IOException e) {
            System.out.println("Could not save treatment plans.");
        }
    }
}