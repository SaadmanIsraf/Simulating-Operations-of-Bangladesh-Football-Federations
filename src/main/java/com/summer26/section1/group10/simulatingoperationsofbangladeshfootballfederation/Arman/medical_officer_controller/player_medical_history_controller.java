package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.*;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.InjuryReportManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.MedicalIncidentManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.TreatmentPlanManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class player_medical_history_controller {

    @FXML
    private TableColumn<MedicalHistoryRecord, Integer> player_id_column;
    @FXML
    private TableColumn<MedicalHistoryRecord, String> player_name_column;
    @FXML
    private TextField player_id_textfield;
    @FXML
    private TableColumn<MedicalHistoryRecord, LocalDate> date_column;
    @FXML
    private TableColumn<MedicalHistoryRecord, String> treatment_plans_column;
    @FXML
    private TableColumn<MedicalHistoryRecord, String> injury_report_column;
    @FXML
    private TableView<MedicalHistoryRecord> player_mdecial_history_tableview;
    @FXML
    private TableColumn<MedicalHistoryRecord, String> matchday_incident_column;

    private final List<Player> playerList = new ArrayList<>();
    private static final String PLAYER_FILE_NAME = "players.bin";

    @FXML
    public void initialize() {

        player_id_column.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        player_name_column.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        injury_report_column.setCellValueFactory(new PropertyValueFactory<>("injuryReport"));
        date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        treatment_plans_column.setCellValueFactory(new PropertyValueFactory<>("treatmentPlan"));
        matchday_incident_column.setCellValueFactory(new PropertyValueFactory<>("matchDayIncident"));

        loadPlayersFromFile();
    }

    @FXML
    public void search_button_on_action(ActionEvent actionEvent) {

        String playerIdText = player_id_textfield.getText().trim();

        if (playerIdText.isEmpty()) {showAlert(Alert.AlertType.ERROR, "Empty Field", "Please enter a Player ID.");
            return;
        }

        int playerId;

        try {
            playerId = Integer.parseInt(playerIdText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Player ID", "Player ID must be a valid whole number.");
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
            player_mdecial_history_tableview.getItems().clear();
            showAlert(Alert.AlertType.ERROR, "Player Not Found", "Player does not exist in the system.");
            return;
        }

        player_mdecial_history_tableview.getItems().clear();

        boolean recordFound = false;

        for (InjuryReport injuryReport : InjuryReportManager.getInjuryReportList()) {

            if (injuryReport.getPlayerId() == playerId) {

                String treatmentInformation = getTreatmentInformation(playerId);
                String incidentInformation = getIncidentInformation(playerId);

                MedicalHistoryRecord historyRecord = new MedicalHistoryRecord(
                        playerId,
                        foundPlayer.getName(),
                        injuryReport.getInjuryType()
                                + " - "
                                + injuryReport.getSeverity()
                                + " - "
                                + injuryReport.getFitnessStatus(),
                        injuryReport.getInjuryDate(),
                        treatmentInformation,
                        incidentInformation
                );

                player_mdecial_history_tableview.getItems().add(historyRecord);
                recordFound = true;
            }
        }

        if (!recordFound) {

            TreatmentPlan treatmentPlan = getFirstTreatmentPlan(playerId);
            MatchDayMedicalIncident incident = getFirstIncident(playerId);

            if (treatmentPlan != null || incident != null) {

                LocalDate recordDate = null;

                if (incident != null) {
                    recordDate = incident.getMatchDate();
                } else if (treatmentPlan != null) {
                    recordDate = treatmentPlan.getFollowUpDate();
                }

                MedicalHistoryRecord historyRecord = new MedicalHistoryRecord(
                        playerId,
                        foundPlayer.getName(),
                        "No injury report",
                        recordDate,
                        getTreatmentInformation(playerId),
                        getIncidentInformation(playerId)
                );

                player_mdecial_history_tableview.getItems().add(historyRecord);
                recordFound = true;
            }
        }

        if (!recordFound) {showAlert(Alert.AlertType.INFORMATION, "No Record Found", "No medical history found for this Player ID.");
            return;
        }

        showAlert(Alert.AlertType.INFORMATION, "Successful", "Medical history loaded successfully.");
    }

    private String getTreatmentInformation(int playerId) {

        for (TreatmentPlan treatmentPlan : TreatmentPlanManager.getTreatmentPlanList()) {
            if (treatmentPlan.getPlayerId() == playerId) {
                return treatmentPlan.getPrescribedMedicines()
                        + ", Rest: "
                        + treatmentPlan.getRestDurationDays()
                        + " days";
            }
        }

        return "No treatment plan";
    }

    private String getIncidentInformation(int playerId) {

        for (MatchDayMedicalIncident incident : MedicalIncidentManager.getIncidentList()) {
            if (incident.getPlayerId() == playerId) {
                return incident.getIncidentType()
                        + " - "
                        + incident.getActionTaken();
            }
        }

        return "No match-day incident";
    }

    private TreatmentPlan getFirstTreatmentPlan(int playerId) {

        for (TreatmentPlan treatmentPlan : TreatmentPlanManager.getTreatmentPlanList()) {
            if (treatmentPlan.getPlayerId() == playerId) {
                return treatmentPlan;
            }
        }

        return null;
    }

    private MatchDayMedicalIncident getFirstIncident(int playerId) {

        for (MatchDayMedicalIncident incident : MedicalIncidentManager.getIncidentList()) {
            if (incident.getPlayerId() == playerId) {
                return incident;
            }
        }

        return null;
    }

    @FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_officer_dashboard.fxml");
    }

    @SuppressWarnings("unchecked")
    private void loadPlayersFromFile() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(PLAYER_FILE_NAME))) {

            playerList.clear();
            playerList.addAll((ArrayList<Player>) in.readObject());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load player data.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
