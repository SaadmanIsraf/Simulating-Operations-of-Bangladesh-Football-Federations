package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.UserAccount;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.UserAccountManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class federation_manageuseraccountController {

    @FXML
    private TextField usernameTF;

    @FXML
    private ComboBox<String> roleCB;

    @FXML
    private ComboBox<String> statusCB;

    @FXML
    private TableView<UserAccount> userTable;

    @FXML
    private TableColumn<UserAccount, String> usernameCol;

    @FXML
    private TableColumn<UserAccount, String> roleCol;

    @FXML
    private TableColumn<UserAccount, String> statusCol;

    @FXML
    public void initialize() {

        roleCB.getItems().addAll(
                "Federation Administrator",
                "Match Official",
                "Club Manager",
                "Team Doctor"
        );

        statusCB.getItems().addAll(
                "Active",
                "Inactive"
        );

        usernameCol.setCellValueFactory(
                new PropertyValueFactory<>("username"));

        roleCol.setCellValueFactory(
                new PropertyValueFactory<>("role"));

        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("status"));

        loadUsers();
    }

    private void loadUsers() {

        UserAccountManager.loadFromFile();

        userTable.getItems().setAll(
                UserAccountManager.getUserAccountList());

        userTable.refresh();
    }

    @FXML
    public void addButtonOnAction(ActionEvent actionEvent) {

        String username = usernameTF.getText().trim();
        String role = roleCB.getValue();
        String status = statusCB.getValue();

        if (username.isEmpty()
                || role == null
                || status == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Information",
                    "Please fill in all fields."
            );

            return;
        }

        UserAccount user = new UserAccount(
                username,
                role,
                status
        );

        UserAccountManager.addUserAccount(user);
        UserAccountManager.saveToFile();

        loadUsers();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Success",
                "User account added successfully."
        );
    }

    @FXML
    public void updateButtonOnAction(ActionEvent actionEvent) {

        UserAccount selected =
                userTable.getSelectionModel().getSelectedItem();

        if (selected == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "No Selection",
                    "Please select a user from the table."
            );

            return;
        }

        selected.setUsername(usernameTF.getText().trim());
        selected.setRole(roleCB.getValue());
        selected.setStatus(statusCB.getValue());

        UserAccountManager.saveToFile();

        loadUsers();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Updated",
                "User account updated successfully."
        );
    }
    @FXML
    public void clearButtonOnAction(ActionEvent actionEvent) {

        clearFields();
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/federation_administrator/federation_dashboard.fxml"
        );
    }

    private void clearFields() {

        usernameTF.clear();

        roleCB.setValue(null);
        statusCB.setValue(null);

        userTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType alertType,
                           String title,
                           String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}