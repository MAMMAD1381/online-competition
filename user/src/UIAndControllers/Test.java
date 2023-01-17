package UIAndControllers;

import UIAndControllers.Controllers.mainMenuController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    public Test(){

        HBox sectionHeader = new HBox();
        Label question = new Label("soal");
        Label questionNumber = new Label("question number");
        questionNumber.setMinWidth(0.2*CONSTANTS.WIDTH);
        sectionHeader.getChildren().addAll(questionNumber,question);
        sectionHeader.setAlignment(Pos.CENTER);

        HBox sectionQuestion1 = new HBox();
        Label option1 = new Label("1");
        radioOption1 = new RadioButton("option 1");
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

        startTest();
    }

    private void startTest() {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        while (testNumber< mainMenuController.user.getData().size()){

            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    radioOption1.setText(mainMenuController.user.getData().get(testNumber).options[0]);
                    radioOption2.setText(mainMenuController.user.getData().get(testNumber).options[1]);
                    radioOption3.setText(mainMenuController.user.getData().get(testNumber).options[2]);
                    radioOption4.setText(mainMenuController.user.getData().get(testNumber).options[3]);
                }
            }, 0, 1, TimeUnit.SECONDS);
            testNumber++;

        }
    }
}
