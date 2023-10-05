package workoutplanner.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class OverviewController {

    @FXML
    private VBox containerBox;

    
    @FXML
    public void cancel(){
        System.out.println("hei");
    }

    @FXML
    public void initialize(){
        System.out.println(this.containerBox);
        OverviewGridHandler ogh = new OverviewGridHandler(containerBox);
        ogh.createGrid();
    }
}
