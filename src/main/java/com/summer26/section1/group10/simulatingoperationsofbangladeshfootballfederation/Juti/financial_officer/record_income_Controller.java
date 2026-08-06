package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;

public class record_income_Controller
{
    @javafx.fxml.FXML
    private TextField record_income_amountTF;
    @javafx.fxml.FXML
    private TableView<record_income> record_income_tableview;
    @javafx.fxml.FXML
    private TableColumn<record_income,String> record_income_source_type_column;
    @javafx.fxml.FXML
    private TableColumn<record_income,String> incomeID_column;
    @javafx.fxml.FXML
    private TableColumn<record_income,String> record_income_date_column;
    @javafx.fxml.FXML
    private DatePicker record_income_date_datepicker;
    @javafx.fxml.FXML
    private Label output_lable;
    @javafx.fxml.FXML
    private TableColumn<record_income,String> record_income_source_column;
    @javafx.fxml.FXML
    private TableColumn<record_income,String> record_description_column;
    @javafx.fxml.FXML
    private TextField incomeIDTF;
    @javafx.fxml.FXML
    private TextField recordIDTF;
    @javafx.fxml.FXML
    private TextField record_income_sourceTF;
    @javafx.fxml.FXML
    private TableColumn<record_income,String> record_income_amount_column;
    @javafx.fxml.FXML
    private TextField record_descriptionTF;
    @javafx.fxml.FXML
    private TableColumn recordID_column;
    @javafx.fxml.FXML
    private TextField record_income_typeTF;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void clearButtonOA(Event event) {
    }

    @javafx.fxml.FXML
    public void saveButtonOA(ActionEvent actionEvent) {
    }
}