import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class userController {
    @FXML
    private TextField serverAnswer;
    @FXML
    private TextField userQuestion;
    private User user;
    private int port = 9090;
    private String serverAddress = "127.0.0.1";


    public userController(){
        setUser(new User(port,serverAddress));
    }
    @FXML
    private void send(ActionEvent e) throws IOException {
        getUser().sendMessage(getUserQuestion().getText());

        try {
            //todo receive message here
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

    private TextField getServerAnswer() {
        return serverAnswer;
    }

    private void setServerAnswer(TextField serverAnswer) {
        this.serverAnswer = serverAnswer;
    }

    private TextField getUserQuestion() {
        return userQuestion;
    }

    private void setUserQuestion(TextField userQuestion) {
        this.userQuestion = userQuestion;
    }
}
