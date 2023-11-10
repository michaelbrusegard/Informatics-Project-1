package workoutplanner.ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import workoutplanner.core.Exercise;
import workoutplanner.core.Workout;

/**
 * Uses REST API to access and manipulate the user object.
 */

public class RemoteUserAccess implements UserAccess {

  private final URI baseUrl;

  public RemoteUserAccess(URI inputUrl) {
    baseUrl = inputUrl;
  }

  private Reader httpGetRequest(String path) throws IOException {
    URI url = baseUrl.resolve(path);
    HttpURLConnection connection = (HttpURLConnection) url.toURL().openConnection();
    connection.setRequestProperty("Accept", "application/json");

    InputStream responseStream = connection.getInputStream();
    Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
    return reader;
  }

  private HttpURLConnection httpPutRequest(String path) throws IOException {
    URI url = baseUrl.resolve(path);
    HttpURLConnection connection = (HttpURLConnection) url.toURL().openConnection();
    connection.connect();
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setRequestMethod("PUT");
    return connection;
  }

  private HttpURLConnection httpDeleteRequest(String path) throws IOException {
    URI url = baseUrl.resolve(path);
    HttpURLConnection connection = (HttpURLConnection) url.toURL().openConnection();
    connection.connect();
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setRequestMethod("DELETE");
    return connection;
  }

  private void checkResponseCode(HttpURLConnection connection) throws IOException {
    if (connection.getResponseCode() != 200) {
      throw new IllegalArgumentException(connection.getResponseCode() + "");
    }
  }

  @Override
  public Workout getCurrentWorkout() {
    Reader reader = httpGetRequest("/current-workout");
    Workout workout = Deserializer.deserializeWorkout(reader);
    return workout;
  }

  @Override
  public List<Workout> getWorkouts() {
    Reader reader = httpGetRequest("/workouts");
    List<Workout> workouts = Deserializer.deserializeWorkouts(reader);
    return workouts;
  }

  @Override
  public void setCurrentWorkout(final int workoutIndex) throws IOException {
    HttpURLConnection connection = httpPutRequest("/current-workout/" + workoutIndex);
    checkResponseCode(connection);
  }

  @Override
  public void addExerciseToCurrentWorkout(final String inputName,
      final int sets,
      final int repMin,
      final int repMax,
      final int weight) throws IOException {
    HttpURLConnection connection = httpPutRequest("/current-workout/exercise");
    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
    String json = Serializer.serializeExercise(new Exercise(inputName, sets, repMin, repMax, weight));
    writer.write(json);
    writer.flush();
    writer.close();
    checkResponseCode(connection);
  };

  @Override
  public void moveExerciseInCurrentWorkout(final int exerciseIndex, final boolean left) throws IOException {
    HttpURLConnection connection = httpPutRequest("/current-workout/exercise/" + exerciseIndex + "?left=" + left);
    checkResponseCode(connection);
  };

  @Override
  public void saveCurrentWorkout(final String name) throws IOException {
    HttpURLConnection connection = httpPutRequest("/current-workout/save?name=" + URLEncoder.encode(name, "UTF-8"));
    checkResponseCode(connection);
  }

  @Override
  public void removeWorkout(final int workoutIndex) throws IOException {
    HttpURLConnection connection = httpDeleteRequest("/workout/" + workoutIndex);
    checkResponseCode(connection);
  }

  @Override
  public void removeCurrentWorkout() throws IOException {
    HttpURLConnection connection = httpDeleteRequest("/current-workout");
    checkResponseCode(connection);
  }

  @Override
  public void removeExerciseFromCurrentWorkout(final int exerciseIndex) throws IOException {
    HttpURLConnection connection = httpDeleteRequest("/current-workout/exercise/" + exerciseIndex);
    checkResponseCode(connection);
  };
}
