package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class federation_administrator_manageplayersController {


    @FXML
    private TextField clubTF;

    @FXML
    private TableView<ObservableList<String>> playertableview;

    @FXML
    private TextField playernameTF;

    @FXML
    private TableColumn<ObservableList<String>, String> injurycol;

    @FXML
    private ComboBox<String> injuryCB;

    @FXML
    private ComboBox<String> positionCB;

    @FXML
    private TableColumn<ObservableList<String>, String> positioncol;

    @FXML
    private TableColumn<ObservableList<String>, String> clubcol;

    @FXML
    private TableColumn<ObservableList<String>, String> valuecol;

    @FXML
    private TextField valueTF;

    @FXML
    private TableColumn<ObservableList<String>, String> playernamecol;


    private ObservableList<ObservableList<String>> playerList =
            FXCollections.observableArrayList();



    @FXML
    public void initialize() {


        positionCB.setItems(FXCollections.observableArrayList(
                "Goalkeeper",
                "Defender",
                "Midfielder",
                "Forward"
        ));


        injuryCB.setItems(FXCollections.observableArrayList(
                "Fit",
                "Injured",
                "Recovering"
        ));


        playernamecol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(0)
                ));


        clubcol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(1)
                ));


        positioncol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(2)
                ));


        injurycol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(3)
                ));


        valuecol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().get(4)
                ));


        playertableview.setItems(playerList);

    }



    @FXML
    public void addplayerOA(ActionEvent actionEvent) {


        if(playernameTF.getText().isEmpty()
                || clubTF.getText().isEmpty()
                || positionCB.getValue() == null
                || injuryCB.getValue() == null
                || valueTF.getText().isEmpty()){


            showAlert(
                    "Error",
                    "Please fill all player information"
            );

            return;
        }



        ObservableList<String> player =
                FXCollections.observableArrayList();


        player.add(playernameTF.getText());

        player.add(clubTF.getText());

        player.add(positionCB.getValue());

        player.add(injuryCB.getValue());

        player.add(valueTF.getText());


        playerList.add(player);


        playertableview.refresh();



        showAlert(
                "Success",
                "Player Added Successfully"
        );

    }





    @FXML
    public void saveOA(ActionEvent actionEvent) {


        showAlert(
                "Saved",
                "Player Information Saved Successfully"
        );

    }





    @FXML
    public void refreshOA(ActionEvent actionEvent) {


        playertableview.refresh();


        showAlert(
                "Refresh",
                "Player Table Refreshed"
        );

    }





    @FXML
    public void deleteplayerOA(ActionEvent actionEvent) {


        ObservableList<String> selected =
                playertableview.getSelectionModel()
                        .getSelectedItem();



        if(selected == null){


            showAlert(
                    "Error",
                    "Select a player first"
            );

            return;
        }



        playerList.remove(selected);


        showAlert(
                "Deleted",
                "Player Deleted Successfully"
        );

    }





    @FXML
    public void updateplayerOA(ActionEvent actionEvent) {


        ObservableList<String> selected =
                playertableview.getSelectionModel()
                        .getSelectedItem();



        if(selected == null){


            showAlert(
                    "Error",
                    "Select a player first"
            );

            return;
        }



        selected.set(0, playernameTF.getText());

        selected.set(1, clubTF.getText());

        selected.set(2, positionCB.getValue());

        selected.set(3, injuryCB.getValue());

        selected.set(4, valueTF.getText());


        playertableview.refresh();



        showAlert(
                "Updated",
                "Player Updated Successfully"
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