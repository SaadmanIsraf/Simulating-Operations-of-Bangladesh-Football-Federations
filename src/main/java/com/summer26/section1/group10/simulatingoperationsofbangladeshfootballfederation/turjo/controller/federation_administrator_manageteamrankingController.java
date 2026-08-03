package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.TeamRanking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class federation_administrator_manageteamrankingController {

    @FXML
    private TableColumn<TeamRanking, String> rankingtableviewlossescol;
    @FXML
    private TableView<TeamRanking> rankingtableview;
    @FXML
    private TextField teamnameTF;
    @FXML
    private TextField winsTF;
    @FXML
    private TableColumn<TeamRanking, String> rankingtableviewteamnamecol;
    @FXML
    private TextField rankTF;
    @FXML
    private TextField drawTF;
    @FXML
    private TableColumn<TeamRanking, String> rankingtableviewpointscol;
    @FXML
    private TextField lossesTF;
    @FXML
    private TableColumn<TeamRanking, String> rankingtableviewwinscol;
    @FXML
    private TableColumn<TeamRanking, String> rankingtableviewdrawcol;
    @FXML
    private TextField poitsTF;
    @FXML
    private TableColumn<TeamRanking, String> rankingtableviewrankcol;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {

        rankingtableviewrankcol.setCellValueFactory(new PropertyValueFactory<TeamRanking, String>("rank"));
        rankingtableviewteamnamecol.setCellValueFactory(new PropertyValueFactory<TeamRanking, String>("teamname"));
        rankingtableviewwinscol.setCellValueFactory(new PropertyValueFactory<TeamRanking, String>("wins"));
        rankingtableviewdrawcol.setCellValueFactory(new PropertyValueFactory<TeamRanking, String>("draw"));
        rankingtableviewlossescol.setCellValueFactory(new PropertyValueFactory<TeamRanking, String>("losses"));
        rankingtableviewpointscol.setCellValueFactory(new PropertyValueFactory<TeamRanking, String>("points"));

        ArrayList<Object> rankingList = BinaryFileUtility.readObjects("Teamrankings.bin");
        for (Object record : rankingList) {
            if (record instanceof TeamRanking teamranking) {
                rankingtableview.getItems().add(teamranking);
            }
        }
    }

    private boolean validateInput() {

        if (teamnameTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter team name.");
            teamnameTF.requestFocus();
            return false;
        }

        if (!teamnameTF.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Team name must contain only letters.");
            teamnameTF.requestFocus();
            return false;
        }

        if (rankTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter rank.");
            rankTF.requestFocus();
            return false;
        }

        if (!rankTF.getText().matches("\\d+")) {
            messageLabel.setText("Rank must contain only numbers.");
            rankTF.requestFocus();
            return false;
        }

        if (winsTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter wins.");
            winsTF.requestFocus();
            return false;
        }

        if (!winsTF.getText().matches("\\d+")) {
            messageLabel.setText("Wins must contain only numbers.");
            winsTF.requestFocus();
            return false;
        }

        if (drawTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter draws.");
            drawTF.requestFocus();
            return false;
        }

        if (!drawTF.getText().matches("\\d+")) {
            messageLabel.setText("Draws must contain only numbers.");
            drawTF.requestFocus();
            return false;
        }

        if (lossesTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter losses.");
            lossesTF.requestFocus();
            return false;
        }

        if (!lossesTF.getText().matches("\\d+")) {
            messageLabel.setText("Losses must contain only numbers.");
            lossesTF.requestFocus();
            return false;
        }

        if (poitsTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter points.");
            poitsTF.requestFocus();
            return false;
        }

        if (!poitsTF.getText().matches("\\d+")) {
            messageLabel.setText("Points must contain only numbers.");
            poitsTF.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void addteamrankOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        TeamRanking teamranking = new TeamRanking(
                rankTF.getText(),
                teamnameTF.getText(),
                winsTF.getText(),
                drawTF.getText(),
                lossesTF.getText(),
                poitsTF.getText());

        rankingtableview.getItems().add(teamranking);
        BinaryFileUtility.writeObjects("Teamrankings.bin", teamranking);

        messageLabel.setText("Team ranking added successfully.");
    }

    @FXML
    public void updaterankOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        TeamRanking selected = rankingtableview.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Please select a team to update.");
            return;
        }

        selected.setRank(rankTF.getText());
        selected.setTeamname(teamnameTF.getText());
        selected.setWins(winsTF.getText());
        selected.setDraw(drawTF.getText());
        selected.setLosses(lossesTF.getText());
        selected.setPoints(poitsTF.getText());

        rankingtableview.refresh();

        messageLabel.setText("Team ranking updated successfully.");
    }

    @FXML
    public void saveOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        messageLabel.setText("Team ranking saved successfully.");
    }

    @FXML
    public void deleteteamOA(ActionEvent actionEvent) {

        if (teamnameTF.getText().trim().isEmpty()) {
            messageLabel.setText("Enter team name to delete.");
            teamnameTF.requestFocus();
            return;
        }

        TeamRanking selected = rankingtableview.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Please select a team to delete.");
            return;
        }

        rankingtableview.getItems().remove(selected);

        messageLabel.setText("Team deleted successfully.");
    }

    @FXML
    public void refreshOA(ActionEvent actionEvent) {

        teamnameTF.clear();
        rankTF.clear();
        winsTF.clear();
        drawTF.clear();
        lossesTF.clear();
        poitsTF.clear();

        messageLabel.setText("Form refreshed.");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/federation_administrator/dashboardView.fxml");
    }
}