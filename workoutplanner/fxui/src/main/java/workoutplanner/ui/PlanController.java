package workoutplanner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;

public class PlanController {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    public void initialize(){
        PlanGridHandler planGrid = new PlanGridHandler(scrollPane);
        planGrid.createGrid();
    }
}
