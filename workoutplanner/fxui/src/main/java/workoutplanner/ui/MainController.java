package workoutplanner.ui;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import workoutplanner.core.User;
import workoutplanner.ui.Controller;

public class MainController {

  @FXML
  private VBox exerciseView;
  @FXML
  private VBox home;
  @FXML
  private VBox overview;
  @FXML
  private VBox workoutsView;
  @FXML
  private HomeController homeController;
  @FXML
  private ExerciseViewController exerciseViewController;
  @FXML
  private OverviewController overviewController;
  @FXML
  private WorkoutViewController workoutViewController;

  private User user = new User();
  private int currentContainer = 0;
  private List<VBox> fxmlContainers;
  private List<String> containerString = Arrays.asList("ExerciseView",
      "Home", "Overview", "PlansView");
  private List<Controller> controllers;

  /**
   * Initializes the maincontroller, and sets only the neccessary window visible,
   * and connects the controllers.
   * 
   *
   */
  @FXML
  private void initialize() {
    // Hides unnecessary windows
    // Connects all controllers by connecting them to this MainController
    // Reads json file for persistency
    home.setVisible(true);
    exerciseView.setVisible(false);
    overview.setVisible(false);
    workoutsView.setVisible(false);
    this.fxmlContainers = Arrays.asList(exerciseView, home, overview, workoutsView);
    controllers = Arrays.asList(homeController, exerciseViewController, overviewController, workoutViewController);

    for (Controller c : controllers) {
      c.setMainController(this);
    }
  }

  public User getRemote() {
    return user;
  }

  /**
   * Shows the new window and hides the previous window.
   *
   * @param resource the new fxml
   */
  public void showFXML(String resource) {
    fxmlContainers.get(this.currentContainer).setVisible(false);
    for (VBox container : fxmlContainers) {
      if (containerString.get(fxmlContainers.indexOf(container)).equals(resource)) {
        container.setVisible(true);
        this.currentContainer = fxmlContainers.indexOf(container);
      }
    }
  }
}
