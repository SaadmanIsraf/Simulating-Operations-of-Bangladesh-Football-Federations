package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class manage_annual_budget_Controller {

    @javafx.fxml.FXML
    private TextField budgetIDTF;

    @javafx.fxml.FXML
    private ComboBox<Integer> yearCB;

    @javafx.fxml.FXML
    private TextField categoryTF;

    @javafx.fxml.FXML
    private TextField allocated_budgetTF;

    @javafx.fxml.FXML
    private TextField spent_budgetTF;

    @javafx.fxml.FXML
    private TextField remaining_budgetTF;

    @javafx.fxml.FXML
    private TableView<manage_annual_budget> generate_reportTV;

    @javafx.fxml.FXML
    private TableColumn<manage_annual_budget, String> budgetIDColumn;

    @javafx.fxml.FXML
    private TableColumn<manage_annual_budget, Integer> yearColumn;

    @javafx.fxml.FXML
    private TableColumn<manage_annual_budget, String> categoryColumn;

    @javafx.fxml.FXML
    private TableColumn<manage_annual_budget, String> allocated_amountColumn;

    @javafx.fxml.FXML
    private TableColumn<manage_annual_budget, String> spent_amountColumn;

    @javafx.fxml.FXML
    private TableColumn<manage_annual_budget, String> remaining_amountColumn;


    private final ObservableList<manage_annual_budget> budgetList =
            FXCollections.observableArrayList();


    @javafx.fxml.FXML
    public void initialize() {

        budgetIDColumn.setCellValueFactory(
                new PropertyValueFactory<>("budgetID"));

        yearColumn.setCellValueFactory(
                new PropertyValueFactory<>("year"));

        categoryColumn.setCellValueFactory(
                new PropertyValueFactory<>("category"));

        allocated_amountColumn.setCellValueFactory(
                new PropertyValueFactory<>("allocated_budget"));

        spent_amountColumn.setCellValueFactory(
                new PropertyValueFactory<>("spent_budget"));

        remaining_amountColumn.setCellValueFactory(
                new PropertyValueFactory<>("remaining_budget"));

        generate_reportTV.setItems(budgetList);

        yearCB.setItems(
                FXCollections.observableArrayList(
                        2024,
                        2025,
                        2026,
                        2027,
                        2028
                )
        );
    }


    @javafx.fxml.FXML
    public void saveButtonOA(ActionEvent actionEvent) {

        String budgetID = budgetIDTF.getText().trim();
        Integer year = yearCB.getValue();
        String category = categoryTF.getText().trim();
        String allocatedBudget = allocated_budgetTF.getText().trim();
        String spentBudget = spent_budgetTF.getText().trim();
        String remainingBudget = remaining_budgetTF.getText().trim();


        if (budgetID.isEmpty()
                || year == null
                || category.isEmpty()
                || allocatedBudget.isEmpty()
                || spentBudget.isEmpty()
                || remainingBudget.isEmpty()) {

            return;
        }

        manage_annual_budget budget =
                new manage_annual_budget(
                        budgetID,
                        year,
                        category,
                        allocatedBudget,
                        spentBudget,
                        remainingBudget
                );

        budgetList.add(budget);

        generate_reportTV.refresh();

        clearFields();
    }


    @javafx.fxml.FXML
    public void refreshButtonOA(ActionEvent actionEvent) {

        generate_reportTV.refresh();
    }


    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {

    }


    private void clearFields() {

        budgetIDTF.clear();
        yearCB.setValue(null);
        categoryTF.clear();
        allocated_budgetTF.clear();
        spent_budgetTF.clear();
        remaining_budgetTF.clear();
    }
}

