package workoutplanner.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import workoutplanner.fxutil.Controller;
import workoutplanner.fxutil.PageLoader;
import workoutplanner.fxutil.UIUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import workoutplanner.core.Workout;

public class OverviewController implements Controller {

    private Workout workout;

    @FXML
    private VBox saveWorkoutNameBox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button cancelButton, saveButton;

    @FXML
    private TextField inpName;

    // Method for when the cancel button is pressed
    @FXML
    public void cancel() throws IOException {
        if (this.validateOverview(false, true)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            PageLoader.pageLoader(loader, cancelButton);
        }
    }

    @FXML
    public void save() throws IOException {
        if (this.validateOverview(true, false)) {
            workout.setName(inpName.getText());
            workout.setDate(new Date());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlanView.fxml"));
            PlanController planController = (PlanController) PageLoader.pageLoader(loader, cancelButton);
            planController.init(new ArrayList<>(List.of(workout)));
        }
    }

    private boolean validateOverview(boolean saved, boolean close) {
        if (inpName.getText().length() == 0 && saved) {
            UIUtils.showAlert("Empty inputfield", "Inputfield cannot be empty", AlertType.ERROR);
            return false;
        } else if (inpName.getText().length() >= 20) {
            UIUtils.showAlert("Error", "Inputfield shouldn't have more than 20 characters",
                    AlertType.ERROR);
            return false;
        } else if (saved) {
            UIUtils.showAlert("Save successful", "Workout saved successfully", AlertType.INFORMATION);
            return false;
        } else if (close) {
            UIUtils.showAlert("Cancellation successful", "Workout deleted, bringing you back to home",
                    AlertType.INFORMATION);
            return false;
        }
        return true;
    }

    public void init(Workout workout) {
        this.workout = workout;
        OverviewGridHandler ogh = new OverviewGridHandler(scrollPane, this.workout);
        ogh.createGrid();
    }

    public void loadOverviewFromPlan() {
        saveWorkoutNameBox.getChildren().clear();
        Text name = new Text(workout.getName());
        name.setFont(new Font(40));
        Text date = new Text(workout.getDateAsString());
        date.setFont(new Font(20));
        saveWorkoutNameBox.getChildren().addAll(name, date, cancelButton);
    }
}
