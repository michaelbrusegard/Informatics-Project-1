package workoutplanner.ui;

import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import workoutplanner.core.Workout;
public class WorkoutCell {
    private final Workout workout;

    public WorkoutCell(Workout w) {
        this.workout = w;
    }

    public Group getGroup() {
        Group group = new Group();
        group.getChildren().add(new Text(workout.toString()));
        return group;
    }
}
