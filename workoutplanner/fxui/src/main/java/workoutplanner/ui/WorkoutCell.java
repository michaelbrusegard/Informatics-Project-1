package workoutplanner.ui;

import java.text.SimpleDateFormat;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import workoutplanner.core.Workout;

public class WorkoutCell {
    private final Workout workout;

    public WorkoutCell(Workout workout) {
        this.workout = workout;
    }

    private Text getName() {
        Text name = new Text("Name: " + workout.getName().toString());
        name.setFont(new Font(28));
        return name;
    }

    private Text getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm");
        String formattedDate = dateFormat.format(workout.getDate());
        Text date = new Text("Date: " + formattedDate);
        date.setLayoutY(28);
        return date;
    }

    public Group getCellContent() {
        Group group = new Group();
        group.getChildren().addAll(getName(), getDate());
        return group;
    }

    public Workout getWorkout() {
        return workout;
    }
}
