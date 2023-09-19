package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exerciseview {
    
    private List<Exercise> exercises = new ArrayList<>();

    public Exerciseview(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void addExercise(Exercise exercise) {
        getExercises().add(exercise);
        sortList();
        write();
    }

    private void write() {
        ExerciseFileHandler.write(this.toString());
    }

    private void sortList() {
        Collections.sort(getExercises());
    }

    public void show() {
        //TODO: FXML logic here
        return;
    }
    
    @Override
    public String toString() {
        return exercises.toString();
    }

}
