package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class VerifyPlayerEligibilityManager {

    private static final String FILE_NAME = "verifyplayereligibility.bin";

    private static ArrayList<VerifyPlayerEligibility> playerList = new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<VerifyPlayerEligibility> getPlayerList() {
        return playerList;
    }

    public static void addPlayer(VerifyPlayerEligibility player) {
        playerList.add(player);
    }

    public static void removePlayer(VerifyPlayerEligibility player) {
        playerList.remove(player);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(playerList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            playerList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            playerList =
                    (ArrayList<VerifyPlayerEligibility>) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {

            playerList = new ArrayList<>();
            e.printStackTrace();
        }
    }
}
