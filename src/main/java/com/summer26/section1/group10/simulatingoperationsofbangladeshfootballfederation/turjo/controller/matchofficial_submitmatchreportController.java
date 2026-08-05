package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchOfficials;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.model.MatchReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class matchofficial_submitmatchreportController implements UserReceiver {

    @FXML
    private TextField cardsTF;
    @FXML
    private TableColumn<MatchReport, String> scoreCol;
    @FXML
    private TableView<MatchReport> matchReportsTable;
    @FXML
    private TableColumn<MatchReport, Integer> cardsCol;
    @FXML
    private TableColumn<MatchReport, String> statusCol;
    @FXML
    private TextField goalScorersTF;
    @FXML
    private TableColumn<MatchReport, String> matchBetweenCol;
    @FXML
    private ComboBox<String> statusCB;
    @FXML
    private TextArea summaryTA;
    @FXML
    private TextField scoreTF;
    @FXML
    private TableColumn<MatchReport, String> goalScorersCol;
    @FXML
    private TextField matchBetweenTF;
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

        statusCB.getItems().addAll(
                "Completed",
                "Abandoned",
                "Postponed"
        );

        matchBetweenCol.setCellValueFactory(new PropertyValueFactory<>("matchBetween"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        goalScorersCol.setCellValueFactory(new PropertyValueFactory<>("goalScorers"));
        cardsCol.setCellValueFactory(new PropertyValueFactory<>("cards"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        ArrayList<Object> reportList = BinaryFileUtility.readObjects("MatchReports.bin");
        for (Object record : reportList) {
            if (record instanceof MatchReport matchReport) {
                matchReportsTable.getItems().add(matchReport);
            }
        }
    }

    private boolean validateInput() {

        if (matchBetweenTF.getText().trim().isEmpty()) {
            messageLabel.setText("Match name cannot be empty.");
            matchBetweenTF.requestFocus();
            return false;
        }

        if (!matchBetweenTF.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Match name can contain only letters.");
            matchBetweenTF.requestFocus();
            return false;
        }

        if (scoreTF.getText().trim().isEmpty()) {
            messageLabel.setText("Score cannot be empty.");
            scoreTF.requestFocus();
            return false;
        }

        if (!scoreTF.getText().matches("\\d+-\\d+")) {
            messageLabel.setText("Score must be in format 2-1.");
            scoreTF.requestFocus();
            return false;
        }

        if (goalScorersTF.getText().trim().isEmpty()) {
            messageLabel.setText("Goal scorers cannot be empty.");
            goalScorersTF.requestFocus();
            return false;
        }

        if (cardsTF.getText().trim().isEmpty()) {
            messageLabel.setText("Cards field cannot be empty.");
            cardsTF.requestFocus();
            return false;
        }

        if (!cardsTF.getText().matches("\\d+")) {
            messageLabel.setText("Cards must contain only numbers.");
            cardsTF.requestFocus();
            return false;
        }

        if (statusCB.getValue() == null) {
            messageLabel.setText("Select match status.");
            statusCB.requestFocus();
            return false;
        }

        if (summaryTA.getText().trim().isEmpty()) {
            messageLabel.setText("Summary cannot be empty.");
            summaryTA.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void submitReportOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        MatchReport report = new MatchReport(
                matchBetweenTF.getText().trim(),
                scoreTF.getText().trim(),
                goalScorersTF.getText().trim(),
                Integer.parseInt(cardsTF.getText().trim()),
                statusCB.getValue(),
                summaryTA.getText().trim()
        );

        matchReportsTable.getItems().add(report);
        BinaryFileUtility.writeObjects("MatchReports.bin", report);

        messageLabel.setText("Match report submitted successfully.");
        clearOA(actionEvent);
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        matchBetweenTF.clear();
        scoreTF.clear();
        goalScorersTF.clear();
        cardsTF.clear();
        summaryTA.clear();

        statusCB.getSelectionModel().clearSelection();

        messageLabel.setText("");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/matchofficialsdashboard.fxml");
    }
}