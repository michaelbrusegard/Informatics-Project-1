package workoutplanner.json.internal;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import workoutplanner.core.Exercise;
import workoutplanner.core.Workout;

/**
 * <h1>WorkoutDeserializer</h1>
 * Deserializes JSON representations of Workout objects.
 *
 * <p>
 *   The WorkoutDeserializer class is responsible for deserializing JSON objects
 *   into Workout instances. It processes JSON objects with fields for the
 *   workout's name, date, and a list of exercises, converting them into Workout
 *   instances. The deserialization is performed by reading a JSON object using
 *   a provided JsonParser and delegating the deserialization process to the
 *   `deserializeNode` method. This class is used in conjunction with the
 *   Jackson JSON processing library to facilitate the conversion of JSON data
 *   into Workout objects.
 * </p>
 *
 * @since 2.0.0
 * @author Arman Ilkka Nemati
 * @version 1.0.0
 */
public class WorkoutDeserializer extends JsonDeserializer<Workout> {
  /**
   * Local ExerciseDeserializer variable, used to deserialize exerciseNode.
   */
  private final ExerciseDeserializer exerciseDeserializer
          = new ExerciseDeserializer();

  /**
   * Deserializes a JSON object into a Workout instance.
   *
   * <p>
   *   This method is responsible for deserializing a JSON object into a Workout
   *   instance. It reads a JSON object using the provided JsonParser and
   *   delegates the deserialization process to the `deserializeNode` method.
   *   The resulting Workout instance is returned.
   * </p>
   *
   * @param jsonParser The JsonParser for reading the JSON object to be
   *                   deserialized.
   * @param deserializationContext The DeserializationContext for additional
   *                               deserialization options (not used in this
   *                               method).
   * @return A deserialized Workout instance.
   * @throws IOException If an I/O error occurs during JSON parsing or
   *                    deserialization.
   * @throws JacksonException If there is an issue with Jackson's JSON
   *                          processing.
   */
  @Override
  public Workout deserialize(final JsonParser jsonParser,
                             final DeserializationContext
                                     deserializationContext)
          throws IOException, JacksonException {
    // creates JsonNode to delegate further
    JsonNode workoutNode = jsonParser.getCodec().readTree(jsonParser);
    // delegates the deserialization to the deserializeNode method
    return deserializeNode(workoutNode);
  }

  /**
   * Deserializes a JsonNode into a Workout object.
   *
   * <p>
   *   This method deserializes a JsonNode into a Workout object, based on the
   *   specified format. The input JsonNode is expected to represent a workout
   *   and contain fields for the workout's name, date, and a list of exercises.
   * </p>
   *
   * @param workoutNode The JsonNode representing the workout to be
   *                    deserialized.
   * @return A deserialized Workout object, or null if the deserialization
   *        fails.
   */
  protected Workout deserializeNode(final JsonNode workoutNode) {
    // checks that the JsonNode is the correct type of node
    if (workoutNode instanceof ObjectNode objectNode) {
      // creates new Workout instance
      Workout workout = new Workout();
      // finds the name of the workout
      JsonNode nameNode = objectNode.get("name");
      // checks that the node is of the correct type
      if (nameNode instanceof TextNode) {
        // configures the name of the workout
        workout.setName(nameNode.asText());
      }
      // finds the date of the workout
      JsonNode dateNode = objectNode.get("date");
      // checks that the node is of the correct type
      if (dateNode instanceof TextNode) {
        // configures the date of the workout
        workout.setDate(LocalDate.from(LocalDateTime.parse(dateNode.asText())));
      }
      // finds the list of exercises for the workout
      JsonNode exercisesNode = objectNode.get("exercises");
      // checks that the node is of the correct type
      if (exercisesNode instanceof ArrayNode) {
        for (JsonNode exerciseNode : exercisesNode) {
          // delegates task of deserializing the exerciseNode to
          // exerciseDeserializer
          Exercise exercise = exerciseDeserializer
                  .deserializeNode(exerciseNode);
          // checks that there is an exercise
          if (exercise != null) {
            workout.addExercise(exercise);
          }
        }
      }
      // checks that the required fields of the workout are there
      if (workout.getName() == null
              || workout.getDateAsString() == null
              || workout.getExerciseCount() == 0) {
        // returns null if there is a required field missing
        return null;
      }
      // returns the workout that was constructed
      return workout;
    }
    // returns null if the input-node is not of the correct type
    return null;
  }
}
