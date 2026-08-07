package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.InjuryReport;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MedicalOfficer;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.TreatmentPlan;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.InjuryReportManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.MedicalOfficerManager;
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

public class treatment_plan_controller {

    @FXML
    private TextField search_player_id_textfield;
    @FXML
    private TextArea treatment_details_textarea;
    @FXML
    private TextField player_id_textfield;
    @FXML
    private TextField medical_officer_id_textfield;
    @FXML
    private TextField prescribed_medicine_textfield;
    @FXML
    private ComboBox<Integer> session_required_combobox;
    @FXML
    private TextField rest_duration_textfield;
    @FXML
    private TextField dietary_ins_textfield;
    @FXML
    private DatePicker follow_up_datepicker;
    @FXML
    private TableView<TreatmentPlan> Treatmentplan_tableview;
    @FXML
    private TableColumn<TreatmentPlan, Integer> player_id_column;
    @FXML
    private TableColumn<TreatmentPlan, Integer> medical_officer_id_column;
    @FXML
    private TableColumn<TreatmentPlan, String> prescribed_medicine_column;
    @FXML
    private TableColumn<TreatmentPlan, Integer> session_required_column;
    @FXML
    private TableColumn<TreatmentPlan, Integer> rest_duration_column;
    @FXML
    private TableColumn<TreatmentPlan, String> dietary_instruction_column;
    @FXML
    private TableColumn<TreatmentPlan, LocalDate> follow_up_date_column;

    private final List<Player> playerList = new ArrayList<>();
    private static final String PLAYER_FILE_NAME = "players.bin";

    @FXML
    public void initialize() {

        initializeSessionComboBox();
        initializeTableColumns();
        loadPlayersFromFile();
        refreshTreatmentPlanTable();

        treatment_details_textarea.setEditable(false);
        treatment_details_textarea.setWrapText(true);
    }

    private void initializeSessionComboBox() {

        session_required_combobox.getItems().setAll(
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                12, 15, 20
        );
    }

    private void initializeTableColumns() {

        player_id_column.setCellValueFactory(
                new PropertyValueFactory<>("playerId")
        );

        medical_officer_id_column.setCellValueFactory(
                new PropertyValueFactory<>("medicalOfficerId")
        );

        prescribed_medicine_column.setCellValueFactory(
                new PropertyValueFactory<>("prescribedMedicines")
        );

        session_required_column.setCellValueFactory(
                new PropertyValueFactory<>("physiotherapySessionsRequired")
        );

        rest_duration_column.setCellValueFactory(
                new PropertyValueFactory<>("restDurationDays")
        );

        dietary_instruction_column.setCellValueFactory(
                new PropertyValueFactory<>("dietaryInstruction")
        );

        follow_up_date_column.setCellValueFactory(
                new PropertyValueFactory<>("followUpDate")
        );
    }

    private void refreshTreatmentPlanTable() {

        Treatmentplan_tableview.getItems().setAll(
                TreatmentPlanManager.getTreatmentPlanList()
        );

        Treatmentplan_tableview.refresh();
    }

