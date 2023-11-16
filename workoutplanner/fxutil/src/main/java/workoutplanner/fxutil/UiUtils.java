package workoutplanner.fxutil;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * <h1>UIUtils</h1>
 * Provides utility methods for managing the user interface across all views.
 *
 * <p>
 * The UIUtils class is responsible for providing
 * utility methods for managing the user interface
 * related to all views of the application.
 * </p>
 *
 * @since 1.0.0
 * @author Michael Brusegard
 * @version 2.0.0
 */
public final class UiUtils {

  private UiUtils() {
    // Private constructor to prevent instantiation
    throw new UnsupportedOperationException(
            "Utility class should not be instantiated.");
  }

  /**
   * Shows an alert dialog with the specified title, message, and alert type.
   *
   * <p>
   * This method displays an alert dialog with the given title, message, and
   * alert type. It provides a way to show informative messages, warnings, or
   * errors to the user. The dialog includes an OK button for acknowledgment.
   * </p>
   *
   * @param title      The title of the alert dialog.
   * @param message    The message content of the alert dialog.
   * @param alertType  The type of the alert (e.g., INFORMATION, WARNING,
   *                   ERROR).
   */
  public static void showAlert(final String title, final String message,
                               final AlertType alertType) {
    // Show an alert with the given title, message, and alert type
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    Button errorButton = (Button) alert.getDialogPane()
            .lookupButton(ButtonType.OK);
    errorButton.setId("alertButton");
    alert.showAndWait();
  }

  /**
   * Shows a confirmation dialog with the specified title and message and waits
   * for the user's response.
   *
   * <p>
   * This method displays a confirmation dialog with the given title and message
   * and waits for the user's response. The user can confirm or cancel the
   * action using the "OK" and "Cancel" buttons. The method returns a boolean
   * value indicating whether the user confirmed the action.
   * </p>
   *
   * @param title    The title of the confirmation dialog.
   * @param message  The message content of the confirmation dialog.
   * @return True if the user confirmed the action; false if the user canceled.
   */
  public static boolean showConfirmation(final String title,
                                         final String message) {
    // Show a confirmation dialog with the given title and message
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);

    ButtonType okButton = new ButtonType("OK", ButtonType.OK.getButtonData());
    ButtonType cancelButton = new ButtonType("Cancel", ButtonType.CANCEL
            .getButtonData());

    alert.getButtonTypes().setAll(okButton, cancelButton);

    // Show and wait for the user's response
    ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
    return result == okButton;
  }
}
