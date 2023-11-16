package workoutplanner.fxui;

import javafx.fxml.FXML;

/**
 * <h1>BaseController</h1>
 * The BaseController class provides access to the MainController object for
 * all sub-controllers and defines a base init method to be overridden.
 *
 * <p>
 * The BaseController class serves as the base for all other controllers,
 * allowing access to the MainController object and defining a base
 * initialization method that can be customized by sub-controllers. It provides
 * the foundation for managing and interacting with the program's various
 * controllers and their functionalities.
 * </p>
 *
 * @since 2.0.0
 * @author Michael Brusegard
 * @version 2.0.0
 */
public class BaseController {
  /**
   * Local MainController variable, used for running an instance of the program.
   */
  @FXML
  private MainController mainController;

  /**
   * Returns the MainController object.
   *
   * <p>
   * This method provides access to the MainController object, allowing other
   * controllers to access and interact with the same MainController instance.
   * </p>
   *
   * @return The MainController object for accessing application-wide
   *         functionality.
   */
  public MainController getMainController() {
    // Return the MainController object so that other controllers can access the
    // same User object
    return mainController;
  }

  /**
   * Sets the MainController object for a sub-controller.
   *
   * <p>
   * This method allows you to set the MainController object that a
   * sub-controller is using. It updates the current MainController instance
   * with the provided MainController object.
   * </p>
   *
   * @param inputMainController The MainController object to set for the
   *                            sub-controller.
   */
  public void setMainController(final MainController inputMainController) {
    // Set the MainController object a sub-controller is using
    mainController = inputMainController;
  }

  /**
   * Base initialization method to be overridden by sub-controllers.
   *
   * <p>
   * This method is a base initialization method provided for customization by
   * sub-controllers. Sub-controllers can override this method to perform their
   * own specific initialization tasks when needed.
   * </p>
   */
  public void init() {
    // Base init method to be overridden by sub-controllers
  }
}
