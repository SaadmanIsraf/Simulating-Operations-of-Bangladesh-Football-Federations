package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class matchofficial_submitmatchreportController {

    @FXML
    private TextField cardsTF;
    @FXML
    private TableColumn<?, ?> scoreCol;
    @FXML
    private TableView<?> matchReportsTable;
    @FXML
    private TableColumn<?, ?> cardsCol;
    @FXML
    private TableColumn<?, ?> statusCol;
    @FXML
    private TextField goalScorersTF;
    @FXML
    private TableColumn<?, ?> matchBetweenCol;
    @FXML
    private ComboBox<String> statusCB;
    @FXML
    private TextArea summaryTA;
    @FXML
    private TextField scoreTF;
    @FXML
    private TableColumn<?, ?> goalScorersCol;
    @FXML
    private TextField matchBetweenTF;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {

        statusCB.getItems().addAll(
                "Completed",
                "Abandoned",
                "Postponed"
        );
    }

    private boolean validateInput() {

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

        if (scoreTF.getText().trim().isEmpty()) {
            messageLabel.setText("Score cannot be empty.");
            scoreTF.requestFocus();
            return false;
        }

        if (!scoreTF.getText().matches("\\d+-\\d+")) {
            messageLabel.setText("Score must be in format 2-1.");
            scoreTF.requestFocus();
            return false;
        }

        if (goalScorersTF.getText().trim().isEmpty()) {
            messageLabel.setText("Goal scorers cannot be empty.");
            goalScorersTF.requestFocus();
            return false;
        }

        if (cardsTF.getText().trim().isEmpty()) {
            messageLabel.setText("Cards field cannot be empty.");
            cardsTF.requestFocus();
            return false;
        }

        if (!cardsTF.getText().matches("\\d+")) {
            messageLabel.setText("Cards must contain only numbers.");
            cardsTF.requestFocus();
            return false;
        }

        if (statusCB.getValue() == null) {
            messageLabel.setText("Select match status.");
            statusCB.requestFocus();
            return false;
        }

        if (summaryTA.getText().trim().isEmpty()) {
            messageLabel.setText("Summary cannot be empty.");
            summaryTA.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void submitReportOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("Match report submitted successfully.");
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        matchBetweenTF.clear();
        scoreTF.clear();
        goalScorersTF.clear();
        cardsTF.clear();
        summaryTA.clear();

        statusCB.getSelectionModel().clearSelection();

        messageLabel.setText("");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

    }
}