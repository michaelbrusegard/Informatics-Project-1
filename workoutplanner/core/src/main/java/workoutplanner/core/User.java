package workoutplanner.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import workoutplanner.json.ExerciseListLoader;

/**
 * <h1>User</h1>
 * Represents a user in the workoutplanner program.
 *
 * <p>
 * A User object represents a user of the workout planner application,
 * including a list of workouts and the current workout used by the user. It
 * allows you to
 * manage and interact with user data and their workouts.
 * </p>
 *
 * @since 1.0.0
 * @author Michael Brusegard
 * @version 2.0.0
 */
public class User implements UserAccess {
  /**
   * Local List variable, used to have a list of all workouts for the user.
   */
  private final List<Workout> workouts;
  /**
   * Local int variable, used to find the current workout that will be edited.
   */
  private int currentWorkoutIndex = -1;

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
    workouts = new ArrayList<>();
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

  public List<String> getWorkoutNames() {
    List<String> workoutNames = new ArrayList<>();

    for (Workout workout : workouts) {
      workoutNames.add(workout.getName());
    }

    return workoutNames;
  }

  public List<String> getWorkoutDates() {
    List<String> workoutDates = new ArrayList<>();

    for (Workout workout : workouts) {
      workoutDates.add(workout.getDate());
    }

    return workoutDates;
  }

  public void addExerciseToCurrentWorkout(final String inputName,
      final int sets,
      final int repMin,
      final int repMax,
      final int weight) {
    workouts.get(currentWorkoutIndex)
        .addExercise(inputName, sets, repMin, repMax, weight);
  }

  public void removeExerciseFromCurrentWorkout(final int exerciseIndex) {
    workouts.get(currentWorkoutIndex).removeExercise(exerciseIndex);
  }

  public void moveExerciseInCurrentWorkout(final int exerciseIndex,
      final boolean left) {
    workouts.get(currentWorkoutIndex).moveExercise(exerciseIndex, left);
  }

  public void saveCurrentWorkout(final String name, final String date) {
    Workout workout = workouts.get(currentWorkoutIndex);
    workout.setName(name);
    workout.setDate(date);
    workout.setSaved(true);
  }

  public List<String> getExerciseList() {
    try {
      return ExerciseListLoader.loadExerciseListFromJson();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
