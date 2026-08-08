package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class financial_audit_Controller {

    @javafx.fxml.FXML
    private TableColumn<financial_audit, Double> amountColumnTF;

    @javafx.fxml.FXML
    private TableColumn<financial_audit, String> auditIDColumnTF;

    @javafx.fxml.FXML
    private TableColumn<financial_audit, LocalDate> dateColumnTF;

    @javafx.fxml.FXML
    private TableColumn<financial_audit, String> recordIDColumnTF;

    @javafx.fxml.FXML
    private ComboBox<String> auditYearCB;

    @javafx.fxml.FXML
    private ComboBox<String> auditMonthCB;

    @javafx.fxml.FXML
    private TableView<financial_audit> auditReportTableView;

    @javafx.fxml.FXML
    private TableColumn<financial_audit, String> statusColumnTF;

    @javafx.fxml.FXML
    private TableColumn<financial_audit, String> recordTypeColumnTF;

    private final ObservableList<financial_audit> auditList =
            FXCollections.observableArrayList();


    @javafx.fxml.FXML
    public void initialize() {


        auditYearCB.setItems(
                FXCollections.observableArrayList(
                        "2024",
                        "2025",
                        "2026",
                        "2027",
                        "2028"
                )
        );

        auditMonthCB.setItems(
                FXCollections.observableArrayList(
                        "January",
                        "February",
                        "March",
                        "April",
                        "May",
                        "June",
                        "July",
                        "August",
                        "September",
                        "October",
                        "November",
                        "December"
                )
        );


        auditIDColumnTF.setCellValueFactory(
                new PropertyValueFactory<>("auditId")
        );

        recordTypeColumnTF.setCellValueFactory(
                new PropertyValueFactory<>("recordType")
        );

        recordIDColumnTF.setCellValueFactory(
                new PropertyValueFactory<>("recordId")
        );

        amountColumnTF.setCellValueFactory(
                new PropertyValueFactory<>("amount")
        );

        dateColumnTF.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );

        statusColumnTF.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

        auditReportTableView.setItems(auditList);
    }


    @javafx.fxml.FXML
    public void generateAuditReportButtonOA(ActionEvent actionEvent) {

        if (auditYearCB.getValue() == null ||
                auditMonthCB.getValue() == null) {

            showAlert(
                    "Missing Information",
                    "Please select both year and month."
            );

            return;
        }


        int selectedYear =
                Integer.parseInt(auditYearCB.getValue());

        int selectedMonth =
                auditMonthCB.getSelectionModel()
                        .getSelectedIndex() + 1;


        auditList.clear();


        financial_audit audit1 = new financial_audit(
                "AUD001",
                "Income",
                "INC001",
                50000.00,
                LocalDate.of(selectedYear, selectedMonth, 5),
                "Verified"
        );


        financial_audit audit2 = new financial_audit(
                "AUD002",
                "Expense",
                "EXP001",
                25000.00,
                LocalDate.of(selectedYear, selectedMonth, 12),
                "Approved"
        );


        financial_audit audit3 = new financial_audit(
                "AUD003",
                "Budget",
                "BUD001",
                100000.00,
                LocalDate.of(selectedYear, selectedMonth, 20),
                "Pending"
        );


        auditList.addAll(
                audit1,
                audit2,
                audit3
        );


        auditReportTableView.refresh();


        showAlert(
                "Audit Report",
                "Audit report generated successfully."
        );
    }



    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {

    }


    private void showAlert(
            String title,
            String message) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}