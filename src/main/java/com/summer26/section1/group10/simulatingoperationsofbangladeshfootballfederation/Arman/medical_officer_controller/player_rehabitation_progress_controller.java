package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.InjuryReport;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.RehabilitationProgress;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.TreatmentPlan;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.InjuryReportManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.RehabilitationProgressManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.TreatmentPlanManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class player_rehabitation_progress_controller {

    @FXML
    private TableColumn<RehabilitationProgress, Integer> player_id_column;
    @FXML
    private TableColumn<RehabilitationProgress, LocalDate> progress_date_column;
    @FXML
    private TableColumn<RehabilitationProgress, Integer> session_status_column;
    @FXML
    private TextField player_id_textfield;
    @FXML
    private TableView<RehabilitationProgress> player_rehabitation_tableview;
    @FXML
    private TableColumn<RehabilitationProgress, String> note_column;
    @FXML
    private TableColumn<RehabilitationProgress, String> update_fitness_status_column;
    @FXML
    private DatePicker progress_date_datepicker;
    @FXML
    private TableColumn<RehabilitationProgress, Double> recovery_percentage_column;
    @FXML
    private ComboBox<Integer> physiotherapy_sessions_completed_combobox;
    @FXML
    private ComboBox<String> updated_fitness_status_combobox;
    @FXML
    private ComboBox<Double> recovery_percentage_combobox;
    @FXML
    private TextField physical_condition_note_textfield;

    private final List<Player> playerList = new ArrayList<>();
    private static final String PLAYER_FILE_NAME = "players.bin";

    @FXML
    public void initialize() {

        recovery_percentage_combobox.getItems().addAll(
                0.0, 10.0, 20.0, 30.0, 40.0, 50.0,
                60.0, 70.0, 80.0, 90.0, 100.0
        );

        physiotherapy_sessions_completed_combobox.getItems().addAll(
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                12, 15, 20
        );

        updated_fitness_status_combobox.getItems().addAll(
                "Unfit",
                "Recovering",
                "Fit"
        );

        player_id_column.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        progress_date_column.setCellValueFactory(new PropertyValueFactory<>("progressDate"));
        recovery_percentage_column.setCellValueFactory(new PropertyValueFactory<>("recoveryPercentage"));
        update_fitness_status_column.setCellValueFactory(new PropertyValueFactory<>("updatedFitnessStatus"));
        session_status_column.setCellValueFactory(new PropertyValueFactory<>("physiotherapySessionsCompleted"));
        note_column.setCellValueFactory(new PropertyValueFactory<>("physicalConditionNotes"));

        player_rehabitation_tableview.getItems().addAll(
                RehabilitationProgressManager.getProgressList()
        );

        loadPlayersFromFile();
    }

    @FXML
    public void physiotherapy_sessions_completed_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @FXML
    public void save_button_on_action(ActionEvent actionEvent) {

        String playerIdText = player_id_textfield.getText().trim();
        LocalDate progressDate = progress_date_datepicker.getValue();
        Double recoveryPercentage = recovery_percentage_combobox.getValue();
        Integer completedSessions =
                physiotherapy_sessions_completed_combobox.getValue();
        String physicalConditionNote =
                physical_condition_note_textfield.getText().trim();
        String updatedFitnessStatus =
                updated_fitness_status_combobox.getValue();

        if (playerIdText.isEmpty()
                || progressDate == null
                || recoveryPercentage == null
                || completedSessions == null
                || physicalConditionNote.isEmpty()
                || updatedFitnessStatus == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
                    "Please fill in all rehabilitation progress fields."
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

        if (progressDate.isAfter(LocalDate.now())) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Progress Date",
                    "Progress date cannot be a future date."
            );
            return;
        }

        if (recoveryPercentage < 0 || recoveryPercentage > 100) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Recovery Percentage",
                    "Recovery percentage must be between 0 and 100."
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

        InjuryReport activeInjuryReport = null;

        for (InjuryReport injuryReport :
                InjuryReportManager.getInjuryReportList()) {

            if (injuryReport.getPlayerId() == playerId
                    && injuryReport.isActive()) {

                activeInjuryReport = injuryReport;
                break;
            }
        }

        if (activeInjuryReport == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "No Active Injury",
                    "No active injury report found for this player."
            );
            return;
        }

        TreatmentPlan activeTreatmentPlan = null;

        for (TreatmentPlan treatmentPlan :
                TreatmentPlanManager.getTreatmentPlanList()) {

            if (treatmentPlan.getPlayerId() == playerId
                    && treatmentPlan.isActive()) {

                activeTreatmentPlan = treatmentPlan;
                break;
            }
        }

        if (activeTreatmentPlan == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "No Treatment Plan",
                    "No active treatment plan found for this player."
            );
            return;
        }

        if (completedSessions >
                activeTreatmentPlan.getPhysiotherapySessionsRequired()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Session Count",
                    "Completed sessions cannot exceed required sessions."
            );
            return;
        }

        int progressId =
                RehabilitationProgressManager.getProgressList().size() + 1;

        RehabilitationProgress progress = new RehabilitationProgress(
                progressId,
                playerId,
                progressDate,
                recoveryPercentage,
                completedSessions,
                physicalConditionNote,
                updatedFitnessStatus
        );

        RehabilitationProgressManager.addProgress(progress);
        RehabilitationProgressManager.saveToFile();

        foundPlayer.setFitnessStatus(updatedFitnessStatus);
        savePlayersToFile();

        activeInjuryReport.setFitnessStatus(updatedFitnessStatus);

        if (updatedFitnessStatus.equals("Fit")) {
            activeInjuryReport.setActive(false);
            activeTreatmentPlan.setActive(false);
        }

        InjuryReportManager.saveToFile();
        TreatmentPlanManager.saveToFile();

        player_rehabitation_tableview.getItems().add(progress);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Rehabilitation Progress Updated Successfully!"
        );

        clearFields();
    }

    @FXML
    public void recovery_percentage_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @FXML
    public void physical_condition_note_textfield_on_action(
            ActionEvent actionEvent) {
    }

    @FXML
    public void updated_fitness_status_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo(
                "Arman/medical_officer/medical_officer_dashboard.fxml"
        );
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

    private void savePlayersToFile() {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(PLAYER_FILE_NAME))) {

            ArrayList<Player> tempList =
                    new ArrayList<>(playerList);

            out.writeObject(tempList);

        } catch (IOException e) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "File Error",
                    "Could not update player fitness status."
            );
        }
    }

    private void clearFields() {

        player_id_textfield.clear();
        progress_date_datepicker.setValue(null);
        recovery_percentage_combobox.setValue(null);
        physiotherapy_sessions_completed_combobox.setValue(null);
        physical_condition_note_textfield.clear();
        updated_fitness_status_combobox.setValue(null);
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