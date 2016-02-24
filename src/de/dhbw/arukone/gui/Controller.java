package de.dhbw.arukone.gui;

import de.dhbw.arukone.ArukoneBoard;
import de.dhbw.arukone.reader.BoardReader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    public TitledPane paneOptions;
    public ComboBox chooseBoard;
    public CheckBox showSolving;
    public TextField txtSleepTime;
    public Slider sliderSleepTime;
    public Button btnStart;
    public Button btnHelp;
    public Button btnAbout;
    public Label lblSleepTime;
    public Label lblBoard;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        fillComboBoxWithBoards();
        onlyNumericInputOnSleepTimeTextField();
        bindTextFieldToSlider();
    }

    public void handleStart (ActionEvent actionEvent) {
        System.out.println(" -- start");
        ArukoneBoard board = (ArukoneBoard) chooseBoard.getSelectionModel().getSelectedItem();
        System.out.println(board.deepToString());

        new SolverGUI(5, 5, 50);
    }

    public void handleHelp (ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.setContentText("content dummy");
        alert.showAndWait();
    }

    public void handleAbout (ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("content dummy");
        alert.showAndWait();
    }

    public void toggleSolvingOptions (ActionEvent actionEvent) {
        if (showSolving.isSelected()) {
            paneOptions.setDisable(false);
        } else {
            paneOptions.setDisable(true);
        }
    }

    private void bindTextFieldToSlider() {
        txtSleepTime.textProperty().bindBidirectional(sliderSleepTime.valueProperty(), new NumberStringConverter("####"));
    }

    private void fillComboBoxWithBoards() {
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

    private void onlyNumericInputOnSleepTimeTextField() {
        txtSleepTime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("[0-9]*")) {
                    if (newValue.equals("")) {
                        txtSleepTime.textProperty().setValue("0");
                        return;
                    }
                    int value = Integer.parseInt(newValue);
                    if (value < 0) {
                        txtSleepTime.textProperty().setValue("0");
                    } else if (value > 3000) {
                        txtSleepTime.textProperty().setValue("3000");
                    } else {
                        txtSleepTime.textProperty().setValue(String.valueOf(value));
                    }
                } else {
                    txtSleepTime.textProperty().setValue(oldValue);
                }
            }
        });
    }
}
