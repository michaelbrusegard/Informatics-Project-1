package workoutplanner.restapi;

import java.util.List;

/**
 * <h1>ValidateEndpoints</h1>
 * Provides static methods for validating inputs related to workout planner
 * endpoints.
 *
 * <p>
 * The ValidateEndpoints class contains static methods that validate various
 * inputs related to workout planner endpoints. It includes methods for
 * validating exercise inputs, workout and exercise indices, exercise
 * attributes, and inputs for saving workouts.
 * </p>
 *
 * @since 2.0.0
 * @author Michael Brusegard
 * @version 1.0.0
 */
public final class ValidateEndpoints {
  /**
   * Local int variable, used to define a numerical limit for exercise inputs.
   */
  private static final int LIMIT = 5000;

  /**
   * Local int variable, used to define the maximum number of characters for a
   * name.
   */
  private static final int MAX_NAME_LENGTH = 20;

  private ValidateEndpoints() {
    // Private constructor to prevent instantiation
    throw new UnsupportedOperationException(
            "Utility class should not be instantiated.");
  }

  /**
   * Validates the input for creating an exercise.
   *
   * <p>
   * This static method validates the provided parameters for creating an
   * exercise. It checks if the exercise name is not null, not empty, and exists
   * in the list of valid names. It also validates the number of sets (between 1
   * and 5000), the weight (between 0 and 5000), and the range of repetitions
   * (repMin and repMax) to ensure they are within valid limits. The method
   * returns true if all validation conditions are met; otherwise, it returns
   * false.
   * </p>
   *
   * @param name        The name of the exercise to be validated.
   * @param sets        The number of sets for the exercise (between 1 and
   *                    5000).
   * @param repMin      The minimum number of repetitions for the exercise
   *                    (greater than or equal to 0).
   * @param repMax      The maximum number of repetitions for the exercise
   *                    (greater than 0 and less than or equal to 5000).
   * @param weight      The weight for the exercise (between 0 and 5000).
   * @param validNames  A list of valid exercise names.
   * @return            True if the input is valid; false otherwise.
   */
  public static boolean validateExerciseInput(final String name, final int sets,
      final int repMin, final int repMax, final int weight,
      final List<String> validNames) {
    // Validate name
    boolean validName = name != null
        && !name.isEmpty()
        && validNames.contains(name);

    // Validate sets (between 1 and 5000)
    boolean validSets = sets > 0 && sets <= LIMIT;

    // Validate weight (between 0 and 5000)
    boolean validWeight = weight >= 0 && weight <= LIMIT;

    // Validate repMin and repMax
    boolean validReps = repMin >= 0 && repMax > 0 && repMax <= LIMIT
            && repMin <= repMax;

    // All conditions must be true for the input to be considered valid
    return validName && validSets && validWeight && validReps;
  }

  /**
   * Validates a workout index.
   *
   * <p>
   * This static method validates a workout index to ensure it is within the
   * valid range of indices for the list of workouts.
   * </p>
   *
   * @param workoutIndex The workout index to be validated.
   * @param maxWorkoutIndex The maximum valid workout index.
   * @return True if the workout index is valid; false otherwise.
   */
  public static boolean validateWorkoutIndex(final int workoutIndex,
      final int maxWorkoutIndex) {
    return workoutIndex >= -1 && workoutIndex < maxWorkoutIndex;
  }

  /**
   * Validates an exercise index.
   *
   * <p>
   * This static method validates an exercise index to ensure it is within the
   * valid range of indices for the list of exercises in a workout.
   * </p>
   *
   * @param exerciseIndex The exercise index to be validated.
   * @param maxExerciseIndex The maximum valid exercise index.
   * @return True if the exercise index is valid; false otherwise.
   */
  public static boolean validateExerciseIndex(final int exerciseIndex,
      final int maxExerciseIndex) {
    return exerciseIndex >= 0 && exerciseIndex < maxExerciseIndex;
  }

  /**
   * Validates an exercise attribute.
   *
   * <p>
   * This static method validates an exercise attribute to ensure it is one of
   * the valid attribute types ("name", "sets", "repMin", "repMax", "weight").
   * </p>
   *
   * @param attribute The exercise attribute to be validated.
   * @return True if the exercise attribute is valid; false otherwise.
   */
  public static boolean validateExerciseAttribute(final String attribute) {
    return "name".equals(attribute) || "sets".equals(attribute)
            || "repMin".equals(attribute) || "repMax".equals(attribute)
            || "weight".equals(attribute);
  }

  /**
   * Validates input for saving a workout.
   *
   * <p>
   * This static method validates the input parameters for saving a workout. It
   * checks if the provided name is not null, not empty, and does not exist in
   * the list of used workout names. It also validates the date to ensure it is
   * not null or empty. The method returns true if all validation conditions are
   * met; otherwise, it returns false.
   * </p>
   *
   * @param name        The name of the workout to be saved.
   * @param date        The date of the workout to be saved.
   * @param usedNames   A list of names already used for workouts.
   * @return            True if the input for saving the workout is valid;
   *                    false otherwise.
   */
  public static boolean validateSaveWorkoutInput(final String name,
      final String date, final List<String> usedNames) {
    return name != null && !usedNames.contains(name) && name.length()
            < MAX_NAME_LENGTH && date != null && !date.isEmpty();
  }
}
