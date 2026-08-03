package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.Announcement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class federation_administrator_publishannouncementController {

    @FXML
    private TableColumn<Announcement, String> titleCol;
    @FXML
    private TableView<Announcement> announcementTable;
    @FXML
    private TableColumn<Announcement, String> matchTypeCol;
    @FXML
    private TableColumn<Announcement, String> venueCol;
    @FXML
    private TextField txtMatchTime;
    @FXML
    private ComboBox<String> cmbCompetition;
    @FXML
    private TableColumn<Announcement, String> matchCol;
    @FXML
    private TextArea txtAnnouncementDetails;
    @FXML
    private ComboBox<String> cmbMatch;
    @FXML
    private TableColumn<Announcement, String> competitionCol;
    @FXML
    private TextField txtVenue;
    @FXML
    private TableColumn<Announcement, LocalDate> dateCol;
    @FXML
    private TableColumn<Announcement, String> detailsCol;
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
    private TableColumn<Announcement, String> timeCol;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {

        titleCol.setCellValueFactory(new PropertyValueFactory<Announcement, String>("title"));
        matchCol.setCellValueFactory(new PropertyValueFactory<Announcement, String>("match"));
        competitionCol.setCellValueFactory(new PropertyValueFactory<Announcement, String>("competition"));
        venueCol.setCellValueFactory(new PropertyValueFactory<Announcement, String>("venue"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Announcement, LocalDate>("matchdate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Announcement, String>("matchtime"));
        matchTypeCol.setCellValueFactory(new PropertyValueFactory<Announcement, String>("matchtype"));
        detailsCol.setCellValueFactory(new PropertyValueFactory<Announcement, String>("details"));

        ArrayList<Object> announcementList = BinaryFileUtility.readObjects("Announcements.bin");
        for (Object record : announcementList) {
            if (record instanceof Announcement announcement) {
                announcementTable.getItems().add(announcement);
            }
        }

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

        Announcement announcement = new Announcement(
                txtAnnouncementTitle.getText(),
                cmbMatch.getValue(),
                cmbCompetition.getValue(),
                txtVenue.getText(),
                txtSponsor.getText(),
                txtPlace.getText(),
                dpMatchDate.getValue(),
                txtMatchTime.getText(),
                cmbMatchType.getValue(),
                txtAnnouncementDetails.getText());

        announcementTable.getItems().add(announcement);
        BinaryFileUtility.writeObjects("Announcements.bin", announcement);

        messageLabel.setText("Announcement published successfully.");
    }

    @FXML
    public void updateAnnouncementOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        Announcement selected = announcementTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Please select an announcement to update.");
            return;
        }

        selected.setTitle(txtAnnouncementTitle.getText());
        selected.setMatch(cmbMatch.getValue());
        selected.setCompetition(cmbCompetition.getValue());
        selected.setVenue(txtVenue.getText());
        selected.setSponsor(txtSponsor.getText());
        selected.setPlace(txtPlace.getText());
        selected.setMatchdate(dpMatchDate.getValue());
        selected.setMatchtime(txtMatchTime.getText());
        selected.setMatchtype(cmbMatchType.getValue());
        selected.setDetails(txtAnnouncementDetails.getText());

        announcementTable.refresh();

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
        SceneSwitcher.switchTo("turjo/federation_administrator/dashboardView.fxml");
    }
}