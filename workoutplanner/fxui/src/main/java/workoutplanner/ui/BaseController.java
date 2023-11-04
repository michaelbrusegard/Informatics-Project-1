package workoutplanner.ui;

import javafx.fxml.FXML;

/**
 * <h1>BaseController</h1>
 * <p>
 * The BaseController class gives access to the MainController object for all
 * sub-controllers and defines a base init method to be overridden.
 * </p>
 *
 * @since 1.4.0
 * @author Michael Brusegard
 * @version 2.0.0
 */
public class BaseController {
    @FXML
    private MainController mainController;

    // Return the MainController object so that other controllers can access the
    // same User object
    public MainController getMainController() {
        return mainController;
    }

    // Set the MainController object a sub-controller is using
    public void setMainController(MainController mc) {
        this.mainController = mc;
    }

    // Base init method to be overridden by sub-controllers
    public void init() {
    }
}
