package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class matchofficial_dashboardController {

    @FXML
    public void initialize() {

    }

    @FXML
    public void registerOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/registarasmatchofficial.fxml");
    }

    @FXML
    public void viewmatchscheduleOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/viewmatchschedule.fxml");
    }

    @FXML
    public void assignedmatchesOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/assignedmatches.fxml");
    }

    @FXML
    public void verifyplayereligibilityOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/verifyplayereligibility.fxml");
    }

    @FXML
    public void recordfoulscardsOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/recordfoulsandcards.fxml");
    }

    @FXML
    public void submitmatchreportOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/SubmitMatchReport.fxml");
    }

    @FXML
    public void logvarreportOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/logvarreport.fxml");
    }

    @FXML
    public void requestrefereereplacementOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/requestrefreereplacement.fxml");
    }

    @FXML
    public void logoutOA(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Logged out successfully.");
        alert.showAndWait();

        SceneSwitcher.switchTo("Login.fxml");
    }
}