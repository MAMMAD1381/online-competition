package UIAndControllers.Controllers;

import main.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import main.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainMenuController implements Initializable {
    public static User user;
    private int port = 9090;
    private String serverAddress = "127.0.0.1";
    private String[] data = {"dsdsds","amir ","mmd"};
    @FXML
    ListView<String> serverList ;
    @FXML
    AnchorPane anchorPane;

    public mainMenuController() throws IOException {
        user = new User(port,serverAddress);
        Thread client=new Thread(user);
        client.start();
        System.out.println("start");
        checkSevers();
//        anchorPane.getChildren().add(serverList);
    }

    private void checkSevers(){

    }

    @FXML
    public void btnStart(ActionEvent e) throws IOException {
        SceneController.switchScene(FXMLLoader.load(getClass().getResource("../Fxmls/test.fxml")));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
