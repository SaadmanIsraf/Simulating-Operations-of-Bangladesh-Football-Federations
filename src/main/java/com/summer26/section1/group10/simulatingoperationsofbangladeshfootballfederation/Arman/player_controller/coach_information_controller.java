package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.LinkedHashMap;
import java.util.Map;

public class coach_information_controller {

    @FXML
    private ComboBox<String> coach_combobox;

    @FXML
    private Label coach_name_label;

    @FXML
    private Label coach_contact_label;

    @FXML
    private Label coach_specialization_label;

    @FXML
    private TextArea coach_schedule_textarea;

    private final Map<String, String> contactNumberMap =
            new LinkedHashMap<>();

    private final Map<String, String> specializationMap =
            new LinkedHashMap<>();

    private final Map<String, String> scheduleMap =
            new LinkedHashMap<>();

    @FXML
    public void initialize() {

        initializeCoachNames();
        initializeContactNumbers();
        initializeSpecializations();
        initializeSchedules();

        coach_schedule_textarea.setEditable(false);
        coach_schedule_textarea.setWrapText(true);

        clearCoachInformation();
    }

    private void initializeCoachNames() {

        coach_combobox.getItems().setAll(
                "Coach Rahman",
                "Coach Karim",
                "Coach Hasan",
                "Coach Kabir",
                "Coach Ahmed"
        );
    }

    private void initializeContactNumbers() {

        contactNumberMap.put(
                "Coach Rahman",
                "01711000001"
        );

        contactNumberMap.put(
                "Coach Karim",
                "01711000002"
        );

        contactNumberMap.put(
                "Coach Hasan",
                "01711000003"
        );

        contactNumberMap.put(
                "Coach Kabir",
                "01711000004"
        );

        contactNumberMap.put(
                "Coach Ahmed",
                "01711000005"
        );
    }

    private void initializeSpecializations() {

        specializationMap.put(
                "Coach Rahman",
                "Defensive Training"
        );

        specializationMap.put(
                "Coach Karim",
                "Attacking and Finishing"
        );

        specializationMap.put(
                "Coach Hasan",
                "Midfield Development"
        );

        specializationMap.put(
                "Coach Kabir",
                "Goalkeeping Training"
        );

        specializationMap.put(
                "Coach Ahmed",
                "Fitness and Tactical Training"
        );
    }

    private void initializeSchedules() {

        scheduleMap.put(
                "Coach Rahman",
                "Sunday: 08:00 AM - 10:00 AM\n"
                        + "Tuesday: 03:00 PM - 05:00 PM\n"
                        + "Thursday: 05:00 PM - 07:00 PM"
        );

        scheduleMap.put(
                "Coach Karim",
                "Monday: 08:00 AM - 10:00 AM\n"
                        + "Wednesday: 03:00 PM - 05:00 PM\n"
                        + "Saturday: 05:00 PM - 07:00 PM"
        );

        scheduleMap.put(
                "Coach Hasan",
                "Sunday: 03:00 PM - 05:00 PM\n"
                        + "Tuesday: 05:00 PM - 07:00 PM\n"
                        + "Friday: 08:00 AM - 10:00 AM"
        );

        scheduleMap.put(
                "Coach Kabir",
                "Monday: 06:00 AM - 08:00 AM\n"
                        + "Wednesday: 08:00 AM - 10:00 AM\n"
                        + "Friday: 03:00 PM - 05:00 PM"
        );

        scheduleMap.put(
                "Coach Ahmed",
                "Tuesday: 08:00 AM - 10:00 AM\n"
                        + "Thursday: 03:00 PM - 05:00 PM\n"
                        + "Saturday: 07:00 PM - 09:00 PM"
        );
    }

    @FXML
    public void view_details_button_on_action(
            ActionEvent actionEvent) {

        String selectedCoach =
                coach_combobox.getValue();

        if (selectedCoach == null
                || selectedCoach.isBlank()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Coach Not Selected",
                    "Please select a coach first."
            );

            return;
        }

        displayCoachInformation(selectedCoach);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Coach Information",
                "Coach information loaded successfully."
        );
    }

    private void displayCoachInformation(
            String selectedCoach) {

        coach_name_label.setText(
                selectedCoach
        );

        coach_contact_label.setText(
                contactNumberMap.getOrDefault(
                        selectedCoach,
                        "Not available"
                )
        );

        coach_specialization_label.setText(
                specializationMap.getOrDefault(
                        selectedCoach,
                        "Not available"
                )
        );

        coach_schedule_textarea.setText(
                scheduleMap.getOrDefault(
                        selectedCoach,
                        "No schedule is available."
                )
        );

        coach_schedule_textarea.positionCaret(0);
    }

    @FXML
    public void coach_combobox_on_action(
            ActionEvent actionEvent) {

        /*
         * Coach details are loaded only after
         * clicking the View Details button.
         */
    }

    private void clearCoachInformation() {

        coach_name_label.setText("-");
        coach_contact_label.setText("-");
        coach_specialization_label.setText("-");
        coach_schedule_textarea.clear();
    }

    @FXML
    public void back_button_on_action(
            ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "Arman/player/player_dashboard.fxml"
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