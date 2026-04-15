package br.dev.fscarmo.sudoku.ui;


import br.dev.fscarmo.sudoku.controllers.SpaceController;
import br.dev.fscarmo.sudoku.game.Game;

import javafx.scene.control.TextField;


public abstract class Actions {


    public static void addFireGuess(
            SpaceController controller,
            TextField input,
            final int row,
            final int col
    ) {
        final Game game = Game.getInstance();
        input.setOnAction(_ -> {
            try {
                var space = game.getGridSpace(row, col);
                var number = Integer.parseInt(input.getText());
                space.guessTheNumber(number);
                controller.refresh();
            } catch (IllegalArgumentException e) {
                Popup.warning().show("Palpite incorreto", e.getMessage());
                input.clear();
                game.increaseErrors();
            } finally {
                game.checkStatus();
            }
        });
    }
}
