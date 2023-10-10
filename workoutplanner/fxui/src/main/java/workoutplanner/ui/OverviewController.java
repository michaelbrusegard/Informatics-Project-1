package workoutplanner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;

public class OverviewController {

    @FXML
    private ScrollPane scrollPane;

    
    @FXML
    public void cancel(){
        System.out.println("hei");
    }

    @FXML
    public void initialize(){
        OverviewGridHandler ogh = new OverviewGridHandler(scrollPane);
        ogh.createGrid();
    }
}
