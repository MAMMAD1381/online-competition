package UIAndControllers.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Cell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class scoreBoardController {

    @FXML
    private ListView listView = new ListView();


    public scoreBoardController(){
        listView.getItems().add("listItem");

    }
}
