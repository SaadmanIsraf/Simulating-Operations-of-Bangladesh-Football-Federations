package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class TeamController {

    @FXML
    private ComboBox<String> teamComboBox;
    @FXML
    private TableView<TeamModel> teamTable;
    @FXML
    private TableColumn<TeamModel, String> nameColumn;
    @FXML
    private TableColumn<TeamModel, String> coachColumn;
    @FXML
    private TableColumn<TeamModel, String> countryColumn;
    @FXML
    private TableColumn<TeamModel, Integer> rankColumn;

    private final ArrayList<TeamModel> allTeams = new ArrayList<>();

    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        coachColumn.setCellValueFactory(new PropertyValueFactory<>("coach"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));

        teamComboBox.getItems().addAll(
                "Bashundhara Kings",
                "Abahani Limited",
                "Mohammedan SC"
        );

        allTeams.add(new TeamModel("Bashundhara Kings", "Oscar Bruzon", "Bangladesh", 1));
        allTeams.add(new TeamModel("Abahani Limited", "Andres Gonzalez", "Bangladesh", 2));
        allTeams.add(new TeamModel("Mohammedan SC", "Alfredo Vera", "Bangladesh", 3));

        teamTable.setItems(FXCollections.observableArrayList(allTeams));
    }

    @FXML
    public void viewTeam() {

        String selectedTeam = teamComboBox.getValue();

        if (selectedTeam == null) {
            teamTable.setItems(FXCollections.observableArrayList(allTeams));
            return;
        }

        ArrayList<TeamModel> result = new ArrayList<>();
        for (TeamModel t : allTeams) {
            if (t.getTeamName().equals(selectedTeam)) {
                result.add(t);
            }
        }

        teamTable.setItems(FXCollections.observableArrayList(result));
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