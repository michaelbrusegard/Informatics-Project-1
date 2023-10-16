package workoutplanner.ui;

import java.io.IOException;
import javafx.fxml.FXML;

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
  private void showAllWorkouts() throws IOException {
    getMainController().showFXML("WorkoutView.fxml");
  }
}
