package workoutplanner.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorkoutTest {
  private Workout workout;
  private String name;
  private static final int SETS_TEST = 3;
  private static final int REPMIN_TEST = 8;
  private static final int REPMAX_TEST = 12;
  private static final int WEIGHT_TEST = 100;

  @BeforeEach
  public void setUp() {
    name = "My Workout";
    workout = new Workout();
    workout.setName(name);
    LocalDate date = LocalDate.now();
    workout.setDate(date.toString());
  }
  @Test
  public void testGetName() {
    assertEquals(name, workout.getName());
  }
  @Test
  public void testAddExercise() {
    workout.addExercise(
            "Squats", SETS_TEST, REPMIN_TEST, REPMAX_TEST, WEIGHT_TEST);
    workout.addExercise(
            "Bench Press", SETS_TEST, REPMIN_TEST, REPMAX_TEST, WEIGHT_TEST);
    assertEquals(2, workout.getExercises().size());
  }
  @Test
  public void testGetExercises() {
    workout.addExercise(
            "Squats", SETS_TEST, REPMIN_TEST, REPMAX_TEST, WEIGHT_TEST);
    workout.addExercise(
            "Bench Press", SETS_TEST, REPMIN_TEST, REPMAX_TEST, WEIGHT_TEST);
    List<Exercise> exercises = workout.getExercises();
    assertEquals(2, exercises.size());
    assertEquals("Squats", exercises.get(0).name());
    assertEquals("Bench Press", exercises.get(1).name());
  }
}
