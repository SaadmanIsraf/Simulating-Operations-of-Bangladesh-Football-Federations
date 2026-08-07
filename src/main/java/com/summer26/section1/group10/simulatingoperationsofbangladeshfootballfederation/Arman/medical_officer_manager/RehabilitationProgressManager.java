package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.RehabilitationProgress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RehabilitationProgressManager {

    private static final List<RehabilitationProgress> progressList = new ArrayList<>();
    private static final String FILE_NAME = "rehabilitation-progress.bin";

    static {
        loadFromFile();
    }

    public static List<RehabilitationProgress> getProgressList() {
        return progressList;
    }

    public static void addProgress(RehabilitationProgress progress) {
        progressList.add(progress);
    }

    @SuppressWarnings("unchecked")
    private static void loadFromFile() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            progressList.clear();
            progressList.addAll(
                    (ArrayList<RehabilitationProgress>) in.readObject()
            );

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load rehabilitation progress.");
        }
    }

    public static void saveToFile() {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            ArrayList<RehabilitationProgress> tempList =
                    new ArrayList<>(progressList);

            out.writeObject(tempList);

        } catch (IOException e) {
            System.out.println("Could not save rehabilitation progress.");
        }
    }
}