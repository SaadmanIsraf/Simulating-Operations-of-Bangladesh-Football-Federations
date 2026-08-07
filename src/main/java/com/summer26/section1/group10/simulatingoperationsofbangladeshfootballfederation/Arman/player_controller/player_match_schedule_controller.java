package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.PlayerMatchSchedule;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class player_match_schedule_controller {

    @FXML
    private TableView<PlayerMatchSchedule> player_match_schedule_tableview;
    @FXML
    private TableColumn<PlayerMatchSchedule, String> match_id_column;
    @FXML
    private TableColumn<PlayerMatchSchedule, String> home_team_column;
    @FXML
    private TableColumn<PlayerMatchSchedule, String> away_team_column;
    @FXML
    private TableColumn<PlayerMatchSchedule, String> competition_column;
    @FXML
    private TableColumn<PlayerMatchSchedule, String> stadium_column;
    @FXML
    private TableColumn<PlayerMatchSchedule, LocalDate> match_date_column;
    @FXML
    private TableColumn<PlayerMatchSchedule, String> match_time_column;
    @FXML
    private TableColumn<PlayerMatchSchedule, String> status_column;

    @FXML
    public void initialize() {
        initializeTableColumns();
        loadDummyMatches();
    }

    private void initializeTableColumns() {
        match_id_column.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        home_team_column.setCellValueFactory(new PropertyValueFactory<>("homeTeam"));
        away_team_column.setCellValueFactory(new PropertyValueFactory<>("awayTeam"));
        competition_column.setCellValueFactory(new PropertyValueFactory<>("competition"));
        stadium_column.setCellValueFactory(new PropertyValueFactory<>("stadium"));
        match_date_column.setCellValueFactory(new PropertyValueFactory<>("matchDate"));
        match_time_column.setCellValueFactory(new PropertyValueFactory<>("matchTime"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadDummyMatches() {
        player_match_schedule_tableview.getItems().setAll(
                new PlayerMatchSchedule(
                        "M-001",
                        "Bashundhara Kings",
                        "Abahani Limited Dhaka",
                        "Bangladesh Premier League",
                        "Bashundhara Kings Arena",
                        LocalDate.of(2026, 8, 15),
                        "06:00 PM",
                        "Scheduled"
                ),
                new PlayerMatchSchedule(
                        "M-002",
                        "Mohammedan Sporting Club",
                        "Sheikh Russel KC",
                        "Federation Cup",
                        "Bangabandhu National Stadium",
                        LocalDate.of(2026, 8, 18),
                        "05:30 PM",
                        "Scheduled"
                ),
                new PlayerMatchSchedule(
                        "M-003",
                        "Bangladesh Police FC",
                        "Fortis FC",
                        "Independence Cup",
                        "Muktijuddho Sriti Stadium",
                        LocalDate.of(2026, 8, 22),
                        "04:30 PM",
                        "Scheduled"
                ),
                new PlayerMatchSchedule(
                        "M-004",
                        "Sheikh Jamal Dhanmondi Club",
                        "Chittagong Abahani",
                        "Bangladesh Premier League",
                        "Sheikh Fazlul Haque Mani Stadium",
                        LocalDate.of(2026, 8, 25),
                        "06:30 PM",
                        "Scheduled"
                ),
                new PlayerMatchSchedule(
                        "M-005",
                        "Brothers Union",
                        "Rahmatganj MFS",
                        "Federation Cup",
                        "Bir Sherestha Shaheed Shipahi Mostafa Kamal Stadium",
                        LocalDate.of(2026, 8, 29),
                        "05:00 PM",
                        "Scheduled"
                )
        );

        player_match_schedule_tableview.refresh();
    }

    @FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/player_dashboard.fxml");
    }
}