package de.dhbw.arukone.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

/**
 * created by Karsten Köhler on 18.02.2016
 */
public class Controller {
    public ComboBox chooseField;
    public CheckBox autoFill;
    public CheckBox checkBlocked;
    public TextField fieldSleep;
    public Slider sliderSleep;
    public Button start;
    public Button help;
    public Button about;


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
