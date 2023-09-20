package ui;

import core.Exercise;
import core.ExerciseFileHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ExerciseViewController {
    @FXML
    TextField exerciseName, exerciseSet, exerciseRep, exerciseWeight;

    Text name;
    Exercise exercise;

    @FXML
    public void setExName(MouseEvent event) {
        exerciseName.setText(((Text) event.getSource()).getText());
    }


    @FXML
    public void saveExercises(){
        ExerciseFileHandler.write((new Exercise(getExerciseName(),null)).toString());
    }
    public TextField getExerciseName() {
        return ((TextField) exerciseName);
    }

    public TextField getExerciseSet() {
        return exerciseSet;
    }

    public TextField getExerciseRep() {
        return exerciseRep;
    }

    public TextField getExerciseWeight() {
        return exerciseWeight;
    }

    public Text getName() {
        return name;
    }
    public void setName(Text name) {
        this.name = name;
    }
    public Exercise getExercise() {
        return exercise;
    }

}