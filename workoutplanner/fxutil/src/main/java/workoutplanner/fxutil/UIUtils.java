package workoutplanner.fxutil;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * <h1>UIUtils.</h1>
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

public class UIUtils {
    // Show an alert with the given title, message, and alert type
    public static void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Button errorButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        errorButton.setId("alertButton");
        alert.showAndWait();
    }

    // Show a confirmation dialog with the given title and message
    public static boolean showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType okButton = new ButtonType("OK", ButtonType.OK.getButtonData());
        ButtonType cancelButton = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());

        alert.getButtonTypes().setAll(okButton, cancelButton);

        // Show and wait for the user's response
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        return result == okButton;
    }
}