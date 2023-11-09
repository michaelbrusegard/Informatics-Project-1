package workoutplanner.ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

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
    Reader reader = httpGetRequest("/currentWorkout");
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
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setRequestMethod("PUT");
    return connection;
  }

  @Override
  public void setCurrentWorkout(final int workoutIndex) {
    HttpURLConnection connection = httpPutRequest("/currentWorkout/" + workoutIndex);
    connection.connect();
    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
    String json = Serializer.serializeWorkoutIndex(workoutIndex);
    writer.write(json);
    writer.flush();
    if (connection.getResponseCode() == 404) {
      throw new IllegalArgumentException("Workout index not found.");
    }
    writer.close();
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

  public void removeWorkout(final int workoutIndex) throws IOException {
    HttpURLConnection connection = httpDeleteRequest("/removeWorkout/" + workoutIndex);
    connection.connect();
    if (connection.getResponseCode() == 404) {
      throw new IllegalArgumentException("Workout index not found.");
    }
  }

  public void removeCurrentWorkout() throws IOException {
    HttpURLConnection connection = httpDeleteRequest("/removeCurrentWorkout");
    connection.connect();
    if (connection.getResponseCode() == 500) {
      throw new IllegalArgumentException("No current workout.");
    }
  }
}
