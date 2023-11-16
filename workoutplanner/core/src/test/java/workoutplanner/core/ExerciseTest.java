package workoutplanner.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ExerciseTest {

  private Exercise exercise;
  private static final int SETS_TEST = 3;
  private static final int REPMIN_TEST = 8;
  private static final int REPMAX_TEST = 12;
  private static final int WEIGHT_TEST = 100;

  @BeforeEach
  public void setUp() {
    exercise = new Exercise(
            "Back Squat", SETS_TEST, REPMIN_TEST, REPMAX_TEST, WEIGHT_TEST);
  }
  @Test
  public void testGetName() {
    assertEquals("Back Squat", exercise.name());
  }
  @Test
  public void testGetSets() {
    assertEquals(SETS_TEST, exercise.sets());
  }
  @Test
  public void testGetRepMin() {
    assertEquals(REPMIN_TEST, exercise.repMin());
  }
  @Test
  public void testGetRepMax() {
    assertEquals(REPMAX_TEST, exercise.repMax());
  }
  @Test
  public void testGetWeight() {
    assertEquals(WEIGHT_TEST, exercise.weight());
  }

  @Test
  public void testExerciseConstructor() {
    Exercise newExercise = new Exercise(
            "Bench Press", SETS_TEST, REPMIN_TEST, REPMAX_TEST, WEIGHT_TEST);
    assertEquals("Bench Press", newExercise.name());
    assertEquals(SETS_TEST, newExercise.sets());
    assertEquals(REPMIN_TEST, newExercise.repMin());
    assertEquals(REPMAX_TEST, newExercise.repMax());
    assertEquals(WEIGHT_TEST, newExercise.weight());
  }
}
