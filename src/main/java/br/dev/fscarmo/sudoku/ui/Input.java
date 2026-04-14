package br.dev.fscarmo.sudoku.ui;


import br.dev.fscarmo.sudoku.game.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public abstract class Input {


    public static TextField createNew(int row, int col) {
        TextField input = new TextField();

        input.setStyle("-fx-background-radius: 0; -fx-border-radius: 0;");
        input.setAlignment(Pos.CENTER);
        input.setMaxWidth(Double.MAX_VALUE);
        input.setMaxHeight(Double.MAX_VALUE);

        addAction(input, row, col);
        VBox.setVgrow(input, Priority.ALWAYS);

        return input;
    }


    public static void addAction(final TextField input, int row, int col) {
        input.setOnAction(_ -> {
            try {
                var position = Game.getInstance().getPositionFromGrid(row, col);
                int number = Integer.parseInt(input.getText());
                position.guessTheNumber(number);
                tabToNext(input);
            } catch (IllegalArgumentException e) {
                Popup.open(Alert.AlertType.ERROR).show("Ops", e.getMessage());
                input.clear();
            }
        });
    }


    public static void tabToNext(TextField input) {
        var event = new KeyEvent(
                KeyEvent.KEY_PRESSED, "", "",
                KeyCode.TAB, false, false, false, false
        );
        input.fireEvent(event);
    }
}
