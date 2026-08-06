package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.*;
import java.util.ArrayList;

public class MatchHighlightManager {

    private static final String FILE_NAME = "matchhighlights.bin";

    private static ArrayList<MatchHighlight> highlightList =
            new ArrayList<>();

    static {
        loadFromFile();
    }

    public static ArrayList<MatchHighlight> getHighlightList() {
        return highlightList;
    }

    public static void addHighlight(MatchHighlight highlight) {
        highlightList.add(highlight);
    }

    public static void removeHighlight(MatchHighlight highlight) {
        highlightList.remove(highlight);
    }

    public static void saveToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            outputStream.writeObject(highlightList);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            highlightList = new ArrayList<>();
            return;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                highlightList = (ArrayList<MatchHighlight>) object;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            highlightList = new ArrayList<>();
        }
    }
}
