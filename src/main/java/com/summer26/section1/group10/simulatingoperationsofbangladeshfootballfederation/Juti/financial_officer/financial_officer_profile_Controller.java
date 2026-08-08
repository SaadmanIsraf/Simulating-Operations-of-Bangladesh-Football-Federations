package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class financial_officer_profile_Controller {

    @FXML
    private TextField officerIDTF;

    @FXML
    private TextField newPasswordTF;

    @FXML
    private TextField fullNameTF;

    @FXML
    private TextField contactNumberTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField currentPasswordTF;

    @FXML
    private TextField positionTF;

    @FXML
    private TextField departmentTF;



    @FXML
    public void initialize() {

        officerIDTF.setText("4444");
        fullNameTF.setText("Juti");
        emailTF.setText("4444@gmail.com");
        contactNumberTF.setText("01642152124");
        departmentTF.setText("CSE");
        positionTF.setText("Junior Officer");
        currentPasswordTF.setText("@Juti700");

        newPasswordTF.setPromptText("Leave Empty if Unchanged");
    }


    @FXML
    public void backButtonOA(ActionEvent actionEvent) {

        SceneSwitcher.switchTo("financial_officer_dashboard.fxml");
    }

    @FXML
    public void refreshButtonOA(ActionEvent actionEvent) {

        officerIDTF.setText("4444");
        fullNameTF.setText("Juti");
        emailTF.setText("4444@gmail.com");
        contactNumberTF.setText("01642152124");
        departmentTF.setText("CSE");
        positionTF.setText("Junior Officer");
        currentPasswordTF.setText("@Juti700");

        newPasswordTF.clear();
        newPasswordTF.setPromptText("Leave Empty if Unchanged");
    }

    @FXML
    public void saveButtonOA(ActionEvent actionEvent) {

        System.out.println("SAVE BUTTON CLICKED");

        System.out.println("Officer ID: " + officerIDTF.getText());
        System.out.println("Full Name: " + fullNameTF.getText());
        System.out.println("Email: " + emailTF.getText());
        System.out.println("Contact Number: " + contactNumberTF.getText());
        System.out.println("Department: " + departmentTF.getText());
        System.out.println("Position: " + positionTF.getText());
        System.out.println("Current Password: " + currentPasswordTF.getText());

        if (!newPasswordTF.getText().isEmpty()) {
            System.out.println("New Password Updated");
        }

        System.out.println("Profile Saved Successfully");
    }
}