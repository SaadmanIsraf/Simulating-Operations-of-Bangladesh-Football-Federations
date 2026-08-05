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

public class TournamentController {

    @FXML
    private ComboBox<String> tournamentComboBox;
    @FXML
    private TableView<TournamentModel> tournamentTable;
    @FXML
    private TableColumn<TournamentModel, String> tournamentNameColumn;
    @FXML
    private TableColumn<TournamentModel, String> dateColumn;
    @FXML
    private TableColumn<TournamentModel, String> venueColumn;
    @FXML
    private TableColumn<TournamentModel, String> matchColumn;

    private final ArrayList<TournamentModel> allTournaments = new ArrayList<>();

    @FXML
    public void initialize() {

        tournamentNameColumn.setCellValueFactory(new PropertyValueFactory<>("tournamentName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        venueColumn.setCellValueFactory(new PropertyValueFactory<>("venue"));
        matchColumn.setCellValueFactory(new PropertyValueFactory<>("match"));

        tournamentComboBox.getItems().addAll(
                "Bangladesh Premier League",
                "Federation Cup",
                "Independence Cup"
        );

        allTournaments.add(new TournamentModel(
                "Bangladesh Premier League",
                "10 August 2026",
                "Dhaka Stadium",
                "Bashundhara Kings vs Abahani Limited"
        ));

        allTournaments.add(new TournamentModel(
                "Federation Cup",
                "20 September 2026",
                "Sylhet Stadium",
                "Mohammedan SC vs Sheikh Russel"
        ));

        allTournaments.add(new TournamentModel(
                "Independence Cup",
                "15 December 2026",
                "Chittagong Stadium",
                "Abahani Limited vs Rahmatganj"
        ));

        tournamentTable.setItems(FXCollections.observableArrayList(allTournaments));
    }

    @FXML
    public void searchTournament() {

        String selectedTournament = tournamentComboBox.getValue();

        if (selectedTournament == null) {
            tournamentTable.setItems(FXCollections.observableArrayList(allTournaments));
            return;
        }

        ArrayList<TournamentModel> result = new ArrayList<>();
        for (TournamentModel t : allTournaments) {
            if (t.getTournamentName().equals(selectedTournament)) {
                result.add(t);
            }
        }

        tournamentTable.setItems(FXCollections.observableArrayList(result));
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