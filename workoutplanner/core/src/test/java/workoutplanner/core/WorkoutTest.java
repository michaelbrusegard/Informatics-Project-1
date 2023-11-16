package workoutplanner.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class WorkoutTest {

  private Workout workout;
  private String name;

  @BeforeEach
  public void setUp() {
    LocalDateTime date = LocalDateTime.now();
    name = "My Workout";
    workout = new Workout();
    workout.setDate(date.toString());
    workout.setName(name);
  }

  @Test
  public void testGetDateAsString() {
    assertInstanceOf(String.class, workout.getDate());
  }

  @Test
  public void testGetName() {
    assertEquals(name, workout.getName());
  }

  @Test
  public void testSetName() {
    workout.setName("Test Workout");
    assertEquals("Test Workout", workout.getName());
  }

  @Test
  public void testAddExercise() {
    workout.addExercise("Deadlift", 3, 8, 12, 100);
    workout.addExercise("Lunges", 4, 6, 10, 135);
    assertEquals(2, workout.getExercises().size());
  }

  @Test
  public void testRemoveExercise() {
    workout.addExercise("Deadlift", 3, 8, 12, 100);
    workout.removeExercise(0);
    assertEquals(0, workout.getExercises().size());
  }

  @Test
  public void testInvalidInputNumberMoveExercise() {
    workout.addExercise("Deadlift", 3, 10, 15, 0);
    workout.addExercise("Lunges", 3, 12, 18, 0);
    workout.addExercise("Leg Press", 4, 12, 18, 0);
    workout.moveExercise(-1, false);
    List<Exercise> exercises = workout.getExercises();
    assertEquals("Deadlift", exercises.get(0).name());
    assertEquals("Lunges", exercises.get(1).name());
    assertEquals("Leg Press", exercises.get(2).name());
    workout.moveExercise(-1, true);
    assertEquals("Deadlift", exercises.get(0).name());
    assertEquals("Lunges", exercises.get(1).name());
    assertEquals("Leg Press", exercises.get(2).name());
    workout.moveExercise(exercises.size() + 1, false);
    assertEquals("Deadlift", exercises.get(0).name());
    assertEquals("Lunges", exercises.get(1).name());
    assertEquals("Leg Press", exercises.get(2).name());
  }

  @Test
  public void testMoveExerciseToLeft() {
    workout.addExercise("Deadlift", 3, 10, 15, 0);
    workout.addExercise("Lunges", 3, 12, 18, 0);
    workout.addExercise("Leg Press", 4, 12, 18, 0);
    workout.moveExercise(1, true);
    List<Exercise> exercises = workout.getExercises();
    assertEquals("Lunges", exercises.get(0).name());
    assertEquals("Deadlift", exercises.get(1).name());
    assertEquals("Leg Press", exercises.get(2).name());
    workout.moveExercise(0, true);
    assertEquals("Deadlift", exercises.get(0).name());
    assertEquals("Leg Press", exercises.get(1).name());
    assertEquals("Lunges", exercises.get(2).name());
  }

  @Test
  public void testMoveExerciseToRight() {
    workout.addExercise("Deadlift", 3, 10, 15, 0);
    workout.addExercise("Lunges", 3, 12, 18, 0);
    workout.addExercise("Leg Press", 4, 12, 18, 0);
    List<Exercise> exercises = workout.getExercises();
    workout.moveExercise(0, false);
    assertEquals("Lunges", exercises.get(0).name());
    assertEquals("Deadlift", exercises.get(1).name());
    assertEquals("Leg Press", exercises.get(2).name());
    workout.moveExercise(exercises.size() - 1, false);
    assertEquals("Leg Press", exercises.get(0).name());
    assertEquals("Lunges", exercises.get(1).name());
    assertEquals("Deadlift", exercises.get(2).name());
  }

  @Test
  public void testGetExercises() {
    workout.addExercise("Squats", 3, 10, 15, 0);
    workout.addExercise("Bench Press", 3, 12, 18, 0);
    workout.addExercise("Leg Press", 4, 12, 18, 0);
    List<Exercise> exercises = workout.getExercises();
    assertEquals(3, exercises.size());
    assertEquals("Squats", exercises.get(0).name());
    assertEquals("Bench Press", exercises.get(1).name());
  }

  @Test
  public void testSave() {
    assertFalse(workout.getSaved());
    workout.setSaved(true);
    assertTrue(workout.getSaved());
  }
}

