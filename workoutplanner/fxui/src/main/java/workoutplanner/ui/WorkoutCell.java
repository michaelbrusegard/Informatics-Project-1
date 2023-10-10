package workoutplanner.ui;

import javafx.scene.text.Text;
import workoutplanner.core.Workout;
public class WorkoutCell {
    private final Workout workout;

    public WorkoutCell(Workout w) {
        this.workout = w;
    }

    public Text getText() {
        return new Text(workout.getDate().toString());
    }

    public Workout getWorkout() {
        return workout;
    }
}
