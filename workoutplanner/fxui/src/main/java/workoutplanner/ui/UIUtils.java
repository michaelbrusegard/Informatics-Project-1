package workoutplanner.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

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