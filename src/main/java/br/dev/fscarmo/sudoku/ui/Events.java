package br.dev.fscarmo.sudoku.ui;


import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public abstract class Events {


    public static void fireFocusToNext(Control control) {
        var event = new KeyEvent(
                KeyEvent.KEY_PRESSED, "", "",
                KeyCode.TAB, false, false, false, false
        );
        control.fireEvent(event);
    }
}
