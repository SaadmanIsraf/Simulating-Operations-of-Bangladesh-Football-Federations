package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.Managematch;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.federation_administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class federation_administrator_managematchesController implements UserReceiver {

    @FXML
    private TableColumn<Managematch, String> awayTeamCol;
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
    private TableColumn<Managematch, String> homeTeamCol;
    @FXML
    private TableColumn<Managematch, String> competitionCol;
    @FXML
    private TableColumn<Managematch, String> matchIdCol;
    @FXML
    private TableColumn<Managematch, String> stadiumCol;
    @FXML
    private ComboBox<String> cmbAwayTeam;
    @FXML
    private TableColumn<Managematch, LocalDate> dateCol;
    @FXML
    private TableColumn<Managematch, String> statusCol;
    @FXML
    private TableView<Managematch> tblMatches;
    @FXML
    private ComboBox<String> cmbHomeTeam;
    @FXML
    private DatePicker dpMatchDate;
    @FXML
    private TableColumn<Managematch, String> timeCol;
    @FXML
    private TableColumn<Managematch, String> officialCol;
    @FXML
    private ComboBox<String> cmbStadium;
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

        matchIdCol.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        homeTeamCol.setCellValueFactory(new PropertyValueFactory<>("hometeam"));
        awayTeamCol.setCellValueFactory(new PropertyValueFactory<>("awayteam"));
        competitionCol.setCellValueFactory(new PropertyValueFactory<>("competition"));
        stadiumCol.setCellValueFactory(new PropertyValueFactory<>("stadium"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("matchdate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("matchtime"));
        officialCol.setCellValueFactory(new PropertyValueFactory<>("officialId"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        ArrayList managematch = BinaryFileUtility.readObjects("Managematches.bin");
        for (Object record : managematch) {
            if (record instanceof Managematch managematches) {
                tblMatches.getItems().add(managematches);
            }
        }

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

        Managematch newMatch = loggedInUser.manageMatchControl(txtMatchId.getText(),
                cmbHomeTeam.getValue(),
                cmbAwayTeam.getValue(),
                cmbCompetition.getValue(),
                cmbStadium.getValue(),
                dpMatchDate.getValue(),
                txtMatchTime.getText(),
                assignedOfficialTF.getText(),
                cmbMatchStatus.getValue()
        );


        tblMatches.getItems().add(newMatch);

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
        SceneSwitcher.switchTo("turjo/match_officials/matchofficialsdashboard.fxml");
    }
}