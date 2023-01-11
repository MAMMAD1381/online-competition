package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {
    public static void main(String[] args) {
        Application.launch();

    }

    @Override
    public void start(Stage stage) throws Exception {
//        Parent parent = FXMLLoader.load(getClass().getResource("/UIAndControllers/Fxmls/mainMenu.fxml"));
//        Scene scene = new Scene(parent);
//        stage.setScene(scene);
//        stage.show();
        new SceneController(stage);

    }
}
