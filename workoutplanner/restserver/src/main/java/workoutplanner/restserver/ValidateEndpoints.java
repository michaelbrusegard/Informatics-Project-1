package workoutplanner.restserver;

import java.util.List;

import workoutplanner.core.Exercise;

public class ValidateEndpoints {
  public static boolean validateExerciseInput(Exercise exercise, List<String> validNames) {
    // Validate name
    boolean validName = exercise.name() != null && exercise.name().length() > 0 && validNames.contains(exercise.name());

    // Validate sets (between 1 and 5000)
    boolean validSets = exercise.sets() > 0 && exercise.sets() <= 2000;

    // Validate weight (between 0 and 5000)
    boolean validWeight = exercise.weight() >= 0 && exercise.weight() <= 5000;

    // Validate repMin and repMax
    boolean validReps = exercise.repMin() >= 0 && exercise.repMin() <= 5000
        && exercise.repMax() > 0 && exercise.repMax() <= 5000
        && exercise.repMin() <= exercise.repMax();

    // All conditions must be true for the input to be considered valid
    return validName && validSets && validWeight && validReps;
  }

  public static boolean validateWorkoutIndex(int workoutIndex, int maxWorkoutIndex) {
    return workoutIndex >= -1 && workoutIndex < maxWorkoutIndex;
  }

  public static boolean validateExerciseIndex(int exerciseIndex, int maxExerciseIndex) {
    return exerciseIndex >= 0 && exerciseIndex < maxExerciseIndex;
  }

  public static boolean validateSaveWorkoutInput(String name, String date) {
    return name != null && name.length() > 0 && date != null && date.length() > 0;
  }
}
