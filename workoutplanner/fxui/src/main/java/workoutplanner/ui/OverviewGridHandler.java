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


    private ScrollPane scrollPane;
    private List<Exercise> exercises = new ArrayList<>();

    public OverviewGridHandler(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
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
        (this.scrollPane).setContent(grid);
        grid.setGridLinesVisible(true);
        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setFitToHeight(true);
        for (int i = 0; i < exercises.size(); i++) {
            ExerciseCell cell = new ExerciseCell(exercises.get(i));
            grid.add(cell.getGroup(), i%2, (int) i/2);
            if (grid.getRowCount() < i){
                grid.getRowConstraints().add(new RowConstraints(150, 150,150, Priority.ALWAYS, VPos.TOP, false));
            }
            if (i < 2){
                grid.getColumnConstraints().add(new ColumnConstraints(250, 250, 250, Priority.ALWAYS, HPos.CENTER, false));
            }
        // grid.setConstraints(cell.getGroup(), i%2, (int) i/2,1,1,HPos.CENTER,VPos.TOP,Priority.ALWAYS,Priority.ALWAYS);
    }
    }

}
