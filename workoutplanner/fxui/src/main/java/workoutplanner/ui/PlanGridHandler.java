package workoutplanner.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import workoutplanner.core.Workout;

import java.util.ArrayList;
import java.util.List;

public class PlanGridHandler {
    private List<HBox> hBoxList;
    private final VBox vBox;

    public PlanGridHandler(VBox vBox, ArrayList<Workout> workouts) {
        this.vBox = vBox;
        for (Workout w:workouts) {
            HBox hBox = new HBox();
            hBox.getChildren().add(new WorkoutCell(w).getGroup());
            assert false;
            this.hBoxList.add(hBox);
        }
    }

    public void createGrid() {
        for (HBox h:hBoxList) {
            vBox.getChildren().add(h);
        }

    }
}
