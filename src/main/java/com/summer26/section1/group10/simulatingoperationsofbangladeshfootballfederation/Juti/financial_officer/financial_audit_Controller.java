package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class financial_audit_Controller
{
    @javafx.fxml.FXML
    private TableColumn<financial_audit,Double> amountColumnTF;
    @javafx.fxml.FXML
    private TableColumn<financial_audit,String> auditIDColumnTF;
    @javafx.fxml.FXML
    private TableColumn<financial_audit,String> dateColumnTF;
    @javafx.fxml.FXML
    private TableColumn<financial_audit,String> recordIDColumnTF;
    @javafx.fxml.FXML
    private ComboBox<String> auditYearCB;
    @javafx.fxml.FXML
    private ComboBox<String> auditMonthCB;
    @javafx.fxml.FXML
    private TableView<financial_audit> auditReportTableView;
    @javafx.fxml.FXML
    private TableColumn<financial_audit,String> statusColumnTF;
    @javafx.fxml.FXML
    private TableColumn<financial_audit,String> recordTypeColumnTF;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void generateAuditReportButtonOA(ActionEvent actionEvent) {
    }
}