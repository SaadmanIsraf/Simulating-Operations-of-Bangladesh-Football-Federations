package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.TransferRequest;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_manager.TransferRequestManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class join_team_request_controller {

    @FXML
    private TextField player_id_textfield;
    @FXML
    private ComboBox<String> team_name_combobox;
    @FXML
    private TextArea request_message_textarea;
    @FXML
    private TableView<TransferRequest> transfer_request_tableview;
    @FXML
    private TableColumn<TransferRequest, Integer> request_id_column;
    @FXML
    private TableColumn<TransferRequest, Integer> player_id_column;
    @FXML
    private TableColumn<TransferRequest, String> player_name_column;
    @FXML
    private TableColumn<TransferRequest, String> current_team_column;
    @FXML
    private TableColumn<TransferRequest, String> requested_team_column;
    @FXML
    private TableColumn<TransferRequest, LocalDate> request_date_column;
    @FXML
    private TableColumn<TransferRequest, String> status_column;

    private final List<Player> playerList = new ArrayList<>();
    private static final String PLAYER_FILE_NAME = "players.bin";

    @FXML
    public void initialize() {
        initializeTeamComboBox();
        initializeTableColumns();

        loadPlayersFromFile();
        refreshTransferRequestTable();
    }

    private void initializeTeamComboBox() {
        team_name_combobox.getItems().setAll(
                "Abahani Limited Dhaka",
                "Bashundhara Kings",
                "Mohammedan Sporting Club",
                "Sheikh Russel KC",
                "Sheikh Jamal Dhanmondi Club",
                "Bangladesh Police FC",
                "Brothers Union",
                "Rahmatganj MFS",
                "Fortis FC",
                "Chittagong Abahani"
        );
    }

    private void initializeTableColumns() {
        request_id_column.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        player_id_column.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        player_name_column.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        current_team_column.setCellValueFactory(new PropertyValueFactory<>("currentTeam"));
        requested_team_column.setCellValueFactory(new PropertyValueFactory<>("requestedTeam"));
        request_date_column.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void refreshTransferRequestTable() {
        transfer_request_tableview.getItems().setAll(TransferRequestManager.getTransferRequestList());
        transfer_request_tableview.refresh();
    }

    @FXML
    public void send_request_button_on_action(ActionEvent actionEvent) {
        String playerIdText = player_id_textfield.getText().trim();
        String requestedTeam = team_name_combobox.getValue();
        String requestMessage = request_message_textarea.getText().trim();

        if (playerIdText.isEmpty()
                || requestedTeam == null
                || requestMessage.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
                    "Please enter Player ID, select a team and write a request message."
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
                    "Player ID must be a whole number."
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

        if (requestMessage.length() < 10) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Message Too Short",
                    "Please write at least 10 characters explaining your transfer request."
            );
            return;
        }

        if (requestMessage.length() > 500) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Message Too Long",
                    "The request message cannot contain more than 500 characters."
            );
            return;
        }

        if (!loadPlayersFromFile()) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Data Error",
                    "Could not load player data from players.bin."
            );
            return;
        }

        Player foundPlayer = findPlayerById(playerId);

        if (foundPlayer == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Player Not Found",
                    "No player exists with Player ID: " + playerId
            );
            return;
        }

        String currentTeam = foundPlayer.getTeamName();

        if (currentTeam == null || currentTeam.isBlank()) {
            currentTeam = "No Current Team";
        }

        if (currentTeam.equalsIgnoreCase(requestedTeam)) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Same Team Selected",
                    "You are already registered with "
                            + requestedTeam
                            + ". Please select another team."
            );
            return;
        }

        boolean pendingRequestExists =
                TransferRequestManager.hasPendingRequest(
                        playerId,
                        requestedTeam
                );

        if (pendingRequestExists) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Request Already Submitted",
                    "You already have a pending transfer request for "
                            + requestedTeam
                            + "."
            );
            return;
        }

        int requestId = TransferRequestManager.generateRequestId();

        TransferRequest transferRequest =
                new TransferRequest(
                        requestId,
                        playerId,
                        foundPlayer.getName(),
                        currentTeam,
                        requestedTeam,
                        requestMessage,
                        LocalDate.now(),
                        "Pending"
                );

        TransferRequestManager.addTransferRequest(transferRequest);
        TransferRequestManager.saveToFile();

        refreshTransferRequestTable();

        transfer_request_tableview
                .getSelectionModel()
                .select(transferRequest);

        transfer_request_tableview.scrollTo(transferRequest);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Transfer Request Sent",
                "Your transfer request has been sent successfully.\n\n"
                        + "Request ID: "
                        + requestId
                        + "\n"
                        + "Player: "
                        + foundPlayer.getName()
                        + "\n"
                        + "Requested Team: "
                        + requestedTeam
                        + "\n"
                        + "Status: Pending"
        );

        clearInputFields();
    }

    private Player findPlayerById(int playerId) {
        for (Player player : playerList) {
            if (player.getId() == playerId) {
                return player;
            }
        }

        return null;
    }

    @FXML
    public void team_name_combobox_on_action(ActionEvent actionEvent) {
    }

    @FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/player/player_dashboard.fxml");
    }

    @SuppressWarnings("unchecked")
    private boolean loadPlayersFromFile() {
        playerList.clear();

        File playerFile = new File(PLAYER_FILE_NAME);

        if (!playerFile.exists()) {
            System.out.println(
                    "players.bin was not found at: "
                            + playerFile.getAbsolutePath()
            );
            return false;
        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(
                             new FileInputStream(playerFile)
                     )) {

            Object savedObject = inputStream.readObject();

            if (!(savedObject instanceof ArrayList<?> loadedList)) {
                showAlert(
                        Alert.AlertType.ERROR,
                        "Invalid Player File",
                        "players.bin does not contain valid player data."
                );
                return false;
            }

            for (Object item : loadedList) {
                if (item instanceof Player player) {
                    playerList.add(player);
                }
            }

            return !playerList.isEmpty();

        } catch (InvalidClassException e) {e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Incompatible Player File",
                    "players.bin was created using an older Player or User class.\n\n"
                            + "Delete players.bin and save the Player profile again."
            );

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Player File Error",
                    "Could not load player information.\n\n"
                            + e.getMessage()
            );
        }

        return false;
    }

    private void clearInputFields() {
        player_id_textfield.clear();
        team_name_combobox.setValue(null);
        request_message_textarea.clear();
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