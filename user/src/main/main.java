package main;

import UIAndControllers.CONSTANTS;
import UIAndControllers.MainMenu;
import UIAndControllers.SceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {
    public static void main(String[] args) {
        Application.launch();

    }

    @Override
    public void start(Stage stage) throws Exception {
        new SceneController(stage);

    }
}