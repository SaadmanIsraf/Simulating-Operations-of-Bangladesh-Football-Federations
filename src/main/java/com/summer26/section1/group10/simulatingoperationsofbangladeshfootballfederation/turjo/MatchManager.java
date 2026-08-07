package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class MatchManager {

    private static final String FILE_NAME = "Match.bin";

    private static ArrayList<Match> matchList = new ArrayList<>();

    public static void addMatch(Match match) {
        matchList.add(match);
    }

    public static void removeMatch(Match match) {
        matchList.remove(match);
    }

    public static ArrayList<Match> getMatchList() {
        return matchList;
    }

    public static void saveToFile() {

        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE_NAME));

            oos.writeObject(matchList);

            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            matchList = new ArrayList<>();
            return;
        }

        try {
            ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(FILE_NAME));

            matchList = (ArrayList<Match>) ois.readObject();

            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            matchList = new ArrayList<>();
            e.printStackTrace();
        }
    }
}