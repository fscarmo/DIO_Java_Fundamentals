package br.dev.fscarmo.sudoku.ui;


import javafx.scene.layout.GridPane;


public class Grid {


    public static GridPane createNewBlock(final double gap) {
        var block = new GridPane();

        block.setHgap(gap);
        block.setVgap(gap);

        block.setStyle(
                """
                -fx-border-color: #334155;
                -fx-border-width: 1;
                -fx-padding: 3;
                """
        );

        return block;
    }
}
