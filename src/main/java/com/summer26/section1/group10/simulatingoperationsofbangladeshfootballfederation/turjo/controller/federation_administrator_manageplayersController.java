package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.federation_administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class federation_administrator_manageplayersController implements UserReceiver {

    @FXML
    private TextField clubTF;
    @FXML
    private TableView<?> playertableview;
    @FXML
    private TextField playernameTF;
    @FXML
    private TableColumn<?, ?> injurycol;
    @FXML
    private ComboBox<String> injuryCB;
    @FXML
    private ComboBox<String> positionCB;
    @FXML
    private TableColumn<?, ?> positioncol;
    @FXML
    private TableColumn<?, ?> clubcol;
    @FXML
    private TableColumn<?, ?> playernamecol1;
    @FXML
    private TableColumn<?, ?> valuecol;
    @FXML
    private TextField valueTF;
    @FXML
    private TableColumn<?, ?> playernamecol;
    @FXML
    private Label messageLabel;
    private federation_administrator loggedInUser;
    @Override
    public void setLoggedInUser(User user){
        if (user instanceof federation_administrator f){
            loggedInUser = f;
        }
        else {
            AlertGenerator.showAlert("Error", "This is not a valid user for this page");
        }
    }

    @FXML
    public void initialize() {

        positionCB.getItems().addAll(
                "Goalkeeper",
                "Defender",
                "Midfielder",
                "Forward"
        );

        injuryCB.getItems().addAll(
                "Fit",
                "Minor Injury",
                "Major Injury"
        );
    }

    private boolean validateInput() {

        String playerName = playernameTF.getText().trim();
        String club = clubTF.getText().trim();
        String value = valueTF.getText().trim();

        if (playerName.isEmpty()) {
            messageLabel.setText("Player name cannot be empty.");
            playernameTF.requestFocus();
            return false;
        }

        if (!playerName.matches("[A-Za-z ]+")) {
            messageLabel.setText("Player name must contain only letters.");
            playernameTF.requestFocus();
            return false;
        }

        if (positionCB.getValue() == null) {
            messageLabel.setText("Select a position.");
            positionCB.requestFocus();
            return false;
        }

        if (injuryCB.getValue() == null) {
            messageLabel.setText("Select injury status.");
            injuryCB.requestFocus();
            return false;
        }

        if (club.isEmpty()) {
            messageLabel.setText("Current club cannot be empty.");
            clubTF.requestFocus();
            return false;
        }

        if (!club.matches("[A-Za-z0-9 ]+")) {
            messageLabel.setText("Invalid club name.");
            clubTF.requestFocus();
            return false;
        }

        if (value.isEmpty()) {
            messageLabel.setText("Current value cannot be empty.");
            valueTF.requestFocus();
            return false;
        }

        if (!value.matches("\\d+")) {
            messageLabel.setText("Current value must contain only numbers.");
            valueTF.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void addplayerOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("Player added successfully.");
    }

    @FXML
    public void updateplayerOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("Player updated successfully.");
    }

    @FXML
    public void saveOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("Player information saved successfully.");
    }

    @FXML
    public void refreshOA(ActionEvent actionEvent) {

        playernameTF.clear();
        clubTF.clear();
        valueTF.clear();

        positionCB.getSelectionModel().clearSelection();
        injuryCB.getSelectionModel().clearSelection();

        messageLabel.setText("Form refreshed.");
    }

    @FXML
    public void deleteplayerOA(ActionEvent actionEvent) {

        if (playernameTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter player name to delete.");
            playernameTF.requestFocus();
            return;
        }

        messageLabel.setText("Player deleted successfully.");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/federation_administrator/dashboardView.fxml");

    }
}