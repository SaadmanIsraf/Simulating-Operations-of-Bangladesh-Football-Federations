package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class player_rehabitation_progress_controller
{
    @javafx.fxml.FXML
    private TableColumn player_id_column;
    @javafx.fxml.FXML
    private TableColumn progress_date_column;
    @javafx.fxml.FXML
    private TableColumn session_status_column;
    @javafx.fxml.FXML
    private TextField player_id_textfield;
    @javafx.fxml.FXML
    private TableView player_rehabitation_tableview;
    @javafx.fxml.FXML
    private TableColumn note_column;
    @javafx.fxml.FXML
    private TableColumn update_fitness_status_column;
    @javafx.fxml.FXML
    private DatePicker progress_date_datepicker;
    @javafx.fxml.FXML
    private TableColumn recovery_percentage_column;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void physiotherapy_sessions_completed_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void save_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void recovery_percentage_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void physical_condition_note_textfield_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void updated_fitness_status_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_officer_dashboard.fxml");
    }
}