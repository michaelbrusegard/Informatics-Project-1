package workoutplanner.fxutil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ExerciseLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Method to load exercises from a JSON file as a URL
    public static List<String> loadExercisesFromJson() throws IOException {
        // Get the URL of the resource
        URL resourceURL = ExerciseLoader.class.getResource("exercices.json");

        if (resourceURL == null) {
            throw new IOException("Resource not found: exercices.json");
        }

        // Open an InputStream from the URL
        try (InputStream inputStream = resourceURL.openStream()) {
            return objectMapper.readValue(inputStream, new TypeReference<List<String>>() {
            });
        }
    }
}