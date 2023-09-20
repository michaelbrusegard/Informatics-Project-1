package ui;

import core.Exercise;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ExerciseViewController {
    @FXML
    TextField exerciseSet, exerciseRep, exerciseWeight;

    @FXML
    Text exerciseName;
    Exercise exercise;

    @FXML
    HBox hBoxName;

    @FXML
    public void setExName(MouseEvent event) {
        exerciseName.setText(((Text) event.getSource()).getText());
    }
    @FXML
    public void addNewExercise(){
        TextField newField = new TextField();
        hBoxName.getChildren().add(newField);
    }


    @FXML
    public void saveExercises(){
        // ExerciseFileHandler.write((new Exercise(getExerciseName(),null)).toString());
    }
    // public TextField getExerciseName() {
    //     return ((TextField) exerciseName);
    // }

    // public TextField getExerciseSet() {
    //     return exerciseSet;
    // }

    // public TextField getExerciseRep() {
    //     return exerciseRep;
    // }

    // public TextField getExerciseWeight() {
    //     return exerciseWeight;
    // }

    // public Text getName() {
    //     return name;
    // }
    // public void setName(Text name) {
    //     this.name = name;
    // }
    // public Exercise getExercise() {
    //     return exercise;
    // }

}