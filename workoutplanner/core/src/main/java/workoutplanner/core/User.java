package workoutplanner.core;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>User</h1>
 * <p>
 * The User class is responsible for storing
 * the user's workouts.
 * </p>
 *
 * @since 1.0.0
 * @author Michael
 * @version 2.0.0
 */
public class User {
    List<Workout> workouts;

    // Constructor with a new list of workouts
    public User() {
        this.workouts = new ArrayList<Workout>();
    }

    // Add a workout to the list of workouts
    public void addWorkout(Workout workout) {
        this.workouts.add(workout);
    }

    // Remove a workout from the list of workouts
    public void removeWorkout(Workout workout) {
        this.workouts.remove(workout);
    }

    // Return the list of workouts
    public List<Workout> getWorkouts() {
        return this.workouts;
    }
}
