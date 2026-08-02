//package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_controller;
//
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
//import javafx.event.ActionEvent;
//import javafx.scene.control.*;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class player_profile_controller
//{
//    @javafx.fxml.FXML
//    private TextField player_age_textfield;
//    @javafx.fxml.FXML
//    private PasswordField new_password_field;
//    @javafx.fxml.FXML
//    private Label team_name_label;
//    @javafx.fxml.FXML
//    private Label playing_position_label;
//    @javafx.fxml.FXML
//    private TextField player_contact_textfield;
//    @javafx.fxml.FXML
//    private TextField player_name_textfield;
//    @javafx.fxml.FXML
//    private Label player_id_label;
//    @javafx.fxml.FXML
//    private Label information_label;
//    @javafx.fxml.FXML
//    private Label player_name_label;
//    @javafx.fxml.FXML
//    private Label player_age_label;
//    @javafx.fxml.FXML
//    private Label player_contact_label;
//    @javafx.fxml.FXML
//    private TextField player_id_textfield;
//    @javafx.fxml.FXML
//    private TextField team_name_textfield;
//    @javafx.fxml.FXML
//    private ComboBox<String> playing_position_combobox;
//
//    private final List<Player> playerList = new ArrayList<>();
//    private Player currentPlayer;
//
//    private static final String PLAYER_FILE_NAME = "players.bin";
//
//    @javafx.fxml.FXML
//    public void initialize() {
//
//        playing_position_combobox.getItems().addAll(
//                "Goalkeeper",
//                "Defender",
//                "Midfielder",
//                "Forward"
//        );
//
//        loadPlayersFromFile();
//
//        if (!playerList.isEmpty()) {
//
//            currentPlayer = playerList.get(0);
//            displayPlayerInformation();
//
//        } else {
//
//            information_label.setText(
//                    "No Player profile found. Fill in the form to create one."
//            );
//        }
//    }
//
//    private void displayPlayerInformation() {
//
//        player_name_label.setText(
//                currentPlayer.getName()
//        );
//
//        player_id_label.setText(
//                String.valueOf(currentPlayer.getId())
//        );
//
//        player_age_label.setText(
//                String.valueOf(currentPlayer.getAge())
//        );
//
//        team_name_label.setText(
//                currentPlayer.getTeamName()
//        );
//
//        playing_position_label.setText(
//                currentPlayer.getPlayingPosition()
//        );
//
//        player_contact_label.setText(
//                currentPlayer.getContactNumber()
//        );
//
//        player_name_textfield.setText(
//                currentPlayer.getName()
//        );
//
//        player_id_textfield.setText(
//                String.valueOf(currentPlayer.getId())
//        );
//
//        player_age_textfield.setText(
//                String.valueOf(currentPlayer.getAge())
//        );
//
//        team_name_textfield.setText(
//                currentPlayer.getTeamName()
//        );
//
//        playing_position_combobox.setValue(
//                currentPlayer.getPlayingPosition()
//        );
//
//        player_contact_textfield.setText(
//                currentPlayer.getContactNumber()
//        );
//
//        information_label.setText("");
//    }
//
//    @javafx.fxml.FXML
//    public void save_button_on_action(ActionEvent actionEvent) {
//
//        String playerName =
//                player_name_textfield.getText().trim();
//
//        String playerIdText =
//                player_id_textfield.getText().trim();
//
//        String playerAgeText =
//                player_age_textfield.getText().trim();
//
//        String teamName =
//                team_name_textfield.getText().trim();
//
//        String playingPosition =
//                playing_position_combobox.getValue();
//
//        String contactNumber =
//                player_contact_textfield.getText().trim();
//
//        String newPassword =
//                new_password_field.getText();
//
//        if (playerName.isEmpty()
//                || playerIdText.isEmpty()
//                || playerAgeText.isEmpty()
//                || teamName.isEmpty()
//                || playingPosition == null
//                || contactNumber.isEmpty()) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Empty Field",
//                    "Please fill in all required fields."
//            );
//            return;
//        }
//
//        if (!playerName.matches("[a-zA-Z ]+")) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Name",
//                    "Player name must contain only letters and spaces."
//            );
//            return;
//        }
//
//        int playerId;
//        int playerAge;
//
//        try {
//
//            playerId = Integer.parseInt(playerIdText);
//            playerAge = Integer.parseInt(playerAgeText);
//
//        } catch (NumberFormatException e) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Input",
//                    "Player ID and age must be whole numbers."
//            );
//            return;
//        }
//
//        if (playerId <= 0) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Player ID",
//                    "Player ID must be greater than zero."
//            );
//            return;
//        }
//
//        if (playerAge < 16 || playerAge > 50) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Age",
//                    "Player age must be between 16 and 50."
//            );
//            return;
//        }
//
//        if (!contactNumber.matches("\\d{11}")) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Contact Number",
//                    "Contact number must contain exactly 11 digits."
//            );
//            return;
//        }
//
//        if (!newPassword.isEmpty()
//                && newPassword.length() < 8) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Password",
//                    "Password must contain at least 8 characters."
//            );
//            return;
//        }
//
//        for (Player player : playerList) {
//
//            if (player != currentPlayer
//                    && player.getId() == playerId) {
//
//                showAlert(
//                        Alert.AlertType.ERROR,
//                        "Duplicate Player ID",
//                        "This Player ID is already registered."
//                );
//                return;
//            }
//
//            if (player != currentPlayer
//                    && player.getContactNumber()
//                    .equals(contactNumber)) {
//
//                showAlert(
//                        Alert.AlertType.ERROR,
//                        "Duplicate Contact Number",
//                        "This contact number is already registered."
//                );
//                return;
//            }
//        }
//
//        if (currentPlayer == null) {
//
//            if (newPassword.isEmpty()) {
//
//                showAlert(
//                        Alert.AlertType.ERROR,
//                        "Password Required",
//                        "Password is required when creating the profile."
//                );
//                return;
//            }
//
//            currentPlayer = new Player(
//                    playerId,
//                    playerName,
//                    "",
//                    newPassword,
//                    "Player",
//                    playerAge,
//                    teamName,
//                    playingPosition,
//                    contactNumber,
//                    "Fit",
//                    "Eligible"
//            );
//
//            playerList.add(currentPlayer);
//
//        } else {
//
//            currentPlayer.setId(playerId);
//            currentPlayer.setName(playerName);
//            currentPlayer.setAge(playerAge);
//            currentPlayer.setTeamName(teamName);
//            currentPlayer.setPlayingPosition(playingPosition);
//            currentPlayer.setContactNumber(contactNumber);
//
//            if (!newPassword.isEmpty()) {
//                currentPlayer.changePassword(newPassword);
//            }
//        }
//
//        savePlayersToFile();
//        displayPlayerInformation();
//        new_password_field.clear();
//
//        information_label.setText(
//                "Player profile saved successfully."
//        );
//
//        showAlert(
//                Alert.AlertType.INFORMATION,
//                "Successful",
//                "Player Profile Updated Successfully!"
//        );
//    }
//
//    @javafx.fxml.FXML
//    public void playing_position_combobox_on_action(
//            ActionEvent actionEvent) {
//    }
//
//    @javafx.fxml.FXML
//    public void back_button_on_action(ActionEvent actionEvent) {
//
//        SceneSwitcher.switchTo(
//                "Arman/player/player_dashboard.fxml"
//        );
//    }
//
//    @SuppressWarnings("unchecked")
//    private void loadPlayersFromFile() {
//
//        try (ObjectInputStream in =
//                     new ObjectInputStream(
//                             new FileInputStream(PLAYER_FILE_NAME))) {
//
//            playerList.clear();
//
//            playerList.addAll(
//                    (ArrayList<Player>) in.readObject()
//            );
//
//        } catch (IOException | ClassNotFoundException e) {
//
//            System.out.println(
//                    "Could not load player data."
//            );
//        }
//    }
//
//    private void savePlayersToFile() {
//
//        try (ObjectOutputStream out =
//                     new ObjectOutputStream(
//                             new FileOutputStream(PLAYER_FILE_NAME))) {
//
//            ArrayList<Player> tempList =
//                    new ArrayList<>(playerList);
//
//            out.writeObject(tempList);
//
//        } catch (IOException e) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "File Error",
//                    "Could not save Player profile."
//            );
//        }
//    }
//
//    private void showAlert(
//            Alert.AlertType alertType,
//            String title,
//            String message) {
//
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//}