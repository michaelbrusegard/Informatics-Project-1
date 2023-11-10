package workoutplanner.ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

import workoutplanner.core.Exercise;
import workoutplanner.core.Workout;

/**
 * Uses REST API to access and manipulate the user object.
 */

public class RemoteUserAccess implements UserAccess {

  private final URL baseUrl;

  public RemoteUserAccess(URL inputUrl) {
    baseUrl = inputUrl;
  }

  private Reader httpGetRequest(String path) throws IOException {
    URL url = new URL(baseUrl + path);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("Accept", "application/json");

    InputStream responseStream = connection.getInputStream();
    Reader reader = new InputStreamReader(responseStream, "UTF-8");
    return reader;
  }

  @Override
  public Workout getCurrentWorkout() {
    Reader reader = httpGetRequest("/current-workout");
    Workout workout = Deserializer.deserializeCurrentWorkout(reader);
    return workout;
  }

  @Override
  public List<Workout> getWorkouts() {
    Reader reader = httpGetRequest("/workouts");
    List<Workout> workouts = Deserializer.deserializeWorkouts(reader);
    return workouts;
  }

  private HttpURLConnection httpPutRequest(String path) throws IOException {
    URL url = new URL(baseUrl + path);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.connect();
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setRequestMethod("PUT");
    return connection;
  }

  @Override
  public void setCurrentWorkout(final int workoutIndex) throws IOException {
    HttpURLConnection connection = httpPutRequest("/current-workout/" + workoutIndex);
    if (connection.getResponseCode() == 404) {
      throw new IllegalArgumentException("Workout index not found.");
    }
  }

  @Override
  public void addExerciseToCurrentWorkout(final Exercise exercise) {
    HttpURLConnection connection = httpPutRequest("/current-workout/exercise");
    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
    String json = Serializer.serializeExercise(exercise);
    writer.write(json);
    writer.flush();
    writer.close();
  };

  @Override
  public void moveExerciseInCurrentWorkout(final int exerciseIndex, final boolean left) throws IOException {
    HttpURLConnection connection = httpPutRequest("/current-workout/exercise/" + exerciseIndex + "?left=" + left);
    if (connection.getResponseCode() == 404) {
      throw new IllegalArgumentException("Exercise index not found.");
    }
  };

  @Override
  public void saveCurrentWorkout(final String name) throws IOException {
    HttpURLConnection connection = httpPutRequest("/current-workout/save?name=" + URLEncoder.encode(name, "UTF-8"));
    if (connection.getResponseCode() == 404) {
      throw new IllegalArgumentException("No current workout.");
    }
  }

  private HttpURLConnection httpDeleteRequest(String path) throws IOException {
    URL url = new URL(baseUrl + path);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.connect();
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setRequestMethod("DELETE");
    return connection;
  }

  @Override
  public void removeWorkout(final int workoutIndex) throws IOException {
    HttpURLConnection connection = httpDeleteRequest("/workout/" + workoutIndex);
    if (connection.getResponseCode() == 404) {
      throw new IllegalArgumentException("Workout index not found.");
    }
  }

  @Override
  public void removeCurrentWorkout() throws IOException {
    HttpURLConnection connection = httpDeleteRequest("/current-workout");
    if (connection.getResponseCode() == 500) {
      throw new IllegalArgumentException("No current workout.");
    }
  }

  @Override
  public void removeExerciseFromCurrentWorkout(final int exerciseIndex) throws IOException {
    HttpURLConnection connection = httpDeleteRequest("/current-workout/exercise/" + exerciseIndex);
    if (connection.getResponseCode() == 404) {
      throw new IllegalArgumentException("Exercise index not found.");
    }
  };
}
