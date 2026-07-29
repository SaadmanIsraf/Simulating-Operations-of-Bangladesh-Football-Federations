package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class federation_administrator_manageteamrankingController {


    @FXML
    private TableColumn<ObservableList<String>, String> rankingtableviewlossescol;

    @FXML
    private TableView<ObservableList<String>> rankingtableview;

    @FXML
    private TextField teamnameTF;

    @FXML
    private TextField winsTF;

    @FXML
    private TableColumn<ObservableList<String>, String> rankingtableviewteamnamecol;

    @FXML
    private TextField rankTF;

    @FXML
    private TextField drawTF;

    @FXML
    private TableColumn<ObservableList<String>, String> rankingtableviewpointscol;

    @FXML
    private TextField lossesTF;

    @FXML
    private TableColumn<ObservableList<String>, String> rankingtableviewwinscol;

    @FXML
    private TableColumn<ObservableList<String>, String> rankingtableviewdrawcol;

    @FXML
    private TextField poitsTF;

    @FXML
    private TableColumn<ObservableList<String>, String> rankingtableviewrankcol;



    private ObservableList<ObservableList<String>> rankingList =
            FXCollections.observableArrayList();



    @FXML
    public void initialize() {


        rankingtableviewrankcol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(0)
                ));


        rankingtableviewteamnamecol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(1)
                ));


        rankingtableviewwinscol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(2)
                ));


        rankingtableviewdrawcol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(3)
                ));


        rankingtableviewlossescol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(4)
                ));


        rankingtableviewpointscol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(5)
                ));


        rankingtableview.setItems(rankingList);

    }




    @FXML
    public void addteamrankOA(ActionEvent actionEvent) {


        if(rankTF.getText().isEmpty()
                || teamnameTF.getText().isEmpty()
                || winsTF.getText().isEmpty()
                || drawTF.getText().isEmpty()
                || lossesTF.getText().isEmpty()
                || poitsTF.getText().isEmpty()){


            showAlert(
                    "Error",
                    "Please fill all team ranking information"
            );

            return;
        }



        ObservableList<String> team =
                FXCollections.observableArrayList();



        team.add(rankTF.getText());

        team.add(teamnameTF.getText());

        team.add(winsTF.getText());

        team.add(drawTF.getText());

        team.add(lossesTF.getText());

        team.add(poitsTF.getText());



        rankingList.add(team);


        rankingtableview.refresh();



        showAlert(
                "Success",
                "Team Ranking Added Successfully"
        );

    }





    @FXML
    public void updaterankOA(ActionEvent actionEvent) {


        ObservableList<String> selected =
                rankingtableview.getSelectionModel()
                        .getSelectedItem();



        if(selected == null){


            showAlert(
                    "Error",
                    "Select a team first"
            );

            return;
        }



        selected.set(0, rankTF.getText());

        selected.set(1, teamnameTF.getText());

        selected.set(2, winsTF.getText());

        selected.set(3, drawTF.getText());

        selected.set(4, lossesTF.getText());

        selected.set(5, poitsTF.getText());



        rankingtableview.refresh();



        showAlert(
                "Updated",
                "Team Ranking Updated Successfully"
        );

    }





    @FXML
    public void deleteteamOA(ActionEvent actionEvent) {


        ObservableList<String> selected =
                rankingtableview.getSelectionModel()
                        .getSelectedItem();



        if(selected == null){


            showAlert(
                    "Error",
                    "Select a team first"
            );

            return;
        }



        rankingList.remove(selected);



        showAlert(
                "Deleted",
                "Team Removed Successfully"
        );

    }





    @FXML
    public void refreshOA(ActionEvent actionEvent) {


        rankingtableview.refresh();



        showAlert(
                "Refresh",
                "Ranking Table Refreshed"
        );

    }





    @FXML
    public void saveOA(ActionEvent actionEvent) {


        showAlert(
                "Saved",
                "Team Ranking Saved Successfully"
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