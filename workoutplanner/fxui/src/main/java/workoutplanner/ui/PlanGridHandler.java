package workoutplanner.ui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import workoutplanner.core.Workout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanGridHandler {
    private final List<WorkoutCell> workoutCellList = new ArrayList<>();
    private final ScrollPane scrollPane;
    private final GridPane gridPane;

    public PlanGridHandler(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
        this.gridPane = new GridPane();
        configureGrid();
    }

    private void configureGrid() {
        gridPane.setGridLinesVisible(true);
        gridPane.setOnMouseClicked(this::clickNode);
//        gridPane.setMinWidth(scrollPane.getMinViewportWidth());
        gridPane.setAlignment(Pos.CENTER);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(gridPane);
    }

    private void clickNode(MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != gridPane) {
            Node parent = clickedNode.getParent();
            while (parent != gridPane) {
                clickedNode = parent;
                parent = clickedNode.getParent();
            }
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            System.out.println("Mouse clicked cell: " + colIndex + " And: " + rowIndex);
        }
    }

    private void addWorkoutCell (Workout workout) {
        workoutCellList.add(new WorkoutCell(workout));
    }

    private void exampleWorkouts() {
        for (int j = 0; j < 10; j++) {
            Workout workout = new Workout();
            for (int i = 0; i < 5; i++) {
                workout.addExercise("e", 3, 8, 12, 70);
                workout.setDate(new Date());
            }
            addWorkoutCell(workout);
        }
    }

    private void addRow() {
        gridPane.getRowConstraints().add(new RowConstraints(150, 150,150, Priority.SOMETIMES, VPos.CENTER, false));
    }

    private void addColumn() {
        gridPane.getColumnConstraints().add(new ColumnConstraints(250, 250, 250, Priority.SOMETIMES, HPos.CENTER, false));
    }

    public void createGrid() {
        exampleWorkouts();
        int row = 0;
        int col = 0;
        addRow();
        for (int i = 0; i < 3; i++) {
            addColumn();
        }
        for (WorkoutCell w:workoutCellList) {
            gridPane.add(w.getText(), col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
                addRow();
            }
        }
    }
}
