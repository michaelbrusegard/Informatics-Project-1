//package workoutplanner.fxui;
//
//import javafx.beans.Observable;
//import javafx.collections.ObservableList;
//import javafx.scene.Node;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Text;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.testfx.api.FxRobotException;
//import org.testfx.service.query.EmptyNodeQueryException;
//import org.testfx.util.WaitForAsyncUtils;
//
//import java.util.concurrent.TimeUnit;
//
//public class OverviewControllerTest extends FxTest{
//
//
//
//    @BeforeEach
//    public void setUp(){
//        clickOn("#createNewWorkout");
//
//        listView = getNode(ListView.class,"list");
//        String exercise = listView.getItems().get(7);
//
//        clickOn(exercise);
//
//        clickOn("#sets").write("5");
//        clickOn("#repMin").write("16");
//        clickOn("#repMax").write("20");
//        clickOn("#weight").write("60");
//        clickOn("#addExercise");
//        clickOn("#alertButton");
//
//        exercise = listView.getItems().get(3);
//
//
//        clickOn(exercise);
//
//        clickOn("#sets").write("3");
//        clickOn("#repMin").write("4");
//        clickOn("#repMax").write("6");
//        clickOn("#weight").write("90");
//        clickOn("#addExercise");
//        clickOn("#alertButton");
//        clickOn("#finishButton");
//    }
//
//    @Test
//    public void testCancelButton(){
//        clickOn("#cancel");
//        clickAndCheckConfirmation("Cancel Workout","Are you sure you want to cancel the workout? "
//                + "All progress will be lost.");
//    }
//
//    @Test
//    public void testCellButtons(){
//        Button rightButton = (Button) ((HBox)((VBox)((GridPane)((ScrollPane) lookup("#scrollPane").query()).getContent()).getChildren().get(1)).getChildren().get(2)).getChildren().get(2);
//        clickOn(rightButton);
//        Button leftButton = (Button) ((HBox)((VBox)((GridPane)((ScrollPane) lookup("#scrollPane").query()).getContent()).getChildren().get(2)).getChildren().get(2)).getChildren().get(0);
//        clickOn(leftButton);
//        Button deleteButton = (Button) ((HBox)((VBox)((GridPane)((ScrollPane) lookup("#scrollPane").query()).getContent()).getChildren().get(1)).getChildren().get(2)).getChildren().get(1);
//        clickOn(deleteButton);
//        clickAndCheckConfirmation("Delete Exercise", "Are you sure you want to delete "
//                + "Calf Raises"
//                + "? ");
//
//    }
//
//    @Test
//    public void testSaveButton(){
//        checkExistingWorkouts();
//        clickOn("#inputName").write("Leg Day");
//        clickOn("#save");
//        clickOn("#alertButton");
//        Button editButton = (Button) ((HBox)((VBox)((GridPane)((ScrollPane) lookup("#scrollPaneWorkout").query()).getContent()).getChildren().get(1)).getChildren().get(2)).getChildren().get(0);
//        clickOn(editButton);
//        clickOn("#addExercises");
//        listView = getNode(ListView.class,"list");
//        String exercise = listView.getItems().get(1);
//        clickOn(exercise);
//
//        clickOn("#sets").write("2");
//        clickOn("#repMin").write("3");
//        clickOn("#repMax").write("5");
//        clickOn("#weight").write("100");
//        clickOn("#addExercise");
//        clickOn("#alertButton");
//        clickOn("#finishButton");
//        clickOn("#returnWorkoutView");
//        Button deleteButton = (Button) ((HBox)((VBox)((GridPane)((ScrollPane) lookup("#scrollPaneWorkout").query()).getContent()).getChildren().get(1)).getChildren().get(2)).getChildren().get(1);
//        clickOn(deleteButton);
//        clickAndCheckConfirmation("Delete Workout","Are you sure you want to delete Leg Day? All workout data will be lost.");
//        clickOn("#returnHome");
//
//    }
//
//    private void checkExistingWorkouts(){
//        clickOn("#cancel");
//        clickAndCheckConfirmation("Cancel Workout","Are you sure you want to cancel the workout? "
//                + "All progress will be lost.");
//        clickOn("#showAllWorkouts");
//        boolean vbox = false;
//        boolean empty = true;
//        while (empty) {
//            try {
//                vbox = (((ScrollPane) lookup("#scrollPaneWorkout").query()).getContent()) instanceof VBox;
//            }
//            catch (ClassCastException e){
//                vbox = false;
//            }
//            if (!vbox) {
//                Text text = (Text)((VBox)((GridPane) ((ScrollPane) lookup("#scrollPaneWorkout").query()).getContent()).getChildren().get(1)).getChildren().get(0);
//                Button deleteButton = (Button) ((HBox) ((VBox) ((GridPane) ((ScrollPane) lookup("#scrollPaneWorkout").query()).getContent()).getChildren().get(1)).getChildren().get(2)).getChildren().get(1);
//                System.out.println(deleteButton);
//                clickOn("#DeleteWorkout");
//                clickAndCheckConfirmation("Delete Workout","Are you sure you want to delete "+ text.getText()+"? All workout data will be lost.");
//            } else {
//                empty = false;
//            }
//        }
//        clickOn("#returnHome");
//        setUp();
//    }
//}
