package fxutil.doc;

import com.fasterxml.jackson.databind.ObjectMapper;

import workoutplanner.core.Workout;

import java.io.File;
import java.io.IOException;

public class WorkoutWriter {
    private static final ObjectMapper mapper = new ObjectMapper();

    // Method to load exercises from a JSON file
    public static void loadExercisesFromJson(String filePath, Workout workout) throws IOException {
        File file = new File(filePath);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workout);
        mapper.writeValue(file, json);
    }
}
