package workoutplanner.core;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>User</h1>
 * Represents a user in the workoutplanner program.
 *
 * <p>
 * A User object represents a user of the workout planner application,
 * including a list of workouts associated with the user. It allows you to
 * manage and interact with user data and their workouts.
 * </p>
 *
 * @since 1.0.0
 * @author Michael Brusegard
 * @version 2.0.0
 */
public class User {
  /**
   * Local List variable, used to have a list of all workouts for the user.
   */
  private final List<Workout> workouts;
  /**
   * Local int variable, used to find the current workout that will be edited.
   */
  private int currentWorkoutIndex;

  /**
   * Constructs a new User instance.
   *
   * <p>
   * This constructor creates a new User object. A User represents a user of the
   * workout planner application, including a list of workouts associated with
   * the user. The constructor initializes the list of workouts as a new
   * ArrayList.
   * </p>
   */
  public User() {
    // Constructor with a new list of workouts
    this.workouts = new ArrayList<>();
  }

  /**
   * Removes a workout from the list of workouts.
   *
   * <p>
   * This method allows you to remove a specific workout from the list of
   * workouts associated with the user. It takes the provided index and removes
   * the workout at that position from the list.
   * </p>
   *
   * @param workoutIndex The index of the workout to be removed from the list of
   *                     workouts.
   */
  public void removeWorkout(final int workoutIndex) {
    // Remove a workout from the list of workouts
    workouts.remove(workoutIndex);
  }

  /**
   * Removes the current workout from the list of workouts.
   *
   * <p>
   * This method allows you to remove the workout that is currently selected as
   * the "current" workout from the list of workouts associated with the user.
   * It removes the current workout and resets the current workout index to -1.
   * </p>
   */
  public void removeCurrentWorkout() {
    // Remove the current workout from the list of workouts
    workouts.remove(currentWorkoutIndex);
    currentWorkoutIndex = -1;
  }

  /**
   * Returns the current workout or creates a new one if it doesn't exist.
   *
   * <p>
   * This method retrieves the workout that is currently selected as the
   * "current" workout from the list of workouts associated with the user.
   * If no current workout exists, it creates a new workout, makes it the
   * current one, and adds it to the list of workouts.
   * </p>
   *
   * @return The current Workout object or a new one if it doesn't exist.
   */
  public Workout getCurrentWorkout() {
    // Return the current workout or create a new one if it doesn't exist
    if (currentWorkoutIndex == -1) {
      currentWorkoutIndex = workouts.size();
      workouts.add(new Workout());
    }
    return workouts.get(currentWorkoutIndex);
  }

  /**
   * Sets the current workout to the one at the specified index in the list of
   * workouts.
   *
   * <p>
   * This method updates the current workout to the workout located at the
   * provided index within the list of workouts associated with the user. The
   * specified index should be within the valid range of indices for the list.
   * </p>
   *
   * @param workoutIndex The index of the workout to set as the current workout.
   */
  public void setCurrentWorkout(final int workoutIndex) {
    // Set the current workout
    currentWorkoutIndex = workoutIndex;
  }

  /**
   * Returns the list of workouts associated with the user.
   *
   * <p>
   * This method provides access to the list of workouts associated with the
   * user. The workouts are returned as a List, enabling external code to work
   * with and iterate through the workouts.
   * </p>
   *
   * @return A List of Workout objects representing the user's workouts.
   */
  public List<Workout> getWorkouts() {
    // Return the list of workouts
    return workouts;
  }

  /**
   * Adds a workout to the list of workouts from a file.
   *
   * <p>
   * This method adds a provided workout to the list of workouts. It is
   * typically used to populate the list of workouts by loading them from a
   * file.
   * </p>
   *
   * @param workout The workout to be added to the list of workouts.
   */
  public void addWorkoutFromFile(final Workout workout) {
    workouts.add(workout);
  }
}
