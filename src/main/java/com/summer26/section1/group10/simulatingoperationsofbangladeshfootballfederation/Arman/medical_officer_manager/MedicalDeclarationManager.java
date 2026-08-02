package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MedicalDeclaration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalDeclarationManager {

    private static final List<MedicalDeclaration> declarationList = new ArrayList<>();

    private static final String FILE_NAME = "medical-declarations.bin";

    static {
        loadFromFile();
    }

    public static List<MedicalDeclaration> getDeclarationList() {
        return declarationList;
    }

    public static void addDeclaration(MedicalDeclaration declaration) {
        declarationList.add(declaration);
    }

    private static void loadFromFile() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            declarationList.clear();
            declarationList.addAll((ArrayList<MedicalDeclaration>) in.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load medical declarations.");
        }
    }

    public static void saveToFile() {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            ArrayList<MedicalDeclaration> tempList =
                    new ArrayList<>(declarationList);

            out.writeObject(tempList);

        } catch (IOException e) {
            System.out.println("Could not save medical declarations.");
        }
    }
}