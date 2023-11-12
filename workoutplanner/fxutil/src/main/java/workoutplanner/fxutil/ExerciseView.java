package workoutplanner.fxutil;

import javafx.scene.control.Alert.AlertType;

/**
 * <h1>ExerciseView</h1>
 *
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

  /**
   * Local int variable, used for defining a numerical limit for reps,
   * sets and weight.
   */
  private static final int LIMIT = 5000;

  /**
   * Validates user input for creating an exercise.
   * <p>
   * This static method validates the user input when creating an exercise.
   * It checks if the provided exercise name is not null, if all fields (sets,
   * repMin, repMax, weight) are filled in, and if the entered values are
   * numeric and within valid ranges. It also ensures that the minimum number of
   * reps is not greater than the maximum number of reps. If any validation
   * check fails, it displays an error message using `UiUtils.showAlert()` and
   * returns false.
   * </p>
   *
   * @param exerciseName The name of the selected exercise.
   * @param setsText     The user input for the number of sets.
   * @param repMinText   The user input for the minimum number of reps.
   * @param repMaxText   The user input for the maximum number of reps.
   * @param weightText   The user input for the weight.
   * @return True if the input is valid, false otherwise.
   */
  public static boolean validateExerciseInput(final String exerciseName,
      final String setsText,
      final String repMinText,
      final String repMaxText,
      final String weightText) {
    // Validate the input for creating an exercise
    // Check if the user has selected an exercise
    if (exerciseName == null) {
      UiUtils.showAlert("Error", "Please select an exercise.", AlertType.ERROR);
      return false;
      // Check if the user has filled in all fields
    } else if (setsText.isEmpty()
        || repMinText.isEmpty()
        || repMaxText.isEmpty()
        || weightText.isEmpty()) {
      UiUtils.showAlert("Error", "Please fill in all fields.", AlertType.ERROR);
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
        UiUtils.showAlert("Error", "The minimum amount of reps "
            + "cannot be greater than the maximum amount of reps.",
            AlertType.ERROR);
        return false;
      } else if (exerciseRepMin < 0
          || exerciseRepMax <= 0
          || exerciseSets <= 0
          || exerciseWeight < 0) {
        UiUtils.showAlert("Error",
            "You can't do negative reps or weight. "
                + "Also, you need to have at least one set "
                + "and at least one max repetition in the rep-range.",
            AlertType.ERROR);
        return false;
      } else if (exerciseRepMin > LIMIT
          || exerciseRepMax > LIMIT
          || exerciseSets > LIMIT
          || exerciseWeight > LIMIT) {
        UiUtils.showAlert("Error", "Please enter a number less "
            + "than 5000. You are not that strong.", AlertType.ERROR);
        return false;
      }
    } catch (NumberFormatException e) {
      UiUtils.showAlert("Error", "Please enter a number for "
          + "sets, rep-range, and weight.", AlertType.ERROR);
      return false;
    }

    return true;
  }

  /**
   * Displays a prompt confirming the addition of an exercise to the workout.
   * <p>
   * This static method displays an information prompt to confirm that an
   * exercise has been successfully added to the workout. The prompt includes
   * details of the added exercise, such as its name, the number of sets, the
   * range of repetitions, and the weight.
   * </p>
   *
   * @param exerciseName   The name of the added exercise.
   * @param exerciseSets   The number of sets for the added exercise.
   * @param exerciseRepMin The minimum number of repetitions for the added
   *                       exercise.
   * @param exerciseRepMax The maximum number of repetitions for the added
   *                       exercise.
   * @param exerciseWeight The weight for the added exercise in kilograms.
   */
  public static void displayExerciseAddedPrompt(final String exerciseName,
      final int exerciseSets,
      final int exerciseRepMin,
      final int exerciseRepMax,
      final int exerciseWeight) {
    // Display a prompt that the exercise has been added with information about
    // the exercise
    String alertContent = "Exercise has been added to the workout "
        + "with the following details:\n\n"
        + "Name: " + exerciseName + "\n"
        + "Sets: " + exerciseSets + "\n"
        + "Rep-range: " + exerciseRepMin + "-" + exerciseRepMax + "\n"
        + "Weight: " + exerciseWeight + "kg";

    UiUtils.showAlert("Exercise Added", alertContent, AlertType.INFORMATION);
  }
}
