package UIAndControllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.SceneController;

public class MainMenu extends Parent {
    public MainMenu(){
        AnchorPane anchorPane = new AnchorPane();
        VBox sectionFriends = new VBox();
        VBox sectionServers = new VBox();
        VBox sectionUser = new VBox();

        Label labelFriends = new Label();
        Label labelServers = new Label();
        Label labelUser = new Label();
        ListView listFriends = new ListView();
        ListView listServers = new ListView();
        ListView listUser = new ListView();
        labelFriends.setText("friends:");
        labelServers.setText("servers:");
        labelUser.setText("me");


        sectionFriends.setMaxWidth(CONSTANTS.WIDTH/3);
        sectionServers.setMaxWidth(CONSTANTS.WIDTH/3);
        sectionUser.setMaxWidth(CONSTANTS.WIDTH/3);

        sectionFriends.setMaxHeight(CONSTANTS.HEIGHT);
        sectionServers.setMaxHeight(CONSTANTS.HEIGHT);
        sectionUser.setMaxHeight(CONSTANTS.HEIGHT);

        sectionFriends.setAlignment(Pos.CENTER);
        sectionServers.setAlignment(Pos.CENTER);
        sectionUser.setAlignment(Pos.CENTER);

        Button buttonStart = new Button();
        buttonStart.setText("start");
        buttonStart.setPadding(new Insets(5));
        buttonStart.setMaxWidth(80);
        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneController.switchScene(new Test());
            }
        });


        sectionFriends.setPadding(new Insets(5));
        sectionServers.setPadding(new Insets(5));
        sectionUser.setPadding(new Insets(5));
        sectionFriends.getChildren().addAll(labelFriends,listFriends);
        sectionServers.getChildren().addAll(labelServers,listServers);
        sectionUser.getChildren().addAll(labelUser,listUser,buttonStart);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(sectionFriends,sectionServers,sectionUser);

        anchorPane.getChildren().add(hBox);
        anchorPane.setMaxSize(CONSTANTS.WIDTH,CONSTANTS.HEIGHT);

        this.getChildren().add(anchorPane);

    }
}
