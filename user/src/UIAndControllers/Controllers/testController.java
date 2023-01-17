package UIAndControllers.Controllers;

import UIAndControllers.MainMenu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import main.User;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class testController implements Initializable {
    @FXML
    Label question;

    RadioButton option = new RadioButton();

    @FXML
    RadioButton option1;

    @FXML
    RadioButton option2;

    @FXML
    RadioButton option3;

    @FXML
    RadioButton option4;

    int testNumber=0;

    public testController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        while (testNumber< mainMenuController.user.getData().size()){

            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    option1.setText(mainMenuController.user.getData().get(testNumber).options[0]);
                    option2.setText(mainMenuController.user.getData().get(testNumber).options[1]);
                    option3.setText(mainMenuController.user.getData().get(testNumber).options[2]);
                    option4.setText(mainMenuController.user.getData().get(testNumber).options[3]);
                }
            }, 0, 1, TimeUnit.SECONDS);
            testNumber++;

        }
    }

}
