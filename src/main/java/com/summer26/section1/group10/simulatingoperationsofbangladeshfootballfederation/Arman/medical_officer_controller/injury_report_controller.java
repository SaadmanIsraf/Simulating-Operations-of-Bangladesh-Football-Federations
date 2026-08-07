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

public class injury_report_controller {

    @FXML
    private TableColumn<InjuryReport, Integer> player_id_column;
    @FXML
    private TableColumn<InjuryReport, String> injury_type_column;
    @FXML
    private TableColumn<InjuryReport, LocalDate> injury_date_column;
    @FXML
    private TableColumn<InjuryReport, String> affected_body_part_column;
    @FXML
    private TableColumn<InjuryReport, String> initial_fitness_status_column;
    @FXML
    private TableColumn<InjuryReport, String> severity_column;
    @FXML
    private TableColumn<InjuryReport, String> additional_notes_column;
    @FXML
    private TableView<InjuryReport> Injurt_report_tableview;
    @FXML
    private TextArea player_details_textarea;
    @FXML
    private TextArea additional_notes_textfield;
    @FXML
    private DatePicker injury_date_datepicker;
    @FXML
    private TextField search_by_player_Id_textfield;
    @FXML
    private TextField player_id_text_field;
    @FXML
    private ComboBox<String> initial_fitness_status_combobox;
    @FXML
    private ComboBox<String> injury_type_combobox;
    @FXML
    private ComboBox<String> severity_combobox;
    @FXML
    private ComboBox<String> affected_body_part_combobox;

    private final ArrayList<Player> playerList = new ArrayList<>();
    private static final String PLAYER_FILE_NAME = "players.bin";

    @FXML
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

        severity_combobox.getItems().addAll("Minor", "Moderate", "Severe");
        initial_fitness_status_combobox.getItems().addAll("Unfit", "Recovering");

        player_id_column.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        injury_type_column.setCellValueFactory(new PropertyValueFactory<>("injuryType"));
        injury_date_column.setCellValueFactory(new PropertyValueFactory<>("injuryDate"));
        affected_body_part_column.setCellValueFactory(new PropertyValueFactory<>("affectedBodyPart"));
        initial_fitness_status_column.setCellValueFactory(new PropertyValueFactory<>("fitnessStatus"));
        severity_column.setCellValueFactory(new PropertyValueFactory<>("severity"));
        additional_notes_column.setCellValueFactory(new PropertyValueFactory<>("additionalNotes"));

        Injurt_report_tableview.getItems().setAll(InjuryReportManager.getInjuryReportList());

        player_details_textarea.setWrapText(true);
        additional_notes_textfield.setWrapText(true);

