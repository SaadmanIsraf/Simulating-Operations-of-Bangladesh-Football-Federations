package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.MatchOfficialManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class matchofficial_registerController {

    @FXML
    private TextField officialIdTF;

    @FXML
    private TextField nameTF;

    @FXML
    private ComboBox<String> roleCB;

    @FXML
    private TextField licenseTF;

    @FXML
    private TextField experienceTF;

    @FXML
    private TableView<MatchOfficial> officialTable;

    @FXML
    private TableColumn<MatchOfficial, String> officialIdCol;

    @FXML
    private TableColumn<MatchOfficial, String> nameCol;

    @FXML
    private TableColumn<MatchOfficial, String> roleCol;

    @FXML
    private TableColumn<MatchOfficial, String> licenseCol;

    @FXML
    private TableColumn<MatchOfficial, String> experienceCol;

    @FXML
    public void initialize() {

        roleCB.getItems().addAll(
                "Referee",
                "Assistant Referee",
                "Fourth Official",
                "VAR Official"
        );

        officialIdCol.setCellValueFactory(
                new PropertyValueFactory<>("officialId"));

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        roleCol.setCellValueFactory(
                new PropertyValueFactory<>("role"));

        licenseCol.setCellValueFactory(
                new PropertyValueFactory<>("licenseNumber"));

        experienceCol.setCellValueFactory(
                new PropertyValueFactory<>("experience"));

        loadMatchOfficials();
    }

    private void loadMatchOfficials() {

        MatchOfficialManager.loadFromFile();

        officialTable.getItems().setAll(
                MatchOfficialManager.getMatchOfficialList());

        officialTable.refresh();
    }

    @FXML
    public void registerButtonOnAction(ActionEvent actionEvent) {

        String officialId = officialIdTF.getText().trim();
        String name = nameTF.getText().trim();
        String role = roleCB.getValue();
        String licenseNumber = licenseTF.getText().trim();
        String experience = experienceTF.getText().trim();

        if (officialId.isEmpty()
                || name.isEmpty()
                || role == null
                || licenseNumber.isEmpty()
                || experience.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Missing Information",
                    "Please fill in all fields."
            );
            return;
        }

        MatchOfficial official = new MatchOfficial(
                officialId,
                name,
                role,
                licenseNumber,
                experience
        );

        MatchOfficialManager.addMatchOfficial(official);
        MatchOfficialManager.saveToFile();

        loadMatchOfficials();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Success",
                "Match Official registered successfully."
        );
    }
    @FXML
    public void clearButtonOnAction(ActionEvent actionEvent) {

        clearFields();
    }

    @FXML
    public void backButtonOnAction(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "turjo/match_official/matchofficial_dashboard.fxml"
        );
    }

    private void clearFields() {

        officialIdTF.clear();
        nameTF.clear();
        licenseTF.clear();
        experienceTF.clear();

        roleCB.setValue(null);

        officialTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType alertType,
                           String title,
                           String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}