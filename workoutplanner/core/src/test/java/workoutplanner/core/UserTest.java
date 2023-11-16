package workoutplanner.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
  private User user;
  @BeforeEach
  void setUp() {
    user = new User(false);
    user.getCurrentWorkoutSaved();
  }

  @Test
  void testAddAndRemoveWorkout() {
    user.addExerciseToCurrentWorkout("Exercise1", 3, 8, 12, 50);
    // Ensure that there is one workout in the list
    assertEquals(1, user.getWorkoutNames().size());
    // Remove the workout
    user.removeCurrentWorkout();
    // Ensure that the list is empty after removing the workout
    assertTrue(user.getWorkoutNames().isEmpty());
  }


    @Test
    void testMoveExercise() {
        user.addExerciseToCurrentWorkout("Exercise1", 3, 8, 12, 50);
        user.addExerciseToCurrentWorkout("Exercise2", 4, 10, 15, 60);

        // Move the first exercise to the right
        user.moveExerciseInCurrentWorkout(0, false);

        // Check if the order is correct
        assertEquals("Exercise2", user.getCurrentWorkoutExerciseAttribute(0, "name"));
        assertEquals("Exercise1", user.getCurrentWorkoutExerciseAttribute(1, "name"));
    }

    @Test
    void testSaveCurrentWorkout() {
        user.addExerciseToCurrentWorkout("Exercise1", 3, 8, 12, 50);
        user.saveCurrentWorkout("Workout1", "2023-01-01");

        // Ensure that the current workout is saved
        assertTrue(user.getCurrentWorkoutSaved());

        // Ensure the workout has the correct name and date
        assertEquals("Workout1", user.getCurrentWorkoutName());
        assertEquals("2023-01-01", user.getWorkoutDates().get(0));
    }

    @Test
    void testDeleteUnsavedWorkouts() {
        user.addExerciseToCurrentWorkout("Exercise1", 3, 8, 12, 50);
        user.saveCurrentWorkout("Workout1", "2023-01-01");

        // Add another unsaved workout
        user.addExerciseToCurrentWorkout("Exercise2", 4, 10, 15, 60);

        // Delete unsaved workouts
        user.deleteUnsavedWorkouts();

        // Ensure there is only one saved workout remaining
        assertEquals(1, user.getWorkoutNames().size());
        assertTrue(user.getWorkoutNames().contains("Workout1"));
    }

    @Test
    void testRetrievingExerciseAttributes() {
        user.addExerciseToCurrentWorkout("Exercise1", 3, 8, 12, 50);

        // Ensure that the exercise has the correct attributes
        assertEquals("Exercise1", user.getCurrentWorkoutExerciseAttribute(0, "name"));
        assertEquals(3, user.getCurrentWorkoutExerciseAttribute(0, "sets"));
        assertEquals(8, user.getCurrentWorkoutExerciseAttribute(0, "reps"));
        assertEquals(12, user.getCurrentWorkoutExerciseAttribute(0, "rest"));
        assertEquals(50, user.getCurrentWorkoutExerciseAttribute(0, "weight"));
    }

    @Test
    void testRemoveWorkout() {
        user.addExerciseToCurrentWorkout("Exercise1", 3, 8, 12, 50);
        user.saveCurrentWorkout("Workout1", "2023-01-01");

        // Remove the workout
        user.removeWorkout(0);

        // Ensure that the workout is removed
        assertTrue(user.getWorkoutNames().isEmpty());
    }

    @Test
    void testCurrentWorkout() {
        user.addExerciseToCurrentWorkout("Exercise1", 3, 8, 12, 50);
        user.addExerciseToCurrentWorkout("Exercise2", 4, 10, 15, 60);

        // Ensure that the workout has two exercises
        assertEquals(2, user.getCurrentWorkoutExerciseCount());

        // Save current workout
        user.saveCurrentWorkout("Workout1", "2023-01-01");

        // Ensure that the workout is saved
        assertTrue(user.getCurrentWorkoutSaved());

        // Set current workout to unspecified
        user.setCurrentWorkout(-1);
    }
}
