package workoutplanner.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import workoutplanner.core.Workout;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPersistence {

  /**
   * Local objectmapper variable, used to map from the json file.
   */
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

  private static final String resourcePath = "/workoutplanner/json/Workouts.json";

  public static List<Workout> loadWorkoutsFromJson() {
    try (FileInputStream fileInputStream = new FileInputStream("../core/src/main/resources" + resourcePath)) {
      return OBJECT_MAPPER.readValue(fileInputStream, new TypeReference<List<Workout>>() {
      });
    } catch (MismatchedInputException e) {
      return new ArrayList<>();
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  public static void saveWorkoutsToJson(List<Workout> workouts) {
    try {
      try (FileOutputStream fileOutputStream = new FileOutputStream("../core/src/main/resources" + resourcePath)) {
        OBJECT_MAPPER.writeValue(fileOutputStream, workouts);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}