package de.dhbw.arukone.gui;

import de.dhbw.arukone.ArukoneBoard;
import de.dhbw.arukone.reader.BoardReader;
import javafx.collections.ObservableList;
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
    public TextField fieldSleep;
    public Slider sliderSleep;
    public Button start;
    public Button help;
    public Button about;


    @Override
    public void initialize (URL location, ResourceBundle resources) {
        fieldSleep.textProperty().bindBidirectional(sliderSleep.valueProperty(), new NumberStringConverter("#### ms"));

        ObservableList<ArukoneBoard> list = new BoardReader().readAllBoardsInDirectory("res/boards");
        list.sort((o1, o2) -> {
            if (o1.getSize() > o2.getSize()) {
                return 1;
            } else if (o1.getSize() < o2.getSize()) {
                return -1;
            } else {
                return o1.toString().compareTo(o2.toString());
            }
        });
        chooseField.setItems(list);
        if (!list.isEmpty()) {
            chooseField.setValue(list.get(0));
        }
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
