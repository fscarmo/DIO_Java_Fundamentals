package br.dev.fscarmo.sudoku.ui;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public abstract class Text {


    public static Label createNewText(Object value) {
        var textValue = String.valueOf(value);
        var text = new Label(textValue);

        text.setAlignment(Pos.CENTER);
        text.setMaxWidth(Double.MAX_VALUE);
        text.setMaxHeight(Double.MAX_VALUE);

        text.setStyle(
                """
                -fx-background-color: #6ee7b7;
                -fx-color: #064e3b;
                -fx-font-weight: bold;
                -fx-font-size: 16;
                """
        );

        VBox.setVgrow(text, Priority.ALWAYS);

        return text;
    }
}
