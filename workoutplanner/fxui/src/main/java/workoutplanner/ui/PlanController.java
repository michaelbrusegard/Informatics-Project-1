package workoutplanner.ui;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import workoutplanner.core.Workout;
import workoutplanner.fxutil.Controller;

/**
 * The PlanController class is responsible for managing the PlanView,
 * displaying a list of workouts.
 * <p>
 *   This class implements the Controller interface
 *   and is designed to work with the PlanView.
 *   It provides a method for initializing the
 *   PlanView with a list of workouts, creating a grid to display
 *   the workouts using a PlanGridHandler, and adding
 *   WorkoutCell elements to the grid for each workout.
 * </p>
 *
 * @since 1.0.0
 * @version 1.4.0
 * @author Arman Ilkka Nemati
 */
public class PlanController implements Controller {
  /**
   * Imported scrollPane from javaFx, used to make a plan-grid scrollable.
   */
  @FXML
  private ScrollPane scrollPane;

  /**
   * Initializes the PlanView with a list of workouts.
   * <p>
   * This method initializes the PlanView with the provided list of workouts.
   * It creates a PlanGridHandler to manage the display of workouts within the
   * associated ScrollPane. For each workout in the list, it creates a new
   * WorkoutCell and adds it to the PlanGridHandler.
   * Finally, it creates the grid for the PlanView to display the workouts.
   * </p>
   *
   * @param workouts A list of Workout objects to be displayed in the PlanView.
   */
  public void init(final List<Workout> workouts) {
    PlanGridHandler planGrid = new PlanGridHandler(scrollPane);
    for (Workout workout : workouts) {
      planGrid.addWorkoutCell(new WorkoutCell(workout));
    }
    planGrid.createGrid();
  }
}
