//package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;
//
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.InjuryReport;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MedicalOfficer;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.TreatmentPlan;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.InjuryReportManager;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.MedicalOfficerManager;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.TreatmentPlanManager;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
//import javafx.event.ActionEvent;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class treatment_plan_controller
//{
//    @javafx.fxml.FXML
//    private TableColumn<TreatmentPlan, Integer> player_id_column;
//    @javafx.fxml.FXML
//    private TableColumn<TreatmentPlan, Integer> rest_duration_column;
//    @javafx.fxml.FXML
//    private TextField medical_officer_id_textfield;
//    @javafx.fxml.FXML
//    private TextField player_id_textfield;
//    @javafx.fxml.FXML
//    private TableColumn<TreatmentPlan, Integer> session_required_column;
//    @javafx.fxml.FXML
//    private DatePicker follow_up_datepicker;
//    @javafx.fxml.FXML
//    private TextField rest_duration_textfield;
//    @javafx.fxml.FXML
//    private TableColumn<TreatmentPlan, String> prescribed_medicine_column;
//    @javafx.fxml.FXML
//    private TextField prescribed_medicine_textfield;
//    @javafx.fxml.FXML
//    private TableColumn<TreatmentPlan, Integer> medical_officer_id_column;
//    @javafx.fxml.FXML
//    private TableView<TreatmentPlan> Treatmentplan_tableview;
//    @javafx.fxml.FXML
//    private TextField dietary_ins_textfield;
//    @javafx.fxml.FXML
//    private ComboBox<Integer> session_required_combobox;
//
//    private final List<Player> playerList = new ArrayList<>();
//    private static final String PLAYER_FILE_NAME = "players.bin";
//
//    @javafx.fxml.FXML
//    public void initialize() {
//
//        session_required_combobox.getItems().addAll(
//                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
//                12, 15, 20
//        );
//
//        player_id_column.setCellValueFactory(
//                new PropertyValueFactory<>("playerId")
//        );
//
//        medical_officer_id_column.setCellValueFactory(
//                new PropertyValueFactory<>("medicalOfficerId")
//        );
//
//        prescribed_medicine_column.setCellValueFactory(
//                new PropertyValueFactory<>("prescribedMedicines")
//        );
//
//        session_required_column.setCellValueFactory(
//                new PropertyValueFactory<>("physiotherapySessionsRequired")
//        );
//
//        rest_duration_column.setCellValueFactory(
//                new PropertyValueFactory<>("restDurationDays")
//        );
//
//        Treatmentplan_tableview.getItems().addAll(
//                TreatmentPlanManager.getTreatmentPlanList()
//        );
//
//        loadPlayersFromFile();
//    }
//
//    @javafx.fxml.FXML
//    public void save_button_on_action(ActionEvent actionEvent) {
//
//        String playerIdText = player_id_textfield.getText().trim();
//        String medicalOfficerIdText =
//                medical_officer_id_textfield.getText().trim();
//
//        String prescribedMedicine =
//                prescribed_medicine_textfield.getText().trim();
//
//        Integer sessionsRequired =
//                session_required_combobox.getValue();
//
//        String restDurationText =
//                rest_duration_textfield.getText().trim();
//
//        String dietaryInstruction =
//                dietary_ins_textfield.getText().trim();
//
//        LocalDate followUpDate =
//                follow_up_datepicker.getValue();
//
//        if (playerIdText.isEmpty()
//                || medicalOfficerIdText.isEmpty()
//                || prescribedMedicine.isEmpty()
//                || sessionsRequired == null
//                || restDurationText.isEmpty()
//                || dietaryInstruction.isEmpty()
//                || followUpDate == null) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Empty Field",
//                    "Please fill in all treatment plan fields."
//            );
//            return;
//        }
//
//        int playerId;
//        int medicalOfficerId;
//        int restDuration;
//
//        try {
//            playerId = Integer.parseInt(playerIdText);
//            medicalOfficerId = Integer.parseInt(medicalOfficerIdText);
//            restDuration = Integer.parseInt(restDurationText);
//
//        } catch (NumberFormatException e) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Input",
//                    "Player ID, Officer ID and rest duration must be whole numbers."
//            );
//            return;
//        }
//
//        if (playerId <= 0 || medicalOfficerId <= 0) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid ID",
//                    "Player ID and Officer ID must be greater than zero."
//            );
//            return;
//        }
//
//        if (restDuration <= 0) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Rest Duration",
//                    "Rest duration must be greater than zero."
//            );
//            return;
//        }
//
//        if (!followUpDate.isAfter(LocalDate.now())) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Follow-up Date",
//                    "Follow-up date must be a future date."
//            );
//            return;
//        }
//
//        Player foundPlayer = null;
//
//        for (Player player : playerList) {
//
//            if (player.getId() == playerId) {
//                foundPlayer = player;
//                break;
//            }
//        }
//
//        if (foundPlayer == null) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Player Not Found",
//                    "Player does not exist in the system."
//            );
//            return;
//        }
//
//        MedicalOfficer foundMedicalOfficer = null;
//
//        for (MedicalOfficer medicalOfficer :
//                MedicalOfficerManager.getMedicalOfficerList()) {
//
//            if (medicalOfficer.getId() == medicalOfficerId) {
//                foundMedicalOfficer = medicalOfficer;
//                break;
//            }
//        }
//
//        if (foundMedicalOfficer == null) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Officer Not Found",
//                    "Medical Officer ID does not exist."
//            );
//            return;
//        }
//
//        InjuryReport activeInjuryReport = null;
//
//        for (InjuryReport injuryReport :
//                InjuryReportManager.getInjuryReportList()) {
//
//            if (injuryReport.getPlayerId() == playerId
//                    && injuryReport.isActive()) {
//
//                activeInjuryReport = injuryReport;
//                break;
//            }
//        }
//
//        if (activeInjuryReport == null) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "No Active Injury",
//                    "No active injury report found for this player."
//            );
//            return;
//        }
//
//        for (TreatmentPlan treatmentPlan :
//                TreatmentPlanManager.getTreatmentPlanList()) {
//
//            if (treatmentPlan.getPlayerId() == playerId
//                    && treatmentPlan.isActive()) {
//
//                showAlert(
//                        Alert.AlertType.ERROR,
//                        "Active Treatment Plan",
//                        "This player already has an active treatment plan."
//                );
//                return;
//            }
//        }
//
//        int treatmentId =
//                TreatmentPlanManager.getTreatmentPlanList().size() + 1;
//
//        TreatmentPlan treatmentPlan = new TreatmentPlan(
//                treatmentId,
//                playerId,
//                medicalOfficerId,
//                prescribedMedicine,
//                sessionsRequired,
//                restDuration,
//                dietaryInstruction,
//                followUpDate,
//                true
//        );
//
//        TreatmentPlanManager.addTreatmentPlan(treatmentPlan);
//        TreatmentPlanManager.saveToFile();
//
//        Treatmentplan_tableview.getItems().add(treatmentPlan);
//
//        showAlert(
//                Alert.AlertType.INFORMATION,
//                "Successful",
//                "Treatment Plan Prescribed Successfully for Player ID: "
//                        + playerId + "."
//        );
//
//        clearFields();
//    }
//
//    @javafx.fxml.FXML
//    public void session_required_combobox_on_action(ActionEvent actionEvent) {
//    }
//
//    @javafx.fxml.FXML
//    public void back_button_on_action(ActionEvent actionEvent) {
//
//        SceneSwitcher.switchTo(
//                "Arman/medical_officer/medical_officer_dashboard.fxml"
//        );
//    }
//
//    @SuppressWarnings("unchecked")
//    private void loadPlayersFromFile() {
//
//        try (ObjectInputStream in =
//                     new ObjectInputStream(
//                             new FileInputStream(PLAYER_FILE_NAME))) {
//
//            playerList.clear();
//            playerList.addAll(
//                    (ArrayList<Player>) in.readObject()
//            );
//
//        } catch (IOException | ClassNotFoundException e) {
//
//            System.out.println("Could not load player data.");
//        }
//    }
//
//    private void clearFields() {
//
//        player_id_textfield.clear();
//        medical_officer_id_textfield.clear();
//        prescribed_medicine_textfield.clear();
//        session_required_combobox.setValue(null);
//        rest_duration_textfield.clear();
//        dietary_ins_textfield.clear();
//        follow_up_datepicker.setValue(null);
//    }
//
//    private void showAlert(
//            Alert.AlertType alertType,
//            String title,
//            String message) {
//
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//}