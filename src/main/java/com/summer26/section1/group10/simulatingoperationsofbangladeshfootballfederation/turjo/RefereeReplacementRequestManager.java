package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class RefereeReplacementRequestManager {

    private static final String FILE_NAME = "refereereplacementrequests.bin";

    private static ArrayList<RefereeReplacementRequest> requestList =
            new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<RefereeReplacementRequest> getRequestList() {
        return requestList;
    }

    public static void addRequest(RefereeReplacementRequest request) {
        requestList.add(request);
    }

    public static void removeRequest(RefereeReplacementRequest request) {
        requestList.remove(request);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(requestList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            requestList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                requestList =
                        (ArrayList<RefereeReplacementRequest>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            requestList = new ArrayList<>();
        }
    }
}