        loadPlayersFromFile();
    }

    @FXML
    public void save_button_on_action(ActionEvent actionEvent) {

        if (!loadPlayersFromFile()) {
            return;
        }

        String playerIdText = player_id_text_field.getText().trim();
        String injuryType = injury_type_combobox.getValue();
        LocalDate injuryDate = injury_date_datepicker.getValue();
        String bodyPart = affected_body_part_combobox.getValue();
        String severity = severity_combobox.getValue();
        String fitnessStatus = initial_fitness_status_combobox.getValue();
        String notes = additional_notes_textfield.getText().trim();

        if (playerIdText.isEmpty()) {showAlert(Alert.AlertType.ERROR, "Error", "Player ID cannot be empty.");
            player_id_text_field.requestFocus();
            return;
        }

        if (!playerIdText.matches("\\d+")) {showAlert(Alert.AlertType.ERROR, "Error", "Player ID must contain only numbers.");
            player_id_text_field.requestFocus();
            return;
        }

        if (injuryType == null) {showAlert(Alert.AlertType.ERROR, "Error", "Please select an injury type.");
            return;
        }

        if (injuryDate == null) {showAlert(Alert.AlertType.ERROR, "Error", "Please select an injury date.");
            return;
        }

        if (injuryDate.isAfter(LocalDate.now())) {showAlert(Alert.AlertType.ERROR, "Error", "Injury date cannot be a future date.");
            return;
        }

        if (bodyPart == null) {showAlert(Alert.AlertType.ERROR, "Error", "Please select the affected body part.");
            return;
        }

        if (severity == null) {showAlert(Alert.AlertType.ERROR, "Error", "Please select injury severity.");
            return;
        }

        if (fitnessStatus == null) {showAlert(Alert.AlertType.ERROR, "Error", "Please select initial fitness status.");
            return;
        }

        int playerId = Integer.parseInt(playerIdText);
        Player foundPlayer = findPlayer(playerId);

        if (foundPlayer == null) {showAlert(Alert.AlertType.ERROR, "Player Not Found", "Player does not exist in the system.");
            return;
        }

        for (InjuryReport report : InjuryReportManager.getInjuryReportList()) {
            if (report.getPlayerId() == playerId && report.isActive()) {showAlert(Alert.AlertType.ERROR, "Active Report Found", "This player already has an active injury report.");
                return;
            }
        }

        int reportId = generateReportId();

        InjuryReport injuryReport = new InjuryReport(
                reportId,
                playerId,
                injuryType,
                injuryDate,
                bodyPart,
                severity,
                fitnessStatus,
                notes,
                true
        );

        InjuryReportManager.addReport(injuryReport);
        InjuryReportManager.saveToFile();

        foundPlayer.setFitnessStatus(fitnessStatus);

        if (!savePlayersToFile()) {
            return;
        }

        Injurt_report_tableview.getItems().setAll(InjuryReportManager.getInjuryReportList());
        Injurt_report_tableview.getSelectionModel().select(injuryReport);
        Injurt_report_tableview.scrollTo(injuryReport);

        List<InjuryReport> playerReports = getReportsForPlayer(playerId);
        displayPlayerDetails(foundPlayer, playerReports);

        showAlert(Alert.AlertType.INFORMATION, "Successful", "Injury Report Added Successfully!");

        clearForm();
    }

    @FXML
    public void search_by_player_Id_on_action(ActionEvent actionEvent) {

        if (!loadPlayersFromFile()) {
            return;
        }

        String playerIdText = search_by_player_Id_textfield.getText().trim();

        if (playerIdText.isEmpty()) {showAlert(Alert.AlertType.ERROR, "Error", "Please enter a Player ID.");
            return;
        }

        if (!playerIdText.matches("\\d+")) {showAlert(Alert.AlertType.ERROR, "Error", "Player ID must contain only numbers.");
            return;
        }

        int playerId = Integer.parseInt(playerIdText);
        Player foundPlayer = findPlayer(playerId);

        if (foundPlayer == null) {
            player_details_textarea.setText("Player does not exist in the system.");
            Injurt_report_tableview.getItems().clear();
            showAlert(Alert.AlertType.ERROR, "Player Not Found", "Player does not exist in the system.");
            return;
        }

        List<InjuryReport> playerReports = getReportsForPlayer(playerId);

        Injurt_report_tableview.getItems().setAll(playerReports);
        displayPlayerDetails(foundPlayer, playerReports);

        if (playerReports.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Player Found", "Player found, but no injury report exists.");
        } else {
            InjuryReport latestReport = playerReports.get(playerReports.size() - 1);
            Injurt_report_tableview.getSelectionModel().select(latestReport);
            Injurt_report_tableview.scrollTo(latestReport);
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

    private List<InjuryReport> getReportsForPlayer(int playerId) {

        ArrayList<InjuryReport> playerReports = new ArrayList<>();

        for (InjuryReport report : InjuryReportManager.getInjuryReportList()) {
            if (report.getPlayerId() == playerId) {
                playerReports.add(report);
            }
        }

        return playerReports;
    }

    private int generateReportId() {

        int highestId = 0;

        for (InjuryReport report : InjuryReportManager.getInjuryReportList()) {
            if (report.getReportId() > highestId) {
                highestId = report.getReportId();
            }
        }

        return highestId + 1;
    }

    private void displayPlayerDetails(Player player, List<InjuryReport> reports) {

        String details =
                "Player ID: " + player.getId() + "\n" +
                        "Player Name: " + player.getName() + "\n" +
                        "Team: " + player.getTeamName() + "\n" +
                        "Playing Position: " + player.getPlayingPosition() + "\n" +
                        "Age: " + player.getAge() + "\n" +
                        "Fitness Status: " + player.getFitnessStatus() + "\n" +
                        "Contact Number: " + player.getContactNumber() + "\n\n";

        if (reports.isEmpty()) {
            details += "No injury report found for this player.";
        } else {
            details += "Injury Reports:\n";

            for (InjuryReport report : reports) {

                String notes = report.getAdditionalNotes();

                if (notes == null || notes.isBlank()) {
                    notes = "None";
                }

                details +=
                        "\nReport ID: " + report.getReportId() +
                                "\nInjury Type: " + report.getInjuryType() +
                                "\nInjury Date: " + report.getInjuryDate() +
                                "\nAffected Body Part: " + report.getAffectedBodyPart() +
                                "\nSeverity: " + report.getSeverity() +
                                "\nFitness Status: " + report.getFitnessStatus() +
                                "\nAdditional Notes: " + notes +
                                "\nStatus: " + (report.isActive() ? "Active" : "Inactive") +
                                "\n----------------------------------------\n";
            }
        }

        player_details_textarea.setText(details);
    }

    private boolean loadPlayersFromFile() {

        playerList.clear();

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(PLAYER_FILE_NAME))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?> loadedList) {
                for (Object item : loadedList) {
                    if (item instanceof Player player) {
                        playerList.add(player);
                    }
                }
            }

            if (playerList.isEmpty()) {showAlert(Alert.AlertType.WARNING, "No Players Found", "No player record was found in players.bin.");
                return false;
            }

            return true;

        } catch (FileNotFoundException e) {showAlert(Alert.AlertType.ERROR, "File Error", "players.bin was not found.");
        } catch (IOException | ClassNotFoundException e) {showAlert(Alert.AlertType.ERROR, "File Error", "Could not load player data.");
        }

        return false;
    }

    private boolean savePlayersToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(PLAYER_FILE_NAME))) {

            outputStream.writeObject(new ArrayList<>(playerList));
            return true;

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "File Error", "Could not update players.bin.");
            return false;
        }
    }

    private void clearForm() {

        player_id_text_field.clear();
        injury_type_combobox.getSelectionModel().clearSelection();
        injury_date_datepicker.setValue(null);
        affected_body_part_combobox.getSelectionModel().clearSelection();
        severity_combobox.getSelectionModel().clearSelection();
        initial_fitness_status_combobox.getSelectionModel().clearSelection();
        additional_notes_textfield.clear();
    }

    @FXML
    public void Severity_combobox_on_action(ActionEvent actionEvent) {
    }

    @FXML
    public void injury_type_combobox_on_action(ActionEvent actionEvent) {
    }

    @FXML
    public void initial_fitness_status_combobox_on_action(ActionEvent actionEvent) {
    }

    @FXML
    public void affected_body_part_combobox_on_action(ActionEvent actionEvent) {
    }

    @FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_officer_dashboard.fxml");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}