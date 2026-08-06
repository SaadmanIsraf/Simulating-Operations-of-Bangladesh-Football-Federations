package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_manager;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.PersonalStats;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonalStatsManager {

    private static final List<PersonalStats> personalStatsList = new ArrayList<>();
    private static final String FILE_NAME = "personal-stats.bin";

    static {
        loadFromFile();
    }

    public static List<PersonalStats> getPersonalStatsList() {
        return personalStatsList;
    }

    public static void addPersonalStats(PersonalStats personalStats) {
        personalStatsList.add(personalStats);
    }

    public static void removePersonalStats(PersonalStats personalStats) {
        personalStatsList.remove(personalStats);
    }

    public static PersonalStats findByPlayerId(int playerId) {
        for (PersonalStats personalStats : personalStatsList) {
            if (personalStats.getPlayerId() == playerId) {
                return personalStats;
            }
        }

        return null;
    }

    public static void updatePersonalStats(PersonalStats personalStats) {
        PersonalStats existingStats =
                findByPlayerId(personalStats.getPlayerId());

        if (existingStats == null) {
            addPersonalStats(personalStats);
        } else {
            existingStats.setPlayerName(personalStats.getPlayerName());
            existingStats.setGoals(personalStats.getGoals());
            existingStats.setAssists(personalStats.getAssists());
            existingStats.setMatchesPlayed(personalStats.getMatchesPlayed());
            existingStats.setMedicalCondition(personalStats.getMedicalCondition());
            existingStats.setLastUpdated(personalStats.getLastUpdated());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadFromFile() {
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            personalStatsList.clear();

            personalStatsList.addAll(
                    (ArrayList<PersonalStats>) inputStream.readObject()
            );

            System.out.println(
                    "Personal statistics loaded successfully: "
                            + personalStatsList.size()
            );

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load personal statistics data.");
        }
    }

    public static void saveToFile() {
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            ArrayList<PersonalStats> temporaryList =
                    new ArrayList<>(personalStatsList);

            outputStream.writeObject(temporaryList);
            outputStream.flush();

            System.out.println("Personal statistics saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not save personal statistics data.");
        }
    }
}