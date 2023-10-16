package workoutplanner.core;

import java.util.ArrayList;
import java.util.List;

public class User {
    List<Workout> workouts;

    public User() {
        this.workouts = new ArrayList<Workout>();
    }

    public void addWorkout(Workout workout) {
        this.workouts.add(workout);
    }

    public void removeWorkout(Workout workout) {
        this.workouts.remove(workout);
    }

    public List<Workout> getWorkouts() {
        return this.workouts;
    }
}
