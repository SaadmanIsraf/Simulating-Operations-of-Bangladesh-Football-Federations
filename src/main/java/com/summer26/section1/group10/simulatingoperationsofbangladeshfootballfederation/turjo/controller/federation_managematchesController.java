package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.Match;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class federation_managematchesController {

    @FXML
    private TextField matchIdTF;
    @FXML
    private ComboBox<String> homeTeamCB;
    @FXML
    private ComboBox<String> awayTeamCB;
    @FXML
    private TextField venueTF;
    @FXML
    private DatePicker matchDateDP;
    @FXML
    private TextField matchTimeTF;
    @FXML
    private ComboBox<String> statusCB;

    @FXML
    private TableView<Match> matchTable;

    @FXML
    private TableColumn<Match, String> matchIdCol;
    @FXML
    private TableColumn<Match, String> homeTeamCol;
    @FXML
    private TableColumn<Match, String> awayTeamCol;
    @FXML
    private TableColumn<Match, String> venueCol;
    @FXML
    private TableColumn<Match, ?> matchDateCol;
    @FXML
    private TableColumn<Match, String> matchTimeCol;
    @FXML
    private TableColumn<Match, String> statusCol;

    @FXML
    public void initialize() {

        homeTeamCB.getItems().addAll(
                "Abahani",
                "Mohammedan",
                "Bashundhara Kings",
                "Sheikh Russel",
                "Rahmatganj",
                "Brothers Union"
        );

        awayTeamCB.getItems().addAll(
                "Abahani",
                "Mohammedan",
                "Bashundhara Kings",
                "Sheikh Russel",
                "Rahmatganj",
                "Brothers Union"
        );

        statusCB.getItems().addAll(
                "Scheduled",
                "Ongoing",
                "Completed",
                "Cancelled"
        );

        matchIdCol.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        homeTeamCol.setCellValueFactory(new PropertyValueFactory<>("homeTeam"));
        awayTeamCol.setCellValueFactory(new PropertyValueFactory<>("awayTeam"));
        venueCol.setCellValueFactory(new PropertyValueFactory<>("venue"));
        matchDateCol.setCellValueFactory(new PropertyValueFactory<>("matchDate"));
        matchTimeCol.setCellValueFactory(new PropertyValueFactory<>("matchTime"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadTable();
    }

    private void loadTable() {
        MatchManager.loadFromFile();
        matchTable.getItems().setAll(MatchManager.getMatchList());
        matchTable.refresh();
    }

    @FXML
    public void addButtonOnAction(ActionEvent event) {

        if (matchIdTF.getText().isEmpty()
                || homeTeamCB.getValue() == null
                || awayTeamCB.getValue() == null
                || venueTF.getText().isEmpty()
                || matchDateDP.getValue() == null
                || matchTimeTF.getText().isEmpty()
                || statusCB.getValue() == null) {

            showAlert(Alert.AlertType.ERROR, "Please fill all fields.");
            return;
        }

        Match match = new Match(
                matchIdTF.getText(),
                homeTeamCB.getValue(),
                awayTeamCB.getValue(),
                venueTF.getText(),
                matchDateDP.getValue(),
                matchTimeTF.getText(),
                statusCB.getValue()
        );

        MatchManager.addMatch(match);
        MatchManager.saveToFile();

        loadTable();
        clearFields();

        showAlert(Alert.AlertType.INFORMATION, "Match added successfully.");
    }

    @FXML
    public void updateButtonOnAction(ActionEvent event) {

        Match match = matchTable.getSelectionModel().getSelectedItem();

        if (match == null) {
            showAlert(Alert.AlertType.WARNING, "Select a match first.");
            return;
        }

        match.setMatchId(matchIdTF.getText());
        match.setHomeTeam(homeTeamCB.getValue());
        match.setAwayTeam(awayTeamCB.getValue());
        match.setVenue(venueTF.getText());
        match.setMatchDate(matchDateDP.getValue());
        match.setMatchTime(matchTimeTF.getText());
        match.setStatus(statusCB.getValue());

        MatchManager.saveToFile();
        loadTable();

        showAlert(Alert.AlertType.INFORMATION, "Match updated successfully.");
    }

    @FXML
    public void deleteButtonOnAction(ActionEvent event) {

        Match match = matchTable.getSelectionModel().getSelectedItem();

        if (match == null) {
            showAlert(Alert.AlertType.WARNING, "Select a match first.");
            return;
        }

        MatchManager.removeMatch(match);
        MatchManager.saveToFile();

        loadTable();
        clearFields();

        showAlert(Alert.AlertType.INFORMATION, "Match deleted successfully.");
    }

    @FXML
    public void clearButtonOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void tableMouseClicked() {

        Match match = matchTable.getSelectionModel().getSelectedItem();

        if (match == null) {
            return;
        }

        matchIdTF.setText(match.getMatchId());
        homeTeamCB.setValue(match.getHomeTeam());
        awayTeamCB.setValue(match.getAwayTeam());
        venueTF.setText(match.getVenue());
        matchDateDP.setValue(match.getMatchDate());
        matchTimeTF.setText(match.getMatchTime());
        statusCB.setValue(match.getStatus());
    }

    @FXML
    public void backButtonOnAction(ActionEvent event) {

        SceneSwitcher.switchTo(
                "turjo/federation_administrator/dashboardView.fxml"
        );
    }

    private void clearFields() {

        matchIdTF.clear();
        homeTeamCB.setValue(null);
        awayTeamCB.setValue(null);
        venueTF.clear();
        matchDateDP.setValue(null);
        matchTimeTF.clear();
        statusCB.setValue(null);

        matchTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String message) {

        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}