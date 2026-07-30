package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class matchofficial_logvarreportController {

    @FXML
    private ComboBox<String> reviewTypeCB;

    @FXML
    private TextField decisionTF;

    @FXML
    private TextArea detailsTA;

    @FXML
    private DatePicker matchDayDP;

    @FXML
    private ComboBox<String> varDecisionCB;

    @FXML
    private TextField minuteTF;

    @FXML
    private TextField matchBetweenTF;

    @FXML
    private TextField playerNameTF;

    @FXML
    public void initialize() {

        reviewTypeCB.setItems(
                FXCollections.observableArrayList(
                        "Goal Review",
                        "Penalty Review",
                        "Red Card Review",
                        "Offside Review",
                        "Handball Review"
                )
        );

        varDecisionCB.setItems(
                FXCollections.observableArrayList(
                        "Decision Confirmed",
                        "Decision Overturned",
                        "No Review Required"
                )
        );

        reviewTypeCB.setValue("Goal Review");
        varDecisionCB.setValue("Decision Confirmed");
    }

    @FXML
    public void submitVARReportOA(ActionEvent actionEvent) {

        if (matchBetweenTF.getText().isEmpty()
                || playerNameTF.getText().isEmpty()
                || minuteTF.getText().isEmpty()
                || decisionTF.getText().isEmpty()
                || detailsTA.getText().isEmpty()
                || matchDayDP.getValue() == null) {

            showAlert(
                    "Error",
                    "Please fill in all required fields."
            );

            return;
        }

        String report =
                "Match: " + matchBetweenTF.getText()
                        + "\nDate: " + matchDayDP.getValue()
                        + "\nPlayer: " + playerNameTF.getText()
                        + "\nMinute: " + minuteTF.getText()
                        + "\nReview Type: " + reviewTypeCB.getValue()
                        + "\nVAR Decision: " + varDecisionCB.getValue()
                        + "\nFinal Decision: " + decisionTF.getText();

        showAlert(
                "VAR Report Submitted",
                report
        );
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        matchBetweenTF.clear();
        playerNameTF.clear();
        minuteTF.clear();
        decisionTF.clear();
        detailsTA.clear();

        matchDayDP.setValue(null);

        reviewTypeCB.setValue("Goal Review");
        varDecisionCB.setValue("Decision Confirmed");

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
    }

    private void clearFields() {

        matchBetweenTF.clear();
        playerNameTF.clear();
        minuteTF.clear();
        decisionTF.clear();
        detailsTA.clear();

        matchDayDP.setValue(null);

        reviewTypeCB.setValue("Goal Review");
        varDecisionCB.setValue("Decision Confirmed");
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}