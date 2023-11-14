package workoutplanner.core;

import java.io.IOException;
import java.io.Reader;
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
  @Override
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
  @Override
  public void removeCurrentWorkout() {
    // Remove the current workout from the list of workouts
    workouts.remove(currentWorkoutIndex);
    currentWorkoutIndex = -1;
  }

  @Override
  public boolean getCurrentWorkoutSaved() {
    // Return the current workout or create a new one if it doesn't exist
    if (currentWorkoutIndex == -1) {
      currentWorkoutIndex = workouts.size();
      workouts.add(new Workout());
    }
    return workouts.get(currentWorkoutIndex).getSaved();
  }

  @Override
  public int getCurrentWorkoutExerciseCount() {
    return workouts.get(currentWorkoutIndex).getExercises().size();
  }

  @Override
  public String getCurrentWorkoutName() {
    return workouts.get(currentWorkoutIndex).getName();
  }

  @Override
  public String getCurrentWorkoutExerciseAttribute(int exerciseIndex, String attribute) {
    switch (attribute) {
      case "name":
        return workouts.get(currentWorkoutIndex).getExercises()
            .get(exerciseIndex).name();
      case "sets":
        return String.valueOf(workouts.get(currentWorkoutIndex).getExercises()
            .get(exerciseIndex).sets());
      case "repMin":
        return String.valueOf(workouts.get(currentWorkoutIndex).getExercises()
            .get(exerciseIndex).repMin());
      case "repMax":
        return String.valueOf(workouts.get(currentWorkoutIndex).getExercises()
            .get(exerciseIndex).repMax());
      case "weight":
        return String.valueOf(workouts.get(currentWorkoutIndex).getExercises()
            .get(exerciseIndex).weight());
      default:
        throw new IllegalArgumentException("Unknown attribute: " + attribute);
    }
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
  @Override
  public void setCurrentWorkout(final int workoutIndex) {
    // Set the current workout
    currentWorkoutIndex = workoutIndex;
  }

  @Override
  public List<String> getWorkoutNames() {
    List<String> workoutNames = new ArrayList<>();

    for (Workout workout : workouts) {
      workoutNames.add(workout.getName());
    }

    return workoutNames;
  }

  @Override
  public List<String> getWorkoutDates() {
    List<String> workoutDates = new ArrayList<>();

    for (Workout workout : workouts) {
      workoutDates.add(workout.getDate());
    }

    return workoutDates;
  }

  @Override
  public void addExerciseToCurrentWorkout(final String inputName,
      final int sets,
      final int repMin,
      final int repMax,
      final int weight) {
    workouts.get(currentWorkoutIndex)
        .addExercise(inputName, sets, repMin, repMax, weight);
  }

  @Override
  public void removeExerciseFromCurrentWorkout(final int exerciseIndex) {
    workouts.get(currentWorkoutIndex).removeExercise(exerciseIndex);
  }

  @Override
  public void moveExerciseInCurrentWorkout(final int exerciseIndex,
      final boolean left) {
    workouts.get(currentWorkoutIndex).moveExercise(exerciseIndex, left);
  }

  @Override
  public void saveCurrentWorkout(final String name, final String date) {
    Workout workout = workouts.get(currentWorkoutIndex);
    workout.setName(name);
    workout.setDate(date);
    workout.setSaved(true);
  }

  @Override
  public List<String> getExerciseList() {
    try {
      return ExerciseListLoader.loadExerciseListFromJson();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
