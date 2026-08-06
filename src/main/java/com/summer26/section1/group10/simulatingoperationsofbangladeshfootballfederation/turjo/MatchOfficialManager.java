package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class MatchOfficialManager {

    private static final String FILE_NAME = "matchofficials.bin";

    private static ArrayList<MatchOfficial> matchOfficialList =
            new ArrayList<>();


    static {
        loadFromFile();
    }

    public static ArrayList<MatchOfficial> getMatchOfficialList() {
        return matchOfficialList;
    }

    public static void addMatchOfficial(MatchOfficial matchOfficial) {
        matchOfficialList.add(matchOfficial);
    }

    public static void removeMatchOfficial(MatchOfficial matchOfficial) {
        matchOfficialList.remove(matchOfficial);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(matchOfficialList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            matchOfficialList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                matchOfficialList =
                        (ArrayList<MatchOfficial>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            matchOfficialList = new ArrayList<>();
        }
    }
}