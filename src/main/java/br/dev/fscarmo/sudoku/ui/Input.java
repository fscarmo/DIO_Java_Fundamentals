package br.dev.fscarmo.sudoku.ui;


import br.dev.fscarmo.sudoku.controllers.SpaceController;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public abstract class Input {


    public static TextField createNew(SpaceController controller) {
        var input = new TextField();

        VBox.setVgrow(input, Priority.ALWAYS);
        Actions.addFireGuess(controller, input);

        input.setAlignment(Pos.CENTER);
        input.setMaxWidth(Double.MAX_VALUE);
        input.setMaxHeight(Double.MAX_VALUE);

        input.setStyle(
                """
                -fx-background-radius: 0;
                -fx-border-radius: 0;
                -fx-font-size: 16;
                """
        );

        input.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().matches("[0-9]*")) {
                return change;
            }
            return null;
        }));

        return input;
    }
}
