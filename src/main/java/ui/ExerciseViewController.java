package ui;

import core.Exercise;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ExerciseViewController {
    @FXML
    Text exerciseName, exerciseSet, exerciseRep, exerciseWeight;

    Exercise exercise;
    @FXML
    public void setExName(MouseEvent event){
        Text name = ((Text) event.getSource());
        this.exerciseName.setText(name.getText());       
    }
    @FXML
    public void saveExercises(){
        // exercise = new Exercise(this.exerciseName,null,this.exerciseRep,this.exerciseWeight);
    }
}