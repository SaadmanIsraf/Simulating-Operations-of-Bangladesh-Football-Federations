package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.InjuryReport;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.InjuryReportManager;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    private final List<Player> playerList = new ArrayList<>();

    private static final String PLAYER_FILE_NAME = "players.bin";

    @FXML
    public void initialize() {

        injury_type_combobox.getItems().setAll(
                "Muscle Tear",
                "Fracture",
                "Sprain",
                "Ligament Injury",
                "Concussion",
                "Cramp",
                "Other"
        );

        affected_body_part_combobox.getItems().setAll(
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

        severity_combobox.getItems().setAll(
                "Minor",
                "Moderate",
                "Severe"
        );

        initial_fitness_status_combobox.getItems().setAll(
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
        additional_notes_textfield.setWrapText(true);

        loadPlayersFromFile();
    }

    @FXML
    public void save_button_on_action(ActionEvent actionEvent) {

        if (!loadPlayersFromFile()) {
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

        if (playerId <= 0) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Player ID",
                    "Player ID must be greater than zero."
            );
            return;
        }

        Player foundPlayer = findPlayer(playerId);

        if (foundPlayer == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Not Found",
                    "No player exists with Player ID: " + playerId
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

        int reportId = generateReportId();

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

        if (!savePlayersToFile()) {
            return;
        }

        Injurt_report_tableview.getItems().setAll(
                InjuryReportManager.getInjuryReportList()
        );

        Injurt_report_tableview.refresh();

        List<InjuryReport> playerReports =
                getReportsForPlayer(playerId);

        displayPlayerAndAllInjuries(
                foundPlayer,
                playerReports
        );

        Injurt_report_tableview
                .getSelectionModel()
                .select(injuryReport);

        Injurt_report_tableview.scrollTo(injuryReport);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Injury Report Added Successfully!"
        );

        clearFormFields();
    }

    @FXML
    public void search_by_player_Id_on_action(
            ActionEvent actionEvent) {

        if (!loadPlayersFromFile()) {
            return;
        }

        String playerIdText =
                search_by_player_Id_textfield
                        .getText()
                        .trim();

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

        if (playerId <= 0) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Player ID",
                    "Player ID must be greater than zero."
            );
            return;
        }

        Player foundPlayer = findPlayer(playerId);

        if (foundPlayer == null) {

            player_details_textarea.setText(
                    "Player does not exist in the system."
            );

            Injurt_report_tableview.getItems().clear();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Not Found",
                    "No player exists with Player ID: " + playerId
            );
            return;
        }

        List<InjuryReport> playerReports =
                getReportsForPlayer(playerId);

        Injurt_report_tableview.getItems().setAll(
                playerReports
        );

        Injurt_report_tableview.refresh();

        displayPlayerAndAllInjuries(
                foundPlayer,
                playerReports
        );

        if (playerReports.isEmpty()) {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Player Found",
                    "Player found, but no injury report exists."
            );

        } else {

            InjuryReport latestReport =
                    playerReports.get(
                            playerReports.size() - 1
                    );

            Injurt_report_tableview
                    .getSelectionModel()
                    .select(latestReport);

            Injurt_report_tableview.scrollTo(
                    latestReport
            );

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Medical Records Found",
                    "Player details and injury reports loaded successfully."
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

    private List<InjuryReport> getReportsForPlayer(
            int playerId) {

        List<InjuryReport> playerReports =
                new ArrayList<>();

        for (InjuryReport report :
                InjuryReportManager.getInjuryReportList()) {

            if (report.getPlayerId() == playerId) {
                playerReports.add(report);
            }
        }

        return playerReports;
    }

    private int generateReportId() {

        int highestId = 0;

        for (InjuryReport report :
                InjuryReportManager.getInjuryReportList()) {

            if (report.getReportId() > highestId) {
                highestId = report.getReportId();
            }
        }

        return highestId + 1;
    }

    private void displayPlayerAndAllInjuries(
            Player player,
            List<InjuryReport> reports) {

        StringBuilder details = new StringBuilder();

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

        details.append("PLAYER INJURY REPORTS\n");
        details.append(
                "=====================================================\n"
        );

        if (reports.isEmpty()) {

            details.append(
                    "No injury reports found for this player."
            );

        } else {

            int reportNumber = 1;

            for (InjuryReport report : reports) {

                details.append("\nReport #")
                        .append(reportNumber++)
                        .append("\n");

                details.append("Report ID          : ")
                        .append(report.getReportId())
                        .append("\n");

                details.append("Injury Type        : ")
                        .append(report.getInjuryType())
                        .append("\n");

                details.append("Injury Date        : ")
                        .append(report.getInjuryDate())
                        .append("\n");

                details.append("Affected Body Part : ")
                        .append(report.getAffectedBodyPart())
                        .append("\n");

                details.append("Severity           : ")
                        .append(report.getSeverity())
                        .append("\n");

                details.append("Fitness Status     : ")
                        .append(report.getFitnessStatus())
                        .append("\n");

                details.append("Additional Notes   : ")
                        .append(
                                report.getAdditionalNotes() == null
                                        || report
                                        .getAdditionalNotes()
                                        .isBlank()
                                        ? "None"
                                        : report.getAdditionalNotes()
                        )
                        .append("\n");

                details.append("Status             : ")
                        .append(
                                report.isActive()
                                        ? "Active"
                                        : "Inactive"
                        )
                        .append("\n");

                details.append(
                        "-----------------------------------------------------\n"
                );
            }
        }

        player_details_textarea.setText(
                details.toString()
        );

        player_details_textarea.positionCaret(0);
    }

    @SuppressWarnings("unchecked")
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
                             new FileInputStream(playerFile))) {

            Object object = inputStream.readObject();

            if (!(object instanceof ArrayList<?> loadedList)) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Invalid Player File",
                        "players.bin does not contain an ArrayList."
                );

                return false;
            }

            for (Object item : loadedList) {

                if (item instanceof Player player) {
                    playerList.add(player);
                }
            }

            System.out.println(
                    "Players loaded successfully: "
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
                        "players.bin exists, but it contains no Player records."
                );

                return false;
            }

            return true;

        } catch (InvalidClassException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Incompatible Player File",
                    "The existing players.bin was created using an older "
                            + "Player or User class.\n\n"
                            + "Delete players.bin and save the Player profile again."
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
                            + e.getClass().getSimpleName()
                            + ": "
                            + e.getMessage()
            );
        }

        return false;
    }

    private boolean savePlayersToFile() {

        File playerFile =
                new File(PLAYER_FILE_NAME);

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(
                             new FileOutputStream(playerFile))) {

            ArrayList<Player> savedPlayers =
                    new ArrayList<>(playerList);

            outputStream.writeObject(savedPlayers);
            outputStream.flush();

            System.out.println(
                    "Players saved successfully to: "
                            + playerFile.getAbsolutePath()
            );

            return true;

        } catch (IOException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "File Error",
                    "Could not update players.bin.\n\n"
                            + e.getMessage()
            );

            return false;
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

    @FXML
    public void Severity_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @FXML
    public void injury_type_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @FXML
    public void initial_fitness_status_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @FXML
    public void affected_body_part_combobox_on_action(
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

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}