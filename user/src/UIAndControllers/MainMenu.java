package UIAndControllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.SceneController;
import main.User;

public class MainMenu extends Parent {
    public static User user;
    private TextArea chats;
    private TextArea currentChat;

    public MainMenu(){
        user=new User(9090,"127.0.0.1");

        AnchorPane anchorPane = new AnchorPane();
        VBox sectionFriends = new VBox();
        VBox sectionChat = new VBox();
        VBox sectionUser = new VBox();

        Label labelFriends = new Label();
        Label labelChat = new Label();
        Label labelUser = new Label();
        ListView listFriends = new ListView();
        chats = new TextArea();
        chats.setEditable(false);
        currentChat = new TextArea();
        Button btnSend = new Button("send");
        ListView listUser = new ListView();
        labelFriends.setText("friends:");
        labelChat.setText("chat:");
        labelUser.setText("me");


        sectionFriends.setMaxWidth(CONSTANTS.WIDTH/3);
        sectionChat.setMaxWidth(CONSTANTS.WIDTH/3);
        sectionUser.setMaxWidth(CONSTANTS.WIDTH/3);

        sectionFriends.setMaxHeight(CONSTANTS.HEIGHT);
        sectionChat.setMaxHeight(CONSTANTS.HEIGHT);
        sectionUser.setMaxHeight(CONSTANTS.HEIGHT);

        sectionFriends.setAlignment(Pos.CENTER);
        sectionChat.setAlignment(Pos.CENTER);
        sectionUser.setAlignment(Pos.CENTER);

        Button buttonStart = new Button();
        buttonStart.setText("start");
        buttonStart.setPadding(new Insets(5));
        buttonStart.setMaxWidth(80);



        sectionFriends.setPadding(new Insets(5));
        sectionChat.setPadding(new Insets(5));
        sectionUser.setPadding(new Insets(5));
        sectionFriends.getChildren().addAll(labelFriends,listFriends);
        sectionChat.getChildren().addAll(labelChat,currentChat,chats,btnSend);
        sectionUser.getChildren().addAll(labelUser,listUser,buttonStart);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(sectionFriends,sectionChat,sectionUser);

        anchorPane.getChildren().add(hBox);
        anchorPane.setMaxSize(CONSTANTS.WIDTH,CONSTANTS.HEIGHT);

        this.getChildren().add(anchorPane);

        listeners(buttonStart,btnSend);

    }

    public void usersMessage(String username, String message){

    }

    private void listeners(Button buttonStart, Button btnSend){
        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneController.switchScene(new Test());
            }
        });

        btnSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chats.setText(chats.getText() + "you:\n" + currentChat.getText() + "\n");
            }
        });
    }
}
