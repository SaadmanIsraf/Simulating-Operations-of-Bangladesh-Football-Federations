package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_manager;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.TrainingSchedule;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TrainingScheduleManager {

    private static final List<TrainingSchedule> trainingScheduleList = new ArrayList<>();
    private static final String FILE_NAME = "training-schedules.bin";

    static {
        loadFromFile();
    }

    public static List<TrainingSchedule> getTrainingScheduleList() {
        return trainingScheduleList;
    }

    public static void addTrainingSchedule(TrainingSchedule trainingSchedule) {
        trainingScheduleList.add(trainingSchedule);
    }

    public static void removeTrainingSchedule(TrainingSchedule trainingSchedule) {
        trainingScheduleList.remove(trainingSchedule);
    }

    @SuppressWarnings("unchecked")
    private static void loadFromFile() {
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            trainingScheduleList.clear();

            trainingScheduleList.addAll(
                    (ArrayList<TrainingSchedule>) inputStream.readObject()
            );

            System.out.println(
                    "Training schedules loaded successfully: "
                            + trainingScheduleList.size()
            );

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load training schedule data.");
        }
    }

    public static void saveToFile() {
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            ArrayList<TrainingSchedule> tempList =
                    new ArrayList<>(trainingScheduleList);

            outputStream.writeObject(tempList);
            outputStream.flush();

            System.out.println("Training schedules saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not save training schedule data.");
        }
    }
}