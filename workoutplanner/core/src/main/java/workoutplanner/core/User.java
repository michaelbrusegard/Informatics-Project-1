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
    List<Workout> workouts;
    Workout currentWorkout;

    // Constructor with a new list of workouts
    public User() {
        this.workouts = new ArrayList<>();
    }

    // Create a new workout
    public void createWorkout() {
        Workout newWorkout = new Workout();
        this.workouts.add(newWorkout);
        this.currentWorkout = newWorkout;
    }

    // Remove a workout from the list of workouts
    public void removeCurrentWorkout() {
        this.workouts.remove(this.currentWorkout);
    }

    // Return the current workout
    public Workout getCurrentWorkout() {
        return this.currentWorkout;
    }

    public int getCurrentWorkoutIndex() {
        return this.workouts.indexOf(this.currentWorkout);
    }

    // Remove the latest workout
    public void setCurrentWorkout(int workoutIndex) {
        this.currentWorkout = this.workouts.get(workoutIndex);
    }

    // Return the list of workouts
    public List<Workout> getWorkouts() {
        return this.workouts;
    }
}
