package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class matchofficial_verifyplayereligibilityController {

    @FXML
    private ComboBox<String> playerCB;
    @FXML
    private TextField eligibilityTF;
    @FXML
    private Button addPlayerBtn;
    @FXML
    private TableColumn<?, ?> matchCol;
    @FXML
    private TableColumn<?, ?> eligibilityCol;
    @FXML
    private Button removePlayerBtn;
    @FXML
    private TableColumn<?, ?> yellowCardCol;
    @FXML
    private ComboBox<Integer> redCardsCB;
    @FXML
    private TableColumn<?, ?> playerNameCol;
    @FXML
    private TableColumn<?, ?> redCardCol;
    @FXML
    private ComboBox<String> matchCB;
    @FXML
    private TableView<?> playerTable;
    @FXML
    private ComboBox<Integer> yellowCardsCB;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {

        matchCB.getItems().addAll(
                "Bangladesh vs India",
                "Abahani vs Mohammedan",
                "Bashundhara Kings vs Rahmatganj"
        );

        playerCB.getItems().addAll(
                "Rakib Hossain",
                "Topu Barman",
                "Jamal Bhuyan",
                "Sohel Rana"
        );

        yellowCardsCB.getItems().addAll(0, 1, 2, 3, 4, 5);
        redCardsCB.getItems().addAll(0, 1, 2);
    }

    private boolean validateInput() {

        if (matchCB.getValue() == null) {
            messageLabel.setText("Please select a match.");
            matchCB.requestFocus();
            return false;
        }

        if (playerCB.getValue() == null) {
            messageLabel.setText("Please select a player.");
            playerCB.requestFocus();
            return false;
        }

        if (yellowCardsCB.getValue() == null) {
            messageLabel.setText("Please select yellow cards.");
            yellowCardsCB.requestFocus();
            return false;
        }

        if (redCardsCB.getValue() == null) {
            messageLabel.setText("Please select red cards.");
            redCardsCB.requestFocus();
            return false;
        }

        return true;
    }

    @FXML
    public void checkEligibilityOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        int yellow = yellowCardsCB.getValue();
        int red = redCardsCB.getValue();

        if (red >= 1 || yellow >= 2) {
            eligibilityTF.setText("Not Eligible");
        } else {
            eligibilityTF.setText("Eligible");
        }

        messageLabel.setText("Eligibility checked successfully.");
    }

    @FXML
    public void updateCardsOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("Card record updated successfully.");
    }

    @FXML
    public void addPlayerOA(ActionEvent actionEvent) {

        if (playerCB.getValue() == null) {
            messageLabel.setText("Select a player to add.");
            playerCB.requestFocus();
            return;
        }

        messageLabel.setText("Player added successfully.");
    }

    @FXML
    public void removePlayerOA(ActionEvent actionEvent) {

        if (playerCB.getValue() == null) {
            messageLabel.setText("Select a player to remove.");
            playerCB.requestFocus();
            return;
        }

        messageLabel.setText("Player removed successfully.");
    }

    @FXML
    public void viewCardsOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("Cards viewed successfully.");
    }

    @FXML
    public void refreshOA(ActionEvent actionEvent) {

        playerCB.getSelectionModel().clearSelection();
        matchCB.getSelectionModel().clearSelection();
        yellowCardsCB.getSelectionModel().clearSelection();
        redCardsCB.getSelectionModel().clearSelection();

        eligibilityTF.clear();
        messageLabel.setText("");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

    }
}