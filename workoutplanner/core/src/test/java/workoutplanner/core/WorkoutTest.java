package workoutplanner.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workoutplanner.core.Workout;

public class WorkoutTest {
    Workout workout = new Workout(LocalDate.now(), null);
    @BeforeEach
    public void setUp(){

    }

    @Test
    public void testDate(){
        assertEquals(LocalDate.now(), workout.getDate());
    }
}
