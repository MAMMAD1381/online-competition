import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class userController {
    private User user;
    private int port = 9090;
    private String serverAddress = "127.0.0.1";


    public userController(){
        setUser(new User(port,serverAddress));
    }

    private User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }
}
