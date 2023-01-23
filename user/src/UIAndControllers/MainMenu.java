package UIAndControllers;

import javafx.application.Platform;
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

import java.util.ArrayList;

public class MainMenu extends Parent {
    public static User user;
    private static TextArea chats;
    private TextArea currentChat;
    private ListView listUser;
    private ListView listFriends;

    public MainMenu(){
        user=new User(9090,"127.0.0.1");

        AnchorPane anchorPane = new AnchorPane();
        VBox sectionFriends = new VBox();
        VBox sectionChat = new VBox();
        VBox sectionUser = new VBox();

        Label labelFriends = new Label();
        Label labelChat = new Label();
        Label labelUser = new Label();
        listFriends = new ListView();
        chats = new TextArea();
        chats.setEditable(false);
        currentChat = new TextArea();
        Button btnSend = new Button("send");
        listUser = new ListView();
        labelFriends.setText("friends:");
        labelChat.setText("chat:");
        labelUser.setText("me");
        Button buttonRefresh = new Button("refresh");


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
        sectionFriends.getChildren().addAll(labelFriends,listFriends,buttonRefresh);
        sectionChat.getChildren().addAll(labelChat,currentChat,chats,btnSend);
        sectionUser.getChildren().addAll(labelUser,listUser,buttonStart);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(sectionFriends,sectionChat,sectionUser);

        anchorPane.getChildren().add(hBox);
        anchorPane.setMaxSize(CONSTANTS.WIDTH,CONSTANTS.HEIGHT);

        this.getChildren().add(anchorPane);

        listeners(buttonStart,btnSend,buttonRefresh);

    }

    public void usersMessage(String username, String message){

    }

    private void listeners(Button buttonStart, Button buttonSend ,Button buttonRefresh){
        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                SceneController.switchScene(new Test());
                //user.receiveMessage();
            }
        });

        buttonSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                user.sendPM(listFriends.getSelectionModel().getSelectedItems().get(0).toString(),currentChat.getText());
                chats.setText(chats.getText() + "you:\n" + currentChat.getText() + "\n");
            }
        });

        buttonRefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                user.sendMessage("Friends");
                user.receiveMessage();
                listFriends.getItems().clear();
                for (String friendName:user.getFriends()) {

                    listFriends.getItems().add(friendName);
                }
            }
        });
    }

    public static void updateChat(String username ,String msg){
        chats.setText(username+":\t" + msg);
    }
}
