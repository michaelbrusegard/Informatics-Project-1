package workoutplanner.ui;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import workoutplanner.core.User;

/**
 *
 * @since 2.0.0
 * @author Erlend Løken Sæveraas + Michael Brusegard
 * @version 2.0.0
 */

public class MainController {
  @FXML
  private VBox exerciseView, home, overview, workoutView;

  @FXML
  private HomeController homeController;
  @FXML
  private ExerciseViewController exerciseViewController;
  @FXML
  private OverviewController overviewController;
  @FXML
  private WorkoutViewController workoutViewController;

  private User user = new User();
  private Map<String, FXMLControllerPair> fxmlControllerMap = new HashMap<>();
  private String currentFXMLName = "Home";

  @FXML
  private void initialize() {
    fxmlControllerMap.put("Home", new FXMLControllerPair(this, home, homeController));
    fxmlControllerMap.put("ExerciseView", new FXMLControllerPair(this, exerciseView, exerciseViewController));
    fxmlControllerMap.put("Overview", new FXMLControllerPair(this, overview, overviewController));
    fxmlControllerMap.put("WorkoutView", new FXMLControllerPair(this, workoutView, workoutViewController));
    showFXML("Home");
  }

  /**
   * Shows the new window and hides the previous window.
   *
   * @param resource the new fxml
   */
  public void showFXML(String FXMLName) {
    if (fxmlControllerMap.containsKey(FXMLName)) {
      fxmlControllerMap.get(currentFXMLName).hide();
      fxmlControllerMap.get(FXMLName).show();
      currentFXMLName = FXMLName;
    }
  }

  /**
   * Returns the user.
   *
   * @return the user
   */
  public User getUser() {
    return user;
  }
}
