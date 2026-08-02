package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MedicalEquipment;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.MedicalEquipmentManager;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class medical_equipment_controller
{
    @javafx.fxml.FXML
    private TableView<MedicalEquipment> equipment_tableview;
    @javafx.fxml.FXML
    private TextField equipemrent_name_textfield;
    @javafx.fxml.FXML
    private TableColumn<MedicalEquipment, String> storage_location_column;
    @javafx.fxml.FXML
    private TableColumn<MedicalEquipment, Integer> equipment_id_coloum;
    @javafx.fxml.FXML
    private TableColumn<MedicalEquipment, Integer> quantity_available_column;
    @javafx.fxml.FXML
    private TextField search_id_textfield;
    @javafx.fxml.FXML
    private TextField equipment_id_textfield;
    @javafx.fxml.FXML
    private TextField quality_available_textfield;
    @javafx.fxml.FXML
    private TableColumn<MedicalEquipment, String> equipment_name_column;
    @javafx.fxml.FXML
    private TableColumn<MedicalEquipment, String> condition_column;
    @javafx.fxml.FXML
    private TextField stroage_location_textfield;
    @javafx.fxml.FXML
    private ComboBox<String> condition_combobox;

    private MedicalEquipment selectedEquipment;
    @javafx.fxml.FXML
    private TextArea equipment_details_textarea;

    @javafx.fxml.FXML
    public void initialize() {

        equipment_id_coloum.setCellValueFactory(
                new PropertyValueFactory<>("equipmentId")
        );

        equipment_name_column.setCellValueFactory(
                new PropertyValueFactory<>("equipmentName")
        );

        quantity_available_column.setCellValueFactory(
                new PropertyValueFactory<>("quantityAvailable")
        );

        condition_column.setCellValueFactory(
                new PropertyValueFactory<>("condition")
        );

        storage_location_column.setCellValueFactory(
                new PropertyValueFactory<>("storageLocation")
        );

        condition_combobox.getItems().addAll(
                "Good",
                "Needs Repair",
                "Damaged",
                "Expired"
        );

        refreshTable();

        equipment_tableview.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {

                    if (newValue != null) {
                        selectedEquipment = newValue;
                        displayEquipment(newValue);
                    }
                });
    }

    private void refreshTable() {

        equipment_tableview.getItems().setAll(
                MedicalEquipmentManager.getEquipmentList()
        );

        equipment_tableview.refresh();
    }

    private void displayEquipment(MedicalEquipment equipment) {

        equipment_id_textfield.setText(
                String.valueOf(equipment.getEquipmentId())
        );

        equipemrent_name_textfield.setText(
                equipment.getEquipmentName()
        );

        quality_available_textfield.setText(
                String.valueOf(equipment.getQuantityAvailable())
        );

        condition_combobox.setValue(
                equipment.getCondition()
        );

        stroage_location_textfield.setText(
                equipment.getStorageLocation()
        );

        equipment_details_textarea.setText(
                "ID: " + equipment.getEquipmentId()
                        + " | Name: " + equipment.getEquipmentName()
                        + " | Quantity: " + equipment.getQuantityAvailable()
                        + " | Condition: " + equipment.getCondition()
                        + " | Storage Location: "
                        + equipment.getStorageLocation()
        );
    }

    @javafx.fxml.FXML
    public void search_button_on_action(ActionEvent actionEvent) {

        String searchIdText = search_id_textfield.getText().trim();

        if (searchIdText.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
                    "Please enter an Equipment ID."
            );
            return;
        }

        int searchId;

        try {
            searchId = Integer.parseInt(searchIdText);

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid ID",
                    "Equipment ID must be a whole number."
            );
            return;
        }

        selectedEquipment = null;

        for (MedicalEquipment equipment :
                MedicalEquipmentManager.getEquipmentList()) {

            if (equipment.getEquipmentId() == searchId) {
                selectedEquipment = equipment;
                break;
            }
        }

        if (selectedEquipment == null) {

            equipment_details_textarea.setText(
                    "No equipment found for Equipment ID: " + searchId
            );

            showAlert(
                    Alert.AlertType.ERROR,
                    "Equipment Not Found",
                    "No equipment exists with this Equipment ID."
            );
            return;
        }

        displayEquipment(selectedEquipment);

        equipment_tableview.getSelectionModel()
                .select(selectedEquipment);

        equipment_tableview.scrollTo(selectedEquipment);

        showAlert(
                Alert.AlertType.INFORMATION,
                "Equipment Found",
                "Equipment details loaded successfully."
        );
    }

    @javafx.fxml.FXML
    public void update_button_on_action(ActionEvent actionEvent) {

        String idText =
                equipment_id_textfield.getText().trim();

        String equipmentName =
                equipemrent_name_textfield.getText().trim();

        String quantityText =
                quality_available_textfield.getText().trim();

        String condition =
                condition_combobox.getValue();

        String storageLocation =
                stroage_location_textfield.getText().trim();

        if (idText.isEmpty()
                || equipmentName.isEmpty()
                || quantityText.isEmpty()
                || condition == null
                || storageLocation.isEmpty()) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Empty Field",
                    "Please fill in all equipment fields."
            );
            return;
        }

        int equipmentId;
        int quantity;

        try {
            equipmentId = Integer.parseInt(idText);
            quantity = Integer.parseInt(quantityText);

        } catch (NumberFormatException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Input",
                    "Equipment ID and quantity must be whole numbers."
            );
            return;
        }

        if (equipmentId <= 0) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Equipment ID",
                    "Equipment ID must be greater than zero."
            );
            return;
        }

        if (quantity < 0) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid Quantity",
                    "Quantity cannot be negative."
            );
            return;
        }

        MedicalEquipment foundEquipment = null;

        for (MedicalEquipment equipment :
                MedicalEquipmentManager.getEquipmentList()) {

            if (equipment.getEquipmentId() == equipmentId) {
                foundEquipment = equipment;
                break;
            }
        }

        if (foundEquipment == null) {

            MedicalEquipment newEquipment =
                    new MedicalEquipment(
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

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Successful",
                    "New Medical Equipment Added Successfully!"
            );

        } else {

            foundEquipment.setEquipmentName(equipmentName);
            foundEquipment.setQuantityAvailable(quantity);
            foundEquipment.setCondition(condition);
            foundEquipment.setStorageLocation(storageLocation);

            MedicalEquipmentManager.saveToFile();

            refreshTable();
            displayEquipment(foundEquipment);

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Successful",
                    "Medical Equipment Updated Successfully!"
            );
        }

        clearInputFields();
    }

    @javafx.fxml.FXML
    public void condition_combobox_on_action(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void back_button_on_action(ActionEvent actionEvent) {

        SceneSwitcher.switchTo(
                "Arman/medical_officer/medical_officer_dashboard.fxml"
        );
    }

    private void clearInputFields() {

        equipment_id_textfield.clear();
        equipemrent_name_textfield.clear();
        quality_available_textfield.clear();
        condition_combobox.setValue(null);
        stroage_location_textfield.clear();

        equipment_tableview.getSelectionModel().clearSelection();
        selectedEquipment = null;
    }

    private void showAlert(
            Alert.AlertType alertType,
            String title,
            String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}