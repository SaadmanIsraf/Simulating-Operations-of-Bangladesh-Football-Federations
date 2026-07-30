package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class matchofficial_requestrefereereplacementController {

    @FXML
    private ComboBox<String> priorityCB;

    @FXML
    private TextField currentRefereeTF;

    @FXML
    private TextField matchIdTF;

    @FXML
    private TextArea detailsTA;

    @FXML
    private TextField matchIdTF1;

    @FXML
    public void initialize() {

        priorityCB.setItems(
                FXCollections.observableArrayList(
                        "Low",
                        "Medium",
                        "High",
                        "Urgent"
                )
        );

        priorityCB.setValue("Medium");
    }

    @FXML
    public void submitRequestOA(ActionEvent actionEvent) {

        if (matchIdTF.getText().isEmpty()
                || matchIdTF1.getText().isEmpty()
                || currentRefereeTF.getText().isEmpty()
                || detailsTA.getText().isEmpty()
                || priorityCB.getValue() == null) {

            showAlert(
                    "Error",
                    "Please complete all request details."
            );

            return;
        }

        String request =
                "Match ID: " + matchIdTF.getText()
                        + "\nReplacement Referee ID: " + matchIdTF1.getText()
                        + "\nCurrent Referee: " + currentRefereeTF.getText()
                        + "\nPriority: " + priorityCB.getValue()
                        + "\nReason: " + detailsTA.getText();

        showAlert(
                "Request Submitted",
                "Referee replacement request submitted successfully.\n\n"
                        + request
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

        matchIdTF.clear();
        matchIdTF1.clear();
        currentRefereeTF.clear();
        detailsTA.clear();

        priorityCB.setValue("Medium");
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}