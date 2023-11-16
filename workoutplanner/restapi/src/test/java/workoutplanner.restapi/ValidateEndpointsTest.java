package workoutplanner.restapi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;


class ValidateEndpointsTest {

  // Test data
  private static final List<String> VALID_EXERCISE_NAMES = Arrays.asList("Push-up", "Sit-up");
  private static final int MAX_WORKOUT_INDEX = 10;
  private static final int MAX_EXERCISE_INDEX = 20;
  private static final List<String> USED_WORKOUT_NAMES = Arrays.asList("Workout1", "Workout2");

  @Test
  void testValidateExerciseInputValidInput() {
    assertTrue(ValidateEndpoints.validateExerciseInput("Push-up", 3, 0, 10, 20, VALID_EXERCISE_NAMES));
  }

  @Test
  void testValidateExerciseInputInvalidName() {
    assertFalse(ValidateEndpoints.validateExerciseInput(null, 3, 0, 10, 20, VALID_EXERCISE_NAMES));
  }

  @Test
  void testValidateExerciseInputInvalidSets() {
    assertFalse(ValidateEndpoints.validateExerciseInput("Push-up", 0, 0, 10, 20, VALID_EXERCISE_NAMES));
  }

  @Test
  void testValidateExerciseInputInvalidWeight() {
    assertFalse(ValidateEndpoints.validateExerciseInput("Push-up", 3, 0, 10, -1, VALID_EXERCISE_NAMES));
  }

  @Test
  void testValidateExerciseInputInvalidReps() {
    assertFalse(ValidateEndpoints.validateExerciseInput("Push-up", 3, 5, 2, 20, VALID_EXERCISE_NAMES));
  }

  @Test
  void testValidateWorkoutIndexValidIndex() {
    assertTrue(ValidateEndpoints.validateWorkoutIndex(5, MAX_WORKOUT_INDEX));
  }

  @Test
  void testValidateWorkoutIndexInvalidIndex() {
    assertFalse(ValidateEndpoints.validateWorkoutIndex(-2, MAX_WORKOUT_INDEX));
  }

  @Test
  void testValidateExerciseIndexValidIndex() {
    assertTrue(ValidateEndpoints.validateExerciseIndex(15, MAX_EXERCISE_INDEX));
  }

  @Test
  void testValidateExerciseIndexInvalidIndex() {
    assertFalse(ValidateEndpoints.validateExerciseIndex(25, MAX_EXERCISE_INDEX));
  }

  @Test
  void testValidateExerciseAttributeValidAttribute() {
    assertTrue(ValidateEndpoints.validateExerciseAttribute("sets"));
  }

  @Test
  void testValidateExerciseAttributeInvalidAttribute() {
    assertFalse(ValidateEndpoints.validateExerciseAttribute("invalid"));
  }

  @Test
  void testValidateSaveWorkoutInputValidInput() {
    assertTrue(ValidateEndpoints.validateSaveWorkoutInput("NewWorkout", "2023-01-01", USED_WORKOUT_NAMES));
  }

  @Test
  void testValidateSaveWorkoutInputInvalidName() {
    assertFalse(ValidateEndpoints.validateSaveWorkoutInput(null, "2023-01-01", USED_WORKOUT_NAMES));
  }

  @Test
  void testValidateSaveWorkoutInputUsedName() {
    assertFalse(ValidateEndpoints.validateSaveWorkoutInput("Workout1", "2023-01-01", USED_WORKOUT_NAMES));
  }

  @Test
  void testValidateSaveWorkoutInputInvalidDate() {
    assertFalse(ValidateEndpoints.validateSaveWorkoutInput("NewWorkout", null, USED_WORKOUT_NAMES));
  }
}

