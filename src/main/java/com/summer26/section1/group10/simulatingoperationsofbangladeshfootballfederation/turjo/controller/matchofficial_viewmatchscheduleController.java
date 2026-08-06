package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchSchedule;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchScheduleManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class matchofficial_viewmatchscheduleController {

    @FXML
    private TableView<MatchSchedule> scheduleTable;

    @FXML
    private TableColumn<MatchSchedule, String> matchIdCol;

    @FXML
    private TableColumn<MatchSchedule, String> homeTeamCol;

    @FXML
    private TableColumn<MatchSchedule, String> awayTeamCol;

    @FXML
    private TableColumn<MatchSchedule, String> matchDateCol;

    @FXML
    private TableColumn<MatchSchedule, String> matchTimeCol;

    @FXML
    private TableColumn<MatchSchedule, String> venueCol;
    @FXML
    private Button refreshButton;
    @FXML
    private Button backButton;

    @FXML
    public void initialize() {

        matchIdCol.setCellValueFactory(
                new PropertyValueFactory<>("matchId"));

        homeTeamCol.setCellValueFactory(
                new PropertyValueFactory<>("homeTeam"));

        awayTeamCol.setCellValueFactory(
                new PropertyValueFactory<>("awayTeam"));

        matchDateCol.setCellValueFactory(
                new PropertyValueFactory<>("matchDate"));

        matchTimeCol.setCellValueFactory(
                new PropertyValueFactory<>("matchTime"));

        venueCol.setCellValueFactory(
                new PropertyValueFactory<>("venue"));

        loadSchedules();
    }

    private void loadSchedules() {

        MatchScheduleManager.loadFromFile();

        scheduleTable.getItems().setAll(
                MatchScheduleManager.getMatchScheduleList());

        scheduleTable.refresh();
    }

    @FXML
    public void refreshButtonOnAction(ActionEvent actionEvent) {

        loadSchedules();
    }
    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/match_official/matchofficialsdashboard.fxml"
        );
    }

}