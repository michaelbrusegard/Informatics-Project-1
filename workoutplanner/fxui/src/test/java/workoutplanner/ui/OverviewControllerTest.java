package workoutplanner.ui;

import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.util.WaitForAsyncUtils;
import workoutplanner.fxutil.ExerciseLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class OverviewControllerTest extends FxTest{



    @BeforeEach
    public void setUp(){
        clickOn("#createNewWorkout");

        listView = getNode(ListView.class,"list");
        String exercise = listView.getItems().get(7);

        clickOn(exercise);

        clickOn("#sets").write("5");
        clickOn("#repMin").write("16");
        clickOn("#repMax").write("20");
        clickOn("#weight").write("60");
        clickOn("#addExercise");
        clickOn("#alertButton");

        exercise = listView.getItems().get(3);


        clickOn(exercise);

        clickOn("#sets").write("3");
        clickOn("#repMin").write("4");
        clickOn("#repMax").write("6");
        clickOn("#weight").write("90");
        clickOn("#addExercise");
        clickOn("#alertButton");
        clickOn("#finishButton");
    }

    @Test
    public void testCancelButton(){
        clickOn("#cancel");
        clickAndCheckConfirmation("Cancel Workout","Are you sure you want to cancel the workout? "
                + "All progress will be lost.");
    }

    @Test
    public void testCellButtons(){
        Button rightButton = (Button) ((HBox)((VBox)((GridPane)((ScrollPane) lookup("#scrollPane").query()).getContent()).getChildren().get(1)).getChildren().get(2)).getChildren().get(2);
        clickOn(rightButton);
        Button leftButton = (Button) ((HBox)((VBox)((GridPane)((ScrollPane) lookup("#scrollPane").query()).getContent()).getChildren().get(2)).getChildren().get(2)).getChildren().get(0);
        clickOn(leftButton);
        Button deleteButton = (Button) ((HBox)((VBox)((GridPane)((ScrollPane) lookup("#scrollPane").query()).getContent()).getChildren().get(1)).getChildren().get(2)).getChildren().get(1);
        clickOn(deleteButton);
        clickAndCheckConfirmation("Delete Exercise", "Are you sure you want to delete "
                + "Calf Raises"
                + "? ");

    }

    @Test
    public void testSaveButton(){
        clickOn("#inputName").write("Leg Day");
        clickOn("#save");
        clickOn("#alertButton");
    }
    @Test
    public void testValidation(){
        //test all errors that are possible from ui
    }

}
