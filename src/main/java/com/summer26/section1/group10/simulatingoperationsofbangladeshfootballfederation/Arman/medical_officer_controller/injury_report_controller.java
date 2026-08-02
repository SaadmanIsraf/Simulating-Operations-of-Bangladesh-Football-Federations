package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.InjuryReport;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.InjuryReportManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    private TextArea player_details_textarea;

    @FXML
    private TextArea additional_notes_textfield;

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

    @FXML
    private TableColumn<InjuryReport, String> additional_notes_column;

    @javafx.fxml.FXML
    private ComboBox<String> initial_fitness_status_combobox;

    @javafx.fxml.FXML
    private ComboBox<String> injury_type_combobox;

    @javafx.fxml.FXML
    private ComboBox<String> severity_combobox;

    @javafx.fxml.FXML
    private ComboBox<String> affected_body_part_combobox;

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

        initial_fitness_status_column.setCellValueFactory(
                new PropertyValueFactory<>("fitnessStatus")
        );

        severity_column.setCellValueFactory(
                new PropertyValueFactory<>("severity")
        );

        additional_notes_column.setCellValueFactory(
                new PropertyValueFactory<>("additionalNotes")
        );

        Injurt_report_tableview.getItems().setAll(
                InjuryReportManager.getInjuryReportList()
        );

        player_details_textarea.setWrapText(true);

        loadPlayersFromFile();
    }

    @javafx.fxml.FXML
    public void save_button_on_action(ActionEvent actionEvent) {

        loadPlayersFromFile();

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

        if (injuryDate.isAfter(LocalDate.now())) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Injury Date",
                    "Injury date cannot be a future date."
            );
            return;
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

        foundPlayer.setFitnessStatus(fitnessStatus);
        savePlayersToFile();

        Injurt_report_tableview.getItems().setAll(
                InjuryReportManager.getInjuryReportList()
        );

        Injurt_report_tableview.refresh();

        displayPlayerAndInjuryDetails(
                foundPlayer,
                injuryReport
        );

        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Injury Report Added Successfully!"
        );

        clearFormFields();
    }

    @javafx.fxml.FXML
    public void search_by_player_Id_on_action(ActionEvent actionEvent) {

        loadPlayersFromFile();

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

        Player foundPlayer = null;

        for (Player player : playerList) {

            if (player.getId() == playerId) {
                foundPlayer = player;
                break;
            }
        }

        if (foundPlayer == null) {

            player_details_textarea.setText(
                    "Player does not exist in the system."
            );

            Injurt_report_tableview.getItems().clear();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Not Found",
                    "Player does not exist in the system."
            );
            return;
        }

        ArrayList<InjuryReport> playerReports =
                new ArrayList<>();

        for (InjuryReport report :
                InjuryReportManager.getInjuryReportList()) {

            if (report.getPlayerId() == playerId) {
                playerReports.add(report);
            }
        }

        Injurt_report_tableview.getItems().setAll(
                playerReports
        );

        Injurt_report_tableview.refresh();

        if (playerReports.isEmpty()) {

            displayPlayerWithoutInjury(foundPlayer);

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Player Found",
                    "Player found. No injury report exists."
            );

        } else {

            InjuryReport latestReport =
                    playerReports.get(playerReports.size() - 1);

            displayPlayerAndAllInjuries(
                    foundPlayer,
                    playerReports
            );

            Injurt_report_tableview.getSelectionModel()
                    .select(latestReport);

            Injurt_report_tableview.scrollTo(latestReport);

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Medical Records Found",
                    "Player details and injury reports loaded successfully."
            );
        }
    }

    private void displayPlayerAndInjuryDetails(
            Player player,
            InjuryReport report) {

        ArrayList<InjuryReport> reports = new ArrayList<>();

        for (InjuryReport r : InjuryReportManager.getInjuryReportList()) {

            if (r.getPlayerId() == player.getId()) {
                reports.add(r);
            }
        }

        displayPlayerAndAllInjuries(player, reports);
    }



    private void displayPlayerAndAllInjuries(
            Player player,
            List<InjuryReport> reports) {

        StringBuilder sb = new StringBuilder();

        sb.append("PLAYER DETAILS\n");
        sb.append("=====================================================\n");
        sb.append("Player ID          : ").append(player.getId()).append("\n");
        sb.append("Player Name        : ").append(player.getName()).append("\n");
        sb.append("Team               : ").append(player.getTeamName()).append("\n");
        sb.append("Playing Position   : ").append(player.getPlayingPosition()).append("\n");
        sb.append("Age                : ").append(player.getAge()).append("\n");
        sb.append("Fitness Status     : ").append(player.getFitnessStatus()).append("\n");
        sb.append("Contact Number     : ").append(player.getContactNumber()).append("\n\n");

        sb.append("PLAYER INJURY REPORTS\n");
        sb.append("=====================================================\n");

        if (reports.isEmpty()) {

            sb.append("No injury reports found.");

        } else {

            int i = 1;

            for (InjuryReport report : reports) {

                sb.append("\nReport #").append(i++).append("\n");
                sb.append("Injury Type        : ").append(report.getInjuryType()).append("\n");
                sb.append("Injury Date        : ").append(report.getInjuryDate()).append("\n");
                sb.append("Affected Body Part : ").append(report.getAffectedBodyPart()).append("\n");
                sb.append("Severity           : ").append(report.getSeverity()).append("\n");
                sb.append("Fitness Status     : ").append(report.getFitnessStatus()).append("\n");
                sb.append("Additional Notes   : ").append(report.getAdditionalNotes()).append("\n");
                sb.append("-----------------------------------------------------\n");
            }
        }

        player_details_textarea.setText(sb.toString());
    }

    private void displayPlayerWithoutInjury(Player player) {

        player_details_textarea.setText(
                "Player Details"
                        + "\nPlayer ID: " + player.getId()
                        + "\nName: " + player.getName()
                        + "\nTeam: " + player.getTeamName()
                        + "\nPosition: " + player.getPlayingPosition()
                        + "\nCurrent Fitness: "
                        + player.getFitnessStatus()
                        + "\n\nNo injury report found."
        );
    }

    @SuppressWarnings("unchecked")
    private void loadPlayersFromFile() {

        playerList.clear();

        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(PLAYER_FILE_NAME))) {

            Object object = in.readObject();

            if (object instanceof ArrayList<?>) {

                playerList.addAll(
                        (ArrayList<Player>) object
                );
            }

        } catch (FileNotFoundException e) {

            System.out.println(
                    "players.bin does not exist."
            );

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
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

            e.printStackTrace();
        }
    }

    private void clearFormFields() {

        player_id_text_field.clear();
        injury_type_combobox.setValue(null);
        injury_date_datepicker.setValue(null);
        affected_body_part_combobox.setValue(null);
        severity_combobox.setValue(null);
        initial_fitness_status_combobox.setValue(null);
        additional_notes_textfield.clear();
    }

    @javafx.fxml.FXML
    public void Severity_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void injury_type_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void initial_fitness_status_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void affected_body_part_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "Arman/medical_officer/medical_officer_dashboard.fxml"
        );
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