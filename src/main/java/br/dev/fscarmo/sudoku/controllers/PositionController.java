package br.dev.fscarmo.sudoku.controllers;


import br.dev.fscarmo.sudoku.game.Position;

import br.dev.fscarmo.sudoku.ui.PositionState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class PositionController implements Initializable {


    @FXML
    private VBox vboxContainer;

    @FXML
    private Label labelNumber;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vboxContainer.setOnMouseClicked(event -> {
            setFocus(true);
        });
    }


    public void setPosition(Position position) {
        if (position == null)
            return;

        boolean isSelected = position.isSelected();
        setSelection(isSelected);

        if (position.isSelected())
            labelNumber.setText(String.valueOf(position.getNumber()));
    }


    public void setSelection(boolean isSelected) {
        vboxContainer.setStyle(
                isSelected ? "-fx-background-color: #86efac;" : "-fx-background-color: transparent;"
        );
    }


    public void setFocus(boolean isOn) {
        vboxContainer.setStyle(
                isOn ? "-fx-border-color: #0f172a;" : "-fx-border-color: transparent;"
        );
    }
}
