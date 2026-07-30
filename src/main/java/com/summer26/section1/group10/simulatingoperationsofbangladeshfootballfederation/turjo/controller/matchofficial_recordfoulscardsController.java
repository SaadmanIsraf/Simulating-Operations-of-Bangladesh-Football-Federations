package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class matchofficial_recordfoulscardsController {

    @FXML
    private TextField foulTypeTF;

    @FXML
    private ComboBox<String> cardTypeCB;

    @FXML
    private TextArea noteTA;

    @FXML
    private ComboBox<String> matchCB;

    @FXML
    private TextField minuteTF;

    @FXML
    private ComboBox<String> eventTypeCB;

    @FXML
    private TextField playerNameTF;

    @FXML
    public void initialize() {

        matchCB.setItems(
                FXCollections.observableArrayList(
                        "Abahani Ltd vs Bashundhara Kings",
                        "Mohammedan SC vs Sheikh Russel",
                        "Police FC vs Brothers Union",
                        "Fortis FC vs Rahmatganj"
                )
        );

        eventTypeCB.setItems(
                FXCollections.observableArrayList(
                        "Foul",
                        "Yellow Card",
                        "Red Card",
                        "Second Yellow Card"
                )
        );

        cardTypeCB.setItems(
                FXCollections.observableArrayList(
                        "No Card",
                        "Yellow Card",
                        "Red Card"
                )
        );

        eventTypeCB.setValue("Foul");
        cardTypeCB.setValue("No Card");
    }

    @FXML
    public void saveRecordOA(ActionEvent actionEvent) {

        if (matchCB.getValue() == null
                || playerNameTF.getText().isEmpty()
                || minuteTF.getText().isEmpty()
                || foulTypeTF.getText().isEmpty()) {

            showAlert(
                    "Error",
                    "Please complete all required fields."
            );

            return;
        }

        String record =
                "Match: " + matchCB.getValue()
                        + "\nPlayer: " + playerNameTF.getText()
                        + "\nMinute: " + minuteTF.getText()
                        + "\nEvent: " + eventTypeCB.getValue()
                        + "\nCard: " + cardTypeCB.getValue()
                        + "\nFoul: " + foulTypeTF.getText();

        showAlert(
                "Saved",
                "Foul/Card record saved successfully.\n\n" + record
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

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        clearFields();

        showAlert(
                "Cleared",
                "All fields have been cleared."
        );
    }

    private void clearFields() {

        playerNameTF.clear();
        minuteTF.clear();
        foulTypeTF.clear();
        noteTA.clear();

        matchCB.setValue(null);
        eventTypeCB.setValue("Foul");
        cardTypeCB.setValue("No Card");
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}