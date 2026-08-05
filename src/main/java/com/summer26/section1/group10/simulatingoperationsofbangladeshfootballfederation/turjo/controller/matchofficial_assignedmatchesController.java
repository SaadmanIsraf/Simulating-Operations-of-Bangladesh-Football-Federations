package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.Managematch;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchOfficials;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class matchofficial_assignedmatchesController {

    @FXML
    private TextField officialIdTF;
    @FXML
    private TextField officialNameTF;
    @FXML
    private TableView<Managematch> assignedMatchesTable;
    @FXML
    private TableColumn<Managematch, String> matchIdCol;
    @FXML
    private TableColumn<Managematch, String> homeTeamCol;
    @FXML
    private TableColumn<Managematch, String> awayTeamCol;
    @FXML
    private TableColumn<Managematch, String> competitionCol;
    @FXML
    private TableColumn<Managematch, String> stadiumCol;
    @FXML
    private TableColumn<Managematch, LocalDate> dateCol;
    @FXML
    private TableColumn<Managematch, String> timeCol;
    @FXML
    private TableColumn<Managematch, String> statusCol;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {

        matchIdCol.setCellValueFactory(new PropertyValueFactory<Managematch, String>("matchId"));
        homeTeamCol.setCellValueFactory(new PropertyValueFactory<Managematch, String>("hometeam"));
        awayTeamCol.setCellValueFactory(new PropertyValueFactory<Managematch, String>("awayteam"));
        competitionCol.setCellValueFactory(new PropertyValueFactory<Managematch, String>("competition"));
        stadiumCol.setCellValueFactory(new PropertyValueFactory<Managematch, String>("stadium"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Managematch, LocalDate>("matchdate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Managematch, String>("matchtime"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Managematch, String>("status"));
    }

    private boolean validateInput() {

        if (officialIdTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter your Official ID.");
            officialIdTF.requestFocus();
            return false;
        }

        if (!officialIdTF.getText().trim().matches("\\d+")) {
            messageLabel.setText("Official ID must contain only numbers.");
            officialIdTF.requestFocus();
            return false;
        }

        if (officialNameTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter your name.");
            officialNameTF.requestFocus();
            return false;
        }

        if (!officialNameTF.getText().trim().matches("[A-Za-z ]+")) {
            messageLabel.setText("Name can contain only letters.");
            officialNameTF.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void viewAssignedMatchesOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        String enteredId = officialIdTF.getText().trim();
        String enteredName = officialNameTF.getText().trim();

        MatchOfficials matchedOfficial = null;

        ArrayList<Object> userList = BinaryFileUtility.readObjects("User.bin");
        for (Object user : userList) {
            if (user instanceof MatchOfficials official) {
                if (String.valueOf(official.getId()).equals(enteredId) && official.getName().equalsIgnoreCase(enteredName)) {
                    matchedOfficial = official;
                    break;
                }
            }
        }

        if (matchedOfficial == null) {
            messageLabel.setText("No official found with that ID and name.");
            assignedMatchesTable.getItems().clear();
            return;
        }

        assignedMatchesTable.getItems().clear();

        String matchedOfficialId = String.valueOf(matchedOfficial.getId());

        ArrayList<Object> matchList = BinaryFileUtility.readObjects("Managematches.bin");
        for (Object record : matchList) {
            if (record instanceof Managematch managematch) {
                if (managematch.getOfficialId().equals(matchedOfficialId)) {
                    assignedMatchesTable.getItems().add(managematch);
                }
            }
        }

        if (assignedMatchesTable.getItems().isEmpty()) {
            messageLabel.setText("No matches currently assigned to you.");
        } else {
            messageLabel.setText("Showing matches assigned to " + matchedOfficial.getName() + ".");
        }
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/matchofficialsdashboard.fxml");
    }



    public class SeedTestOfficial {

        public static void main(String[] args) {

            MatchOfficials testOfficial = new MatchOfficials(
                    4821,
                    "Rakib Hossain",
                    "rakib.hossain@federation.local",
                    "test1234",
                    "Match Officials",
                    123456,
                    "3-5 Years",
                    "Referee");

            BinaryFileUtility.writeObjects("User.bin", testOfficial);

            System.out.println("Test official saved: ID=4821, Name=Rakib Hossain");
        }
    }
}

