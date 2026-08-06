package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.RecordFoulsCardsManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.matchofficial_recordfoulscards;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class matchofficial_recordfoulscardsController {

    @FXML
    private TextField matchTF;
    @FXML
    private TextField playerNameTF;
    @FXML
    private TextField minuteTF;

    @FXML
    private ComboBox<String> foulTypeCB;
    @FXML
    private ComboBox<String> cardTypeCB;

    @FXML
    private TableView<matchofficial_recordfoulscards> recordTable;

    @FXML
    private TableColumn<matchofficial_recordfoulscards, String> matchCol;
    @FXML
    private TableColumn<matchofficial_recordfoulscards, String> playerCol;
    @FXML
    private TableColumn<matchofficial_recordfoulscards, String> foulCol;
    @FXML
    private TableColumn<matchofficial_recordfoulscards, String> cardCol;
    @FXML
    private TableColumn<matchofficial_recordfoulscards, Integer> minuteCol;

    @FXML
    public void initialize() {

        foulTypeCB.getItems().addAll(
                "Handball",
                "Dangerous Tackle",
                "Holding",
                "Push",
                "Simulation"
        );

        cardTypeCB.getItems().addAll(
                "No Card",
                "Yellow Card",
                "Red Card"
        );

        matchCol.setCellValueFactory(new PropertyValueFactory<>("match"));
        playerCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        foulCol.setCellValueFactory(new PropertyValueFactory<>("foulType"));
        cardCol.setCellValueFactory(new PropertyValueFactory<>("cardType"));
        minuteCol.setCellValueFactory(new PropertyValueFactory<>("minute"));

        loadRecords();
    }

    private void loadRecords() {
        RecordFoulsCardsManager.loadFromFile();
        recordTable.getItems().setAll(RecordFoulsCardsManager.getRecordList());
        recordTable.refresh();
    }

    @FXML
    public void addButtonOnAction(ActionEvent event) {

        if (matchTF.getText().isEmpty()
                || playerNameTF.getText().isEmpty()
                || minuteTF.getText().isEmpty()
                || foulTypeCB.getValue() == null
                || cardTypeCB.getValue() == null) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Please fill all fields.");
            return;
        }

        matchofficial_recordfoulscards record =
                new matchofficial_recordfoulscards(
                        matchTF.getText(),
                        playerNameTF.getText(),
                        foulTypeCB.getValue(),
                        cardTypeCB.getValue(),
                        Integer.parseInt(minuteTF.getText())
                );

        RecordFoulsCardsManager.addRecord(record);
        RecordFoulsCardsManager.saveToFile();

        loadRecords();
        clearFields();

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Record added successfully.");
    }
    @FXML
    public void updateButtonOnAction(ActionEvent event) {

        matchofficial_recordfoulscards record =
                recordTable.getSelectionModel().getSelectedItem();

        if (record == null) {
            showAlert(Alert.AlertType.WARNING,
                    "Warning",
                    "Please select a record.");
            return;
        }

        record.setMatch(matchTF.getText());
        record.setPlayerName(playerNameTF.getText());
        record.setFoulType(foulTypeCB.getValue());
        record.setCardType(cardTypeCB.getValue());
        record.setMinute(Integer.parseInt(minuteTF.getText()));

        RecordFoulsCardsManager.saveToFile();

        recordTable.refresh();

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Record updated successfully.");
    }

    @FXML
    public void deleteButtonOnAction(ActionEvent event) {

        matchofficial_recordfoulscards record =
                recordTable.getSelectionModel().getSelectedItem();

        if (record == null) {
            showAlert(Alert.AlertType.WARNING,
                    "Warning",
                    "Please select a record.");
            return;
        }

        RecordFoulsCardsManager.removeRecord(record);
        RecordFoulsCardsManager.saveToFile();

        loadRecords();
        clearFields();

        showAlert(Alert.AlertType.INFORMATION,
                "Success",
                "Record deleted successfully.");
    }

    @FXML
    public void clearButtonOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void tableMouseClicked() {

        matchofficial_recordfoulscards record =
                recordTable.getSelectionModel().getSelectedItem();

        if (record == null) {
            return;
        }

        matchTF.setText(record.getMatch());
        playerNameTF.setText(record.getPlayerName());
        foulTypeCB.setValue(record.getFoulType());
        cardTypeCB.setValue(record.getCardType());
        minuteTF.setText(String.valueOf(record.getMinute()));
    }

    private void clearFields() {

        matchTF.clear();
        playerNameTF.clear();
        minuteTF.clear();

        foulTypeCB.setValue(null);
        cardTypeCB.setValue(null);

        recordTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type,
                           String title,
                           String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }


    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {

        SceneSwitcher.switchTo(
                "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/match_officials/matchofficialsdashboard.fxml"
        );
    }
}


