package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class federation_administrator_manageuseraccountController {

    @FXML
    private TableColumn<?, ?> usernameCol;
    @FXML
    private TextField usernameTF;
    @FXML
    private ComboBox<String> roleCB;
    @FXML
    private TableColumn<?, ?> statusCol;
    @FXML
    private ComboBox<String> permissionCB;
    @FXML
    private TableColumn<?, ?> permissionCol;
    @FXML
    private ComboBox<String> statusCB;
    @FXML
    private TableView<?> userTable;
    @FXML
    private TableColumn<?, ?> roleCol;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {

        roleCB.getItems().addAll(
                "Administrator",
                "Manager",
                "Match Official",
                "Club Representative"
        );

        statusCB.getItems().addAll(
                "Active",
                "Blocked"
        );

        permissionCB.getItems().addAll(
                "Admin",
                "User"
        );
    }

    private boolean validateInput() {

        String username = usernameTF.getText().trim();

        if (username.isEmpty()) {
            messageLabel.setText("Username cannot be empty.");
            usernameTF.requestFocus();
            return false;
        }

        if (!username.matches("[A-Za-z0-9_ ]+")) {
            messageLabel.setText("Invalid username.");
            usernameTF.requestFocus();
            return false;
        }

        if (roleCB.getValue() == null) {
            messageLabel.setText("Select a role.");
            roleCB.requestFocus();
            return false;
        }

        if (statusCB.getValue() == null) {
            messageLabel.setText("Select account status.");
            statusCB.requestFocus();
            return false;
        }

        if (permissionCB.getValue() == null) {
            messageLabel.setText("Select permission.");
            permissionCB.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void updateUserOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("User updated successfully.");
    }

    @FXML
    public void blockUserOA(ActionEvent actionEvent) {

        if (usernameTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter username first.");
            usernameTF.requestFocus();
            return;
        }

        statusCB.setValue("Blocked");
        messageLabel.setText("User blocked successfully.");
    }

    @FXML
    public void activateUserOA(ActionEvent actionEvent) {

        if (usernameTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter username first.");
            usernameTF.requestFocus();
            return;
        }

        statusCB.setValue("Active");
        messageLabel.setText("User activated successfully.");
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        usernameTF.clear();

        roleCB.getSelectionModel().clearSelection();
        statusCB.getSelectionModel().clearSelection();
        permissionCB.getSelectionModel().clearSelection();

        messageLabel.setText("");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

    }
}