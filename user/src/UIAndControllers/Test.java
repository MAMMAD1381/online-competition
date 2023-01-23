package UIAndControllers;

import UIAndControllers.Controllers.mainMenuController;
import com.sun.tools.javac.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.SceneController;
import main.User;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test extends Parent {

    private static double HEADER_HEIGHT = 0.2;
    private static double OPTIONS_HEIGHT = 0.6;
    private static double PROGRESSBAR_HEIGHT = 0.2;
    private int testNumber = 0;
    private RadioButton radioOption1;
    private RadioButton radioOption2;
    private RadioButton radioOption3;
    private RadioButton radioOption4;
    private Label question;
    private Label questionNumber;

    public Test(){

        HBox sectionHeader = new HBox();
        question = new Label("soal");
        questionNumber = new Label("question number");
        questionNumber.setMinWidth(0.2*CONSTANTS.WIDTH);
        sectionHeader.getChildren().addAll(question,questionNumber);
        sectionHeader.setAlignment(Pos.CENTER_RIGHT);
        sectionHeader.setSpacing(10);

        HBox sectionQuestion1 = new HBox();
        Label option1 = new Label("1");
        radioOption1 = new RadioButton("option 1");
        radioOption1.setSelected(true);
        sectionQuestion1.getChildren().addAll(radioOption1,option1);
        sectionQuestion1.setAlignment(Pos.TOP_RIGHT);
        sectionQuestion1.setSpacing(30);
        sectionQuestion1.setPadding(new Insets(0,20,0,0));

        HBox sectionQuestion2 = new HBox();
        Label option2 = new Label("2");
        radioOption2 = new RadioButton("option 2");
        sectionQuestion2.getChildren().addAll(radioOption2,option2);
        sectionQuestion2.setAlignment(Pos.TOP_RIGHT);
        sectionQuestion2.setSpacing(30);
        sectionQuestion2.setPadding(new Insets(0,20,0,0));

        HBox sectionQuestion3 = new HBox();
        Label option3 = new Label("3");
        radioOption3 = new RadioButton("option 3");
        sectionQuestion3.getChildren().addAll(radioOption3,option3);
        sectionQuestion3.setAlignment(Pos.TOP_RIGHT);
        sectionQuestion3.setSpacing(30);
        sectionQuestion3.setPadding(new Insets(0,20,0,0));

        HBox sectionQuestion4 = new HBox();
        Label option4 = new Label("4");
        radioOption4 = new RadioButton("option 4");
        sectionQuestion4.getChildren().addAll(radioOption4,option4);
        sectionQuestion4.setAlignment(Pos.TOP_RIGHT);
        sectionQuestion4.setSpacing(30);
        sectionQuestion4.setPadding(new Insets(0,20,0,0));

        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(0.0);
        progressBar.setMinWidth(CONSTANTS.WIDTH * 0.98);

        VBox headerBox = new VBox();
        headerBox.getChildren().add(sectionHeader);
        headerBox.setAlignment(Pos.CENTER);

        VBox options = new VBox();
        options.getChildren().addAll(sectionQuestion1,sectionQuestion2,sectionQuestion3,sectionQuestion4);
        options.setAlignment(Pos.CENTER);
        options.setSpacing(50);


        VBox progressBarBox = new VBox();
        progressBarBox.getChildren().add(progressBar);
        progressBarBox.setAlignment(Pos.CENTER);

        VBox main = new VBox();
        headerBox.setMinHeight(Test.HEADER_HEIGHT * (CONSTANTS.HEIGHT - progressBar.getHeight()));
        headerBox.setMinWidth(CONSTANTS.WIDTH);
        options.setMinHeight(Test.OPTIONS_HEIGHT * (CONSTANTS.HEIGHT - progressBar.getHeight()));
        options.setMinWidth(CONSTANTS.WIDTH);
        progressBarBox.setMinWidth(CONSTANTS.WIDTH);
        main.getChildren().addAll(headerBox,new Separator(Orientation.HORIZONTAL),options,new Separator(Orientation.HORIZONTAL),progressBarBox);
        main.setAlignment(Pos.CENTER);
        this.getChildren().add(main);

        listeners();
        startTest();
    }

    private void listeners() {
        radioOption1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                radioOption2.setSelected(false);
                radioOption3.setSelected(false);
                radioOption4.setSelected(false);
            }
        });
        radioOption2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                radioOption1.setSelected(false);
                radioOption3.setSelected(false);
                radioOption4.setSelected(false);
            }
        });
        radioOption3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                radioOption2.setSelected(false);
                radioOption1.setSelected(false);
                radioOption4.setSelected(false);
            }
        });
        radioOption4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                radioOption2.setSelected(false);
                radioOption3.setSelected(false);
                radioOption1.setSelected(false);
            }
        });
    }

    private void startTest() {

        MainMenu.user.sendMessage("test");// receiving test question from server
        MainMenu.user.receiveMessage();

        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if (testNumber >= MainMenu.user.getData().size()) {
                    t.cancel();
                    t.purge();
                    return;
                }
                Platform.runLater(() -> {
                    questionNumber.setText("."+(testNumber+1));
                    question.setText(MainMenu.user.getData().get(testNumber).question);
                    radioOption1.setText(MainMenu.user.getData().get(testNumber).options[0]);
                    radioOption2.setText(MainMenu.user.getData().get(testNumber).options[1]);
                    radioOption3.setText(MainMenu.user.getData().get(testNumber).options[2]);
                    radioOption4.setText(MainMenu.user.getData().get(testNumber).options[3]);
                            testNumber++;
                }


                );
                while (!MainMenu.user.receiveMessage().equals("answer")){

                }
                String selectedOption = "0";
                while (selectedOption.equals("0")) {
                    if (radioOption1.isSelected())
                        selectedOption = "1";
                    else if (radioOption2.isSelected())
                        selectedOption = "2";
                    else if (radioOption3.isSelected())
                        selectedOption = "3";
                    else if (radioOption4.isSelected())
                        selectedOption = "4";
//                    else{
//                        Alert alert = new Alert(Alert.AlertType.ERROR,"pls select an option",ButtonType.OK);
//                        alert.show();
//                    }
                }
                MainMenu.user.sendMessage(selectedOption);
                MainMenu.user.receiveMessage();
                //SceneController.switchScene(new ScoreBoard());

            };
        };
        t.schedule(tt, new Date(),3000);



    }
}
