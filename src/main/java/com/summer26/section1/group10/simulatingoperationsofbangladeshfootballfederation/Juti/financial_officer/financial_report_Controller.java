package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.time.LocalDate;

public class financial_report_Controller {

    @javafx.fxml.FXML
    private TextField reportID_TF;

    @javafx.fxml.FXML
    private ComboBox<String> reportType_CB;

    @javafx.fxml.FXML
    private TextField amount_TF;

    @javafx.fxml.FXML
    private DatePicker date_DP;

    @javafx.fxml.FXML
    private ComboBox<String> status_CB;

    @javafx.fxml.FXML
    private Button saveButton;

    @javafx.fxml.FXML
    private Button backButton;


    @javafx.fxml.FXML
    public void initialize() {

        reportType_CB.getItems().addAll(
                "Income Report",
                "Expense Report",
                "Payment Report",
                "Cash Flow Report"
        );

        status_CB.getItems().addAll(
                "Pending",
                "Approved",
                "Completed"
        );
    }


    @javafx.fxml.FXML
    public void saveButtonOA(ActionEvent actionEvent) {

        String reportID = reportID_TF.getText().trim();
        String reportType = reportType_CB.getValue();
        String amountText = amount_TF.getText().trim();
        LocalDate date = date_DP.getValue();
        String status = status_CB.getValue();


        if (reportID.isEmpty()
                || reportType == null
                || amountText.isEmpty()
                || date == null
                || status == null) {

            showAlert(
                    "Error",
                    "Please fill in all fields."
            );

            return;
        }

        double amount;

        try {

            amount = Double.parseDouble(amountText);

        } catch (NumberFormatException e) {

            showAlert(
                    "Error",
                    "Amount must be a number."
            );

            return;
        }

        if (amount < 0) {

            showAlert(
                    "Error",
                    "Amount cannot be negative."
            );

            return;
        }

        financial_report report =
                new financial_report(
                        reportID,
                        reportType,
                        amount,
                        date.toString(),
                        status
                );


        showAlert(
                "Success",
                "Financial report saved successfully."
        );


        clearFields();
    }


    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {

        // Add your SceneSwitcher code here.
    }


    private void clearFields() {

        reportID_TF.clear();

        reportType_CB.setValue(null);

        amount_TF.clear();

        date_DP.setValue(null);

        status_CB.setValue(null);
    }


    private void showAlert(String title, String message) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}

