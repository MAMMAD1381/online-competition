package UIAndControllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.SceneController;
import main.Server;

import java.io.IOException;
import java.util.ArrayList;

public class MainMenu extends Parent {

    private double START_SECTION_WIDTH = 0.3;
    private double USERS_SECTION_WIDTH = 0.7;


    private static ListView<String> listUser;
    private Button btnStart;
    public MainMenu(){
        HBox main = new HBox();
        main.setMinWidth(CONSTANTS.WIDTH);
        main.setMinHeight(CONSTANTS.HEIGHT);
        main.setAlignment(Pos.CENTER);

        VBox sectionStart = new VBox();
        btnStart = new Button("start test!");
        sectionStart.setMinWidth(START_SECTION_WIDTH * CONSTANTS.WIDTH);
        sectionStart.setMinHeight(CONSTANTS.HEIGHT);
        sectionStart.setAlignment(Pos.CENTER);
        sectionStart.getChildren().add(btnStart);



        VBox sectionUsers = new VBox();

        HBox sectionUsersBtn = new HBox();
        Button btnOnlineUsers = new Button("online users");
        Button btnOfflineUsers = new Button("offline users");
        Button btnRefresh = new Button("refresh");
        sectionUsersBtn.setAlignment(Pos.TOP_CENTER);
        sectionUsersBtn.setSpacing(20);
        sectionUsersBtn.getChildren().addAll(btnOnlineUsers,btnOfflineUsers,btnRefresh);

        HBox sectionUsersList = new HBox();
        listUser = new ListView<>();
        listUser.setMinWidth(USERS_SECTION_WIDTH * CONSTANTS.WIDTH);
        sectionUsersList.getChildren().addAll(listUser);
        sectionUsersList.setAlignment(Pos.TOP_CENTER);

        sectionUsers.setMinWidth(USERS_SECTION_WIDTH * CONSTANTS.WIDTH);
        sectionUsers.setMinHeight(CONSTANTS.HEIGHT);
        sectionUsers.setAlignment(Pos.TOP_CENTER);
        sectionUsers.getChildren().addAll(sectionUsersBtn,sectionUsersList);



        main.getChildren().addAll(sectionUsers,sectionStart);
        this.getChildren().addAll(main);


        listeners();
//        updateUsers();
    }

    public static void updateUsers(ArrayList<String> users) {
        listUser.getItems().clear();
        for (String user:users) {

            listUser.getItems().add(user);
        }
    }

    private void listeners() {
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Server.showScoreBoard();
                SceneController.switchScene(new Observation());
            }
        });
    }


}
