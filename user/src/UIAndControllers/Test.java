package UIAndControllers;

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

public class Test extends Parent {

    private static double HEADER_HEIGHT = 0.2;
    private static double OPTIONS_HEIGHT = 0.6;
    private static double PROGRESSBAR_HEIGHT = 0.2;
    public Test(){

        HBox sectionHeader = new HBox();
        Label question = new Label("soal 1");
        Label questionNumber = new Label("1");
        questionNumber.setMinWidth(0.2*CONSTANTS.WIDTH);
        sectionHeader.getChildren().addAll(questionNumber,question);
        sectionHeader.setAlignment(Pos.CENTER);

        HBox sectionQuestion1 = new HBox();
        Label option1 = new Label("1");
        RadioButton radioOption1 = new RadioButton("option 1");
        sectionQuestion1.getChildren().addAll(option1,radioOption1);

        HBox sectionQuestion2 = new HBox();
        Label option2 = new Label("2");
        RadioButton radioOption2 = new RadioButton("option 2");
        sectionQuestion2.getChildren().addAll(option2,radioOption2);

        HBox sectionQuestion3 = new HBox();
        Label option3 = new Label("3");
        RadioButton radioOption3 = new RadioButton("option 3");
        sectionQuestion3.getChildren().addAll(option3,radioOption3);

        HBox sectionQuestion4 = new HBox();
        Label option4 = new Label("4");
        RadioButton radioOption4 = new RadioButton("option 4");
        sectionQuestion4.getChildren().addAll(option4,radioOption4);

        ProgressBar progressBar = new ProgressBar();
//
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
//
        VBox main = new VBox();
        headerBox.setMinHeight(Test.HEADER_HEIGHT * (CONSTANTS.HEIGHT - progressBar.getHeight()));
        headerBox.setMinWidth(CONSTANTS.WIDTH);
        options.setMinHeight(Test.OPTIONS_HEIGHT * (CONSTANTS.HEIGHT - progressBar.getHeight()));
        options.setMinWidth(CONSTANTS.WIDTH);
        progressBarBox.setMinWidth(CONSTANTS.WIDTH);
//        progressBarBox.setMinHeight(Test.OPTIONS_HEIGHT * CONSTANTS.HEIGHT);
        main.getChildren().addAll(headerBox,new Separator(Orientation.HORIZONTAL),options,new Separator(Orientation.HORIZONTAL),progressBarBox);
        main.setAlignment(Pos.CENTER);
//        main.setAlignment(Pos.CENTER);
//        main.setMaxHeight(CONSTANTS.HEIGHT);
//        main.setMaxWidth(CONSTANTS.WIDTH);
//

        this.getChildren().add(main);
//        AnchorPane anchorPane = new AnchorPane();
//        anchorPane.getChildren().add(main);
//        anchorPane.setMaxWidth(CONSTANTS.WIDTH);
//        anchorPane.setMaxHeight(CONSTANTS.HEIGHT);
//        anchorPane.setMaxSize(CONSTANTS.WIDTH,CONSTANTS.HEIGHT);
//
//        this.getChildren().addAll(anchorPane);



    }
}
