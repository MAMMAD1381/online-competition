import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class userController {
    @FXML
    private TextField fromServer;
    @FXML
    private TextField toServer;
    private User user;
    private int port = 9090;
    private String serverAddress = "127.0.0.1";


    public userController(){
        setUser(new User(port,serverAddress));
    }
    @FXML
    private void send(ActionEvent e) throws IOException {
        getUser().sendMessage(getToServer().getText());

        try {
            fromServer.setText(getUser().receiveMessage().toString());
        }
        catch (Exception exception){
            System.out.println("ERROR: " + exception.getMessage() + " "+ exception.getCause());
        }
    }

    private User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    private TextField getFromServer() {
        return fromServer;
    }

    private void setFromServer(TextField fromServer) {
        this.fromServer = fromServer;
    }

    private TextField getToServer() {
        return toServer;
    }

    private void setToServer(TextField toServer) {
        this.toServer = toServer;
    }
}
