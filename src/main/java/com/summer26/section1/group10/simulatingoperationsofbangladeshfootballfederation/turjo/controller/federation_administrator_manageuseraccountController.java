package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.UserAccount;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.federation_administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class federation_administrator_manageuseraccountController implements UserReceiver {

    @FXML
    private TableColumn<UserAccount, String> usernameCol;
    @FXML
    private TextField usernameTF;
    @FXML
    private ComboBox<String> roleCB;
    @FXML
    private TableColumn<UserAccount, String> statusCol;
    @FXML
    private ComboBox<String> permissionCB;
    @FXML
    private TableColumn<UserAccount, String> permissionCol;
    @FXML
    private ComboBox<String> statusCB;
    @FXML
    private TableView<UserAccount> userTable;
    @FXML
    private TableColumn<UserAccount, String> roleCol;
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

        usernameCol.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("name"));
        roleCol.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("role"));
        statusCol.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("status"));
        permissionCol.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("permission"));

        ArrayList<Object> userList = BinaryFileUtility.readObjects("UserAccounts.bin");
        for (Object record : userList) {
            if (record instanceof UserAccount userAccount) {
                userTable.getItems().add(userAccount);
            }
        }

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

        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                usernameTF.setText(newVal.getName());
                roleCB.setValue(newVal.getRole());
                statusCB.setValue(newVal.getStatus());
                permissionCB.setValue(newVal.getPermission());
            }
        });
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
    public void addUserOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        int newId = (int) System.currentTimeMillis();
        String generatedEmail = usernameTF.getText().trim().replaceAll("\\s+", "").toLowerCase() + "@federation.local";

        UserAccount userAccount = new UserAccount(
                newId,
                usernameTF.getText(),
                generatedEmail,
                "changeme123",
                roleCB.getValue(),
                statusCB.getValue(),
                permissionCB.getValue());

        userTable.getItems().add(userAccount);
        BinaryFileUtility.writeObjects("UserAccounts.bin", userAccount);

        messageLabel.setText("User added successfully.");
    }

    @FXML
    public void updateUserOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        UserAccount selected = userTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Please select a user to update.");
            return;
        }

        selected.setName(usernameTF.getText());
        selected.setRole(roleCB.getValue());
        selected.setStatus(statusCB.getValue());
        selected.setPermission(permissionCB.getValue());

        selected.updateUser();
        userTable.refresh();

        messageLabel.setText("User updated successfully.");
    }

    @FXML
    public void blockUserOA(ActionEvent actionEvent) {

        if (usernameTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter username first.");
            usernameTF.requestFocus();
            return;
        }

        UserAccount selected = userTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Please select a user to block.");
            return;
        }

        selected.setStatus("Blocked");
        statusCB.setValue("Blocked");
        selected.updateUser();
        userTable.refresh();

        messageLabel.setText("User blocked successfully.");
    }

    @FXML
    public void activateUserOA(ActionEvent actionEvent) {

        if (usernameTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter username first.");
            usernameTF.requestFocus();
            return;
        }

        UserAccount selected = userTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Please select a user to activate.");
            return;
        }

        selected.setStatus("Active");
        statusCB.setValue("Active");
        selected.updateUser();
        userTable.refresh();

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
        SceneSwitcher.switchTo("turjo/federation_administrator/dashboardView.fxml");
    }
}