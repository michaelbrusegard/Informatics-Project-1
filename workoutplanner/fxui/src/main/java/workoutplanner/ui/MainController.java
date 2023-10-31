package workoutplanner.ui;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import workoutplanner.core.User;

/**
 *
 * @since 2.0.0
 * @author Erlend Løken Sæveraas + Michael
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
  private int currentContainer = 0;
  private List<VBox> fxmlContainers;
  private List<String> containerString = Arrays.asList("Home", "ExerciseView", "Overview", "WorkoutView");
  private List<Controller> controllers;

  @FXML
  private void initialize() {
    initializeContainers();
    connectControllers();
    loadStylesheets();
    showContainer("Home");
  }

  private void initializeContainers() {
    fxmlContainers = Arrays.asList(home, exerciseView, overview, workoutView);
    fxmlContainers.forEach(container -> container.setVisible(false));
  }

  private void connectControllers() {
    controllers = Arrays.asList(homeController, exerciseViewController, overviewController, workoutViewController);
    controllers.forEach(controller -> controller.setMainController(this));
  }

  private void loadStylesheets() {
    overview.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    exerciseView.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    workoutView.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
  }

  private void showContainer(String containerName) {
    fxmlContainers.forEach(container -> container.setVisible(container.getId().equalsIgnoreCase(containerName)));
  }

  /**
   * Returns the user.
   *
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * Shows the new window and hides the previous window.
   *
   * @param resource the new fxml
   */
  public void showFXML(String resource) {
    VBox currentContainer = fxmlContainers.get(this.currentContainer);
    currentContainer.setVisible(false);

    int resourceIndex = containerString.indexOf(resource);
    if (resourceIndex != -1) {
      VBox newContainer = fxmlContainers.get(resourceIndex);
      newContainer.setVisible(true);
      this.currentContainer = resourceIndex;

      if (resource.equals("ExerciseView")) {
        this.exerciseViewController.init();
      } else if (resource.equals("Overview")) {
        this.overviewController.init();
      } else if (resource.equals("WorkoutView")) {
        this.workoutViewController.init();
      }
    }
  }

}
