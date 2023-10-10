package workoutplanner.ui;

import workoutplanner.core.Exercise;

import java.util.ArrayList;
import java.util.List;

public class Exerciseview {
    
    private List<Exercise> exercises = new ArrayList<>();

    public Exerciseview(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    // public void addExercise(Exercise exercise) {
    //     getExercises().add(exercise);
    //     sortList();
    //     ExerciseFileHandler.write(exercise);
    // }

    // public List<Exercise> read() throws IOException {
    //     return ExerciseFileHandler.read();
    // }

    public void show() {
        //TODO: FXML logic here
        return;
    }
    
    @Override
    public String toString() {
        return exercises.toString();
    }

}
