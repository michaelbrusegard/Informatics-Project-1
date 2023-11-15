package workoutplanner.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import workoutplanner.core.Workout;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPersistence {

  /**
   * Local objectmapper variable, used to map from the json file.
   */
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

  private static final String resourcePath = "/workoutplanner/json/Workouts.json";

  public static List<Workout> loadWorkoutsFromJson() {
    InputStream resourceStream = WorkoutPersistence.class
        .getResourceAsStream(resourcePath);
    try {
      return OBJECT_MAPPER.readValue(resourceStream, new TypeReference<List<Workout>>() {
      });
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  public static void saveWorkoutsToJson(List<Workout> workouts) {
    try {
      Path path = Path.of("../core/src/main/resources" + resourcePath);

      try (FileOutputStream fileOutputStream = new FileOutputStream(path.toFile())) {
        OBJECT_MAPPER.writeValue(fileOutputStream, workouts);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}