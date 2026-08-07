package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class manage_annual_budget_Controller
{
    @javafx.fxml.FXML
    private TableColumn<manage_annual_budget_Controller,String> budgetIDColumn;
    @javafx.fxml.FXML
    private ComboBox<manage_annual_budget_Controller> yearCB;
    @javafx.fxml.FXML
    private TableColumn<manage_annual_budget_Controller,String> categoryColumn;
    @javafx.fxml.FXML
    private TableView<manage_annual_budget_Controller> generatereportTV;
    @javafx.fxml.FXML
    private TableColumn<manage_annual_budget_Controller,Double> spentamountColumn;
    @javafx.fxml.FXML
    private TableColumn<manage_annual_budget_Controller,String> remainingamountColumn;
    @javafx.fxml.FXML
    private TableColumn<manage_annual_budget,Double> allocatedamountColumn;
    @javafx.fxml.FXML
    private TextField remainingbudgetTF;
    @javafx.fxml.FXML
    private TextField spentbudgetTF;
    @javafx.fxml.FXML
    private TextField allocated_budgetTF;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
            SceneSwitcher.switchTo("");

        }

    @javafx.fxml.FXML
    public void generatereportButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void refreshButtonOA(ActionEvent actionEvent) {
    }
}