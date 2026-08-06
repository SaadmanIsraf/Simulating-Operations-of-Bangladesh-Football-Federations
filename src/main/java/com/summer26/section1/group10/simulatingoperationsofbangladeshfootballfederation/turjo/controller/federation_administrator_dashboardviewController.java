package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class federation_administrator_dashboardviewController {

    @FXML
    public void initialize() {

    }

    @FXML
    public void managematchesOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/federation_administrator/managematches.fxml");
    }

    @FXML
    public void manageplayersOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/federation_administrator/manageplayer.fxml");
    }

    @FXML
    public void manageteamrankingOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/federation_administrator/teamranking.fxml");
    }

    @FXML
    public void manageuseraccountOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/federation_administrator/manageuseraccount.fxml");
    }

    @FXML
    public void approveplayertransferOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/federation_administrator/approveplayertransfer.fxml");
    }

    @FXML
    public void publishannounceOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/federation_administrator/publishannouncement.fxml");
    }

    @FXML
    public void uploadmatchhighlightsOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/federation_administrator/uploadmatchhighlights.fxml");
    }

    @FXML
    public void postliveupdatesOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/federation_administrator/postliveupdates.fxml");
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