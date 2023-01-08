package UIAndControllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private static Stage stage;
    public SceneController(Stage stage) {
        this.stage = stage;
        switchScene(new MainMenu());
        stage.show();
    }

    public static void switchScene(Parent parent){
        stage.setScene(new Scene(parent, CONSTANTS.WIDTH, CONSTANTS.HEIGHT));
    }
}
