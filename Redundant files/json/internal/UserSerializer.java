package workoutplanner.json.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import workoutplanner.core.User;
import workoutplanner.core.Workout;

/**
 * <h1>UserSerializer</h1>
 * A JSON serializer for User objects.
 *
 * <p>
 *   The UserSerializer class provides functionality to serialize a User object
 *   into a JSON representation based on the specified format. The resulting
 *   JSON object includes an array of the user's workouts.
 * </p>
 *
 * @since 2.0.0
 * @author Arman Ilkka Nemati
 * @version 1.0.0
 */
public class UserSerializer extends JsonSerializer<User> {

  /**
   * Serializes a User object to JSON format.
   *
   * <p>
   *   This method converts a User object into a JSON representation based on
   *   the specified format. The resulting JSON object contains an array of the
   *   user's workouts.
   * </p>
   *
   * @param user The User object to be serialized to JSON.
   * @param jsonGenerator The JsonGenerator to write the JSON output.
   * @param serializerProvider The SerializerProvider for additional
   *                           serialization options.
   * @throws IOException If an I/O error occurs during JSON serialization.
   */
  /*
   * format: { "workouts": [ ... ] }
   */
  @Override
  public void serialize(final User user,
                        final JsonGenerator jsonGenerator,
                        final SerializerProvider serializerProvider)
          throws IOException {
    jsonGenerator.writeStartObject();
    // checks if the user has workouts
    if (!user.getWorkouts().isEmpty()) {
      // writes the workouts of the user to Json format as shown above
      jsonGenerator.writeArrayFieldStart("workouts");
      for (Workout workout : user.getWorkouts()) {
        jsonGenerator.writeObject(workout);
      }
      jsonGenerator.writeEndArray();
    }
    jsonGenerator.writeEndObject();
  }
}
