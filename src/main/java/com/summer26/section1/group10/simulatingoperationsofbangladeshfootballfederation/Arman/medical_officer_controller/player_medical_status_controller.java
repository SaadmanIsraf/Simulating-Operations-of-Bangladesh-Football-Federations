package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MedicalDeclaration;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MedicalOfficer;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.RehabilitationProgress;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.MedicalDeclarationManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.MedicalOfficerManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.RehabilitationProgressManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class player_medical_status_controller
{
    @javafx.fxml.FXML
    private DatePicker matchday_datepicker;
    @javafx.fxml.FXML
    private TableColumn<MedicalDeclaration, Integer> player_id_column;
    @javafx.fxml.FXML
    private TextField player_id_textfield;
    @javafx.fxml.FXML
    private TableColumn<MedicalDeclaration, String> declaration_status_column;
    @javafx.fxml.FXML
    private TableView<MedicalDeclaration> player_medical_fitness_tableview;
    @javafx.fxml.FXML
    private TableColumn<MedicalDeclaration, LocalDate> match_day_column;
    @javafx.fxml.FXML
    private ComboBox<String> gameplay_declaration_combobox;

    private final List<Player> playerList = new ArrayList<>();
    private static final String PLAYER_FILE_NAME = "players.bin";

    @javafx.fxml.FXML
    public void initialize() {

        gameplay_declaration_combobox.getItems().addAll(
                "Medically Fit",
                "Banned from Play"
        );

        player_id_column.setCellValueFactory(
                new PropertyValueFactory<>("playerId")
        );

        match_day_column.setCellValueFactory(
                new PropertyValueFactory<>("declarationDate")
        );

        declaration_status_column.setCellValueFactory(
                new PropertyValueFactory<>("declarationStatus")
        );

        player_medical_fitness_tableview.getItems().addAll(
                MedicalDeclarationManager.getDeclarationList()
        );

        loadPlayersFromFile();
    }

    @javafx.fxml.FXML
    public void save_button_on_action(ActionEvent actionEvent) {

        String playerIdText = player_id_textfield.getText().trim();
        LocalDate declarationDate = matchday_datepicker.getValue();
        String declarationStatus =
                gameplay_declaration_combobox.getValue();

        if (playerIdText.isEmpty()
                || declarationDate == null
                || declarationStatus == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
                    "Please fill in all fields."
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

        if (declarationDate.isAfter(LocalDate.now())) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Date",
                    "Declaration date cannot be a future date."
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

        RehabilitationProgress foundProgress = null;

        for (RehabilitationProgress progress :
                RehabilitationProgressManager.getProgressList()) {

            if (progress.getPlayerId() == playerId) {
                foundProgress = progress;
            }
        }

        if (foundProgress == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "No Rehabilitation Record",
                    "No rehabilitation record found for this player."
            );
            return;
        }

        int medicalOfficerId = 0;

        if (!MedicalOfficerManager.getMedicalOfficerList().isEmpty()) {

            MedicalOfficer medicalOfficer =
                    MedicalOfficerManager.getMedicalOfficerList().get(0);

            medicalOfficerId = medicalOfficer.getId();
        }

        int declarationId =
                MedicalDeclarationManager.getDeclarationList().size() + 1;

        MedicalDeclaration declaration =
                new MedicalDeclaration(
                        declarationId,
                        playerId,
                        medicalOfficerId,
                        declarationDate,
                        declarationStatus,
                        "No remarks provided"
                );

        MedicalDeclarationManager.addDeclaration(declaration);
        MedicalDeclarationManager.saveToFile();

        foundPlayer.setMatchEligibilityStatus(declarationStatus);
        foundPlayer.setFitnessStatus(
                declarationStatus.equals("Medically Fit")
                        ? "Fit"
                        : "Unfit"
        );

        savePlayersToFile();

        player_medical_fitness_tableview.getItems().add(declaration);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Player ID: " + playerId
                        + " has been declared "
                        + declarationStatus + "."
        );

        clearFields();
    }

    @javafx.fxml.FXML
    public void gameplay_declaration_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "Arman/medical_officer/medical_officer_dashboard.fxml"
        );
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
                    "Could not update player status."
            );
        }
    }

    private void clearFields() {

        player_id_textfield.clear();
        matchday_datepicker.setValue(null);
        gameplay_declaration_combobox.setValue(null);
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