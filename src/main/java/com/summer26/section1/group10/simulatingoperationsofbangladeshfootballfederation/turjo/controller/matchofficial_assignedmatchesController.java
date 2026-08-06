package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.AssignedMatch;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.AssignedMatchManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class matchofficial_assignedmatchesController {

    @FXML
    private TableView<AssignedMatch> assignedMatchesTable;

    @FXML
    private TableColumn<AssignedMatch, String> matchIdCol;

    @FXML
    private TableColumn<AssignedMatch, String> homeTeamCol;

    @FXML
    private TableColumn<AssignedMatch, String> awayTeamCol;

    @FXML
    private TableColumn<AssignedMatch, String> timeCol;

    @FXML
    private TableColumn<AssignedMatch, String> venueCol;

    @FXML
    private TableColumn<AssignedMatch, String> roleCol;

    @FXML
    private TableColumn<AssignedMatch, java.time.LocalDate> dateCol;
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

        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("matchDate"));

        timeCol.setCellValueFactory(
                new PropertyValueFactory<>("matchTime"));

        venueCol.setCellValueFactory(
                new PropertyValueFactory<>("venue"));

        roleCol.setCellValueFactory(
                new PropertyValueFactory<>("role"));

        loadAssignedMatches();
    }

    private void loadAssignedMatches() {

        AssignedMatchManager.loadFromFile();

        assignedMatchesTable.getItems().setAll(
                AssignedMatchManager.getAssignedMatchList());

        assignedMatchesTable.refresh();
    }

    @FXML
    public void refreshButtonOnAction(ActionEvent actionEvent) {

        loadAssignedMatches();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Refreshed",
                "Assigned matches loaded successfully."
        );
    }
    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/matchofficial/dashboardView.fxml"
        );
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