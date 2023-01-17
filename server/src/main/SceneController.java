package main;

import UIAndControllers.CONSTANTS;
import UIAndControllers.MainMenu;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static Stage stage;

    public SceneController(Stage stage) throws IOException {
        this.stage = stage;
//        switchScene(FXMLLoader.load(getClass().getResource("/UiAndControllers/Fxmls/mainMenu.fxml")));
        switchScene(new MainMenu());
        stage.show();
    }

    public static void switchScene(Parent parent){
        stage.setScene(new Scene(parent, CONSTANTS.WIDTH, CONSTANTS.HEIGHT));
    }


}
