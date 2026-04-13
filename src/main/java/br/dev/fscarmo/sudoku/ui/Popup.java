package br.dev.fscarmo.sudoku.ui;


import javafx.scene.control.Alert;


public final class Popup {


    public static Popup open(Alert.AlertType alertType) {
        return new Popup(alertType);
    }


    private final Alert.AlertType alertType;


    private Popup(Alert.AlertType alertType) {
        this.alertType = alertType;
    }


    public void show(String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
