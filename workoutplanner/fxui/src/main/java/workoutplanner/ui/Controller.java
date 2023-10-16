package workoutplanner.ui;

import javafx.fxml.FXML;

/**
 * <h1>Controller.</h1>
 * <p>
 * The Controller class gives access to the MainController object for all
 * sub-controllers.
 * </p>
 *
 * @since 1.4.0
 * @version 2.0.0
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
    public void setMainController(MainController mc) {
        this.mainController = mc;
    }
}
