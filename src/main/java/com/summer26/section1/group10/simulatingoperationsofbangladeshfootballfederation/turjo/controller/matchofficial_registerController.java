package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class matchofficial_registerController {

    @FXML
    private TextField nameTF;

    @FXML
    private ComboBox<String> roleCB;

    @FXML
    private TextField licenseTF;

    @FXML
    private ComboBox<String> experienceCB;

    @FXML
    public void initialize() {

        roleCB.setItems(
                FXCollections.observableArrayList(
                        "Referee",
                        "Assistant Referee",
                        "Fourth Official",
                        "VAR Official"
                )
        );

        experienceCB.setItems(
                FXCollections.observableArrayList(
                        "0-2 Years",
                        "3-5 Years",
                        "6-10 Years",
                        "10+ Years"
                )
        );

        roleCB.setValue("Referee");
        experienceCB.setValue("0-2 Years");
    }

    @FXML
    public void registerOA(ActionEvent actionEvent) {

        if (nameTF.getText().isEmpty()
                || licenseTF.getText().isEmpty()
                || roleCB.getValue() == null
                || experienceCB.getValue() == null) {

            showAlert(
                    "Error",
                    "Please fill in all registration details."
            );

            return;
        }

        String registration =
                "Name: " + nameTF.getText()
                        + "\nRole: " + roleCB.getValue()
                        + "\nLicense No: " + licenseTF.getText()
                        + "\nExperience: " + experienceCB.getValue();

        showAlert(
                "Registration Successful",
                registration
        );
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        clearFields();

        showAlert(
                "Cleared",
                "All fields have been cleared."
        );
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

        clearFields();

        showAlert(
                "Back",
                "Returning to previous page."
        );
    }

    private void clearFields() {

        nameTF.clear();
        licenseTF.clear();

        roleCB.setValue("Referee");
        experienceCB.setValue("0-2 Years");
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}