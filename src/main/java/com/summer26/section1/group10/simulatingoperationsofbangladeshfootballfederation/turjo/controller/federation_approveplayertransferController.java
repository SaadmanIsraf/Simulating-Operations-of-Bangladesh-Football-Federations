package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.PlayerTransfer;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.PlayerTransferManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class federation_approveplayertransferController {

    @FXML
    private TextField playerNameTF;

    @FXML
    private TextField fromClubTF;

    @FXML
    private TextField toClubTF;

    @FXML
    private ComboBox<String> decisionCB;

    @FXML
    private TableView<PlayerTransfer> transferTable;

    @FXML
    private TableColumn<PlayerTransfer, String> playerNameCol;

    @FXML
    private TableColumn<PlayerTransfer, String> fromClubCol;

    @FXML
    private TableColumn<PlayerTransfer, String> toClubCol;

    @FXML
    private TableColumn<PlayerTransfer, String> decisionCol;

    @FXML
    public void initialize() {

        decisionCB.getItems().addAll(
                "Approved",
                "Rejected"
        );

        playerNameCol.setCellValueFactory(
                new PropertyValueFactory<>("playerName"));

        fromClubCol.setCellValueFactory(
                new PropertyValueFactory<>("fromClub"));

        toClubCol.setCellValueFactory(
                new PropertyValueFactory<>("toClub"));

        decisionCol.setCellValueFactory(
                new PropertyValueFactory<>("decision"));

        loadTransfers();
    }

    private void loadTransfers() {

        PlayerTransferManager.loadFromFile();

        transferTable.getItems().setAll(
                PlayerTransferManager.getPlayerTransferList());

        transferTable.refresh();
    }

    @FXML
    public void approveButtonOnAction(ActionEvent actionEvent) {

        String playerName = playerNameTF.getText().trim();
        String fromClub = fromClubTF.getText().trim();
        String toClub = toClubTF.getText().trim();
        String decision = decisionCB.getValue();

        if (playerName.isEmpty()
                || fromClub.isEmpty()
                || toClub.isEmpty()
                || decision == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Information",
                    "Please fill in all fields."
            );

            return;
        }

        PlayerTransfer playerTransfer =
                new PlayerTransfer(
                        playerName,
                        fromClub,
                        toClub,
                        decision
                );

        PlayerTransferManager.addPlayerTransfer(playerTransfer);
        PlayerTransferManager.saveToFile();

        loadTransfers();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Success",
                "Player transfer decision saved successfully."
        );
    }
    @FXML
    public void clearButtonOnAction(ActionEvent actionEvent) {

        clearFields();
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/federation_administrator/dashboardView.fxml"
        );
    }

    private void clearFields() {

        playerNameTF.clear();
        fromClubTF.clear();
        toClubTF.clear();
        decisionCB.setValue(null);

        transferTable.getSelectionModel().clearSelection();
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