package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchOfficials;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.ReplacementRef;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class matchofficial_requestrefereereplacementController implements UserReceiver {

    @FXML
    private TextField matchIdTF1;
    @FXML
    private ComboBox<String> priorityCB;
    @FXML
    private TextField currentRefereeTF;
    @FXML
    private TableView<ReplacementRef> replacementRequestsTable;
    @FXML
    private TableColumn<ReplacementRef, String> reasonCol;
    @FXML
    private TextField matchIdTF;
    @FXML
    private TableColumn<ReplacementRef, String> detailsCol;
    @FXML
    private TableColumn<ReplacementRef, String> priorityCol;
    @FXML
    private TextArea detailsTA;
    @FXML
    private TableColumn<ReplacementRef, String> currentRefereeCol;
    @FXML
    private TableColumn<ReplacementRef, String> matchIdCol;
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

        matchIdCol.setCellValueFactory(new PropertyValueFactory<ReplacementRef, String>("matchid"));
        currentRefereeCol.setCellValueFactory(new PropertyValueFactory<ReplacementRef, String>("currentreferee"));
        reasonCol.setCellValueFactory(new PropertyValueFactory<ReplacementRef, String>("reason"));
        priorityCol.setCellValueFactory(new PropertyValueFactory<ReplacementRef, String>("priority"));
        detailsCol.setCellValueFactory(new PropertyValueFactory<ReplacementRef, String>("details"));

        ArrayList<Object> requestList = BinaryFileUtility.readObjects("ReplacementRefs.bin");
        for (Object record : requestList) {
            if (record instanceof ReplacementRef replacementRef) {
                replacementRequestsTable.getItems().add(replacementRef);
            }
        }

        priorityCB.getItems().addAll(
                "Low",
                "Medium",
                "High",
                "Emergency"
        );
    }

    private boolean validateInput() {

        if (matchIdTF.getText().trim().isEmpty()) {
            messageLabel.setText("Match ID cannot be empty.");
            matchIdTF.requestFocus();
            return false;
        }

        if (matchIdTF.getText().trim().isEmpty()) {
            messageLabel.setText("Cant be empty");
            matchIdTF.requestFocus();
            return false;
        }

        if (currentRefereeTF.getText().trim().isEmpty()) {
            messageLabel.setText("Current referee name cannot be empty.");
            currentRefereeTF.requestFocus();
            return false;
        }

        if (!currentRefereeTF.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Referee name can contain only letters.");
            currentRefereeTF.requestFocus();
            return false;
        }

        if (matchIdTF1.getText().trim().isEmpty()) {
            messageLabel.setText("Reason cannot be empty.");
            matchIdTF1.requestFocus();
            return false;
        }

        if (!matchIdTF1.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Reason can contain only letters.");
            matchIdTF1.requestFocus();
            return false;
        }

        if (priorityCB.getValue() == null) {
            messageLabel.setText("Select a priority.");
            priorityCB.requestFocus();
            return false;
        }

        if (detailsTA.getText().trim().isEmpty()) {
            messageLabel.setText("Details cannot be empty.");
            detailsTA.requestFocus();
            return false;
        }

        messageLabel.setText("xxx");
        return true;
    }

    @FXML
    public void submitRequestOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        ReplacementRef replacementRef = new ReplacementRef(
                matchIdTF.getText(),
                currentRefereeTF.getText(),
                matchIdTF1.getText(),
                priorityCB.getValue(),
                detailsTA.getText());

        replacementRequestsTable.getItems().add(replacementRef);
        BinaryFileUtility.writeObjects("ReplacementRef.bin", replacementRef);

        messageLabel.setText("Replacement request submitted successfully.");
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        matchIdTF.clear();
        currentRefereeTF.clear();
        matchIdTF1.clear();
        detailsTA.clear();

        priorityCB.getSelectionModel().clearSelection();

        messageLabel.setText("xxx");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/matchofficialsdashboard.fxml");
    }
}