package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class PlayerTransferManager {

    private static final String FILE_NAME = "playertransfer.bin";

    private static ArrayList<PlayerTransfer> playerTransferList =
            new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<PlayerTransfer> getPlayerTransferList() {
        return playerTransferList;
    }

    public static void addPlayerTransfer(PlayerTransfer playerTransfer) {
        playerTransferList.add(playerTransfer);
    }

    public static void removePlayerTransfer(PlayerTransfer playerTransfer) {
        playerTransferList.remove(playerTransfer);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(playerTransferList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            playerTransferList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                playerTransferList =
                        (ArrayList<PlayerTransfer>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            playerTransferList = new ArrayList<>();
        }
    }
}
