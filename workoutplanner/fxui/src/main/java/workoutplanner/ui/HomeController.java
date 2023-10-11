package workoutplanner.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import workoutplanner.core.User;
import workoutplanner.core.Workout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fxutil.doc.Controller;
import fxutil.doc.PageLoader;

public class HomeController implements Controller{

    private User user = new User("Erlend", "David", new ArrayList<>(List.of(new Workout(new Date()))));

    @FXML
    private Button newWorkout, allWorkouts;

    @FXML
    private void handleNewWorkout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExerciseView.fxml"));
        ExerciseViewController exerciseViewController = (ExerciseViewController) PageLoader.pageLoader(loader, newWorkout);
        exerciseViewController.initialize();
    }

    @FXML
    private void handleAllWorkout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlanView.fxml"));
        PlanController planController = (PlanController) PageLoader.pageLoader(loader, allWorkouts);
        planController.init(user.getWorkouts());
    }


}
