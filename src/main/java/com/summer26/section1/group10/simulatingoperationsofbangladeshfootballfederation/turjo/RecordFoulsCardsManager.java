package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class RecordFoulsCardsManager {

    private static final String FILE_NAME = "recordfoulscards.bin";

    private static ArrayList<matchofficial_recordfoulscards> recordList = new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<matchofficial_recordfoulscards> getRecordList() {
        return recordList;
    }

    public static void addRecord(matchofficial_recordfoulscards record) {
        recordList.add(record);
    }

    public static void removeRecord(matchofficial_recordfoulscards record) {
        recordList.remove(record);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(recordList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            recordList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            recordList = (ArrayList<matchofficial_recordfoulscards>) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {

            recordList = new ArrayList<>();
            e.printStackTrace();
        }
    }
}
