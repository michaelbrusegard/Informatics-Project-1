package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExerciseTest {

    private List<Exercise> exercises;

    @BeforeEach
    public void setUp() {
        exercises = new ArrayList<>();
        exercises.add(new Exercise("Exercise1", "Legs", 12, 100));
        exercises.add(new Exercise("Exercise2", "Chest", 10, 80));
        exercises.add(new Exercise("Exercise3", "Back", 8, 120));
    }

    @Test
    public void testGetName() {
        Exercise exercise = new Exercise("TestExercise", "TestMuscleGroup", 10, 50);
        assertEquals("TestExercise", exercise.getName());
    }

    @Test
    public void testGetMuscleGroup() {
        Exercise exercise = new Exercise("TestExercise", "TestMuscleGroup", 10, 50);
        assertEquals("TestMuscleGroup", exercise.getMuscleGroup());
    }

    @Test
    public void testGetReps() {
        Exercise exercise = new Exercise("TestExercise", "TestMuscleGroup", 10, 50);
        assertEquals(10, exercise.getReps());
    }

    @Test
    public void testGetWeight() {
        Exercise exercise = new Exercise("TestExercise", "TestMuscleGroup", 10, 50);
        assertEquals(50, exercise.getWeight());
    }

    @Test
    public void testToString() {
        Exercise exercise = new Exercise("TestExercise", "TestMuscleGroup", 10, 50);
        assertEquals("TestExercise,TestMuscleGroup,", exercise.toString());
    }

    @Test
    public void testCompareTo() {
        // Sort the list based on muscle group
        Collections.sort(exercises);

        assertEquals("Back", exercises.get(0).getMuscleGroup());
        assertEquals("Chest", exercises.get(1).getMuscleGroup());
        assertEquals("Legs", exercises.get(2).getMuscleGroup());
    }
}
