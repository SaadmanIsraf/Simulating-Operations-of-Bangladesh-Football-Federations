package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class approve_payment_Controller {

    @javafx.fxml.FXML
    private TextField requestIDTF;

    @javafx.fxml.FXML
    private TextField payeeNameTF;

    @javafx.fxml.FXML
    private TextArea remarksTA;

    @javafx.fxml.FXML
    private TableView<approve_payment> paymentTV;

    @javafx.fxml.FXML
    private TableColumn<approve_payment, String> requestIDCol;

    @javafx.fxml.FXML
    private TableColumn<approve_payment, String> payeeNameCol;


    private final ObservableList<approve_payment> paymentList =
            FXCollections.observableArrayList();


    @javafx.fxml.FXML
    public void initialize() {

        requestIDCol.setCellValueFactory(
                new PropertyValueFactory<>("requestID"));

        payeeNameCol.setCellValueFactory(
                new PropertyValueFactory<>("payeeName"));

        paymentTV.setItems(paymentList);
    }


    @javafx.fxml.FXML
    public void approveButtonOA(ActionEvent actionEvent) {

        approve_payment selectedPayment =
                paymentTV.getSelectionModel().getSelectedItem();

        if (selectedPayment == null) {
            remarksTA.setText("Please select a payment request.");
            return;
        }

        remarksTA.setText(
                "Payment request " +
                        selectedPayment.getRequestID() +
                        " approved successfully."
        );
    }


    @javafx.fxml.FXML
    public void rejectButtonOA(ActionEvent actionEvent) {

        approve_payment selectedPayment =
                paymentTV.getSelectionModel().getSelectedItem();

        if (selectedPayment == null) {
            remarksTA.setText("Please select a payment request.");
            return;
        }

        remarksTA.setText(
                "Payment request " +
                        selectedPayment.getRequestID() +
                        " rejected."
        );
    }


    @javafx.fxml.FXML
    public void refreshButtonOA(ActionEvent actionEvent) {

        paymentTV.refresh();

        remarksTA.setText(
                "Payment table refreshed."
        );
    }


    @javafx.fxml.FXML
    public void clearButtonOA(ActionEvent actionEvent) {

        requestIDTF.clear();
        payeeNameTF.clear();
        remarksTA.clear();

        paymentTV.getSelectionModel().clearSelection();
    }


    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {

        // Add your SceneSwitcher code here.
    }
}
