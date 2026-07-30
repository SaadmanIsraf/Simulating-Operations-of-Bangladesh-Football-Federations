package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class matchofficial_verifyplayereligibilityController {

    @FXML
    private ComboBox<String> matchCB;

    @FXML
    private ComboBox<String> playerCB;

    @FXML
    private TableView<PlayerEligibility> playerTable;

    @FXML
    private TableColumn<PlayerEligibility, String> playerNameCol;

    @FXML
    private TableColumn<PlayerEligibility, String> matchCol;

    @FXML
    private TableColumn<PlayerEligibility, Integer> yellowCardCol;

    @FXML
    private TableColumn<PlayerEligibility, Integer> redCardCol;

    @FXML
    private TableColumn<PlayerEligibility, String> eligibilityCol;

    private final ObservableList<PlayerEligibility> playerList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        matchCB.setItems(FXCollections.observableArrayList(
                "Abahani Ltd vs Bashundhara Kings",
                "Mohammedan SC vs Sheikh Russel",
                "Police FC vs Brothers Union",
                "Fortis FC vs Rahmatganj"
        ));

        playerCB.setItems(FXCollections.observableArrayList(
                "Rakib Hossain",
                "Jamal Bhuyan",
                "Topu Barman",
                "Biplu Ahmed",
                "Sohel Rana",
                "Emon Mahmud",
                "Sujon Mia",
                "Rahim Uddin"
        ));

        playerNameCol.setCellValueFactory(
                new PropertyValueFactory<>("playerName"));

        matchCol.setCellValueFactory(
                new PropertyValueFactory<>("match"));

        yellowCardCol.setCellValueFactory(
                new PropertyValueFactory<>("yellowCards"));

        redCardCol.setCellValueFactory(
                new PropertyValueFactory<>("redCards"));

        eligibilityCol.setCellValueFactory(
                new PropertyValueFactory<>("eligibility"));

        playerTable.setItems(playerList);

        playerList.add(new PlayerEligibility(
                "Rakib Hossain",
                "Abahani Ltd vs Bashundhara Kings",
                1,
                0,
                "Eligible"
        ));

        playerList.add(new PlayerEligibility(
                "Jamal Bhuyan",
                "Mohammedan SC vs Sheikh Russel",
                2,
                0,
                "Eligible"
        ));

        playerList.add(new PlayerEligibility(
                "Topu Barman",
                "Police FC vs Brothers Union",
                3,
                0,
                "Suspended"
        ));
    }

    @FXML
    public void addPlayerOA(ActionEvent actionEvent) {

        if (matchCB.getValue() == null || playerCB.getValue() == null) {

            showAlert(
                    "Error",
                    "Please select both a match and a player."
            );
            return;
        }

        for (PlayerEligibility player : playerList) {

            if (player.getPlayerName().equals(playerCB.getValue())
                    && player.getMatch().equals(matchCB.getValue())) {

                showAlert(
                        "Duplicate",
                        "This player is already added for the selected match."
                );
                return;
            }
        }

        playerList.add(new PlayerEligibility(
                playerCB.getValue(),
                matchCB.getValue(),
                0,
                0,
                "Eligible"
        ));

        playerTable.refresh();

        showAlert(
                "Success",
                "Player added successfully."
        );
    }
    @FXML
    public void removePlayerOA(ActionEvent actionEvent) {

        PlayerEligibility selected =
                playerTable.getSelectionModel().getSelectedItem();

        if (selected == null) {

            showAlert(
                    "Error",
                    "Please select a player to remove."
            );
            return;
        }

        playerList.remove(selected);

        showAlert(
                "Removed",
                "Player removed successfully."
        );
    }

    @FXML
    public void viewCardsOA(ActionEvent actionEvent) {

        PlayerEligibility selected =
                playerTable.getSelectionModel().getSelectedItem();

        if (selected == null) {

            showAlert(
                    "Error",
                    "Please select a player from the table."
            );
            return;
        }

        showAlert(
                "Player Cards",
                "Player: " + selected.getPlayerName()
                        + "\nYellow Cards: " + selected.getYellowCards()
                        + "\nRed Cards: " + selected.getRedCards()
        );
    }

    @FXML
    public void updateCardsOA(ActionEvent actionEvent) {

        PlayerEligibility selected =
                playerTable.getSelectionModel().getSelectedItem();

        if (selected == null) {

            showAlert(
                    "Error",
                    "Please select a player first."
            );
            return;
        }

        selected.setYellowCards(selected.getYellowCards() + 1);

        if (selected.getYellowCards() >= 5) {
            selected.setRedCards(1);
        }

        playerTable.refresh();

        showAlert(
                "Updated",
                "Card record updated successfully."
        );
    }

    @FXML
    public void checkEligibilityOA(ActionEvent actionEvent) {

        PlayerEligibility selected =
                playerTable.getSelectionModel().getSelectedItem();

        if (selected == null) {

            showAlert(
                    "Error",
                    "Please select a player first."
            );
            return;
        }

        if (selected.getRedCards() >= 1 ||
                selected.getYellowCards() >= 3) {

            selected.setEligibility("Suspended");
        }
        else {

            selected.setEligibility("Eligible");
        }

        playerTable.refresh();

        showAlert(
                "Updated",
                "Eligibility updated in the table."
        );
    }
    @FXML
    public void refreshOA(ActionEvent actionEvent) {

        playerTable.refresh();

        showAlert(
                "Refreshed",
                "Table refreshed successfully."
        );
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

        matchCB.setValue(null);
        playerCB.setValue(null);
        playerTable.getSelectionModel().clearSelection();

        showAlert(
                "Back",
                "Returning to previous page."
        );
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static class PlayerEligibility {

        private final SimpleStringProperty playerName;
        private final SimpleStringProperty match;
        private final SimpleIntegerProperty yellowCards;
        private final SimpleIntegerProperty redCards;
        private final SimpleStringProperty eligibility;

        public PlayerEligibility(String playerName,
                                 String match,
                                 int yellowCards,
                                 int redCards,
                                 String eligibility) {

            this.playerName = new SimpleStringProperty(playerName);
            this.match = new SimpleStringProperty(match);
            this.yellowCards = new SimpleIntegerProperty(yellowCards);
            this.redCards = new SimpleIntegerProperty(redCards);
            this.eligibility = new SimpleStringProperty(eligibility);
        }

        public String getPlayerName() {
            return playerName.get();
        }

        public void setPlayerName(String playerName) {
            this.playerName.set(playerName);
        }

        public String getMatch() {
            return match.get();
        }

        public void setMatch(String match) {
            this.match.set(match);
        }

        public int getYellowCards() {
            return yellowCards.get();
        }

        public void setYellowCards(int yellowCards) {
            this.yellowCards.set(yellowCards);
        }

        public int getRedCards() {
            return redCards.get();
        }

        public void setRedCards(int redCards) {
            this.redCards.set(redCards);
        }

        public String getEligibility() {
            return eligibility.get();
        }

        public void setEligibility(String eligibility) {
            this.eligibility.set(eligibility);
        }
    }
}