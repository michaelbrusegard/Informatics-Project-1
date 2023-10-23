package workoutplanner.ui;

import java.io.IOException;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import workoutplanner.fxutil.GridBuilder;
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
   * Local int variable, used to define size of display-font for workout-name.
   */
  private static final int NAMEFONTSIZE = 40;
  /**
   * Local int variable, used to define size of display-font for workout-date.
   */
  private static final int DATEFONTSIZE = 20;
  /**
   * Local double variable, used to define the x-position of data in the cell.
   */
  private static final double LAYOUTX = -10.0;
  /**
   * Local int variable, used to define the y-position of data in the cell.
   */
  private static final int LAYOUTY = 30;
  /**
   * Local int variable, used to define the font size used in the cell.
   */
  private static final int CELLFONTSIZE = 18;

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
    new GridBuilder(gridPane,
        getMainController().getUser().getLatestWorkout().getExercises(), this::createCell);
  }

  /**
   * Constructs an ExerciseCell with the provided Exercise instance.
   * <p>
   * This constructor initializes an ExerciseCell
   * with the given Exercise instance,
   * allowing the cell to display information related to the exercise.
   * It also triggers the creation of the data for the exercise cell.
   * </p>
   *
   * @param exercise The Exercise instance to be displayed in the cell.
   */
  private Group createCell(int index) {
    Group cellGroup = new Group();
    Text name = new Text(getMainController().getUser().getLatestWorkout().getExercises().get(index).name() + ":");
    name.setLayoutX(LAYOUTX);
    Font font = new Font(CELLFONTSIZE);
    name.setFont(font);
    Text sets = new Text("Sets: " + getMainController().getUser().getLatestWorkout().getExercises().get(index).sets());
    Text reps = new Text("Reps: " + getMainController().getUser().getLatestWorkout().getExercises().get(index).repMin()
        + " - " + getMainController().getUser().getLatestWorkout().getExercises().get(index).repMax());
    Text weight = new Text(
        "Weight: " + getMainController().getUser().getLatestWorkout().getExercises().get(index).weight() + "kg");
    cellGroup.getChildren().addAll(name, sets, reps, weight);
    for (int i = 1; i < cellGroup.getChildren().size(); i++) {
      cellGroup.getChildren().get(i).setLayoutY(i * LAYOUTY);
      ((Text) cellGroup.getChildren().get(i)).setFont(font);
    }
    return cellGroup;
  }
}