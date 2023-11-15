package workoutplanner.fxui;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import workoutplanner.core.User;
import workoutplanner.core.UserAccess;

/**
 * <h1>MainController</h1>
 * Represents the primary controller for the workout planner application.
 *
 * <p>
 * The MainController class manages user interactions and the application's
 * primary functionality. It allows the user to switch between different views,
 * such as the home view, exercise view, overview, and workout view. It also
 * provides access to the User object, enabling interaction with the user's
 * workout data.
 * </p>
 *
 * @since 2.0.0
 * @author Erlend Løken Sæveraas + Michael Brusegard
 * @version 2.0.0
 */

public class MainController {
  /**
   * Local Vbox variable, used for containing and switching to the
   * ExerciseViewController.
   */
  @FXML
  private VBox exerciseView;

  /**
   * Local Vbox variable, used for containing and switching to the
   * HomeController.
   */
  @FXML
  private VBox home;

  /**
   * Local Vbox variable, used for containing and switching to the
   * OverviewController.
   */
  @FXML
  private VBox overview;

  /**
   * Local Vbox variable, used for containing and switching to the
   * WorkoutViewController.
   */
  @FXML
  private VBox workoutView;

  /**
   * Local HomeController variable, used for defining the HomeController.
   */
  @FXML
  private HomeController homeController;

  /**
   * Local ExerciseViewController variable, used for defining the
   * ExerciseViewController.
   */
  @FXML
  private ExerciseViewController exerciseViewController;

  /**
   * Local OverviewController variable, used for defining the
   * OverviewController.
   */
  @FXML
  private OverviewController overviewController;

  /**
   * Local WorkoutViewController variable, used for defining the
   * WorkoutViewController.
   */
  @FXML
  private WorkoutViewController workoutViewController;

  /**
   * User object, is the current user object that is used to encapsulate
   * the workouts.
   */
  private final UserAccess user;

  /**
   * Local Map variable, used to register the different controllers.
   */
  private final Map<String, FxmlControllerPair> fxmlControllerMap = new HashMap<>();

  /**
   * Local String variable, used to define which controller should be used.
   */
  private String currentFxmlName = "Home";

  /**
   * Local boolean variable, used to choose between running with rest server or
   * not.
   */
  private final boolean useRemote = true;

  /**
   * Local String variable, used for referring to server port.
   */
  private final String remoteUrl = "http://localhost:8080/";

  /**
   * Constructs a new MainController instance.
   *
   * <p>
   * This constructor creates a new MainController object. The MainController
   * serves as the primary controller for the workout planner application and
   * manages user interactions and the application's main functionality.
   * </p>
   */

  public MainController() {
    if (useRemote) {
      user = new RemoteUserAccess(URI.create(remoteUrl));
    } else {
      user = new User(true);
    }
  }

  @FXML
  private void initialize() {
    fxmlControllerMap.put("Home",
        new FxmlControllerPair(this, home, homeController));
    fxmlControllerMap.put("ExerciseView",
        new FxmlControllerPair(this, exerciseView, exerciseViewController));
    fxmlControllerMap.put("Overview",
        new FxmlControllerPair(this, overview, overviewController));
    fxmlControllerMap.put("WorkoutView",
        new FxmlControllerPair(this, workoutView, workoutViewController));
    showFxml("Home");
  }

  /**
   * Shows an FXML view by its name and hides the previously displayed view.
   *
   * <p>
   * This method displays the FXML view associated with the provided name while
   * hiding the previously displayed view. It ensures that only one view is
   * visible at a time. The name of the FXML view to display is passed as the
   * argument, and it is used to look up the associated controller.
   * </p>
   *
   * @param fxmlName The name of the FXML view to display.
   */
  public void showFxml(final String fxmlName) {
    if (fxmlControllerMap.containsKey(fxmlName)) {
      fxmlControllerMap.get(currentFxmlName).hide();
      fxmlControllerMap.get(fxmlName).show();
      currentFxmlName = fxmlName;
    }
  }

  /**
   * Retrieves the User object associated with the application.
   *
   * <p>
   * This method provides access to the User object, which represents the user
   * of the workout planner application. It allows other parts of the
   * application to access and interact with the user's data and workouts.
   * </p>
   *
   * @return The User object representing the user of the application.
   */
  public UserAccess getUser() {
    return user;
  }
}