    @FXML
    public void search_button_on_action(ActionEvent actionEvent) {

        String playerIdText =
                search_player_id_textfield.getText().trim();

        treatment_details_textarea.clear();

        if (playerIdText.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Player ID",
                    "Please enter a Player ID."
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
                    "Player ID must be a whole number."
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

        loadPlayersFromFile();

        Player foundPlayer = findPlayerById(playerId);

        if (foundPlayer == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Not Found",
                    "No player exists with Player ID: " + playerId
            );

            return;
        }

        StringBuilder result = new StringBuilder();

        result.append("PLAYER INFORMATION\n");
        result.append("================================================\n");

        result.append("Player ID        : ")
                .append(foundPlayer.getId())
                .append("\n");

        result.append("Player Name      : ")
                .append(safeText(foundPlayer.getName()))
                .append("\n");

        result.append("Team Name        : ")
                .append(safeText(foundPlayer.getTeamName()))
                .append("\n");

        result.append("Playing Position : ")
                .append(safeText(foundPlayer.getPlayingPosition()))
                .append("\n");

        result.append("Player Type      : ")
                .append(safeText(foundPlayer.getPlayerType()))
                .append("\n");

        result.append("Fitness Status   : ")
                .append(safeText(foundPlayer.getFitnessStatus()))
                .append("\n");

        result.append("Eligibility      : ")
                .append(
                        safeText(
                                foundPlayer.getMatchEligibilityStatus()
                        )
                )
                .append("\n\n");

        result.append("TREATMENT HISTORY\n");
        result.append("================================================\n");

        boolean treatmentFound = false;
        int treatmentNumber = 1;

        for (TreatmentPlan treatmentPlan :
                TreatmentPlanManager.getTreatmentPlanList()) {

            if (treatmentPlan.getPlayerId() == playerId) {

                treatmentFound = true;

                result.append("Treatment Plan #")
                        .append(treatmentNumber++)
                        .append("\n");

                result.append("Treatment ID       : ")
                        .append(treatmentPlan.getTreatmentId())
                        .append("\n");

                result.append("Medical Officer ID : ")
                        .append(treatmentPlan.getMedicalOfficerId())
                        .append("\n");

                result.append("Medicines          : ")
                        .append(
                                safeText(
                                        treatmentPlan
                                                .getPrescribedMedicines()
                                )
                        )
                        .append("\n");

                result.append("Physio Sessions    : ")
                        .append(
                                treatmentPlan
                                        .getPhysiotherapySessionsRequired()
                        )
                        .append("\n");

                result.append("Rest Duration      : ")
                        .append(treatmentPlan.getRestDurationDays())
                        .append(" day(s)\n");

                result.append("Diet Instruction   : ")
                        .append(
                                safeText(
                                        treatmentPlan
                                                .getDietaryInstruction()
                                )
                        )
                        .append("\n");

                result.append("Follow-up Date     : ")
                        .append(
                                treatmentPlan.getFollowUpDate() == null
                                        ? "Not available"
                                        : treatmentPlan.getFollowUpDate()
                        )
                        .append("\n");

                result.append("Status             : ")
                        .append(
                                treatmentPlan.isActive()
                                        ? "Active"
                                        : "Inactive"
                        )
                        .append("\n");

                result.append(
                        "------------------------------------------------\n"
                );
            }
        }

        if (!treatmentFound) {

            result.append(
                    "No treatment plan found for this player."
            );
        }

        treatment_details_textarea.setText(
                result.toString()
        );

        treatment_details_textarea.positionCaret(0);
    }

    @FXML
    public void save_button_on_action(ActionEvent actionEvent) {

        String playerIdText =
                player_id_textfield.getText().trim();

        String medicalOfficerIdText =
                medical_officer_id_textfield.getText().trim();

        String prescribedMedicine =
                prescribed_medicine_textfield.getText().trim();

        Integer sessionsRequired =
                session_required_combobox.getValue();

        String restDurationText =
                rest_duration_textfield.getText().trim();

        String dietaryInstruction =
                dietary_ins_textfield.getText().trim();

        LocalDate followUpDate =
                follow_up_datepicker.getValue();

        if (playerIdText.isEmpty()
                || medicalOfficerIdText.isEmpty()
                || prescribedMedicine.isEmpty()
                || sessionsRequired == null
                || restDurationText.isEmpty()
                || dietaryInstruction.isEmpty()
                || followUpDate == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
                    "Please fill in all treatment plan fields."
            );

            return;
        }

        int playerId;
        int medicalOfficerId;
        int restDuration;

        try {
            playerId = Integer.parseInt(playerIdText);
            medicalOfficerId = Integer.parseInt(medicalOfficerIdText);
            restDuration = Integer.parseInt(restDurationText);

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Player ID, Officer ID and rest duration must be whole numbers."
            );

