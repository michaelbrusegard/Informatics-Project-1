package workoutplanner.ui;

import java.io.IOException;
import java.util.List;

import workoutplanner.fxutil.Controller;
import workoutplanner.fxutil.PageLoader;
import workoutplanner.fxutil.UIUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import workoutplanner.core.Exercise;
import workoutplanner.core.Workout;
import workoutplanner.fxutil.ExerciseLoader;

public class ExerciseViewController implements Controller {
    @FXML
    private TextField sets, repMin, repMax, weight;

    @FXML
    private Text name;

    @FXML
    private ListView<String> list;

    @FXML
    private Button finishButton;

    // List of exercises
    private ObservableList<String> exercises;

    // The Workout object we are creating
    private Workout workout = new Workout();

    @FXML
    public void initialize() {
        // Update the list view with the exercises
        try {
            // Use ExerciseLoader to load exercises from the JSON file
            List<String> exercisesList = ExerciseLoader.loadExercisesFromJson();

            // Convert the List to an ObservableList
            exercises = FXCollections.observableArrayList(exercisesList);

            // Set the loaded exercises to the ListView
            list.setItems(exercises);
        } catch (IOException e) {
            // Handle the exception if the file loading fails
            e.printStackTrace();
            // You might want to show an error message or take appropriate action here
        }

        // Update the name text when an exercise is selected in the list view
        name.textProperty().bind(list.getSelectionModel().selectedItemProperty());

    }

    @FXML
    private void addExercise() {
        // Get the name of the exercise
        String exerciseName = name.getText();

        // Check if the user has selected an exercise and filled in all the fields
        if (exerciseName == null) {
            UIUtils.showAlert("Error", "Please select an exercise.", AlertType.ERROR);
            return;
        } else if (sets.getText().isEmpty() ||
                repMin.getText().isEmpty() ||
                repMax.getText().isEmpty() ||
                weight.getText().isEmpty()) {
            UIUtils.showAlert("Error", "Please fill in all fields.", AlertType.ERROR);
            return;
        }

        try {
            int exerciseSets = Integer.parseInt(sets.getText());
            int exerciseRepMin = Integer.parseInt(repMin.getText());
            int exerciseRepMax = Integer.parseInt(repMax.getText());
            int exerciseWeight = Integer.parseInt(weight.getText());

            // Check if the user has entered valid values
            if (exerciseRepMin > exerciseRepMax) {
                UIUtils.showAlert("Error", "The minimum rep-range cannot be greater than the maximum rep-range.",
                        AlertType.ERROR);
                return;
            } else if (exerciseRepMin < 0 || exerciseRepMax <= 0 || exerciseSets <= 0 || exerciseWeight < 0) {
                UIUtils.showAlert("Error",
                        "You can't do negative reps or weight. Also you need to have at least one set and at least one max repetition in the rep-range.",
                        AlertType.ERROR);
                return;
            }

            // Add the exercise to the workout
            workout.addExercise(
                    new Exercise(exerciseName, exerciseSets, exerciseRepMin, exerciseRepMax, exerciseWeight));

            // Show an alert with exercise details that have been added to the workout
            String alertContent = "Exercise has been added to the workout with the following details:\n\n" +
                    "Name: " + exerciseName + "\n" +
                    "Sets: " + exerciseSets + "\n" +
                    "Rep-range: " + exerciseRepMin + "-" + exerciseRepMax + "\n" +
                    "Weight: " + exerciseWeight + "kg";

            UIUtils.showAlert("Exercise Added", alertContent, AlertType.INFORMATION);

            // Clear the input fields
            sets.setText("");
            repMin.setText("");
            repMax.setText("");
            weight.setText("");

            // Deselect the exercise in the list view
            list.getSelectionModel().clearSelection();

        } catch (NumberFormatException e) {
            UIUtils.showAlert("Error",
                    "Please enter an integer for sets, rep-range, and weight. Anything else is not supported.",
                    AlertType.ERROR);
        }
    }

    @FXML
    private void finishAddingExercises() throws IOException {
        // Check if the workout object is not null
        if (workout.getExerciseCount() == 0) {
            UIUtils.showAlert("Error", "No exercises added to the workout.", AlertType.ERROR);
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Overview.fxml"));
        OverviewController overviewController = (OverviewController) PageLoader.pageLoader(loader,
                finishButton);
        overviewController.init(workout);
    }
}
