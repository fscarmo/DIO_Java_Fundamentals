package br.dev.fscarmo.sudoku.controllers;


import br.dev.fscarmo.sudoku.game.Position;

import br.dev.fscarmo.sudoku.ui.Input;
import br.dev.fscarmo.sudoku.ui.PositionState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class PositionController implements Initializable {


    @FXML
    private VBox vboxContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vboxContainer.setOnMouseClicked(event -> {
            setFocus(true);
        });
    }


    public void setPosition(Position position) {
        if (position == null)
            return;

        int positionRow = position.getGridRow();
        int positionCol = position.getGridCol();
        boolean isSelected = position.isSelected();
        setSelection(isSelected);

        if (position.isSelected())
            vboxContainer.getChildren().add(new Label(String.valueOf(position.getNumber())));
        else
            vboxContainer.getChildren().add(Input.createNew(positionRow, positionCol));
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
