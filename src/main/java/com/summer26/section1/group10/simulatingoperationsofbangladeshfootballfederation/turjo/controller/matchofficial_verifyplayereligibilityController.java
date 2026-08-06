package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.VerifyPlayerEligibility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.VerifyPlayerEligibilityManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class matchofficial_verifyplayereligibilityController {

    @FXML
    private TextField playerIdTF;
    @FXML
    private TextField playerNameTF;
    @FXML
    private TextField teamTF;
    @FXML
    private TextField yellowCardTF;
    @FXML
    private TextField redCardTF;
    @FXML
    private TextField eligibilityTF;

    @FXML
    private TableView<VerifyPlayerEligibility> playerTable;

    @FXML
    private TableColumn<VerifyPlayerEligibility, String> playerIdCol;
    @FXML
    private TableColumn<VerifyPlayerEligibility, String> playerNameCol;
    @FXML
    private TableColumn<VerifyPlayerEligibility, String> teamCol;
    @FXML
    private TableColumn<VerifyPlayerEligibility, Integer> yellowCardCol;
    @FXML
    private TableColumn<VerifyPlayerEligibility, Integer> redCardCol;
    @FXML
    private TableColumn<VerifyPlayerEligibility, String> eligibilityCol;

    @FXML
    public void initialize() {

        playerIdCol.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        playerNameCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        teamCol.setCellValueFactory(new PropertyValueFactory<>("team"));
        yellowCardCol.setCellValueFactory(new PropertyValueFactory<>("yellowCards"));
        redCardCol.setCellValueFactory(new PropertyValueFactory<>("redCards"));
        eligibilityCol.setCellValueFactory(new PropertyValueFactory<>("eligibility"));

        loadPlayers();
    }

    private void loadPlayers() {
        VerifyPlayerEligibilityManager.loadFromFile();
        playerTable.getItems().setAll(VerifyPlayerEligibilityManager.getPlayerList());
    }

    @FXML
    public void verifyButtonOnAction(ActionEvent event) {

        try {

            int yellow = Integer.parseInt(yellowCardTF.getText());
            int red = Integer.parseInt(redCardTF.getText());

            if (red >= 1 || yellow >= 2) {
                eligibilityTF.setText("Suspended");
            } else {
                eligibilityTF.setText("Eligible");
            }

        } catch (NumberFormatException e) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Yellow Cards and Red Cards must be numbers.");
        }
    }

    @FXML
    public void addButtonOnAction(ActionEvent event) {

        try {

            int yellow = Integer.parseInt(yellowCardTF.getText());
            int red = Integer.parseInt(redCardTF.getText());

            String eligibility =
                    (red >= 1 || yellow >= 2)
                            ? "Suspended"
                            : "Eligible";

            eligibilityTF.setText(eligibility);

            VerifyPlayerEligibility player =
                    new VerifyPlayerEligibility(
                            playerIdTF.getText(),
                            playerNameTF.getText(),
                            teamTF.getText(),
                            yellow,
                            red,
                            eligibility
                    );

            VerifyPlayerEligibilityManager.addPlayer(player);
            VerifyPlayerEligibilityManager.saveToFile();

            loadPlayers();
            clearFields();

            showAlert(Alert.AlertType.INFORMATION,
                    "Success",
                    "Player added successfully.");

        } catch (Exception e) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Please enter valid values.");
        }
    }

    @FXML
    public void updateButtonOnAction(ActionEvent event) {

        VerifyPlayerEligibility player =
                playerTable.getSelectionModel().getSelectedItem();

        if (player == null) {
            showAlert(Alert.AlertType.WARNING,
                    "Warning",
                    "Select a player first.");
            return;
        }

        int yellow = Integer.parseInt(yellowCardTF.getText());
        int red = Integer.parseInt(redCardTF.getText());

        String eligibility =
                (red >= 1 || yellow >= 2)
                        ? "Suspended"
                        : "Eligible";

        player.setPlayerId(playerIdTF.getText());
        player.setPlayerName(playerNameTF.getText());
        player.setTeam(teamTF.getText());
        player.setYellowCards(yellow);
        player.setRedCards(red);
        player.setEligibility(eligibility);

        VerifyPlayerEligibilityManager.saveToFile();
        playerTable.refresh();

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Player updated.");
    }

    @FXML
    public void deleteButtonOnAction(ActionEvent event) {

        VerifyPlayerEligibility player =
                playerTable.getSelectionModel().getSelectedItem();

        if (player == null) {
            return;
        }

        VerifyPlayerEligibilityManager.removePlayer(player);
        VerifyPlayerEligibilityManager.saveToFile();

        loadPlayers();
        clearFields();
    }

    @FXML
    public void clearButtonOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void tableMouseClicked() {

        VerifyPlayerEligibility player =
                playerTable.getSelectionModel().getSelectedItem();

        if (player == null) {
            return;
        }

        playerIdTF.setText(player.getPlayerId());
        playerNameTF.setText(player.getPlayerName());
        teamTF.setText(player.getTeam());
        yellowCardTF.setText(String.valueOf(player.getYellowCards()));
        redCardTF.setText(String.valueOf(player.getRedCards()));
        eligibilityTF.setText(player.getEligibility());
    }

    @FXML
    public void backButtonOnAction(ActionEvent event) {

        SceneSwitcher.switchTo(
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/matchofficialsdashboard.fxml"
        );
    }

    private void clearFields() {

        playerIdTF.clear();
        playerNameTF.clear();
        teamTF.clear();
        yellowCardTF.clear();
        redCardTF.clear();
        eligibilityTF.clear();

        playerTable.getSelectionModel().clearSelection();
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