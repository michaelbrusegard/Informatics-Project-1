package workoutplanner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import workoutplanner.core.Workout;

public class OverviewController {
Workout workout;

    @FXML
    private ScrollPane scrollPane;

    
    @FXML
    public void cancel(){
        System.out.println("hei");
    }

    @FXML
    public void initialize(){
  
    }

    public void init(Workout workout){
        System.out.println(this.scrollPane);
        this.workout = workout;
        OverviewGridHandler ogh = new OverviewGridHandler(scrollPane,this.workout);
        ogh.createGrid();
    }
}
