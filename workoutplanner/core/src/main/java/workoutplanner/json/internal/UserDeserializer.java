package workoutplanner.json.internal;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import workoutplanner.core.User;
import workoutplanner.core.Workout;

/**
 * <h1>UserDeserializer</h1>
 * Deserializes JSON data into a User instance.
 *
 * <p>
 * This class is responsible for deserializing JSON data into a User instance.
 * It reads a JSON object using the provided JsonParser and delegates the
 * deserialization process to the `deserializeNode` method. The resulting User
 * instance is returned.
 * </p>
 *
 * @since 2.0.0
 * @author Arman Ilkka Nemati
 * @version 1.0.0
 */
public class UserDeserializer extends JsonDeserializer<User> {
  /**
   * Local WorkoutDeserializer variable, used to deserialize workoutNode.
   */
  private final WorkoutDeserializer workoutDeserializer = new WorkoutDeserializer();

  /**
   * Deserializes a JSON object into a User instance.
   *
   * <p>
   * This method is responsible for deserializing a JSON object into a User
   * instance. It reads a JSON object using the provided JsonParser and
   * delegates the deserialization process to the `deserializeNode` method.
   * The resulting User instance is returned.
   * </p>
   *
   * @param jsonParser             The JsonParser for reading the JSON object to
   *                               be
   *                               deserialized.
   * @param deserializationContext The DeserializationContext for additional
   *                               deserialization options (not used in this
   *                               method).
   * @return A deserialized User instance.
   * @throws IOException      If an I/O error occurs during JSON parsing or
   *                          deserialization.
   * @throws JacksonException If there is an issue with Jackson's JSON
   *                          processing.
   */
  @Override
  public User deserialize(final JsonParser jsonParser,
      final DeserializationContext deserializationContext)
      throws IOException, JacksonException {
    // creates JsonNode to delegate further
    JsonNode userNode = jsonParser.getCodec().readTree(jsonParser);
    // delegates the deserialization to the deserializeNode method
    return deserializeNode(userNode);
  }

  /**
   * Deserializes a JsonNode into a User object.
   *
   * <p>
   * This method deserializes a JsonNode into a User object based on the
   * specified format. The input JsonNode is expected to represent a User and
   * contain a list of workouts. The method checks for valid node types,
   * deserializes the workouts, and appends them to the User object.
   * </p>
   *
   * @param userNode The JsonNode representing the User to be deserialized.
   * @return A deserialized User object, or null if the deserialization fails.
   */
  protected User deserializeNode(final JsonNode userNode) {
    // checks that the JsonNode is the correct type of node
    if (userNode instanceof ObjectNode objectNode) {
      // creates new User instance
      User user = new User();
      // finds the list of workouts for the user
      JsonNode workoutsNode = objectNode.get("workouts");
      // checks that the node is of the correct type
      if (workoutsNode instanceof ArrayNode) {
        for (JsonNode workoutNode : workoutsNode) {
          // delegates task of deserializing the workoutNode to
          // workoutDeserializer
          Workout workout = workoutDeserializer.deserializeNode(workoutNode);
          // checks that there is a workout
          if (workout != null) {
            // appends the workout to the user
            // user.addWorkoutFromFile(workout);
          }
        }
      }
      // returns the user that was constructed
      return user;
    }
    // returns null if the input-node is not of the correct type
    return null;
  }
}
