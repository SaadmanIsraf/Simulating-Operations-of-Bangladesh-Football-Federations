package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class federation_administrator_publishannouncementController {

    @FXML
    private TableColumn<?, ?> titleCol;
    @FXML
    private TableView<?> announcementTable;
    @FXML
    private TableColumn<?, ?> matchTypeCol;
    @FXML
    private TableColumn<?, ?> venueCol;
    @FXML
    private TextField txtMatchTime;
    @FXML
    private ComboBox<String> cmbCompetition;
    @FXML
    private TableColumn<?, ?> matchCol;
    @FXML
    private TextArea txtAnnouncementDetails;
    @FXML
    private ComboBox<String> cmbMatch;
    @FXML
    private TableColumn<?, ?> competitionCol;
    @FXML
    private TextField txtVenue;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> detailsCol;
    @FXML
    private TextField txtPlace;
    @FXML
    private TextField txtAnnouncementTitle;
    @FXML
    private DatePicker dpMatchDate;
    @FXML
    private ComboBox<String> cmbMatchType;
    @FXML
    private TextField txtSponsor;
    @FXML
    private TableColumn<?, ?> timeCol;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {

        cmbCompetition.getItems().addAll(
                "Premier League",
                "Federation Cup",
                "Independence Cup"
        );

        cmbMatch.getItems().addAll(
                "Match 101",
                "Match 102",
                "Match 103"
        );

        cmbMatchType.getItems().addAll(
                "League",
                "Friendly",
                "Knockout",
                "Final"
        );
    }

    private boolean validateInput() {

        if (txtAnnouncementTitle.getText().trim().isEmpty()) {
            messageLabel.setText("Enter announcement title.");
            txtAnnouncementTitle.requestFocus();
            return false;
        }

        if (cmbMatch.getValue() == null) {
            messageLabel.setText("Select a match.");
            cmbMatch.requestFocus();
            return false;
        }

        if (cmbCompetition.getValue() == null) {
            messageLabel.setText("Select a competition.");
            cmbCompetition.requestFocus();
            return false;
        }

        if (txtVenue.getText().trim().isEmpty()) {
            messageLabel.setText("Enter venue.");
            txtVenue.requestFocus();
            return false;
        }

        if (!txtVenue.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Venue must contain only letters.");
            txtVenue.requestFocus();
            return false;
        }

        if (txtSponsor.getText().trim().isEmpty()) {
            messageLabel.setText("Enter sponsor.");
            txtSponsor.requestFocus();
            return false;
        }

        if (!txtSponsor.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Sponsor must contain only letters.");
            txtSponsor.requestFocus();
            return false;
        }

        if (txtPlace.getText().trim().isEmpty()) {
            messageLabel.setText("Enter place.");
            txtPlace.requestFocus();
            return false;
        }

        if (!txtPlace.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Place must contain only letters.");
            txtPlace.requestFocus();
            return false;
        }

        if (dpMatchDate.getValue() == null) {
            messageLabel.setText("Select match date.");
            dpMatchDate.requestFocus();
            return false;
        }

        if (txtMatchTime.getText().trim().isEmpty()) {
            messageLabel.setText("Enter match time.");
            txtMatchTime.requestFocus();
            return false;
        }

        if (cmbMatchType.getValue() == null) {
            messageLabel.setText("Select match type.");
            cmbMatchType.requestFocus();
            return false;
        }

        if (txtAnnouncementDetails.getText().trim().isEmpty()) {
            messageLabel.setText("Enter announcement details.");
            txtAnnouncementDetails.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void publishAnnouncementOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("Announcement published successfully.");
    }

    @FXML
    public void updateAnnouncementOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("Announcement updated successfully.");
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        txtAnnouncementTitle.clear();
        txtVenue.clear();
        txtSponsor.clear();
        txtPlace.clear();
        txtMatchTime.clear();
        txtAnnouncementDetails.clear();

        cmbCompetition.getSelectionModel().clearSelection();
        cmbMatch.getSelectionModel().clearSelection();
        cmbMatchType.getSelectionModel().clearSelection();

        dpMatchDate.setValue(null);

        messageLabel.setText("");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

    }
}