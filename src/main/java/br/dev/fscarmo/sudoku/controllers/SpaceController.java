package br.dev.fscarmo.sudoku.controllers;


import br.dev.fscarmo.sudoku.game.Space;
import br.dev.fscarmo.sudoku.ui.Input;
import br.dev.fscarmo.sudoku.ui.Text;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class SpaceController {


    @FXML
    private VBox vboxContainer;

    private TextField input;
    private Label text;
    private Space space;


    public Space getSpace() {
        return space;
    }


    public void setSpace(Space space) {
        if (space == null) {
            return;
        }
        this.space = space;
    }


    public void load() {
        vboxContainer.getChildren().clear();
        if (space.isSelected()) {
            input = null;
            text = Text.createNewText(space.getNumber());
            vboxContainer.getChildren().add(text);
        } else {
            text = null;
            input = Input.createNew(this);
            vboxContainer.getChildren().add(input);
        }
    }


    public void lock() {
        if (input != null) {
            input.setDisable(true);
        } else if (text != null) {
            text.setText(null);
        }
    }


    public void unlock() {
        if (input != null) {
            input.setDisable(false);
        } else if (text != null) {
            text.setText(String.valueOf(space.getNumber()));
        }
    }
}
