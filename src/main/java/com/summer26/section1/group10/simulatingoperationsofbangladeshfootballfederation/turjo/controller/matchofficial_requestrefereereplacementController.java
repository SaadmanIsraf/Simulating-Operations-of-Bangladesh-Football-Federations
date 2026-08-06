package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.RefereeReplacementRequest;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.RefereeReplacementRequestManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class matchofficial_requestrefereereplacementController {

    @FXML
    private TextField matchIdTF;

    @FXML
    private TextField currentRefereeTF;

    @FXML
    private TextArea reasonTA;

    @FXML
    private ComboBox<String> priorityCB;

    @FXML
    private ComboBox<String> statusCB;

    @FXML
    private TableView<RefereeReplacementRequest> requestTable;

    @FXML
    private TableColumn<RefereeReplacementRequest, String> matchIdCol;

    @FXML
    private TableColumn<RefereeReplacementRequest, String> refereeCol;

    @FXML
    private TableColumn<RefereeReplacementRequest, String> reasonCol;

    @FXML
    private TableColumn<RefereeReplacementRequest, String> priorityCol;

    @FXML
    private TableColumn<RefereeReplacementRequest, String> statusCol;

    @FXML
    public void initialize() {

        priorityCB.getItems().addAll(
                "Low",
                "Medium",
                "High"
        );

        statusCB.getItems().addAll(
                "Pending",
                "Approved",
                "Rejected"
        );

        matchIdCol.setCellValueFactory(
                new PropertyValueFactory<>("matchId"));

        refereeCol.setCellValueFactory(
                new PropertyValueFactory<>("currentReferee"));

        reasonCol.setCellValueFactory(
                new PropertyValueFactory<>("replacementReason"));

        priorityCol.setCellValueFactory(
                new PropertyValueFactory<>("priority"));

        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("requestStatus"));

        loadRequests();
    }

    private void loadRequests() {

        RefereeReplacementRequestManager.loadFromFile();

        requestTable.getItems().setAll(
                RefereeReplacementRequestManager.getRequestList());

        requestTable.refresh();
    }

    @FXML
    public void submitButtonOnAction(ActionEvent actionEvent) {

        String matchId = matchIdTF.getText().trim();
        String currentReferee = currentRefereeTF.getText().trim();
        String reason = reasonTA.getText().trim();
        String priority = priorityCB.getValue();
        String status = statusCB.getValue();

        if (matchId.isEmpty()
                || currentReferee.isEmpty()
                || reason.isEmpty()
                || priority == null
                || status == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Information",
                    "Please fill in all fields."
            );
            return;
        }

        RefereeReplacementRequest request =
                new RefereeReplacementRequest(
                        matchId,
                        currentReferee,
                        reason,
                        priority,
                        status
                );

        RefereeReplacementRequestManager.addRequest(request);
        RefereeReplacementRequestManager.saveToFile();

        loadRequests();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Success",
                "Replacement request submitted successfully."
        );
    }
    @FXML
    public void clearButtonOnAction(ActionEvent actionEvent) {

        clearFields();
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/match_officials/matchofficialsdashboard.fxml"
        );
    }

    private void clearFields() {

        matchIdTF.clear();
        currentRefereeTF.clear();

        reasonTA.clear();

        priorityCB.setValue(null);
        statusCB.setValue(null);

        requestTable.getSelectionModel().clearSelection();
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