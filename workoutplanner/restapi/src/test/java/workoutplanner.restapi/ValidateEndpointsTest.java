package workoutplanner.restapi;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidateEndpointsTest {

    // Test data
    private static final List<String> VALID_EXERCISE_NAMES = Arrays.asList("Push-up", "Sit-up");
    private static final int MAX_WORKOUT_INDEX = 10;
    private static final int MAX_EXERCISE_INDEX = 20;
    private static final List<String> USED_WORKOUT_NAMES = Arrays.asList("Workout1", "Workout2");

    @Test
    void testValidateExerciseInput_ValidInput() {
        assertTrue(ValidateEndpoints.validateExerciseInput("Push-up", 3, 0, 10, 20, VALID_EXERCISE_NAMES));
    }

    @Test
    void testValidateExerciseInput_InvalidName() {
        assertFalse(ValidateEndpoints.validateExerciseInput(null, 3, 0, 10, 20, VALID_EXERCISE_NAMES));
    }

    @Test
    void testValidateExerciseInput_InvalidSets() {
        assertFalse(ValidateEndpoints.validateExerciseInput("Push-up", 0, 0, 10, 20, VALID_EXERCISE_NAMES));
    }

    @Test
    void testValidateExerciseInput_InvalidWeight() {
        assertFalse(ValidateEndpoints.validateExerciseInput("Push-up", 3, 0, 10, -1, VALID_EXERCISE_NAMES));
    }

    @Test
    void testValidateExerciseInput_InvalidReps() {
        assertFalse(ValidateEndpoints.validateExerciseInput("Push-up", 3, 5, 2, 20, VALID_EXERCISE_NAMES));
    }

    @Test
    void testValidateWorkoutIndex_ValidIndex() {
        assertTrue(ValidateEndpoints.validateWorkoutIndex(5, MAX_WORKOUT_INDEX));
    }

    @Test
    void testValidateWorkoutIndex_InvalidIndex() {
        assertFalse(ValidateEndpoints.validateWorkoutIndex(-2, MAX_WORKOUT_INDEX));
    }

    @Test
    void testValidateExerciseIndex_ValidIndex() {
        assertTrue(ValidateEndpoints.validateExerciseIndex(15, MAX_EXERCISE_INDEX));
    }

    @Test
    void testValidateExerciseIndex_InvalidIndex() {
        assertFalse(ValidateEndpoints.validateExerciseIndex(25, MAX_EXERCISE_INDEX));
    }

    @Test
    void testValidateExerciseAttribute_ValidAttribute() {
        assertTrue(ValidateEndpoints.validateExerciseAttribute("sets"));
    }

    @Test
    void testValidateExerciseAttribute_InvalidAttribute() {
        assertFalse(ValidateEndpoints.validateExerciseAttribute("invalid"));
    }

    @Test
    void testValidateSaveWorkoutInput_ValidInput() {
        assertTrue(ValidateEndpoints.validateSaveWorkoutInput("NewWorkout", "2023-01-01", USED_WORKOUT_NAMES));
    }

    @Test
    void testValidateSaveWorkoutInput_InvalidName() {
        assertFalse(ValidateEndpoints.validateSaveWorkoutInput(null, "2023-01-01", USED_WORKOUT_NAMES));
    }

    @Test
    void testValidateSaveWorkoutInput_UsedName() {
        assertFalse(ValidateEndpoints.validateSaveWorkoutInput("Workout1", "2023-01-01", USED_WORKOUT_NAMES));
    }

    @Test
    void testValidateSaveWorkoutInput_InvalidDate() {
        assertFalse(ValidateEndpoints.validateSaveWorkoutInput("NewWorkout", null, USED_WORKOUT_NAMES));
    }
}

