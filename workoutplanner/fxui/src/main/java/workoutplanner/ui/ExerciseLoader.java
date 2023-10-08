package workoutplanner.ui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExerciseLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Method to load exercises from a JSON file
    public static List<String> loadExercisesFromJson(String filePath) throws IOException {
        File jsonFile = new File(filePath);
        return objectMapper.readValue(jsonFile, new TypeReference<List<String>>() {
        });
    }
}
