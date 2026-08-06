package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.Managematch;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchOfficials;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class matchofficial_viewmatchscheduleController implements UserReceiver {

    @FXML
    private TableColumn<Managematch, String> awayTeamCol;
    @FXML
    private TableColumn<Managematch, LocalDate> dateCol;
    @FXML
    private TableColumn<Managematch, String> venueCol;
    @FXML
    private TableView<Managematch> scheduleTable;
    @FXML
    private TableColumn<Managematch, String> homeTeamCol;
    @FXML
    private TableColumn<Managematch, String> timeCol;
    @FXML
    private Label messageLabel;

    private MatchOfficials loggedInUser;

    @Override
    public void setLoggedInUser(User user){
        if (user instanceof MatchOfficials m){
            loggedInUser = m;
        }
        else {
            AlertGenerator.showAlert("Error", "This is not a valid user for this page");
        }
    }

    @FXML
    public void initialize() {

        homeTeamCol.setCellValueFactory(new PropertyValueFactory<>("hometeam"));
        awayTeamCol.setCellValueFactory(new PropertyValueFactory<>("awayteam"));
        venueCol.setCellValueFactory(new PropertyValueFactory<>("stadium"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("matchdate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("matchtime"));

        loadMatches();
    }

    private void loadMatches() {

        scheduleTable.getItems().clear();

        ArrayList matches = BinaryFileUtility.readObjects("Managematches.bin");

        for (Object record : matches) {
            if (record instanceof Managematch match) {
                scheduleTable.getItems().add(match);
            }
        }

        if (scheduleTable.getItems().isEmpty()) {
            messageLabel.setText("No match schedule available.");
        } else {
            messageLabel.setText("");
        }
    }

    @FXML
    public void refreshOA(ActionEvent actionEvent) {

        loadMatches();

        if (scheduleTable.getItems().isEmpty()) {
            messageLabel.setText("No match schedule available.");
        } else {
            messageLabel.setText("Match schedule refreshed successfully.");
        }
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/matchofficialsdashboard.fxml");



    }
}