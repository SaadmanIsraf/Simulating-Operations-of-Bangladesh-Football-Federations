package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.SubmitMatchReport;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.SubmitMatchReportManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class matchofficial_submitmatchreportController {

    @FXML
    private TextField matchIdTF;
    @FXML
    private TextField homeTeamTF;
    @FXML
    private TextField awayTeamTF;
    @FXML
    private DatePicker matchDateDP;
    @FXML
    private TextField scoreTF;
    @FXML
    private TextField goalScorersTF;
    @FXML
    private TextField yellowCardsTF;
    @FXML
    private TextField redCardsTF;
    @FXML
    private ComboBox<String> statusCB;
    @FXML
    private TextArea summaryTA;

    @FXML
    private TableView<SubmitMatchReport> matchReportTable;

    @FXML
    private TableColumn<SubmitMatchReport, String> matchIdCol;
    @FXML
    private TableColumn<SubmitMatchReport, String> homeTeamCol;
    @FXML
    private TableColumn<SubmitMatchReport, String> awayTeamCol;
    @FXML
    private TableColumn<SubmitMatchReport, java.time.LocalDate> dateCol;
    @FXML
    private TableColumn<SubmitMatchReport, String> scoreCol;
    @FXML
    private TableColumn<SubmitMatchReport, String> goalScorersCol;
    @FXML
    private TableColumn<SubmitMatchReport, Integer> yellowCardsCol;
    @FXML
    private TableColumn<SubmitMatchReport, Integer> redCardsCol;
    @FXML
    private TableColumn<SubmitMatchReport, String> statusCol;

    @FXML
    public void initialize() {

        statusCB.getItems().addAll(
                "Completed",
                "Abandoned",
                "Postponed"
        );

        matchIdCol.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        homeTeamCol.setCellValueFactory(new PropertyValueFactory<>("homeTeam"));
        awayTeamCol.setCellValueFactory(new PropertyValueFactory<>("awayTeam"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("matchDate"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        goalScorersCol.setCellValueFactory(new PropertyValueFactory<>("goalScorers"));
        yellowCardsCol.setCellValueFactory(new PropertyValueFactory<>("yellowCards"));
        redCardsCol.setCellValueFactory(new PropertyValueFactory<>("redCards"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadReports();
    }

    private void loadReports() {

        SubmitMatchReportManager.loadFromFile();

        matchReportTable.getItems().setAll(
                SubmitMatchReportManager.getReportList());

        matchReportTable.refresh();
    }

    @FXML
    public void submitButtonOnAction(ActionEvent event) {

        if (matchIdTF.getText().isEmpty()
                || homeTeamTF.getText().isEmpty()
                || awayTeamTF.getText().isEmpty()
                || matchDateDP.getValue() == null
                || scoreTF.getText().isEmpty()
                || goalScorersTF.getText().isEmpty()
                || yellowCardsTF.getText().isEmpty()
                || redCardsTF.getText().isEmpty()
                || statusCB.getValue() == null
                || summaryTA.getText().isEmpty()) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Please fill all fields.");

            return;
        }

        SubmitMatchReport report =
                new SubmitMatchReport(
                        matchIdTF.getText(),
                        homeTeamTF.getText(),
                        awayTeamTF.getText(),
                        matchDateDP.getValue(),
                        scoreTF.getText(),
                        goalScorersTF.getText(),
                        Integer.parseInt(yellowCardsTF.getText()),
                        Integer.parseInt(redCardsTF.getText()),
                        statusCB.getValue(),
                        summaryTA.getText()
                );

        SubmitMatchReportManager.addReport(report);
        SubmitMatchReportManager.saveToFile();

        loadReports();
        clearFields();

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Match report submitted successfully.");
    }
    @FXML
    public void updateButtonOnAction(ActionEvent event) {

        SubmitMatchReport report =
                matchReportTable.getSelectionModel().getSelectedItem();

        if (report == null) {

            showAlert(Alert.AlertType.WARNING,
                    "Warning",
                    "Please select a report.");

            return;
        }

        report.setMatchId(matchIdTF.getText());
        report.setHomeTeam(homeTeamTF.getText());
        report.setAwayTeam(awayTeamTF.getText());
        report.setMatchDate(matchDateDP.getValue());
        report.setScore(scoreTF.getText());
        report.setGoalScorers(goalScorersTF.getText());
        report.setYellowCards(Integer.parseInt(yellowCardsTF.getText()));
        report.setRedCards(Integer.parseInt(redCardsTF.getText()));
        report.setStatus(statusCB.getValue());
        report.setSummary(summaryTA.getText());

        SubmitMatchReportManager.saveToFile();

        matchReportTable.refresh();

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Match report updated successfully.");
    }

    @FXML
    public void deleteButtonOnAction(ActionEvent event) {

        SubmitMatchReport report =
                matchReportTable.getSelectionModel().getSelectedItem();

        if (report == null) {

            showAlert(Alert.AlertType.WARNING,
                    "Warning",
                    "Please select a report.");

            return;
        }

        SubmitMatchReportManager.removeReport(report);
        SubmitMatchReportManager.saveToFile();

        loadReports();
        clearFields();

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Match report deleted successfully.");
    }

    @FXML
    public void clearButtonOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void tableMouseClicked() {

        SubmitMatchReport report =
                matchReportTable.getSelectionModel().getSelectedItem();

        if (report == null) {
            return;
        }

        matchIdTF.setText(report.getMatchId());
        homeTeamTF.setText(report.getHomeTeam());
        awayTeamTF.setText(report.getAwayTeam());
        matchDateDP.setValue(report.getMatchDate());
        scoreTF.setText(report.getScore());
        goalScorersTF.setText(report.getGoalScorers());
        yellowCardsTF.setText(String.valueOf(report.getYellowCards()));
        redCardsTF.setText(String.valueOf(report.getRedCards()));
        statusCB.setValue(report.getStatus());
        summaryTA.setText(report.getSummary());
    }

    @FXML
    public void backButtonOnAction(ActionEvent event) {

        SceneSwitcher.switchTo(
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/matchofficialsdashboard.fxml"
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

    private void showAlert(Alert.AlertType type,
                           String title,
                           String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

}