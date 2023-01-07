package UIAndControllers.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.Server;

import java.io.IOException;

public class mainMenuController {
    private Server server;
    private int port = 9090;
    public mainMenuController() throws IOException {
        setServer( new Server(port));
    }

    private Server getServer() {
        return server;
    }

    private void setServer(Server server) {
        this.server = server;
    }

    @FXML
    private void btnOnlineUsers(ActionEvent e){

    }

    @FXML
    private void btnOfflineUsers(ActionEvent e){

    }

    @FXML
    private void btnOn(ActionEvent e){

    }

    @FXML
    private void btnStart(ActionEvent e){

    }
}
