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
        this.createFXCell();
    }

    private Group createFXCell() {
        Group cellGroup = new Group();
        Text name = new Text(exercise.getName() + ":");
        name.setLayoutX(-10.0);
        name.setFont(new Font("Serif", 18));
        Text sets = new Text("Sets: " + exercise.getSets());
        Text reps = new Text("Reps: " + exercise.getRepMin() + " - " + exercise.getRepMax());
        Text weight = new Text("Weight: " + exercise.getWeight() + "kg");
        cellGroup.getChildren().addAll(name, sets, reps, weight);
        for (int index = 1; index < 4; index++) {
            cellGroup.getChildren().get(index).setLayoutY(index * 30);
            ((Text) cellGroup.getChildren().get(index)).setFont(new Font("Serif", 18));
        }
        group = cellGroup;
        return cellGroup;
    }

    public Group getGroup() {
        return group;
    }
}
