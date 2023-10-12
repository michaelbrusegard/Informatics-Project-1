package workoutplanner.fxutil;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UIUtils {

    // Show an alert with the given title, message, and alert type
    public static void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}