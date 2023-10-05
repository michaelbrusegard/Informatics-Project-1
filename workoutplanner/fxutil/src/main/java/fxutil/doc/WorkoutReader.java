package fxutil.doc;

import com.fasterxml.jackson.databind.ObjectMapper;

import workoutplanner.core.Workout;

import java.io.File;
import java.io.IOException;


public class WorkoutReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    // Method to load exercises from a JSON file
    public static Workout loadExercisesFromJson(String filePath) throws IOException {
        File file = new File(filePath);
        Workout readJSON = mapper.readValue(file, Workout.class);
        return readJSON;
    }
}
