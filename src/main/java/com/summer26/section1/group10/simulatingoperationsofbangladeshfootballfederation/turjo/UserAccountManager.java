package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class UserAccountManager {

    private static final String FILE_NAME = "useraccounts.bin";

    private static ArrayList<UserAccount> userAccountList =
            new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public static void addUserAccount(UserAccount userAccount) {
        userAccountList.add(userAccount);
    }

    public static void removeUserAccount(UserAccount userAccount) {
        userAccountList.remove(userAccount);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(userAccountList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            userAccountList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                userAccountList =
                        (ArrayList<UserAccount>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            userAccountList = new ArrayList<>();
        }
    }
}
