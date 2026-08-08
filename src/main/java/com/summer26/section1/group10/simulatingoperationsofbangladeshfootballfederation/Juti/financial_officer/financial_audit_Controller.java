package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.util.ArrayList;


import static java.util.Collections.addAll;

public class financial_audit_Controller
{
    @javafx.fxml.FXML
    private ComboBox<String> auditYearCB;
    @javafx.fxml.FXML
    private ComboBox<String> auditMonthCB;
    @javafx.fxml.FXML
    private TableView<financial_audit> auditReportTableView;
    @javafx.fxml.FXML
    private TextField typeTF;
    @javafx.fxml.FXML
    private TextField recordIDTF;
    @javafx.fxml.FXML
    private TextField statusTF;
    @javafx.fxml.FXML
    private TextField amountTF;
    @javafx.fxml.FXML
    private TableColumn<financial_audit,String> recordIDColumn;
    @javafx.fxml.FXML
    private TableColumn<financial_audit,String> auditIDColumn;
    @javafx.fxml.FXML
    private TableColumn<financial_audit,String> statusColumn;
    @javafx.fxml.FXML
    private TableColumn<financial_audit,String> amountColumn;
    @javafx.fxml.FXML
    private TableColumn<financial_audit,String> recordTypeColumn;

    @javafx.fxml.FXML
    public void initialize() {
        //CB

        auditYearCB.getItems().addAll("2020 Year","2021 Year","2022 Year","Unlimited");
        auditMonthCB.getItems().addAll("January","February","March","April","May");

        auditIDColumn.setCellValueFactory(new PropertyValueFactory<>("auditID"));
        recordIDColumn.setCellValueFactory(new PropertyValueFactory<>("recordID"));
        recordTypeColumn.setCellValueFactory(new PropertyValueFactory<>("recordType"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));


    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void generateAuditReportButtonOA(ActionEvent actionEvent) {
        String auditID = auditIDColumn.getId();
        String recordType= recordTypeColumn.getId();
        String recordID =recordIDColumn.getId();
        String status = statusColumn.getId();
        String amount = amountColumn.getId();
        if(auditID.isEmpty() ||recordType.isEmpty() ||recordID.isEmpty() ||status.isEmpty() ||amount.isEmpty()){
            System.out.println("Please fill in all fields.");
        }

        }
    }




