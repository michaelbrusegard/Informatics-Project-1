package workoutplanner.fxui;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;

public class FXApplicationTest extends FxTest {

    @Test
    public void testApplication(){
        checkExistingWorkouts();
        gotoOverview();
        clickOn("#save");
        clickOn("#alertButton");
        addExerciseToWorkout();
        while (workoutExists()){
            deleteWorkout();
        }
        clickOn("#returnHome");
        gotoOverview();
        validateOverview();

    }
    private void gotoOverview(){
        createExercises();
        clickOn("#inputName").write("Leg Day");
    }

    private void validateOverview() {
        clickOn("#cancel");
        clickAndCheckConfirmation("Cancel Workout","Are you sure you want to cancel the workout? "
                + "All progress will be lost.");
        gotoOverview();
        Button rightButton = (Button) getExerciseCell(1).get(2);
        clickOn(rightButton);
        Button leftButton = (Button) getExerciseCell(2).get(0);
        clickOn(leftButton);
        Button deleteButton = (Button) getExerciseCell(1).get(1);
        clickOn(deleteButton);
        clickAndCheckConfirmation("Delete Exercise", "Are you sure you want to delete "
                + "Calf Raises"
                + "? ");
    }

    //get exercise cell from overview
    private ObservableList<Node> getExerciseCell(int index){
        return (((HBox)((VBox)((GridPane)((ScrollPane) lookup("#scrollPane").query()).getContent()).getChildren().get(index)).getChildren().get(2)).getChildren());
    }

    //get workout cell from workoutview
    private ObservableList<Node> getWorkoutcell(int index){
        try {
            return ((VBox) ((GridPane) ((ScrollPane) lookup("#scrollPaneWorkout").query()).getContent()).getChildren().get(index)).getChildren();
        }
        catch (ClassCastException e){
            return null;
        }
    }


    private boolean workoutExists(){
        return !((((ScrollPane) lookup("#scrollPaneWorkout").query()).getContent()) instanceof VBox);
    }

    //create exercise
    private void createExercises(){
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

    private void addExerciseToWorkout(){
        Button editButton = (Button) ((HBox)((VBox)((GridPane)((ScrollPane) lookup("#scrollPaneWorkout").query()).getContent()).getChildren().get(1)).getChildren().get(2)).getChildren().get(0);
        clickOn(editButton);
        clickOn("#addExercises");

        listView = getNode(ListView.class,"list");
        String exercise = listView.getItems().get(1);

        clickOn(exercise);
        clickOn("#sets").write("2");
        clickOn("#repMin").write("3");
        clickOn("#repMax").write("5");
        clickOn("#weight").write("100");
        clickOn("#addExercise");
        clickOn("#alertButton");
        clickOn("#finishButton");
        clickOn("#returnWorkoutView");
    }

    private void deleteWorkout(){
        Button deleteButton = (Button) ((HBox) (getWorkoutcell(1)).get(2)).getChildren().get(1);
        clickOn(deleteButton);
        Text text = (Text)((VBox)((GridPane) ((ScrollPane) lookup("#scrollPaneWorkout").query()).getContent()).getChildren().get(1)).getChildren().get(0);
        clickAndCheckConfirmation("Delete Workout","Are you sure you want to delete "+text.getText()+"? All workout data will be lost.");
    }
    private void checkExistingWorkouts(){
        clickOn("#showAllWorkouts");
        boolean vbox = false;
        boolean empty = true;
        while (empty) {
            try {
                vbox = (((ScrollPane) lookup("#scrollPaneWorkout").query()).getContent()) instanceof VBox;
            }
            catch (ClassCastException e){
                vbox = false;
            }
            if (!vbox) {
                Text text = (Text)((VBox)((GridPane) ((ScrollPane) lookup("#scrollPaneWorkout").query()).getContent()).getChildren().get(1)).getChildren().get(0);
                Button deleteButton = (Button) ((HBox) ((VBox) ((GridPane) ((ScrollPane) lookup("#scrollPaneWorkout").query()).getContent()).getChildren().get(1)).getChildren().get(2)).getChildren().get(1);
                clickOn("#DeleteWorkout");
                clickAndCheckConfirmation("Delete Workout","Are you sure you want to delete "+ text.getText()+"? All workout data will be lost.");
            } else {
                empty = false;
            }
        }
        clickOn("#returnHome");}
}
