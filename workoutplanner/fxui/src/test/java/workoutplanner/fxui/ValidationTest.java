package workoutplanner.fxui;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;
import workoutplanner.fxutil.Overview;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest extends FxTest{

    @Test
    public void testValidation(){
        clickOn("#createNewWorkout");
        valueValidation();
        validationInExerciseView();
        validateOverview();

    }

    private void validateOverview() {
        //only static methods are tested here
        assertFalse(Overview.validateOverview(true,false,new TextField()));
        assertFalse(Overview.validateOverview(true,true,new TextField("abcdefghijklmnopqrstuvwxyz")));
        assertTrue(Overview.validateOverview(true,false,new TextField("valid")));
        assertTrue(Overview.validateOverview(false,true,new TextField("valid")));
        assertTrue(Overview.validateOverview(false,false,new TextField("valid")));

    }

    private void valueValidation() {
        Integer invalidUnder = -1;
        Integer invalidOver = 5002;
        clickOn("#addExercise");
        clickAndCheckAlert("Error", "Please select an exercise.", Alert.AlertType.ERROR);
        List<Integer> list = new ArrayList<>(List.of(3,4,5,6));
        listView = getNode(ListView.class,"list");
        String exercise = listView.getItems().get(0);
        clickOn(exercise);
        clickOn("#sets").write(String.valueOf(""));
        clickOn("#repMin").write(String.valueOf(list.get(1)));
        clickOn("#repMax").write(String.valueOf(list.get(2)));
        clickOn("#weight").write(String.valueOf(list.get(3)));
        clickOn("#addExercise");
        clickAndCheckAlert("Error", "Please fill in all fields.", Alert.AlertType.ERROR);

        clickOn(exercise);
        clickOn("#sets").write(String.valueOf(list.get(0)));
        clickOn("#repMin").write("");
        clickOn("#repMax").write(String.valueOf(list.get(2)));
        clickOn("#weight").write(String.valueOf(list.get(3)));
        clickOn("#addExercise");
        clickAndCheckAlert("Error", "Please fill in all fields.", Alert.AlertType.ERROR);

        clickOn(exercise);
        clickOn("#sets").write(String.valueOf(list.get(0)));
        clickOn("#repMin").write(String.valueOf(list.get(1)));
        clickOn("#repMax").write("");
        clickOn("#weight").write(String.valueOf(list.get(3)));
        clickOn("#addExercise");
        clickAndCheckAlert("Error", "Please fill in all fields.", Alert.AlertType.ERROR);

        clickOn(exercise);
        clickOn("#sets").write(String.valueOf(list.get(0)));
        clickOn("#repMin").write(String.valueOf(list.get(1)));
        clickOn("#repMax").write(String.valueOf(list.get(2)));
        clickOn("#weight").write("");
        clickOn("#addExercise");
        clickAndCheckAlert("Error", "Please fill in all fields.", Alert.AlertType.ERROR);

        clickOn(exercise);
        clickOn("#sets").write(String.valueOf("sets"));
        clickOn("#repMin").write(String.valueOf(list.get(1)));
        clickOn("#repMax").write(String.valueOf(list.get(2)));
        clickOn("#weight").write(String.valueOf(list.get(3)));
        clickOn("#addExercise");
        clickAndCheckAlert("Error", "Please enter a number for "
                + "sets, rep-range, and weight.", Alert.AlertType.ERROR);

        clickOn(exercise);
        clickOn("#sets").write(String.valueOf(list.get(0)));
        clickOn("#repMin").write(String.valueOf("repMin"));
        clickOn("#repMax").write(String.valueOf(list.get(2)));
        clickOn("#weight").write(String.valueOf(list.get(3)));
        clickOn("#addExercise");
        clickAndCheckAlert("Error", "Please enter a number for "
                + "sets, rep-range, and weight.", Alert.AlertType.ERROR);
        clickOn(exercise);
        clickOn("#sets").write(String.valueOf(list.get(0)));
        clickOn("#repMin").write(String.valueOf(list.get(1)));
        clickOn("#repMax").write(String.valueOf("repMax"));
        clickOn("#weight").write(String.valueOf(list.get(3)));
        clickOn("#addExercise");
        clickAndCheckAlert("Error", "Please enter a number for "
                + "sets, rep-range, and weight.", Alert.AlertType.ERROR);
        clickOn(exercise);
        clickOn("#sets").write(String.valueOf(list.get(0)));
        clickOn("#repMin").write(String.valueOf(list.get(1)));
        clickOn("#repMax").write(String.valueOf(list.get(2)));
        clickOn("#weight").write(String.valueOf("weight"));
        clickOn("#addExercise");
        clickAndCheckAlert("Error", "Please enter a number for "
                + "sets, rep-range, and weight.", Alert.AlertType.ERROR);

        for(Integer value : list){
            int index = list.indexOf(value);
            list.set(index, invalidUnder);
            createWrongExercise(0,list.get(0),list.get(1),list.get(2),list.get(3));
            clickAndCheckAlert("Error",
                    "You can't do negative reps or weight. "
                            + "Also, you need to have at least one set "
                            + "and at least one max repetition in the rep-range.",
                    Alert.AlertType.ERROR);
            list.set(index, invalidOver);

            if(index == 1){
                list.set(index+1, 5010);
                createWrongExercise(0,list.get(0),list.get(1),list.get(2),list.get(3));
                clickAndCheckAlert("Error", "Please enter a number less "
                    + "than 5000. You are not that strong.", Alert.AlertType.ERROR);
                list.set(index+1, 5);
            }
            else {
                createWrongExercise(0,list.get(0),list.get(1),list.get(2),list.get(3));
                clickAndCheckAlert("Error", "Please enter a number less "
                        + "than 5000. You are not that strong.", Alert.AlertType.ERROR);
            }
            list.set(index, 5);
        }

    }
        //exercise name == null
        //each field isempty
        //each field is integer
        //each field under 1
        //each field over limit

    private void validationInExerciseView() {
        List<String> alertItems = new ArrayList<>();
        String exercise = listView.getItems().get(0);
        alertItems.add(exercise);
        alertItems.add(String.valueOf(3));
        alertItems.add(String.valueOf(8));
        alertItems.add(String.valueOf(12));
        alertItems.add(String.valueOf(20));
        // Select an exercise in the list
        clickOn(exercise);
        assertEquals(exercise,getNode(Text.class, "name").getText());
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

    private void createWrongExercise(Integer listIndex,Integer sets, Integer repMin, Integer repMax, int weight){
        listView = getNode(ListView.class,"list");
        String exercise = listView.getItems().get(listIndex);

        clickOn(exercise);
        clickOn("#sets").write(String.valueOf(sets));
        clickOn("#repMin").write(String.valueOf(repMin));
        clickOn("#repMax").write(String.valueOf(repMax));
        clickOn("#weight").write(String.valueOf(weight));
        clickOn("#addExercise");
    }

    //create exercise
    private void createExercises(){
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
}
