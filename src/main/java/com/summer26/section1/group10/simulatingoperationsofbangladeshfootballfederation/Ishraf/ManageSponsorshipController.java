package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.beans.property.SimpleStringProperty;

public class ManageSponsorshipController
{   private ObservableList<Sponsorship> sponsorshipList =
        FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private TableColumn<Sponsorship, String> sponsorshipTypeTC;
    @javafx.fxml.FXML
    private TableColumn<Sponsorship, String> contractStartDateTC;
    @javafx.fxml.FXML
    private TableColumn<Sponsorship, String> sponsornameTC;
    @javafx.fxml.FXML
    private TableColumn<Sponsorship, String> sponsorIdTC;
    @javafx.fxml.FXML
    private Label messageLabel2;
    @javafx.fxml.FXML
    private Label messageLabel1;
    @javafx.fxml.FXML
    private TableColumn<Sponsorship, String> sponsorshipAmountTC;
    @javafx.fxml.FXML
    private TableView<Sponsorship> createContractTV;
    @javafx.fxml.FXML
    private TableColumn<Sponsorship, String> contractEndDateTC;
    @javafx.fxml.FXML
    private TextArea ShowContractInfoTA;
    @javafx.fxml.FXML
    private DatePicker contractStartDateDP;
    @javafx.fxml.FXML
    private TextField sponsornameTF;
    @javafx.fxml.FXML
    private TextField sponsorIdTF;
    @javafx.fxml.FXML
    private TextField sponsorshipAmountTF;
    @javafx.fxml.FXML
    private ComboBox<String> sponsorshipTypeCB;
    @javafx.fxml.FXML
    private DatePicker contractEndDateDP;

    @javafx.fxml.FXML
    public void initialize() {
        sponsorIdTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getSponsorId()
                )
        );

        sponsornameTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getSponsorName()
                )
        );

        sponsorshipAmountTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getSponsorshipAmount()
                )
        );

        sponsorshipTypeTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getSponsorshipType()
                )
        );

        contractStartDateTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getContractStartDate()
                )
        );

        contractEndDateTC.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getContractEndDate()
                )
        );

        // Connect the list to the TableView
        createContractTV.setItems(sponsorshipList);

        // ComboBox options
        sponsorshipTypeCB.setItems(
                FXCollections.observableArrayList(
                        "Main Sponsor",
                        "Official Sponsor",
                        "Technical Sponsor",
                        "Media Sponsor"
                )
        );
    }

    @javafx.fxml.FXML
    public void CreatecontractButton(ActionEvent actionEvent) {
        String sponsorId = sponsorIdTF.getText().trim();
        String sponsorName = sponsornameTF.getText().trim();
        String sponsorshipAmount = sponsorshipAmountTF.getText().trim();

        String sponsorshipType = "";

        if (sponsorshipTypeCB.getValue() != null) {
            sponsorshipType = sponsorshipTypeCB.getValue();
        }

        String contractStartDate = "";

        if (contractStartDateDP.getValue() != null) {
            contractStartDate =
                    contractStartDateDP.getValue().toString();
        }

        String contractEndDate = "";

        if (contractEndDateDP.getValue() != null) {
            contractEndDate =
                    contractEndDateDP.getValue().toString();
        }

        // Check if any field is empty
        if (sponsorId.isEmpty() ||
                sponsorName.isEmpty() ||
                sponsorshipAmount.isEmpty() ||
                sponsorshipType.isEmpty() ||
                contractStartDate.isEmpty() ||
                contractEndDate.isEmpty()) {

            messageLabel1.setText("Please fill in all fields.");
            return;
        }

        // Create Sponsorship object
        Sponsorship sponsorship = new Sponsorship(
                sponsorId,
                sponsorName,
                sponsorshipAmount,
                sponsorshipType,
                contractStartDate,
                contractEndDate
        );

        // Add to TableView
        sponsorshipList.add(sponsorship);

        // Clear fields
        sponsorIdTF.clear();
        sponsornameTF.clear();
        sponsorshipAmountTF.clear();
        sponsorshipTypeCB.setValue(null);
        contractStartDateDP.setValue(null);
        contractEndDateDP.setValue(null);

        messageLabel1.setText("Contract created successfully!");
    }

    @javafx.fxml.FXML
    public void ShowcontractinfoButton(ActionEvent actionEvent) {
        Sponsorship selectedSponsorship =
                createContractTV.getSelectionModel().getSelectedItem();

        // Check if a row is selected
        if (selectedSponsorship == null) {
            ShowContractInfoTA.setText("Please select a contract from the table.");
            return;
        }

        // Show selected contract information
        ShowContractInfoTA.setText(
                "CONTRACT INFORMATION\n\n" +
                        "Sponsor ID: " + selectedSponsorship.getSponsorId() + "\n" +
                        "Sponsor Name: " + selectedSponsorship.getSponsorName() + "\n" +
                        "Sponsorship Amount: " + selectedSponsorship.getSponsorshipAmount() + "\n" +
                        "Sponsorship Type: " + selectedSponsorship.getSponsorshipType() + "\n" +
                        "Contract Start Date: " + selectedSponsorship.getContractStartDate() + "\n" +
                        "Contract End Date: " + selectedSponsorship.getContractEndDate()
        );
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Ishraf/BFF_Manager/BffManagerDashBoard.fxml");
    }
}