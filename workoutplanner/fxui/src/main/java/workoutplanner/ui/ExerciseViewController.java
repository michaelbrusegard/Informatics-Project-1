package workoutplanner.ui;

import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import workoutplanner.fxutil.ExerciseView;
import workoutplanner.fxutil.UiUtils;

/**
 * <h1>ExerciseViewController</h1>
 * The ExerciseViewController class is responsible for managing the user
 * interface and user interactions related to creating and adding exercises
 * to a workout.
 * <p>
 * This class provides the functionality to create and add exercises to a
 * workout. It allows users to select exercises from a list, input exercise
 * details (sets, reps, weight), and add exercises to the workout. Users can
 * also cancel the workout creation process or finish creating the workout.
 * </p>
 *
 * @since 1.0.0
 * @author Michael Brusegard
 * @version 2.0.0
 */
public class ExerciseViewController extends BaseController {
  /**
   * Imported TextField from javaFx,
   * used for acquiring amount of sets for an exercise.
   */
  @FXML
  private TextField sets;
  /**
   * Imported TextField from javaFx,
   * used for setting the minimum amount of reps for an exercise.
   */
  @FXML
  private TextField repMin;
  /**
   * Imported TextField from javaFx,
   * used for setting the maximum amount of reps for an exercise.
   */
  @FXML
  private TextField repMax;
  /**
   * Imported TextField from javaFx,
   * used for setting the weight for an exercise.
   */
  @FXML
  private TextField weight;
  /**
   * Imported Text from javaFx, used to display the name of an exercise.
   */
  @FXML
  private Text name;
  /**
   * Imported ListView from javaFx, used to display a list of exercises.
   */
  @FXML
  private ListView<String> list;
  /**
   * Imported Button from javaFx, used to cancel creating a workout.
   */
  @FXML
  private Button cancelButton;

  /**
   * Initializes the controller and sets up the user interface elements.
   * <p>
   * This method is called when the controller is initialized
   * and is responsible for performing the following tasks:
   * 1. Load a list of exercises from the Server.
   * 2. Converts the loaded exercise names to an ObservableList.
   * 3. Sets the loaded exercises to the ListView.
   * 4. Binds the text property of the 'name' Text element
   * to the selected item in the ListView.
   * </p>
   */
  @FXML
  public void initialize() {
    // Update the list view with the exercises
    // Wait for the UI to be initialized
    Platform.runLater(() -> {
      // Load the list of exercises from the server
      List<String> exercisesList = getMainController().getUser()
          .getExerciseList();

      // Convert the List to an ObservableList
      ObservableList<String> exercises = FXCollections
          .observableArrayList(exercisesList);

      // Set the loaded exercises to the ListView
      list.setItems(exercises);

      // Update the name text when an exercise is selected in the list view
      name.textProperty().bind(list.getSelectionModel().selectedItemProperty());
    });
  }

  // When the user clicks the add Exercise button
  @FXML
  private void addExercise() {
    String exerciseName = name.getText();
    String setsText = sets.getText();
    String repMinText = repMin.getText();
    String repMaxText = repMax.getText();
    String weightText = weight.getText();

    if (ExerciseView.validateExerciseInput(exerciseName, setsText, repMinText,
        repMaxText, weightText)) {
      int exerciseSets = Integer.parseInt(setsText);
      int exerciseRepMin = Integer.parseInt(repMinText);
      int exerciseRepMax = Integer.parseInt(repMaxText);
      int exerciseWeight = Integer.parseInt(weightText);

      // Add the exercise to the new workout
      try {
        getMainController().getUser().addExerciseToCurrentWorkout(
            exerciseName, exerciseSets, exerciseRepMin,
            exerciseRepMax, exerciseWeight);
      } catch (IOException e) {
        UiUtils.showAlert("Error",
            e.getMessage(),
            AlertType.ERROR);
        return;
      }

      ExerciseView.displayExerciseAddedPrompt(exerciseName, exerciseSets,
          exerciseRepMin, exerciseRepMax, exerciseWeight);

      // Clear the input fields so a new exercise can be added
      clearInputFields();
    }
  }

  // When the user clicks the cancel button
  @FXML
  private void cancel() {
    if (UiUtils.showConfirmation("Cancel Workout",
        "Are you sure you want to cancel the workout? "
            + "All progress will be lost.")) {
      clearInputFields();
      try {
        getMainController().getUser().removeCurrentWorkout();
      } catch (IOException e) {
        UiUtils.showAlert("Error",
            e.getMessage(),
            AlertType.ERROR);
        return;
      }
      getMainController().showFxml("Home");
    }
  }

  // When the user clicks the finish button
  @FXML
  private void finish() throws IOException {
    // Check if the workout object is not null
    if (getMainController().getUser().getCurrentWorkout().getExercises().size() == 0) {
      UiUtils.showAlert("Error",
          "No exercises added to the workout.",
          AlertType.ERROR);
      return;
    }
    clearInputFields();
    // Load overview
    getMainController().showFxml("Overview");
  }

  /**
   * Initializes the controller's state and UI elements.
   *
   * <p>
   * This method is an overridden implementation of the base initialization
   * method. It initializes the controller's state and user interface (UI)
   * elements. In this case, it controls the visibility of the "Cancel" button
   * based on the saved status of the current workout associated with the user.
   * If the current workout is saved, the "Cancel" button is hidden; otherwise,
   * it is shown.
   * </p>
   */
  @Override
  public void init() {
    cancelButton.setVisible(
        !getMainController().getUser().getCurrentWorkout().getSaved());
  }

  // Clear the input fields
  private void clearInputFields() {
    sets.setText("");
    repMin.setText("");
    repMax.setText("");
    weight.setText("");
    list.getSelectionModel().clearSelection();
  }
}
