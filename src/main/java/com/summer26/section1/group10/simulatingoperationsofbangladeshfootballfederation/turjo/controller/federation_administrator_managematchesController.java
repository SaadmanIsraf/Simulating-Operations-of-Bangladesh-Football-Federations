package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class federation_administrator_managematchesController {


    @FXML
    private TableColumn<ObservableList<String>, String> awayTeamCol;

    @FXML
    private ComboBox<String> cmbMatchStatus;

    @FXML
    private TextField txtMatchTime;

    @FXML
    private ComboBox<String> cmbCompetition;

    @FXML
    private TextField txtMatchId;

    @FXML
    private TableColumn<ObservableList<String>, String> homeTeamCol;

    @FXML
    private TableColumn<ObservableList<String>, String> competitionCol;

    @FXML
    private TableColumn<ObservableList<String>, String> matchIdCol;

    @FXML
    private TableColumn<ObservableList<String>, String> stadiumCol;

    @FXML
    private ComboBox<String> cmbAwayTeam;

    @FXML
    private TableColumn<ObservableList<String>, String> dateCol;

    @FXML
    private TableColumn<ObservableList<String>, String> statusCol;

    @FXML
    private TableView<ObservableList<String>> tblMatches;

    @FXML
    private ComboBox<String> cmbHomeTeam;

    @FXML
    private DatePicker dpMatchDate;

    @FXML
    private TableColumn<ObservableList<String>, String> timeCol;

    @FXML
    private ComboBox<String> cmbStadium;

    @FXML
    private TextField assignedOfficialTF;

    @FXML
    private TableColumn<ObservableList<String>, String> officialCol;


    private ObservableList<ObservableList<String>> matchList =
            FXCollections.observableArrayList();



    @FXML
    public void initialize() {


        cmbMatchStatus.setItems(FXCollections.observableArrayList(
                "Scheduled",
                "Ongoing",
                "Finished",
                "Cancelled"
        ));


        cmbCompetition.setItems(FXCollections.observableArrayList(
                "Premier League",
                "Federation Cup",
                "National Championship"
        ));


        cmbHomeTeam.setItems(FXCollections.observableArrayList(
                "Abahani Limited",
                "Mohammedan SC",
                "Bashundhara Kings",
                "Sheikh Russel"
        ));


        cmbAwayTeam.setItems(FXCollections.observableArrayList(
                "Abahani Limited",
                "Mohammedan SC",
                "Bashundhara Kings",
                "Sheikh Russel"
        ));


        cmbStadium.setItems(FXCollections.observableArrayList(
                "Bangabandhu Stadium",
                "National Stadium",
                "Sylhet Stadium"
        ));


        cmbMatchStatus.setValue("Scheduled");


        matchIdCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(0)
                ));


        homeTeamCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(1)
                ));


        awayTeamCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(2)
                ));


        competitionCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(3)
                ));


        stadiumCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(4)
                ));


        dateCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(5)
                ));


        timeCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(6)
                ));


        statusCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(7)
                ));


        officialCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(8)
                ));


        tblMatches.setItems(matchList);

    }




    @FXML
    public void createMatchOA(ActionEvent actionEvent) {


        if(txtMatchId.getText().isEmpty()
                || cmbHomeTeam.getValue() == null
                || cmbAwayTeam.getValue() == null
                || dpMatchDate.getValue() == null) {


            showAlert(
                    "Error",
                    "Please fill all match information"
            );

            return;
        }



        ObservableList<String> match =
                FXCollections.observableArrayList();


        match.add(txtMatchId.getText());

        match.add(cmbHomeTeam.getValue());

        match.add(cmbAwayTeam.getValue());

        match.add(cmbCompetition.getValue());

        match.add(cmbStadium.getValue());

        match.add(dpMatchDate.getValue().toString());

        match.add(txtMatchTime.getText());

        match.add(cmbMatchStatus.getValue());

        match.add(assignedOfficialTF.getText());



        matchList.add(match);


        tblMatches.refresh();



        showAlert(
                "Success",
                "Match Created Successfully"
        );

    }





    @FXML
    public void updateMatchOA(ActionEvent actionEvent) {


        ObservableList<String> selected =
                tblMatches.getSelectionModel()
                        .getSelectedItem();


        if(selected == null){

            showAlert(
                    "Error",
                    "Select a match from table first"
            );

            return;
        }



        selected.set(0, txtMatchId.getText());
        selected.set(1, cmbHomeTeam.getValue());
        selected.set(2, cmbAwayTeam.getValue());
        selected.set(3, cmbCompetition.getValue());
        selected.set(4, cmbStadium.getValue());
        selected.set(5, dpMatchDate.getValue().toString());
        selected.set(6, txtMatchTime.getText());
        selected.set(7, cmbMatchStatus.getValue());
        selected.set(8, assignedOfficialTF.getText());



        tblMatches.refresh();



        showAlert(
                "Updated",
                "Match Updated Successfully"
        );

    }





    @FXML
    public void searchMatchOA(ActionEvent actionEvent) {


        String id = txtMatchId.getText();



        for(ObservableList<String> match : matchList){


            if(match.get(0).equals(id)){


                showAlert(
                        "Found",
                        "Match Found: "
                                + match.get(1)
                                + " vs "
                                + match.get(2)
                );


                return;
            }

        }



        showAlert(
                "Not Found",
                "No Match Found"
        );

    }





    @FXML
    public void cancelMatchOA(ActionEvent actionEvent) {


        ObservableList<String> selected =
                tblMatches.getSelectionModel()
                        .getSelectedItem();



        if(selected == null){

            showAlert(
                    "Error",
                    "Select a match first"
            );

            return;
        }



        selected.set(7,"Cancelled");


        tblMatches.refresh();



        showAlert(
                "Cancelled",
                "Match Cancelled Successfully"
        );

    }





    @FXML
    public void clearOA(ActionEvent actionEvent) {


        txtMatchId.clear();

        txtMatchTime.clear();

        assignedOfficialTF.clear();


        cmbHomeTeam.setValue(null);

        cmbAwayTeam.setValue(null);

        cmbCompetition.setValue(null);

        cmbStadium.setValue(null);

        cmbMatchStatus.setValue("Scheduled");


        dpMatchDate.setValue(null);

    }





    @FXML
    public void backOA(ActionEvent actionEvent) {


        clearOA(actionEvent);


        showAlert(
                "Back",
                "Returning to previous page"
        );

    }





    private void showAlert(String title, String message){


        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);


        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);


        alert.showAndWait();

    }

}