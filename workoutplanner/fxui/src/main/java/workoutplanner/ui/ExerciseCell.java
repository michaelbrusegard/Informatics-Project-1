package workoutplanner.ui;


import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import workoutplanner.core.Exercise;

public class ExerciseCell {
    private Exercise exercise;
    private Group group;

    public ExerciseCell(Exercise exercise) {
        this.exercise = exercise;
    }

    private void createFXCell() {
        Group cellGroup = new Group();
        Text name = new Text(exercise.getName());
        // Text sets = new Text(exercise.getSets());
        // Text reps = new Text(exercise.getReps());
        // cellGroup.getChildren().addAll(name, sets, reps);
    }

}
