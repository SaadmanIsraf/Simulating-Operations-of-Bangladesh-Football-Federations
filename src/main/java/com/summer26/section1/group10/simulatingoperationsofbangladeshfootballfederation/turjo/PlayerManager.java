package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;

import java.io.*;
import java.util.ArrayList;

public class PlayerManager {

    private static final String FILE_NAME = "players.bin";

    private static ArrayList<Player> playerList = new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public static void addPlayer(Player player) {
        playerList.add(player);
    }

    public static void removePlayer(Player player) {
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

            playerList = (ArrayList<Player>) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            playerList = new ArrayList<>();
            e.printStackTrace();
        }
    }
}