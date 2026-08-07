package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WomenFootballController
{
    @javafx.fxml.FXML
    private TextField coachNameTF;
    @javafx.fxml.FXML
    private TableColumn<WomenFootball, String>  coachNameTC;
    @javafx.fxml.FXML
    private TableColumn<WomenFootball, String>  programNameTC;
    @javafx.fxml.FXML
    private TableView<WomenFootball> createProgramTV;
    @javafx.fxml.FXML
    private TableColumn<WomenFootball, String>  ageTC;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TextField contactNumberTF;
    @javafx.fxml.FXML
    private TableColumn<WomenFootball, String>  contactTC;
    @javafx.fxml.FXML
    private TableColumn<WomenFootball, String>  teamNameTC;
    @javafx.fxml.FXML
    private TextField programidTF;
    @javafx.fxml.FXML
    private TextField ageTF;
    @javafx.fxml.FXML
    private TextArea generateProgressTA;
    @javafx.fxml.FXML
    private TextField programNameTF;
    @javafx.fxml.FXML
    private TextField teamNameTF;
    private ObservableList<WomenFootball> programList =
            FXCollections.observableArrayList();
    @FXML
    private TableColumn<WomenFootball, String> programIdTC;

    @javafx.fxml.FXML
    public void initialize() {
        programIdTC.setCellValueFactory(
                new PropertyValueFactory<>("programId"));

        programNameTC.setCellValueFactory(
                new PropertyValueFactory<>("programName"));

        ageTC.setCellValueFactory(
                new PropertyValueFactory<>("age"));

        teamNameTC.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));

        coachNameTC.setCellValueFactory(
                new PropertyValueFactory<>("coachName"));

        contactTC.setCellValueFactory(
                new PropertyValueFactory<>("contactNumber"));

        createProgramTV.setItems(programList);

        loadPrograms();
    }

    @javafx.fxml.FXML
    public void createProgramButton(ActionEvent actionEvent) {
        String programId = programidTF.getText();
        String programName = programNameTF.getText();
        String age = ageTF.getText();
        String teamName = teamNameTF.getText();
        String coachName = coachNameTF.getText();
        String contactNumber = contactNumberTF.getText();
        // Check if any field is empty
        if (programId.isEmpty() ||
                programName.isEmpty() ||
                age.isEmpty() ||
                teamName.isEmpty() ||
                coachName.isEmpty() ||
                contactNumber.isEmpty()) {

            messageLabel.setText("Please fill in all fields.");
            return;
        }
        WomenFootball program = new WomenFootball(
                programId,
                programName,
                age,
                teamName,
                coachName,
                contactNumber
        );
        programList.add(program);
        savePrograms();
        messageLabel.setText("Program created successfully.");
        clearFields();

    }

    @javafx.fxml.FXML
    public void registerPlayerButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void registerClubButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void generateProgressButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
    }
    private void loadPrograms() {

        File file = new File("women_football.bin");

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            List<WomenFootball> savedPrograms =
                    (List<WomenFootball>) input.readObject();

            programList.setAll(savedPrograms);

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
            messageLabel.setText("Error loading program data.");
        }
    }
    private void savePrograms() {

        try (ObjectOutputStream output =
                     new ObjectOutputStream(
                             new FileOutputStream("women_football.bin"))) {

            output.writeObject(new ArrayList<>(programList));

        } catch (IOException e) {

            e.printStackTrace();
            messageLabel.setText("Error saving program data.");
        }
    }
    private void clearFields() {

        programidTF.clear();
        programNameTF.clear();
        ageTF.clear();
        teamNameTF.clear();
        coachNameTF.clear();
        contactNumberTF.clear();
    }
}