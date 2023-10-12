package workoutplanner.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {

    private Workout workout;
    private Date date;
    private String name;

    @BeforeEach
    public void setUp() {
        date = new Date();
        name = "My Workout";
        workout = new Workout();
        workout.setDate(date);
        workout.setName(name);
    }

    @Test
    public void testGetDateAsString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
        String expectedFormattedDate = dateFormat.format(date);
        assertEquals(expectedFormattedDate, workout.getDateAsString());
    }

    @Test
    public void testGetName() {
        assertEquals(name, workout.getName());
    }

    @Test
    public void testAddExercise() {
        workout.addExercise(new Exercise("Squats", 3, 8, 12, 100));
        workout.addExercise(new Exercise("Bench Press", 4, 6, 10, 135));
        assertEquals(2, workout.getExerciseCount());
    }

    @Test
    public void testGetExercises() {
        workout.addExercise(new Exercise("Squats", 3, 8, 12, 100));
        workout.addExercise(new Exercise("Bench Press", 4, 6, 10, 135));
        List<Exercise> exercises = workout.getExercises();
        assertEquals(2, exercises.size());
        assertEquals("Squats", exercises.get(0).getName());
        assertEquals("Bench Press", exercises.get(1).getName());
    }
}
