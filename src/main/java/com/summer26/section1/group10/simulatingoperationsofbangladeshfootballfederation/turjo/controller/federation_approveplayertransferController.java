package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.PlayerTransfer;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.federation_administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class federation_approveplayertransferController implements UserReceiver {

    @FXML
    private ComboBox<String> decisionCB;
    @FXML
    private TableColumn<PlayerTransfer,String > toClubCol;
    @FXML
    private TextField fromClubTF;
    @FXML
    private TableView<PlayerTransfer> transferTable;
    @FXML
    private TableColumn<PlayerTransfer, String> playerNameCol;
    @FXML
    private TextField toClubTF;
    @FXML
    private TableColumn<PlayerTransfer, String> fromClubCol;
    @FXML
    private Label messageLabel;
    @FXML
    private TableColumn<PlayerTransfer, String> decisionCol;
    @FXML
    private TextField playerNameTF;
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

        playerNameCol.setCellValueFactory(new PropertyValueFactory<PlayerTransfer,String>("playerName"));
        fromClubCol.setCellValueFactory(new PropertyValueFactory<PlayerTransfer,String>("fromclubname"));
        toClubCol.setCellValueFactory(new PropertyValueFactory<PlayerTransfer,String>("toclubname"));
        decisionCol.setCellValueFactory(new PropertyValueFactory<PlayerTransfer,String>("transdecission"));

        ArrayList<Object> transferList = BinaryFileUtility.readObjects("PlayerTransfer.bin");
        for (Object record : transferList) {
            if(record instanceof PlayerTransfer playerTransfer) {
                transferTable.getItems().add(playerTransfer);
            }
        }

        decisionCB.getItems().addAll(
                "Approved",
                "Rejected"
        );
    }

    private boolean validateInput() {

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

        if (fromClubTF.getText().trim().isEmpty()) {
            messageLabel.setText("From Club cannot be empty.");
            fromClubTF.requestFocus();
            return false;
        }

        if (!fromClubTF.getText().matches("[A-Za-z0-9 &-]+")) {
            messageLabel.setText("Invalid From Club name.");
            fromClubTF.requestFocus();
            return false;
        }

        if (toClubTF.getText().trim().isEmpty()) {
            messageLabel.setText("To Club cannot be empty.");
            toClubTF.requestFocus();
            return false;
        }

        if (!toClubTF.getText().matches("[A-Za-z0-9 &-]+")) {
            messageLabel.setText("Invalid To Club name.");
            toClubTF.requestFocus();
            return false;
        }

        if (fromClubTF.getText().equalsIgnoreCase(toClubTF.getText())) {
            messageLabel.setText("From Club and To Club cannot be the same.");
            toClubTF.requestFocus();
            return false;
        }

        if (decisionCB.getValue() == null) {
            messageLabel.setText("Please select a decision.");
            decisionCB.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void saveOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }
        PlayerTransfer playerTransfer = new PlayerTransfer(playerNameTF.getText(), fromClubTF.getText(), toClubTF.getText(), decisionCB.getValue());

        transferTable.getItems().add(playerTransfer);
        BinaryFileUtility.writeObjects("PlayerTransfer.bin", playerTransfer);

        messageLabel.setText("Player transfer saved successfully.");
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        playerNameTF.clear();
        fromClubTF.clear();
        toClubTF.clear();

        decisionCB.getSelectionModel().clearSelection();

        messageLabel.setText("Cleared Successfully.");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/matchofficialsdashboard.fxml");

    }
}