package workoutplanner.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {

    private Exercise exercise;

    @BeforeEach
    public void setUp() {
        exercise = new Exercise("Back Squat", 3, 8, 12, 100);
    }

    @Test
    public void testGetName() {
        assertEquals("Back Squat", exercise.getName());
    }

    @Test
    public void testGetSets() {
        assertEquals(3, exercise.getSets());
    }

    @Test
    public void testGetRepMin() {
        assertEquals(8, exercise.getRepMin());
    }

    @Test
    public void testGetRepMax() {
        assertEquals(12, exercise.getRepMax());
    }

    @Test
    public void testGetWeight() {
        assertEquals(100, exercise.getWeight());
    }

    @Test
    public void testExerciseConstructor() {
        Exercise newExercise = new Exercise("Bench Press", 4, 6, 10, 135);
        assertEquals("Bench Press", newExercise.getName());
        assertEquals(4, newExercise.getSets());
        assertEquals(6, newExercise.getRepMin());
        assertEquals(10, newExercise.getRepMax());
        assertEquals(135, newExercise.getWeight());
    }
}
