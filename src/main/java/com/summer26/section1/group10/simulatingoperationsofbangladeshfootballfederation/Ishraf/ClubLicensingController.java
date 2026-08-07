package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClubLicensingController {

    @javafx.fxml.FXML
    private TableColumn<ClubRegistration, String> clubCategoryTC;
    @javafx.fxml.FXML
    private TextField clubAddressTF;
    @javafx.fxml.FXML
    private TableColumn<ClubRegistration, String> clubIdTC;
    @javafx.fxml.FXML
    private TableColumn<ClubRegistration, String> clubNameTC;
    @javafx.fxml.FXML
    private TextField clubNameTF;
    @javafx.fxml.FXML
    private TextField clubIdTF;
    @javafx.fxml.FXML
    private TextArea DisplayRegTA;
    @javafx.fxml.FXML
    private TextField contactTF;
    @javafx.fxml.FXML
    private ComboBox<String> clubCategoryCB;
    @javafx.fxml.FXML
    private TableColumn<ClubRegistration, String> contactTC;
    @javafx.fxml.FXML
    private TableColumn<ClubRegistration, String> managerNameTC;
    @javafx.fxml.FXML
    private TableColumn<ClubRegistration, String> clubAddressTC;
    @javafx.fxml.FXML
    private TableView<ClubRegistration> RegisterClubTV;
    //datalist//
    private ObservableList<ClubRegistration> clubList =
            FXCollections.observableArrayList();
    //bin file//
    private final String FILE_NAME = "club_registrations.bin";
    @FXML
    private TextField managerNameTF;


    @javafx.fxml.FXML
    public void initialize() {
        clubIdTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getClubId()
                )
        );
        clubNameTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getClubName()
                )
        );
        clubCategoryTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getClubCategory()
                )
        );
        clubAddressTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getClubAddress()
                )
        );
        managerNameTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getManagerName()
                )
        );
        contactTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getContactNumber()
                )
        );
        clubCategoryCB.setItems(
                FXCollections.observableArrayList(
                        "Professional",
                        "Semi-Professional",
                        "Amateur",
                        "Youth"
                )
        );


        // CONNECT LIST TO TABLEVIEW
        RegisterClubTV.setItems(clubList);


    }

    @javafx.fxml.FXML
    public void registerButton(ActionEvent actionEvent) {
        String id = clubIdTF.getText().trim();

        String name = clubNameTF.getText().trim();

        String address = clubAddressTF.getText().trim();

        String manager = managerNameTF.getText().trim();

        String contact = contactTF.getText().trim();

        String category = clubCategoryCB.getValue();

        if (id.isEmpty()
                || name.isEmpty()
                || address.isEmpty()
                || manager.isEmpty()
                || contact.isEmpty()
                || category == null) {

            showAlert(
                    "Error",
                    "Please fill in all fields."
            );

            return;
        }
        ClubRegistration club =
                new ClubRegistration(
                        id,
                        name,
                        category,
                        address,
                        manager,
                        contact
                );
        clubList.add(club);

        showAlert(
                "Success",
                "Club registered successfully!"
        );
    }


    @Deprecated
    public void RegisterClubTV(Event event) {
    }

    @javafx.fxml.FXML
    public void updateButton(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void uploadButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void DisplayRegButton(ActionEvent actionEvent) {

    }


    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
