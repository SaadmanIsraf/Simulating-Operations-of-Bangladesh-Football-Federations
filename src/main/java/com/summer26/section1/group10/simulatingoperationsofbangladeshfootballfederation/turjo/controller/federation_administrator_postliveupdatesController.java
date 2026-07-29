package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class federation_administrator_postliveupdatesController {


    @FXML
    private ComboBox<String> cmbMatchStatus;

    @FXML
    private TextField txtCardPlayer;

    @FXML
    private TextField txtMinute;

    @FXML
    private TextArea txtCommentary;

    @FXML
    private ComboBox<String> cmbCardType;

    @FXML
    private TextField txtSubstitution;

    @FXML
    private TextField txtAwayScore;

    @FXML
    private ComboBox<String> cmbMatch;

    @FXML
    private TextField txtAssist;

    @FXML
    private TextField txtHomeScore;

    @FXML
    private TextField txtGoalScorer;



    @FXML
    public void initialize() {


        cmbMatch.setItems(
                javafx.collections.FXCollections.observableArrayList(
                        "Abahani Limited vs Bashundhara Kings",
                        "Mohammedan SC vs Sheikh Russel",
                        "Abahani Limited vs Mohammedan SC"
                )
        );


        cmbMatchStatus.setItems(
                javafx.collections.FXCollections.observableArrayList(
                        "Scheduled",
                        "First Half",
                        "Half Time",
                        "Second Half",
                        "Finished"
                )
        );


        cmbCardType.setItems(
                javafx.collections.FXCollections.observableArrayList(
                        "Yellow Card",
                        "Red Card"
                )
        );


        cmbMatchStatus.setValue("Scheduled");

    }





    @FXML
    public void updateScoreOA(ActionEvent actionEvent) {


        if(txtHomeScore.getText().isEmpty()
                || txtAwayScore.getText().isEmpty()){


            showAlert(
                    "Error",
                    "Enter both home and away scores"
            );

            return;
        }



        showAlert(
                "Score Updated",
                "Current Score: "
                        + txtHomeScore.getText()
                        + " - "
                        + txtAwayScore.getText()
        );

    }





    @FXML
    public void postUpdateOA(ActionEvent actionEvent) {


        if(cmbMatch.getValue() == null
                || txtCommentary.getText().isEmpty()){


            showAlert(
                    "Error",
                    "Select match and write commentary"
            );

            return;
        }



        String update = "Match: "
                + cmbMatch.getValue()
                + "\nStatus: "
                + cmbMatchStatus.getValue()
                + "\nMinute: "
                + txtMinute.getText()
                + "\nCommentary: "
                + txtCommentary.getText();



        showAlert(
                "Live Update Posted",
                update
        );

    }





    @FXML
    public void clearOA(ActionEvent actionEvent) {


        txtCardPlayer.clear();

        txtMinute.clear();

        txtCommentary.clear();

        txtSubstitution.clear();

        txtAwayScore.clear();

        txtAssist.clear();

        txtHomeScore.clear();

        txtGoalScorer.clear();


        cmbMatch.setValue(null);

        cmbCardType.setValue(null);

        cmbMatchStatus.setValue("Scheduled");

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