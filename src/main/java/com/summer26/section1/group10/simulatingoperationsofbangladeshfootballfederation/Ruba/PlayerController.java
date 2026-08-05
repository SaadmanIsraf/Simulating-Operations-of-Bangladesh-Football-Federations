package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerController {

    @FXML
    private ComboBox<String> playerComboBox;
    @FXML
    private TableView<PlayerModel> playerTable;
    @FXML
    private TableColumn<PlayerModel, String> playerNameColumn;
    @FXML
    private TableColumn<PlayerModel, String> teamColumn;
    @FXML
    private TableColumn<PlayerModel, Integer> goalColumn;
    @FXML
    private TableColumn<PlayerModel, Integer> appearanceColumn;

    private final ArrayList<PlayerModel> allPlayers = new ArrayList<>();

    @FXML
    public void initialize() {

        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        goalColumn.setCellValueFactory(new PropertyValueFactory<>("goals"));
        appearanceColumn.setCellValueFactory(new PropertyValueFactory<>("appearances"));

        playerComboBox.getItems().addAll(
                "Rakib Hossain",
                "Jamal Bhuyan",
                "Topu Barman"
        );

        allPlayers.add(new PlayerModel("Rakib Hossain", "Bashundhara Kings", 8, 15));
        allPlayers.add(new PlayerModel("Jamal Bhuyan", "Sheikh Russel", 5, 12));
        allPlayers.add(new PlayerModel("Topu Barman", "Abahani Limited", 3, 10));

        playerTable.setItems(FXCollections.observableArrayList(allPlayers));
    }

    @FXML
    public void searchPlayer() {

        String selectedPlayer = playerComboBox.getValue();

        if (selectedPlayer == null) {
            showValidationAlert("Please select a player first.");
            return;
        }

        ArrayList<PlayerModel> result = new ArrayList<>();
        for (PlayerModel p : allPlayers) {
            if (p.getPlayerName().equals(selectedPlayer)) {
                result.add(p);
            }
        }

        playerTable.setItems(FXCollections.observableArrayList(result));
    }

    private void showValidationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Selection Required");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/Ruba/SpectatorDashboard.fxml"));
        Parent home = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(home));
        stage.setTitle("Spectator Dashboard");
        stage.show();
    }
}