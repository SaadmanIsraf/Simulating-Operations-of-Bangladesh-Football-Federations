package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MedicalEquipment;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.MedicalEquipmentManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class medical_equipment_controller {

    @FXML
    private TableView<MedicalEquipment> equipment_tableview;
    @FXML
    private TextField equipemrent_name_textfield;
    @FXML
    private TableColumn<MedicalEquipment, String> storage_location_column;
    @FXML
    private TableColumn<MedicalEquipment, Integer> equipment_id_coloum;
    @FXML
    private TableColumn<MedicalEquipment, Integer> quantity_available_column;
    @FXML
    private TextField search_id_textfield;
    @FXML
    private TextField equipment_id_textfield;
    @FXML
    private TextField quality_available_textfield;
    @FXML
    private TableColumn<MedicalEquipment, String> equipment_name_column;
    @FXML
    private TableColumn<MedicalEquipment, String> condition_column;
    @FXML
    private TextField stroage_location_textfield;
    @FXML
    private ComboBox<String> condition_combobox;
    @FXML
    private TextArea equipment_details_textarea;

    private MedicalEquipment selectedEquipment;

    @FXML
    public void initialize() {

        equipment_id_coloum.setCellValueFactory(new PropertyValueFactory<>("equipmentId"));
        equipment_name_column.setCellValueFactory(new PropertyValueFactory<>("equipmentName"));
        quantity_available_column.setCellValueFactory(new PropertyValueFactory<>("quantityAvailable"));
        condition_column.setCellValueFactory(new PropertyValueFactory<>("condition"));
        storage_location_column.setCellValueFactory(new PropertyValueFactory<>("storageLocation"));

        condition_combobox.getItems().addAll(
                "Good",
                "Needs Repair",
                "Damaged",
                "Expired"
        );

        refreshTable();

        equipment_tableview.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectedEquipment = newValue;
                        displayEquipment(newValue);
                    }
                });
    }

    private void refreshTable() {
        equipment_tableview.getItems().setAll(MedicalEquipmentManager.getEquipmentList());
        equipment_tableview.refresh();
    }

    private void displayEquipment(MedicalEquipment equipment) {

        equipment_id_textfield.setText(String.valueOf(equipment.getEquipmentId()));
        equipemrent_name_textfield.setText(equipment.getEquipmentName());
        quality_available_textfield.setText(String.valueOf(equipment.getQuantityAvailable()));
        condition_combobox.setValue(equipment.getCondition());
        stroage_location_textfield.setText(equipment.getStorageLocation());

        String details =
                "Equipment ID: " + equipment.getEquipmentId() + "\n" +
                        "Equipment Name: " + equipment.getEquipmentName() + "\n" +
                        "Quantity Available: " + equipment.getQuantityAvailable() + "\n" +
                        "Condition: " + equipment.getCondition() + "\n" +
                        "Storage Location: " + equipment.getStorageLocation();

        equipment_details_textarea.setText(details);
    }

    @FXML
    public void search_button_on_action(ActionEvent actionEvent) {

        String searchIdText = search_id_textfield.getText().trim();

        if (searchIdText.isEmpty()) {showAlert(Alert.AlertType.ERROR, "Empty Field", "Please enter an Equipment ID.");
            search_id_textfield.requestFocus();
            return;
        }

        if (!searchIdText.matches("\\d+")) {showAlert(Alert.AlertType.ERROR, "Invalid ID", "Equipment ID must contain only numbers.");
            search_id_textfield.requestFocus();
            return;
        }

        int searchId = Integer.parseInt(searchIdText);
        selectedEquipment = null;

        for (MedicalEquipment equipment : MedicalEquipmentManager.getEquipmentList()) {
            if (equipment.getEquipmentId() == searchId) {
                selectedEquipment = equipment;
                break;
            }
        }

        if (selectedEquipment == null) {
            equipment_details_textarea.setText("No equipment found for Equipment ID: " + searchId);
            showAlert(Alert.AlertType.ERROR, "Equipment Not Found", "No equipment exists with this Equipment ID.");
            return;
        }

        displayEquipment(selectedEquipment);
        equipment_tableview.getSelectionModel().select(selectedEquipment);
        equipment_tableview.scrollTo(selectedEquipment);

        showAlert(Alert.AlertType.INFORMATION, "Equipment Found", "Equipment details loaded successfully.");
    }

    @FXML
    public void update_button_on_action(ActionEvent actionEvent) {

        String idText = equipment_id_textfield.getText().trim();
        String equipmentName = equipemrent_name_textfield.getText().trim();
        String quantityText = quality_available_textfield.getText().trim();
        String condition = condition_combobox.getValue();
        String storageLocation = stroage_location_textfield.getText().trim();

        if (idText.isEmpty()) {showAlert(Alert.AlertType.ERROR, "Empty Field", "Equipment ID cannot be empty.");
            equipment_id_textfield.requestFocus();
            return;
        }

        if (!idText.matches("\\d+")) {showAlert(Alert.AlertType.ERROR, "Invalid ID", "Equipment ID must contain only numbers.");
            equipment_id_textfield.requestFocus();
            return;
        }

        if (equipmentName.isEmpty()) {showAlert(Alert.AlertType.ERROR, "Empty Field", "Equipment name cannot be empty.");
            equipemrent_name_textfield.requestFocus();
            return;
        }

        if (quantityText.isEmpty()) {showAlert(Alert.AlertType.ERROR, "Empty Field", "Quantity cannot be empty.");
            quality_available_textfield.requestFocus();
            return;
        }

        if (!quantityText.matches("\\d+")) {showAlert(Alert.AlertType.ERROR, "Invalid Quantity", "Quantity must contain only numbers.");
            quality_available_textfield.requestFocus();
            return;
        }

        if (condition == null) {showAlert(Alert.AlertType.ERROR, "Empty Field", "Please select the equipment condition.");
            return;
        }

        if (storageLocation.isEmpty()) {showAlert(Alert.AlertType.ERROR, "Empty Field", "Storage location cannot be empty.");
            stroage_location_textfield.requestFocus();
            return;
        }

        int equipmentId = Integer.parseInt(idText);
        int quantity = Integer.parseInt(quantityText);

        if (equipmentId <= 0) {showAlert(Alert.AlertType.ERROR, "Invalid Equipment ID", "Equipment ID must be greater than zero.");
            return;
        }

        MedicalEquipment foundEquipment = null;

        for (MedicalEquipment equipment : MedicalEquipmentManager.getEquipmentList()) {
            if (equipment.getEquipmentId() == equipmentId) {
                foundEquipment = equipment;
                break;
            }
        }

        if (foundEquipment == null) {

            MedicalEquipment newEquipment = new MedicalEquipment(
                    equipmentId,
                    equipmentName,
                    quantity,
                    condition,
                    storageLocation
            );

            MedicalEquipmentManager.addEquipment(newEquipment);
            MedicalEquipmentManager.saveToFile();

            refreshTable();
            displayEquipment(newEquipment);
            equipment_tableview.getSelectionModel().select(newEquipment);

            showAlert(Alert.AlertType.INFORMATION, "Successful", "New Medical Equipment Added Successfully!");

        } else {

            foundEquipment.setEquipmentName(equipmentName);
            foundEquipment.setQuantityAvailable(quantity);
            foundEquipment.setCondition(condition);
            foundEquipment.setStorageLocation(storageLocation);

            MedicalEquipmentManager.saveToFile();

            refreshTable();
            displayEquipment(foundEquipment);
            equipment_tableview.getSelectionModel().select(foundEquipment);

            showAlert(Alert.AlertType.INFORMATION, "Successful", "Medical Equipment Updated Successfully!");
        }

        clearInputFields();
    }

    @FXML
    public void condition_combobox_on_action(ActionEvent actionEvent) {
    }

    @FXML
    public void back_button_on_action(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Arman/medical_officer/medical_officer_dashboard.fxml");
    }

    private void clearInputFields() {

        equipment_id_textfield.clear();
        equipemrent_name_textfield.clear();
        quality_available_textfield.clear();
        condition_combobox.getSelectionModel().clearSelection();
        stroage_location_textfield.clear();

        equipment_tableview.getSelectionModel().clearSelection();
        selectedEquipment = null;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}