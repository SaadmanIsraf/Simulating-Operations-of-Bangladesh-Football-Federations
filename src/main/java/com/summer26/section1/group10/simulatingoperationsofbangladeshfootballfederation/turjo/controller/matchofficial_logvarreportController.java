package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchOfficials;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.VARreport;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class matchofficial_logvarreportController implements UserReceiver {

    @FXML
    private TableView<VARreport> varReportsTable;
    @FXML
    private TableColumn<VARreport, LocalDate> matchDayCol;
    @FXML
    private ComboBox<String> reviewTypeCB;
    @FXML
    private TextField decisionTF;
    @FXML
    private TableColumn<VARreport, String> minuteCol;
    @FXML
    private TextArea detailsTA;
    @FXML
    private DatePicker matchDayDP;
    @FXML
    private ComboBox<String> varDecisionCB;
    @FXML
    private TextField minuteTF;
    @FXML
    private TextField matchBetweenTF;
    @FXML
    private TableColumn<VARreport, String> reviewTypeCol;
    @FXML
    private TableColumn<VARreport, String> varDecisionCol;
    @FXML
    private TableColumn<VARreport, String> matchBetweenCol;
    @FXML
    private TableColumn<VARreport, String> playerNameCol;
    @FXML
    private TableColumn<VARreport, String> finalDecisionCol;
    @FXML
    private TextField playerNameTF;
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

        matchBetweenCol.setCellValueFactory(new PropertyValueFactory<VARreport, String>("matchbetween"));
        matchDayCol.setCellValueFactory(new PropertyValueFactory<VARreport, LocalDate>("matchday"));
        minuteCol.setCellValueFactory(new PropertyValueFactory<VARreport, String>("minute"));
        reviewTypeCol.setCellValueFactory(new PropertyValueFactory<VARreport, String>("reviewtype"));
        varDecisionCol.setCellValueFactory(new PropertyValueFactory<VARreport, String>("vardecision"));
        playerNameCol.setCellValueFactory(new PropertyValueFactory<VARreport, String>("playername"));
        finalDecisionCol.setCellValueFactory(new PropertyValueFactory<VARreport, String>("finaldecision"));

        ArrayList<Object> reportList = BinaryFileUtility.readObjects("VARreports.bin");
        for (Object record : reportList) {
            if (record instanceof VARreport varreport) {
                varReportsTable.getItems().add(varreport);
            }
        }

        reviewTypeCB.getItems().addAll(
                "Goal Review",
                "Penalty Review",
                "Red Card Review",
                "Offside Review"
        );

        varDecisionCB.getItems().addAll(
                "Confirmed",
                "Overturned"
        );
    }

    private boolean validateInput() {

        if (matchDayDP.getValue() == null) {
            messageLabel.setText("Select match day.");
            matchDayDP.requestFocus();
            return false;
        }

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

        if (minuteTF.getText().trim().isEmpty()) {
            messageLabel.setText("Minute cannot be empty.");
            minuteTF.requestFocus();
            return false;
        }

        if (!minuteTF.getText().matches("\\d+")) {
            messageLabel.setText("Minute must contain only numbers.");
            minuteTF.requestFocus();
            return false;
        }

        int minute = Integer.parseInt(minuteTF.getText());

        if (minute < 1 || minute > 120) {
            messageLabel.setText("Minute must be between 1 and 120.");
            minuteTF.requestFocus();
            return false;
        }

        if (playerNameTF.getText().trim().isEmpty()) {
            messageLabel.setText("Player name cannot be empty.");
            playerNameTF.requestFocus();
            return false;
        }

        if (!playerNameTF.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Player name can contain only letters.");
            playerNameTF.requestFocus();
            return false;
        }

        if (reviewTypeCB.getValue() == null) {
            messageLabel.setText("Select review type.");
            reviewTypeCB.requestFocus();
            return false;
        }

        if (varDecisionCB.getValue() == null) {
            messageLabel.setText("Select VAR decision.");
            varDecisionCB.requestFocus();
            return false;
        }

        if (decisionTF.getText().trim().isEmpty()) {
            messageLabel.setText("Final decision cannot be empty.");
            decisionTF.requestFocus();
            return false;
        }

        if (!decisionTF.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Final decision can contain only letters.");
            decisionTF.requestFocus();
            return false;
        }

        if (detailsTA.getText().trim().isEmpty()) {
            messageLabel.setText("Details cannot be empty.");
            detailsTA.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void submitVARReportOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        VARreport varreport = new VARreport(
                matchBetweenTF.getText(),
                matchDayDP.getValue(),
                minuteTF.getText(),
                playerNameTF.getText(),
                reviewTypeCB.getValue(),
                varDecisionCB.getValue(),
                decisionTF.getText(),
                detailsTA.getText());

        varReportsTable.getItems().add(varreport);
        BinaryFileUtility.writeObjects("VARreports.bin", varreport);

        messageLabel.setText("VAR report submitted successfully.");
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        matchDayDP.setValue(null);
        matchBetweenTF.clear();
        minuteTF.clear();
        playerNameTF.clear();
        decisionTF.clear();
        detailsTA.clear();

        reviewTypeCB.getSelectionModel().clearSelection();
        varDecisionCB.getSelectionModel().clearSelection();

        messageLabel.setText("");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/matchofficialsdashboard.fxml");
    }
}