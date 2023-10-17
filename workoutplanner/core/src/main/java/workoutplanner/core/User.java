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
 * @author Michael Brusegard
 * @version 2.0.0
 */
public class User {
    Stack<Workout> workouts;

    // Constructor with a new list of workouts
    public User() {
        this.workouts = new Stack<>();
    }

    // Create a new workout
    public void createWorkout() {
        this.workouts.add(new Workout());
    }

    // Remove a workout from the list of workouts
    public void removeWorkout(Workout workout) {
        this.workouts.remove(workout);
    }

    // Return the latest workout
    public Workout getLatestWorkout() {
        return this.workouts.peek();
    }

    // Remove the latest workout
    public void removeLatestWorkout() {
        this.workouts.pop();
    }

    // Return the list of workouts
    public List<Workout> getWorkouts() {
        return this.workouts;
    }
}
