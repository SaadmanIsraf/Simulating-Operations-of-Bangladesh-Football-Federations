package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class matchofficial_logvarreportController {

    @FXML
    private TableView<?> varReportsTable;
    @FXML
    private TableColumn<?, ?> matchDayCol;
    @FXML
    private ComboBox<String> reviewTypeCB;
    @FXML
    private TextField decisionTF;
    @FXML
    private TableColumn<?, ?> minuteCol;
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
    private TableColumn<?, ?> reviewTypeCol;
    @FXML
    private TableColumn<?, ?> varDecisionCol;
    @FXML
    private TableColumn<?, ?> matchBetweenCol;
    @FXML
    private TableColumn<?, ?> playerNameCol;
    @FXML
    private TableColumn<?, ?> finalDecisionCol;
    @FXML
    private TextField playerNameTF;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {

        reviewTypeCB.getItems().addAll(
                "Goal Review",
                "Penalty Review",
                "Red Card Review",
                "Offside Review"
        );

        varDecisionCB.getItems().addAll(
                "Confirmed",
                "Overturned"
        );
    }

    private boolean validateInput() {

        if (matchDayDP.getValue() == null) {
            messageLabel.setText("Select match day.");
            matchDayDP.requestFocus();
            return false;
        }

        if (matchBetweenTF.getText().trim().isEmpty()) {
            messageLabel.setText("Match name cannot be empty.");
            matchBetweenTF.requestFocus();
            return false;
        }

        if (!matchBetweenTF.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Match name can contain only letters.");
            matchBetweenTF.requestFocus();
            return false;
        }

        if (minuteTF.getText().trim().isEmpty()) {
            messageLabel.setText("Minute cannot be empty.");
            minuteTF.requestFocus();
            return false;
        }

        if (!minuteTF.getText().matches("\\d+")) {
            messageLabel.setText("Minute must contain only numbers.");
            minuteTF.requestFocus();
            return false;
        }

        int minute = Integer.parseInt(minuteTF.getText());

        if (minute < 1 || minute > 120) {
            messageLabel.setText("Minute must be between 1 and 120.");
            minuteTF.requestFocus();
            return false;
        }

        if (playerNameTF.getText().trim().isEmpty()) {
            messageLabel.setText("Player name cannot be empty.");
            playerNameTF.requestFocus();
            return false;
        }

        if (!playerNameTF.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Player name can contain only letters.");
            playerNameTF.requestFocus();
            return false;
        }

        if (reviewTypeCB.getValue() == null) {
            messageLabel.setText("Select review type.");
            reviewTypeCB.requestFocus();
            return false;
        }

        if (varDecisionCB.getValue() == null) {
            messageLabel.setText("Select VAR decision.");
            varDecisionCB.requestFocus();
            return false;
        }

        if (decisionTF.getText().trim().isEmpty()) {
            messageLabel.setText("Final decision cannot be empty.");
            decisionTF.requestFocus();
            return false;
        }

        if (!decisionTF.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Final decision can contain only letters.");
            decisionTF.requestFocus();
            return false;
        }

        if (detailsTA.getText().trim().isEmpty()) {
            messageLabel.setText("Details cannot be empty.");
            detailsTA.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void submitVARReportOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("VAR report submitted successfully.");
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        matchDayDP.setValue(null);
        matchBetweenTF.clear();
        minuteTF.clear();
        playerNameTF.clear();
        decisionTF.clear();
        detailsTA.clear();

        reviewTypeCB.getSelectionModel().clearSelection();
        varDecisionCB.getSelectionModel().clearSelection();

        messageLabel.setText("");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

    }
}