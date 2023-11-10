package workoutplanner.ui;

import java.util.Date;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import workoutplanner.fxutil.GridBuilder;
import workoutplanner.fxutil.Overview;
import workoutplanner.fxutil.UiUtils;

/**
 * <h1>OverviewController</h1>
 *
 * <p>
 * The OverviewController class is responsible for managing the user
 * interface and user interactions related to the overview of the workout.
 * It allows users to save and cancel workout information.
 * </p>
 *
 * @since 1.0.0
 * @author Erlend Løken Sæveraas + Michael Brusegard
 * @version 2.0.0
 */
public class OverviewController extends BaseController {
  /**
   * Imported VBox from javaFx, used to contain the viewable page.
   */
  @FXML
  private VBox saveWorkoutNameBox;
  /**
   * Imported HBox from javaFx, used to display the workout information.
   */
  @FXML
  private HBox workoutInfoBox;
  /**
   * Imported GridPane from javaFx, used to make a grid scrollable.
   */
  @FXML
  private ScrollPane scrollPane;
  /**
   * Imported TextField from javaFx, used for naming workout.
   */
  @FXML
  private TextField inputName;
  /**
   * Imported TextField from javaFx, used for displaying workout name.
   */
  @FXML
  private Text name;
  /**
   * Local int variable, used to define size of display-font for workout-name.
   */
  private static final int FONTSIZE = 20;
  /**
   * Local int variable, used to define size of display-font for workout-name.
   */
  private static final String FONT_FAMILY = "SansSerif";

  /**
   * Local int variable, used to define the spacing of the contentBox.
   */
  private static final int CONTENTSPACING = 10;

  /**
   * Local int variable, used to define the bottom margin for the contentBox.
   */
  private static final int CONTENTMARGINBOTTOM = 10;

  /**
   * Local int variable, used to define the left margin for the contentBox.
   */
  private static final int CONTENTMARGINLEFT = 20;

