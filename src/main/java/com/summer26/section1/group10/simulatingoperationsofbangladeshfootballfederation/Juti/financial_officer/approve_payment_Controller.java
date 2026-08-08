package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class approve_payment_Controller {

    @javafx.fxml.FXML
    private TextField paymentIdTF;

    @javafx.fxml.FXML
    private TextField paymentTypeTF;

    @javafx.fxml.FXML
    private TextField referenceIdTF;

    @javafx.fxml.FXML
    private TextField amountTF;

    @javafx.fxml.FXML
    private TextField statusTF;



    @javafx.fxml.FXML
    private DatePicker dateDP;


    @javafx.fxml.FXML
    private TableView<approve_payment> paymentTableView;


    @javafx.fxml.FXML
    private TableColumn<approve_payment, Double> amountColumnTF;

    @javafx.fxml.FXML
    private TableColumn<approve_payment, String> paymentIDColumnTF;

    @javafx.fxml.FXML
    private TableColumn<approve_payment, String> statusColumnTF;



    private final ObservableList<approve_payment> paymentList =
            FXCollections.observableArrayList();



    @javafx.fxml.FXML
    public void initialize() {


        amountColumnTF.setCellValueFactory(
                new PropertyValueFactory<>("amount")
        );

        paymentIDColumnTF.setCellValueFactory(
                new PropertyValueFactory<>("paymentId")
        );

        statusColumnTF.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );



        paymentTableView.setItems(paymentList);


        paymentTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {

                    if (newValue != null) {

                        paymentIdTF.setText(
                                newValue.getPaymentId()
                        );

                        paymentTypeTF.setText(
                                newValue.getPaymentType()
                        );

                        referenceIdTF.setText(
                                newValue.getReferenceId()
                        );

                        amountTF.setText(
                                String.valueOf(newValue.getAmount())
                        );

                        dateDP.setValue(
                                newValue.getDate()
                        );

                        statusTF.setText(
                                newValue.getStatus()
                        );
                    }
                });
    }

    @javafx.fxml.FXML
    public void saveButtonOA(ActionEvent actionEvent) {


        if (paymentIdTF.getText().isEmpty()
                || paymentTypeTF.getText().isEmpty()
                || referenceIdTF.getText().isEmpty()
                || amountTF.getText().isEmpty()
                || dateDP.getValue() == null
                || statusTF.getText().isEmpty()) {

            showAlert(
                    "Missing Information",
                    "Please fill in all fields."
            );

            return;
        }


        double amount;

        try {

            amount = Double.parseDouble(
                    amountTF.getText()
            );

        } catch (NumberFormatException e) {

            showAlert(
                    "Invalid Amount",
                    "Please enter a valid amount."
            );

            return;
        }

        approve_payment payment = new approve_payment(
                paymentIdTF.getText(),
                paymentTypeTF.getText(),
                referenceIdTF.getText(),
                amount,
                dateDP.getValue(),
                statusTF.getText()
        );

        paymentList.add(payment);


        // Clear fields

        clearFields();


        showAlert(
                "Success",
                "Payment saved successfully."
        );
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {

        // Add your dashboard scene switching code here
    }

    private void clearFields() {

        paymentIdTF.clear();

        paymentTypeTF.clear();

        referenceIdTF.clear();

        amountTF.clear();

        statusTF.clear();

        dateDP.setValue(null);

        paymentTableView.getSelectionModel()
                .clearSelection();
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