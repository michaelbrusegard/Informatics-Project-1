package workoutplanner.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {

    private Workout workout;

    @BeforeEach
    public void setUp() {
        workout = new Workout();
    }

    @Test
    public void testSetAndGetDate() {
        Date date = new Date();
        workout.setDate(date);
        assertEquals(date, workout.getDate());
    }

    @Test
    public void testAddExercise() {
        workout.addExercise("Squats", 3, 8, 12, 100);
        workout.addExercise("Bench Press", 4, 6, 10, 135);
        assertEquals(2, workout.getExerciseCount());
    }

    @Test
    public void testGetExercises() {
        workout.addExercise("Squats", 3, 8, 12, 100);
        workout.addExercise("Bench Press", 4, 6, 10, 135);
        List<Exercise> exercises = workout.getExercises();
        assertEquals(2, exercises.size());
        assertEquals("Squats", exercises.get(0).getName());
        assertEquals("Bench Press", exercises.get(1).getName());
    }
}
