package workoutplanner.fxui;

import javafx.scene.layout.VBox;

/**
 * <h1>FXMLControllerPair</h1>
 * Maintains the association between an FXML file and its corresponding
 * Controller.
 *
 * <p>
 * The FxmlControllerPair class maintains information about an FXML view
 * (represented by a VBox) and its associated BaseController instance. It allows
 * users to manage and interact with FXML views and their corresponding
 * controllers within the workout planner program.
 * </p>
 *
 * @since 3.0.0
 * @author Michael Brusegard
 * @version 3.0.0
 */
public class FxmlControllerPair {
  /**
   * Local Vbox variable, used for defining where the page will be displayed.
   */
  private final VBox fxml;
  /**
   * Local BaseController variable, used for reference. (?)
   */
  private final BaseController baseController;

  /**
   * Constructs a new FxmlControllerPair instance.
   *
   * <p>
   * This constructor creates a new FxmlControllerPair object, which associates
   * an FXML view (represented by a VBox) with a BaseController instance and a
   * MainController. It sets the visibility of the associated FXML view to
   * "false" and establishes the connection with the provided MainController.
   * </p>
   *
   * @param mainController      The MainController instance used for
   *                            application-wide
   *                            functionality.
   * @param inputFxml           The FXML view (VBox) associated with the
   *                            controller.
   * @param inputBaseController The BaseController instance linked to the FXML
   *                            view.
   */
  public FxmlControllerPair(final MainController mainController,
      final VBox inputFxml,
      final BaseController inputBaseController) {
    this.fxml = inputFxml;
    this.baseController = inputBaseController;
    this.fxml.setVisible(false);
    this.baseController.setMainController(mainController);
  }

  /**
   * Shows the controller's associated view and initializes its state.
   *
   * <p>
   * This method displays the view associated with the controller by setting its
   * visibility to "true." Additionally, it initializes the state of the
   * controller and its associated user interface (UI) elements by invoking the
   * {@link BaseController#init()} method of the base controller.
   * </p>
   */
  public void show() {
    fxml.setVisible(true);
    baseController.init();
  }

  /**
   * Hides the controller's associated view.
   *
   * <p>
   * This method hides the view associated with the controller by setting its
   * visibility to "false." It is typically used to hide or close the UI element
   * represented by this controller.
   * </p>
   */
  public void hide() {
    fxml.setVisible(false);
  }
}
