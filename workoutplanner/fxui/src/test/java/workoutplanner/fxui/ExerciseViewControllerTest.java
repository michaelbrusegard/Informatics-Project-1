package workoutplanner.fxui;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.util.WaitForAsyncUtils;
import workoutplanner.json.ExerciseListLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseViewControllerTest extends FxTest {


    @BeforeEach
    public void setUp(){
        clickOn("#createNewWorkout");
        WaitForAsyncUtils.waitForFxEvents();
        listView = getNode(ListView.class,"list");
    }

    @Test
    public void testExerciseListIsLoaded() throws IOException {
        assertEquals(listView.getItems().stream().toList(), ExerciseListLoader.loadExerciseListFromJson());
    }

    @Test
    public void testSelectExerciseUpdatesNameText() {
        String exercise = listView.getItems().get(0);
        assertEquals(exercise,"Barbell Bench Press");
        clickOn(exercise);
        assertEquals(Objects.requireNonNull(getNode(Text.class, "name")).getText(),exercise);
    }

    @Test
    public void testAddExerciseButton() {
        List<String> alertItems = new ArrayList<>();
        String exercise = listView.getItems().get(0);
        alertItems.add(exercise);
        alertItems.add(String.valueOf(3));
        alertItems.add(String.valueOf(8));
        alertItems.add(String.valueOf(12));
        alertItems.add(String.valueOf(20));
        // Select an exercise in the list
        clickOn(exercise);
        // Enter values in the input fields
        clickOn("#sets").write("3");
        assertEquals(3,Integer.parseInt(Objects.requireNonNull(getNode(TextField.class, "sets")).getText()));
        clickOn("#repMin").write("8");
        assertEquals(8,Integer.parseInt(Objects.requireNonNull(getNode(TextField.class, "repMin")).getText()));
        clickOn("#repMax").write("12");
        assertEquals(12,Integer.parseInt(Objects.requireNonNull(getNode(TextField.class, "repMax")).getText()));
        clickOn("#weight").write("20");
        assertEquals(20,Integer.parseInt(Objects.requireNonNull(getNode(TextField.class, "weight")).getText()));

        // Click on the "Add Exercise" button
        clickOn("#addExercise");
        clickAndCheckAlert("Exercise Added","Exercise has been added to the workout "
                + "with the following details:\n\n"
                + "Name: " + alertItems.get(0) + "\n"
                + "Sets: " + alertItems.get(1) + "\n"
                + "Rep-range: " + alertItems.get(2) + "-" + alertItems.get(3) + "\n"
                + "Weight: " + alertItems.get(4) + "kg", Alert.AlertType.INFORMATION);
        assertEquals("",Objects.requireNonNull(getNode(TextField.class, "sets")).getText());
        assertEquals("",Objects.requireNonNull(getNode(TextField.class, "repMin")).getText());
        assertEquals("",Objects.requireNonNull(getNode(TextField.class, "repMax")).getText());
        assertEquals("",Objects.requireNonNull(getNode(TextField.class, "weight")).getText());

        clickOn("#finishButton");
    }

    @Test
    public void testValidation(){

    }

}
