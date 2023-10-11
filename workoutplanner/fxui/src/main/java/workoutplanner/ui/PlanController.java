package workoutplanner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import workoutplanner.core.Workout;

import java.util.List;

public class PlanController {

    @FXML
    private ScrollPane scrollPane;
    public void init(List<Workout> workouts){
        PlanGridHandler planGrid = new PlanGridHandler(scrollPane);
        for (Workout workout:workouts) {
            planGrid.addWorkoutCell(new WorkoutCell(workout));
        }
        planGrid.createGrid();
    }
}