  /**
   * Cancels the current operation and navigates to the Homepage.
   * <p>
   * This method is responsible for canceling the current operation,
   * and it validates the input data.
   * If the validation succeeds, it navigates to the Homepage by loading
   * Home.fxml.
   * </p>
   */
  @FXML
  public void cancel() {
    if (Overview.validateOverview(false, true, this.inputName)) {
      if (Overview.checkIfCancel()) {
        try {
          getMainController().getUser().removeCurrentWorkout();
        } catch (Exception e) {
          UiUtils.showAlert("Error",
              e.getMessage(),
              AlertType.ERROR);
        }
        getMainController().showFxml("Home");
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
   */
  @FXML
  public void save() {
    if (Overview.validateOverview(true, false, this.inputName)) {
      try {
        getMainController().getUser().saveCurrentWorkout(inputName.getText());
      } catch (Exception e) {
        UiUtils.showAlert("Error",
            e.getMessage(),
            AlertType.ERROR);
        return;
      }
      getMainController().showFxml("WorkoutView");
    }
  }

  @FXML
  private void returnWorkoutView() {
    getMainController().showFxml("WorkoutView");
  }

  @FXML
  private void addExercises() {
    getMainController().showFxml("ExerciseView");
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
  @Override
  public void init() {
    // Clear name and scrollPane
    inputName.clear();
    scrollPane.setContent(new VBox());

    if (getMainController().getUser().getCurrentWorkout().isSaved()) {
      saveWorkoutNameBox.setVisible(false);
      workoutInfoBox.setVisible(true);
      name.setText(getMainController().getUser().getCurrentWorkout().getName());
    } else {
      workoutInfoBox.setVisible(false);
      saveWorkoutNameBox.setVisible(true);
    }
    saveWorkoutNameBox.managedProperty()
        .bind(saveWorkoutNameBox.visibleProperty());
    workoutInfoBox.managedProperty().bind(workoutInfoBox.visibleProperty());

    // Create grid
    new GridBuilder(scrollPane,
        getMainController().getUser().getCurrentWorkout().getExercises(),
        this::createCell);
  }

  /**
   * Creates a cell for displaying an exercise in the user's current workout.
   *
   * <p>
   * This method creates a graphical cell for displaying an exercise from the
   * user's current workout. The cell includes the exercise name, details
   * (sets, reps, and weight), buttons to move the exercise position, and a
   * delete button to remove the exercise from the workout.
   * </p>
   *
   * @param exerciseIndex The index of the exercise to be displayed.
   * @return A VBox representing the graphical cell for the exercise.
   */
  private VBox createCell(final int exerciseIndex) {
    // Create text elements
    Text exerciseName = new Text(
        getMainController().getUser().getCurrentWorkout().getExercises()
            .get(exerciseIndex).name() + ":");
    exerciseName.setFont(new Font(FONT_FAMILY, FONTSIZE));

    // Move buttons
    String defaultButton = "-fx-font-size: 25;"
        + "-fx-background-insets: 2;"
        + "-fx-background-color:  #e9e9e9;"
        + "-fx-bounds-type: visual";

    Button moveLeftButton = new Button("←");
    moveLeftButton.setStyle(defaultButton);
    Button moveRightButton = new Button("→");
    moveRightButton.setStyle(defaultButton);

    moveLeftButton.setOnAction(event -> move(exerciseIndex, true));
    moveRightButton.setOnAction(event -> move(exerciseIndex, false));

    // Delete button
    String deleteButtonStyle = "-fx-pref-width: 80;"
        + "-fx-pref-height: 30;"
        + "-fx-background-insets: 2;"
        + "-fx-background-color:  white;"
        + "-fx-border-color:  #666666;"
        + "-fx-border-width: 2;"
        + "-fx-background-radius: 20;"
        + "-fx-border-radius: 10;";
    Button deleteButton = new Button("Delete");
    deleteButton.setOnAction(event -> delete(exerciseIndex));
    deleteButton.setStyle(deleteButtonStyle);

    // VBox for the content of the cell
    Text sets = new Text(
        "Sets: " + getMainController().getUser().getCurrentWorkout()
            .getExercises().get(exerciseIndex).sets());
    Text reps = new Text(
        "Reps: " + getMainController().getUser().getCurrentWorkout()
            .getExercises().get(exerciseIndex).repMin()
            + " - " + getMainController().getUser().getCurrentWorkout()
                .getExercises().get(exerciseIndex).repMax());
    Text weight = new Text(
        "Weight: " + getMainController().getUser().getCurrentWorkout()
            .getExercises().get(exerciseIndex).weight()
            + "kg");
    VBox cell = new VBox();
    VBox contentBox = new VBox();
    contentBox.getChildren().addAll(sets, reps, weight, deleteButton);
    contentBox.setSpacing(CONTENTSPACING);
    cell.setAlignment(Pos.CENTER);
    VBox.setMargin(contentBox, new Insets(0, 0,
        CONTENTMARGINBOTTOM, CONTENTMARGINLEFT));

    // HBox for the move buttons and the deleteButton
    HBox moveContentBox = new HBox(moveLeftButton, deleteButton,
        moveRightButton);
    moveContentBox.setAlignment(Pos.CENTER);

    moveContentBox.setSpacing(0);

    cell.getChildren().addAll(exerciseName, contentBox, moveContentBox);

    // Add all elements to the cell with correct layout

    return cell;
  }

  private void move(final int exerciseIndex, final boolean left) {
    // Move the exercise
    try {
      getMainController().getUser().moveExerciseInCurrentWorkout(exerciseIndex, left);
    } catch (Exception e) {
      UiUtils.showAlert("Error",
          e.getMessage(),
          AlertType.ERROR);
      return;
    }
    // Reload the overview with the new order
    init();
  }

  private void delete(final int exerciseIndex) {
    if (getMainController().getUser().getCurrentWorkout()
        .getExerciseCount() == 1) {
      UiUtils.showAlert("Error",
          "Cannot delete the last exercise in a workout.",
          AlertType.ERROR);
      return;
    }

    if (UiUtils.showConfirmation("Delete Exercise",
        "Are you sure you want to delete "
            + getMainController().getUser().getCurrentWorkout().getExercises()
                .get(exerciseIndex).name()
            + "? ")) {
      try {
        getMainController().getUser().removeExerciseFromCurrentWorkout(exerciseIndex);
      } catch (Exception e) {
        UiUtils.showAlert("Error",
            e.getMessage(),
            AlertType.ERROR);
        return;
      }

      // Reload the overview now that an exercise has been deleted
      init();
    }
  }
}
