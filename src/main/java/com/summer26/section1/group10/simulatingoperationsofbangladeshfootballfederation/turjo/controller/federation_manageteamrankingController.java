package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.TeamRanking;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.TeamRankingManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class federation_manageteamrankingController {

    @FXML
    private TextField teamNameTF;

    @FXML
    private TextField winsTF;

    @FXML
    private TextField drawsTF;

    @FXML
    private TextField lossesTF;

    @FXML
    private TextField searchTF;

    @FXML
    private TableView<TeamRanking> rankingTable;

    @FXML
    private TableColumn<TeamRanking, String> teamNameCol;

    @FXML
    private TableColumn<TeamRanking, Integer> winsCol;

    @FXML
    private TableColumn<TeamRanking, Integer> drawsCol;

    @FXML
    private TableColumn<TeamRanking, Integer> lossesCol;

    @FXML
    private TableColumn<TeamRanking, Integer> pointsCol;

    @FXML
    public void initialize() {

        teamNameCol.setCellValueFactory(
                new PropertyValueFactory<>("teamName"));

        winsCol.setCellValueFactory(
                new PropertyValueFactory<>("wins"));

        drawsCol.setCellValueFactory(
                new PropertyValueFactory<>("draws"));

        lossesCol.setCellValueFactory(
                new PropertyValueFactory<>("losses"));

        pointsCol.setCellValueFactory(
                new PropertyValueFactory<>("points"));

        loadRankings();
    }

    private void loadRankings() {

        TeamRankingManager.loadFromFile();

        rankingTable.getItems().setAll(
                TeamRankingManager.getTeamRankingList());

        rankingTable.refresh();
    }

    @FXML
    public void addButtonOnAction(ActionEvent actionEvent) {

        try {

            String teamName = teamNameTF.getText().trim();

            int wins = Integer.parseInt(winsTF.getText().trim());
            int draws = Integer.parseInt(drawsTF.getText().trim());
            int losses = Integer.parseInt(lossesTF.getText().trim());

            if (teamName.isEmpty()) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Missing Information",
                        "Please enter the team name."
                );

                return;
            }

            int points = (wins * 3) + draws;

            TeamRanking ranking = new TeamRanking(
                    teamName,
                    wins,
                    draws,
                    losses,
                    points
            );

            TeamRankingManager.addTeamRanking(ranking);
            TeamRankingManager.saveToFile();

            loadRankings();

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success",
                    "Team ranking added successfully."
            );

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Wins, Draws and Losses must be numbers."
            );
        }
    }

    @FXML
    public void searchButtonOnAction(ActionEvent actionEvent) {

        String search = searchTF.getText().trim().toLowerCase();

        if (search.isEmpty()) {
            loadRankings();
            return;
        }

        rankingTable.getItems().clear();

        for (TeamRanking team :
                TeamRankingManager.getTeamRankingList()) {

            if (team.getTeamName().toLowerCase().contains(search)) {

                rankingTable.getItems().add(team);
            }
        }
    }

    @FXML
    public void updateButtonOnAction(ActionEvent actionEvent) {

        TeamRanking selected =
                rankingTable.getSelectionModel().getSelectedItem();

        if (selected == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "No Selection",
                    "Please select a team from the table."
            );

            return;
        }

        try {

            selected.setTeamName(teamNameTF.getText().trim());

            int wins = Integer.parseInt(winsTF.getText().trim());
            int draws = Integer.parseInt(drawsTF.getText().trim());
            int losses = Integer.parseInt(lossesTF.getText().trim());

            selected.setWins(wins);
            selected.setDraws(draws);
            selected.setLosses(losses);
            selected.setPoints((wins * 3) + draws);

            TeamRankingManager.saveToFile();

            loadRankings();

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Updated",
                    "Team ranking updated successfully."
            );

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Wins, Draws and Losses must be numbers."
            );
        }
    }
    @FXML
    public void clearButtonOnAction(ActionEvent actionEvent) {

        clearFields();
        loadRankings();
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/federation_administrator/federation_dashboard.fxml"
        );
    }

    private void clearFields() {

        teamNameTF.clear();
        winsTF.clear();
        drawsTF.clear();
        lossesTF.clear();
        searchTF.clear();

        rankingTable.getSelectionModel().clearSelection();
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