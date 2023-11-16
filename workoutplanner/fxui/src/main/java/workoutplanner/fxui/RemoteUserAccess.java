package workoutplanner.fxui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import workoutplanner.core.UserAccess;

/**
 * <h1>RemoteUserAccess</h1>
 * The RemoteUserAccess class provides remote access to user-related data and
 * operations through HTTP requests to a remote server.
 *
 * <p>
 * This class implements the UserAccess interface and includes methods for
 * retrieving and manipulating user and workout data on the remote server.
 * </p>
 *
 * @since 2.0.0
 * @author Michael Brusegard
 * @version 1.0.0
 */
public class RemoteUserAccess implements UserAccess {

  /**
   * Local URI variable, used for pathing.
   */
  private final URI baseUri;

  /**
   * Local ObjectMapper variable, used for object serializing and deserializing.
   */
  private final ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Local int variable, used to determine response code.
   */
  private static final int OK_RESPONSE_CODE = 200;

  /**
   * Local Logger variable, used to log exceptions.
   */
  private static final Logger LOGGER = Logger.getLogger(RemoteUserAccess.class.getName());

  /**
   * Constructs a new instance of the RemoteUserAccess class.
   *
   * <p>
   * This constructor initializes a RemoteUserAccess object with the specified
   * base URI for accessing the remote server. The base URI is used for pathing
   * when making requests to the remote server.
   * </p>
   *
   * @param inputUri The URI representing the base path for accessing the remote
   *                 server.
   */
  public RemoteUserAccess(final URI inputUri) {
    baseUri = inputUri;
  }

  private Reader httpGetRequest(final String path) throws IOException {
    URI uri = baseUri.resolve("/user" + path);
    HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
    connection.setRequestProperty("Accept", "application/json");

    InputStream responseStream = connection.getInputStream();
    return new InputStreamReader(responseStream, StandardCharsets.UTF_8);
  }

  private HttpURLConnection httpPutRequest(final String path)
      throws IOException {
    URI uri = baseUri.resolve("/user" + path);
    HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setRequestMethod("PUT");
    return connection;
  }

  private HttpURLConnection httpDeleteRequest(final String path)
      throws IOException {
    URI uri = baseUri.resolve("/user" + path);
    HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setRequestMethod("DELETE");
    return connection;
  }

