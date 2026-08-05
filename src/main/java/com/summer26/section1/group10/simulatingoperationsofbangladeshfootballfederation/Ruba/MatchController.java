package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MatchController {

    @FXML
    private ComboBox<String> tournamentComboBox;
    @FXML
    private ComboBox<String> matchComboBox;
    @FXML
    private TableView<MatchModel> matchTable;
    @FXML
    private TableColumn<MatchModel, String> matchColumn;
    @FXML
    private TableColumn<MatchModel, String> dateColumn;
    @FXML
    private TableColumn<MatchModel, String> timeColumn;
    @FXML
    private TableColumn<MatchModel, String> venueColumn;
    @FXML
    private TableColumn<MatchModel, String> scoreColumn;
    @FXML
    private Label match_details_label;

    private final ArrayList<MatchModel> allMatches = new ArrayList<>();

    @FXML
    public void initialize() {

        matchColumn.setCellValueFactory(new PropertyValueFactory<>("match"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        venueColumn.setCellValueFactory(new PropertyValueFactory<>("venue"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        tournamentComboBox.getItems().addAll(
                "BFF Championship",
                "National League",
                "Federation Cup"
        );

        matchComboBox.getItems().addAll(
                "Bangladesh vs India",
                "Bangladesh vs Nepal",
                "Bangladesh vs Bhutan"
        );

        allMatches.add(new MatchModel(
                "Bangladesh vs India", "10 August 2026", "5:00 PM",
                "Bangabandhu Stadium", "2 - 1", "BFF Championship"
        ));

        allMatches.add(new MatchModel(
                "Bangladesh vs Nepal", "15 August 2026", "6:00 PM",
                "Sylhet Stadium", "1 - 1", "National League"
        ));

        allMatches.add(new MatchModel(
                "Bangladesh vs Bhutan", "20 August 2026", "7:00 PM",
                "Dhaka Stadium", "3 - 0", "Federation Cup"
        ));

        matchTable.setItems(FXCollections.observableArrayList(allMatches));

        tournamentComboBox.setOnAction(actionEvent -> viewSchedule());
    }

    @FXML
    public void viewSchedule() {

        matchTable.getColumns().setAll(matchColumn, dateColumn, timeColumn, venueColumn, scoreColumn);

        String selectedTournament = tournamentComboBox.getValue();

        if (selectedTournament == null) {
            matchTable.setItems(FXCollections.observableArrayList(allMatches));
            match_details_label.setText("Showing Match Schedule (all tournaments)");
            return;
        }

        ArrayList<MatchModel> result = new ArrayList<>();
        for (MatchModel m : allMatches) {
            if (m.getTournament().equals(selectedTournament)) {
                result.add(m);
            }
        }

        matchTable.setItems(FXCollections.observableArrayList(result));
        match_details_label.setText("Showing Schedule for: " + selectedTournament);
    }

    @FXML
    public void viewScore() {

        matchTable.getColumns().setAll(matchColumn, scoreColumn);

        String selectedMatch = matchComboBox.getValue();

        if (selectedMatch == null) {
            showValidationAlert("Please select a match first to view its score.");
            return;
        }

        ArrayList<MatchModel> result = new ArrayList<>();
        for (MatchModel m : allMatches) {
            if (m.getMatch().equals(selectedMatch)) {
                result.add(m);
            }
        }

        matchTable.setItems(FXCollections.observableArrayList(result));
        match_details_label.setText("Showing Score for: " + selectedMatch);
    }

    @FXML
    public void viewVenue() {

        matchTable.getColumns().setAll(matchColumn, dateColumn, venueColumn);

        String selectedMatch = matchComboBox.getValue();

        if (selectedMatch == null) {
            showValidationAlert("Please select a match first to view venue and date.");
            return;
        }

        ArrayList<MatchModel> result = new ArrayList<>();
        for (MatchModel m : allMatches) {
            if (m.getMatch().equals(selectedMatch)) {
                result.add(m);
            }
        }

        matchTable.setItems(FXCollections.observableArrayList(result));
        match_details_label.setText("Showing Venue & Date for: " + selectedMatch);
    }

    private void showValidationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Selection Required");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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