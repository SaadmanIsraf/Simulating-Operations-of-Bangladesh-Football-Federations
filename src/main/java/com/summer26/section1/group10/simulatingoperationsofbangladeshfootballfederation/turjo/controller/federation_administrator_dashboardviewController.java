//package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.controller;
//
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.AlertGenerator;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.SceneSwitcher;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.UserReceiver;
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo.federation_administrator;
//import javafx.event.ActionEvent;
//
//import java.io.IOException;
//
//public class federation_administrator_dashboardviewController
//implements UserReceiver {
//
//    private federation_administrator loggedInUser;
//    @Override
//    public void setLoggedInUser(User user){
//        if (user instanceof federation_administrator f){
//            loggedInUser = f;
//        }
//        else {
//            AlertGenerator.showAlert("Error", "This is not a valid user for this page");
//        }
//    }
//
//    @javafx.fxml.FXML
//    public void initialize() {
//    }
//
//    @javafx.fxml.FXML
//    public void managematchesOA(ActionEvent actionEvent) throws IOException {
//        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/federation_administrator/managematches.fxml", loggedInUser);
//
//    }
//
//    @javafx.fxml.FXML
//    public void logoutOA(ActionEvent actionEvent) {
//        SceneSwitcher.switchTo("Login.fxml");
//    }
//
//    @javafx.fxml.FXML
//    public void approveplayertransferOA(ActionEvent actionEvent) throws IOException {
//        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/federation_administrator/approveplayertransfer.fxml", loggedInUser);
//
//    }
//
//    @javafx.fxml.FXML
//    public void manageplayersOA(ActionEvent actionEvent) throws IOException {
//
//        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/federation_administrator/manageplayer.fxml", loggedInUser);
//
//
//    }
//
//    @javafx.fxml.FXML
//    public void uploadmatchhighlightsOA(ActionEvent actionEvent) throws IOException {
//        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/federation_administrator/uploadmatchhighlights.fxml", loggedInUser);
//
//
//    }
//
//    @javafx.fxml.FXML
//    public void publishannounceOA(ActionEvent actionEvent) throws IOException {
//        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/federation_administrator/publishannouncement.fxml", loggedInUser);
//
//    }
//
//    @javafx.fxml.FXML
//    public void postliveupdatesOA(ActionEvent actionEvent) throws IOException {
//        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/federation_administrator/postliveupdates.fxml", loggedInUser);
//
//    }
//
//    @javafx.fxml.FXML
//    public void manageuseraccountOA(ActionEvent actionEvent) throws IOException {
//        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/federation_administrator/manageuseraccount.fxml", loggedInUser);
//
//    }
//
//    @javafx.fxml.FXML
//    public void manageteamrankingOA(ActionEvent actionEvent) throws IOException {
//        SceneSwitcher.switchSceneWithData(actionEvent, "/com/summer26/section1/group10/simulatingoperationsofbangladeshfootballfederation/turjo/federation_administrator/manageteamranking.fxml", loggedInUser);
//
//    }
//}