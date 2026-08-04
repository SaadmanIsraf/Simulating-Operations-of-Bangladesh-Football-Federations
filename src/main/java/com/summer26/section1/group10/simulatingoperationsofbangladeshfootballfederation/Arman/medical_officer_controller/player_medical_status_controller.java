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

public class player_medical_status_controller {

    @FXML
    private DatePicker matchday_datepicker;

    @FXML
    private TableColumn<MedicalDeclaration, Integer> player_id_column;

    @FXML
    private TextField player_id_textfield;

    @FXML
    private TableColumn<MedicalDeclaration, String> declaration_status_column;

    @FXML
    private TableView<MedicalDeclaration> player_medical_fitness_tableview;

    @FXML
    private TableColumn<MedicalDeclaration, LocalDate> match_day_column;

    @FXML
    private ComboBox<String> gameplay_declaration_combobox;

    @FXML
    private TextField search_player_id_textfield;

    @FXML
    private TextArea player_details_textarea;

    private final List<Player> playerList = new ArrayList<>();

    private static final String PLAYER_FILE_NAME = "players.bin";

    @FXML
    public void initialize() {

        gameplay_declaration_combobox.getItems().setAll(
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

        player_medical_fitness_tableview.getItems().setAll(
                MedicalDeclarationManager.getDeclarationList()
        );

        player_details_textarea.setWrapText(true);

        loadPlayersFromFile();
    }

    @FXML
    public void save_button_on_action(ActionEvent actionEvent) {

        if (!loadPlayersFromFile()) {
            return;
        }

        String playerIdText =
                player_id_textfield.getText().trim();

        LocalDate declarationDate =
                matchday_datepicker.getValue();

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

        Player foundPlayer = findPlayer(playerId);

        if (foundPlayer == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Not Found",
                    "No player exists with Player ID: " + playerId
            );
            return;
        }

        RehabilitationProgress foundProgress =
                findLatestRehabilitationProgress(playerId);

        if (foundProgress == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "No Rehabilitation Record",
                    "No rehabilitation record found for this player."
            );
            return;
        }

        int medicalOfficerId = getCurrentMedicalOfficerId();

