package br.dev.fscarmo.sudoku.controllers;


import br.dev.fscarmo.sudoku.game.Position;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class PositionController {


    @FXML
    private Label labelNumber;


    public void setPosition(Position position) {
        if (position == null)
            return;
        if (position.isSelected()) {
            labelNumber.setText(String.valueOf(position.getNumber()));
        }
    }
}
