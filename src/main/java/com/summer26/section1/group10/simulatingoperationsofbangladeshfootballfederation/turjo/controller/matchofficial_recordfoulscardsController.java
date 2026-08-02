package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class matchofficial_recordfoulscardsController {

    @FXML
    private TextField foulTypeTF;
    @FXML
    private TableColumn<?, ?> eventTypeCol;
    @FXML
    private TableColumn<?, ?> cardTypeCol;
    @FXML
    private TableColumn<?, ?> minuteCol;
    @FXML
    private TableColumn<?, ?> matchCol;
    @FXML
    private TextField minuteTF;
    @FXML
    private ComboBox<String> eventTypeCB;
    @FXML
    private TableView<?> foulsCardsTable;
    @FXML
    private ComboBox<String> cardTypeCB;
    @FXML
    private TableColumn<?, ?> playerNameCol;
    @FXML
    private TextArea noteTA;
    @FXML
    private ComboBox<String> matchCB;
    @FXML
    private TableColumn<?, ?> foulTypeCol;
    @FXML
    private TextField playerNameTF;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {

        eventTypeCB.getItems().addAll(
                "Foul",
                "Yellow Card",
                "Red Card"
        );

        cardTypeCB.getItems().addAll(
                "No Card",
                "Yellow",
                "Red"
        );

        matchCB.getItems().addAll(
                "Bangladesh vs India",
                "Bangladesh vs Nepal",
                "Bangladesh vs Maldives"
        );
    }

    private boolean validateInput() {

        if (matchCB.getValue() == null) {
            messageLabel.setText("Select a match.");
            matchCB.requestFocus();
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

        if (eventTypeCB.getValue() == null) {
            messageLabel.setText("Select event type.");
            eventTypeCB.requestFocus();
            return false;
        }

        if (cardTypeCB.getValue() == null) {
            messageLabel.setText("Select card type.");
            cardTypeCB.requestFocus();
            return false;
        }

        if (foulTypeTF.getText().trim().isEmpty()) {
            messageLabel.setText("Foul type cannot be empty.");
            foulTypeTF.requestFocus();
            return false;
        }

        if (!foulTypeTF.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Foul type can contain only letters.");
            foulTypeTF.requestFocus();
            return false;
        }

        if (noteTA.getText().trim().isEmpty()) {
            messageLabel.setText("Notes cannot be empty.");
            noteTA.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void saveRecordOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("Foul/Card record saved successfully.");
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        matchCB.getSelectionModel().clearSelection();
        eventTypeCB.getSelectionModel().clearSelection();
        cardTypeCB.getSelectionModel().clearSelection();

        playerNameTF.clear();
        minuteTF.clear();
        foulTypeTF.clear();
        noteTA.clear();

        messageLabel.setText("");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

    }
}