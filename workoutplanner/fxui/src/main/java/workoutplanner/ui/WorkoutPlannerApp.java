package workoutplanner.ui;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * <h1>WorkoutPlannerApp</h1>
 *
 * <p>
 * This class extends the JavaFX Application class
 * and is the main entry point for the application.
 * It initializes the main application window,
 * loads the UI from the "Overview.fxml" file, and sets up
 * the application icon. Additionally,
 * it provides the main method for launching the application.
 * </p>
 *
 * @since 1.0.0
 * @author Michael Brusegard
 * @version 1.4.0
 */
public class WorkoutPlannerApp extends Application {
  /**
   * Local int variable, used to define the minimum width of the stage.
   */
  private static final int MINWIDTH = 600;
  /**
   * Local int variable, used to define the minimum height of the stage.
   */
  private static final int MINHEIGHT = 428;

  /**
   * Starts the workout planner application by initializing
   * the main application window.
   * <p>
   * This method is called when the application is launched
   * and is responsible for performing the following tasks:
   * 1. Loads the main application UI from the "Home.fxml" file
   * using a FXMLLoader.
   * 2. Loads an application icon from the "/icon.png" resource.
   * 3. Sets the loaded icon as the application icon.
   * 4. Sets the main scene with the loaded UI.
   * 5. Displays the main application window.
   * </p>
   *
   * @param primaryStage The primary stage for this application,
   *                     onto which the application scene can be set.
   *                     Applications may create other stages, if needed,
   *                     but they will not be primary stages.
   * @throws IOException If an I/O error occurs while loading
   *                     the application UI or icon.
   */
  @Override
  public void start(final Stage primaryStage) throws IOException {
    // Set the app title
    primaryStage.setTitle("Workout Planner");

    // Set the application icon
    InputStream inputStream = this.getClass().getResourceAsStream("/icon.png");
    assert inputStream != null;
    Image icon = new Image(inputStream);
    primaryStage.getIcons().add(icon);

    // Set the minimum width and height for the stage
    primaryStage.setMinWidth(MINWIDTH); // Set the minimum width
    primaryStage.setMinHeight(MINHEIGHT); // Set the minimum height

    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass()
        .getResource("Main.fxml"));
    primaryStage.setScene(new Scene(fxmlLoader.load()));
    primaryStage.show();
  }

  /**
   * The entry point for launching the Workout Planner application.
   * <p>
   * This method is the main entry point for launching
   * the Workout Planner application.
   * It calls the JavaFX launch method to start the application.
   * </p>
   *
   * @param args An array of command-line arguments
   *             (not used in this application).
   */
  public static void main(final String[] args) {
    WorkoutPlannerApp.launch(args);
  }
}
