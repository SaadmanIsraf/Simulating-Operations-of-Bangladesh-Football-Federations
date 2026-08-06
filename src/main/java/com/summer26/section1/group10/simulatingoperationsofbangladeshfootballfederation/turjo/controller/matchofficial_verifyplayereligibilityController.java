package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.VerifyPlayerEligibility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.VerifyPlayerEligibilityManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class matchofficial_verifyplayereligibilityController {

    @FXML
    private ComboBox<String> matchCB;

    @FXML
    private ComboBox<String> playerCB;

    @FXML
    private TextField yellowCardTF;

    @FXML
    private TextField redCardTF;

    @FXML
    private TextField eligibilityTF;

    @FXML
    private TableView<VerifyPlayerEligibility> playerTable;

    @FXML
    private TableColumn<VerifyPlayerEligibility, String> playerNameCol;

    @FXML
    private TableColumn<VerifyPlayerEligibility, String> matchCol;

    @FXML
    private TableColumn<VerifyPlayerEligibility, Integer> yellowCardCol;

    @FXML
    private TableColumn<VerifyPlayerEligibility, Integer> redCardCol;

    @FXML
    private TableColumn<VerifyPlayerEligibility, String> eligibilityCol;

    @FXML
    public void initialize() {

        matchCB.getItems().addAll(
                "Dhaka FC vs Chittagong FC",
                "Abahani vs Mohammedan",
                "Brothers Union vs Rahmatganj",
                "Bashundhara Kings vs Sheikh Russel"
        );

        playerCB.getItems().addAll(
                "Rakib Hasan",
                "Jamal Bhuyan",
                "Topu Barman",
                "Sohel Rana",
                "Biplu Ahmed",
                "Mamun Miah"
        );

        playerNameCol.setCellValueFactory(
                new PropertyValueFactory<>("playerName"));

        matchCol.setCellValueFactory(
                new PropertyValueFactory<>("match"));

        yellowCardCol.setCellValueFactory(
                new PropertyValueFactory<>("yellowCards"));

        redCardCol.setCellValueFactory(
                new PropertyValueFactory<>("redCards"));

        eligibilityCol.setCellValueFactory(
                new PropertyValueFactory<>("eligibility"));

        playerTable.getItems().setAll(
                VerifyPlayerEligibilityManager.getEligibilityList());
    }

    @FXML
    public void verifyButtonOnAction(ActionEvent actionEvent) {

        String match = matchCB.getValue();
        String player = playerCB.getValue();

        if (match == null || player == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Information",
                    "Please select both Match and Player."
            );

            return;
        }

        int yellowCards = (int) (Math.random() * 3);
        int redCards = (int) (Math.random() * 2);

        String eligibility;

        if (redCards >= 1 || yellowCards >= 2) {
            eligibility = "Not Eligible";
        }
        else {
            eligibility = "Eligible";
        }

        yellowCardTF.setText(String.valueOf(yellowCards));
        redCardTF.setText(String.valueOf(redCards));
        eligibilityTF.setText(eligibility);

        VerifyPlayerEligibility verifyPlayerEligibility =
                new VerifyPlayerEligibility(
                        player,
                        match,
                        yellowCards,
                        redCards,
                        eligibility
                );

        VerifyPlayerEligibilityManager.addEligibility(
                verifyPlayerEligibility);

        VerifyPlayerEligibilityManager.saveToFile();

        playerTable.getItems().setAll(
                VerifyPlayerEligibilityManager.getEligibilityList());

        playerTable.refresh();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Verification Complete",
                "Player eligibility verified successfully."
        );
    }
    @FXML
    public void clearButtonOnAction(ActionEvent actionEvent) {

        matchCB.setValue(null);
        playerCB.setValue(null);

        yellowCardTF.clear();
        redCardTF.clear();
        eligibilityTF.clear();

        playerTable.getSelectionModel().clearSelection();
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/matchofficial/matchofficialsdashboard.fxml"
        );
    }

    @FXML
    public void matchCBOnAction(ActionEvent actionEvent) {

    }

    @FXML
    public void playerCBOnAction(ActionEvent actionEvent) {

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