package workoutplanner.ui;

import java.io.IOException;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import workoutplanner.core.Workout;
import workoutplanner.fxutil.Overview;

/**
 * <h1>OverviewController.</h1>
 * <p>
 * The OverviewController class is responsible for managing the user
 * interface and user interactions related to the overview of the workout.
 * It allows users to save and cancel workout information.
 * </p>
 *
 * @since 1.0.0
 * @author Erlend Løken Sæveraas
 * @version 2.0.0
 */
public class OverviewController extends Controller {

  /**
   * Local int variable, used to define size of display-font for workout-name.
   */
  private static final int NAMEFONTSIZE = 40;
  /**
   * Local int variable, used to define size of display-font for workout-date.
   */
  private static final int DATEFONTSIZE = 20;
  /**
   * Imported VBox from javaFx, used to contain the viewable page.
   */
  @FXML
  private VBox saveWorkoutNameBox;
  /**
   * Imported GridPane from javaFx, used to make a grid scrollable.
   */
  @FXML
  private GridPane gridPane;
  /**
   * Imported Button from javaFx, used for cancel function.
   */
  @FXML
  private Button cancelButton;
  /**
   * Imported Button from javaFx, used for save function.
   */
  @FXML
  private Button saveButton;
  /**
   * Imported TextField from javaFx, used for naming workout.
   */
  @FXML
  private TextField inpName;
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
   * Local int variable, used to define current row.
   */
  private int currentRow = 0;
  /**
   * Local int variable, used to define current column.
   */
  private int currentColumn = 0;

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
  public void init() {
    Workout workout = getMainController().getUser().getLatestWorkout();

    // Clear any existing content in the GridPane
    gridPane.getChildren().clear();

    // Define RowConstraints and ColumnConstraints
    RowConstraints rowConstraints = new RowConstraints(ROWHEIGHT, ROWHEIGHT, ROWHEIGHT, Priority.SOMETIMES, VPos.CENTER,
        false);
    ColumnConstraints columnConstraints = new ColumnConstraints(COLUMNWIDTH, COLUMNWIDTH, COLUMNWIDTH,
        Priority.SOMETIMES, HPos.CENTER, false);

    // Apply ColumnConstraints to the GridPane
    for (int i = 0; i < COLUMNS; i++) {
      gridPane.getColumnConstraints().add(columnConstraints);
    }

    // Add exercises to grid
    workout.getExercises().forEach(exercise -> {
      ExerciseCell cell = new ExerciseCell(exercise);
      // Add the cell to the grid at the current column and row
      gridPane.add(cell.getGroup(), currentRow % COLUMNS, currentRow / COLUMNS);

      // Update the current row and create new rows as needed
      currentRow++;

      // Add new rows if we need more rows
      if (currentColumn < COLUMNS - 1) {
        currentColumn++;
      } else {
        currentColumn = 0;
        gridPane.getRowConstraints().add(rowConstraints);
      }
    });
  }

  /**
   * Cancels the current operation and navigates to the Homepage.
   * <p>
   * This method is responsible for canceling the current operation,
   * and it validates the input data.
   * If the validation succeeds, it navigates to the Homepage by loading
   * Home.fxml.
   * </p>
   *
   * @throws IOException If an I/O error occurs while loading Home.fxml.
   */
  @FXML
  public void cancel() throws IOException {
    if (Overview.validateOverview(false, true, this.inpName)) {
      if (Overview.checkIfCancel()) {
        getMainController().getUser().removeLatestWorkout();
        getMainController().showFXML("Home");
      }
    }
  }

  /**
   * Saves the workout data and initializes the PlanController with the workout.
   * <p>
   * This method is responsible for saving the workout data, name and date,
   * and initializing the PlanController with the workout information.
   * The PlanController is then used to display the workout in the PlanView
   * page.
   * </p>
   *
   * @throws IOException If an I/O error occurs while loading PlanView.fxml.
   */
  @FXML
  public void save() throws IOException {
    if (Overview.validateOverview(true, false, this.inpName)) {
      getMainController().getUser().getLatestWorkout().setName(inpName.getText());
      getMainController().getUser().getLatestWorkout().setDate(new Date());
      getMainController().showFXML("WorkoutView");
    }
  }

  /**
   * Loads the overview from a workout plan.
   * <p>
   * This method is responsible for gathering the information from a workout
   * and displaying it. It gathers the workout name and date,
   * as well as all the exercises that pertain to that workout.
   * </p>
   */
  public void loadOverviewFromPlan() {
    saveWorkoutNameBox.getChildren().clear();
    Text name = new Text(getMainController().getUser().getLatestWorkout().getName());
    name.setFont(new Font(NAMEFONTSIZE));
    Text date = new Text(getMainController().getUser().getLatestWorkout().getDateAsString());
    date.setFont(new Font(DATEFONTSIZE));
    saveWorkoutNameBox.getChildren().addAll(name, date, cancelButton);
  }
}