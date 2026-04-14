module br.dev.fscarmo.sudoku {

    requires javafx.controls;
    requires javafx.fxml;

    opens br.dev.fscarmo.sudoku to javafx.fxml;
    exports br.dev.fscarmo.sudoku;

    opens br.dev.fscarmo.sudoku.controllers to javafx.fxml;
    exports br.dev.fscarmo.sudoku.controllers;

    opens br.dev.fscarmo.sudoku.game to javafx.fxml;
    exports br.dev.fscarmo.sudoku.game;

    opens br.dev.fscarmo.sudoku.ui to javafx.fxml;
    exports br.dev.fscarmo.sudoku.ui;
}