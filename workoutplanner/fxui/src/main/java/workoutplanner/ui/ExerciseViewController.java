package workoutplanner.ui;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import workoutplanner.fxutil.ExerciseLoader;
import workoutplanner.fxutil.ExerciseView;
import workoutplanner.fxutil.UIUtils;

/**
 * <h1>ExerciseViewController.</h1>
 * <p>
 * The ExerciseViewController class is responsible for managing
 * the user interface and user interactions
 * related to creating and adding exercises to a workout.
 * </p>
 *
 * @since 1.0.0
 * @author Michael Brusegard
 * @version 2.0.0
 */
public class ExerciseViewController extends Controller {
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
   * Imported Button from javaFx, used to finish creating a workout.
   */
  @FXML
  private Button finishButton;

  /**
   * Initializes the controller and sets up the user interface elements.
   * <p>
   * This method is called when the controller is initialized
   * and is responsible for performing the following tasks:
   * 1. Load a list of exercises from a JSON file using ExerciseLoader.
   * 2. Converts the loaded exercise names to an ObservableList.
   * 3. Sets the loaded exercises to the ListView.
   * 4. Binds the text property of the 'name' Text element
   * to the selected item in the ListView.
   * </p>
   *
   * @throws IOException If an I/O error occurs while loading exercises
   *                     from the JSON file.
   */
  @FXML
  public void initialize() throws IOException {
    // Update the list view with the exercises
    // Use ExerciseLoader to load exercises from the JSON file
    List<String> exercisesList = ExerciseLoader.loadExercisesFromJson();

    // Convert the List to an ObservableList
    ObservableList<String> exercises = FXCollections
        .observableArrayList(exercisesList);

    // Set the loaded exercises to the ListView
    list.setItems(exercises);

    // Update the name text when an exercise is selected in the list view
    name.textProperty().bind(list.getSelectionModel().selectedItemProperty());
  }

  // When the user clicks the add Exercise button
  @FXML
  private void addExercise() {
    String exerciseName = name.getText();
    String setsText = sets.getText();
    String repMinText = repMin.getText();
    String repMaxText = repMax.getText();
    String weightText = weight.getText();

    if (ExerciseView.validateExerciseInput(exerciseName, setsText, repMinText, repMaxText, weightText)) {
      int exerciseSets = Integer.parseInt(setsText);
      int exerciseRepMin = Integer.parseInt(repMinText);
      int exerciseRepMax = Integer.parseInt(repMaxText);
      int exerciseWeight = Integer.parseInt(weightText);

      // Add the exercise to the new workout
      getMainController().getUser().getLatestWorkout().addExercise(exerciseName, exerciseSets, exerciseRepMin,
          exerciseRepMax, exerciseWeight);

      ExerciseView.displayExerciseAddedPrompt(exerciseName, exerciseSets, exerciseRepMin, exerciseRepMax,
          exerciseWeight);

      // Clear the input fields so a new exercise can be added
      clearInputFields();
    }
  }

  // When the user clicks the cancel button
  @FXML
  private void cancel() throws IOException {
    if (UIUtils.showConfirmation("Cancel Workout",
        "Are you sure you want to cancel the workout? "
            + "All progress will be lost.")) {
      clearInputFields();
      getMainController().getUser().removeLatestWorkout();
      getMainController().showFXML("Home");
    }
  }

  // When the user clicks the finish button
  @FXML
  private void finish() throws IOException {
    // Check if the workout object is not null
    if (getMainController().getUser().getLatestWorkout().getExerciseCount() == 0) {
      UIUtils.showAlert("Error",
          "No exercises added to the workout.",
          AlertType.ERROR);
      return;
    }
    clearInputFields();
    getMainController().showFXML("Overview");
  }

  // Start a new workout
  public void init() {
    getMainController().getUser().createWorkout();
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
