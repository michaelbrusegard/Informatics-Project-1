package workoutplanner.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import workoutplanner.core.Exercise;
import workoutplanner.core.Workout;

public class OverviewGridHandler {

    private ScrollPane scrollPane;
    private Workout workout;
    private List<Exercise> exercises = new ArrayList<>();

    public OverviewGridHandler(ScrollPane scrollPane, Workout workout) {
        this.scrollPane = scrollPane;
        this.workout = workout;
    }

    public void createGrid() {
        for (Exercise exercise : this.workout.getExercises()) {
            exercises.add(exercise);
        }
        GridPane grid = new GridPane();
        (this.scrollPane).setContent(grid);
        grid.setGridLinesVisible(true);
        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setFitToHeight(true);
        for (int i = 0; i < exercises.size(); i++) {
            ExerciseCell cell = new ExerciseCell(exercises.get(i));
            grid.add(cell.getGroup(), i % 2, (int) i / 2);
            if (grid.getRowCount() < i) {
                grid.getRowConstraints().add(new RowConstraints(150, 150, 150, Priority.SOMETIMES, VPos.CENTER, false));
            }
            if (i < 2) {
                grid.getColumnConstraints()
                        .add(new ColumnConstraints(250, 250, 250, Priority.SOMETIMES, HPos.CENTER, false));
            }
        }
    }

}