            return;
        }

        if (playerId <= 0 || medicalOfficerId <= 0) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid ID",
                    "Player ID and Officer ID must be greater than zero."
            );

            return;
        }

        if (restDuration <= 0) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Rest Duration",
                    "Rest duration must be greater than zero."
            );

            return;
        }

        if (!followUpDate.isAfter(LocalDate.now())) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Follow-up Date",
                    "Follow-up date must be a future date."
            );

            return;
        }

        loadPlayersFromFile();

        Player foundPlayer = findPlayerById(playerId);

        if (foundPlayer == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Not Found",
                    "Player does not exist in the system."
            );

            return;
        }

        MedicalOfficer foundMedicalOfficer = null;

        for (MedicalOfficer medicalOfficer :
                MedicalOfficerManager.getMedicalOfficerList()) {

            if (medicalOfficer.getId() == medicalOfficerId) {
                foundMedicalOfficer = medicalOfficer;
                break;
            }
        }

        if (foundMedicalOfficer == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Officer Not Found",
                    "Medical Officer ID does not exist."
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

        for (TreatmentPlan treatmentPlan :
                TreatmentPlanManager.getTreatmentPlanList()) {

            if (treatmentPlan.getPlayerId() == playerId
                    && treatmentPlan.isActive()) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Active Treatment Plan",
                        "This player already has an active treatment plan."
                );

                return;
            }
        }

        int treatmentId = generateTreatmentId();

        TreatmentPlan treatmentPlan =
                new TreatmentPlan(
                        treatmentId,
                        playerId,
                        medicalOfficerId,
                        prescribedMedicine,
                        sessionsRequired,
                        restDuration,
                        dietaryInstruction,
                        followUpDate,
                        true
                );

        TreatmentPlanManager.addTreatmentPlan(
                treatmentPlan
        );

        TreatmentPlanManager.saveToFile();

        refreshTreatmentPlanTable();

        Treatmentplan_tableview
                .getSelectionModel()
                .select(treatmentPlan);

        Treatmentplan_tableview.scrollTo(
                treatmentPlan
        );

        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Treatment Plan Prescribed Successfully for Player ID: "
                        + playerId
                        + "."
        );

        clearFields();
    }

    private int generateTreatmentId() {

        int highestTreatmentId = 0;

        for (TreatmentPlan treatmentPlan :
                TreatmentPlanManager.getTreatmentPlanList()) {

            if (treatmentPlan.getTreatmentId()
                    > highestTreatmentId) {

                highestTreatmentId =
                        treatmentPlan.getTreatmentId();
            }
        }

        return highestTreatmentId + 1;
    }

    private Player findPlayerById(int playerId) {

        for (Player player : playerList) {

            if (player.getId() == playerId) {
                return player;
            }
        }

        return null;
    }

    @FXML
    public void session_required_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @FXML
    public void back_button_on_action(
            ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "Arman/medical_officer/medical_officer_dashboard.fxml"
        );
    }

    @SuppressWarnings("unchecked")
    private boolean loadPlayersFromFile() {

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(
                             new FileInputStream(
                                     PLAYER_FILE_NAME
                             ))) {

            playerList.clear();

            Object savedObject =
                    inputStream.readObject();

            if (!(savedObject instanceof ArrayList<?> loadedList)) {

                System.out.println(
                        "players.bin does not contain an ArrayList."
                );

                return false;
            }

            for (Object item : loadedList) {

                if (item instanceof Player player) {
                    playerList.add(player);
                }
            }

            return !playerList.isEmpty();

        } catch (IOException |
                 ClassNotFoundException e) {

            e.printStackTrace();

            System.out.println(
                    "Could not load player data."
            );

            return false;
        }
    }

    private void clearFields() {

        player_id_textfield.clear();
        medical_officer_id_textfield.clear();
        prescribed_medicine_textfield.clear();
        session_required_combobox.setValue(null);
        rest_duration_textfield.clear();
        dietary_ins_textfield.clear();
        follow_up_datepicker.setValue(null);
    }

    private String safeText(String text) {

        if (text == null || text.isBlank()) {
            return "Not available";
        }

        return text;
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