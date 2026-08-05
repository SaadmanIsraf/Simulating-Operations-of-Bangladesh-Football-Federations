package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class player_profile_controller {

    @FXML
    private TextField player_age_textfield;

    @FXML
    private PasswordField new_password_field;

    @FXML
    private Label team_name_label;

    @FXML
    private Label playing_position_label;

    @FXML
    private TextField player_contact_textfield;

    @FXML
    private TextField player_name_textfield;

    @FXML
    private Label player_id_label;

    @FXML
    private Label information_label;

    @FXML
    private Label player_name_label;

    @FXML
    private Label player_age_label;

    @FXML
    private Label player_contact_label;

    @FXML
    private TextField player_id_textfield;

    @FXML
    private TextField team_name_textfield;

    @FXML
    private ComboBox<String> playing_position_combobox;

    private final List<Player> playerList = new ArrayList<>();

    private Player currentPlayer;

    private static final String PLAYER_FILE_NAME = "players.bin";

    @FXML
    public void initialize() {

        playing_position_combobox.getItems().setAll(
                "Goalkeeper",
                "Defender",
                "Midfielder",
                "Forward"
        );

        loadPlayersFromFile();

        if (!playerList.isEmpty()) {

            currentPlayer = playerList.get(0);
            displayPlayerInformation();

            new_password_field.setDisable(true);
            new_password_field.setPromptText(
                    "Password cannot be changed here"
            );

        } else {

            currentPlayer = null;

            information_label.setText(
                    "No Player profile found. Fill in the form to create one."
            );

            new_password_field.setDisable(false);
            new_password_field.setPromptText(
                    "Enter password for new profile"
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

        team_name_textfield.setText(
                currentPlayer.getTeamName()
        );

        playing_position_combobox.setValue(
                currentPlayer.getPlayingPosition()
        );

        player_contact_textfield.setText(
                currentPlayer.getContactNumber()
        );

        information_label.setText("");
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
                team_name_textfield.getText().trim();

        String playingPosition =
                playing_position_combobox.getValue();

        String contactNumber =
                player_contact_textfield.getText().trim();

        String password =
                new_password_field.getText();

        if (playerName.isEmpty()
                || playerIdText.isEmpty()
                || playerAgeText.isEmpty()
                || teamName.isEmpty()
                || playingPosition == null
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
        }

        if (currentPlayer == null) {

            if (password.isEmpty()) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Password Required",
                        "Password is required when creating a new profile."
                );
                return;
            }

            if (password.length() < 8) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Invalid Password",
                        "Password must contain at least 8 characters."
                );
                return;
            }

            currentPlayer = new Player(
                    playerId,
                    playerName,
                    password,
                    "Player",
                    playerAge,
                    teamName,
                    playingPosition,
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
            currentPlayer.setContactNumber(contactNumber);
        }

        if (!savePlayersToFile()) {
            return;
        }

        displayPlayerInformation();

        new_password_field.clear();
        new_password_field.setDisable(true);
        new_password_field.setPromptText(
                "Password cannot be changed here"
        );

        information_label.setText(
                "Player profile saved successfully."
        );

        showAlert(
                Alert.AlertType.INFORMATION,
                "Successful",
                "Player Profile Updated Successfully!"
        );
    }

    @FXML
    public void playing_position_combobox_on_action(
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
    private void loadPlayersFromFile() {

        playerList.clear();

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(
                             new FileInputStream(
                                     PLAYER_FILE_NAME
                             ))) {

            Object object = inputStream.readObject();

            if (object instanceof ArrayList<?>) {

                playerList.addAll(
                        (ArrayList<Player>) object
                );
            }

        } catch (FileNotFoundException e) {

            System.out.println(
                    "players.bin does not exist yet."
            );

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "File Error",
                    "Could not load Player profile data."
            );
        }
    }

    private boolean savePlayersToFile() {

        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(
                             new FileOutputStream(
                                     PLAYER_FILE_NAME
                             ))) {

            ArrayList<Player> savedPlayers =
                    new ArrayList<>(playerList);

            outputStream.writeObject(savedPlayers);
            return true;

        } catch (IOException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "File Error",
                    "Could not save Player profile."
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