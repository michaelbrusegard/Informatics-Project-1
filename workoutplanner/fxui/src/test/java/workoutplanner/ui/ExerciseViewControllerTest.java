package workoutplanner.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ExerciseViewControllerTest extends ApplicationTest {

    private Parent root;
    private ExerciseViewController exerciseViewController;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(ExerciseViewController.class.getResource("ExerciseView.fxml"));
        root = loader.load();
        exerciseViewController = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        exerciseViewController.initialize();
    }

    @Test
    public void testLabel() {
        ListView<String> list;
        try {
            list = (ListView) root.lookup("#list");
        } catch (Exception e) {
            throw new UnsupportedOperationException("Could not fint ListView object");
        }

        Assertions.assertNotNull(list);

        Assertions.assertEquals(list.getItems().size(), 43);

        Text text = (Text) root.lookup("#name");
        clickOn(list.getItems().get(0));
        Assertions.assertEquals(list.getItems().get(0), text.getText());

        clickOn(list.getItems().get(1));
        Assertions.assertEquals(list.getItems().get(1), text.getText());
    }

    @Test
    public void testAddValidExercise() {
        ListView<String> list;
        try {
            list = (ListView) root.lookup("#list");
        } catch (Exception e) {
            throw new UnsupportedOperationException("Could not fint ListView object");
        }
        list.getSelectionModel().select("Bench Press");
        clickOn(); // Select an exercise
        lookup("#sets").queryAs(TextField.class).setText("3");
        lookup("#repMin").queryAs(TextField.class).setText("5");
        lookup("#repMax").queryAs(TextField.class).setText("10");
        lookup("#weight").queryAs(TextField.class).setText("50");
        clickOn("#addExercise");

        // Check if the exercise details have been added to the workout
        // You may need to adapt this based on your specific implementation
        // assertEquals(1, exerciseViewController.getWorkout().getExerciseCount());
        clickOn("#alertButton");
    }

    @Test
    public void testAddInvalidExercise() {
        ListView<String> list;
        try {
            list = (ListView) root.lookup("#list");
        } catch (Exception e) {
            throw new UnsupportedOperationException("Could not fint ListView object");
        }
        list.getSelectionModel().select("Bench Press");
        lookup("#sets").queryAs(TextField.class).setText("3");
        lookup("#repMin").queryAs(TextField.class).setText("10");
        lookup("#repMax").queryAs(TextField.class).setText("5");
        lookup("#weight").queryAs(TextField.class).setText("50");
        clickOn("#addExercise");
        FxAssert.verifyThat("#alertButton", (button) -> !button.isDisabled());
        clickOn("#alertButton");

    }

    @Test
    public void testAddExerciseValidation() {
        ListView<String> list;
        try {
            list = (ListView) root.lookup("#list");
        } catch (Exception e) {
            throw new UnsupportedOperationException("Could not fint ListView object");
        }
        list.getSelectionModel().select("Bench Press");
        lookup("#sets").queryAs(TextField.class).setText("A");
        lookup("#repMin").queryAs(TextField.class).setText("5");
        lookup("#repMax").queryAs(TextField.class).setText("10");
        lookup("#weight").queryAs(TextField.class).setText("50");
        clickOn("#addExercise");
        FxAssert.verifyThat("#alertButton", (button) -> !button.isDisabled());
        clickOn("#alertButton");
    }

    @Test
    public void testAddExerciseNameValidation() {
        lookup("#sets").queryAs(TextField.class).setText("3");
        lookup("#repMin").queryAs(TextField.class).setText("5");
        lookup("#repMax").queryAs(TextField.class).setText("10");
        lookup("#weight").queryAs(TextField.class).setText("50");
        clickOn("#addExercise");
        FxAssert.verifyThat("#alertButton", (button) -> !button.isDisabled());
        clickOn("#alertButton");
    }

    @Test
    public void testAddExerciseFieldValidation() {
        ListView<String> list;
        try {
            list = (ListView) root.lookup("#list");
        } catch (Exception e) {
            throw new UnsupportedOperationException("Could not fint ListView object");
        }
        list.getSelectionModel().select("Bench Press");
        lookup("#sets").queryAs(TextField.class).setText("");
        lookup("#repMin").queryAs(TextField.class).setText("");
        lookup("#repMax").queryAs(TextField.class).setText("");
        lookup("#weight").queryAs(TextField.class).setText("");
        clickOn("#addExercise");
        FxAssert.verifyThat("#alertButton", (button) -> !button.isDisabled());
        clickOn("#alertButton");
    }

    @Test
    public void testAddExerciseintValidation() {
        ListView<String> list;
        try {
            list = (ListView) root.lookup("#list");
        } catch (Exception e) {
            throw new UnsupportedOperationException("Could not fint ListView object");
        }
        list.getSelectionModel().select("Bench Press");
        lookup("#sets").queryAs(TextField.class).setText("3");
        lookup("#repMin").queryAs(TextField.class).setText("-1");
        lookup("#repMax").queryAs(TextField.class).setText("10");
        lookup("#weight").queryAs(TextField.class).setText("50");
        clickOn("#addExercise");
        FxAssert.verifyThat("#alertButton", (button) -> !button.isDisabled());
        clickOn("#alertButton");
    }

    @Test
    public void testFinishedExercise() {
        ListView<String> list;
        try {
            list = (ListView) root.lookup("#list");
        } catch (Exception e) {
            throw new UnsupportedOperationException("Could not fint ListView object");
        }
        list.getSelectionModel().select("Bench Press");
        lookup("#sets").queryAs(TextField.class).setText("3");
        lookup("#repMin").queryAs(TextField.class).setText("5");
        lookup("#repMax").queryAs(TextField.class).setText("10");
        lookup("#weight").queryAs(TextField.class).setText("50");
        clickOn("#addExercise");
        FxAssert.verifyThat("#alertButton", (button) -> !button.isDisabled());
        clickOn("#alertButton");
        Scene initialScene = stage.getScene();
        clickOn("#finishButton");
        Scene updatedScene = stage.getScene();
        Assertions.assertNotSame(initialScene, updatedScene);
    }

    @Test
    public void testFinishedValidationExercise() {
        clickOn("#finishButton");
        FxAssert.verifyThat("#alertButton", (button) -> !button.isDisabled());
        clickOn("#alertButton");
    }

    @Test
    public void testsave() throws IOException {
        ListView<String> list;
        try {
            list = (ListView) root.lookup("#list");
        } catch (Exception e) {
            throw new UnsupportedOperationException("Could not fint ListView object");
        }
        list.getSelectionModel().select("Bench Press");
        lookup("#sets").queryAs(TextField.class).setText("3");
        lookup("#repMin").queryAs(TextField.class).setText("5");
        lookup("#repMax").queryAs(TextField.class).setText("10");
        lookup("#weight").queryAs(TextField.class).setText("50");
        clickOn("#addExercise");
        FxAssert.verifyThat("#alertButton", (button) -> !button.isDisabled());
        clickOn("#alertButton");
        clickOn("#finishButton");
        // Get the initial scene
        lookup("#inpName").queryAs(TextField.class).setText("push");
        // Click on the "newWorkout" button
        clickOn("#saveButton");
        FxAssert.verifyThat("#alertButton", (button) -> !button.isDisabled());
        clickOn("#alertButton");
    }
}