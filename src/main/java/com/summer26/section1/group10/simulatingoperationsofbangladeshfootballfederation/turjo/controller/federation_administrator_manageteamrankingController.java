package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class federation_administrator_manageteamrankingController {

    @FXML
    private TableColumn<?, ?> rankingtableviewlossescol;
    @FXML
    private TableView<?> rankingtableview;
    @FXML
    private TextField teamnameTF;
    @FXML
    private TextField winsTF;
    @FXML
    private TableColumn<?, ?> rankingtableviewteamnamecol;
    @FXML
    private TextField rankTF;
    @FXML
    private TextField drawTF;
    @FXML
    private TableColumn<?, ?> rankingtableviewpointscol;
    @FXML
    private TextField lossesTF;
    @FXML
    private TableColumn<?, ?> rankingtableviewwinscol;
    @FXML
    private TableColumn<?, ?> rankingtableviewdrawcol;
    @FXML
    private TextField poitsTF;
    @FXML
    private TableColumn<?, ?> rankingtableviewrankcol;
    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {

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

        messageLabel.setText("Team ranking added successfully.");
    }

    @FXML
    public void updaterankOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

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

    }
}