package workoutplanner.core;

import java.util.List;

/**
 * <h1>UserAccess</h1>
 * Provides access to user-related operations in the workout planner
 * application.
 *
 * <p>
 * The UserAccess interface defines a set of methods for accessing and
 * manipulating user-related data, including information about the current
 * workout, exercise details, workout names and dates, and various operations
 * such as adding, removing, and updating workouts and exercises.
 * </p>
 *
 * @since 2.0.0
 * @author Micheal Brusegard
 * @version 1.0.0
 */
public interface UserAccess {
  /**
   * Retrieves the saved status of the current workout.
   *
   * <p>
   * This method checks if a current workout exists. If the current workout does
   * not exist (i.e., the current workout index is -1), it creates a new workout
   * and sets it as the current workout. It then returns the saved status of the
   * current workout. If a current workout already exists, it directly returns
   * its saved status. The saved status indicates whether changes have been
   * saved for the current workout.
   * </p>
   *
   * @return True if the current workout is saved; false otherwise.
   */
  boolean getCurrentWorkoutSaved();

  /**
   * Retrieves the number of exercises in the current workout.
   *
   * <p>
   * This method returns the count of exercises in the current workout. It
   * retrieves the list of exercises from the current workout using the
   * {@code getExercises()} method and returns the size of the list.
   * </p>
   *
   * @return The number of exercises in the current workout.
   */
  int getCurrentWorkoutExerciseCount();

  /**
   * Retrieves the name of the current workout.
   *
   * <p>
   * This method returns the name of the current workout. It retrieves the
   * workout from the list of workouts using the {@code get()} method with the
   * current workout index, and then it calls the {@code getName()} method on
   * the workout object to obtain the name.
   * </p>
   *
   * @return The name of the current workout.
   */
  String getCurrentWorkoutName();

  /**
   * Retrieves a specific attribute of an exercise in the current workout.
   *
   * <p>
   * This method accepts an index representing the position of the exercise
   * in the current workout and a String attribute specifying the information
   * to retrieve (e.g., "name", "sets", "repMin", "repMax", "weight"). It uses a
   * switch expression to determine the attribute type and returns the
   * corresponding value from the Exercise object at the specified index.
   * </p>
   *
   * @param exerciseIndex The index of the exercise in the current workout.
   * @param attribute     The attribute to retrieve ("name", "sets", "repMin",
   *                      "repMax", "weight").
   * @return A String representing the specified attribute of the exercise.
   * @throws IllegalArgumentException If the provided attribute is unknown.
   */
  String getCurrentWorkoutExerciseAttribute(int exerciseIndex,
                                            String attribute);

  /**
   * Retrieves a list of names for all workouts.
   *
   * <p>
   * This method iterates through the list of workouts and extracts the name
   * of each workout. It creates a new ArrayList and populates it with the names
   * of all workouts using the {@code getName()} method of each Workout object.
   * The resulting list is then returned.
   * </p>
   *
   * @return A List of String objects representing the names of all workouts.
   */
  List<String> getWorkoutNames();

  /**
   * Retrieves a list of dates for all workouts.
   *
   * <p>
   * This method iterates through the list of workouts and extracts the date
   * of each workout. It creates a new ArrayList and populates it with the dates
   * of all workouts using the {@code getDate()} method of each Workout object.
   * The resulting list is then returned.
   * </p>
   *
   * @return A List of String objects representing the dates of all workouts.
   */
  List<String> getWorkoutDates();

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
  void setCurrentWorkout(int workoutIndex);

  /**
   * Adds a new exercise to the current workout.
   *
   * <p>
   * This method adds a new exercise to the current workout with the specified
   * parameters, including the exercise name, number of sets, minimum and
   * maximum repetitions, and weight. It delegates the addition to the
   * current Workout object, updating the exercise list accordingly.
   * </p>
   *
   * @param inputName The name of the new exercise.
   * @param sets      The number of sets for the new exercise.
   * @param repMin    The minimum number of repetitions for the new exercise.
   * @param repMax    The maximum number of repetitions for the new exercise.
   * @param weight    The weight for the new exercise in kilograms.
   */
  void addExerciseToCurrentWorkout(String inputName,
      int sets,
      int repMin,
      int repMax,
      int weight);

