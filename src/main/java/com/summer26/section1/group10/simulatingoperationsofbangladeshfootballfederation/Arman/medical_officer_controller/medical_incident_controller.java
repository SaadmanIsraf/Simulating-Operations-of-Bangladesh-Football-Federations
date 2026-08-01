package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class medical_incident_controller
{
    @javafx.fxml.FXML
    private TableColumn incident_type_column;
    @javafx.fxml.FXML
    private TableView medical_incident_tableview;
    @javafx.fxml.FXML
    private TableColumn player_id_column;
    @javafx.fxml.FXML
    private TextField player_id_textfield;
    @javafx.fxml.FXML
    private TableColumn action_taken_column;
    @javafx.fxml.FXML
    private TableColumn severity_column;
    @javafx.fxml.FXML
    private DatePicker match_date_datepicker;
    @javafx.fxml.FXML
    private TableColumn match_date_column;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void severity_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void incident_type_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void submit_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_officer_dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void action_taken_on_action(ActionEvent actionEvent) {
    }
}