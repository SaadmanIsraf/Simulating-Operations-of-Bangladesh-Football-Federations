package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class federation_administrator_managematchesController {

    @FXML
    private TableColumn<?, ?> awayTeamCol;
    @FXML
    private ComboBox<String> cmbMatchStatus;
    @FXML
    private TextField assignedOfficialTF;
    @FXML
    private TextField txtMatchTime;
    @FXML
    private ComboBox<String> cmbCompetition;
    @FXML
    private TextField txtMatchId;
    @FXML
    private TableColumn<?, ?> homeTeamCol;
    @FXML
    private TableColumn<?, ?> competitionCol;
    @FXML
    private TableColumn<?, ?> matchIdCol;
    @FXML
    private TableColumn<?, ?> stadiumCol;
    @FXML
    private ComboBox<String> cmbAwayTeam;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> statusCol;
    @FXML
    private TableView<?> tblMatches;
    @FXML
    private ComboBox<String> cmbHomeTeam;
    @FXML
    private DatePicker dpMatchDate;
    @FXML
    private TableColumn<?, ?> timeCol;
    @FXML
    private TableColumn<?, ?> officialCol;
    @FXML
    private ComboBox<String> cmbStadium;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {

        cmbCompetition.getItems().addAll(
                "Premier League",
                "Federation Cup",
                "Independence Cup"
        );

        cmbHomeTeam.getItems().addAll(
                "Abahani",
                "Mohammedan",
                "Bashundhara Kings",
                "Brothers Union"
        );

        cmbAwayTeam.getItems().addAll(
                "Abahani",
                "Mohammedan",
                "Bashundhara Kings",
                "Brothers Union"
        );

        cmbStadium.getItems().addAll(
                "National Stadium",
                "Army Stadium",
                "Mymensingh Stadium"
        );

        cmbMatchStatus.getItems().addAll(
                "Scheduled",
                "Ongoing",
                "Completed",
                "Cancelled"
        );
    }

    private boolean validateInput() {

        String matchId = txtMatchId.getText().trim();
        String matchTime = txtMatchTime.getText().trim();
        String official = assignedOfficialTF.getText().trim();

        if (matchId.isEmpty()) {
            messageLabel.setText("Match ID cannot be empty.");
            txtMatchId.requestFocus();
            return false;
        }

        if (!matchId.matches("\\d+")) {
            messageLabel.setText("Match ID must contain only numbers.");
            txtMatchId.requestFocus();
            return false;
        }

        if (cmbCompetition.getValue() == null) {
            messageLabel.setText("Please select a competition.");
            cmbCompetition.requestFocus();
            return false;
        }

        if (cmbHomeTeam.getValue() == null) {
            messageLabel.setText("Please select the Home Team.");
            cmbHomeTeam.requestFocus();
            return false;
        }

        if (cmbAwayTeam.getValue() == null) {
            messageLabel.setText("Please select the Away Team.");
            cmbAwayTeam.requestFocus();
            return false;
        }

        if (cmbHomeTeam.getValue().equals(cmbAwayTeam.getValue())) {
            messageLabel.setText("Home Team and Away Team cannot be the same.");
            cmbAwayTeam.requestFocus();
            return false;
        }

        if (dpMatchDate.getValue() == null) {
            messageLabel.setText("Please select the match date.");
            dpMatchDate.requestFocus();
            return false;
        }

        if (matchTime.isEmpty()) {
            messageLabel.setText("Match time cannot be empty.");
            txtMatchTime.requestFocus();
            return false;
        }

        if (cmbStadium.getValue() == null) {
            messageLabel.setText("Please select a stadium.");
            cmbStadium.requestFocus();
            return false;
        }

        if (official.isEmpty()) {
            messageLabel.setText("Assigned Official cannot be empty.");
            assignedOfficialTF.requestFocus();
            return false;
        }

        if (!official.matches("[A-Za-z ]+")) {
            messageLabel.setText("Official name can contain only letters and spaces.");
            assignedOfficialTF.requestFocus();
            return false;
        }

        if (cmbMatchStatus.getValue() == null) {
            messageLabel.setText("Please select the match status.");
            cmbMatchStatus.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void createMatchOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("Match created successfully.");
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        txtMatchId.clear();
        txtMatchTime.clear();
        assignedOfficialTF.clear();

        cmbCompetition.getSelectionModel().clearSelection();
        cmbHomeTeam.getSelectionModel().clearSelection();
        cmbAwayTeam.getSelectionModel().clearSelection();
        cmbStadium.getSelectionModel().clearSelection();
        cmbMatchStatus.getSelectionModel().clearSelection();

        dpMatchDate.setValue(null);

        messageLabel.setText("");
    }

    @FXML
    public void cancelMatchOA(ActionEvent actionEvent) {
        messageLabel.setText("Match cancelled.");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

    }
}