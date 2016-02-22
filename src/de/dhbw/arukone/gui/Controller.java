package de.dhbw.arukone.gui;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * created by Karsten Köhler on 18.02.2016
 */
public class Controller implements Initializable {
    public ComboBox chooseField;
    public CheckBox autoFill;
    public CheckBox checkBlocked;
    public TextField fieldSleep;
    public Slider sliderSleep;
    public Button start;
    public Button help;
    public Button about;


    @Override
    public void initialize (URL location, ResourceBundle resources) {
        fieldSleep.textProperty().bindBidirectional(sliderSleep.valueProperty(), new NumberStringConverter("#### ms"));
    }

    public void handleStart (ActionEvent actionEvent) {
        System.out.println("start");
    }

    public void handleHelp (ActionEvent actionEvent) {
        System.out.println("help");
    }

    public void handleAbout (ActionEvent actionEvent) {
        System.out.println("about");
    }
}
