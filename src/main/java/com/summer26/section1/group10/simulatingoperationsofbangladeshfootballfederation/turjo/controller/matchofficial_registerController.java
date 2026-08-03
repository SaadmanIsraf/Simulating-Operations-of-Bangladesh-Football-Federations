package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.AlertGenerator;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.DatabaseAccessor;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchOfficials;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.Serializable;
import java.util.ArrayList;

public class matchofficial_registerController  {

    @FXML
    private TableColumn<MatchOfficials, Integer> licenseCol;
    @FXML
    private TextField nameTF;
    @FXML
    private ComboBox<String> roleCB;
    @FXML
    private TextField licenseTF;
    @FXML
    private TableColumn<MatchOfficials, String> experienceCol;
    @FXML
    private TableColumn<MatchOfficials, String> nameCol;
    @FXML
    private TableColumn<MatchOfficials, String> roleCol;
    @FXML
    private ComboBox<String> experienceCB;
    @FXML
    private TableView<MatchOfficials> officialsTable;
    @FXML
    private TextField newPasswordTF;
    @FXML
    private TextField ConfirmPasswordTF;
    @FXML
    private Label messageLabel;
    @FXML
    private TextField newEmailTF;

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<MatchOfficials, String>("name"));
        licenseCol.setCellValueFactory(new PropertyValueFactory<MatchOfficials,Integer>("licenseNumber"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("matchOfficeRole"));
        experienceCol.setCellValueFactory(new PropertyValueFactory<>("exprerienceLevel"));

        ArrayList<Object> userList = BinaryFileUtility.readObjects("User.bin");
        for (Object user : userList) {
            if(user instanceof MatchOfficials matchOfficials) {
                if(matchOfficials.getRole().equals("Match Officials")) {
                    officialsTable.getItems().add(matchOfficials);
                }
            }
        }


        roleCB.getItems().addAll(
                "Referee",
                "Assistant Referee",
                "Fourth Official",
                "VAR Official"
        );

        experienceCB.getItems().addAll(
                "1-2 Years",
                "3-5 Years",
                "6-10 Years",
                "10+ Years"
        );
    }

    private boolean validateInput() {

        if (nameTF.getText().trim().isEmpty()) {
            messageLabel.setText("Name cannot be empty.");
            nameTF.requestFocus();
            return false;
        }

        if (!nameTF.getText().matches("[A-Za-z ]+")) {
            messageLabel.setText("Name can contain only letters.");
            nameTF.requestFocus();
            return false;
        }

        if (roleCB.getValue() == null) {
            messageLabel.setText("Select a role.");
            roleCB.requestFocus();
            return false;
        }

        if (licenseTF.getText().trim().isEmpty()) {
            messageLabel.setText("License number cannot be empty.");
            licenseTF.requestFocus();
            return false;
        }

        if (!licenseTF.getText().matches("[A-Za-z0-9-]+")) {
            messageLabel.setText("Invalid license number.");
            licenseTF.requestFocus();
            return false;
        }

        if (experienceCB.getValue() == null) {
            messageLabel.setText("Select experience.");
            experienceCB.requestFocus();
            return false;
        }

        messageLabel.setText("");
        return true;
    }

    @FXML
    public void registerOA(ActionEvent actionEvent) {

        if (!validateInput()) {
            return;
        }
        if(!newPasswordTF.getText().equals(ConfirmPasswordTF.getText())) {
            AlertGenerator.showAlert(Alert.AlertType.ERROR, "Passwords do not match", "Passwords do not match");
            return;
        }

        int newid = DatabaseAccessor.generateNewUniqueId("User.bin", "id");
//        int id, String name, String email, String password, String role,
//        int licenseNumber, String exprerienceLevel, String matchOfficeRole
        MatchOfficials matchOfficials = new MatchOfficials(newid, nameTF.getText(), newEmailTF.getText(), newPasswordTF.getText(),
                "Match Officials", Integer.parseInt(licenseTF.getText()), experienceCB.getValue(), roleCB.getValue());

        BinaryFileUtility.writeObjects("User.bin", matchOfficials);
        officialsTable.getItems().add(matchOfficials);
        messageLabel.setText("Official registered successfully.");
    }

    @FXML
    public void clearOA(ActionEvent actionEvent) {

        nameTF.clear();
        licenseTF.clear();

        roleCB.getSelectionModel().clearSelection();
        experienceCB.getSelectionModel().clearSelection();

        messageLabel.setText("");
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {

        SceneSwitcher.switchTo("turjo/match_officials/matchofficialsdashboard.fxml");
    }
}