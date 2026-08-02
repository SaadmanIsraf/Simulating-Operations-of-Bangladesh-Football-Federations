package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.InjuryReport;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.InjuryReportManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class injury_report_controller
{
    @javafx.fxml.FXML
    private TableColumn<InjuryReport, Integer> player_id_column;
    @javafx.fxml.FXML
    private TableColumn<InjuryReport, String> affected_body_part_column;
    @javafx.fxml.FXML
    private TableColumn<InjuryReport, String> initial_fitness_status_column;
    @javafx.fxml.FXML
    private Label player_details_label;
    @javafx.fxml.FXML
    private TextField additional_notes_textfield;
    @javafx.fxml.FXML
    private TableView<InjuryReport> Injurt_report_tableview;
    @javafx.fxml.FXML
    private TableColumn<InjuryReport, String> severity_column;
    @javafx.fxml.FXML
    private DatePicker injury_date_datepicker;
    @javafx.fxml.FXML
    private TextField search_by_player_Id_textfield;
    @javafx.fxml.FXML
    private TextField player_id_text_field;
    @javafx.fxml.FXML
    private TableColumn<InjuryReport, String> injury_type_column;
    @javafx.fxml.FXML
    private TableColumn<InjuryReport, LocalDate> injury_date_column;
    @javafx.fxml.FXML
    private ComboBox<String> initial_fitness_status_combobox;
    @javafx.fxml.FXML
    private ComboBox<String> injury_type_combobox;
    @javafx.fxml.FXML
    private ComboBox<String> severity_combobox;
    @javafx.fxml.FXML
    private ComboBox<String> affected_body_part_combobox;

    private Player searchedPlayer;

    private final List<Player> playerList = new ArrayList<>();

    private static final String PLAYER_FILE_NAME = "players.bin";

    @javafx.fxml.FXML
    public void initialize() {

        injury_type_combobox.getItems().addAll(
                "Muscle Tear",
                "Fracture",
                "Sprain",
                "Ligament Injury",
                "Concussion",
                "Cramp",
                "Other"
        );

        affected_body_part_combobox.getItems().addAll(
                "Head",
                "Shoulder",
                "Arm",
                "Hand",
                "Back",
                "Hip",
                "Knee",
                "Leg",
                "Ankle",
                "Foot"
        );

        severity_combobox.getItems().addAll(
                "Minor",
                "Moderate",
                "Severe"
        );

        initial_fitness_status_combobox.getItems().addAll(
                "Unfit",
                "Recovering"
        );

        player_id_column.setCellValueFactory(
                new PropertyValueFactory<>("playerId")
        );

        injury_type_column.setCellValueFactory(
                new PropertyValueFactory<>("injuryType")
        );

        injury_date_column.setCellValueFactory(
                new PropertyValueFactory<>("injuryDate")
        );

        affected_body_part_column.setCellValueFactory(
                new PropertyValueFactory<>("affectedBodyPart")
        );

        severity_column.setCellValueFactory(
                new PropertyValueFactory<>("severity")
        );

        initial_fitness_status_column.setCellValueFactory(
                new PropertyValueFactory<>("fitnessStatus")
        );

        Injurt_report_tableview.getItems().addAll(
                InjuryReportManager.getInjuryReportList()
        );

        loadPlayersFromFile();
    }

    @javafx.fxml.FXML
    public void Severity_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void save_button_on_action(ActionEvent actionEvent) {

        if (searchedPlayer == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Player Not Selected",
                    "Search for a valid player before saving."
            );
            return;
        }

        String playerIdText =
                player_id_text_field.getText().trim();

        String injuryType =
                injury_type_combobox.getValue();

        LocalDate injuryDate =
                injury_date_datepicker.getValue();

        String affectedBodyPart =
                affected_body_part_combobox.getValue();

        String severity =
                severity_combobox.getValue();

        String fitnessStatus =
                initial_fitness_status_combobox.getValue();

        String additionalNotes =
                additional_notes_textfield.getText().trim();

        if (playerIdText.isEmpty()
                || injuryType == null
                || injuryDate == null
                || affectedBodyPart == null
                || severity == null
                || fitnessStatus == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
                    "Please fill in all required fields."
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

        if (searchedPlayer.getId() != playerId) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player ID Mismatch",
                    "The entered Player ID does not match the searched player."
            );
            return;
        }

        if (injuryDate.isAfter(LocalDate.now())) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Injury Date",
                    "Injury date cannot be a future date."
            );
            return;
        }

        for (InjuryReport report :
                InjuryReportManager.getInjuryReportList()) {

            if (report.getPlayerId() == playerId
                    && report.isActive()) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Active Injury Found",
                        "Player already has an active injury report."
                );
                return;
            }
        }

        int reportId =
                InjuryReportManager.getInjuryReportList().size() + 1;

        InjuryReport injuryReport = new InjuryReport(
                reportId,
                playerId,
                injuryType,
                injuryDate,
                affectedBodyPart,
                severity,
                fitnessStatus,
                additionalNotes,
                true
        );

        InjuryReportManager.addReport(injuryReport);
        InjuryReportManager.saveToFile();

        searchedPlayer.setFitnessStatus(fitnessStatus);
        savePlayersToFile();

        Injurt_report_tableview.getItems().add(injuryReport);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Injury Report Added Successfully! Player fitness status updated to "
                        + fitnessStatus + "."
        );

        clearFields();
    }

    @javafx.fxml.FXML
    public void injury_type_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void initial_fitness_status_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "Arman/medical_officer/medical_officer_dashboard.fxml"
        );
    }

    @javafx.fxml.FXML
    public void search_by_player_Id_on_action(ActionEvent actionEvent) {

        String playerIdText =
                search_by_player_Id_textfield.getText().trim();

        if (playerIdText.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
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
                    "Player ID must be a valid whole number."
            );
            return;
        }

        searchedPlayer = null;

        for (Player player : playerList) {

            if (player.getId() == playerId) {
                searchedPlayer = player;
                break;
            }
        }

        if (searchedPlayer == null) {

            player_details_label.setText(
                    "Player does not exist in the system."
            );

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Not Found",
                    "Player does not exist in the system."
            );
            return;
        }

        for (InjuryReport report :
                InjuryReportManager.getInjuryReportList()) {

            if (report.getPlayerId() == playerId
                    && report.isActive()) {

                player_details_label.setText(
                        "Player already has an active injury report."
                );

                showAlert(
                        Alert.AlertType.ERROR,
                        "Active Injury Found",
                        "Player already has an active injury report."
                );
                searchedPlayer = null;
                return;
            }
        }

        player_id_text_field.setText(
                String.valueOf(searchedPlayer.getId())
        );

        player_details_label.setText(
                "Player ID: " + searchedPlayer.getId()
                        + " | Name: " + searchedPlayer.getName()
                        + " | Current Fitness: "
                        + searchedPlayer.getFitnessStatus()
        );

        showAlert(
                Alert.AlertType.INFORMATION,
                "Player Found",
                "Player details loaded successfully."
        );
    }

    @javafx.fxml.FXML
    public void affected_body_part_combobox_on_action(
            ActionEvent actionEvent) {
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
                    "Could not update the player fitness status."
            );
        }
    }

    private void clearFields() {

        search_by_player_Id_textfield.clear();
        player_id_text_field.clear();
        injury_type_combobox.setValue(null);
        injury_date_datepicker.setValue(null);
        affected_body_part_combobox.setValue(null);
        severity_combobox.setValue(null);
        initial_fitness_status_combobox.setValue(null);
        additional_notes_textfield.clear();

        player_details_label.setText(
                "Search for a player to add an injury report."
        );

        searchedPlayer = null;
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