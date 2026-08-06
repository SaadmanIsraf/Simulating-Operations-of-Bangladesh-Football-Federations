package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.VARReport;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.VARReportManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class matchofficial_logvarreportController {

    @FXML
    private TextField matchIdTF;

    @FXML
    private TextField minuteTF;

    @FXML
    private TextField playerNameTF;

    @FXML
    private ComboBox<String> reviewTypeCB;

    @FXML
    private ComboBox<String> decisionCB;

    @FXML
    private TextArea detailsTA;

    @FXML
    private TableView<VARReport> varReportTable;

    @FXML
    private TableColumn<VARReport, String> matchIdCol;

    @FXML
    private TableColumn<VARReport, String> minuteCol;

    @FXML
    private TableColumn<VARReport, String> playerNameCol;

    @FXML
    private TableColumn<VARReport, String> reviewTypeCol;

    @FXML
    private TableColumn<VARReport, String> decisionCol;

    @FXML
    private TableColumn<VARReport, String> detailsCol;

    @FXML
    public void initialize() {

        reviewTypeCB.getItems().addAll(
                "Goal Review",
                "Penalty Review",
                "Red Card Review",
                "Offside Review"
        );

        decisionCB.getItems().addAll(
                "Confirmed",
                "Overturned",
                "No Review Needed"
        );

        matchIdCol.setCellValueFactory(
                new PropertyValueFactory<>("matchId"));

        minuteCol.setCellValueFactory(
                new PropertyValueFactory<>("minute"));

        playerNameCol.setCellValueFactory(
                new PropertyValueFactory<>("playerName"));

        reviewTypeCol.setCellValueFactory(
                new PropertyValueFactory<>("reviewType"));

        decisionCol.setCellValueFactory(
                new PropertyValueFactory<>("decision"));

        detailsCol.setCellValueFactory(
                new PropertyValueFactory<>("details"));

        loadVarReports();
    }

    private void loadVarReports() {

        VARReportManager.loadFromFile();

        varReportTable.getItems().setAll(
                VARReportManager.getVarReportList());

        varReportTable.refresh();
    }

    @FXML
    public void submitButtonOnAction(ActionEvent actionEvent) {

        String matchId = matchIdTF.getText().trim();
        String minute = minuteTF.getText().trim();
        String playerName = playerNameTF.getText().trim();
        String reviewType = reviewTypeCB.getValue();
        String decision = decisionCB.getValue();
        String details = detailsTA.getText().trim();

        if (matchId.isEmpty()
                || minute.isEmpty()
                || playerName.isEmpty()
                || reviewType == null
                || decision == null
                || details.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Information",
                    "Please fill in all fields."
            );
            return;
        }

        VARReport report = new VARReport(
                matchId,
                minute,
                playerName,
                reviewType,
                decision,
                details
        );

        VARReportManager.addVarReport(report);
        VARReportManager.saveToFile();

        loadVarReports();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Success",
                "VAR report submitted successfully."
        );
    }
    @FXML
    public void clearButtonOnAction(ActionEvent actionEvent) {

        clearFields();
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/match_official/matchofficialsdashboard.fxml"
        );
    }

    private void clearFields() {

        matchIdTF.clear();
        minuteTF.clear();
        playerNameTF.clear();

        reviewTypeCB.setValue(null);
        decisionCB.setValue(null);

        detailsTA.clear();

        varReportTable.getSelectionModel().clearSelection();
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