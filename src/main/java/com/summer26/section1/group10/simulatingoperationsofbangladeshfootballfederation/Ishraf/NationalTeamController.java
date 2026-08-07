package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NationalTeamController
{   private ObservableList<NationalTeam> teamList =
        FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private TextField coachNameTF;
    @javafx.fxml.FXML
    private ComboBox<String> teamCategoryCB;
    @javafx.fxml.FXML
    private TableColumn<NationalTeam, String> teamIdTC;
    @javafx.fxml.FXML
    private TableColumn<NationalTeam, String> coachNameTC;
    @javafx.fxml.FXML
    private TableView<NationalTeam> addTeamTV;
    @javafx.fxml.FXML
    private TextArea teamDetailsTA;
    @javafx.fxml.FXML
    private TextField teamIdTF;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TableColumn<NationalTeam, String> teamCategoryTC;
    @javafx.fxml.FXML
    private TableColumn<NationalTeam, String> teamNameTC;
    @javafx.fxml.FXML
    private DatePicker endDateDP;
    @javafx.fxml.FXML
    private DatePicker startDateDP;
    @javafx.fxml.FXML
    private TableColumn<NationalTeam, String> startDateTC;
    @javafx.fxml.FXML
    private TableColumn<NationalTeam, String> endDateTC;
    @javafx.fxml.FXML
    private TextField teamNameTF;

    @javafx.fxml.FXML
    public void initialize() {
        teamCategoryCB.setItems(FXCollections.observableArrayList(
                "Main team",
                "Secendory team",
                "Prospect"
        ));

        teamIdTC.setCellValueFactory(
                new PropertyValueFactory<>("teamId"));

        teamNameTC.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));

        coachNameTC.setCellValueFactory(
                new PropertyValueFactory<>("coachName"));

        teamCategoryTC.setCellValueFactory(
                new PropertyValueFactory<>("teamCategory"));

        startDateTC.setCellValueFactory(
                new PropertyValueFactory<>("startDate"));

        endDateTC.setCellValueFactory(
                new PropertyValueFactory<>("endDate"));

        addTeamTV.setItems(teamList);

        loadTeams();

    }


    @javafx.fxml.FXML
    public void deleteButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void generateDevelopmentRecordButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void addTeamButton(ActionEvent actionEvent) {
        String teamId = teamIdTF.getText();
        String teamName = teamNameTF.getText();
        String coachName = coachNameTF.getText();
        String teamCategory = teamCategoryCB.getValue();

        LocalDate startDate = startDateDP.getValue();
        LocalDate endDate = endDateDP.getValue();

        if (teamId.isEmpty() ||
                teamName.isEmpty() ||
                coachName.isEmpty() ||
                teamCategory == null ||
                startDate == null ||
                endDate == null) {

            messageLabel.setText("Please fill in all fields.");
            return;
        }

        NationalTeam team = new NationalTeam(
                teamId,
                teamName,
                coachName,
                teamCategory,
                startDate,
                endDate
        );

        teamList.add(team);

        saveTeams();

        messageLabel.setText("Team added successfully.");

        clearFields();
    }
    private void clearFields() {

        teamIdTF.clear();
        teamNameTF.clear();
        coachNameTF.clear();
        teamCategoryCB.setValue(null);
        startDateDP.setValue(null);
        endDateDP.setValue(null);

    }
    //save team
    private void saveTeams() {

        try (ObjectOutputStream output =
                     new ObjectOutputStream(
                             new FileOutputStream("national_teams.bin"))) {

            output.writeObject(new ArrayList<>(teamList));

        } catch (IOException e) {

            e.printStackTrace();
            messageLabel.setText("Error saving team data.");
        }
    }
    //loadTeam
    private void loadTeams() {

        File file = new File("national_teams.bin");

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            List<NationalTeam> savedTeams =
                    (List<NationalTeam>) input.readObject();

            teamList.setAll(savedTeams);

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
            messageLabel.setText("Error loading team data.");
        }
    }



    @javafx.fxml.FXML
    public void updateButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void displayTeamButton(ActionEvent actionEvent) {
    }
}