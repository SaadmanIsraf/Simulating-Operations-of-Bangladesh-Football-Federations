package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class matchofficial_registerController {

    @FXML
    private TableColumn<?, ?> licenseCol;
    @FXML
    private TextField nameTF;
    @FXML
    private ComboBox<String> roleCB;
    @FXML
    private TextField licenseTF;
    @FXML
    private TableColumn<?, ?> experienceCol;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<?, ?> roleCol;
    @FXML
    private ComboBox<String> experienceCB;
    @FXML
    private TableView<?> officialsTable;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {

        roleCB.getItems().addAll(
                "Referee",
                "Assistant Referee",
                "Fourth Official",
                "VAR Official"
        );

        experienceCB.getItems().addAll(
                "1-2 Years",
                "3-5 Years",
                "6-10 Years",
                "10+ Years"
        );
    }

    private boolean validateInput() {

        if (nameTF.getText().trim().isEmpty()) {
            messageLabel.setText("Name cannot be empty.");
            nameTF.requestFocus();
            return false;
        }

        if (!nameTF.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Name can contain only letters.");
            nameTF.requestFocus();
            return false;
        }

        if (roleCB.getValue() == null) {
            messageLabel.setText("Select a role.");
            roleCB.requestFocus();
            return false;
        }

        if (licenseTF.getText().trim().isEmpty()) {
            messageLabel.setText("License number cannot be empty.");
            licenseTF.requestFocus();
            return false;
        }

        if (!licenseTF.getText().matches("[A-Za-z0-9-]+")) {
            messageLabel.setText("Invalid license number.");
            licenseTF.requestFocus();
            return false;
        }

        if (experienceCB.getValue() == null) {
            messageLabel.setText("Select experience.");
            experienceCB.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void registerOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("Official registered successfully.");
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        nameTF.clear();
        licenseTF.clear();

        roleCB.getSelectionModel().clearSelection();
        experienceCB.getSelectionModel().clearSelection();

        messageLabel.setText("");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

    }
}