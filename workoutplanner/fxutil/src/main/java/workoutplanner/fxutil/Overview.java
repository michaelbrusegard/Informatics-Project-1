package workoutplanner.fxutil;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * <h1>OverviewController</h1>
 * Handles error validation and user interaction for the workout overview.
 *
 * <p>
 * The Overview class is responsible for error handling for the overview class.
 * </p>
 *
 * @since 2.0.0
 * @author Erlend Løken Sæveraas
 * @version 2.0.0
 */
public class Overview {
  /**
   * Local int variable, used to limit characters in Input-field.
   */
  private static final int CHARLIMIT = 20;

  /**
   * Validates the overview of a workout, including saving and closing actions.
   *
   * <p>
   * This method validates the overview of a workout based on the provided
   * parameters and the input field's content. It checks if the input field is
   * empty when the 'saved' parameter is true and displays an error message if
   * it is. If the input exceeds the character limit, another error message is
   * shown. When the 'saved' parameter is true, an information message indicates
   * a successful save. If the 'close' parameter is true, the method returns
   * true, indicating that the overview is valid and can be closed.
   * </p>
   *
   * @param saved   A boolean indicating whether the overview should be saved.
   * @param close   A boolean indicating whether the overview should be closed.
   * @param inpName The TextField containing the workout name for validation.
   * @return True if the overview is valid and can be saved or closed; false if
   *         there are validation issues.
   */
  public static boolean validateOverview(final boolean saved,
                                         final boolean close,
                                         final TextField inpName) {
    if (inpName.getText().isEmpty() && saved) {
      UiUtils.showAlert("Empty input-field",
              "Input-field cannot be empty",
              AlertType.ERROR);
      return false;
    } else if (inpName.getText().length() >= CHARLIMIT) {
      UiUtils.showAlert("Too many characters in input-field",
              "Name shouldn't be more than 20 characters",
              AlertType.ERROR);
      return false;
    } else if (saved) {
      UiUtils.showAlert("Save successful",
              "Workout saved successfully",
              AlertType.INFORMATION);
      return true;
    } else if (close) {
      return true;
    }
    return true;
  }

  /**
   * Checks if the user wants to cancel a workout.
   *
   * <p>
   * This method displays a confirmation dialog to confirm whether the user
   * wants to cancel the current workout. If the user confirms the cancellation,
   * the method returns true; otherwise, it returns false. Cancelling a workout
   * results in the loss of all progress.
   * </p>
   *
   * @return True if the user confirms the cancellation; false if the user
   *         cancels the action.
   */
  public static boolean checkIfCancel() {
    return (UiUtils.showConfirmation("Cancel Workout",
        "Are you sure you want to cancel the workout? "
            + "All progress will be lost."));
  }
}
