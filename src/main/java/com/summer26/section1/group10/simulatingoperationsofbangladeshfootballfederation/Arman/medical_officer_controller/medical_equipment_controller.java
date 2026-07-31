package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class medical_equipment_controller
{
    @javafx.fxml.FXML
    private TableView equipment_tableview;
    @javafx.fxml.FXML
    private TextField equipemrent_name_textfield;
    @javafx.fxml.FXML
    private TableColumn storage_location_column;
    @javafx.fxml.FXML
    private TableColumn equipment_id_coloum;
    @javafx.fxml.FXML
    private TableColumn quantity_available_column;
    @javafx.fxml.FXML
    private TextField search_id_textfield;
    @javafx.fxml.FXML
    private TextField equipment_id_textfield;
    @javafx.fxml.FXML
    private TextField quality_available_textfield;
    @javafx.fxml.FXML
    private TableColumn equipment_name_column;
    @javafx.fxml.FXML
    private TableColumn condition_column;
    @javafx.fxml.FXML
    private Label equipment_details_label;
    @javafx.fxml.FXML
    private TextField stroage_location_textfield;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void search_button_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void condition_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_officer_dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void update_button_on_action(ActionEvent actionEvent) {
    }
}