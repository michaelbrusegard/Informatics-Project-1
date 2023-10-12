package workoutplanner.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class HomeControllerTest extends ApplicationTest {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("Home.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    public void testNewWorkoutButton() {
        // Get the initial scene
        Scene initialScene = stage.getScene();

        // Click on the "newWorkout" button
        clickOn("#newWorkout");

        // Get the updated scene
        Scene updatedScene = stage.getScene();

        // Verify that the scene has changed
        Assertions.assertNotSame(initialScene, updatedScene);
    }

    @Test
    public void testAllWorkoutsButton() {
        // Get the initial scene
        Scene initialScene = stage.getScene();

        // Click on the "allWorkouts" button
        clickOn("#allWorkouts");

        // Get the updated scene
        Scene updatedScene = stage.getScene();

        // Verify that the scene has changed
        Assertions.assertNotSame(initialScene, updatedScene);
    }
}
