package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchOfficials;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.RecordFouls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class matchofficial_verifyplayereligibilityController implements UserReceiver {

    @FXML
    private ComboBox<String> playerCB;
    @FXML
    private TextField eligibilityTF;
    @FXML
    private Button addPlayerBtn;
    @FXML
    private TableColumn<PlayerCardSummary, String> matchCol;
    @FXML
    private TableColumn<PlayerCardSummary, String> eligibilityCol;
    @FXML
    private Button removePlayerBtn;
    @FXML
    private TableColumn<PlayerCardSummary, Integer> yellowCardCol;
    @FXML
    private ComboBox<Integer> redCardsCB;
    @FXML
    private TableColumn<PlayerCardSummary, String> playerNameCol;
    @FXML
    private TableColumn<PlayerCardSummary, Integer> redCardCol;
    @FXML
    private ComboBox<String> matchCB;
    @FXML
    private TableView<PlayerCardSummary> playerTable;
    @FXML
    private ComboBox<Integer> yellowCardsCB;
    @FXML
    private Label messageLabel;
    private MatchOfficials loggedInUser;
    @Override
    public void setLoggedInUser(User user){
        if (user instanceof MatchOfficials m){
            loggedInUser = m;
        }
        else {
            AlertGenerator.showAlert("Error", "This is not a valid user for this page");
        }
    }


    private ArrayList<RecordFouls> allFouls = new ArrayList<>();
    private ArrayList<PlayerCardSummary> manuallyAddedPlayers = new ArrayList<>();

    @FXML
    public void initialize() {

        matchCB.getItems().addAll(
                "Bangladesh vs India",
                "Abahani vs Mohammedan",
                "Bashundhara Kings vs Rahmatganj"
        );

        playerCB.getItems().addAll(
                "Rakib Hossain",
                "Topu Barman",
                "Jamal Bhuyan",
                "Sohel Rana"
        );

        yellowCardsCB.getItems().addAll(0, 1, 2, 3, 4, 5);
        redCardsCB.getItems().addAll(0, 1, 2);

        playerNameCol.setCellValueFactory(new PropertyValueFactory<PlayerCardSummary, String>("playerName"));
        matchCol.setCellValueFactory(new PropertyValueFactory<PlayerCardSummary, String>("match"));
        yellowCardCol.setCellValueFactory(new PropertyValueFactory<PlayerCardSummary, Integer>("yellowCount"));
        redCardCol.setCellValueFactory(new PropertyValueFactory<PlayerCardSummary, Integer>("redCount"));
        eligibilityCol.setCellValueFactory(new PropertyValueFactory<PlayerCardSummary, String>("eligibility"));

        loadFoulsFromFile();
        refreshPlayerTable();

        matchCB.valueProperty().addListener((obs, oldVal, newVal) -> refreshPlayerTable());
    }

    private void loadFoulsFromFile() {

        allFouls.clear();
        ArrayList<Object> records = BinaryFileUtility.readObjects("RecordFouls.bin");

        for (Object record : records) {
            if (record instanceof RecordFouls recordFouls) {
                allFouls.add(recordFouls);
            }
        }
    }

    private void refreshPlayerTable() {

        playerTable.getItems().clear();

        String selectedMatch = matchCB.getValue();

        Map<String, PlayerCardSummary> summaryMap = new LinkedHashMap<>();

        for (RecordFouls foul : allFouls) {

            if (selectedMatch != null && !selectedMatch.equals(foul.getMatchbetween())) {
                continue;
            }

            String player = foul.getPlayername();

            PlayerCardSummary summary = summaryMap.get(player);
            if (summary == null) {
                summary = new PlayerCardSummary(player, foul.getMatchbetween(), 0, 0);
                summaryMap.put(player, summary);
            }

            if ("Yellow Card".equalsIgnoreCase(foul.getCardType())) {
                summary.setYellowCount(summary.getYellowCount() + 1);
            } else if ("Red Card".equalsIgnoreCase(foul.getCardType())) {
                summary.setRedCount(summary.getRedCount() + 1);
            }

            summary.setMatch(foul.getMatchbetween());
        }

        for (PlayerCardSummary manual : manuallyAddedPlayers) {

            if (selectedMatch != null && !selectedMatch.equals(manual.getMatch())) {
                continue;
            }

            if (!summaryMap.containsKey(manual.getPlayerName())) {
                summaryMap.put(manual.getPlayerName(), manual);
            }
        }

        for (PlayerCardSummary summary : summaryMap.values()) {
            summary.setEligibility(computeEligibility(summary.getYellowCount(), summary.getRedCount()));
            playerTable.getItems().add(summary);
        }
    }

    private String computeEligibility(int yellow, int red) {
        if (red >= 1 || yellow >= 2) {
            return "Not Eligible";
        }
        return "Eligible";
    }

    private boolean validateInput() {

        if (matchCB.getValue() == null) {
            messageLabel.setText("Please select a match.");
            matchCB.requestFocus();
            return false;
        }

        if (playerCB.getValue() == null) {
            messageLabel.setText("Please select a player.");
            playerCB.requestFocus();
            return false;
        }

        if (yellowCardsCB.getValue() == null) {
            messageLabel.setText("Please select yellow cards.");
            yellowCardsCB.requestFocus();
            return false;
        }

        if (redCardsCB.getValue() == null) {
            messageLabel.setText("Please select red cards.");
            redCardsCB.requestFocus();
            return false;
        }

        return true;
    }

    @FXML
    public void checkEligibilityOA(ActionEvent actionEvent) {

        if (playerCB.getValue() == null) {
            messageLabel.setText("Please select a player.");
            playerCB.requestFocus();
            return;
        }

        String selectedPlayer = playerCB.getValue();

        for (PlayerCardSummary summary : playerTable.getItems()) {
            if (summary.getPlayerName().equals(selectedPlayer)) {
                eligibilityTF.setText(summary.getEligibility());
                messageLabel.setText("Eligibility checked successfully.");
                return;
            }
        }

        eligibilityTF.setText("Eligible");
        messageLabel.setText("No card records found. Player is Eligible by default.");
    }

    @FXML
    public void updateCardsOA(ActionEvent actionEvent) {

        loadFoulsFromFile();
        refreshPlayerTable();

        messageLabel.setText("Card records refreshed from file.");
    }

    @FXML
    public void addPlayerOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }

        String playerName = playerCB.getValue();
        String matchName = matchCB.getValue();
        int yellow = yellowCardsCB.getValue();
        int red = redCardsCB.getValue();

        for (PlayerCardSummary summary : playerTable.getItems()) {
            if (summary.getPlayerName().equals(playerName)) {
                messageLabel.setText("Player already in the table.");
                return;
            }
        }

        PlayerCardSummary summary = new PlayerCardSummary(playerName, matchName, yellow, red);
        summary.setEligibility(computeEligibility(yellow, red));

        manuallyAddedPlayers.add(summary);
        playerTable.getItems().add(summary);

        eligibilityTF.setText(summary.getEligibility());
        messageLabel.setText("Player added successfully.");
    }

    @FXML
    public void removePlayerOA(ActionEvent actionEvent) {

        if (playerCB.getValue() == null) {
            messageLabel.setText("Select a player to remove.");
            playerCB.requestFocus();
            return;
        }

        String playerName = playerCB.getValue();

        PlayerCardSummary toRemove = null;
        for (PlayerCardSummary summary : playerTable.getItems()) {
            if (summary.getPlayerName().equals(playerName)) {
                toRemove = summary;
                break;
            }
        }

        if (toRemove == null) {
            messageLabel.setText("Player not found in the table.");
            return;
        }

        playerTable.getItems().remove(toRemove);
        manuallyAddedPlayers.remove(toRemove);

        messageLabel.setText("Player removed successfully.");
    }

    @FXML
    public void viewCardsOA(ActionEvent actionEvent) {

        if (matchCB.getValue() == null) {
            messageLabel.setText("Showing all players' card records.");
        } else {
            messageLabel.setText("Showing card records for " + matchCB.getValue() + ".");
        }

        refreshPlayerTable();
    }

    @FXML
    public void refreshOA(ActionEvent actionEvent) {

        playerCB.getSelectionModel().clearSelection();
        matchCB.getSelectionModel().clearSelection();
        yellowCardsCB.getSelectionModel().clearSelection();
        redCardsCB.getSelectionModel().clearSelection();

        eligibilityTF.clear();
        messageLabel.setText("");

        loadFoulsFromFile();
        refreshPlayerTable();
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("turjo/match_officials/matchofficialsdashboard.fxml");
    }

    public static class PlayerCardSummary {

        private String playerName;
        private String match;
        private int yellowCount;
        private int redCount;
        private String eligibility;

        public PlayerCardSummary(String playerName, String match, int yellowCount, int redCount) {
            this.playerName = playerName;
            this.match = match;
            this.yellowCount = yellowCount;
            this.redCount = redCount;
            this.eligibility = "Eligible";
        }

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public String getMatch() {
            return match;
        }

        public void setMatch(String match) {
            this.match = match;
        }

        public int getYellowCount() {
            return yellowCount;
        }

        public void setYellowCount(int yellowCount) {
            this.yellowCount = yellowCount;
        }

        public int getRedCount() {
            return redCount;
        }

        public void setRedCount(int redCount) {
            this.redCount = redCount;
        }

        public String getEligibility() {
            return eligibility;
        }

        public void setEligibility(String eligibility) {
            this.eligibility = eligibility;
        }
    }
}