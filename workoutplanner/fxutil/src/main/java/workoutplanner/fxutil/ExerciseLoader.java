package workoutplanner.fxutil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <h1>ExerciseLoader</h1>
 * The ExerciseLoader class is responsible for loading exercises from a JSON
 * file into the ExerciseView class.
 * <p>
 * This class provides a method for loading a list of exercise names from a JSON
 * file and making them available for the ExerciseView. The class uses an
 * ObjectMapper for deserialization and provides error handling for cases where
 * the JSON file is not found or I/O errors occur.
 * </p>
 *
 * @since 1.4.0
 * @author Michael Brusegard + Arman Ilkka Nemati
 * @version 1.4.0
 */

public class ExerciseLoader {

  /**
   * Local objectmapper variable, used to map from the json file.
   */
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  /**
   * Loads exercises from a JSON file as a list of strings.
   * <p>
   * This static method loads a list of exercise names from a JSON file
   * stored as a resource within the application. It retrieves the resource's
   * URL, opens an input stream, and deserializes the JSON content into a
   * list of exercise names. If the JSON file is not found or any I/O error
   * occurs, an IOException is thrown.
   * </p>
   *
   * @return A list of exercise names loaded from the JSON file.
   * @throws IOException If the JSON file is not found or an I/O error occurs
   *                     during loading.
   */
  public static List<String> loadExercisesFromJson() throws IOException {
    // Method to load exercises from a JSON file as a URL
    // Get the URL of the resource
    String resourcePath = "/workoutplanner/fxutil/exercises.json";
    InputStream resourceUrl = ExerciseLoader.class
            .getResourceAsStream(resourcePath);
    if (resourceUrl == null) {
      throw new IOException("Resource not found: exercises.json");
    }
    // Open an InputStream from the URL
    try (InputStream inputStream = resourceUrl) {
      return OBJECT_MAPPER.readValue(inputStream, new TypeReference<>() {
      });
    }
  }
}
