package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MatchDayMedicalIncident;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.MedicalIncidentManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class medical_incident_controller {

    @FXML
    private TableColumn<MatchDayMedicalIncident, String> incident_type_column;
    @FXML
    private TableView<MatchDayMedicalIncident> medical_incident_tableview;
    @FXML
    private TableColumn<MatchDayMedicalIncident, Integer> player_id_column;
    @FXML
    private TextField player_id_textfield;
    @FXML
    private TableColumn<MatchDayMedicalIncident, String> action_taken_column;
    @FXML
    private TableColumn<MatchDayMedicalIncident, String> severity_column;
    @FXML
    private DatePicker match_date_datepicker;
    @FXML
    private TableColumn<MatchDayMedicalIncident, LocalDate> match_date_column;
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

    private final ArrayList<Player> playerList = new ArrayList<>();
    private static final String PLAYER_FILE_NAME = "players.bin";

    @FXML
    public void initialize() {

        incident_type_combobox.getItems().addAll(
                "Injury",
                "Cramp",
                "Concussion",
                "Emergency"
        );

        severity_combobox.getItems().addAll(
                "Minor",
                "Moderate",
                "Severe"
        );

        action_taken_combobox.getItems().addAll(
                "First Aid",
                "Substitution",
                "Hospitalization"
        );

        player_id_column.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        match_date_column.setCellValueFactory(new PropertyValueFactory<>("matchDate"));
        incident_type_column.setCellValueFactory(new PropertyValueFactory<>("incidentType"));
        severity_column.setCellValueFactory(new PropertyValueFactory<>("severity"));
        action_taken_column.setCellValueFactory(new PropertyValueFactory<>("actionTaken"));

        medical_incident_tableview.getItems().setAll(MedicalIncidentManager.getIncidentList());
        player_incident_details_textarea.setWrapText(true);

        loadPlayersFromFile();
    }

    @FXML
    public void submit_button_on_action(ActionEvent actionEvent) {

        if (!loadPlayersFromFile()) {
            return;
        }

        String playerIdText = player_id_textfield.getText().trim();
        LocalDate matchDate = match_date_datepicker.getValue();
        String incidentType = incident_type_combobox.getValue();
        String severity = severity_combobox.getValue();
        String actionTaken = action_taken_combobox.getValue();

        if (playerIdText.isEmpty()) {showAlert(Alert.AlertType.ERROR, "Empty Field", "Player ID cannot be empty.");
            player_id_textfield.requestFocus();
            return;
        }

        if (!playerIdText.matches("\\d+")) {showAlert(Alert.AlertType.ERROR, "Invalid Player ID", "Player ID must contain only numbers.");
            player_id_textfield.requestFocus();
            return;
        }

        if (matchDate == null) {showAlert(Alert.AlertType.ERROR, "Empty Field", "Please select the match date.");
            return;
        }

        if (matchDate.isAfter(LocalDate.now())) {showAlert(Alert.AlertType.ERROR, "Invalid Match Date", "Match date cannot be a future date.");
            return;
        }

        if (incidentType == null) {showAlert(Alert.AlertType.ERROR, "Empty Field", "Please select an incident type.");
            return;
        }

        if (severity == null) {showAlert(Alert.AlertType.ERROR, "Empty Field", "Please select the severity.");
            return;
        }

        if (actionTaken == null) {showAlert(Alert.AlertType.ERROR, "Empty Field", "Please select the action taken.");
            return;
        }

        int playerId = Integer.parseInt(playerIdText);
        Player foundPlayer = findPlayer(playerId);

        if (foundPlayer == null) {showAlert(Alert.AlertType.ERROR, "Player Not Found", "Player does not exist in the system.");
            return;
        }

        int incidentId = MedicalIncidentManager.getIncidentList().size() + 1;

        MatchDayMedicalIncident incident = new MatchDayMedicalIncident(
                incidentId,
                playerId,
                matchDate,
                incidentType,
                severity,
                actionTaken
        );

        MedicalIncidentManager.addIncident(incident);
        MedicalIncidentManager.saveToFile();

        List<MatchDayMedicalIncident> playerIncidents = getIncidentsForPlayer(playerId);

        medical_incident_tableview.getItems().setAll(playerIncidents);
        medical_incident_tableview.getSelectionModel().select(incident);
        medical_incident_tableview.scrollTo(incident);

        displayPlayerAndIncidents(foundPlayer, playerIncidents);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Match Day Incident Recorded Successfully for Player ID: " + playerId + "."
        );

        clearFields();
    }

    @FXML
    public void search_button_on_action(ActionEvent actionEvent) {

        if (!loadPlayersFromFile()) {
            return;
        }

        String playerIdText = search_player_id_textfield.getText().trim();

        if (playerIdText.isEmpty()) {showAlert(Alert.AlertType.ERROR, "Empty Field", "Please enter a Player ID.");
            search_player_id_textfield.requestFocus();
            return;
        }

        if (!playerIdText.matches("\\d+")) {showAlert(Alert.AlertType.ERROR, "Invalid Player ID", "Player ID must contain only numbers.");
            search_player_id_textfield.requestFocus();
            return;
        }

        int playerId = Integer.parseInt(playerIdText);
        Player foundPlayer = findPlayer(playerId);

        if (foundPlayer == null) {
            player_incident_details_textarea.setText("Player does not exist in the system.");
            medical_incident_tableview.getItems().clear();
            showAlert(Alert.AlertType.ERROR, "Player Not Found", "Player does not exist in the system.");
            return;
        }

        List<MatchDayMedicalIncident> playerIncidents = getIncidentsForPlayer(playerId);

        medical_incident_tableview.getItems().setAll(playerIncidents);
        displayPlayerAndIncidents(foundPlayer, playerIncidents);

        if (playerIncidents.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Player Found", "Player found, but no medical incident exists.");
        } else {
            MatchDayMedicalIncident latestIncident =
                    playerIncidents.get(playerIncidents.size() - 1);

            medical_incident_tableview.getSelectionModel().select(latestIncident);
            medical_incident_tableview.scrollTo(latestIncident);
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

    private List<MatchDayMedicalIncident> getIncidentsForPlayer(int playerId) {

        ArrayList<MatchDayMedicalIncident> playerIncidents = new ArrayList<>();

        for (MatchDayMedicalIncident incident : MedicalIncidentManager.getIncidentList()) {
            if (incident.getPlayerId() == playerId) {
                playerIncidents.add(incident);
            }
        }

        return playerIncidents;
    }

    private void displayPlayerAndIncidents(
            Player player,
            List<MatchDayMedicalIncident> incidents) {

        String details =
                "Player ID: " + player.getId() + "\n" +
                        "Player Name: " + player.getName() + "\n" +
                        "Team: " + player.getTeamName() + "\n" +
                        "Playing Position: " + player.getPlayingPosition() + "\n" +
                        "Age: " + player.getAge() + "\n" +
                        "Fitness Status: " + player.getFitnessStatus() + "\n" +
                        "Eligibility Status: " + player.getMatchEligibilityStatus() + "\n" +
                        "Contact Number: " + player.getContactNumber() + "\n\n";

        if (incidents.isEmpty()) {
            details += "No match day medical incident found for this player.";
        } else {
            details += "Match Day Medical Incidents:\n";

            for (MatchDayMedicalIncident incident : incidents) {
                details +=
                        "\nMatch Date: " + incident.getMatchDate() +
                                "\nIncident Type: " + incident.getIncidentType() +
                                "\nSeverity: " + incident.getSeverity() +
                                "\nAction Taken: " + incident.getActionTaken() +
                                "\n----------------------------------------\n";
            }
        }

        player_incident_details_textarea.setText(details);
    }

    private boolean loadPlayersFromFile() {

        playerList.clear();

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(PLAYER_FILE_NAME))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {
                ArrayList<?> loadedList = (ArrayList<?>) object;

                for (Object item : loadedList) {
                    if (item instanceof Player) {
                        playerList.add((Player) item);
                    }
                }
            }

            if (playerList.isEmpty()) {showAlert(Alert.AlertType.WARNING, "No Players Found", "No player record was found in players.bin.");
                return false;
            }

            return true;

        } catch (FileNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "File Error", "players.bin was not found.");
        } catch (IOException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "File Error", "Could not load player data.");
        }

        return false;
    }

    private void clearFields() {

        player_id_textfield.clear();
        match_date_datepicker.setValue(null);
        incident_type_combobox.getSelectionModel().clearSelection();
        severity_combobox.getSelectionModel().clearSelection();
        action_taken_combobox.getSelectionModel().clearSelection();
    }

    @FXML
    public void severity_combobox_on_action(ActionEvent actionEvent) {
    }

    @FXML
    public void incident_type_combobox_on_action(ActionEvent actionEvent) {
    }

    @FXML
    public void action_taken_on_action(ActionEvent actionEvent) {
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