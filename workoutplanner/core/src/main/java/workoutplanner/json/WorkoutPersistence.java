package workoutplanner.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import workoutplanner.core.Workout;

/**
 * <h1>WorkoutPersistence</h1>
 * The {@code WorkoutPersistence} class provides functionality for loading and
 * saving workout data to and from a JSON file.
 *
 * <p>
 * This class uses Jackson's {@code ObjectMapper} to serialize and deserialize
 * workout data to and from JSON format. It includes methods for loading a list
 * of workouts from a JSON file and saving a list of workouts to a JSON file.
 * </p>
 *
 * @since 2.0.0
 * @author Michael Brusegard + Arman Ilkka Nemati
 * @version 2.0.0
 */
public final class WorkoutPersistence {

  /**
   * Local logger variable, used for logging exceptions.
   */
  private static final Logger LOGGER =
          Logger.getLogger(WorkoutPersistence.class.getName());

  /**
   * Local objectmapper variable, used to map from the json file.
   */
  private static final ObjectMapper OBJECT_MAPPER =
          new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

  /**
   * Local string variable, used to direct to a specific file.
   */
  private static final String RESOURCE_PATH =
          "/workoutplanner/json/Workouts.json";

  private WorkoutPersistence() {
    // Private constructor to prevent instantiation
    throw new UnsupportedOperationException(
            "Utility class should not be instantiated.");
  }

  /**
   * Loads a list of workouts from a JSON file.
   *
   * <p>
   * This method reads workout data from a JSON file located at the specified
   * resource path. It uses Jackson's {@code ObjectMapper} to deserialize the
   * JSON data into a List of Workout objects. If the file is not found or an
   * I/O exception occurs during the process, an empty list is returned. If the
   * JSON data does not match the expected format, a MismatchedInputException is
   * caught, and an empty list is returned.
   * </p>
   *
   * @return A List of Workout objects loaded from the JSON file, or an empty
   *         list if there is an issue during the loading process.
   */
  public static List<Workout> loadWorkoutsFromJson() {
    try (FileInputStream fileInputStream = new FileInputStream(
            "../core/src/main/resources" + RESOURCE_PATH)) {
      return OBJECT_MAPPER.readValue(fileInputStream, new TypeReference<>() {
      });
    } catch (MismatchedInputException e) {
      return new ArrayList<>();
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE,
              "An I/O exception occurred during loading workouts", e);
      return new ArrayList<>();
    }
  }

  /**
   * Saves a list of workouts to a JSON file.
   *
   * <p>
   * This method writes the provided list of Workout objects to a JSON file
   * located at the specified resource path. It uses Jackson's
   * {@code ObjectMapper} to serialize the list into JSON format and writes it
   * to the file using a {@code FileOutputStream}. If an I/O exception occurs
   * during the process, it is caught and logged using the application's LOGGER.
   * </p>
   *
   * @param workouts The List of Workout objects to be saved to the JSON file.
   */
  public static void saveWorkoutsToJson(final List<Workout> workouts) {
    try {
      try (FileOutputStream fileOutputStream = new FileOutputStream(
              "../core/src/main/resources" + RESOURCE_PATH)) {
        OBJECT_MAPPER.writeValue(fileOutputStream, workouts);
      }
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE,
              "An I/O exception occurred during saving workouts", e);
    }
  }
}
