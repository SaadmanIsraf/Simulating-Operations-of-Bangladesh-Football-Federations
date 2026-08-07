package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

import java.util.ArrayList;

public class cash_flow_Controller {

    public TableView<cash_flow> cashFlowTableView;

    public TableColumn<cash_flow, String> transactionIDColumn;
    public TableColumn<cash_flow, String> typeColumn;
    public TableColumn<cash_flow, Double> amountColumn;
    public TableColumn<cash_flow, String> purposeColumn;
    public TableColumn<cash_flow, java.time.LocalDate> dateColumn;

    public TextField transactionIDTF;
    public TextField typeTF;
    public TextField amountTF;
    public TextField purposeTF;

    public DatePicker dateDatePicker;

    public Label outputLabel;

    ArrayList<cash_flow> cashFlowList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        transactionIDColumn.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        purposeColumn.setCellValueFactory(new PropertyValueFactory<>("purpose"));

    }

    @javafx.fxml.FXML
    public void saveButtonOnClick(ActionEvent actionEvent) {

        if (transactionIDTF.getText().trim().isEmpty()) {
            outputLabel.setText("Enter Transaction ID!");
            return;
        }

        if (typeTF.getText().trim().isEmpty()) {
            outputLabel.setText("Enter Transaction Type!");
            return;
        }

        if (amountTF.getText().trim().isEmpty()) {
            outputLabel.setText("Enter Amount!");
            return;
        }

        if (purposeTF.getText().trim().isEmpty()) {
            outputLabel.setText("Enter Purpose!");
            return;
        }

        if (dateDatePicker.getValue() == null) {
            outputLabel.setText("Select Date!");
            return;
        }

        cash_flow cf = new cash_flow(
                transactionIDTF.getText(),
                dateDatePicker.getValue(),
                typeTF.getText(),
                Double.parseDouble(amountTF.getText()),
                purposeTF.getText()
        );

        cashFlowList.add(cf);

        cashFlowTableView.getItems().clear();
        cashFlowTableView.getItems().addAll(cashFlowList);

        outputLabel.setText("Cash Flow Added Successfully.");
    }

    @javafx.fxml.FXML
    public void clearButtonOnClick(ActionEvent actionEvent) {

        cashFlowTableView.getItems().clear();
        cashFlowTableView.getItems().addAll(cashFlowList);

        outputLabel.setText("");
    }

    @javafx.fxml.FXML
    public void backButtonOnClick(ActionEvent actionEvent) {

    }
}