package workoutplanner.ui;

import javafx.fxml.FXMLLoader;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutGridHandler {
    private final List<WorkoutCell> workoutCellList = new ArrayList<>();
    private final ScrollPane scrollPane;
    private final GridPane gridPane;

    public WorkoutGridHandler(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
        this.gridPane = new GridPane();
        configureGrid();
    }

    private void configureGrid() {
        gridPane.setGridLinesVisible(true);
        gridPane.setOnMouseClicked(arg0 -> {
            try {
                getMainController().loadPage("Overview");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        gridPane.setAlignment(Pos.CENTER);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(gridPane);
    }

    public void clickNode(MouseEvent event) throws IOException {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != gridPane) {
            Node parent = clickedNode.getParent();
            while (parent != gridPane) {
                clickedNode = parent;
                parent = clickedNode.getParent();
            }
            int colIndex = GridPane.getColumnIndex(clickedNode);
            int rowIndex = GridPane.getRowIndex(clickedNode);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Overview.fxml"));
            OverviewController overviewController = (OverviewController) PageLoader.pageLoader(loader,
                    clickedNode);
            int index = colIndex;
            for (int i = 0; i < rowIndex; i++) {
                index += 3;
            }
            overviewController.init(workoutCellList.get(index).getWorkout());
            overviewController.loadOverviewFromPlan();
        }
    }

    private void addRow() {
        gridPane.getRowConstraints().add(new RowConstraints(150, 150, 150, Priority.SOMETIMES, VPos.CENTER, false));
    }

    private void addColumn() {
        gridPane.getColumnConstraints()
                .add(new ColumnConstraints(250, 250, 250, Priority.SOMETIMES, HPos.CENTER, false));
    }

    public void addWorkoutCell(WorkoutCell workoutCell) {
        workoutCellList.add(workoutCell);
    }

    public void createGrid(PlanController planController) {
        this.planController = planController;
        int row = 0;
        int col = 0;
        addRow();
        int colAmount;
        if (workoutCellList.size() <= 1) {
            colAmount = 1;
        } else if (workoutCellList.size() == 2) {
            colAmount = 2;
        } else {
            colAmount = 3;
        }
        for (int i = 0; i < colAmount; i++) {
            addColumn();
        }
        for (WorkoutCell w : workoutCellList) {
            gridPane.add(w.getCellContent(), col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
                addRow();
            }
        }
    }
}
