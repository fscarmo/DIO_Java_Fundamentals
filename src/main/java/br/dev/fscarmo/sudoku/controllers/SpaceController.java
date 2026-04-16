package br.dev.fscarmo.sudoku.controllers;


import br.dev.fscarmo.sudoku.game.Space;
import br.dev.fscarmo.sudoku.ui.Input;
import br.dev.fscarmo.sudoku.ui.Text;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;


public class SpaceController {


    @FXML
    private VBox vboxContainer;

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
            var number = space.getNumber();
            var text = Text.createNewText(number);
            vboxContainer.getChildren().add(text);
        } else {
            var input = Input.createNew(this);
            vboxContainer.getChildren().add(input);
        }
    }
}
