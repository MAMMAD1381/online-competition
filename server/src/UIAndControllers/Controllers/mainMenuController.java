package UIAndControllers.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.Server;

import java.io.IOException;

public class mainMenuController {
    @FXML
    private Button btnOn;

    private boolean isServerOn = false;

    private Server server;
    private int port = 9090;


    public mainMenuController() throws IOException {
//        server = new Server(port);
    }

    private Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    @FXML
    private void btnOnlineUsers(ActionEvent e){

    }

    @FXML
    private void btnOfflineUsers(ActionEvent e){

    }

    @FXML
    private void btnStart(ActionEvent e){
        if(Server.numberOfUsers > 3){
//            new SceneC
        }
    }

    @FXML
    private void btnRefresh(ActionEvent e){

    }


}

