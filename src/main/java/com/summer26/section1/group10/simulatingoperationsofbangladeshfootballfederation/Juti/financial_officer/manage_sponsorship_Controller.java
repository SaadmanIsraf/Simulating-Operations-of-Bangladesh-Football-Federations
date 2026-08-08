package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class manage_sponsorship_Controller {

    @javafx.fxml.FXML
    private TextField sponsorIdTF;

    @javafx.fxml.FXML
    private TextField sponsorNameTF;

    @javafx.fxml.FXML
    private ComboBox<String> sponsorshipTypeCB;

    @javafx.fxml.FXML
    private TextField amountTF;

    @javafx.fxml.FXML
    private TextField statusTF;

    @javafx.fxml.FXML
    private TextField sponsorID;

    @javafx.fxml.FXML
    private DatePicker startDatedatepicker;

    @javafx.fxml.FXML
    private DatePicker endDatedatepicker;

    @javafx.fxml.FXML
    private TableView<manage_sponsorship> sponsorTV;

    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship, String> sponsorId_column;

    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship, String> sponsorNameCol;

    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship, String> sponsorshipTypeCol;

    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship, Double> amountCol;

    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship, String> statusCol;

    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship, LocalDate> startDateCol;

    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship, LocalDate> endDateCol;

    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship, String> sponsorIDCol;

    @javafx.fxml.FXML
    private TextArea sponsorshipStatusTA;


    private final ObservableList<manage_sponsorship> sponsorshipList =
            FXCollections.observableArrayList();


    @javafx.fxml.FXML
    public void initialize() {

        sponsorId_column.setCellValueFactory(
                new PropertyValueFactory<>("sponsorId"));

        sponsorNameCol.setCellValueFactory(
                new PropertyValueFactory<>("sponsorName"));

        sponsorshipTypeCol.setCellValueFactory(
                new PropertyValueFactory<>("sponsorshipType"));

        amountCol.setCellValueFactory(
                new PropertyValueFactory<>("amount"));

        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("status"));

        startDateCol.setCellValueFactory(
                new PropertyValueFactory<>("startDate"));

        endDateCol.setCellValueFactory(
                new PropertyValueFactory<>("endDate"));

        sponsorIDCol.setCellValueFactory(
                new PropertyValueFactory<>("sponsorId"));

        sponsorTV.setItems(sponsorshipList);

        sponsorshipTypeCB.setItems(
                FXCollections.observableArrayList(
                        "Financial",
                        "Equipment",
                        "Media",
                        "Official"
                )
        );
    }


    @javafx.fxml.FXML
    public void saveButtonOA(ActionEvent actionEvent) {

        String sponsorId = sponsorIdTF.getText().trim();
        String sponsorName = sponsorNameTF.getText().trim();
        String sponsorshipType = sponsorshipTypeCB.getValue();
        String amountText = amountTF.getText().trim();
        String status = statusTF.getText().trim();

        LocalDate startDate = startDatedatepicker.getValue();
        LocalDate endDate = endDatedatepicker.getValue();


        if (sponsorId.isEmpty() ||
                sponsorName.isEmpty() ||
                sponsorshipType == null ||
                amountText.isEmpty() ||
                status.isEmpty() ||
                startDate == null ||
                endDate == null) {

            sponsorshipStatusTA.setText(
                    "Please fill in all fields."
            );
            return;
        }


        double amount;

        try {

            amount = Double.parseDouble(amountText);

        } catch (NumberFormatException e) {

            sponsorshipStatusTA.setText(
                    "Please enter a valid amount."
            );
            return;
        }


        if (amount < 0) {

            sponsorshipStatusTA.setText(
                    "Amount cannot be negative."
            );
            return;
        }


        if (endDate.isBefore(startDate)) {

            sponsorshipStatusTA.setText(
                    "End date cannot be before start date."
            );
            return;
        }


        manage_sponsorship sponsorship =
                new manage_sponsorship(
                        sponsorId,
                        sponsorName,
                        sponsorshipType,
                        amount,
                        status,
                        startDate,
                        endDate
                );


        sponsorshipList.add(sponsorship);

        sponsorshipStatusTA.setText(
                "Sponsorship saved successfully."
        );

        clearFields();
    }


    @javafx.fxml.FXML
    public void updateButtonOA(ActionEvent actionEvent) {

        manage_sponsorship selectedSponsorship =
                sponsorTV.getSelectionModel().getSelectedItem();


        if (selectedSponsorship == null) {

            sponsorshipStatusTA.setText(
                    "Please select a sponsorship first."
            );
            return;
        }


        String sponsorId = sponsorIdTF.getText().trim();
        String sponsorName = sponsorNameTF.getText().trim();
        String sponsorshipType = sponsorshipTypeCB.getValue();
        String amountText = amountTF.getText().trim();
        String status = statusTF.getText().trim();

        LocalDate startDate = startDatedatepicker.getValue();
        LocalDate endDate = endDatedatepicker.getValue();


        if (sponsorId.isEmpty() ||
                sponsorName.isEmpty() ||
                sponsorshipType == null ||
                amountText.isEmpty() ||
                status.isEmpty() ||
                startDate == null ||
                endDate == null) {

            sponsorshipStatusTA.setText(
                    "Please fill in all fields."
            );
            return;
        }


        double amount;

        try {

            amount = Double.parseDouble(amountText);

        } catch (NumberFormatException e) {

            sponsorshipStatusTA.setText(
                    "Please enter a valid amount."
            );
            return;
        }


        if (amount < 0) {

            sponsorshipStatusTA.setText(
                    "Amount cannot be negative."
            );
            return;
        }


        if (endDate.isBefore(startDate)) {

            sponsorshipStatusTA.setText(
                    "End date cannot be before start date."
            );
            return;
        }


        selectedSponsorship.setSponsorId(sponsorId);
        selectedSponsorship.setSponsorName(sponsorName);
        selectedSponsorship.setSponsorshipType(sponsorshipType);
        selectedSponsorship.setAmount(amount);
        selectedSponsorship.setStatus(status);
        selectedSponsorship.setStartDate(startDate);
        selectedSponsorship.setEndDate(endDate);


        sponsorTV.refresh();

        sponsorshipStatusTA.setText(
                "Sponsorship updated successfully."
        );

        clearFields();
    }


    @javafx.fxml.FXML
    public void refreshButtonOA(ActionEvent actionEvent) {

        sponsorTV.refresh();

        sponsorshipStatusTA.setText(
                "Table refreshed successfully."
        );
    }


    private void clearFields() {

        sponsorIdTF.clear();
        sponsorNameTF.clear();
        sponsorshipTypeCB.setValue(null);
        amountTF.clear();
        statusTF.clear();
        sponsorID.clear();
        startDatedatepicker.setValue(null);
        endDatedatepicker.setValue(null);
    }
}

