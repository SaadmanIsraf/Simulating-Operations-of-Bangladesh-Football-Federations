package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.SubmitMatchReport;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.SubmitMatchReportManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class matchofficial_submitmatchreportController {

    @FXML
    private TextField scoreTF;

    @FXML
    private TextField goalScorersTF;

    @FXML
    private ComboBox<String> statusCB;

    @FXML
    private TextArea summaryTA;

    @FXML
    private TableColumn<SubmitMatchReport, String> scoreCol;

    @FXML
    private TableColumn<SubmitMatchReport, String> statusCol;
    @FXML
    private TableView matchReportsTable;
    @FXML
    private TextField matchBetweenTF;
    @FXML
    private Label messageLabel;
    @FXML
    private TextField cardsTF;
    @FXML
    private TableColumn cardsCol;
    @FXML
    private TableColumn matchBetweenCol;
    @FXML
    private TableColumn goalScorersCol;

    @FXML
    public void initialize() {

        statusCB.getItems().addAll(
                "Completed",
                "Postponed",
                "Abandoned"
        );

        matchIdCol.setCellValueFactory(
                new PropertyValueFactory<>("matchId"));

        homeTeamCol.setCellValueFactory(
                new PropertyValueFactory<>("homeTeam"));

        awayTeamCol.setCellValueFactory(
                new PropertyValueFactory<>("awayTeam"));

        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("matchDate"));

        scoreCol.setCellValueFactory(
                new PropertyValueFactory<>("finalScore"));

        yellowCardsCol.setCellValueFactory(
                new PropertyValueFactory<>("yellowCards"));

        redCardsCol.setCellValueFactory(
                new PropertyValueFactory<>("redCards"));

        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("matchStatus"));

        loadMatchReports();
    }

    private void loadMatchReports() {

        SubmitMatchReportManager.loadFromFile();

        matchReportTable.getItems().setAll(
                SubmitMatchReportManager.getMatchReportList());

        matchReportTable.refresh();
    }

    @Deprecated
    public void submitButtonOnAction(ActionEvent actionEvent) {

        String matchId = matchIdTF.getText().trim();
        String homeTeam = homeTeamTF.getText().trim();
        String awayTeam = awayTeamTF.getText().trim();
        LocalDate matchDate = matchDateDP.getValue();
        String finalScore = scoreTF.getText().trim();
        String goalScorers = goalScorersTF.getText().trim();
        String yellowText = yellowCardsTF.getText().trim();
        String redText = redCardsTF.getText().trim();
        String status = statusCB.getValue();
        String summary = summaryTA.getText().trim();

        if (matchId.isEmpty()
                || homeTeam.isEmpty()
                || awayTeam.isEmpty()
                || matchDate == null
                || finalScore.isEmpty()
                || goalScorers.isEmpty()
                || yellowText.isEmpty()
                || redText.isEmpty()
                || status == null
                || summary.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Information",
                    "Please fill in all fields."
            );

            return;
        }

        int yellowCards;
        int redCards;

        try {

            yellowCards = Integer.parseInt(yellowText);
            redCards = Integer.parseInt(redText);

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Yellow Cards and Red Cards must be numbers."
            );

            return;
        }

        SubmitMatchReport matchReport =
                new SubmitMatchReport(
                        matchId,
                        homeTeam,
                        awayTeam,
                        matchDate,
                        finalScore,
                        goalScorers,
                        yellowCards,
                        redCards,
                        summary,
                        status
                );

        SubmitMatchReportManager.addMatchReport(matchReport);
        SubmitMatchReportManager.saveToFile();

        loadMatchReports();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Success",
                "Match report submitted successfully."
        );
    }
    @Deprecated
    public void clearButtonOnAction(ActionEvent actionEvent) {

        clearFields();
    }

    @Deprecated
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/matchofficial/matchofficial_dashboard.fxml"
        );
    }

    private void clearFields() {

        matchIdTF.clear();
        homeTeamTF.clear();
        awayTeamTF.clear();
        matchDateDP.setValue(null);
        scoreTF.clear();
        goalScorersTF.clear();
        yellowCardsTF.clear();
        redCardsTF.clear();
        statusCB.setValue(null);
        summaryTA.clear();

        matchReportTable.getSelectionModel().clearSelection();
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

    @FXML
    public void submitReportOA(ActionEvent actionEvent) {
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {
    }
}