package workoutplanner.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.GridPane;
import workoutplanner.core.Exercise;

public class OverviewGridHandler {

    private GridPane grid;
    private List<Exercise> exercises = new ArrayList<>();

    public OverviewGridHandler(GridPane grid){
        this.grid = grid;
    }

    public void createGrid(){
        exercises.add(new Exercise("hei", "os", "dsa", "fdsa" ));
        exercises.add(new Exercise("2", "2", "ds3a", "f3dsa" ));
        exercises.add(new Exercise("2", "2", "ds3a", "f3dsa" ));
        exercises.add(new Exercise("2", "2", "ds3a", "f3dsa" ));
        exercises.add(new Exercise("2", "2", "ds3a", "f3dsa" ));
        exercises.add(new Exercise("2", "2", "ds3a", "f3dsa" ));
        for (int i = 0; i < exercises.size(); i++) {
        ExerciseCell cell = new ExerciseCell(exercises.get(i));
        grid.add(cell.getGroup(), i%2, (int) i/2);
        }
    }

}