  private void handleError(final HttpURLConnection connection)
      throws IOException {
    int responseCode = connection.getResponseCode();

    if (responseCode == OK_RESPONSE_CODE) {
      return;
    }
    InputStream errorStream = connection.getErrorStream();
    if (errorStream != null) {
      try (BufferedReader reader = new BufferedReader(
          new InputStreamReader(errorStream, StandardCharsets.UTF_8))) {
        StringBuilder responseBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
          responseBody.append(line);
        }

        throw new RuntimeException(
            responseCode + " " + connection.getResponseMessage() + ": "
                + responseBody);
      }
    } else {
      throw new RuntimeException(responseCode + " "
          + connection.getResponseMessage());
    }
  }

  /**
   * Retrieves the list of exercises from the remote server.
   *
   * <p>
   * This method sends a HTTP GET request to the "/exercise-list" endpoint
   * of the remote server to fetch the list of available exercises. It uses the
   * ObjectMapper to deserialize the response content into a List of Strings.
   * </p>
   *
   * @return A List of Strings representing the available exercises.
   * @throws RuntimeException If an error occurs during the retrieval process,
   *                          including IOException or deserialization issues.
   */
  @Override
  public List<String> getExerciseList() {
    try {
      Reader reader = httpGetRequest("/exercise-list");
      return objectMapper.readValue(reader, new TypeReference<>() {
      });
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error getting exercise list.", e);
      throw new RuntimeException("Error getting exercise list.", e);
    }
  }

  /**
   * Retrieves the saved state of the current workout from the remote server.
   *
   * <p>
   * This method sends a HTTP GET request to the "/current-workout/saved"
   * endpoint of the remote server to fetch the saved state of the current
   * workout. It uses the ObjectMapper to deserialize the response content into
   * a Boolean.
   * </p>
   *
   * @return True if the current workout is saved, false otherwise.
   * @throws RuntimeException If an error occurs during the retrieval process,
   *                          including IOException or deserialization issues.
   */
  @Override
  public boolean getCurrentWorkoutSaved() {
    Reader reader;
    try {
      reader = httpGetRequest("/current-workout/saved");
      return objectMapper.readValue(reader, Boolean.class);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error getting current workout saved state.", e);
      throw new RuntimeException(
          "Error getting current workout saved state.", e);
    }
  }

  /**
   * Retrieves the exercise count of the current workout from the remote server.
   *
   * <p>
   * This method sends a HTTP GET request to the
   * "/current-workout/exercise-count" endpoint of the remote server to fetch
   * the count of exercises in the current workout. It uses the ObjectMapper to
   * deserialize the response content into an integer.
   * </p>
   *
   * @return The number of exercises in the current workout.
   * @throws RuntimeException If an error occurs during the retrieval process,
   *                          including IOException or deserialization issues.
   */
  @Override
  public int getCurrentWorkoutExerciseCount() {
    Reader reader;
    try {
      reader = httpGetRequest("/current-workout/exercise-count");
      return objectMapper.readValue(reader, int.class);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE,
          "Error getting current workout exercise count.", e);
      throw new RuntimeException(
          "Error getting current workout exercise count.", e);
    }
  }

  /**
   * Retrieves the name of the current workout from the remote server.
   *
   * <p>
   * This method sends a HTTP GET request to the "/current-workout/name"
   * endpoint of the remote server to fetch the name of the current workout.
   * It uses the ObjectMapper to deserialize the response content into a String.
   * </p>
   *
   * @return The name of the current workout.
   * @throws RuntimeException If an error occurs during the retrieval process,
   *                          including IOException or deserialization issues.
   */
  @Override
  public String getCurrentWorkoutName() {
    Reader reader;
    try {
      reader = httpGetRequest("/current-workout/name");
      return objectMapper.readValue(reader, String.class);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error getting current workout name.", e);
      throw new RuntimeException("Error getting current workout name.", e);
    }
  }

  /**
   * Retrieves a specific attribute of an exercise in the current workout from
   * the remote server.
   *
   * <p>
   * This method sends a HTTP GET request to the
   * "/current-workout/exercise/{exerciseIndex}" endpoint of the remote server
   * with the specified attribute to fetch the corresponding attribute value of
   * the exercise in the current workout. It uses the ObjectMapper to
   * deserialize the response content into a String.
   * </p>
   *
   * @param exerciseIndex The index of the exercise in the current workout.
   * @param attribute     The attribute to retrieve (e.g., "name", "sets",
   *                      "repMin").
   * @return The value of the specified attribute for the given exercise.
   * @throws RuntimeException If an error occurs during the retrieval process,
   *                          including IOException or deserialization issues.
   */
  @Override
  public String getCurrentWorkoutExerciseAttribute(final int exerciseIndex,
      final String attribute) {
    Reader reader;
    try {
      reader = httpGetRequest("/current-workout/exercise/"
          + exerciseIndex + "/?attribute=" + attribute);
      return objectMapper.readValue(reader, String.class);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE,
          "Error getting current workout exercise " + attribute + ".", e);
      throw new RuntimeException(
          "Error getting current workout exercise " + attribute + ".", e);
    }
  }

  /**
   * Retrieves a list of workout names from the remote server.
   *
   * <p>
   * This method sends a HTTP GET request to the "/workout/names" endpoint
   * of the remote server to fetch the names of all available workouts.
   * It uses the ObjectMapper to deserialize the response content into a List
   * of Strings.
   * </p>
   *
   * @return A List containing the names of all available workouts.
   * @throws RuntimeException If an error occurs during the retrieval process,
   *                          including IOException or deserialization issues.
   */
  @Override
  public List<String> getWorkoutNames() {
    try {
      Reader reader = httpGetRequest("/workout/names");
      return objectMapper.readValue(reader, new TypeReference<>() {
      });
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error getting workout names.", e);
      throw new RuntimeException("Error getting workout names.", e);
    }
  }

  /**
   * Retrieves a list of workout dates from the remote server.
   *
   * <p>
   * This method sends a HTTP GET request to the "/workout/dates" endpoint
   * of the remote server to fetch the dates of all available workouts.
   * It uses the ObjectMapper to deserialize the response content into a List
   * of Strings.
   * </p>
   *
   * @return A List containing the dates of all available workouts.
   * @throws RuntimeException If an error occurs during the retrieval process,
   *                          including IOException or deserialization issues.
   */
  @Override
  public List<String> getWorkoutDates() {
    try {
      Reader reader = httpGetRequest("/workout/dates");
      return objectMapper.readValue(reader, new TypeReference<>() {
      });
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error getting workout dates.", e);
      throw new RuntimeException("Error getting workouts dates.", e);
    }
  }

  /**
   * Sets the current workout on the remote server.
   *
   * <p>
   * This method sends a HTTP PUT request to the
   * "/current-workout/{workoutIndex}" endpoint of the remote server to set the
   * current workout based on the provided index. It checks for errors in the
   * response using the handleError method.
   * </p>
   *
   * @param workoutIndex The index of the workout to set as the current workout.
   * @throws RuntimeException If an error occurs during the request, or if the
   *                          server returns a non-OK response.
   */
  @Override
  public void setCurrentWorkout(final int workoutIndex) {
    try {
      HttpURLConnection connection = httpPutRequest("/current-workout/"
          + workoutIndex);
      handleError(connection);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error setting current workout.", e);
      throw new RuntimeException("Error setting current workout.", e);
    }
  }

  /**
   * Adds a new exercise to the current workout on the remote server.
   *
   * <p>
   * This method sends a HTTP PUT request to the "/current-workout/exercise"
   * endpoint of the remote server with the specified parameters to add a new
   * exercise to the current workout. It uses the handleError method to check
   * for errors in the response.
   * </p>
   *
   * @param inputName The name of the new exercise.
   * @param sets      The number of sets for the new exercise.
   * @param repMin    The minimum number of repetitions for the new exercise.
   * @param repMax    The maximum number of repetitions for the new exercise.
   * @param weight    The weight for the new exercise.
   * @throws RuntimeException If an error occurs during the request, or if the
   *                          server returns a non-OK response.
   * @since 1.0.0
   */
  @Override
  public void addExerciseToCurrentWorkout(final String inputName,
      final int sets,
      final int repMin,
      final int repMax,
      final int weight) {
    try {
      HttpURLConnection connection = httpPutRequest(
          "/current-workout/exercise" + "?name="
              + URLEncoder.encode(inputName, StandardCharsets.UTF_8)
              + "&sets=" + sets + "&repMin=" + repMin + "&repMax=" + repMax
              + "&weight=" + weight);
      handleError(connection);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error adding exercise to current workout.", e);
      throw new RuntimeException("Error adding exercise to current workout.",
          e);
    }
  }

  /**
   * Moves an exercise within the current workout on the remote server.
   *
   * <p>
   * This method sends a HTTP PUT request to the
   * "/current-workout/exercise/{exerciseIndex}?left={left}" endpoint of the
   * remote server to move the specified exercise either to the left or right
   * within the current workout. It uses the handleError method to check for
   * errors in the response.
   * </p>
   *
   * @param exerciseIndex The index of the exercise to be moved.
   * @param left          A boolean indicating the direction of movement (true
   *                      to go left, false to go right).
   * @throws RuntimeException If an error occurs during the request, or if the
   *                          server returns a non-OK response.
   */
  @Override
  public void moveExerciseInCurrentWorkout(final int exerciseIndex,
      final boolean left) {
    try {
      HttpURLConnection connection = httpPutRequest("/current-workout/exercise/"
          + exerciseIndex + "?left=" + left);
      handleError(connection);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error moving exercise in current workout.", e);
      throw new RuntimeException("Error moving exercise in current workout.",
          e);
    }
  }

  /**
   * Saves the current workout on the remote server.
   *
   * <p>
   * This method sends a HTTP PUT request to the
   * "/current-workout/save?name={name}&date={date}" endpoint of the remote
   * server to save the current workout with the specified name and date. It
   * uses the handleError method to check for errors in the response.
   * </p>
   *
   * @param name The name to be assigned to the current workout.
   * @param date The date to be assigned to the current workout.
   * @throws RuntimeException If an error occurs during the request, or if the
   *                          server returns a non-OK response.
   */
  @Override
  public void saveCurrentWorkout(final String name, final String date) {
    try {
      HttpURLConnection connection = httpPutRequest(
          "/current-workout/save?name="
              + URLEncoder.encode(name, StandardCharsets.UTF_8) + "&date="
              + URLEncoder.encode(date, StandardCharsets.UTF_8));
      handleError(connection);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error saving current workout.", e);
      throw new RuntimeException("Error saving current workout.", e);
    }
  }

  /**
   * Removes a workout from the remote server.
   *
   * <p>
   * This method sends a HTTP DELETE request to the "/workout/{workoutIndex}"
   * endpoint of the remote server to remove the workout at the specified index.
   * It uses the handleError method to check for errors in the response.
   * </p>
   *
   * @param workoutIndex The index of the workout to be removed.
   * @throws RuntimeException If an error occurs during the request, or if the
   *                          server returns a non-OK response.
   */
  @Override
  public void removeWorkout(final int workoutIndex) {
    try {
      HttpURLConnection connection = httpDeleteRequest("/workout/"
          + workoutIndex);
      handleError(connection);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error removing workout.", e);
      throw new RuntimeException("Error removing workout.", e);
    }
  }

  /**
   * Removes the current workout from the remote server.
   *
   * <p>
   * This method sends a HTTP DELETE request to the "/current-workout" endpoint
   * of the remote server to remove the current workout. It uses the handleError
   * method to check for errors in the response.
   * </p>
   *
   * @throws RuntimeException If an error occurs during the request, or if the
   *                          server returns a non-OK response.
   */
  @Override
  public void removeCurrentWorkout() {
    try {
      HttpURLConnection connection = httpDeleteRequest("/current-workout");
      handleError(connection);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error removing current workout.", e);
      throw new RuntimeException("Error removing current workout.", e);
    }
  }

  /**
   * Removes an exercise from the current workout on the remote server.
   *
   * <p>
   * This method sends a HTTP DELETE request to the
   * "/current-workout/exercise/{exerciseIndex}" endpoint of the remote server
   * to remove the specified exercise from the current workout. It uses the
   * handleError method to check for errors in the response.
   * </p>
   *
   * @param exerciseIndex The index of the exercise to be removed.
   * @throws RuntimeException If an error occurs during the request, or if the
   *                          server returns a non-OK response.
   */
  @Override
  public void removeExerciseFromCurrentWorkout(final int exerciseIndex) {
    try {
      HttpURLConnection connection = httpDeleteRequest(
          "/current-workout/exercise/" + exerciseIndex);
      handleError(connection);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error removing current workout.", e);
      throw new RuntimeException(
          "Error removing exercise from current workout.", e);
    }
  }

  /**
   * Deletes unsaved workouts from the remote server.
   *
   * <p>
   * This method sends a HTTP DELETE request to the "/workouts/delete-unsaved"
   * endpoint of the remote server to delete any unsaved workouts. It uses the
   * handleError method to check for errors in the response.
   * </p>
   *
   * @throws RuntimeException If an error occurs during the request, or if the
   *                          server returns a non-OK response.
   */
  @Override
  public void deleteUnsavedWorkouts() {
    try {
      HttpURLConnection connection = httpDeleteRequest("/workouts/delete-unsaved");
      handleError(connection);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error deleting unsaved workouts.", e);
      throw new RuntimeException("Error deleting unsaved workouts.", e);
    }
  }
}
