package workoutplanner.ui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ExerciseViewController {
    @FXML
    private TextField exerciseSet, exerciseRepMin, exerciseRepMax, exerciseWeight;

    @FXML
    private Text exerciseName;

    @FXML
    private ListView<String> exerciseList;

    @FXML
    private TextField newExerciseField;

    @FXML
    private Button finishButton;

    @FXML
    private void addExercise() {
    }

    @FXML
    private void handleFinishAddingExercises() throws IOException {
    }

    @FXML
    private void init() throws IOException {
    }
}
