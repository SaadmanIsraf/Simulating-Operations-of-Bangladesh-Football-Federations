package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class federation_administrator_manageuseraccountController {


    @FXML
    private TextField usernameTF;

    @FXML
    private ComboBox<String> roleCB;

    @FXML
    private ComboBox<String> permissionCB;

    @FXML
    private ComboBox<String> statusCB;



    @FXML
    public void initialize() {


        roleCB.getItems().addAll(
                "Administrator",
                "Coach",
                "Player",
                "Official",
                "Club Manager"
        );


        permissionCB.getItems().addAll(
                "Full Access",
                "Edit Access",
                "View Only"
        );


        statusCB.getItems().addAll(
                "Active",
                "Blocked",
                "Pending"
        );


        statusCB.setValue("Active");

    }



    @FXML
    public void activateUserOA(ActionEvent actionEvent) {


        if(usernameTF.getText().isEmpty()){


            showAlert(
                    "Error",
                    "Enter username first"
            );

            return;
        }



        statusCB.setValue("Active");



        showAlert(
                "Activated",
                "User activated successfully"
        );

    }




    @FXML
    public void blockUserOA(ActionEvent actionEvent) {


        if(usernameTF.getText().isEmpty()){


            showAlert(
                    "Error",
                    "Enter username first"
            );

            return;
        }



        statusCB.setValue("Blocked");



        showAlert(
                "Blocked",
                "User blocked successfully"
        );

    }





    @FXML
    public void updateUserOA(ActionEvent actionEvent) {


        if(usernameTF.getText().isEmpty()
                || roleCB.getValue() == null
                || permissionCB.getValue() == null
                || statusCB.getValue() == null){


            showAlert(
                    "Error",
                    "Please fill all user information"
            );

            return;
        }



        showAlert(
                "Updated",
                "User account updated successfully"
        );

    }





    @FXML
    public void backOA(ActionEvent actionEvent) {


        usernameTF.clear();

        roleCB.setValue(null);

        permissionCB.setValue(null);

        statusCB.setValue("Active");



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