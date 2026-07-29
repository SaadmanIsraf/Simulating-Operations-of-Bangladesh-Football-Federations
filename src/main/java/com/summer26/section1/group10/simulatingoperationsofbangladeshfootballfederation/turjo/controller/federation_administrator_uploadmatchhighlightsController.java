package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class federation_administrator_uploadmatchhighlightsController {


    @FXML
    private TextField txtHighlightTitle;

    @FXML
    private ComboBox<String> cmbPublishStatus;

    @FXML
    private TextArea txtKeyMoments;

    @FXML
    private TextArea txtDescription;

    @FXML
    private ComboBox<String> cmbCompetition;

    @FXML
    private TextField txtDuration;

    @FXML
    private ComboBox<String> cmbMatch;

    @FXML
    private ComboBox<String> cmbHighlightType;

    @FXML
    private TextField txtVideoLink;



    @FXML
    public void initialize() {


        cmbPublishStatus.setItems(
                FXCollections.observableArrayList(
                        "Published",
                        "Pending",
                        "Hidden"
                )
        );


        cmbCompetition.setItems(
                FXCollections.observableArrayList(
                        "Premier League",
                        "Federation Cup",
                        "National Championship"
                )
        );


        cmbMatch.setItems(
                FXCollections.observableArrayList(
                        "Abahani Limited vs Bashundhara Kings",
                        "Mohammedan SC vs Sheikh Russel",
                        "Abahani Limited vs Mohammedan SC"
                )
        );


        cmbHighlightType.setItems(
                FXCollections.observableArrayList(
                        "Goal Highlights",
                        "Full Match Highlights",
                        "Best Moments",
                        "Player Highlights"
                )
        );


        cmbPublishStatus.setValue("Pending");

    }





    @FXML
    public void uploadHighlightOA(ActionEvent actionEvent) {


        if(txtHighlightTitle.getText().isEmpty()
                || txtDescription.getText().isEmpty()
                || txtVideoLink.getText().isEmpty()
                || cmbMatch.getValue() == null){


            showAlert(
                    "Error",
                    "Please fill all highlight information"
            );

            return;
        }



        String highlight =
                "Title: "
                        + txtHighlightTitle.getText()
                        + "\nMatch: "
                        + cmbMatch.getValue()
                        + "\nCompetition: "
                        + cmbCompetition.getValue()
                        + "\nType: "
                        + cmbHighlightType.getValue()
                        + "\nDuration: "
                        + txtDuration.getText()
                        + "\nStatus: "
                        + cmbPublishStatus.getValue();



        showAlert(
                "Uploaded",
                "Match highlight uploaded successfully\n\n"
                        + highlight
        );

    }





    @FXML
    public void updateHighlightOA(ActionEvent actionEvent) {


        if(txtHighlightTitle.getText().isEmpty()){


            showAlert(
                    "Error",
                    "Enter highlight title first"
            );

            return;
        }



        showAlert(
                "Updated",
                "Highlight updated successfully"
        );

    }





    @FXML
    public void deleteHighlightOA(ActionEvent actionEvent) {


        if(txtHighlightTitle.getText().isEmpty()){


            showAlert(
                    "Error",
                    "Enter highlight title first"
            );

            return;
        }



        showAlert(
                "Deleted",
                "Highlight deleted successfully"
        );

    }





    @FXML
    public void backOA(ActionEvent actionEvent) {


        clearFields();


        showAlert(
                "Back",
                "Returning to previous page"
        );

    }





    private void clearFields(){


        txtHighlightTitle.clear();

        txtKeyMoments.clear();

        txtDescription.clear();

        txtDuration.clear();

        txtVideoLink.clear();


        cmbPublishStatus.setValue("Pending");

        cmbCompetition.setValue(null);

        cmbMatch.setValue(null);

        cmbHighlightType.setValue(null);

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