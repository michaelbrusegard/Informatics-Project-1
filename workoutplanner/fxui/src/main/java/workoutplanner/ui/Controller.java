package workoutplanner.ui;

import javafx.fxml.FXML;

/**
 * Controller class for connecting all controllers together.
 */
public class Controller {
    @FXML
    private MainController mainController;

    // Return the MainController object so that other controllers can access the
    // same User object
    public MainController getMainController() {
        return mainController;
    }

    // Set the MainController object a sub-controller is using
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
