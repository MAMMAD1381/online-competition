import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class userController {
    @FXML
    private TextField serverAnswer;
    @FXML
    private TextField userQuestion;
    private User user;


    public userController(){
        int port = 9090;
        String serverAddress = "127.0.0.1";
//        setUser(new User(port,serverAddress));
    }
    @FXML
    private void send(ActionEvent e){
        String message = getUserQuestion().getText();
        getServerAnswer().setText(message);
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
