package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;

public class financial_officer_dashboard_Controller
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void cash_flowButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/financial_officer/cash_flow.fxml");
    }

    @javafx.fxml.FXML
    public void manage_annual_budgetButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/financial_officer/manage_annual_budget.fxml");

    }

    @javafx.fxml.FXML
    public void record_incomeButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/financial_officer/income_record.fxml");
    }

    @javafx.fxml.FXML
    public void financial_reportButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/financial_officer/financial_report.fxml");
    }

    @javafx.fxml.FXML
    public void approve_paymentButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/financial_officer/approve_payment.fxml");
    }

    @javafx.fxml.FXML
    public void log_outButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/financial_officer/log_out.fxml");
    }

    @javafx.fxml.FXML
    public void financial_auditButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/financial_officer/financial_audit.fxml");
    }

    @javafx.fxml.FXML
    public void manage_sponsorshipButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/financial_officer/manage-sponsorship.fxml");
    }

    @javafx.fxml.FXML
    public void financial_officer_profileButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/financial_officer/financial_officer_profile.fxml");
    }

    @javafx.fxml.FXML
    public void record_expenseButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Juti/record_expense/financial_officer_profile.fxml");
    }
}