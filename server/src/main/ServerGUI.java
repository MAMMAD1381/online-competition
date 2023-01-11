package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class ServerGUI extends Application implements Runnable {
    @Override
    public void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        new SceneController(stage);

    }
}
