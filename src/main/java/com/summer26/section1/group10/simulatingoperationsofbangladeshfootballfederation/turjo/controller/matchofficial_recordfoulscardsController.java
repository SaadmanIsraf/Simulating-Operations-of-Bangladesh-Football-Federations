package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.recordfoulscards;
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

public class matchofficial_recordfoulscardsController {

    @FXML
    private ComboBox<String> matchComboBox;
    @FXML
    private ComboBox<String> playerComboBox;

    @FXML
    private TableView<recordfoulscards> foulCardTable;

    @FXML
    private TableColumn<recordfoulscards, String> foulTypeColumn;
    @FXML
    private TableColumn<recordfoulscards, String> cardTypeColumn;
    @FXML
    private TableColumn<recordfoulscards, String> minuteColumn;

    private final ArrayList<recordfoulscards> allRecords = new ArrayList<>();

    @FXML
    public void initialize() {

        foulTypeColumn.setCellValueFactory(new PropertyValueFactory<>("foulType"));
        cardTypeColumn.setCellValueFactory(new PropertyValueFactory<>("cardType"));
        minuteColumn.setCellValueFactory(new PropertyValueFactory<>("minute"));

        matchComboBox.getItems().addAll(
                "Bashundhara Kings vs Abahani Limited",
                "Mohammedan SC vs Sheikh Russel",
                "Abahani Limited vs Rahmatganj"
        );

        playerComboBox.getItems().addAll(
                "Rakib Hossain",
                "Jamal Bhuyan",
                "Sohel Rana",
                "Topu Barman"
        );

        allRecords.add(new recordfoulscards(
                "Bashundhara Kings vs Abahani Limited",
                "Rakib Hossain",
                "Dangerous Tackle",
                "Yellow Card",
                "25'"
        ));

        allRecords.add(new recordfoulscards(
                "Bashundhara Kings vs Abahani Limited",
                "Rakib Hossain",
                "Handball",
                "No Card",
                "70'"
        ));

        allRecords.add(new recordfoulscards(
                "Mohammedan SC vs Sheikh Russel",
                "Jamal Bhuyan",
                "Serious Foul",
                "Red Card",
                "81'"
        ));

        allRecords.add(new recordfoulscards(
                "Abahani Limited vs Rahmatganj",
                "Topu Barman",
                "Holding",
                "Yellow Card",
                "43'"
        ));

        foulCardTable.setItems(FXCollections.observableArrayList());
    }

    @FXML
    public void searchRecord() {

        String selectedMatch = matchComboBox.getValue();
        String selectedPlayer = playerComboBox.getValue();

        if (selectedMatch == null || selectedPlayer == null) {
            foulCardTable.setItems(FXCollections.observableArrayList());
            return;
        }

        ArrayList<recordfoulscards> result = new ArrayList<>();

        for (recordfoulscards record : allRecords) {

            if (record.getMatchName().equals(selectedMatch)
                    && record.getPlayerName().equals(selectedPlayer)) {

                result.add(record);
            }
        }

        foulCardTable.setItems(FXCollections.observableArrayList(result));
    }

    @FXML
    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/matchofficialsdashboard.fxml"));

        Parent home = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(new Scene(home));
        stage.setTitle("Match Official Dashboard");
        stage.show();
    }
}