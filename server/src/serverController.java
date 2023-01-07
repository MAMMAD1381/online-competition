import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class serverController {
    @FXML
    private TextField fromUser;
    @FXML
    private TextField toUser;
    private Server server;
    private int port = 9090;

    public serverController() throws IOException {
        setServer( new Server(port));
    }
    public void send(ActionEvent event) throws IOException {
        getServer().sendMessage(toUser.getText());
        try {
            fromUser.setText(getServer().receiveMessage().toString());
        }
        catch (Exception exception){
            System.out.println("ERROR: " + exception.getMessage() +" "+exception.getCause());
        }
    }

    private Server getServer() {
        return server;
    }

    private void setServer(Server server) {
        this.server = server;
    }
}
