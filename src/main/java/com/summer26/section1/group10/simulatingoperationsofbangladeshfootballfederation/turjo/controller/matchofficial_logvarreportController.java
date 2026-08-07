package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.LogVARReport;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.LogVARReportManager;
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
    private ComboBox<String> incidentCB;
    @FXML
    private ComboBox<String> decisionCB;
    @FXML
    private TextArea commentsTA;

    @FXML
    private TableView<LogVARReport> varTable;

    @FXML
    private TableColumn<LogVARReport, String> matchIdCol;
    @FXML
    private TableColumn<LogVARReport, Integer> minuteCol;
    @FXML
    private TableColumn<LogVARReport, String> playerNameCol;
    @FXML
    private TableColumn<LogVARReport, String> incidentCol;
    @FXML
    private TableColumn<LogVARReport, String> decisionCol;
    @FXML
    private TableColumn<LogVARReport, String> commentsCol;

    @FXML
    public void initialize() {

        incidentCB.getItems().addAll(
                "Goal Review",
                "Penalty Review",
                "Red Card Review",
                "Offside Review",
                "Handball Review"
        );

        decisionCB.getItems().addAll(
                "Confirmed",
                "Overturned",
                "No Review",
                "Review Complete"
        );

        matchIdCol.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        minuteCol.setCellValueFactory(new PropertyValueFactory<>("minute"));
        playerNameCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        incidentCol.setCellValueFactory(new PropertyValueFactory<>("incident"));
        decisionCol.setCellValueFactory(new PropertyValueFactory<>("decision"));
        commentsCol.setCellValueFactory(new PropertyValueFactory<>("comments"));

        loadTable();
    }

    private void loadTable() {
        LogVARReportManager.loadFromFile();
        varTable.getItems().setAll(LogVARReportManager.getReportList());
        varTable.refresh();
    }

    @FXML
    public void addButtonOnAction(ActionEvent event) {

        if (matchIdTF.getText().isEmpty()
                || minuteTF.getText().isEmpty()
                || playerNameTF.getText().isEmpty()
                || incidentCB.getValue() == null
                || decisionCB.getValue() == null
                || commentsTA.getText().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Please fill all fields.");
            return;
        }

        LogVARReport report = new LogVARReport(
                matchIdTF.getText(),
                Integer.parseInt(minuteTF.getText()),
                playerNameTF.getText(),
                incidentCB.getValue(),
                decisionCB.getValue(),
                commentsTA.getText()
        );

        LogVARReportManager.addReport(report);
        LogVARReportManager.saveToFile();

        loadTable();
        clearFields();

        showAlert(Alert.AlertType.INFORMATION,
                "VAR report added successfully.");
    }

    @FXML
    public void updateButtonOnAction(ActionEvent event) {

        LogVARReport report =
                varTable.getSelectionModel().getSelectedItem();

        if (report == null) {
            showAlert(Alert.AlertType.WARNING,
                    "Please select a report.");
            return;
        }

        report.setMatchId(matchIdTF.getText());
        report.setMinute(Integer.parseInt(minuteTF.getText()));
        report.setPlayerName(playerNameTF.getText());
        report.setIncident(incidentCB.getValue());
        report.setDecision(decisionCB.getValue());
        report.setComments(commentsTA.getText());

        LogVARReportManager.saveToFile();

        loadTable();

        showAlert(Alert.AlertType.INFORMATION,
                "VAR report updated successfully.");
    }

    @FXML
    public void deleteButtonOnAction(ActionEvent event) {

        LogVARReport report =
                varTable.getSelectionModel().getSelectedItem();

        if (report == null) {
            showAlert(Alert.AlertType.WARNING,
                    "Please select a report.");
            return;
        }

        LogVARReportManager.removeReport(report);
        LogVARReportManager.saveToFile();

        loadTable();
        clearFields();

        showAlert(Alert.AlertType.INFORMATION,
                "VAR report deleted successfully.");
    }

    @FXML
    public void clearButtonOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void tableMouseClicked() {

        LogVARReport report =
                varTable.getSelectionModel().getSelectedItem();

        if (report == null) {
            return;
        }

        matchIdTF.setText(report.getMatchId());
        minuteTF.setText(String.valueOf(report.getMinute()));
        playerNameTF.setText(report.getPlayerName());
        incidentCB.setValue(report.getIncident());
        decisionCB.setValue(report.getDecision());
        commentsTA.setText(report.getComments());
    }

    @FXML
    public void backButtonOnAction(ActionEvent event) {

        SceneSwitcher.switchTo(
                "turjo/match_officials/matchofficialdashboard.fxml"
        );
    }

    private void clearFields() {

        matchIdTF.clear();
        minuteTF.clear();
        playerNameTF.clear();
        commentsTA.clear();

        incidentCB.setValue(null);
        decisionCB.setValue(null);

        varTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type,
                           String message) {

        Alert alert = new Alert(type);

        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}