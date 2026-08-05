package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchOfficials;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class matchofficial_viewmatchscheduleController implements UserReceiver {

    @FXML
    private TableColumn<?, ?> awayTeamCol;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> venueCol;
    @FXML
    private TableView<?> scheduleTable;
    @FXML
    private TableColumn<?, ?> homeTeamCol;
    @FXML
    private TableColumn<?, ?> timeCol;
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

    }

    @FXML
    public void refreshOA(ActionEvent actionEvent) {

        scheduleTable.refresh();

        if (scheduleTable.getItems().isEmpty()) {
            messageLabel.setText("No match schedule available.");
        } else {
            messageLabel.setText("Match schedule refreshed successfully.");
        }
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

    }
}