package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.ManageMatch;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.ManageMatchManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class federation_managematchesController {

    @FXML
    private TextField matchIdTF;

    @FXML
    private ComboBox<String> competitionCB;

    @FXML
    private TextField homeTeamTF;

    @FXML
    private TextField awayTeamTF;

    @FXML
    private DatePicker matchDateDP;

    @FXML
    private TextField matchTimeTF;

    @FXML
    private TextField stadiumTF;

    @FXML
    private ComboBox<String> statusCB;

    @FXML
    private TableView<ManageMatch> matchTable;

    @FXML
    private TableColumn<ManageMatch, String> matchIdCol;

    @FXML
    private TableColumn<ManageMatch, String> competitionCol;

    @FXML
    private TableColumn<ManageMatch, String> homeTeamCol;

    @FXML
    private TableColumn<ManageMatch, String> awayTeamCol;

    @FXML
    private TableColumn<ManageMatch, LocalDate> dateCol;

    @FXML
    private TableColumn<ManageMatch, String> timeCol;

    @FXML
    private TableColumn<ManageMatch, String> stadiumCol;

    @FXML
    private TableColumn<ManageMatch, String> statusCol;

    @FXML
    public void initialize() {

        competitionCB.getItems().addAll(
                "Bangladesh Premier League",
                "Federation Cup",
                "Independence Cup",
                "International Friendly"
        );

        statusCB.getItems().addAll(
                "Scheduled",
                "Live",
                "Completed",
                "Postponed",
                "Cancelled"
        );

        matchIdCol.setCellValueFactory(
                new PropertyValueFactory<>("matchId"));

        competitionCol.setCellValueFactory(
                new PropertyValueFactory<>("competition"));

        homeTeamCol.setCellValueFactory(
                new PropertyValueFactory<>("homeTeam"));

        awayTeamCol.setCellValueFactory(
                new PropertyValueFactory<>("awayTeam"));

        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("matchDate"));

        timeCol.setCellValueFactory(
                new PropertyValueFactory<>("matchTime"));

        stadiumCol.setCellValueFactory(
                new PropertyValueFactory<>("stadium"));

        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("matchStatus"));

        loadMatches();
    }

    private void loadMatches() {

        ManageMatchManager.loadFromFile();

        matchTable.getItems().setAll(
                ManageMatchManager.getMatchList());

        matchTable.refresh();
    }

    @FXML
    public void addButtonOnAction(ActionEvent actionEvent) {

        String matchId = matchIdTF.getText().trim();
        String competition = competitionCB.getValue();
        String homeTeam = homeTeamTF.getText().trim();
        String awayTeam = awayTeamTF.getText().trim();
        LocalDate matchDate = matchDateDP.getValue();
        String matchTime = matchTimeTF.getText().trim();
        String stadium = stadiumTF.getText().trim();
        String status = statusCB.getValue();

        if (matchId.isEmpty()
                || competition == null
                || homeTeam.isEmpty()
                || awayTeam.isEmpty()
                || matchDate == null
                || matchTime.isEmpty()
                || stadium.isEmpty()
                || status == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Information",
                    "Please fill in all fields."
            );

            return;
        }

        ManageMatch manageMatch = new ManageMatch(
                matchId,
                competition,
                homeTeam,
                awayTeam,
                matchDate,
                matchTime,
                stadium,
                status
        );

        ManageMatchManager.addMatch(manageMatch);
        ManageMatchManager.saveToFile();

        loadMatches();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Success",
                "Match added successfully."
        );
    }
    @FXML
    public void clearButtonOnAction(ActionEvent actionEvent) {

        clearFields();
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/federation_administrator/federation_dashboard.fxml"
        );
    }

    private void clearFields() {

        matchIdTF.clear();
        competitionCB.setValue(null);
        homeTeamTF.clear();
        awayTeamTF.clear();
        matchDateDP.setValue(null);
        matchTimeTF.clear();
        stadiumTF.clear();
        statusCB.setValue(null);

        matchTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType alertType,
                           String title,
                           String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}