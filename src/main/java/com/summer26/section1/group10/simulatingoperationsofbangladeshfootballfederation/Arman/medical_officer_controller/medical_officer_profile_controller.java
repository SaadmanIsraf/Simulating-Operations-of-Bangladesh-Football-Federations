//package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_controller;
//
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes.MedicalOfficer;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.medical_officer_manager.MedicalOfficerManager;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
//import javafx.event.ActionEvent;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//
//public class medical_officer_profile_controller
//{
//    @javafx.fxml.FXML
//    private Label medical_officer_id_label;
//    @javafx.fxml.FXML
//    private TextField medical_officer_age_textfield;
//    @javafx.fxml.FXML
//    private Label medical_officer_name_label;
//    @javafx.fxml.FXML
//    private TextField medical_officer_ID_textfield;
//    @javafx.fxml.FXML
//    private PasswordField medical_officer_password_passwordfield;
//    @javafx.fxml.FXML
//    private TextField medical_officer_name_textfield;
//    @javafx.fxml.FXML
//    private Label medical_officer_age_label;
//    @javafx.fxml.FXML
//    private Label medical_officer_contact_label;
//    @javafx.fxml.FXML
//    private Label medial_officer_specialization_label;
//    @javafx.fxml.FXML
//    private TextField medical_officer_numebr_textfield;
//    @javafx.fxml.FXML
//    private ComboBox<String> medical_specialization_combobox;
//
//    private MedicalOfficer currentMedicalOfficer;
//
//    @javafx.fxml.FXML
//    public void initialize() {
//
//        medical_specialization_combobox.getItems().addAll(
//                "Sports Medicine",
//                "Physiotherapy",
//                "Orthopedics",
//                "Emergency Medicine",
//                "General Medicine"
//        );
//
//        if (!MedicalOfficerManager.getMedicalOfficerList().isEmpty()) {
//
//            currentMedicalOfficer =
//                    MedicalOfficerManager.getMedicalOfficerList().get(0);
//
//            displayMedicalOfficerInformation();
//        }
//    }
//
//    private void displayMedicalOfficerInformation() {
//
//        medical_officer_name_label.setText(
//                currentMedicalOfficer.getName()
//        );
//
//        medical_officer_id_label.setText(
//                String.valueOf(currentMedicalOfficer.getId())
//        );
//
//        medical_officer_age_label.setText(
//                String.valueOf(currentMedicalOfficer.getAge())
//        );
//
//        medial_officer_specialization_label.setText(
//                currentMedicalOfficer.getSpecialization()
//        );
//
//        medical_officer_contact_label.setText(
//                currentMedicalOfficer.getContactNumber()
//        );
//
//        medical_officer_name_textfield.setText(
//                currentMedicalOfficer.getName()
//        );
//
//        medical_officer_ID_textfield.setText(
//                String.valueOf(currentMedicalOfficer.getId())
//        );
//
//        medical_officer_age_textfield.setText(
//                String.valueOf(currentMedicalOfficer.getAge())
//        );
//
//        medical_specialization_combobox.setValue(
//                currentMedicalOfficer.getSpecialization()
//        );
//
//        medical_officer_numebr_textfield.setText(
//                currentMedicalOfficer.getContactNumber()
//        );
//    }
//
//    @javafx.fxml.FXML
//    public void save_button_on_action(ActionEvent actionEvent) {
//
//        String name =
//                medical_officer_name_textfield.getText().trim();
//
//        String idText =
//                medical_officer_ID_textfield.getText().trim();
//
//        String ageText =
//                medical_officer_age_textfield.getText().trim();
//
//        String specialization =
//                medical_specialization_combobox.getValue();
//
//        String contactNumber =
//                medical_officer_numebr_textfield.getText().trim();
//
//        String password =
//                medical_officer_password_passwordfield.getText();
//
//        if (name.isEmpty()
//                || idText.isEmpty()
//                || ageText.isEmpty()
//                || specialization == null
//                || contactNumber.isEmpty()) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Empty Field",
//                    "Please fill in all required fields."
//            );
//            return;
//        }
//
//        if (!name.matches("[a-zA-Z ]+")) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Name",
//                    "Name must contain only letters and spaces."
//            );
//            return;
//        }
//
//        int medicalOfficerId;
//        int medicalOfficerAge;
//
//        try {
//            medicalOfficerId = Integer.parseInt(idText);
//            medicalOfficerAge = Integer.parseInt(ageText);
//
//        } catch (NumberFormatException e) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Input",
//                    "ID and age must be valid whole numbers."
//            );
//            return;
//        }
//
//        if (medicalOfficerId <= 0) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid ID",
//                    "Medical Officer ID must be greater than zero."
//            );
//            return;
//        }
//
//        if (medicalOfficerAge < 25 || medicalOfficerAge > 65) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Age",
//                    "Age must be between 25 and 65."
//            );
//            return;
//        }
//
//        if (!contactNumber.matches("\\d{11}")) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Contact Number",
//                    "Contact number must contain exactly 11 digits."
//            );
//            return;
//        }
//
//        if (!password.isEmpty() && password.length() < 8) {
//
//            showAlert(
//                    Alert.AlertType.ERROR,
//                    "Invalid Password",
//                    "Password must contain at least 8 characters."
//            );
//            return;
//        }
//
//        for (MedicalOfficer medicalOfficer :
//                MedicalOfficerManager.getMedicalOfficerList()) {
//
//            if (medicalOfficer != currentMedicalOfficer
//                    && medicalOfficer.getId() == medicalOfficerId) {
//
//                showAlert(
//                        Alert.AlertType.ERROR,
//                        "Duplicate ID",
//                        "This Medical Officer ID is already registered."
//                );
//                return;
//            }
//
//            if (medicalOfficer != currentMedicalOfficer
//                    && medicalOfficer.getContactNumber()
//                    .equals(contactNumber)) {
//
//                showAlert(
//                        Alert.AlertType.ERROR,
//                        "Duplicate Contact Number",
//                        "This contact number is already registered."
//                );
//                return;
//            }
//        }
//
//        if (currentMedicalOfficer == null) {
//
//            if (password.isEmpty()) {
//
//                showAlert(
//                        Alert.AlertType.ERROR,
//                        "Password Required",
//                        "Password is required when creating the profile."
//                );
//                return;
//            }
//
//            currentMedicalOfficer = new MedicalOfficer(
//                    medicalOfficerId,
//                    name,
//                    "",
//                    password,
//                    "Medical Officer",
//                    medicalOfficerAge,
//                    specialization,
//                    contactNumber
//            );
//
//            MedicalOfficerManager.addMedicalOfficer(
//                    currentMedicalOfficer
//            );
//
//        } else {
//
//            currentMedicalOfficer.setName(name);
//            currentMedicalOfficer.setId(medicalOfficerId);
//            currentMedicalOfficer.setAge(medicalOfficerAge);
//            currentMedicalOfficer.setSpecialization(specialization);
//            currentMedicalOfficer.setContactNumber(contactNumber);
//
//            if (!password.isEmpty()) {
//                currentMedicalOfficer.changePassword(password);
//            }
//        }
//
//        MedicalOfficerManager.saveToFile();
//
//        displayMedicalOfficerInformation();
//        medical_officer_password_passwordfield.clear();
//
//        showAlert(
//                Alert.AlertType.INFORMATION,
//                "Successful",
//                "Profile Updated Successfully!"
//        );
//    }
//
//    @Deprecated
//    public void medical_specilization_combobox_on_action(
//            ActionEvent actionEvent) {
//    }
//
//    @javafx.fxml.FXML
//    public void back_button_on_action(ActionEvent actionEvent) {
//
//        SceneSwitcher.switchTo(
//                "Arman/medical_officer/medical_officer_dashboard.fxml"
//        );
//    }
//
//    @javafx.fxml.FXML
//    public void medical_specialization_conbobox_on_action(
//            ActionEvent actionEvent) {
//    }
//
//    private void showAlert(
//            Alert.AlertType alertType,
//            String title,
//            String message) {
//
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//}