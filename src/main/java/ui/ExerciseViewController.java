package ui;

import java.io.IOException;
import java.util.ArrayList;

import core.Exercise;
import core.ExerciseFileHandler;
import core.Exerciseview;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ExerciseViewController {
    @FXML
    TextField exerciseSet, exerciseRep, exerciseWeight, newExerciseField;

    Exerciseview exerciseview = new Exerciseview(new ArrayList<>());

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
        this.newExerciseField = new TextField();
        hBoxName.getChildren().add(newExerciseField);
    }


    @FXML
    public void saveExercises() throws IOException{
        Exercise exer = new Exercise(this.newExerciseField, "arms");
        this.exerciseview.addExercise(exer);
        ExerciseFileHandler.write((exer).toString());
        System.out.println(ExerciseFileHandler.read());
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