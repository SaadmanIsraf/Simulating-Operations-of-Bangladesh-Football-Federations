package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchSchedule;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchScheduleManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class matchofficial_viewmatchscheduleController {

    @FXML
    private TextField matchIdTF;
    @FXML
    private TextField homeTeamTF;
    @FXML
    private TextField awayTeamTF;
    @FXML
    private TextField venueTF;
    @FXML
    private TextField timeTF;
    @FXML
    private DatePicker dateDP;

    @FXML
    private TableView<MatchSchedule> scheduleTable;

    @FXML
    private TableColumn<MatchSchedule, String> matchIdCol;
    @FXML
    private TableColumn<MatchSchedule, String> homeTeamCol;
    @FXML
    private TableColumn<MatchSchedule, String> awayTeamCol;
    @FXML
    private TableColumn<MatchSchedule, String> venueCol;
    @FXML
    private TableColumn<MatchSchedule, java.time.LocalDate> dateCol;
    @FXML
    private TableColumn<MatchSchedule, String> timeCol;

    @FXML
    public void initialize() {

        matchIdCol.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        homeTeamCol.setCellValueFactory(new PropertyValueFactory<>("homeTeam"));
        awayTeamCol.setCellValueFactory(new PropertyValueFactory<>("awayTeam"));
        venueCol.setCellValueFactory(new PropertyValueFactory<>("venue"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        loadSchedules();
    }

    private void loadSchedules() {
        MatchScheduleManager.loadFromFile();
        scheduleTable.getItems().setAll(MatchScheduleManager.getScheduleList());
    }

    @FXML
    public void addButtonOnAction(ActionEvent event) {

        if (matchIdTF.getText().isEmpty()
                || homeTeamTF.getText().isEmpty()
                || awayTeamTF.getText().isEmpty()
                || venueTF.getText().isEmpty()
                || timeTF.getText().isEmpty()
                || dateDP.getValue() == null) {

            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields.");
            return;
        }

        MatchSchedule schedule = new MatchSchedule(
                matchIdTF.getText(),
                homeTeamTF.getText(),
                awayTeamTF.getText(),
                venueTF.getText(),
                dateDP.getValue(),
                timeTF.getText()
        );

        MatchScheduleManager.addSchedule(schedule);
        MatchScheduleManager.saveToFile();

        loadSchedules();
        clearFields();

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Match schedule added successfully.");
    }

    @FXML
    public void clearButtonOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void tableMouseClicked() {

        MatchSchedule schedule =
                scheduleTable.getSelectionModel().getSelectedItem();

        if (schedule == null) {
            return;
        }

        matchIdTF.setText(schedule.getMatchId());
        homeTeamTF.setText(schedule.getHomeTeam());
        awayTeamTF.setText(schedule.getAwayTeam());
        venueTF.setText(schedule.getVenue());
        dateDP.setValue(schedule.getDate());
        timeTF.setText(schedule.getTime());
    }

    @FXML
    public void backButtonOnAction(ActionEvent event) {
        SceneSwitcher.switchTo(
                "turjo/match_official/matchofficialsdashboard.fxml"
        );
    }

    private void clearFields() {
        matchIdTF.clear();
        homeTeamTF.clear();
        awayTeamTF.clear();
        venueTF.clear();
        timeTF.clear();
        dateDP.setValue(null);

        scheduleTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type,
                           String title,
                           String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}