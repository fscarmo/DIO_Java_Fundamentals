package br.dev.fscarmo.sudoku.ui;


import br.dev.fscarmo.sudoku.controllers.SpaceController;

import br.dev.fscarmo.sudoku.game.Game;
import javafx.scene.control.TextField;


public abstract class Actions {


    public static void addFireGuess(SpaceController controller, TextField input) {
        input.setOnAction(_ -> {
            var value = input.getText();

            if (!Game.current().isRunning()) {
                Popup.warning().show("Wops!", "Aperte \"Play\" para iniciar o jogo!");
                return;
            }

            if (value == null || value.isEmpty()) {
                return;
            }

            try {
                var space = controller.getSpace();
                var number = Integer.parseInt(input.getText());
                space.guessTheNumber(number);
                controller.load();
            } catch (IllegalArgumentException e) {
                Popup.warning().show("Palpite incorreto", e.getMessage());
                input.clear();
                Game.current().increaseErrors();
            } finally {
                Game.current().checkStatus();
            }
        });
    }
}
