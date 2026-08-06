package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.LiveUpdate;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.LiveUpdateManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class federation_postliveupdateController {

    @FXML
    private TextField matchIdTF;

    @FXML
    private TextField minuteTF;

    @FXML
    private ComboBox<String> eventTypeCB;

    @FXML
    private TextField playerNameTF;

    @FXML
    private TextField homeScoreTF;

    @FXML
    private TextField awayScoreTF;

    @FXML
    private TextArea detailsTA;

    @FXML
    private TableView<LiveUpdate> liveUpdateTable;

    @FXML
    private TableColumn<LiveUpdate, String> matchIdCol;

    @FXML
    private TableColumn<LiveUpdate, String> minuteCol;

    @FXML
    private TableColumn<LiveUpdate, String> eventTypeCol;

    @FXML
    private TableColumn<LiveUpdate, String> playerNameCol;

    @FXML
    private TableColumn<LiveUpdate, String> scoreCol;

    @FXML
    private TableColumn<LiveUpdate, String> detailsCol;

    @FXML
    public void initialize() {

        eventTypeCB.getItems().addAll(
                "Goal",
                "Yellow Card",
                "Red Card",
                "Substitution",
                "Penalty",
                "VAR Decision"
        );

        matchIdCol.setCellValueFactory(
                new PropertyValueFactory<>("matchId"));

        minuteCol.setCellValueFactory(
                new PropertyValueFactory<>("minute"));

        eventTypeCol.setCellValueFactory(
                new PropertyValueFactory<>("eventType"));

        playerNameCol.setCellValueFactory(
                new PropertyValueFactory<>("playerName"));

        scoreCol.setCellValueFactory(
                new PropertyValueFactory<>("score"));

        detailsCol.setCellValueFactory(
                new PropertyValueFactory<>("details"));

        loadLiveUpdates();
    }

    private void loadLiveUpdates() {

        LiveUpdateManager.loadFromFile();

        liveUpdateTable.getItems().setAll(
                LiveUpdateManager.getLiveUpdateList());

        liveUpdateTable.refresh();
    }

    @FXML
    public void postButtonOnAction(ActionEvent actionEvent) {

        String matchId = matchIdTF.getText().trim();
        String minute = minuteTF.getText().trim();
        String eventType = eventTypeCB.getValue();
        String playerName = playerNameTF.getText().trim();
        String homeScore = homeScoreTF.getText().trim();
        String awayScore = awayScoreTF.getText().trim();
        String details = detailsTA.getText().trim();

        if (matchId.isEmpty()
                || minute.isEmpty()
                || eventType == null
                || playerName.isEmpty()
                || homeScore.isEmpty()
                || awayScore.isEmpty()
                || details.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Information",
                    "Please fill in all fields."
            );
            return;
        }

        String score = homeScore + "-" + awayScore;

        LiveUpdate update = new LiveUpdate(
                matchId,
                minute,
                eventType,
                playerName,
                score,
                details
        );

        LiveUpdateManager.addLiveUpdate(update);
        LiveUpdateManager.saveToFile();

        loadLiveUpdates();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Success",
                "Live update posted successfully."
        );
    }
    @FXML
    public void clearButtonOnAction(ActionEvent actionEvent) {

        clearFields();
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/federation_administrator/dashboardView.fxml"
        );
    }

    private void clearFields() {

        matchIdTF.clear();
        minuteTF.clear();
        playerNameTF.clear();
        homeScoreTF.clear();
        awayScoreTF.clear();

        eventTypeCB.setValue(null);

        detailsTA.clear();

        liveUpdateTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType alertType,
                           String title,
                           String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}