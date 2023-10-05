package workoutplanner.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import workoutplanner.core.Exercise;

public class OverviewGridHandler {


    private VBox vBox;
    private List<Exercise> exercises = new ArrayList<>();

    public OverviewGridHandler(VBox vBox){
        this.vBox = vBox;
    }

    public void createGrid(){
        exercises.add(new Exercise("hei", "os", "dsa", "fdsa" ));
        exercises.add(new Exercise("2", "2", "ds3a", "f3dsa" ));
        exercises.add(new Exercise("2", "2", "ds3a", "f3dsa" ));
        exercises.add(new Exercise("2", "2", "ds3a", "f3dsa" ));
        exercises.add(new Exercise("2", "2", "ds3a", "f3dsa" ));
        exercises.add(new Exercise("2", "2", "ds3a", "f3dsa" ));
        GridPane grid = new GridPane();
        System.out.println(grid.getColumnCount());
        System.out.println(grid.getRowCount());
        Node firstE = vBox.getChildren().get(0);
        ((ScrollPane) firstE).setContent(grid);
        grid.setGridLinesVisible(true);
        for (int i = 0; i < exercises.size(); i++) {
        grid.getRowConstraints().add(new RowConstraints(120, 120,120, Priority.ALWAYS, VPos.TOP, true));
        if (i < 2){
            grid.getColumnConstraints().add(new ColumnConstraints(120, 120, 120, Priority.ALWAYS, HPos.CENTER, true));
        }
        ExerciseCell cell = new ExerciseCell(exercises.get(i));
        grid.add(cell.getGroup(), i%2, (int) i/2);
    }
    }

}
