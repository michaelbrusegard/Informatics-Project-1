package workoutplanner.ui;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import workoutplanner.core.Workout;

/**
 * <h1>HomeController.</h1>
 * <p>
 * The HomeController class is responsible for managing
 * the user interface and user interactions
 * related to the home screen of the application.
 * </p>
 *
 * @since 1.0.0
 * @author Michael Brusegard
 * @version 2.0.0
 */

public class HomeController extends Controller {
  @FXML
  // Button for creating a new workout
  private void createNewWorkout() throws IOException {
    getMainController().showFXML("ExerciseView.fxml");
  }

  @FXML
  // Display all workouts when the user clicks the "All Workouts" button
  private void handleAllWorkout() throws IOException {
    getMainController().showFXML("WorkoutView.fxml");
  }
}
