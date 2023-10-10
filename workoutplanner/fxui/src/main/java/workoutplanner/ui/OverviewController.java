package workoutplanner.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import workoutplanner.core.Workout;

public class OverviewController {
Workout workout;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button cancelButton, saveButton;


    //Method for when the cancel button is pressed
    @FXML
    public void cancel(){
        try {
            // Load the Home.fxml file
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

    public void init(Workout workout){
        System.out.println(this.scrollPane);
        this.workout = workout;
        OverviewGridHandler ogh = new OverviewGridHandler(scrollPane,this.workout);
        ogh.createGrid();
    }
}
