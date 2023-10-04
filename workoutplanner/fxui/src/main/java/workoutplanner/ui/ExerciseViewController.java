package workoutplanner.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import workoutplanner.core.Exercise;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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
    ListView<String> list;
    @FXML
    HBox hBoxName;

    @FXML
    public void setExName(MouseEvent event) {
        exerciseName.setText(((Text) event.getSource()).getText());
    }

    @FXML
    public void addNewExercise() {
        this.newExerciseField = new TextField();
        hBoxName.getChildren().set(0, newExerciseField);
    }

    @FXML
    public void saveExercises() throws IOException {
        Exercise exer = new Exercise(this.newExerciseField.getText(), "arms");
        this.exerciseview.addExercise(exer);
        loadnewExercises();
    }

    @FXML
    public void init() throws IOException {
        hBoxName.getChildren().add(new TextField());
        loadExercises();
        hBoxName.getChildren().set(0, topfield());
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void loadExercises() throws IOException {
        List<Exercise> exercises = this.exerciseview.read();
        ObservableList<String> items = list.getItems();
        for (Exercise exercise : exercises) {
            items.add(exercise.getName());
        }

    }

    @FXML
    public TextField topfield() {
        ObservableList<String> items = list.getItems();
        if (items.isEmpty()) {
            System.out.println(items);
            return new TextField();
        }

        System.out.println(items);
        return (new TextField(items.get(0)));
    }

    @FXML
    public void loadnewExercises() throws IOException {
        List<Exercise> exercises = this.exerciseview.read();
        ObservableList<String> items = list.getItems();
        String t = exercises.get(exercises.size() - 1).getName();
        items.add(t);
        newExerciseField.setText(t);
        hBoxName.getChildren().set(0, newExerciseField);
    }
}
