package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class StandingsController {

    @FXML
    private ComboBox<String> tournamentComboBox;
    @FXML
    private TableView<StandingModel> standingTable;
    @FXML
    private TableColumn<StandingModel, Integer> rankColumn;
    @FXML
    private TableColumn<StandingModel, String> teamColumn;
    @FXML
    private TableColumn<StandingModel, Integer> playedColumn;
    @FXML
    private TableColumn<StandingModel, Integer> pointsColumn;
    private final ArrayList<StandingModel> allStandings = new ArrayList<>();
    @FXML
    public void initialize() {

        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        playedColumn.setCellValueFactory(new PropertyValueFactory<>("played"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        tournamentComboBox.getItems().addAll(
                "Bangladesh Premier League",
                "Federation Cup",
                "Independence Cup"
        );

        allStandings.add(new StandingModel(1, "Bashundhara Kings", 10, 25, "Bangladesh Premier League"));
        allStandings.add(new StandingModel(2, "Abahani Limited", 10, 22, "Bangladesh Premier League"));
        allStandings.add(new StandingModel(3, "Mohammedan SC", 10, 18, "Federation Cup"));

        standingTable.setItems(FXCollections.observableArrayList(allStandings));
    }

    @FXML
    public void viewStandings() {

        String selectedTournament = tournamentComboBox.getValue();

        if (selectedTournament == null) {
            standingTable.setItems(FXCollections.observableArrayList(allStandings));
            return;
        }

        ArrayList<StandingModel> result = new ArrayList<>();
        for (StandingModel s : allStandings) {
            if (s.getTournament().equals(selectedTournament)) {
                result.add(s);
            }
        }

        standingTable.setItems(FXCollections.observableArrayList(result));
    }

    @FXML
    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/SpectatorDashboard.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Spectator Dashboard");
        stage.show();
    }
}