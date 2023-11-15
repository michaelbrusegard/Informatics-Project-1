package workoutplanner.fxui;

import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import workoutplanner.fxutil.GridBuilder;
import workoutplanner.fxutil.UiUtils;

/**
 * <h1>WorkoutViewController</h1>
 * The WorkoutViewController class is responsible for listing all workouts.
 *
 * <p>
 * This class manages the display of all workouts in a scroll pane. It provides
 * functionality to view and delete individual workouts, as well as the ability
 * to return to the home screen. The workouts are displayed as a grid with each
 * workout represented as a cell in the grid.
 * </p>
 *
 * @since 2.0.0
 * @author Michael Brusegard
 * @version 2.0.0
 */
public class WorkoutViewController extends BaseController {

  /**
   * Local ScrollPane variable, used for containing the grid with workouts.
   */
  @FXML
  private ScrollPane scrollPane;
  /**
   * Local int variable, used to define size of display-font for workout-name.
   */
  private static final int FONTSIZE = 20;
  /**
   * Local int variable, used to define size of display-font for workout-name.
   */
  private static final String FONT_FAMILY = "SansSerif";
  /**
   * Local int variable, used to define the y-position of data in the cell.
   */
  private static final int LAYOUTY = 28;

  /**
   * Local int variable, used to define the spacing for each cell.
   */
  private static final int SPACING = 10;

  /**
   * Local double variable, used to define y-position for buttons.
   */
  private static final double BUTTONLAYOUTY = 1.5 * LAYOUTY;

  /**
   * Local List variable, used for containing the names of workouts.
   */
  private List<String> workoutNames;

  /**
   * Local List variable, used for containing the dates of when workouts were
   * created.
   */
  private List<String> workoutDates;

  @FXML
  private void returnHome() {
    getMainController().showFxml("Home");
  }

  /**
   * Initializes the view and displays the list of workouts.
   *
   * <p>
   * This method clears the content of the scroll pane and then populates it
   * with a grid of workouts if there are workouts available. If the user has no
   * workouts, it displays a message indicating that there are no workouts.
   * </p>
   */
  @Override
  public void init() {
    // Clear scrollPane
    scrollPane.setContent(new Group());
    // Get workout info
    try {
      workoutNames = getMainController().getUser().getWorkoutNames();
      workoutDates = getMainController().getUser().getWorkoutDates();
    } catch (RuntimeException e) {
      UiUtils.showAlert("Server Error", e.getMessage(), AlertType.ERROR);
      return;
    }
    // Create a grid if there are workouts
    if (!workoutNames.isEmpty()) {
      new GridBuilder(scrollPane,
          workoutNames.size(), this::createCell);
    } else {
      VBox container = new VBox();
      Text noWorkouts = new Text("You don't have any workouts.");
      noWorkouts.setFont(new Font(FONT_FAMILY, FONTSIZE));
      container.getChildren().add(noWorkouts);
      container.setAlignment(Pos.CENTER);
      scrollPane.setContent(container);
    }
  }

  private VBox createCell(final int workoutIndex) {
    String defaultButton = "-fx-pref-width: 80;"
        + "-fx-pref-height: 35;"
        + "-fx-background-insets: 2;"
        + "-fx-background-color:  white;"
        + "-fx-border-color:  #666666;"
        + "-fx-border-width: 2;"
        + "-fx-background-radius: 20;"
        + "-fx-border-radius: 10;";
    Text name = new Text(workoutNames.get(workoutIndex));
    name.setFont(new Font(FONT_FAMILY, FONTSIZE));
    Text date = new Text(workoutDates.get(workoutIndex));
    date.setLayoutY(LAYOUTY);
    // Define buttons
    Button viewButton = new Button("View");
    viewButton.setStyle(defaultButton);
    Button deleteButton = new Button("Delete");
    deleteButton.setStyle(defaultButton);
    viewButton.setOnAction(event -> view(workoutIndex));
    deleteButton.setOnAction(event -> delete(workoutIndex));
    HBox buttonBox = new HBox(viewButton, deleteButton);
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.setSpacing(SPACING);
    buttonBox.setLayoutY(BUTTONLAYOUTY);
    VBox cell = new VBox();
    cell.getChildren().addAll(name, date, buttonBox);
    return cell;
  }

  private void view(final int workoutIndex) {
    try {
      getMainController().getUser().setCurrentWorkout(workoutIndex);
    } catch (RuntimeException e) {
      UiUtils.showAlert("Server Error", e.getMessage(), AlertType.ERROR);
      return;
    }
    getMainController().showFxml("Overview");
  }

  public void delete(final int workoutIndex) {
    if (UiUtils.showConfirmation("Delete Workout",
        "Are you sure you want to delete "
            + workoutNames.get(workoutIndex)
            + "? "
            + "All workout data will be lost.")) {
      try {
        getMainController().getUser().removeWorkout(workoutIndex);
      } catch (RuntimeException e) {
        UiUtils.showAlert("Server Error", e.getMessage(), AlertType.ERROR);
        return;
      }
      init();
    }
  }
}
