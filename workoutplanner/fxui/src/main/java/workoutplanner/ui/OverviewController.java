package workoutplanner.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import workoutplanner.core.Exercise;

public class OverviewController {
    @FXML
    private GridPane grid;
    @FXML
    private VBox containerBox;

    
    @FXML
    public void cancel(){
        System.out.println("hei");
    }
    @FXML
    public void initialize(){
        OverviewGridHandler ogh = new OverviewGridHandler(containerBox, grid);
        ogh.createGrid();
    }
}
