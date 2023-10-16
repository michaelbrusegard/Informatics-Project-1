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
import workoutplanner.fxutil.Controller;
import workoutplanner.fxutil.PageLoader;

/**
 * <h1>HomeController.</h1>
 * <p>
 *   The HomeController class is responsible for managing
 *   the user interface and user interactions
 *   related to the home screen of the application.
 * </p>
 *
 * @since 1.0.0
 * @author Michael Brusegard
 * @version 1.4.0
 */
public class HomeController implements Controller {
  /**
   * Imported Button from javaFx, used to go to ExerciseView.
   */
  @FXML
  private Button newWorkout;
  /**
   * Imported Button from javaFx, used to go to PlanView.
   */
  @FXML
  private Button allWorkouts;

  @FXML
  private void handleNewWorkout() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass()
            .getResource("ExerciseView.fxml"));
    ExerciseViewController exerciseViewController = (ExerciseViewController)
            PageLoader.pageLoader(loader, newWorkout);
    exerciseViewController.initialize();
  }

  @FXML
  private void handleAllWorkout() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("PlanView.fxml"));
    PlanController planController = (PlanController)
            PageLoader.pageLoader(loader, allWorkouts);
    Workout workout = new Workout();
    workout.setDate(Date.from(Instant.now()));
    planController.init(new ArrayList<>(List.of(workout)));
  }

}
