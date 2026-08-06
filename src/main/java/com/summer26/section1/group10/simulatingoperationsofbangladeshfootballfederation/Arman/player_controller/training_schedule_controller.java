package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.TrainingSchedule;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_manager.TrainingScheduleManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

public class training_schedule_controller {

    @FXML
    private TextField player_id_textfield;
    @FXML
    private DatePicker training_date_datepicker;
    @FXML
    private ComboBox<String> training_time_combobox;
    @FXML
    private ComboBox<String> venue_combobox;
    @FXML
    private ComboBox<String> coach_combobox;
    @FXML
    private TextField search_player_id_textfield;
    @FXML
    private ComboBox<String> search_coach_combobox;
    @FXML
    private ComboBox<String> search_time_combobox;
    @FXML
    private TableView<TrainingSchedule> training_schedule_tableview;
    @FXML
    private TableColumn<TrainingSchedule, Integer> training_id_column;
    @FXML
    private TableColumn<TrainingSchedule, LocalDate> training_date_column;
    @FXML
    private TableColumn<TrainingSchedule, String> training_time_column;
    @FXML
    private TableColumn<TrainingSchedule, String> venue_column;
    @FXML
    private TableColumn<TrainingSchedule, String> coach_column;
    @FXML
    private TableColumn<TrainingSchedule, String> enrollment_count_column;
    @FXML
    private TableColumn<TrainingSchedule, String> session_status_column;
    @FXML
    private TextArea search_result_textarea;
    @FXML
    private Label information_label;
    @FXML
    private Label capacity_information_label;

    private final List<Player> playerList = new ArrayList<>();

    private static final String PLAYER_FILE_NAME = "players.bin";
    private static final int MAXIMUM_CAPACITY = 11;

