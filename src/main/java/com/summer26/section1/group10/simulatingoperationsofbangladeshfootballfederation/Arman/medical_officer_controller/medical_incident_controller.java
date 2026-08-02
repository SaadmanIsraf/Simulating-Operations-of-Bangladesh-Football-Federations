package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MatchDayMedicalIncident;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.MedicalIncidentManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class medical_incident_controller
{
    @javafx.fxml.FXML
    private TableColumn<MatchDayMedicalIncident, String> incident_type_column;
    @javafx.fxml.FXML
    private TableView<MatchDayMedicalIncident> medical_incident_tableview;
    @javafx.fxml.FXML
    private TableColumn<MatchDayMedicalIncident, Integer> player_id_column;
    @javafx.fxml.FXML
    private TextField player_id_textfield;
    @javafx.fxml.FXML
    private TableColumn<MatchDayMedicalIncident, String> action_taken_column;
    @javafx.fxml.FXML
    private TableColumn<MatchDayMedicalIncident, String> severity_column;
    @javafx.fxml.FXML
    private DatePicker match_date_datepicker;
    @javafx.fxml.FXML
    private TableColumn<MatchDayMedicalIncident, LocalDate> match_date_column;
    @javafx.fxml.FXML
    private ComboBox<String> incident_type_combobox;
    @javafx.fxml.FXML
    private ComboBox<String> action_taken_combobox;
    @javafx.fxml.FXML
    private ComboBox<String> severity_combobox;

    private final List<Player> playerList = new ArrayList<>();
    private static final String PLAYER_FILE_NAME = "players.bin";

    @javafx.fxml.FXML
    public void initialize() {

        incident_type_combobox.getItems().addAll(
                "Injury",
                "Cramp",
                "Concussion",
                "Emergency"
        );

        severity_combobox.getItems().addAll(
                "Minor",
                "Moderate",
                "Severe"
        );

        action_taken_combobox.getItems().addAll(
                "First Aid",
                "Substitution",
                "Hospitalization"
        );

        player_id_column.setCellValueFactory(
                new PropertyValueFactory<>("playerId")
        );

        match_date_column.setCellValueFactory(
                new PropertyValueFactory<>("matchDate")
        );

        incident_type_column.setCellValueFactory(
                new PropertyValueFactory<>("incidentType")
        );

        severity_column.setCellValueFactory(
                new PropertyValueFactory<>("severity")
        );

        action_taken_column.setCellValueFactory(
                new PropertyValueFactory<>("actionTaken")
        );

        medical_incident_tableview.getItems().addAll(
                MedicalIncidentManager.getIncidentList()
        );

        loadPlayersFromFile();
    }

    @javafx.fxml.FXML
    public void severity_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void incident_type_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void submit_button_on_action(ActionEvent actionEvent) {

        String playerIdText = player_id_textfield.getText().trim();
        LocalDate matchDate = match_date_datepicker.getValue();
        String incidentType = incident_type_combobox.getValue();
        String severity = severity_combobox.getValue();
        String actionTaken = action_taken_combobox.getValue();

        if (playerIdText.isEmpty()
                || matchDate == null
                || incidentType == null
                || severity == null
                || actionTaken == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
                    "Please fill in all incident fields."
            );
            return;
        }

        int playerId;

        try {
            playerId = Integer.parseInt(playerIdText);

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Player ID",
                    "Player ID must be a valid whole number."
            );
            return;
        }

        if (playerId <= 0) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Player ID",
                    "Player ID must be greater than zero."
            );
            return;
        }

        if (matchDate.isAfter(LocalDate.now())) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Match Date",
                    "Match date cannot be a future date."
            );
            return;
        }

        Player foundPlayer = null;

        for (Player player : playerList) {

            if (player.getId() == playerId) {
                foundPlayer = player;
                break;
            }
        }

        if (foundPlayer == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Not Found",
                    "Player does not exist in the system."
            );
            return;
        }

        int incidentId =
                MedicalIncidentManager.getIncidentList().size() + 1;

        MatchDayMedicalIncident incident =
                new MatchDayMedicalIncident(
                        incidentId,
                        playerId,
                        matchDate,
                        incidentType,
                        severity,
                        actionTaken
                );

        MedicalIncidentManager.addIncident(incident);
        MedicalIncidentManager.saveToFile();

        medical_incident_tableview.getItems().add(incident);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Match Day Incident Recorded Successfully for Player ID: "
                        + playerId + "."
        );

        clearFields();
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "Arman/medical_officer/medical_officer_dashboard.fxml"
        );
    }

    @javafx.fxml.FXML
    public void action_taken_on_action(ActionEvent actionEvent) {
    }

    @SuppressWarnings("unchecked")
    private void loadPlayersFromFile() {

        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(PLAYER_FILE_NAME))) {

            playerList.clear();

            playerList.addAll(
                    (ArrayList<Player>) in.readObject()
            );

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Could not load player data.");
        }
    }

    private void clearFields() {

        player_id_textfield.clear();
        match_date_datepicker.setValue(null);
        incident_type_combobox.setValue(null);
        severity_combobox.setValue(null);
        action_taken_combobox.setValue(null);
    }

    private void showAlert(
            Alert.AlertType alertType,
            String title,
            String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}