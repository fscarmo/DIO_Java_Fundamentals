package br.dev.fscarmo.sudoku.ui;


import javafx.scene.control.Alert;


public final class Popup {


    public Popup info() {
        return new Popup(Alert.AlertType.INFORMATION);
    }

    public static Popup warning() {
        return new Popup(Alert.AlertType.WARNING);
    }

    public static Popup error() {
        return new Popup(Alert.AlertType.ERROR);
    }


    private final Alert.AlertType type;


    private Popup(Alert.AlertType type) {
        this.type = type;
    }


    public void show(String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
