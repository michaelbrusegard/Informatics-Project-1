package workoutplanner.fxutil;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * <h1>OverviewController.</h1>
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
   * Validates for the cancel and save button
   * <p>
   * This method is responsible for validating the saving, and the cancelling for
   * the overview page.
   * </p>
   *
   * @param saved   checks if the save button is pressed.
   * @param close   checks if the cancel button is pressed.
   * @param inpName the value in the input field.
   */
  public static boolean validateOverview(final boolean saved, final boolean close, final TextField inpName) {
    if (inpName.getText().isEmpty() && saved) {
      UIUtils.showAlert("Empty input-field",
          "Input-field cannot be empty",
          AlertType.ERROR);
      return false;
    } else if (inpName.getText().length() >= CHARLIMIT) {
      UIUtils.showAlert("Too many characters in input-field",
          "Name shouldn't be more than 20 characters",
          AlertType.ERROR);
      return false;
    } else if (saved) {
      UIUtils.showAlert("Save successful",
          "Workout saved successfully",
          AlertType.INFORMATION);
      return true;
    } else if (close) {
      return true;
    }
    return true;
  }

  /**
   * Checks if the user wants to cancel.
   */
  public static boolean checkIfCancel() {
    return (UIUtils.showConfirmation("Cancel Workout",
        "Are you sure you want to cancel the workout? "
            + "All progress will be lost."));
  }
}
