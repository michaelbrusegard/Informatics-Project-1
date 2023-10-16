package workoutplanner.core;

import java.util.List;
import java.util.Stack;

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
    Stack<Workout> workouts;

    // Constructor with a new list of workouts
    public User() {
        this.workouts = new Stack<>();
    }

    // Add a workout to the list of workouts
    public void addWorkout(Workout workout) {
        this.workouts.add(workout);
    }

    // Remove a workout from the list of workouts
    public void removeWorkout(Workout workout) {
        this.workouts.remove(workout);
    }

    // Return the latest workout
    public Workout getLatestWorkout() {
        return this.workouts.peek();
    }

    // Return the list of workouts
    public List<Workout> getWorkouts() {
        return this.workouts;
    }
}
