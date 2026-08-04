package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MatchDayMedicalIncident;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.MedicalIncidentManager;
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

public class medical_incident_controller {

    @FXML
    private TableColumn<MatchDayMedicalIncident, String>
            incident_type_column;

    @FXML
    private TableView<MatchDayMedicalIncident>
            medical_incident_tableview;

    @FXML
    private TableColumn<MatchDayMedicalIncident, Integer>
            player_id_column;

    @FXML
    private TextField player_id_textfield;

    @FXML
    private TableColumn<MatchDayMedicalIncident, String>
            action_taken_column;

    @FXML
    private TableColumn<MatchDayMedicalIncident, String>
            severity_column;

    @FXML
    private DatePicker match_date_datepicker;

    @FXML
    private TableColumn<MatchDayMedicalIncident, LocalDate>
            match_date_column;

    @FXML
    private ComboBox<String> incident_type_combobox;

    @FXML
    private ComboBox<String> action_taken_combobox;

    @FXML
    private ComboBox<String> severity_combobox;

    @FXML
    private TextField search_player_id_textfield;

    @FXML
    private TextArea player_incident_details_textarea;

    private final List<Player> playerList =
            new ArrayList<>();

    private static final String PLAYER_FILE_NAME =
            "players.bin";

    @FXML
    public void initialize() {

        incident_type_combobox.getItems().setAll(
                "Injury",
                "Cramp",
                "Concussion",
                "Emergency"
        );

        severity_combobox.getItems().setAll(
                "Minor",
                "Moderate",
                "Severe"
        );

        action_taken_combobox.getItems().setAll(
                "First Aid",
                "Substitution",
                "Hospitalization"
        );

        player_id_column.setCellValueFactory(
                new PropertyValueFactory<>("playerId")
        );

        match_date_column.setCellValueFactory(
                new PropertyValueFactory<>("matchDate")
        );

        incident_type_column.setCellValueFactory(
                new PropertyValueFactory<>("incidentType")
        );

        severity_column.setCellValueFactory(
                new PropertyValueFactory<>("severity")
        );

        action_taken_column.setCellValueFactory(
                new PropertyValueFactory<>("actionTaken")
        );

        medical_incident_tableview.getItems().setAll(
                MedicalIncidentManager.getIncidentList()
        );

        player_incident_details_textarea.setWrapText(true);

        loadPlayersFromFile();
    }

    @FXML
    public void submit_button_on_action(
            ActionEvent actionEvent) {

        if (!loadPlayersFromFile()) {
            return;
        }

        String playerIdText =
                player_id_textfield.getText().trim();

        LocalDate matchDate =
                match_date_datepicker.getValue();

        String incidentType =
                incident_type_combobox.getValue();

        String severity =
                severity_combobox.getValue();

        String actionTaken =
                action_taken_combobox.getValue();

        if (playerIdText.isEmpty()
                || matchDate == null
                || incidentType == null
                || severity == null
                || actionTaken == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
                    "Please fill in all incident fields."
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

        if (matchDate.isAfter(LocalDate.now())) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Match Date",
                    "Match date cannot be a future date."
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

        int incidentId =
                MedicalIncidentManager
                        .getIncidentList()
                        .size() + 1;

        MatchDayMedicalIncident incident =
                new MatchDayMedicalIncident(
                        incidentId,
                        playerId,
                        matchDate,
                        incidentType,
                        severity,
                        actionTaken
                );

        MedicalIncidentManager.addIncident(incident);
        MedicalIncidentManager.saveToFile();

        List<MatchDayMedicalIncident> playerIncidents =
                getIncidentsForPlayer(playerId);

        medical_incident_tableview.getItems().setAll(
                playerIncidents
        );

        medical_incident_tableview.refresh();

        displayPlayerAndIncidents(
                foundPlayer,
                playerIncidents
        );

        medical_incident_tableview
                .getSelectionModel()
                .select(incident);

        medical_incident_tableview.scrollTo(
                incident
        );

        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Match Day Incident Recorded Successfully "
                        + "for Player ID: "
                        + playerId + "."
        );

        clearFields();
    }

    @FXML
    public void search_button_on_action(
            ActionEvent actionEvent) {

        if (!loadPlayersFromFile()) {
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
                    "Please enter a Player ID."
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

            player_incident_details_textarea.setText(
                    "Player does not exist in the system."
            );

            medical_incident_tableview
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

        List<MatchDayMedicalIncident> playerIncidents =
                getIncidentsForPlayer(playerId);

        medical_incident_tableview.getItems().setAll(
                playerIncidents
        );

        medical_incident_tableview.refresh();

        displayPlayerAndIncidents(
                foundPlayer,
                playerIncidents
        );

        if (playerIncidents.isEmpty()) {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Player Found",
                    "Player found, but no medical incident exists."
            );

        } else {

            MatchDayMedicalIncident latestIncident =
                    playerIncidents.get(
                            playerIncidents.size() - 1
                    );

            medical_incident_tableview
                    .getSelectionModel()
                    .select(latestIncident);

            medical_incident_tableview.scrollTo(
                    latestIncident
            );

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Incidents Found",
                    "Player details and medical incidents "
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

    private List<MatchDayMedicalIncident>
    getIncidentsForPlayer(int playerId) {

        List<MatchDayMedicalIncident> playerIncidents =
                new ArrayList<>();

        for (MatchDayMedicalIncident incident :
                MedicalIncidentManager.getIncidentList()) {

            if (incident.getPlayerId() == playerId) {
                playerIncidents.add(incident);
            }
        }

        return playerIncidents;
    }

    private void displayPlayerAndIncidents(
            Player player,
            List<MatchDayMedicalIncident> incidents) {

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

        details.append("Eligibility Status : ")
                .append(player.getMatchEligibilityStatus())
                .append("\n");

        details.append("Contact Number     : ")
                .append(player.getContactNumber())
                .append("\n\n");

        details.append("MATCH DAY MEDICAL INCIDENTS\n");
        details.append(
                "=====================================================\n"
        );

        if (incidents.isEmpty()) {

            details.append(
                    "No match day medical incidents found "
                            + "for this player."
            );

        } else {

            int incidentNumber = 1;

            for (MatchDayMedicalIncident incident :
                    incidents) {

                details.append("\nIncident #")
                        .append(incidentNumber++)
                        .append("\n");

                details.append("Match Date         : ")
                        .append(incident.getMatchDate())
                        .append("\n");

                details.append("Incident Type      : ")
                        .append(incident.getIncidentType())
                        .append("\n");

                details.append("Severity           : ")
                        .append(incident.getSeverity())
                        .append("\n");

                details.append("Action Taken       : ")
                        .append(incident.getActionTaken())
                        .append("\n");

                details.append(
                        "-----------------------------------------------------\n"
                );
            }
        }

        player_incident_details_textarea.setText(
                details.toString()
        );

        player_incident_details_textarea.positionCaret(0);
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
                            + e.getClass().getSimpleName()
                            + ": "
                            + e.getMessage()
            );
        }

        return false;
    }

    private void clearFields() {

        player_id_textfield.clear();
        match_date_datepicker.setValue(null);
        incident_type_combobox.setValue(null);
        severity_combobox.setValue(null);
        action_taken_combobox.setValue(null);
    }

    @FXML
    public void severity_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @FXML
    public void incident_type_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @FXML
    public void action_taken_on_action(
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