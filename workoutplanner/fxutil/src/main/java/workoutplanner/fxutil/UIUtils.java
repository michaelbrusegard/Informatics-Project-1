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
}
