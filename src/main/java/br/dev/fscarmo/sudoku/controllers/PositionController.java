package br.dev.fscarmo.sudoku.controllers;


import br.dev.fscarmo.sudoku.game.Position;

import br.dev.fscarmo.sudoku.ui.Input;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class PositionController {


    @FXML
    private VBox vboxContainer;


    public void setPosition(Position position) {
        if (position == null)
            return;

        int positionRow = position.getGridRow();
        int positionCol = position.getGridCol();

        if (position.isSelected())
            vboxContainer.getChildren().add(new Label(String.valueOf(position.getNumber())));
        else
            vboxContainer.getChildren().add(Input.createNew(positionRow, positionCol));
    }
}
