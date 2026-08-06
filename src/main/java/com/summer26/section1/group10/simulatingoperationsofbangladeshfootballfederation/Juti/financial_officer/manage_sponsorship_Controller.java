package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class manage_sponsorship_Controller
{
    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship,Double> amountCol;
    @javafx.fxml.FXML
    private TextField amountTF;
    @javafx.fxml.FXML
    private TableView<manage_sponsorship> sponsorTV;
    @javafx.fxml.FXML
    private TextField sponsorNameTF;
    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship,LocalDate> endDateCol;
    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship,String> sponsorshipTypeCol;
    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship,String> sponsorIDCol;
    @javafx.fxml.FXML
    private TextArea sponsorshipStatusTA;
    @javafx.fxml.FXML
    private TextField sponsorIDTF;
    @javafx.fxml.FXML
    private DatePicker endDatadatepicker;
    @javafx.fxml.FXML
    private ComboBox<manage_sponsorship> sponsorshipTypeCB;
    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship,String> sponsorNameCol;
    @javafx.fxml.FXML
    private TableColumn<manage_sponsorship,LocalDate> startDateCol;
    @javafx.fxml.FXML
    private DatePicker startDatedatepicker;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void refreshButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void updateButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void saveButtonOA(ActionEvent actionEvent) {
    }


}