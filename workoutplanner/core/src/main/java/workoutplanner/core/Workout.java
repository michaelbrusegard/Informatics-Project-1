package workoutplanner.core;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Workout {

    private Date date;
    private final List<Exercise> exercises = new ArrayList<>();
    public Workout() {
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addExercise(String name, int sets, int repMin, int repMax, int weight) {
        Exercise exercise = new Exercise(name, sets, repMin, repMax, weight);
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
