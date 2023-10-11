package workoutplanner.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserTest {

    private User user;
    private String name;
    private String password;
    private List<Workout> workouts;

    @BeforeEach
    public void setUp() {
        name = "Erlend";
        password = "David";
        workouts = new ArrayList<>(List.of(new Workout(new Date())));
        user = new User(name, password, workouts);
    }

    @Test
    public void constructorTest() {
        assertEquals(user.getName(), name);
        assertEquals(user.getPasswd(), password);
        assertEquals(user.getWorkouts(), workouts);
    }

    @Test
    public void getNameTest() {
        assertEquals(user.getName(), name);
    }

    @Test
    public void getPasswordTest() {
        assertEquals(user.getPasswd(), password);
    }

    @Test
    public void getWorkoutsTest() {
        assertEquals(user.getWorkouts(), workouts);
    }
}
