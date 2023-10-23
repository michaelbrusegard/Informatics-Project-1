package workoutplanner.ui;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import workoutplanner.core.Workout;

import java.util.List;

public class WorkoutViewController extends Controller {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    public void init() {
        List<Workout> workouts = getMainController().getUser().getWorkouts();
        fillGrid(workouts);
    }

    public void fillGrid(List<Workout> workouts) {
        int row = 0;
        int col = 0;

        addRow();
        int colAmount;
        if (workouts.size() <= 1) {
            colAmount = 1;
        } else if (workouts.size() == 2) {
            colAmount = 2;
        } else {
            colAmount = 3;
        }

        for (int i = 0; i < colAmount; i++) {
            addColumn();
        }
        for (Workout w : workouts) {
            Text name = new Text("Name: " + w.getName());
            name.setFont(new Font(28));
            gridPane.add(name, col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
                addRow();
            }
        }
    }

    private void addRow() {
        gridPane.getRowConstraints().add(new RowConstraints(150, 150, 150, Priority.SOMETIMES, VPos.CENTER, false));
    }

    private void addColumn() {
        gridPane.getColumnConstraints()
                .add(new ColumnConstraints(250, 250, 250, Priority.SOMETIMES, HPos.CENTER, false));
    }
}
