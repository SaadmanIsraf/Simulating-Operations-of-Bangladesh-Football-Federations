package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.PlayerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class federation_administrator_manageplayersController {

    @FXML
    private TextField playerIdTF;
    @FXML
    private TextField playerNameTF;
    @FXML
    private TextField clubTF;

    @FXML
    private ComboBox<String> positionCB;
    @FXML
    private ComboBox<String> playerTypeCB;
    @FXML
    private ComboBox<String> fitnessCB;

    @FXML
    private TableView<Player> playerTable;

    @FXML
    private TableColumn<Player,Integer> playerIdCol;
    @FXML
    private TableColumn<Player,String> playerNameCol;
    @FXML
    private TableColumn<Player,String> positionCol;
    @FXML
    private TableColumn<Player,String> clubCol;
    @FXML
    private TableColumn<Player,String> fitnessCol;

    @FXML
    public void initialize() {

        positionCB.getItems().addAll(
                "Goalkeeper",
                "Defender",
                "Midfielder",
                "Forward"
        );

        playerTypeCB.getItems().addAll(
                "Local",
                "Foreign"
        );

        fitnessCB.getItems().addAll(
                "Fit",
                "Injured",
                "Recovering"
        );

        playerIdCol.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        playerNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("playingPosition"));
        clubCol.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        fitnessCol.setCellValueFactory(new PropertyValueFactory<>("fitnessStatus"));

        loadPlayers();
    }

    private void loadPlayers() {

        PlayerManager.loadFromFile();

        playerTable.getItems().setAll(PlayerManager.getPlayerList());

        playerTable.refresh();
    }

    @FXML
    public void addButtonOnAction(ActionEvent actionEvent) {

        if (playerIdTF.getText().isEmpty()
                || playerNameTF.getText().isEmpty()
                || clubTF.getText().isEmpty()
                || positionCB.getValue() == null
                || playerTypeCB.getValue() == null
                || fitnessCB.getValue() == null) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Please fill all fields.");
            return;
        }

        Player player = new Player(
                Integer.parseInt(playerIdTF.getText()),
                playerNameTF.getText(),
                "1234",
                "Player",
                20,
                clubTF.getText(),
                positionCB.getValue(),
                playerTypeCB.getValue(),
                "",
                fitnessCB.getValue(),
                "Eligible"
        );

        PlayerManager.addPlayer(player);
        PlayerManager.saveToFile();

        loadPlayers();

        clearFields();

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Player added successfully.");
    }
    @FXML
    public void updateButtonOnAction(ActionEvent actionEvent) {

        Player selectedPlayer = playerTable.getSelectionModel().getSelectedItem();

        if (selectedPlayer == null) {
            showAlert(Alert.AlertType.WARNING,
                    "Warning",
                    "Select a player first.");
            return;
        }

        selectedPlayer.setPlayerId(Integer.parseInt(playerIdTF.getText()));
        selectedPlayer.setFullName(playerNameTF.getText());
        selectedPlayer.setTeamName(clubTF.getText());
        selectedPlayer.setPlayingPosition(positionCB.getValue());
        selectedPlayer.setPlayerType(playerTypeCB.getValue());
        selectedPlayer.setFitnessStatus(fitnessCB.getValue());

        PlayerManager.saveToFile();

        playerTable.refresh();

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Player updated successfully.");
    }

    @FXML
    public void deleteButtonOnAction(ActionEvent actionEvent) {

        Player selectedPlayer = playerTable.getSelectionModel().getSelectedItem();

        if (selectedPlayer == null) {
            showAlert(Alert.AlertType.WARNING,
                    "Warning",
                    "Select a player first.");
            return;
        }

        PlayerManager.removePlayer(selectedPlayer);
        PlayerManager.saveToFile();

        loadPlayers();

        clearFields();

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Player deleted successfully.");
    }

    @FXML
    public void clearButtonOnAction(ActionEvent actionEvent) {

        clearFields();
    }

    @Deprecated
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/federation_administrator/dashboardView.fxml"
        );
    }

    @FXML
    public void tableMouseClicked() {

        Player player = playerTable.getSelectionModel().getSelectedItem();

        if (player == null) {
            return;
        }

        playerIdTF.setText(String.valueOf(player.getPlayerId()));
        playerNameTF.setText(player.getFullName());
        clubTF.setText(player.getTeamName());

        positionCB.setValue(player.getPlayingPosition());
        playerTypeCB.setValue(player.getPlayerType());
        fitnessCB.setValue(player.getFitnessStatus());
    }

    private void clearFields() {

        playerIdTF.clear();
        playerNameTF.clear();
        clubTF.clear();

        positionCB.setValue(null);
        playerTypeCB.setValue(null);
        fitnessCB.setValue(null);

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