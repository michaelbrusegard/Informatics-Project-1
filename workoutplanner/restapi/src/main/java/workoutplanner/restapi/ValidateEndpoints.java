package workoutplanner.restapi;

import java.util.List;

public class ValidateEndpoints {
  private static final int LIMIT = 5000;

  public static boolean validateExerciseInput(final String name, final int sets, final int repMin, final int repMax,
      final int weight,
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
    boolean validReps = repMin >= 0 && repMin <= LIMIT
        && repMax > 0 && repMax <= LIMIT
        && repMin <= repMax;

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

  public static boolean validateExerciseAttribute(final String attribute) {
    if ("name".equals(attribute) || "sets".equals(attribute) || "repMin".equals(attribute) ||
        "repMax".equals(attribute) || "weight".equals(attribute)) {
      return true;
    }
    return false;
  }

  public static boolean validateSaveWorkoutInput(final String name,
      final String date, final List<String> usedNames) {
    return name != null && !usedNames.contains(name) && date != null && !date.isEmpty();
  }
}
