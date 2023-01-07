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
    private void btnOn(ActionEvent e) throws IOException {
        if(isServerOn) {
            getServer().close();
            isServerOn = false;
            btnOn.setStyle("-fx-background-color: lightgreen");
            btnOn.setText("ON");
        }
        else {
            setServer( new Server(port));
            isServerOn = true;
            btnOn.setStyle("-fx-background-color: red");
            btnOn.setText("OFF");
        }
    }

    @FXML
    private void btnStart(ActionEvent e){

    }

    @FXML
    private void btnRefresh(ActionEvent e){

    }


}
