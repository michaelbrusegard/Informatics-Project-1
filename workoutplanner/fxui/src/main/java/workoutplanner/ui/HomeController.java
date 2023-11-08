package workoutplanner.ui;

import javafx.fxml.FXML;

/**
 * <h1>HomeController</h1>
 *
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

public class HomeController extends BaseController {
  @FXML
  // Button for creating a new workout
  private void createNewWorkout() {
    getMainController().showFxml("ExerciseView");
  }

  @FXML
  // Display all workouts when the user clicks the "All Workouts" button
  private void showAllWorkouts() {
    getMainController().showFxml("WorkoutView");
  }

  /**
   * Initializes the state of the controller.
   *
   * <p>
   * This method is used to initialize the state of the controller, typically
   * when it is first loaded or reset. It sets the current workout index to -1,
   * indicating that there is no active workout selected.
   * </p>
   */
  @Override
  public void init() {
    getMainController().getUser().setCurrentWorkout(-1);
  }
}
