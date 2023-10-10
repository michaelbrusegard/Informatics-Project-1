package workoutplanner.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import workoutplanner.core.Workout;

public class OverviewController {
Workout workout;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button cancelButton, saveButton;

    @FXML
    private TextField inpName;


    //Method for when the cancel button is pressed
    @FXML
    public void cancel(){
        try {
            // Load the Home.fxml file
            this.validateOverview(false, true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.setScene(scene);

            //show the stage
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void save(){
        try {
            // Load the Home.fxml file
            this.validateOverview(true, false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlanView.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.setScene(scene);

            //show the stage
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void validateOverview(boolean saved, boolean cancelled){
        if (inpName.getText().length() == 0){
            UIUtils.showAlert("Empty inputfield", "Inputfield cannot be empty", AlertType.ERROR);
            throw new IllegalArgumentException("Empty inputfield");
        }
        else if (inpName.getText().length() >= 20){
            UIUtils.showAlert("Too many characters", "Inputfield shouldn't have more than 20 characters", AlertType.ERROR);
            throw new IllegalArgumentException("Too many characters");

        }
        else if (saved){
            UIUtils.showAlert("Save successful", "Workout saved successfully", AlertType.INFORMATION);
        }
        else if (cancelled){
            UIUtils.showAlert("Cancellation successful", "Workout deleted, bringing you back to home", AlertType.INFORMATION);
        }
    }

    public void init(Workout workout){
        System.out.println(this.scrollPane);
        this.workout = workout;
        OverviewGridHandler ogh = new OverviewGridHandler(scrollPane,this.workout);
        ogh.createGrid();
    }
}
