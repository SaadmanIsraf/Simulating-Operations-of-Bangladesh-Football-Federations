package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class player_profile_controller {

    @FXML
    private Label player_name_label;

    @FXML
    private Label player_id_label;

    @FXML
    private Label player_age_label;

    @FXML
    private Label team_name_label;

    @FXML
    private Label playing_position_label;

    @FXML
    private Label player_type_label;

    @FXML
    private Label player_contact_label;


    @FXML
    private TextField player_name_textfield;

    @FXML
    private TextField player_id_textfield;

    @FXML
    private TextField player_age_textfield;

    @FXML
    private TextField player_contact_textfield;

    @FXML
    private ComboBox<String> team_name_combobox;

    @FXML
    private ComboBox<String> playing_position_combobox;

    @FXML
    private ComboBox<String> player_type_combobox;

    private final List<Player> playerList = new ArrayList<>();

    private Player currentPlayer;

    private static final String PLAYER_FILE_NAME = "players.bin";

    @FXML
    public void initialize() {

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
                "Chittagong Abahani",
                "No Current Team"
        );

        playing_position_combobox.getItems().setAll(
                "Goalkeeper",
                "Defender",
                "Midfielder",
                "Forward"
        );

        player_type_combobox.getItems().setAll(
                "Captain",
                "Regular Player"
        );

        loadPlayersFromFile();

        if (!playerList.isEmpty()) {

            currentPlayer = playerList.get(0);
            displayPlayerInformation();

        } else {

            currentPlayer = null;

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Player Profile",
                    "No player profile found.\nCreate one by filling in the form."
            );
        }
    }

    private void displayPlayerInformation() {

        if (currentPlayer == null) {
            return;
        }

        player_name_label.setText(
                currentPlayer.getName()
        );

        player_id_label.setText(
                String.valueOf(currentPlayer.getId())
        );

        player_age_label.setText(
                String.valueOf(currentPlayer.getAge())
        );

        team_name_label.setText(
                currentPlayer.getTeamName()
        );

        playing_position_label.setText(
                currentPlayer.getPlayingPosition()
        );

        player_type_label.setText(
                currentPlayer.getPlayerType()
        );

        player_contact_label.setText(
                currentPlayer.getContactNumber()
        );

        player_name_textfield.setText(
                currentPlayer.getName()
        );

        player_id_textfield.setText(
                String.valueOf(currentPlayer.getId())
        );

        player_age_textfield.setText(
                String.valueOf(currentPlayer.getAge())
        );

        team_name_combobox.setValue(
                currentPlayer.getTeamName()
        );

        playing_position_combobox.setValue(
                currentPlayer.getPlayingPosition()
        );

        player_type_combobox.setValue(
                currentPlayer.getPlayerType()
        );

        player_contact_textfield.setText(
                currentPlayer.getContactNumber()
        );

    }

    @FXML
    public void save_button_on_action(ActionEvent actionEvent) {

        String playerName =
                player_name_textfield.getText().trim();

        String playerIdText =
                player_id_textfield.getText().trim();

        String playerAgeText =
                player_age_textfield.getText().trim();

        String teamName =
                team_name_combobox.getValue();

        String playingPosition =
                playing_position_combobox.getValue();

        String playerType =
                player_type_combobox.getValue();

        String contactNumber =
                player_contact_textfield.getText().trim();

        if (playerName.isEmpty()
                || playerIdText.isEmpty()
                || playerAgeText.isEmpty()
                || teamName == null
                || playingPosition == null
                || playerType == null
                || contactNumber.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
                    "Please fill in all required fields."
            );
            return;
        }

        if (!playerName.matches("[a-zA-Z ]+")) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Name",
                    "Player name must contain only letters and spaces."
            );
            return;
        }

        int playerId;
        int playerAge;

        try {

            playerId = Integer.parseInt(playerIdText);
            playerAge = Integer.parseInt(playerAgeText);

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Player ID and age must be whole numbers."
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

        if (playerAge < 16 || playerAge > 50) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Age",
                    "Player age must be between 16 and 50."
            );
            return;
        }

        if (!contactNumber.matches("\\d{11}")) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Contact Number",
                    "Contact number must contain exactly 11 digits."
            );
            return;
        }

        if (playerType.equals("Captain")
                && teamName.equals("No Current Team")) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Player Type",
                    "A captain must belong to a team."
            );
            return;
        }

        for (Player player : playerList) {

            if (player != currentPlayer
                    && player.getId() == playerId) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Duplicate Player ID",
                        "This Player ID is already registered."
                );
                return;
            }

            if (player != currentPlayer
                    && player.getContactNumber() != null
                    && player.getContactNumber().equals(contactNumber)) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Duplicate Contact Number",
                        "This contact number is already registered."
                );
                return;
            }

            if (player != currentPlayer
                    && playerType.equals("Captain")
                    && teamName.equals(player.getTeamName())
                    && "Captain".equals(player.getPlayerType())) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Captain Already Exists",
                        "The selected team already has a captain."
                );
                return;
            }
        }

        if (currentPlayer == null) {

            currentPlayer = new Player(
                    playerId,
                    playerName,
                    "",
                    "Player",
                    playerAge,
                    teamName,
                    playingPosition,
                    playerType,
                    contactNumber,
                    "Fit",
                    "Eligible"
            );

            playerList.add(currentPlayer);

        } else {

            currentPlayer.setPlayerId(playerId);
            currentPlayer.setFullName(playerName);
            currentPlayer.setAge(playerAge);
            currentPlayer.setTeamName(teamName);
            currentPlayer.setPlayingPosition(playingPosition);
            currentPlayer.setPlayerType(playerType);
            currentPlayer.setContactNumber(contactNumber);
        }

        if (!savePlayersToFile()) {
            return;
        }

        displayPlayerInformation();


        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Player profile updated successfully."
        );
    }

    @FXML
    public void team_name_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @FXML
    public void playing_position_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @FXML
    public void player_type_combobox_on_action(
            ActionEvent actionEvent) {
    }

    @FXML
    public void back_button_on_action(
            ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "Arman/player/player_dashboard.fxml"
        );
    }

    @SuppressWarnings("unchecked")
    private boolean loadPlayersFromFile() {

        playerList.clear();

        File playerFile = new File(PLAYER_FILE_NAME);

        System.out.println(
                "Loading players from: "
                        + playerFile.getAbsolutePath()
        );

        if (!playerFile.exists()) {

            System.out.println(
                    "players.bin does not exist yet."
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
                        "players.bin does not contain valid Player data."
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

            return true;

        } catch (InvalidClassException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Incompatible Player File",
                    "players.bin was created using an older Player class.\n\n"
                            + "Delete players.bin and create the Player profile again."
            );

        } catch (FileNotFoundException e) {

            System.out.println(
                    "players.bin does not exist yet."
            );

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "File Error",
                    "Could not load Player profile data.\n\n"
                            + e.getMessage()
            );
        }

        return false;
    }

    private boolean savePlayersToFile() {

        File playerFile = new File(PLAYER_FILE_NAME);

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
                    "Could not save Player profile.\n\n"
                            + e.getMessage()
            );

            return false;
        }
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