package workoutplanner.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import workoutplanner.core.User;
import workoutplanner.json.internal.WorkoutplannerModule;

/**
 *<h1>WorkoutplannerPersistence</h1>
 * Provides functionality for reading, writing, and managing User objects using
 * JSON serialization.
 *
 * <p>
 * The WorkoutplannerPersistence class uses Jackson's ObjectMapper for reading
 * and writing User objects to and from JSON format. It allows setting the save
 * file path for User objects and provides methods for loading and saving User
 * data from and to the specified file. Additionally, it includes methods for
 * creating and configuring the ObjectMapper instance.
 * </p>
 *
 * @since 2.0.0
 * @author Arman Ilkka Nemati
 * @version 1.1.0
 */
public class WorkoutplannerPersistence {
  /**
   * Local Objectmapper variable, used for reading and writing user objects.
   */
  private final ObjectMapper objectMapper;

  /**
   * Local Path variable, used for finding the file which contains the necessary
   * info.
   */
  private Path saveFilePath = null;

  /**
   * Constructs a new instance of the WorkoutplannerPersistence class.
   *
   * <p>
   * This constructor initializes the WorkoutplannerPersistence by creating
   * an ObjectMapper using the createObjectMapper method. The ObjectMapper is
   * responsible for the serialization and deserialization of JSON data.
   * </p>
   */
  public WorkoutplannerPersistence() {
    objectMapper = createObjectMapper();
  }

  /**
   * Static method to create and configure an ObjectMapper instance for
   * JSON serialization and deserialization.
   *
   * <p>
   * This method creates a new ObjectMapper and registers the
   * WorkoutplannerModule, which provides custom serializers and deserializers
   * for Exercise, Workout, and User classes. The configured ObjectMapper is
   * then returned for use in JSON processing.
   * </p>
   *
   * @return A configured ObjectMapper instance for JSON processing.
   */
  public static ObjectMapper createObjectMapper() {
    return  new ObjectMapper().registerModule(new WorkoutplannerModule());
  }

  /**
   * Reads a User object from the provided Reader using ObjectMapper.
   *
   * <p>
   * This method uses the configured ObjectMapper to deserialize JSON data from
   * the given Reader into a User object. It handles IOException and propagates
   * it to the caller.
   * </p>
   *
   * @param reader The Reader from which to read the User data in JSON format.
   * @return The User object read from the provided Reader.
   * @throws IOException If an I/O error occurs during the reading process.
   */
  public User readUser(final Reader reader) throws IOException {
    return objectMapper.readValue(reader, User.class);
  }

  /**
   * Writes the provided User object to the specified Writer using JSON
   * serialization.
   *
   * <p>
   * This method uses the configured ObjectMapper to serialize the given User
   * object into JSON format and writes it to the provided Writer. The User
   * class must be properly annotated for Jackson to map the object to JSON
   * data. If successful, the User object is serialized and written to the
   * Writer.
   * </p>
   *
   * @param user   The User object to be serialized and written.
   * @param writer The Writer to which the JSON data will be written.
   * @throws IOException If an I/O error occurs during the serialization
   *                     process.
   */
  public void writeUser(final User user, final Writer writer)
          throws IOException {
    objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, user);
  }

  /**
   * Sets the save file path for User objects.
   *
   * <p>
   * This method sets the save file path for User objects using the provided
   * file path. The file path is combined with the user's home directory to
   * create the full save file path.
   * </p>
   *
   * @param filePath The file path to set.
   */
  public void setSaveFilePath(final String filePath) {
    saveFilePath = Paths.get(System.getProperty("user.home"), filePath);
  }

  /**
   * Retrieves the current save file path for User objects.
   *
   * <p>
   * This method returns the current save file path for User objects.
   * </p>
   *
   * @return The current save file path for User objects.
   */
  public Path getSaveFilePath() {
    return saveFilePath;
  }

  /**
   * Loads a User object from the specified save file.
   *
   * <p>
   * This method reads and deserializes a User object from the save file
   * specified by the current save file path. It throws an IllegalStateException
   * if the save file path is not set. The method uses the configured
   * ObjectMapper to perform the deserialization, and it handles IOException
   * during the reading process.
   * </p>
   *
   * @return The User object loaded from the save file.
   * @throws IOException            If an I/O error occurs during the reading
   *                                process.
   * @throws IllegalStateException  If the save file path is not set.
   */
  public User loadUser() throws IOException, IllegalStateException {
    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set yet");
    }
    try (Reader reader = new FileReader(saveFilePath.toFile(),
            StandardCharsets.UTF_8)) {
      System.out.println("looking for file in ");
      return readUser(reader);
    }
  }

  /**
   * Saves the provided User object to the specified save file.
   *
   * <p>
   * This method serializes the given User object into JSON format and writes it
   * to the save file specified by the current save file path. It throws an
   * IllegalStateException if the save file path is not set. The method uses the
   * configured ObjectMapper for serialization and handles IOException during
   * the writing process.
   * </p>
   *
   * @param user   The User object to be serialized and saved.
   * @throws IOException            If an I/O error occurs during the writing
   *                                process.
   * @throws IllegalStateException  If the save file path is not set.
   */
  public void saveUser(final User user) throws IOException,
          IllegalStateException {
    System.out.println("saving to file");
    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set yet");
    }
    try (Writer writer = new FileWriter(saveFilePath.toFile(),
            StandardCharsets.UTF_8)) {
      System.out.println("writing to file " + getSaveFilePath());
      writeUser(user, writer);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }


}
