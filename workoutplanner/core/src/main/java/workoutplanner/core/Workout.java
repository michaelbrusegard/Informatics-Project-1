package workoutplanner.core;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Workout {

    private String name;
    private Date date;
    private final List<Exercise> exercises = new ArrayList<>();

    public Workout() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public String getName() {
        return name;
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
