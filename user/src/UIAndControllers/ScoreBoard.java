package UIAndControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class ScoreBoard extends Parent {
    private TableView<Person> tableView;
    private double headerHeight = 0.1;

    static ArrayList<Person> personArrayList;

    public ScoreBoard(){


        VBox main = new VBox();
        main.setMinHeight(CONSTANTS.HEIGHT);
        main.setMinWidth(CONSTANTS.WIDTH);

        HBox sectionHeader = new HBox();
        HBox sectionScoreBoard = new HBox();

        Label labelScoreBoard = new Label("Score board");
        sectionHeader.getChildren().add(labelScoreBoard);
        sectionHeader.setAlignment(Pos.CENTER);
        sectionHeader.setMinHeight(headerHeight * CONSTANTS.HEIGHT);


        TableColumn<Person,String> nameColumn=new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Person, Integer> scoreColumn=new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));
        tableView=new TableView<>();
        tableView.setMinWidth(CONSTANTS.WIDTH);
        tableView.setItems(getList(personArrayList)); //todo here we add users list for score board
        tableView.getColumns().addAll(nameColumn,scoreColumn);
        sectionScoreBoard.getChildren().add(tableView);
        sectionScoreBoard.setMinHeight((1-headerHeight) * CONSTANTS.HEIGHT);
        sectionScoreBoard.setMinWidth(CONSTANTS.WIDTH);
        sectionHeader.setAlignment(Pos.CENTER);

        main.getChildren().addAll(sectionHeader,sectionScoreBoard);
        this.getChildren().add(main);
    }

    public static void setList(ArrayList<Person> personArrayList) {
        ScoreBoard.personArrayList = personArrayList;
    }


    public ObservableList<Person> getList(ArrayList<Person> users){
        ObservableList<Person> list= FXCollections.observableArrayList();

        for (Person person:users) {
            list.add(new Person(person.name,person.score));
        }
        return list;
    }
}

