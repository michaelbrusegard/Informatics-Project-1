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
 * @author Michael Brusegard
 * @version 2.0.0
 */
public class User {
    private List<Workout> workouts;
    private int currentWorkoutIndex = -1;

    // Constructor with a new list of workouts
    public User() {
        this.workouts = new ArrayList<>();
    }

    // Remove a workout from the list of workouts
    public void removeWorkout(int workoutIndex) {
        this.workouts.remove(workoutIndex);
    }

    // Remove the current workout from the list of workouts
    public void removeCurrentWorkout() {
        this.workouts.remove(this.currentWorkoutIndex);
        this.currentWorkoutIndex = -1;
    }

    // Return the current workout or create a new one if it doesn't exist
    public Workout getCurrentWorkout() {
        if (this.currentWorkoutIndex == -1) {
            this.currentWorkoutIndex = this.workouts.size();
            this.workouts.add(new Workout());
        }

        return this.workouts.get(this.currentWorkoutIndex);
    }

    // Set the current workout
    public void setCurrentWorkout(int workoutIndex) {
        this.currentWorkoutIndex = workoutIndex;
    }

    // Return the list of workouts
    public List<Workout> getWorkouts() {
        return this.workouts;
    }
}
