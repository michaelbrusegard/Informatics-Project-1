package workoutplanner.fxutil;

import javafx.scene.control.Alert.AlertType;

/**
 * <h1>ExerciseView</h1>
 * <p>
 * The ExerciseView class is a helper class used
 * for validation and displaying prompts related to
 * adding exercises to a workout.
 * </p>
 *
 * @since 2.0.0
 * @author Michael Brusegard
 * @version 2.0.0
 */
public class ExerciseView {

  // Validate the input for creating an exercise
  public static boolean validateExerciseInput(
      String exerciseName, String setsText, String repMinText, String repMaxText, String weightText) {

    // Check if the user has selected an exercise
    if (exerciseName == null) {
      UIUtils.showAlert("Error", "Please select an exercise.", AlertType.ERROR);
      return false;
      // Check if the user has filled in all fields
    } else if (setsText.isEmpty() || repMinText.isEmpty() || repMaxText.isEmpty() || weightText.isEmpty()) {
      UIUtils.showAlert("Error", "Please fill in all fields.", AlertType.ERROR);
      return false;
    }

    // Check if the user has entered a number for all fields
    try {
      int exerciseSets = Integer.parseInt(setsText);
      int exerciseRepMin = Integer.parseInt(repMinText);
      int exerciseRepMax = Integer.parseInt(repMaxText);
      int exerciseWeight = Integer.parseInt(weightText);

      // Check if the user has entered valid values for all fields
      if (exerciseRepMin > exerciseRepMax) {
        UIUtils.showAlert("Error", "The minimum amount of reps cannot be greater than the maximum amount of reps.",
            AlertType.ERROR);
        return false;
      } else if (exerciseRepMin < 0 || exerciseRepMax == 0 || exerciseSets <= 0 || exerciseWeight < 0) {
        UIUtils.showAlert("Error",
            "You can't do negative reps or weight. Also, you need to have at least one set and at least one max repetition in the rep-range.",
            AlertType.ERROR);
        return false;
      } else if (exerciseRepMin > 5000 || exerciseRepMax > 5000 || exerciseSets > 5000 || exerciseWeight > 5000) {
        UIUtils.showAlert("Error", "Please enter a number less than 5000. You are not that strong.", AlertType.ERROR);
        return false;
      }
    } catch (NumberFormatException e) {
      UIUtils.showAlert("Error", "Please enter a number for sets, rep-range, and weight.", AlertType.ERROR);
      return false;
    }

    return true;
  }

  // Display a prompt that the exercise has been added with information about the
  // exercise
  public static void displayExerciseAddedPrompt(String exerciseName, int exerciseSets, int exerciseRepMin,
      int exerciseRepMax, int exerciseWeight) {
    String alertContent = "Exercise has been added to the workout "
        + "with the following details:\n\n"
        + "Name: " + exerciseName + "\n"
        + "Sets: " + exerciseSets + "\n"
        + "Rep-range: " + exerciseRepMin + "-" + exerciseRepMax + "\n"
        + "Weight: " + exerciseWeight + "kg";

    UIUtils.showAlert("Exercise Added", alertContent, AlertType.INFORMATION);
  }
}