  /**
   * Removes an exercise from the current workout.
   *
   * <p>
   * This method removes the exercise at the specified index from the list of
   * exercises in the current workout. It delegates the removal to the current
   * Workout object, updating the exercise list accordingly.
   * </p>
   *
   * @param exerciseIndex The index of the exercise to be removed.
   * @throws IndexOutOfBoundsException If the provided index is out of range
   *                                   (index < 0 || index >= exercise count).
   */
  void removeExerciseFromCurrentWorkout(int exerciseIndex);

  /**
   * Moves an exercise within the current workout's exercise list.
   *
   * <p>
   * This method adjusts the position of an exercise within the list of
   * exercises in the current workout. It delegates the movement operation
   * to the current Workout object, updating the exercise list accordingly.
   * </p>
   *
   * @param exerciseIndex The index of the exercise to be moved.
   * @param left          A boolean indicating the direction of movement.
   *                      If {@code true}, the exercise is moved to the left
   *                      (towards the beginning of the list); if {@code false},
   *                      the exercise is moved to the right (towards the end of
   *                      the list).
   * @throws IndexOutOfBoundsException If the provided index is out of range
   *                                   (index < 0 || index >= exercise count),
   *                                   or if attempting to move an exercise
   *                                   beyond the list boundaries.
   */
  void moveExerciseInCurrentWorkout(int exerciseIndex, boolean left);

  /**
   * Saves the current workout with the provided name and date.
   *
   * <p>
   * This method updates the details of the current workout, such as its name
   * and date. Additionally, it marks the workout as "saved." If the application
   * is configured for persistence, it also updates the stored JSON file to
   * reflect the changes.
   * </p>
   *
   * @param name The new name for the current workout.
   * @param date The new date for the current workout.
   * @throws IllegalArgumentException If the provided name or date is null or
   *                                  empty.
   */
  void saveCurrentWorkout(String name, String date);

  /**
   * Removes a workout from the list of workouts.
   *
   * <p>
   * This method removes a workout at the specified index from the list of
   * workouts. If the application is configured for persistence, it also updates
   * the stored JSON file to reflect the changes.
   * </p>
   *
   * @param workoutIndex The index of the workout to be removed.
   * @throws IndexOutOfBoundsException If the provided index is out of range
   *                                   (index < 0 || index >= workout count).
   */
  void removeWorkout(int workoutIndex);

  /**
   * Removes the current workout from the list of workouts.
   *
   * <p>
   * This method allows you to remove the workout that is currently selected as
   * the "current" workout from the list of workouts associated with the user.
   * It removes the current workout and resets the current workout index to -1.
   * </p>
   */
  void removeCurrentWorkout();

  /**
   * Deletes unsaved workouts from the list.
   *
   * <p>
   * This method iterates through the list of workouts and removes any
   * unsaved workouts. An unsaved workout is identified by checking its
   * "saved" status. If a workout is not saved, it is removed from the list.
   * </p>
   *
   * @since 1.0.0
   */
  void deleteUnsavedWorkouts();

  /**
   * Retrieves the list of available exercises.
   *
   * <p>
   * This method attempts to load the exercise list from a JSON file using
   * the {@code ExerciseListLoader} class. If the loading is successful, the
   * method returns the list of exercises. If an {@code IOException} occurs
   * during the loading process, it is caught, logged, and the method returns
   * {@code null}.
   * </p>
   *
   * @return The list of available exercises, or {@code null} if there is an
   *         issue during the loading process.
   * @since 1.0.0
   */
  List<String> getExerciseList();
}
