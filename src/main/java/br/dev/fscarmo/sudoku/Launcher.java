package br.dev.fscarmo.sudoku;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Launcher extends Application {


    static void main(String[] args) {
        launch(Launcher.class, args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("scene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 780, 520);

        stage.setTitle("Sudoku!");
        stage.setScene(scene);
        stage.show();
    }
}
