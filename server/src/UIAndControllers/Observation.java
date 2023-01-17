package UIAndControllers;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Observation extends Parent {

    public Observation(){
        HBox main = new HBox();
        main.setMinWidth(CONSTANTS.WIDTH);
        main.setMinHeight(CONSTANTS.HEIGHT);
        main.setAlignment(Pos.TOP_CENTER);

        VBox sectionList = new VBox();
        Label labelUsers = new Label("users status");
        ListView<String> statusList = new ListView<>();
        statusList.setMinWidth(CONSTANTS.WIDTH * 0.9);
        sectionList.setAlignment(Pos.TOP_CENTER);
        sectionList.getChildren().addAll(labelUsers,statusList);

        main.getChildren().add(sectionList);
        this.getChildren().add(main);

    }
}
