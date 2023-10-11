package workoutplanner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import workoutplanner.core.User;
import workoutplanner.core.Workout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeController {

    private User user = new User("Erlend", "David", new ArrayList<>(List.of(new Workout(new Date()))));

    @FXML
    private Button newWorkout, allWorkouts;

    @FXML
    private void handleNewWorkout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ExerciseView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) newWorkout.getScene().getWindow();
            stage.setScene(scene);
            ExerciseViewController eController = loader.getController();
            eController.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAllWorkout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlanView.fxml"));
            Parent root = loader.load();
            PlanController planController = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = (Stage) allWorkouts.getScene().getWindow();
            stage.setScene(scene);

            planController.init(user.getWorkouts());
            //show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
