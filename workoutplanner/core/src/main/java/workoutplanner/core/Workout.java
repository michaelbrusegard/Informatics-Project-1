package workoutplanner.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h1>Workout</h1>
 * <p>
 * A Workout object encapsulates information about a workout,
 * including its name,
 * date of creation, and a list of exercises. It allows you to manage and
 * interact with workout data.
 * </p>
 *
 * @since 1.0.0
 * @author David Salguero Spilde + Michael Brusegard + Arman Ilkka Nemati
 * @version 2.0.0
 */
public class Workout {
  /**
   * Local String variable, used to set the name of a workout.
   */
  private String name;
  /**
   * Local Date variable, used to set the date of when the workout was created.
   */
  private Date date;
  /**
   * Local List variable, used to have a list of all the
   * exercises in the workout.
   */
  private final List<Exercise> exercises = new ArrayList<>();

  /**
   * Constructs a new Workout instance.
   *
   * <p>
   * This constructor creates a new Workout object.
   * A Workout represents a collection
   * of exercises and other workout-related data.
   * </p>
   */
  public Workout() {
  }

  /**
   * Sets the name of the workout.
   *
   * <p>
   * This method allows you to change the name associated with the workout.
   * It updates the name to the provided input name.
   * </p>
   *
   * @param inputName The new name to set for the workout.
   */
  public void setName(final String inputName) {
    this.name = inputName;
  }

  /**
   * Sets the date associated with the workout.
   *
   * <p>
   * This method allows you to change the date associated with the workout.
   * It updates the date to the provided input date.
   * </p>
   *
   * @param inputDate The new date to set for the workout.
   */
  public void setDate(final Date inputDate) {
    this.date = inputDate;
  }

  /**
   * Adds an exercise to the workout.
   *
   * <p>
   * This method is used to include an exercise in the workout.
   * It appends the provided exercise to the list of exercises
   * associated with the workout.
   * </p>
   *
   * @param name   The name of the exercise.
   * @param sets   The number of sets for the exercise.
   * @param repMin The minimum number of reps for the exercise.
   * @param repMax The maximum number of reps for the exercise.
   * @param weight The weight for the exercise.
   * 
   */
  public void addExercise(String name, int sets, int repMin,
      int repMax, int weight) {
    Exercise exercise = new Exercise(name, sets, repMin, repMax, weight);
    this.exercises.add(exercise);
  }

  /**
   * Removes an exercise from the workout.
   *
   * <p>
   * This method is used to remove an exercise from the workout.
   * It removes the exercise at the provided index from the list of exercises
   * associated with the workout.
   * </p>
   *
   * @param exerciseIndex The index of the exercise to remove.
   */
  public void removeExercise(int exerciseIndex) {
    this.exercises.remove(exerciseIndex);
  }

  /**
   * Moves an exercise within the workout.
   *
   * <p>
   * This method is used to move an exercise within the workout.
   * It moves the exercise at the provided index to the left or right
   * within the list of exercises associated with the workout.
   * </p>
   *
   * @param exerciseIndex The index of the exercise to move.
   * @param left          Whether to move the exercise to the left or right.
   */
  public void moveExercise(int exerciseIndex, boolean left) {
    if (exerciseIndex < 0 || exerciseIndex >= exercises.size()) {
      // Invalid exercise index
      return;
    }

    Exercise exercise = exercises.get(exerciseIndex);

    if (left) {
      if (exerciseIndex == 0) {
        // Move the first exercise to the end
        exercises.remove(exerciseIndex);
        exercises.add(exercise);
      } else {
        // Move the exercise to the left
        exercises.remove(exerciseIndex);
        exercises.add(exerciseIndex - 1, exercise);
      }
    } else {
      if (exerciseIndex == exercises.size() - 1) {
        // Move the last exercise to the start
        exercises.remove(exerciseIndex);
        exercises.add(0, exercise);
      } else {
        // Move the exercise to the right
        exercises.remove(exerciseIndex);
        exercises.add(exerciseIndex + 1, exercise);
      }
    }
  }

  /**
   * Retrieves the name of the workout.
   *
   * <p>
   * This method returns the name of the workout.
   * It provides external code with access to the name
   * of the workout for various purposes.
   * </p>
   *
   * @return A String representing the name of the workout.
   */
  public String getName() {
    return name;
  }

  /**
   * Retrieves the formatted date of the workout as a String.
   *
   * <p>
   * This method returns the date of the workout formatted as a String
   * in the pattern "MMMM dd, yyyy HH:mm". It provides external code
   * with access to the date of the workout in a human-readable format.
   * </p>
   *
   * @return A String representing the formatted date of the workout.
   */
  public String getDateAsString() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
    return dateFormat.format(date);
  }

  /**
   * Retrieves the count of exercises in the workout.
   *
   * <p>
   * This method returns the number of exercises in the workout.
   * It provides external code with access to the total count of exercises
   * within the workout object.
   * </p>
   *
   * @return An integer representing the count of exercises in the workout.
   */
  public int getExerciseCount() {
    return exercises.size();
  }

  /**
   * Retrieves the list of exercises in the workout.
   *
   * <p>
   * This method returns a list of Exercise objects, representing
   * the exercises included in the workout. It allows external code
   * to access and work with the list of exercises contained in the workout.
   * </p>
   *
   * @return A List of Exercise objects representing
   *         the exercises in the workout.
   */
  public List<Exercise> getExercises() {
    return exercises;
  }
}
