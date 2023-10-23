package workoutplanner.ui;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import workoutplanner.core.User;

/**
 *
 * @since 2.0.0
 * @author Erlend Løken Sæveraas
 * @version 2.0.0
 */

public class MainController {

  @FXML
  private VBox exerciseView;
  @FXML
  private VBox home;
  @FXML
  private VBox overview;
  @FXML
  private VBox workoutView;
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
    workoutView.setVisible(false);
    this.fxmlContainers = Arrays.asList(home, exerciseView, overview, workoutView);
    controllers = Arrays.asList(homeController, exerciseViewController, overviewController, workoutViewController);

    for (Controller c : controllers) {
      c.setMainController(this);
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

  /**
   * Shows the new window and hides the previous window.
   *
   * @param resource the new fxml
   */
  public void showFXML(String resource) {
    fxmlContainers.get(this.currentContainer).setVisible(false);
    for (VBox container : fxmlContainers) {
      if (containerString.get(fxmlContainers.indexOf(container)).equals(resource)) {
        // Create a new workout if the user goes to the exercise view from home
        if (resource.equals("ExerciseView") && this.currentContainer == 0) {
          getUser().createWorkout();
        }
        if (resource.equals("Overview") && this.currentContainer == 1) {
          this.overviewController.init();
        }
        if (resource.equals("WorkoutView") && this.currentContainer == 2) {
          this.workoutViewController.init();
        }

        container.setVisible(true);
        this.currentContainer = fxmlContainers.indexOf(container);
      }
    }
  }
}
