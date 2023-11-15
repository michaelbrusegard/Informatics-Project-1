package workoutplanner.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import workoutplanner.core.User;
import workoutplanner.core.Workout;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class WorkoutplannerPersistenceTest {

    private final WorkoutplannerPersistence persistence = new WorkoutplannerPersistence();

    @Test
    public void testSerializersDeserializers_usingStringWriter() {
        User user = new User();
        try {
            StringWriter writer = new StringWriter();
            ObjectMapper mapper = WorkoutplannerPersistence.createObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer,user);
            String json = writer.toString();
            System.out.println(json);
            User user2 = mapper.readValue(new StringReader(json),User.class);
            checkUser(user,user2);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    private void checkUser(User expectedUser, User actualUser) {
        // Check if workouts are the same
        List<Workout> expectedWorkouts = expectedUser.getWorkouts();
        List<Workout> actualWorkouts = actualUser.getWorkouts();
        assertEquals(expectedWorkouts.size(), actualWorkouts.size(), "Number of workouts should be the same");

        for (int i = 0; i < expectedWorkouts.size(); i++) {
            assertEquals(expectedWorkouts.get(i), actualWorkouts.get(i), "Workouts should be the same at index " + i);
        }

        // Check if currentWorkoutIndex is the same
        assertEquals(expectedUser.getCurrentWorkoutIndex(), actualUser.getCurrentWorkoutIndex(), "Current workout index should be the same");
    }

    @Test
    public void testSerializersDeserializers_usingSaveFile() {
        User user = new User();
        // set unique save file path
        persistence.setSaveFilePath("PersitenceTest" + System.currentTimeMillis());
        Path saveFilePath = persistence.getSaveFilePath();
        try {
            persistence.saveUser(user);
            assertTrue(Files.exists(saveFilePath));
            User user2 = persistence.loadUser();
            checkUser(user, user2);
        } catch (IOException e) {
            fail(e.getMessage());
        } finally {
            try {
                Files.deleteIfExists(saveFilePath);
            } catch (IOException e) {
            }
        }
    }

}