        if (medicalOfficerId == 0) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Medical Officer Not Found",
                    "No Medical Officer profile exists in the system."
            );
            return;
        }

        int declarationId = generateDeclarationId();

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

        foundPlayer.setMatchEligibilityStatus(
                declarationStatus
        );

        if (declarationStatus.equals("Medically Fit")) {

            foundPlayer.setFitnessStatus("Fit");

        } else {

            foundPlayer.setFitnessStatus("Unfit");
        }

        if (!savePlayersToFile()) {
            return;
        }

        List<MedicalDeclaration> playerDeclarations =
                getDeclarationsForPlayer(playerId);

        player_medical_fitness_tableview.getItems().setAll(
                playerDeclarations
        );

        player_medical_fitness_tableview.refresh();

        displayPlayerAndDeclarations(
                foundPlayer,
                playerDeclarations
        );

        player_medical_fitness_tableview
                .getSelectionModel()
                .select(declaration);

        player_medical_fitness_tableview.scrollTo(
                declaration
        );

        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Player ID " + playerId
                        + " has been declared "
                        + declarationStatus + "."
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

            player_medical_fitness_tableview
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

        List<MedicalDeclaration> playerDeclarations =
                getDeclarationsForPlayer(playerId);

        player_medical_fitness_tableview.getItems().setAll(
                playerDeclarations
        );

        player_medical_fitness_tableview.refresh();

        displayPlayerAndDeclarations(
                foundPlayer,
                playerDeclarations
        );

        if (playerDeclarations.isEmpty()) {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Player Found",
                    "Player found, but no medical fitness declaration exists."
            );

        } else {

            MedicalDeclaration latestDeclaration =
                    playerDeclarations.get(
                            playerDeclarations.size() - 1
                    );

            player_medical_fitness_tableview
                    .getSelectionModel()
                    .select(latestDeclaration);

            player_medical_fitness_tableview.scrollTo(
                    latestDeclaration
            );

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Records Found",
                    "Player details and medical fitness declarations loaded successfully."
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

    private RehabilitationProgress
    findLatestRehabilitationProgress(int playerId) {

        RehabilitationProgress latestProgress = null;

        for (RehabilitationProgress progress :
                RehabilitationProgressManager.getProgressList()) {

            if (progress.getPlayerId() == playerId) {
                latestProgress = progress;
            }
        }

        return latestProgress;
    }

    private int getCurrentMedicalOfficerId() {

        if (MedicalOfficerManager
                .getMedicalOfficerList()
                .isEmpty()) {

            return 0;
        }

        MedicalOfficer medicalOfficer =
                MedicalOfficerManager
                        .getMedicalOfficerList()
                        .get(0);

        return medicalOfficer.getId();
    }

    private List<MedicalDeclaration>
    getDeclarationsForPlayer(int playerId) {

        List<MedicalDeclaration> declarations =
                new ArrayList<>();

        for (MedicalDeclaration declaration :
                MedicalDeclarationManager
                        .getDeclarationList()) {

            if (declaration.getPlayerId() == playerId) {
                declarations.add(declaration);
            }
        }

        return declarations;
    }

    private int generateDeclarationId() {

        int highestId = 0;

        for (MedicalDeclaration declaration :
                MedicalDeclarationManager
                        .getDeclarationList()) {

            if (declaration.getDeclarationId()
                    > highestId) {

                highestId =
                        declaration.getDeclarationId();
            }
        }

        return highestId + 1;
    }

    private void displayPlayerAndDeclarations(
            Player player,
            List<MedicalDeclaration> declarations) {

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

        details.append("Eligibility Status : ")
                .append(player.getMatchEligibilityStatus())
                .append("\n");

        details.append("Contact Number     : ")
                .append(player.getContactNumber())
                .append("\n\n");

        details.append("MEDICAL FITNESS DECLARATIONS\n");
        details.append(
                "=====================================================\n"
        );

        if (declarations.isEmpty()) {

            details.append(
                    "No medical fitness declaration found for this player."
            );

        } else {

            int declarationNumber = 1;

            for (MedicalDeclaration declaration :
                    declarations) {

                details.append("\nDeclaration #")
                        .append(declarationNumber++)
                        .append("\n");

                details.append("Declaration ID     : ")
                        .append(
                                declaration.getDeclarationId()
                        )
                        .append("\n");

                details.append("Medical Officer ID : ")
                        .append(
                                declaration.getMedicalOfficerId()
                        )
                        .append("\n");

                details.append("Match Date         : ")
                        .append(
                                declaration.getDeclarationDate()
                        )
                        .append("\n");

                details.append("Declaration Status : ")
                        .append(
                                declaration.getDeclarationStatus()
                        )
                        .append("\n");

                details.append("Remarks            : ")
                        .append(
                                declaration.getRemarks() == null
                                        || declaration
                                        .getRemarks()
                                        .isBlank()
                                        ? "None"
                                        : declaration.getRemarks()
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

    private boolean loadPlayersFromFile() {

        playerList.clear();

        File playerFile = new File(
                PLAYER_FILE_NAME
        );

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

            Object object = inputStream.readObject();

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

    private boolean savePlayersToFile() {

        File playerFile =
                new File(PLAYER_FILE_NAME);

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(
                             new FileOutputStream(
                                     playerFile
                             ))) {

            ArrayList<Player> savedPlayers =
                    new ArrayList<>(playerList);

            outputStream.writeObject(savedPlayers);
            outputStream.flush();

            System.out.println(
                    "Player status saved successfully to: "
                            + playerFile.getAbsolutePath()
            );

            return true;

        } catch (IOException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "File Error",
                    "Could not update player status.\n\n"
                            + e.getMessage()
            );

            return false;
        }
    }

    private void clearFields() {

        player_id_textfield.clear();
        matchday_datepicker.setValue(null);
        gameplay_declaration_combobox.setValue(null);
    }

    @FXML
    public void gameplay_declaration_combobox_on_action(
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