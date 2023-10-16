package workoutplanner.ui;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import workoutplanner.core.Exercise;
import workoutplanner.core.Workout;
import workoutplanner.fxutil.Controller;
import workoutplanner.fxutil.ExerciseLoader;
import workoutplanner.fxutil.PageLoader;
import workoutplanner.fxutil.UIUtils;

/**
 * <h1>ExerciseViewController.</h1>
 * <p>
 *   The ExerciseViewController class is responsible for managing
 *   the user interface and user interactions
 *   related to creating and adding exercises to a workout.
 * </p>
 *
 * @since 1.0.0
 * @author Michael Brusegard
 * @version 1.4.0
 */
public class ExerciseViewController implements Controller {
  /**
   * Local Workout variable, used to editing the workout that being made.
   */
  private final Workout workout = new Workout();
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
   *<p>
   *   This method is called when the controller is initialized
   *   and is responsible for performing the following tasks:
   *   1. Load a list of exercises from a JSON file using ExerciseLoader.
   *   2. Converts the loaded exercise names to an ObservableList.
   *   3. Sets the loaded exercises to the ListView.
   *   4. Binds the text property of the 'name' Text element
   *   to the selected item in the ListView.
   *</p>
   *
   * @throws IOException If an I/O error occurs while loading exercises
   *                    from the JSON file.
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

  @FXML
  private void addExercise() {
    // Get the name of the exercise
    String exerciseName = name.getText();

    // Check if the user has selected an exercise and filled in all the fields
    if (exerciseName == null) {
      UIUtils.showAlert("Error", "Please select an exercise.", AlertType.ERROR);
      return;
    } else if (sets.getText().isEmpty()
            || repMin.getText().isEmpty()
            || repMax.getText().isEmpty()
            || weight.getText().isEmpty()) {
      UIUtils.showAlert("Error", "Please fill in all fields.", AlertType.ERROR);
      return;
    }

    try {
      int exerciseSets = Integer.parseInt(sets.getText());
      int exerciseRepMin = Integer.parseInt(repMin.getText());
      int exerciseRepMax = Integer.parseInt(repMax.getText());
      int exerciseWeight = Integer.parseInt(weight.getText());

      // Check if the user has entered valid values
      if (exerciseRepMin > exerciseRepMax) {
        UIUtils.showAlert("Error",
                "The minimum amount of reps cannot be greater than "
                        + "the maximum amount of reps.",
                AlertType.ERROR);
        return;
      } else if (exerciseRepMin < 0
              || exerciseRepMax == 0
              || exerciseSets <= 0
              || exerciseWeight < 0) {
        UIUtils.showAlert("Error",
                        "You can't do negative reps or weight. "
                                + "Also you need to have at least one set "
                                + "and at least one max repetition "
                                + "in the rep-range.",
                        AlertType.ERROR);
        return;
      }

      // Add the exercise to the workout
      workout.addExercise(
                    new Exercise(exerciseName,
                            exerciseSets,
                            exerciseRepMin,
                            exerciseRepMax,
                            exerciseWeight));

      // Show an alert with exercise details that have been added to the workout
      String alertContent = "Exercise has been added to the workout "
              + "with the following details:\n\n"
              + "Name: " + exerciseName + "\n"
              + "Sets: " + exerciseSets + "\n"
              + "Rep-range: " + exerciseRepMin + "-" + exerciseRepMax + "\n"
              + "Weight: " + exerciseWeight + "kg";

      UIUtils.showAlert("Exercise Added", alertContent, AlertType.INFORMATION);

      // Clear the input fields
      sets.setText("");
      repMin.setText("");
      repMax.setText("");
      weight.setText("");

      // Deselect the exercise in the list view
      list.getSelectionModel().clearSelection();

    } catch (NumberFormatException e) {
      UIUtils.showAlert("Error",
              "Please enter a number for sets, rep-range, and weight.",
              AlertType.ERROR);
    }
  }

  @FXML
  private void finishAddingExercises() throws IOException {
    // Check if the workout object is not null
    if (workout.getExerciseCount() == 0) {
      UIUtils.showAlert("Error",
              "No exercises added to the workout.",
              AlertType.ERROR);
      return;
    }
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Overview.fxml"));
    Controller controller = PageLoader.pageLoader(loader, finishButton);
    OverviewController overviewController = (OverviewController) controller;
    overviewController.init(workout);
  }

  /**
   * Retrieves the Workout associated with this controller.
   * <p>
   *   This method returns the Workout object
   *   associated with this controller, which contains
   *   information about a specific workout.
   *   It allows external code to access and manipulate
   *   the workout data for various purposes.
   * </p>
   *
   * @return The Workout object associated with this controller.
   */
  public Workout getWorkout() {
    return workout;
  }
}
