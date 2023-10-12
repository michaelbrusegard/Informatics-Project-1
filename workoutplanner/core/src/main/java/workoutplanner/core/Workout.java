package workoutplanner.core;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Workout {

    private final Date date;
    private final List<Exercise> exercises = new ArrayList<>();
    public Workout(Date date) {
        this.date = date;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public Date getDate() {
        return date;
    }

    public int getExerciseCount() {
        return exercises.size();
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
