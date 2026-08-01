package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class treatment_plan_controller
{
    @javafx.fxml.FXML
    private TableColumn player_id_column;
    @javafx.fxml.FXML
    private TableColumn rest_duration_column;
    @javafx.fxml.FXML
    private TextField medical_officer_id_textfield;
    @javafx.fxml.FXML
    private TextField player_id_textfield;
    @javafx.fxml.FXML
    private TableColumn session_required_column;
    @javafx.fxml.FXML
    private DatePicker follow_up_datepicker;
    @javafx.fxml.FXML
    private TextField rest_duration_textfield;
    @javafx.fxml.FXML
    private TableColumn prescribed_medicine_column;
    @javafx.fxml.FXML
    private TextField prescribed_medicine_textfield;
    @javafx.fxml.FXML
    private TableColumn medical_officer_id_column;
    @javafx.fxml.FXML
    private TableView Treatmentplan_tableview;
    @javafx.fxml.FXML
    private TextField dietary_ins_textfield;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void save_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void session_required_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_officer_dashboard.fxml");
    }
}