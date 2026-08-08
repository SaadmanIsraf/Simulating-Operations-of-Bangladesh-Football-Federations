package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.Player;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.player_manager.PlayerManager;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerController {

    @javafx.fxml.FXML
    private ComboBox<String> playerComboBox;

    @javafx.fxml.FXML
    private TableView<PlayerModel> playerTable;

    @javafx.fxml.FXML
    private TableColumn<PlayerModel, String> playerNameColumn;

    @javafx.fxml.FXML
    private TableColumn<PlayerModel, String> teamColumn;

    @javafx.fxml.FXML
    private TableColumn<PlayerModel, String> positionColumn;

    @javafx.fxml.FXML
    private TableColumn<PlayerModel, String> fitnessColumn;

    private final ArrayList<PlayerModel> allPlayers = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        fitnessColumn.setCellValueFactory(new PropertyValueFactory<>("fitnessStatus"));

        // fetch real player data from players.bin
        List<Player> realPlayers = PlayerManager.getPlayerList();

        for (Player p : realPlayers) {
            allPlayers.add(new PlayerModel(
                    p.getFullName(),
                    p.getTeamName(),
                    p.getPlayingPosition(),
                    p.getFitnessStatus()
            ));

            playerComboBox.getItems().add(p.getFullName());
        }

        playerTable.setItems(FXCollections.observableArrayList(allPlayers));
    }

    @javafx.fxml.FXML
    public void searchPlayer() {

        String selectedPlayer = playerComboBox.getValue();

        if (selectedPlayer == null) {
            playerTable.setItems(FXCollections.observableArrayList(allPlayers));
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

    @javafx.fxml.FXML
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