package workoutplanner.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class OverviewController {
    @FXML
    private GridPane grid;

    
    @FXML
    public void cancel(){
        System.out.println("hei");
    }
    @FXML
    public void initialize(){
        OverviewGridHandler ogh = new OverviewGridHandler(grid);
        ogh.createGrid();
    }
}