    @FXML
    public void initialize() {
        initializeComboBoxes();
        initializeTableColumns();

        capacity_information_label.setText(
                "Maximum capacity: " + MAXIMUM_CAPACITY + " players"
        );

        search_result_textarea.setWrapText(true);

        loadPlayersFromFile();
        refreshTrainingTable();

        training_schedule_tableview
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, selectedSchedule) -> {
                    if (selectedSchedule != null) {
                        displaySelectedSession(selectedSchedule);
                    }
                });
    }

    private void initializeComboBoxes() {
        training_time_combobox.getItems().setAll(
                "06:00 AM - 08:00 AM",
                "08:00 AM - 10:00 AM",
                "10:00 AM - 12:00 PM",
                "03:00 PM - 05:00 PM",
                "05:00 PM - 07:00 PM",
                "07:00 PM - 09:00 PM"
        );

        search_time_combobox.getItems().setAll(training_time_combobox.getItems());

        venue_combobox.getItems().setAll(
                "BFF Artificial Turf",
                "BFF Training Ground",
                "Bangabandhu National Stadium",
                "Army Stadium",
                "Bir Sherestha Shaheed Shipahi Mostafa Kamal Stadium"
        );

        coach_combobox.getItems().setAll(
                "Coach Rahman",
                "Coach Karim",
                "Coach Hasan",
                "Coach Kabir",
                "Coach Ahmed"
        );

        search_coach_combobox.getItems().setAll(coach_combobox.getItems());
    }

    private void initializeTableColumns() {
        training_id_column.setCellValueFactory(new PropertyValueFactory<>("trainingId"));
        training_date_column.setCellValueFactory(new PropertyValueFactory<>("trainingDate"));
        training_time_column.setCellValueFactory(new PropertyValueFactory<>("trainingTime"));
        venue_column.setCellValueFactory(new PropertyValueFactory<>("venue"));
        coach_column.setCellValueFactory(new PropertyValueFactory<>("coachName"));
        enrollment_count_column.setCellValueFactory(new PropertyValueFactory<>("enrollmentCount"));
        session_status_column.setCellValueFactory(new PropertyValueFactory<>("sessionStatus"));
    }

    private void refreshTrainingTable() {
        training_schedule_tableview.getItems().setAll(
                TrainingScheduleManager.getTrainingScheduleList()
        );

        training_schedule_tableview.refresh();
    }

    @FXML
    public void enroll_button_on_action(ActionEvent actionEvent) {
        information_label.setText("");
        search_result_textarea.clear();

        if (!loadPlayersFromFile()) {
            return;
        }

        String playerIdText = player_id_textfield.getText().trim();
        LocalDate trainingDate = training_date_datepicker.getValue();
        String trainingTime = training_time_combobox.getValue();
        String venue = venue_combobox.getValue();
        String coachName = coach_combobox.getValue();

        if (playerIdText.isEmpty()
                || trainingDate == null
                || trainingTime == null
                || venue == null
                || coachName == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
                    "Please enter Player ID and select training date, time, venue and coach."
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

        if (trainingDate.isBefore(LocalDate.now())) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Training Date",
                    "Training date cannot be a past date."
            );
            return;
        }

        TrainingSchedule conflictingSchedule =
                findPlayerTimeConflict(playerId, trainingDate, trainingTime);

        if (conflictingSchedule != null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Training Time Conflict",
                    "This player is already enrolled in another training session on the same date and time."
            );
            return;
        }

        TrainingSchedule matchingSchedule =
                findMatchingSchedule(trainingDate, trainingTime, venue, coachName);

        if (matchingSchedule == null) {
            matchingSchedule = new TrainingSchedule(
                    generateTrainingId(),
                    trainingDate,
                    trainingTime,
                    venue,
                    coachName,
                    new ArrayList<>()
            );

            TrainingScheduleManager.addTrainingSchedule(matchingSchedule);
        }

        if (matchingSchedule.getEnrolledPlayerIds().contains(playerId)) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Already Enrolled",
                    "Player ID " + playerId
                            + " is already enrolled in this training session."
            );
            return;
        }

        if (matchingSchedule.getEnrolledPlayerIds().size() >= MAXIMUM_CAPACITY) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Session Full",
                    "This training session already has 11 enrolled players."
            );
            return;
        }

        matchingSchedule.getEnrolledPlayerIds().add(playerId);

        TrainingScheduleManager.saveToFile();

        refreshTrainingTable();

        training_schedule_tableview.getSelectionModel().select(matchingSchedule);
        training_schedule_tableview.scrollTo(matchingSchedule);

        displayPlayerTrainingInformation(foundPlayer, matchingSchedule);

        information_label.setText("Player enrolled successfully.");

        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Player ID " + playerId
                        + " enrolled successfully.\n\n"
                        + "Enrollment: "
                        + matchingSchedule.getEnrollmentCount()
        );

        clearEnrollmentFields();
    }

    @FXML
    public void search_button_on_action(ActionEvent actionEvent) {
        information_label.setText("");
        search_result_textarea.clear();

        if (!loadPlayersFromFile()) {
            return;
        }

        String playerIdText = search_player_id_textfield.getText().trim();
        String selectedCoach = search_coach_combobox.getValue();
        String selectedTime = search_time_combobox.getValue();

        if (playerIdText.isEmpty()
                || selectedCoach == null
                || selectedTime == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Search Field",
                    "Please enter Player ID and select coach and training time."
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
            search_result_textarea.setText(
                    "Player ID " + playerId + " does not exist in players.bin."
            );

            information_label.setText("Player not found.");
            return;
        }

        List<TrainingSchedule> matchingSchedules = new ArrayList<>();

        for (TrainingSchedule schedule :
                TrainingScheduleManager.getTrainingScheduleList()) {

            boolean sameCoach =
                    schedule.getCoachName().equalsIgnoreCase(selectedCoach);

            boolean sameTime =
                    schedule.getTrainingTime().equalsIgnoreCase(selectedTime);

            boolean playerEnrolled =
                    schedule.getEnrolledPlayerIds().contains(playerId);

            if (sameCoach && sameTime && playerEnrolled) {
                matchingSchedules.add(schedule);
            }
        }

        if (matchingSchedules.isEmpty()) {
            String result =
                    "PLAYER INFORMATION\n"
                            + "============================================\n"
                            + "Player ID        : " + foundPlayer.getId() + "\n"
                            + "Player Name      : " + foundPlayer.getName() + "\n"
                            + "Team             : " + foundPlayer.getTeamName() + "\n"
                            + "Playing Position : " + foundPlayer.getPlayingPosition() + "\n\n"
                            + "No training enrollment found with:\n"
                            + "Coach            : " + selectedCoach + "\n"
                            + "Training Time    : " + selectedTime;

            search_result_textarea.setText(result);

            information_label.setText(
                    "Player is not enrolled in the selected training session."
            );
            return;
        }

        displaySearchResults(foundPlayer, matchingSchedules);

        TrainingSchedule firstMatch = matchingSchedules.get(0);

        training_schedule_tableview.getSelectionModel().select(firstMatch);
        training_schedule_tableview.scrollTo(firstMatch);

        information_label.setText(
                matchingSchedules.size() + " matching enrollment(s) found."
        );
    }

    private void displaySearchResults(
            Player player,
            List<TrainingSchedule> matchingSchedules) {

        StringBuilder result = new StringBuilder();

        result.append("PLAYER INFORMATION\n");
        result.append("=====================================================\n");
        result.append("Player ID          : ").append(player.getId()).append("\n");
        result.append("Player Name        : ").append(player.getName()).append("\n");
        result.append("Age                : ").append(player.getAge()).append("\n");
        result.append("Team               : ").append(player.getTeamName()).append("\n");
        result.append("Playing Position   : ").append(player.getPlayingPosition()).append("\n");
        result.append("Fitness Status     : ").append(player.getFitnessStatus()).append("\n");
        result.append("Contact Number     : ").append(player.getContactNumber()).append("\n\n");

        result.append("TRAINING ENROLLMENT INFORMATION\n");
        result.append("=====================================================\n");

        int sessionNumber = 1;

        for (TrainingSchedule schedule : matchingSchedules) {
            result.append("\nSession #").append(sessionNumber++).append("\n");
            result.append("Training ID        : ")
                    .append(schedule.getTrainingId()).append("\n");
            result.append("Training Date      : ")
                    .append(schedule.getTrainingDate()).append("\n");
            result.append("Training Time      : ")
                    .append(schedule.getTrainingTime()).append("\n");
            result.append("Venue              : ")
                    .append(schedule.getVenue()).append("\n");
            result.append("Assigned Coach     : ")
                    .append(schedule.getCoachName()).append("\n");
            result.append("Enrollment         : ")
                    .append(schedule.getEnrollmentCount()).append("\n");
            result.append("Session Status     : ")
                    .append(schedule.getSessionStatus()).append("\n");
            result.append("Player Status      : Enrolled\n");
            result.append("-----------------------------------------------------\n");
        }

        search_result_textarea.setText(result.toString());
        search_result_textarea.positionCaret(0);
    }

    private TrainingSchedule findMatchingSchedule(
            LocalDate trainingDate,
            String trainingTime,
            String venue,
            String coachName) {

        for (TrainingSchedule schedule :
                TrainingScheduleManager.getTrainingScheduleList()) {

            boolean sameDate = schedule.getTrainingDate().equals(trainingDate);
            boolean sameTime =
                    schedule.getTrainingTime().equalsIgnoreCase(trainingTime);
            boolean sameVenue = schedule.getVenue().equalsIgnoreCase(venue);
            boolean sameCoach =
                    schedule.getCoachName().equalsIgnoreCase(coachName);

            if (sameDate && sameTime && sameVenue && sameCoach) {
                return schedule;
            }
        }

        return null;
    }

    private TrainingSchedule findPlayerTimeConflict(
            int playerId,
            LocalDate trainingDate,
            String trainingTime) {

        for (TrainingSchedule schedule :
                TrainingScheduleManager.getTrainingScheduleList()) {

            boolean sameDate = schedule.getTrainingDate().equals(trainingDate);
            boolean sameTime =
                    schedule.getTrainingTime().equalsIgnoreCase(trainingTime);
            boolean playerEnrolled =
                    schedule.getEnrolledPlayerIds().contains(playerId);

            if (sameDate && sameTime && playerEnrolled) {
                return schedule;
            }
        }

        return null;
    }

    private Player findPlayer(int playerId) {
        for (Player player : playerList) {
            if (player.getId() == playerId) {
                return player;
            }
        }

        return null;
    }

    private int generateTrainingId() {
        int highestId = 0;

        for (TrainingSchedule schedule :
                TrainingScheduleManager.getTrainingScheduleList()) {

            if (schedule.getTrainingId() > highestId) {
                highestId = schedule.getTrainingId();
            }
        }

        return highestId + 1;
    }

    private void displaySelectedSession(TrainingSchedule schedule) {
        String result =
                "SELECTED TRAINING SESSION\n"
                        + "=====================================================\n"
                        + "Training ID      : " + schedule.getTrainingId() + "\n"
                        + "Training Date    : " + schedule.getTrainingDate() + "\n"
                        + "Training Time    : " + schedule.getTrainingTime() + "\n"
                        + "Venue            : " + schedule.getVenue() + "\n"
                        + "Assigned Coach   : " + schedule.getCoachName() + "\n"
                        + "Enrollment       : " + schedule.getEnrollmentCount() + "\n"
                        + "Available Seats  : "
                        + (MAXIMUM_CAPACITY
                        - schedule.getEnrolledPlayerIds().size())
                        + "\n"
                        + "Session Status   : " + schedule.getSessionStatus();

        search_result_textarea.setText(result);
        search_result_textarea.positionCaret(0);
    }

    private void displayPlayerTrainingInformation(
            Player player,
            TrainingSchedule schedule) {

        String result =
                "PLAYER TRAINING INFORMATION\n"
                        + "=====================================================\n"
                        + "Player ID        : " + player.getId() + "\n"
                        + "Player Name      : " + player.getName() + "\n"
                        + "Team             : " + player.getTeamName() + "\n"
                        + "Playing Position : " + player.getPlayingPosition() + "\n"
                        + "Training ID      : " + schedule.getTrainingId() + "\n"
                        + "Training Date    : " + schedule.getTrainingDate() + "\n"
                        + "Training Time    : " + schedule.getTrainingTime() + "\n"
                        + "Venue            : " + schedule.getVenue() + "\n"
                        + "Assigned Coach   : " + schedule.getCoachName() + "\n"
                        + "Enrollment       : " + schedule.getEnrollmentCount() + "\n"
                        + "Enrollment State : Enrolled";

        search_result_textarea.setText(result);
        search_result_textarea.positionCaret(0);
    }

    @SuppressWarnings("unchecked")
    private boolean loadPlayersFromFile() {
        playerList.clear();

        File playerFile = new File(PLAYER_FILE_NAME);

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
                     new ObjectInputStream(new FileInputStream(playerFile))) {

            Object savedObject = inputStream.readObject();

            if (!(savedObject instanceof ArrayList<?> loadedList)) {
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
                    "players.bin was created using an older Player or User class.\n\n"
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
                    "Could not load Player data.\n\n"
                            + e.getMessage()
            );
        }

        return false;
    }

    private void clearEnrollmentFields() {
        player_id_textfield.clear();
        training_date_datepicker.setValue(null);
        training_time_combobox.setValue(null);
        venue_combobox.setValue(null);
        coach_combobox.setValue(null);
    }

    @FXML
    public void training_date_datepicker_on_action(ActionEvent actionEvent) {
    }

    @FXML
    public void training_time_combobox_on_action(ActionEvent actionEvent) {
    }

    @FXML
    public void venue_combobox_on_action(ActionEvent actionEvent) {
    }

    @FXML
    public void coach_combobox_on_action(ActionEvent actionEvent) {
    }

    @FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/player_dashboard.fxml");
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