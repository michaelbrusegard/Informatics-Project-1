package workoutplanner.restapi;

import java.util.List;
import workoutplanner.core.Exercise;

public class ValidateEndpoints {
  private static final int LIMIT = 5000;
  public static boolean validateExerciseInput(final Exercise exercise,
                                              final List<String> validNames) {
    // Validate name
    boolean validName = exercise.name() != null
            && !exercise.name().isEmpty()
            && validNames.contains(exercise.name());

    // Validate sets (between 1 and 5000)
    boolean validSets = exercise.sets() > 0 && exercise.sets() <= LIMIT;

    // Validate weight (between 0 and 5000)
    boolean validWeight = exercise.weight() >= 0 && exercise.weight() <= LIMIT;

    // Validate repMin and repMax
    boolean validReps = exercise.repMin() >= 0 && exercise.repMin() <= LIMIT
        && exercise.repMax() > 0 && exercise.repMax() <= LIMIT
        && exercise.repMin() <= exercise.repMax();

    // All conditions must be true for the input to be considered valid
    return validName && validSets && validWeight && validReps;
  }

  public static boolean validateWorkoutIndex(final int workoutIndex,
                                             final int maxWorkoutIndex) {
    return workoutIndex >= -1 && workoutIndex < maxWorkoutIndex;
  }

  public static boolean validateExerciseIndex(final int exerciseIndex,
                                              final int maxExerciseIndex) {
    return exerciseIndex >= 0 && exerciseIndex < maxExerciseIndex;
  }

  public static boolean validateSaveWorkoutInput(final String name,
                                                 final String date) {
    return name != null && !name.isEmpty() && date != null && !date.isEmpty();
  }
}
