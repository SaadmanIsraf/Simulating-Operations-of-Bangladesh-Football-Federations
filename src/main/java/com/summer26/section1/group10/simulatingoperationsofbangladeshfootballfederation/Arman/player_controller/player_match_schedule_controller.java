package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.Managematch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class player_match_schedule_controller {

    @FXML
    private TableView<Managematch> player_match_schedule_tableview;
    @FXML
    private TableColumn<Managematch, String> match_id_column;
    @FXML
    private TableColumn<Managematch, String> home_team_column;
    @FXML
    private TableColumn<Managematch, String> away_team_column;
    @FXML
    private TableColumn<Managematch, String> competition_column;
    @FXML
    private TableColumn<Managematch, String> stadium_column;
    @FXML
    private TableColumn<Managematch, LocalDate> match_date_column;
    @FXML
    private TableColumn<Managematch, String> match_time_column;
    @FXML
    private TableColumn<Managematch, String> status_column;

    private static final String MATCH_FILE_NAME = "Managematches.bin";

    @FXML
    public void initialize() {
        initializeTableColumns();
        loadAllMatches();
    }

    private void initializeTableColumns() {
        match_id_column.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        home_team_column.setCellValueFactory(new PropertyValueFactory<>("hometeam"));
        away_team_column.setCellValueFactory(new PropertyValueFactory<>("awayteam"));
        competition_column.setCellValueFactory(new PropertyValueFactory<>("competition"));
        stadium_column.setCellValueFactory(new PropertyValueFactory<>("stadium"));
        match_date_column.setCellValueFactory(new PropertyValueFactory<>("matchdate"));
        match_time_column.setCellValueFactory(new PropertyValueFactory<>("matchtime"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadAllMatches() {
        Map<String, Managematch> combinedMatchMap = new LinkedHashMap<>();

        for (Managematch defaultMatch : createDefaultMatches()) {
            if (defaultMatch.getMatchId() != null) {
                combinedMatchMap.put(defaultMatch.getMatchId(), defaultMatch);
            }
        }

        ArrayList<Object> savedObjects = BinaryFileUtility.readObjects(MATCH_FILE_NAME);

        if (savedObjects != null) {
            for (Object savedObject : savedObjects) {
                if (savedObject instanceof Managematch savedMatch) {
                    String matchId = savedMatch.getMatchId();

                    if (matchId != null && !matchId.isBlank()) {
                        combinedMatchMap.put(matchId, savedMatch);
                    }
                }
            }
        }

        List<Managematch> combinedMatches =
                new ArrayList<>(combinedMatchMap.values());

        combinedMatches.sort(
                Comparator.comparing(
                        Managematch::getMatchdate,
                        Comparator.nullsLast(Comparator.naturalOrder())
                )
        );

        player_match_schedule_tableview.getItems().setAll(combinedMatches);
        player_match_schedule_tableview.refresh();
    }

    private List<Managematch> createDefaultMatches() {
        List<Managematch> defaultMatches = new ArrayList<>();

        defaultMatches.add(
                new Managematch(
                        "M-001",
                        "Bashundhara Kings",
                        "Abahani Limited Dhaka",
                        "Bangladesh Premier League",
                        "Bashundhara Kings Arena",
                        LocalDate.of(2026, 8, 15),
                        "06:00 PM",
                        "4821",
                        "Scheduled"
                )
        );

        defaultMatches.add(
                new Managematch(
                        "M-002",
                        "Mohammedan Sporting Club",
                        "Sheikh Russel KC",
                        "Federation Cup",
                        "Bangabandhu National Stadium",
                        LocalDate.of(2026, 8, 18),
                        "05:30 PM",
                        "4822",
                        "Scheduled"
                )
        );

        defaultMatches.add(
                new Managematch(
                        "M-003",
                        "Bangladesh Police FC",
                        "Fortis FC",
                        "Independence Cup",
                        "Muktijuddho Sriti Stadium",
                        LocalDate.of(2026, 8, 22),
                        "04:30 PM",
                        "4823",
                        "Scheduled"
                )
        );

        defaultMatches.add(
                new Managematch(
                        "M-004",
                        "Sheikh Jamal Dhanmondi Club",
                        "Chittagong Abahani",
                        "Bangladesh Premier League",
                        "Sheikh Fazlul Haque Mani Stadium",
                        LocalDate.of(2026, 8, 25),
                        "06:30 PM",
                        "4824",
                        "Scheduled"
                )
        );

        defaultMatches.add(
                new Managematch(
                        "M-005",
                        "Brothers Union",
                        "Rahmatganj MFS",
                        "Federation Cup",
                        "Bir Sherestha Shaheed Shipahi Mostafa Kamal Stadium",
                        LocalDate.of(2026, 8, 29),
                        "05:00 PM",
                        "4825",
                        "Scheduled"
                )
        );

        return defaultMatches;
    }

    @FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/player_dashboard.fxml");
    }
}