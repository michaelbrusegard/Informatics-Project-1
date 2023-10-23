package workoutplanner.ui;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import workoutplanner.core.Exercise;
import workoutplanner.core.Workout;

/**
 * <h1>OverviewGridHandler.</h1>
 * The OverviewGridHandler class is responsible for managing
 * the graphical representation of a workout's overview.
 * <p>
 * This class is used to create and populate a grid within a ScrollPane
 * to display workout exercises' overview.
 * It handles the layout and positioning of ExerciseCell elements,
 * each displaying information about an exercise
 * from the associated workout.
 * </p>
 *
 * @since 1.0.0
 * @author Erlend Løken Sæveraas
 * @version 1.4.0
 */
public class OverviewGridHandler {
  /**
   * Local scrollPane variable, used to make grid scrollable.
   */
  private final ScrollPane scrollPane;
  /**
   * Local workout variable, used to get data for the grid.
   */
  private final Workout workout;
  /**
   * Local List variable, used to fill in the grid.
   */
  private final List<Exercise> exercises = new ArrayList<>();
  /**
   * Local int variable, used to define row height of the grid.
   */
  private final int ROWHEIGHT = 150;
  /**
   * Local int variable, used to define column width of the grid.
   */
  private final int COLUMNWIDTH = 199;
  /**
   * Local int variable, define how may columns.
   */
  private final int COLUMNS = 3;

  /**
   * Constructs an OverviewGridHandler to manage the display of a
   * workout's overview.
   * <p>
   * This constructor initializes an OverviewGridHandler with the
   * specified ScrollPane and Workout instances,
   * allowing it to create and manage
   * the graphical representation of a workout's
   * overview within the provided ScrollPane.
   * </p>
   *
   * @param inputScrollPane The ScrollPane in which the workout's overview
   *                        will be displayed.
   * @param inputWorkout    The Workout for which the overview will be
   *                        created and managed.
   */
  public OverviewGridHandler(final ScrollPane inputScrollPane,
      final Workout inputWorkout) {
    this.scrollPane = inputScrollPane;
    this.workout = inputWorkout;
  }

  /**
   * Creates and populates a grid within the ScrollPane
   * to display workout exercises.
   * <p>
   * This method sets up a GridPane to represent the overview of exercises
   * within the provided ScrollPane.
   * It populates the grid with ExerciseCell elements,
   * each displaying information about an exercise from the workout.
   * </p>
   *
   * @implNote The exercises are retrieved from the associated workout.
   */
  public void createGrid() {
    exercises.addAll(this.workout.getExercises());
    GridPane grid = new GridPane();
    (this.scrollPane).setContent(grid);
    grid.setGridLinesVisible(true);
    this.scrollPane.setFitToWidth(true);
    this.scrollPane.setFitToHeight(true);
    for (int i = 0; i < exercises.size(); i++) {
      ExerciseCell cell = new ExerciseCell(exercises.get(i));
      grid.add(cell.getGroup(), i % COLUMNS, Math.floorDiv(i, COLUMNS));
      if (grid.getRowCount() < i) {
        grid.getRowConstraints().add(new RowConstraints(ROWHEIGHT, ROWHEIGHT,
            ROWHEIGHT, Priority.SOMETIMES, VPos.CENTER, false));
      }
      if (i < COLUMNS) {
        grid.getColumnConstraints()
            .add(new ColumnConstraints(COLUMNWIDTH, COLUMNWIDTH,
                COLUMNWIDTH, Priority.SOMETIMES, HPos.CENTER,
                false));
      }
    }
  }

}