package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class federation_administrator_publishannouncementController {


    @FXML
    private TextField txtPlace;

    @FXML
    private TextField txtMatchTime;

    @FXML
    private ComboBox<String> cmbCompetition;

    @FXML
    private TextField txtAnnouncementTitle;

    @FXML
    private TextArea txtAnnouncementDetails;

    @FXML
    private ComboBox<String> cmbMatch;

    @FXML
    private DatePicker dpMatchDate;

    @FXML
    private ComboBox<String> cmbMatchType;

    @FXML
    private TextField txtSponsor;

    @FXML
    private TextField txtVenue;



    @FXML
    public void initialize() {


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
                        "Abahani Limited vs Mohammedan SC" ,
                                "Real Madrid VS Bayern Munchen"
                )
        );


        cmbMatchType.setItems(
                FXCollections.observableArrayList(
                        "League Match",
                        "Cup Match",
                        "Friendly Match",
                        "Final Match"
                )
        );

    }




    @FXML
    public void publishAnnouncementOA(ActionEvent actionEvent) {


        if(txtAnnouncementTitle.getText().isEmpty()
                || txtAnnouncementDetails.getText().isEmpty()
                || cmbCompetition.getValue() == null
                || dpMatchDate.getValue() == null){


            showAlert(
                    "Error",
                    "Please fill all announcement information"
            );

            return;
        }



        String announcement =
                "Title: "
                        + txtAnnouncementTitle.getText()
                        + "\nCompetition: "
                        + cmbCompetition.getValue()
                        + "\nMatch: "
                        + cmbMatch.getValue()
                        + "\nDate: "
                        + dpMatchDate.getValue()
                        + "\nVenue: "
                        + txtVenue.getText()
                        + "\nDetails: "
                        + txtAnnouncementDetails.getText();



        showAlert(
                "Published",
                announcement
        );

    }





    @FXML
    public void updateAnnouncementOA(ActionEvent actionEvent) {


        if(txtAnnouncementTitle.getText().isEmpty()){


            showAlert(
                    "Error",
                    "Enter announcement title first"
            );

            return;
        }



        showAlert(
                "Updated",
                "Announcement updated successfully"
        );

    }





    @FXML
    public void clearOA(ActionEvent actionEvent) {


        txtPlace.clear();

        txtMatchTime.clear();

        txtAnnouncementTitle.clear();

        txtAnnouncementDetails.clear();

        txtSponsor.clear();

        txtVenue.clear();


        cmbCompetition.setValue(null);

        cmbMatch.setValue(null);

        cmbMatchType.setValue(null);


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