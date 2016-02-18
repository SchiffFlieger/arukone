package de.dhbw.arukone.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * created by Karsten Köhler on 18.02.2016
 */
public class InitializationGUI extends Application {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        primaryStage.setTitle("Arukone Solver");
        GridPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("resources/init.fxml"));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
