package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class injury_report_and_fitness_status_controller
{
    @javafx.fxml.FXML
    private TableColumn player_id_column;
    @javafx.fxml.FXML
    private TableColumn affected_body_part_column;
    @javafx.fxml.FXML
    private TableColumn initial_fitness_status_column;
    @javafx.fxml.FXML
    private Label player_details_label;
    @javafx.fxml.FXML
    private TextField additional_notes_textfield;
    @javafx.fxml.FXML
    private TableView Injurt_report_tableview;
    @javafx.fxml.FXML
    private TableColumn severity_column;
    @javafx.fxml.FXML
    private DatePicker injury_date_datepicker;
    @javafx.fxml.FXML
    private TextField search_by_player_Id_textfield;
    @javafx.fxml.FXML
    private TextField player_id_text_field;
    @javafx.fxml.FXML
    private TableColumn injury_type_column;
    @javafx.fxml.FXML
    private TableColumn injury_date_column;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void Severity_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void save_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void injury_type_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void initial_fitness_status_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_officer_dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void search_by_player_Id_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void affected_body_part_combobox_on_action(ActionEvent actionEvent) {
    }
}