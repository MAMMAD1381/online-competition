package UIAndControllers.Controllers;

import main.User;

public class mainMenuController {
    private User user;
    private int port = 9090;
    private String serverAddress = "127.0.0.1";


    public mainMenuController(){
        setUser(new User(port,serverAddress));
    }

    private User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }
}
