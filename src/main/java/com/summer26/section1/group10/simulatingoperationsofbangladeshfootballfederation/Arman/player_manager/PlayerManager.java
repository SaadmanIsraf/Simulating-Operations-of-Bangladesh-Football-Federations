package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_manager;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {

    private static final List<Player> playerList = new ArrayList<>();
    private static final String FILE_NAME = "players.bin";

    static {
        loadFromFile();
    }

    public static List<Player> getPlayerList() {
        return playerList;
    }

    public static void addPlayer(Player player) {
        playerList.add(player);
    }

    @SuppressWarnings("unchecked")
    private static void loadFromFile() {

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            playerList.clear();
            playerList.addAll((ArrayList<Player>) inputStream.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load player data.");
        }
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            ArrayList<Player> savedPlayers = new ArrayList<>(playerList);
            outputStream.writeObject(savedPlayers);

        } catch (IOException e) {
            System.out.println("Could not save player data.");
        }
    }
}