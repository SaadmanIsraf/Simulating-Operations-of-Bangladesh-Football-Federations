package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class medical_officer_profile_controller
{

    @javafx.fxml.FXML
    private Label medical_officer_id_label;
    @javafx.fxml.FXML
    private TextField medical_officer_age_textfield;
    @javafx.fxml.FXML
    private Label medical_officer_name_label;
    @javafx.fxml.FXML
    private TextField medical_officer_ID_textfield;
    @javafx.fxml.FXML
    private PasswordField medical_officer_password_passwordfield;
    @javafx.fxml.FXML
    private TextField medical_officer_name_textfield;
    @javafx.fxml.FXML
    private Label medical_officer_age_label;
    @javafx.fxml.FXML
    private Label medical_officer_contact_label;
    @javafx.fxml.FXML
    private Label medial_officer_specialization_label;
    @javafx.fxml.FXML
    private TextField medical_officer_numebr_textfield;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void save_button_on_action(ActionEvent actionEvent) {
    }

    @Deprecated
    public void medical_specilization_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_officer_dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void medical_specialization_conbobox_on_action(ActionEvent actionEvent) {
    }
}