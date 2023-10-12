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
import workoutplanner.core.Workout;

public class OverviewController implements Controller {

    Workout workout;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button cancelButton, saveButton;

    @FXML
    private TextField inpName;

    // Method for when the cancel button is pressed
    @FXML
    public void cancel() throws IOException {
        this.validateOverview(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        PageLoader.pageLoader(loader, cancelButton);
    }

    @FXML
    public void save() throws IOException {
        this.validateOverview(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlanView.fxml"));
        PlanController planController = (PlanController) PageLoader.pageLoader(loader, cancelButton);
        planController.init(new ArrayList<>(List.of(workout)));
    }

    private void validateOverview(boolean saved) {
        if (inpName.getText().length() == 0) {
            UIUtils.showAlert("Empty inputfield", "Inputfield cannot be empty", AlertType.ERROR);
            throw new IllegalArgumentException("Empty inputfield");
        } else if (inpName.getText().length() >= 20) {
            UIUtils.showAlert("Too many characters", "Inputfield shouldn't have more than 20 characters",
                    AlertType.ERROR);
            throw new IllegalArgumentException("Too many characters");
        } else if (saved) {
            UIUtils.showAlert("Save successful", "Workout saved successfully", AlertType.INFORMATION);
            return;
        } else {
            UIUtils.showAlert("Cancellation successful", "Workout deleted, bringing you back to home",
                    AlertType.INFORMATION);
            return;
        }
    }

    public void init(Workout workout) {
        System.out.println(this.scrollPane);
        this.workout = workout;
        OverviewGridHandler ogh = new OverviewGridHandler(scrollPane, this.workout);
        ogh.createGrid();
    }
}
