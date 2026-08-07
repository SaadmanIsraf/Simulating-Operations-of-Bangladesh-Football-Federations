package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.ArrayList;

public class record_expense_Controller {

    private final ArrayList<record_expense> expenseList = new ArrayList<>();

    @FXML
    private TableColumn<record_expense, String> recordID_column;
    @FXML
    private TableColumn<record_expense, String> expenseID_column;
    @FXML
    private TableColumn<record_expense, String> expense_source_column;
    @FXML
    private TableColumn<record_expense, String> expense_type_column;
    @FXML
    private TableColumn<record_expense, Double> expense_amount_column;
    @FXML
    private TableColumn<record_expense, LocalDate> expense_date_column;
    @FXML
    private TableColumn<record_expense, String> description_column;

    @FXML
    private TextField recordIDTF;
    @FXML
    private TextField expenseIDTF;
    @FXML
    private TextField expense_sourceTF;
    @FXML
    private TextField expense_typeTF;
    @FXML
    private TextField expense_amountTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private TextField expense__amountTF;
    @FXML
    private TableView<record_expense> expense_tableview;
    @FXML
    private AnchorPane expence_datepicker;
    @FXML
    private DatePicker expense_datepicker;

    @FXML
    public void initialize() {

        recordID_column.setCellValueFactory(new PropertyValueFactory<>("recordID"));
        expenseID_column.setCellValueFactory(new PropertyValueFactory<>("expenseID"));
        expense_source_column.setCellValueFactory(new PropertyValueFactory<>("expense_source"));
        expense_type_column.setCellValueFactory(new PropertyValueFactory<>("expense_type"));
        expense_amount_column.setCellValueFactory(new PropertyValueFactory<>("expense_amount"));
        expense_date_column.setCellValueFactory(new PropertyValueFactory<>("expense_date"));
        description_column.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @FXML
    public void saveButtonOA(ActionEvent actionEvent) {

        if (recordIDTF.getText().isEmpty()
                || expenseIDTF.getText().isEmpty()
                || expense_sourceTF.getText().isEmpty()
                || expense_typeTF.getText().isEmpty()
                || expense_amountTF.getText().isEmpty()
                || expense_datepicker.getValue() == null
                || descriptionTF.getText().isEmpty()) {

        }

        record_expense expense = new record_expense(
                recordIDTF.getText(),
                expenseIDTF.getText(),
                expense_sourceTF.getText(),
                expense_typeTF.getText(),
                Double.parseDouble(expense_amountTF.getText()),
                expense_datepicker.getValue(),
                descriptionTF.getText()
        );

        expenseList.add(expense);

        expense_tableview.getItems().clear();
        expense_tableview.getItems().addAll(expenseList);

    }

    @FXML
    public void clearButtonOA(ActionEvent actionEvent) {

        recordIDTF.clear();
        expenseIDTF.clear();
        expense_sourceTF.clear();
        expense_typeTF.clear();
        expense_amountTF.clear();
        descriptionTF.clear();
        expense_datepicker.setValue(null);


    }

    @FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }
}
