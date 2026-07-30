package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class matchofficial_assignedmatchesController {

    @FXML
    private TableColumn<AssignedMatch, String> dateCol;

    @FXML
    private TableColumn<AssignedMatch, String> venueCol;

    @FXML
    private TableColumn<AssignedMatch, String> matchCol;

    @FXML
    private TableColumn<AssignedMatch, String> roleCol;

    @FXML
    private TableColumn<AssignedMatch, String> timeCol;

    @FXML
    private TableView<AssignedMatch> assignedMatchesTable;

    private final ObservableList<AssignedMatch> assignedMatches =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        matchCol.setCellValueFactory(new PropertyValueFactory<>("match"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        venueCol.setCellValueFactory(new PropertyValueFactory<>("venue"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        loadAssignedMatches();
    }

    private void loadAssignedMatches() {

        assignedMatches.clear();

        assignedMatches.add(new AssignedMatch(
                "Abahani Ltd vs Bashundhara Kings",
                "10-08-2026",
                "4:00 PM",
                "National Stadium",
                "Referee"
        ));

        assignedMatches.add(new AssignedMatch(
                "Mohammedan SC vs Sheikh Russel",
                "12-08-2026",
                "6:00 PM",
                "Kings Arena",
                "Assistant Referee"
        ));

        assignedMatches.add(new AssignedMatch(
                "Police FC vs Brothers Union",
                "15-08-2026",
                "5:30 PM",
                "Bir Shrestha Stadium",
                "Fourth Official"
        ));

        assignedMatches.add(new AssignedMatch(
                "Fortis FC vs Rahmatganj",
                "18-08-2026",
                "7:00 PM",
                "Mymensingh Stadium",
                "VAR Official"
        ));

        assignedMatchesTable.setItems(assignedMatches);
    }

    @FXML
    public void refreshOA(ActionEvent actionEvent) {

        loadAssignedMatches();

        showAlert(
                "Refresh",
                "Assigned matches refreshed successfully."
        );
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

        assignedMatchesTable.getSelectionModel().clearSelection();

        showAlert(
                "Back",
                "Returning to previous page."
        );
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static class AssignedMatch {

        private final SimpleStringProperty match;
        private final SimpleStringProperty date;
        private final SimpleStringProperty time;
        private final SimpleStringProperty venue;
        private final SimpleStringProperty role;

        public AssignedMatch(String match, String date, String time, String venue, String role) {
            this.match = new SimpleStringProperty(match);
            this.date = new SimpleStringProperty(date);
            this.time = new SimpleStringProperty(time);
            this.venue = new SimpleStringProperty(venue);
            this.role = new SimpleStringProperty(role);
        }

        public String getMatch() {
            return match.get();
        }

        public String getDate() {
            return date.get();
        }

        public String getTime() {
            return time.get();
        }

        public String getVenue() {
            return venue.get();
        }

        public String getRole() {
            return role.get();
        }
    }
}