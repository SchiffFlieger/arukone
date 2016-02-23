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
    public ComboBox chooseBoard;
    public CheckBox showSolving;
    public TextField txtSleepTime;
    public Slider sliderSleepTime;
    public Button btnStart;
    public Button btnHelp;
    public Button btnAbout;
    public Label lblSleepTime;
    public Label lblBoard;
    public TitledPane paneOptions;


    @Override
    public void initialize (URL location, ResourceBundle resources) {
        txtSleepTime.textProperty().bindBidirectional(sliderSleepTime.valueProperty(), new NumberStringConverter("#### ms"));

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
        chooseBoard.setItems(list);
        if (!list.isEmpty()) {
            chooseBoard.setValue(list.get(0));
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

    public void toggleSolvingOptions (ActionEvent actionEvent) {
        if (showSolving.isSelected()) {
            paneOptions.setDisable(false);
        } else {
            paneOptions.setDisable(true);
        }
    }
}
