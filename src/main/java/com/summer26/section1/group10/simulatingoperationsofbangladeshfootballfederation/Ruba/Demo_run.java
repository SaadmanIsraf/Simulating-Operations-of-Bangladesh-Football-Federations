package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ruba;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Run;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.SceneSwitcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Demo_run extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        SceneSwitcher.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();


    }
}
