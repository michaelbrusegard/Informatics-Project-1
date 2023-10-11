package workoutplanner.ui;

import fxutil.doc.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import workoutplanner.core.Workout;

import java.io.IOException;
import java.util.List;

public class PlanController implements Controller{

    @FXML
    private ScrollPane scrollPane;

    private PlanGridHandler planGrid;

    public void init(List<Workout> workouts) {
        planGrid = new PlanGridHandler(scrollPane);
        for (Workout workout : workouts) {
            planGrid.addWorkoutCell(new WorkoutCell(workout));
        }
        planGrid.createGrid(this);
    }
    
    public void clickNode(MouseEvent event) throws IOException {
        planGrid.clickNode(event);
    }

}
