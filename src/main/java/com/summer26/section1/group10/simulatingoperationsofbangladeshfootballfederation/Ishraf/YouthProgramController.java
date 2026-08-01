package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class YouthProgramController
{
    @javafx.fxml.FXML
    private Label registerLabel;
    @javafx.fxml.FXML
    private TableColumn<YouthProgram,String> genderTC;
    @javafx.fxml.FXML
    private TableColumn<YouthProgram,String> programNameTC;
    @javafx.fxml.FXML
    private ComboBox<String> genderTF;
    @javafx.fxml.FXML
    private TableColumn<YouthProgram,String> ageTC;
    @javafx.fxml.FXML
    private TableView<YouthProgram> youthProgramTV;
    @javafx.fxml.FXML
    private TextField ProgramIdTF;
    @javafx.fxml.FXML
    private ComboBox<String> ageTF;
    @javafx.fxml.FXML
    private TableColumn<YouthProgram,String> programIDTC;
    @javafx.fxml.FXML
    private TextField ProgramNameTF;
    @javafx.fxml.FXML
    private TableColumn<YouthProgram,String> skillTC;
    @javafx.fxml.FXML
    private ComboBox<String> skillTF;
    @javafx.fxml.FXML
    private Label messageLabel;

    @javafx.fxml.FXML
    public void initialize() {
        ageTF.getItems().addAll(
                "8",
                "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28","30"
        );
        genderTF.getItems().addAll(
                "Male", "Female"
        );
        skillTF.getItems().addAll(
                "Beginner", "Intermediate", "Advanced"
        );
        programIDTC.setCellValueFactory(new PropertyValueFactory<>("programID"));

        programNameTC.setCellValueFactory(new PropertyValueFactory<>("programName"));

        ageTC.setCellValueFactory(new PropertyValueFactory<>("age"));

        genderTC.setCellValueFactory(new PropertyValueFactory<>("gender"));

        skillTC.setCellValueFactory(new PropertyValueFactory<>("skill"));


    }

    @javafx.fxml.FXML
    public void CreateProgramOA(ActionEvent actionEvent) {
        String id = ProgramIdTF.getText();
        String name = ProgramNameTF.getText();
        String age = ageTF.getValue();
        String gender = genderTF.getValue();
        String skill = skillTF.getValue();

        int playerAge = Integer.parseInt(age);
        //Age Validation//
        if (playerAge < 0 || playerAge > 42) {

            messageLabel.setText("Player age is invalid");

            return;
        }

        YouthProgram program = new YouthProgram(
                id, name, age, gender, skill
        );
        youthProgramTV.getItems().add(program);
        //Clear all items so new input can be taken//
        ProgramIdTF.clear();
        ProgramNameTF.clear();

        ageTF.setValue(null);
        genderTF.setValue(null);
        skillTF.setValue(null);




    }


    @javafx.fxml.FXML
    public void RegisterPlayerButton(ActionEvent actionEvent) {
    }
}