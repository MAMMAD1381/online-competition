package UIAndControllers.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import main.User;

import java.io.IOException;
import java.util.ArrayList;

public class mainMenuController {
    private User user;
    private ArrayList<Integer> serverPorts;
    private ArrayList<String> serverAddresses;
    private ArrayList<String> serverName;
    private int port = 9090;
    private String serverAddress = "127.0.0.1";
    @FXML
    ListView serverList = new ListView();
    @FXML
    AnchorPane anchorPane;


    public mainMenuController() throws IOException {
        serverPorts = new ArrayList<>();
        serverAddresses = new ArrayList<>();
        serverName = new ArrayList<>();
        serverName.add("server 1");
        serverAddresses.add("127.0.0.1");
        serverPorts.add(9090);
        serverList.getItems().add("sds");
//        anchorPane.getChildren().add(serverList);
    }

    private void checkSevers(){
        for (int i = 0; i < serverName.size(); i++) {

        }
    }

    private User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }
}
