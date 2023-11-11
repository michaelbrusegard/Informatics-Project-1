package workoutplanner.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {

    private Workout workout;
    private LocalDate date;
    private String name;

    @BeforeEach
    public void setUp() {
        date = LocalDate.now();
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
        workout.addExercise(new Exercise("Deadlift", 3, 8, 12, 100));
        workout.addExercise(new Exercise("Lunges", 4, 6, 10, 135));
        assertEquals(2, workout.getExercises().size());
    }

    @Test
    public void testRemoveExercise() {
        workout.addExercise(new Exercise("Deadlift", 3, 8, 12, 100));
        workout.removeExercise(0);
        assertEquals(0, workout.getExercises().size());
    }

    @Test
    public void testInvalidInputNumberMoveExercise() {
        workout.addExercise(new Exercise("Deadlift", 3, 10, 15, 0));
        workout.addExercise(new Exercise("Lunges", 3, 12, 18, 0));
        workout.addExercise(new Exercise("Leg Press", 4, 12, 18, 0));

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
        workout.addExercise(new Exercise("Deadlift", 3, 10, 15, 0));
        workout.addExercise(new Exercise("Lunges", 3, 12, 18, 0));
        workout.addExercise(new Exercise("Leg Press", 4, 12, 18, 0));
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
        workout.addExercise(new Exercise("Deadlift", 3, 10, 15, 0));
        workout.addExercise(new Exercise("Lunges", 3, 12, 18, 0));
        workout.addExercise(new Exercise("Leg Press", 4, 12, 18, 0));

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
        workout.addExercise(new Exercise("Squats", 3, 8, 12, 100));
        workout.addExercise(new Exercise("Bench Press", 4, 6, 10, 135));
        List<Exercise> exercises = workout.getExercises();
        assertEquals(2, exercises.size());
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

