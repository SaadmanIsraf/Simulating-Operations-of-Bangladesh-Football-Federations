package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class TeamRankingManager {

    private static final String FILE_NAME = "teamrankings.bin";

    private static ArrayList<TeamRanking> teamRankingList =
            new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<TeamRanking> getTeamRankingList() {
        return teamRankingList;
    }

    public static void addTeamRanking(TeamRanking teamRanking) {
        teamRankingList.add(teamRanking);
    }

    public static void removeTeamRanking(TeamRanking teamRanking) {
        teamRankingList.remove(teamRanking);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(teamRankingList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            teamRankingList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                teamRankingList = (ArrayList<TeamRanking>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            teamRankingList = new ArrayList<>();
        }
    }
}
