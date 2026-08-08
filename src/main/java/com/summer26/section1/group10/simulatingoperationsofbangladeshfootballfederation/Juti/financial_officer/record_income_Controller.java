package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;

import java.time.LocalDate;

public class record_income_Controller {

    @javafx.fxml.FXML
    private TextField record_income_amountTF;

    @javafx.fxml.FXML
    private TableView<record_income> record_income_tableview;

    @javafx.fxml.FXML
    private TableColumn<record_income, String> record_income_source_type_column;

    @javafx.fxml.FXML
    private TableColumn<record_income, String> incomeID_column;

    @javafx.fxml.FXML
    private TableColumn<record_income, LocalDate> record_income_date_column;

    @javafx.fxml.FXML
    private DatePicker record_income_date_datepicker;

    @javafx.fxml.FXML
    private Label output_lable;

    @javafx.fxml.FXML
    private TableColumn<record_income, String> record_income_source_column;

    @javafx.fxml.FXML
    private TableColumn<record_income, String> record_description_column;

    @javafx.fxml.FXML
    private TextField incomeIDTF;

    @javafx.fxml.FXML
    private TextField recordIDTF;

    @javafx.fxml.FXML
    private TextField record_income_sourceTF;

    @javafx.fxml.FXML
    private TableColumn<record_income, Double> record_income_amount_column;

    @javafx.fxml.FXML
    private TextField record_descriptionTF;

    @javafx.fxml.FXML
    private TableColumn<record_income, String> recordID_column;

    @javafx.fxml.FXML
    private TextField record_income_typeTF;


    private final ObservableList<record_income> incomeList =
            FXCollections.observableArrayList();


    @javafx.fxml.FXML
    public void initialize() {

        recordID_column.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("recordID"));

        incomeID_column.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("incomeID"));

        record_income_source_column.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("record_income_source"));

        record_income_source_type_column.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("record_income_source_type"));

        record_income_amount_column.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("record_income_amount"));

        record_income_date_column.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("record_income_date"));

        record_description_column.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("record_description"));

        record_income_tableview.setItems(incomeList);
    }


    @javafx.fxml.FXML
    public void saveButtonOA(ActionEvent actionEvent) {

        String recordID = recordIDTF.getText().trim();
        String incomeID = incomeIDTF.getText().trim();
        String source = record_income_sourceTF.getText().trim();
        String sourceType = record_income_typeTF.getText().trim();
        String amountText = record_income_amountTF.getText().trim();
        LocalDate date = record_income_date_datepicker.getValue();
        String description = record_descriptionTF.getText().trim();


        if (recordID.isEmpty() ||
                incomeID.isEmpty() ||
                source.isEmpty() ||
                sourceType.isEmpty() ||
                amountText.isEmpty() ||
                date == null ||
                description.isEmpty()) {

            output_lable.setText("Please fill in all fields.");
            return;
        }


        double amount;

        try {
            amount = Double.parseDouble(amountText);

            if (amount < 0) {
                output_lable.setText("Amount cannot be negative.");
                return;
            }

        } catch (NumberFormatException e) {

            output_lable.setText("Please enter a valid amount.");
            return;
        }


        record_income newRecord = new record_income(
                recordID,
                incomeID,
                source,
                sourceType,
                amount,
                date,
                description
        );


        incomeList.add(newRecord);

        output_lable.setText("Income record saved successfully.");

        clearFields();
    }


    @javafx.fxml.FXML
    public void clearButtonOA(Event event) {

        clearFields();

        output_lable.setText("Fields cleared.");
    }


    private void clearFields() {

        recordIDTF.clear();
        incomeIDTF.clear();
        record_income_sourceTF.clear();
        record_income_typeTF.clear();
        record_income_amountTF.clear();
        record_income_date_datepicker.setValue(null);
        record_descriptionTF.clear();
    }


    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {

        output_lable.setText("Back button clicked.");
    }
}

