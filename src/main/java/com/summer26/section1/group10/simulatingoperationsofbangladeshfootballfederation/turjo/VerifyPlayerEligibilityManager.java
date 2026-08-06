package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class VerifyPlayerEligibilityManager {

    private static final String FILE_NAME = "verifyplayereligibility.bin";

    private static ArrayList<VerifyPlayerEligibility> eligibilityList =
            new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<VerifyPlayerEligibility> getEligibilityList() {
        return eligibilityList;
    }

    public static void addEligibility(VerifyPlayerEligibility eligibility) {
        eligibilityList.add(eligibility);
    }

    public static void removeEligibility(VerifyPlayerEligibility eligibility) {
        eligibilityList.remove(eligibility);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(eligibilityList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            eligibilityList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                eligibilityList =
                        (ArrayList<VerifyPlayerEligibility>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            eligibilityList = new ArrayList<>();
        }
    }
}
