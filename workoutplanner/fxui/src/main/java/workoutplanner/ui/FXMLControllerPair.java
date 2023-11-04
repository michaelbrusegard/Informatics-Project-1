package workoutplanner.ui;

import javafx.scene.layout.VBox;

/**
 * <h1>FXMLControllerPair</h1>
 * <p>
 * Keeps track of a pair of FXML and Controller.
 * </p>
 *
 * @since 3.0.0
 * @author Michael Brusegard
 * @version 3.0.0
 */
public class FXMLControllerPair {
  private VBox fxml;
  private BaseController baseController;

  public FXMLControllerPair(MainController mainController, VBox fxml, BaseController baseController) {
    this.fxml = fxml;
    this.baseController = baseController;
    this.fxml.setVisible(false);
    this.baseController.setMainController(mainController);
  }

  public void show() {
    fxml.setVisible(true);
    baseController.init();
  }

  public void hide() {
    fxml.setVisible(false);
  }
}