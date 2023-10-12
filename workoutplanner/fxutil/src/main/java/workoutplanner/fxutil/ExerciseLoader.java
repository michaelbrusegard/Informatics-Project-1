package workoutplanner.fxutil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExerciseLoader {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    // Method to load exercises from a JSON file as a URL
    public static List<String> loadExercisesFromJson() throws IOException {
        // Get the URL of the resource
        String resourcePath = "/workoutplanner/fxutil/exercises.json";
        InputStream resourceURL = ExerciseLoader.class.getResourceAsStream(resourcePath);

        if (resourceURL == null) {
            throw new IOException("Resource not found: exercises.json");
        }

        // Open an InputStream from the URL
        try (InputStream inputStream = resourceURL) {
            return objectMapper.readValue(inputStream, new TypeReference<>() {
            });
        }
    }
}