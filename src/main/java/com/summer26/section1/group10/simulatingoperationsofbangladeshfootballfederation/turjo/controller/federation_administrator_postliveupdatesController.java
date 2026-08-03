package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.Liveupdates;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class federation_administrator_postliveupdatesController {

    @FXML
    private ComboBox<String> cmbMatchStatus;
    @FXML
    private TableView<Liveupdates> updatesTable;
    @FXML
    private TableColumn<Liveupdates, String> scoreCol;
    @FXML
    private TextField txtCardPlayer;
    @FXML
    private TextField txtMinute;
    @FXML
    private ComboBox<String> cmbCardType;
    @FXML
    private TextField txtSubstitution;
    @FXML
    private TableColumn<Liveupdates, String> commentaryCol;
    @FXML
    private TableColumn<Liveupdates, String> minuteCol;
    @FXML
    private TextField txtAwayScore;
    @FXML
    private TableColumn<Liveupdates, String> goalScorerCol;
    @FXML
    private ComboBox<String> cmbMatch;
    @FXML
    private TextField txtAssist;
    @FXML
    private TableColumn<Liveupdates, String> assistCol;
    @FXML
    private TextField txtGoalScorer;
    @FXML
    private TableColumn<Liveupdates, String> cardCol;
    @FXML
    private TableColumn<Liveupdates, String> statusCol;
    @FXML
    private TextArea txtCommentary;
    @FXML
    private TableColumn<Liveupdates, String> substitutionCol;
    @FXML
    private TextField txtHomeScore;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {

        minuteCol.setCellValueFactory(new PropertyValueFactory<Liveupdates, String>("minute"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<Liveupdates, String>("score"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Liveupdates, String>("matchstatus"));
        goalScorerCol.setCellValueFactory(new PropertyValueFactory<Liveupdates, String>("goalscorer"));
        assistCol.setCellValueFactory(new PropertyValueFactory<Liveupdates, String>("assist"));
        cardCol.setCellValueFactory(new PropertyValueFactory<Liveupdates, String>("cardtype"));
        substitutionCol.setCellValueFactory(new PropertyValueFactory<Liveupdates, String>("substitution"));
        commentaryCol.setCellValueFactory(new PropertyValueFactory<Liveupdates, String>("commentary"));

        ArrayList<Object> updatesList = BinaryFileUtility.readObjects("Liveupdates.bin");
        for (Object record : updatesList) {
            if (record instanceof Liveupdates liveupdates) {
                updatesTable.getItems().add(liveupdates);
            }
        }

        cmbMatch.getItems().addAll(
                "Match 101",
                "Match 102",
                "Match 103"
        );

        cmbMatchStatus.getItems().addAll(
                "First Half",
                "Half Time",
                "Second Half",
                "Full Time"
        );

        cmbCardType.getItems().addAll(
                "Yellow Card",
                "Red Card"
        );
    }

    private boolean validateInput() {

        if (cmbMatch.getValue() == null) {
            messageLabel.setText("Select a match.");
            cmbMatch.requestFocus();
            return false;
        }

        if (txtMinute.getText().trim().isEmpty()) {
            messageLabel.setText("Enter match minute.");
            txtMinute.requestFocus();
            return false;
        }

        if (!txtMinute.getText().matches("\\d+")) {
            messageLabel.setText("Minute must contain only numbers.");
            txtMinute.requestFocus();
            return false;
        }

        if (txtHomeScore.getText().trim().isEmpty()) {
            messageLabel.setText("Enter home score.");
            txtHomeScore.requestFocus();
            return false;
        }

        if (!txtHomeScore.getText().matches("\\d+")) {
            messageLabel.setText("Home score must contain only numbers.");
            txtHomeScore.requestFocus();
            return false;
        }

        if (txtAwayScore.getText().trim().isEmpty()) {
            messageLabel.setText("Enter away score.");
            txtAwayScore.requestFocus();
            return false;
        }

        if (!txtAwayScore.getText().matches("\\d+")) {
            messageLabel.setText("Away score must contain only numbers.");
            txtAwayScore.requestFocus();
            return false;
        }

        if (txtGoalScorer.getText().trim().isEmpty()) {
            messageLabel.setText("Enter goal scorer.");
            txtGoalScorer.requestFocus();
            return false;
        }

        if (!txtGoalScorer.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Goal scorer name must contain only letters.");
            txtGoalScorer.requestFocus();
            return false;
        }

        if (txtAssist.getText().trim().isEmpty()) {
            messageLabel.setText("Enter assist player.");
            txtAssist.requestFocus();
            return false;
        }

        if (!txtAssist.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Assist player name must contain only letters.");
            txtAssist.requestFocus();
            return false;
        }

        if (cmbCardType.getValue() == null) {
            messageLabel.setText("Select card type.");
            cmbCardType.requestFocus();
            return false;
        }

        if (txtCardPlayer.getText().trim().isEmpty()) {
            messageLabel.setText("Enter carded player.");
            txtCardPlayer.requestFocus();
            return false;
        }

        if (!txtCardPlayer.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Carded player name must contain only letters.");
            txtCardPlayer.requestFocus();
            return false;
        }

        if (txtSubstitution.getText().trim().isEmpty()) {
            messageLabel.setText("Enter substitution details.");
            txtSubstitution.requestFocus();
            return false;
        }

        if (txtCommentary.getText().trim().isEmpty()) {
            messageLabel.setText("Enter live commentary.");
            txtCommentary.requestFocus();
            return false;
        }

        if (cmbMatchStatus.getValue() == null) {
            messageLabel.setText("Select match status.");
            cmbMatchStatus.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void postUpdateOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        Liveupdates liveupdates = new Liveupdates(
                cmbMatch.getValue(),
                txtMinute.getText(),
                txtHomeScore.getText(),
                txtAwayScore.getText(),
                cmbMatchStatus.getValue(),
                txtGoalScorer.getText(),
                txtAssist.getText(),
                cmbCardType.getValue(),
                txtCardPlayer.getText(),
                txtSubstitution.getText(),
                txtCommentary.getText());

        updatesTable.getItems().add(liveupdates);
        BinaryFileUtility.writeObjects("Liveupdates.bin", liveupdates);

        messageLabel.setText("Live update posted successfully.");
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        cmbMatch.getSelectionModel().clearSelection();
        cmbMatchStatus.getSelectionModel().clearSelection();
        cmbCardType.getSelectionModel().clearSelection();

        txtMinute.clear();
        txtHomeScore.clear();
        txtAwayScore.clear();
        txtGoalScorer.clear();
        txtAssist.clear();
        txtCardPlayer.clear();
        txtSubstitution.clear();
        txtCommentary.clear();

        messageLabel.setText("");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/federation_administrator/dashboardView.fxml");
    }
}