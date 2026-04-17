package br.dev.fscarmo.sudoku.ui;


import javafx.scene.control.Alert;


public final class Popup {


    public static Popup warning() {
        return new Popup(Alert.AlertType.WARNING);
    }

    public static Popup error() {
        return new Popup(Alert.AlertType.ERROR);
    }

    public static Popup confirm(Runnable callback) {
        return new Popup(callback);
    }


    private final Alert.AlertType type;
    private final Runnable callback;


    private Popup(Alert.AlertType type) {
        this.type = type;
        this.callback = null;
    }


    private Popup(Runnable callback) {
        this.type = Alert.AlertType.INFORMATION;
        this.callback = callback;
    }


    public void show(String title, String message) {
        Alert alert = new Alert(type);
        alert.setOnHidden(_ -> {
            if (callback == null) return;
            callback.run();
        });
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
