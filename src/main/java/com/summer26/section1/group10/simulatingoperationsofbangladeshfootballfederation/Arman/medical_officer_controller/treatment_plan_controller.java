package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.InjuryReport;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MedicalOfficer;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.TreatmentPlan;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.InjuryReportManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.TreatmentPlanManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class treatment_plan_controller {

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

    @FXML
    private TableView<TreatmentPlan> Treatmentplan_tableview;

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
    private TextField search_player_id_textfield;

    @FXML
    private TextArea treatment_details_textarea;

    private final List<Player> playerList = new ArrayList<>();

    private final List<MedicalOfficer> medicalOfficerList =
            new ArrayList<>();

    private static final String PLAYER_FILE_NAME =
            "players.bin";

    private static final String MEDICAL_OFFICER_FILE_NAME =
            "medical-officers.bin";

    @FXML
    public void initialize() {

        session_required_combobox.getItems().setAll(
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                12, 15, 20
        );

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
                new PropertyValueFactory<>(
                        "physiotherapySessionsRequired"
                )
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

        Treatmentplan_tableview.getItems().setAll(
                TreatmentPlanManager.getTreatmentPlanList()
        );

        treatment_details_textarea.setWrapText(true);

        loadPlayersFromFile();
        loadMedicalOfficersFromFile();
    }

    @FXML
    public void save_button_on_action(
            ActionEvent actionEvent) {

        if (!loadPlayersFromFile()) {
            return;
        }

        if (!loadMedicalOfficersFromFile()) {
            return;
        }

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

            playerId =
                    Integer.parseInt(playerIdText);

            medicalOfficerId =
                    Integer.parseInt(medicalOfficerIdText);

            restDuration =
                    Integer.parseInt(restDurationText);

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Player ID, Medical Officer ID and rest duration "
                            + "must be whole numbers."
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

        if (medicalOfficerId <= 0) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Medical Officer ID",
                    "Medical Officer ID must be greater than zero."
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
                    "Follow-up date must be after the current date."
            );
            return;
        }

        Player foundPlayer =
                findPlayer(playerId);

        if (foundPlayer == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Not Found",
                    "No player exists with Player ID: "
                            + playerId
            );
            return;
        }

        MedicalOfficer foundMedicalOfficer =
                findMedicalOfficer(medicalOfficerId);

        if (foundMedicalOfficer == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Medical Officer Not Found",
                    "No Medical Officer exists with ID: "
                            + medicalOfficerId
            );
            return;
        }

        if (!hasActiveInjuryReport(playerId)) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "No Active Injury Report",
                    "The player does not have an active injury report."
            );
            return;
        }

        int treatmentId =
                generateTreatmentId();

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

        Treatmentplan_tableview.getItems().setAll(
                TreatmentPlanManager.getTreatmentPlanList()
        );

        Treatmentplan_tableview.refresh();

        List<TreatmentPlan> playerPlans =
                getTreatmentPlansForPlayer(playerId);

        displayPlayerAndTreatmentPlans(
                foundPlayer,
                playerPlans
        );

        Treatmentplan_tableview
                .getSelectionModel()
                .select(treatmentPlan);

        Treatmentplan_tableview.scrollTo(
                treatmentPlan
        );

        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Treatment plan saved successfully for Player ID: "
                        + playerId
        );

        clearFormFields();
    }

    @FXML
    public void search_button_on_action(
            ActionEvent actionEvent) {

        if (!loadPlayersFromFile()) {
            return;
        }

        if (!loadMedicalOfficersFromFile()) {
            return;
        }

        String playerIdText =
                search_player_id_textfield
                        .getText()
                        .trim();

        if (playerIdText.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
                    "Please enter a Player ID to search."
            );
            return;
        }

        int playerId;

        try {

            playerId =
                    Integer.parseInt(playerIdText);

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

        Player foundPlayer =
                findPlayer(playerId);

        if (foundPlayer == null) {

            treatment_details_textarea.setText(
                    "Player does not exist in the system."
            );

            Treatmentplan_tableview
                    .getItems()
                    .clear();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Not Found",
                    "No player exists with Player ID: "
                            + playerId
            );
            return;
        }

        List<TreatmentPlan> playerPlans =
                getTreatmentPlansForPlayer(playerId);

        Treatmentplan_tableview.getItems().setAll(
                playerPlans
        );

        Treatmentplan_tableview.refresh();

        displayPlayerAndTreatmentPlans(
                foundPlayer,
                playerPlans
        );

        if (playerPlans.isEmpty()) {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Player Found",
                    "Player found, but no treatment plan exists."
            );

        } else {

            TreatmentPlan latestPlan =
                    playerPlans.get(
                            playerPlans.size() - 1
                    );

            Treatmentplan_tableview
                    .getSelectionModel()
                    .select(latestPlan);

            Treatmentplan_tableview.scrollTo(
                    latestPlan
            );

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Treatment Plans Found",
                    "Player details and treatment plans "
                            + "loaded successfully."
            );
        }
    }

    private Player findPlayer(int playerId) {

        for (Player player : playerList) {

            if (player.getId() == playerId) {
                return player;
            }
        }

        return null;
    }

    private MedicalOfficer findMedicalOfficer(
            int medicalOfficerId) {

        for (MedicalOfficer medicalOfficer :
                medicalOfficerList) {

            if (medicalOfficer.getId()
                    == medicalOfficerId) {

                return medicalOfficer;
            }
        }

        return null;
    }

    private boolean hasActiveInjuryReport(
            int playerId) {

        for (InjuryReport injuryReport :
                InjuryReportManager
                        .getInjuryReportList()) {

            if (injuryReport.getPlayerId() == playerId
                    && injuryReport.isActive()) {

                return true;
            }
        }

        return false;
    }

    private List<TreatmentPlan>
    getTreatmentPlansForPlayer(int playerId) {

        List<TreatmentPlan> playerPlans =
                new ArrayList<>();

        for (TreatmentPlan treatmentPlan :
                TreatmentPlanManager
                        .getTreatmentPlanList()) {

            if (treatmentPlan.getPlayerId()
                    == playerId) {

                playerPlans.add(treatmentPlan);
            }
        }

        return playerPlans;
    }

    private int generateTreatmentId() {

        int highestId = 0;

        for (TreatmentPlan treatmentPlan :
                TreatmentPlanManager
                        .getTreatmentPlanList()) {

            if (treatmentPlan.getTreatmentId()
                    > highestId) {

                highestId =
                        treatmentPlan.getTreatmentId();
            }
        }

        return highestId + 1;
    }

    private void displayPlayerAndTreatmentPlans(
            Player player,
            List<TreatmentPlan> treatmentPlans) {

        StringBuilder details =
                new StringBuilder();

        details.append("PLAYER DETAILS\n");

        details.append(
                "=====================================================\n"
        );

        details.append("Player ID          : ")
                .append(player.getId())
                .append("\n");

        details.append("Player Name        : ")
                .append(player.getName())
                .append("\n");

        details.append("Team               : ")
                .append(player.getTeamName())
                .append("\n");

        details.append("Playing Position   : ")
                .append(player.getPlayingPosition())
                .append("\n");

        details.append("Age                : ")
                .append(player.getAge())
                .append("\n");

        details.append("Fitness Status     : ")
                .append(player.getFitnessStatus())
                .append("\n");

        details.append("Contact Number     : ")
                .append(player.getContactNumber())
                .append("\n\n");

        details.append("TREATMENT PLANS\n");

        details.append(
                "=====================================================\n"
        );

        if (treatmentPlans.isEmpty()) {

            details.append(
                    "No treatment plans found for this player."
            );

        } else {

            int planNumber = 1;

            for (TreatmentPlan treatmentPlan :
                    treatmentPlans) {

                details.append("\nPlan #")
                        .append(planNumber++)
                        .append("\n");

                details.append("Treatment ID       : ")
                        .append(
                                treatmentPlan
                                        .getTreatmentId()
                        )
                        .append("\n");

                details.append("Medical Officer ID : ")
                        .append(
                                treatmentPlan
                                        .getMedicalOfficerId()
                        )
                        .append("\n");

                MedicalOfficer medicalOfficer =
                        findMedicalOfficer(
                                treatmentPlan
                                        .getMedicalOfficerId()
                        );

                details.append("Medical Officer    : ")
                        .append(
                                medicalOfficer == null
                                        ? "Unknown"
                                        : medicalOfficer.getName()
                        )
                        .append("\n");

                details.append("Medicine           : ")
                        .append(
                                treatmentPlan
                                        .getPrescribedMedicines()
                        )
                        .append("\n");

                details.append("Physio Sessions    : ")
                        .append(
                                treatmentPlan
                                        .getPhysiotherapySessionsRequired()
                        )
                        .append("\n");

                details.append("Rest Duration      : ")
                        .append(
                                treatmentPlan
                                        .getRestDurationDays()
                        )
                        .append(" days\n");

                details.append("Diet Instructions  : ")
                        .append(
                                treatmentPlan
                                        .getDietaryInstruction()
                        )
                        .append("\n");

                details.append("Follow-up Date     : ")
                        .append(
                                treatmentPlan
                                        .getFollowUpDate()
                        )
                        .append("\n");

                details.append("Plan Status        : ")
                        .append(
                                treatmentPlan.isActive()
                                        ? "Active"
                                        : "Inactive"
                        )
                        .append("\n");

                details.append(
                        "-----------------------------------------------------\n"
                );
            }
        }

        treatment_details_textarea.setText(
                details.toString()
        );

        treatment_details_textarea.positionCaret(0);
    }

    private boolean loadPlayersFromFile() {

        playerList.clear();

        File playerFile =
                new File(PLAYER_FILE_NAME);

        System.out.println(
                "Loading players from: "
                        + playerFile.getAbsolutePath()
        );

        if (!playerFile.exists()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player File Not Found",
                    "players.bin was not found at:\n"
                            + playerFile.getAbsolutePath()
            );

            return false;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(
                             new FileInputStream(
                                     playerFile
                             ))) {

            Object object =
                    inputStream.readObject();

            if (!(object instanceof ArrayList<?>)) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Invalid Player File",
                        "players.bin does not contain an ArrayList."
                );

                return false;
            }

            ArrayList<?> loadedList =
                    (ArrayList<?>) object;

            for (Object item : loadedList) {

                if (item instanceof Player) {

                    playerList.add(
                            (Player) item
                    );
                }
            }

            System.out.println(
                    "Players loaded: "
                            + playerList.size()
            );

            for (Player player : playerList) {

                System.out.println(
                        "Player ID: "
                                + player.getId()
                                + ", Name: "
                                + player.getName()
                );
            }

            if (playerList.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "No Players Found",
                        "players.bin contains no Player records."
                );

                return false;
            }

            return true;

        } catch (InvalidClassException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Incompatible Player File",
                    "players.bin was created using an older "
                            + "Player or User class.\n\n"
                            + "Delete players.bin and save the "
                            + "Player profile again."
            );

        } catch (FileNotFoundException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player File Not Found",
                    "players.bin could not be found."
            );

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player File Error",
                    "Could not load players.bin.\n\n"
                            + e.getClass()
                            .getSimpleName()
                            + ": "
                            + e.getMessage()
            );
        }

        return false;
    }

    private boolean loadMedicalOfficersFromFile() {

        medicalOfficerList.clear();

        File medicalOfficerFile =
                new File(
                        MEDICAL_OFFICER_FILE_NAME
                );

        System.out.println(
                "Loading Medical Officers from: "
                        + medicalOfficerFile
                        .getAbsolutePath()
        );

        if (!medicalOfficerFile.exists()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Medical Officer File Not Found",
                    "medical-officers.bin was not found at:\n"
                            + medicalOfficerFile
                            .getAbsolutePath()
            );

            return false;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(
                             new FileInputStream(
                                     medicalOfficerFile
                             ))) {

            Object object =
                    inputStream.readObject();

            if (!(object instanceof ArrayList<?>)) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Invalid Medical Officer File",
                        "medical-officers.bin does not "
                                + "contain an ArrayList."
                );

                return false;
            }

            ArrayList<?> loadedList =
                    (ArrayList<?>) object;

            for (Object item : loadedList) {

                if (item instanceof MedicalOfficer) {

                    medicalOfficerList.add(
                            (MedicalOfficer) item
                    );
                }
            }

            System.out.println(
                    "Medical Officers loaded: "
                            + medicalOfficerList.size()
            );

            for (MedicalOfficer medicalOfficer :
                    medicalOfficerList) {

                System.out.println(
                        "Medical Officer ID: "
                                + medicalOfficer.getId()
                                + ", Name: "
                                + medicalOfficer.getName()
                );
            }

            if (medicalOfficerList.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "No Medical Officers Found",
                        "medical-officers.bin contains "
                                + "no Medical Officer records."
                );

                return false;
            }

            return true;

        } catch (InvalidClassException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Incompatible Medical Officer File",
                    "medical-officers.bin was created using "
                            + "an older MedicalOfficer or User class.\n\n"
                            + "Delete medical-officers.bin and "
                            + "save the Medical Officer profile again."
            );

        } catch (FileNotFoundException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Medical Officer File Not Found",
                    "medical-officers.bin could not be found."
            );

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Medical Officer File Error",
                    "Could not load medical-officers.bin.\n\n"
                            + e.getClass()
                            .getSimpleName()
                            + ": "
                            + e.getMessage()
            );
        }

        return false;
    }

    private void clearFormFields() {

        player_id_textfield.clear();
        medical_officer_id_textfield.clear();
        prescribed_medicine_textfield.clear();
        session_required_combobox.setValue(null);
        rest_duration_textfield.clear();
        dietary_ins_textfield.clear();
        follow_up_datepicker.setValue(null);
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

    private void showAlert(
            Alert.AlertType alertType,
            String title,
            String message) {

        Alert alert =
                new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}