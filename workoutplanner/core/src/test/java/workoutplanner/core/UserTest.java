package workoutplanner.core;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testAddWorkout() {
        Workout workout = new Workout();
        user.addWorkoutFromFile(workout);

        List<Workout> workouts = user.getWorkouts();
        assertEquals(1, workouts.size());
        assertTrue(workouts.contains(workout));
    }

    @Test
    public void testRemoveWorkout() {
        Workout workout = new Workout();
        user.addWorkoutFromFile(workout);

        user.removeWorkout(0);

        List<Workout> workouts = user.getWorkouts();
        assertEquals(0, workouts.size());
    }

    @Test
    public void testRemoveCurrentWorkout() {
        Workout workout = new Workout();
        user.addWorkoutFromFile(workout);
        user.setCurrentWorkout(0);
        user.removeCurrentWorkout();

        List<Workout> workouts = user.getWorkouts();
        assertTrue(workouts.isEmpty());
    }

    @Test
    public void testGetCurrentWorkout() {
        Workout currentWorkout = user.getCurrentWorkout();

        assertNotNull(currentWorkout);
        assertTrue(user.getWorkouts().contains(currentWorkout));
    }

    @Test
    public void testSetCurrentWorkout() {
        Workout workout1 = new Workout();
        Workout workout2 = new Workout();

        user.addWorkoutFromFile(workout1);
        user.addWorkoutFromFile(workout2);

        user.setCurrentWorkout(1);

        assertSame(workout2, user.getCurrentWorkout());
    }
}

