package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchOfficials;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.RecordFouls;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class matchofficial_recordfoulscardsController implements UserReceiver {

    @FXML
    private TextField foulTypeTF;
    @FXML
    private TableColumn<RecordFouls, String> eventTypeCol;
    @FXML
    private TableColumn<RecordFouls, String> cardTypeCol;
    @FXML
    private TableColumn<RecordFouls, String> minuteCol;
    @FXML
    private TableColumn<RecordFouls, String> matchCol;
    @FXML
    private TextField minuteTF;
    @FXML
    private ComboBox<String> eventTypeCB;
    @FXML
    private TableView<RecordFouls> foulsCardsTable;
    @FXML
    private ComboBox<String> cardTypeCB;
    @FXML
    private TableColumn<RecordFouls, String> playerNameCol;
    @FXML
    private TextArea noteTA;
    @FXML
    private ComboBox<String> matchCB;
    @FXML
    private TableColumn<RecordFouls, String> foulTypeCol;
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

        cardTypeCol.setCellValueFactory(new PropertyValueFactory<RecordFouls,String>("cardType"));
        foulTypeCol.setCellValueFactory(new PropertyValueFactory<RecordFouls,String>("foulType"));
        matchCol.setCellValueFactory(new PropertyValueFactory<RecordFouls,String>("matchbetween"));
        playerNameCol.setCellValueFactory(new PropertyValueFactory<RecordFouls,String>("playerName"));
        minuteCol.setCellValueFactory(new PropertyValueFactory<RecordFouls,String>("minute"));
        eventTypeCol.setCellValueFactory(new PropertyValueFactory<RecordFouls,String>("notes"));


        ArrayList<Object> recordList = BinaryFileUtility.readObjects("RecordFouls.bin");
        for (Object record : recordList) {
            if(record instanceof RecordFouls recordsfoul) {
                foulsCardsTable.getItems().add(recordsfoul);
            }
        }




        eventTypeCB.getItems().addAll(
                "Foul",
                "Yellow Card",
                "Goal"
        );

        cardTypeCB.getItems().addAll(
                "No Card",
                "2nd Yellow",
                "Red"
        );

        matchCB.getItems().addAll(
                "Real Madrid VS Nepoli",
                "Brazil VS Norway",
                "Bangladesh vs Nepal",
                "Bangladesh vs Maldives"
        );
    }

    private boolean validateInput() {

        if (matchCB.getValue() == null) {
            messageLabel.setText("Select a match.");
            matchCB.requestFocus();
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

        if (eventTypeCB.getValue() == null) {
            messageLabel.setText("Select event type.");
            eventTypeCB.requestFocus();
            return false;
        }

        if (cardTypeCB.getValue() == null) {
            messageLabel.setText("Select card type.");
            cardTypeCB.requestFocus();
            return false;
        }

        if (foulTypeTF.getText().trim().isEmpty()) {
            messageLabel.setText("Foul type cannot be empty.");
            foulTypeTF.requestFocus();
            return false;
        }

        if (!foulTypeTF.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Foul type can contain only letters.");
            foulTypeTF.requestFocus();
            return false;
        }

        if (noteTA.getText().trim().isEmpty()) {
            messageLabel.setText("Notes cannot be empty.");
            noteTA.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }


    @FXML
    public void saveRecordOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        RecordFouls recordFouls = new RecordFouls(matchCB.getValue(),playerNameTF.getText(),minuteTF.getText(),eventTypeCB.getValue(),cardTypeCB.getValue(),foulTypeTF.getText(),noteTA.getText());
        foulsCardsTable.getItems().add(recordFouls);
        BinaryFileUtility.writeObjects("RecordFouls.bin", recordFouls);
        messageLabel.setText("Official registered successfully.");
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        matchCB.getSelectionModel().clearSelection();
        eventTypeCB.getSelectionModel().clearSelection();
        cardTypeCB.getSelectionModel().clearSelection();

        playerNameTF.clear();
        minuteTF.clear();
        foulTypeTF.clear();
        noteTA.clear();

        messageLabel.setText("");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/matchofficialsdashboard.fxml");

    }
}