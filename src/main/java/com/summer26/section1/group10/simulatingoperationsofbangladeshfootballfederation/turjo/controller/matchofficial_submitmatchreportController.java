package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class matchofficial_submitmatchreportController {

    @FXML
    private TextField cardsTF;

    @FXML
    private TextField goalScorersTF;

    @FXML
    private ComboBox<String> statusCB;

    @FXML
    private TextArea summaryTA;

    @FXML
    private TextField scoreTF;

    @FXML
    private TextField matchBetweenTF;

    @FXML
    public void initialize() {

        statusCB.setItems(
                FXCollections.observableArrayList(
                        "Completed",
                        "Postponed",
                        "Abandoned",
                        "Cancelled"
                )
        );

        statusCB.setValue("Completed");
    }

    @FXML
    public void submitReportOA(ActionEvent actionEvent) {

        if (matchBetweenTF.getText().isEmpty()
                || scoreTF.getText().isEmpty()
                || goalScorersTF.getText().isEmpty()
                || cardsTF.getText().isEmpty()
                || summaryTA.getText().isEmpty()
                || statusCB.getValue() == null) {

            showAlert(
                    "Error",
                    "Please fill in all match report details."
            );

            return;
        }

        String report =
                "Match: " + matchBetweenTF.getText()
                        + "\nScore: " + scoreTF.getText()
                        + "\nGoal Scorers: " + goalScorersTF.getText()
                        + "\nCards: " + cardsTF.getText()
                        + "\nStatus: " + statusCB.getValue()
                        + "\nSummary: " + summaryTA.getText();

        showAlert(
                "Report Submitted",
                "Match report submitted successfully.\n\n" + report
        );
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        clearFields();

        showAlert(
                "Cleared",
                "All fields have been cleared."
        );
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

        clearFields();

        showAlert(
                "Back",
                "Returning to previous page."
        );

        SceneSwitcher.switchTo(
                "turjo/match_official/matchofficialsdashboard.fxml"
        );
    }

    private void clearFields() {

        matchBetweenTF.clear();
        scoreTF.clear();
        goalScorersTF.clear();
        cardsTF.clear();
        summaryTA.clear();

        statusCB.setValue("Completed");
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}