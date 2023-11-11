package workoutplanner.json.internal;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import workoutplanner.core.Exercise;

/**
 * <h1>ExerciseDeserializer</h1>
 * A JSON deserializer for Exercise objects.
 *
 * <p>
 *   This class is responsible for deserializing JSON data into Exercise
 *   objects. It reads JSON data using the provided JsonParser and delegates the
 *   deserialization process to the `deserializeNode` method.
 * </p>
 *
 * @since 2.0.0
 * @author Arman Ilkka Nemati
 * @version 1.0.0
 */
public class ExerciseDeserializer extends JsonDeserializer<Exercise> {

  /**
   * Deserializes a JSON object into an Exercise instance.
   *
   * <p>
   *   This method is responsible for deserializing a JSON object into an
   *   Exercise instance. It reads a JSON object using the provided JsonParser
   *   and delegates the deserialization process to the `deserializeNode`
   *   method. The resulting Exercise instance is returned.
   * </p>
   *
   * @param jsonParser The JsonParser for reading the JSON object to be
   *                   deserialized.
   * @param deserializationContext The DeserializationContext for additional
   *        deserialization options (not used in this method).
   * @return A deserialized Exercise instance.
   * @throws IOException If an I/O error occurs during JSON parsing or
   *        deserialization.
   * @throws JacksonException If there is an issue with Jackson's JSON
   *        processing.
   */
  @Override
  public Exercise deserialize(final JsonParser jsonParser,
                              final DeserializationContext
                                      deserializationContext)
          throws IOException, JacksonException {
    // creates JsonNode to delegate further
    JsonNode exerciseNode = jsonParser.getCodec().readTree(jsonParser);
    // delegates the deserialization to the deserializeNode method
    return deserializeNode(exerciseNode);
  }

  /**
   * Deserializes a JsonNode into an Exercise object.
   *
   * <p>
   *   This method deserializes a JsonNode into an Exercise object, based on the
   *   specified format. The input JsonNode is expected to represent an exercise
   *   and contain fields for the exercise's name, sets, minimum and maximum
   *   repetitions, and weight.
   * </p>
   *
   * @param exerciseNode The JsonNode representing the exercise to be
   *                     deserialized.
   * @return A deserialized Exercise object, or null if the deserialization
   *        fails.
   */
  protected Exercise deserializeNode(final JsonNode exerciseNode) {
    if (exerciseNode instanceof ObjectNode objectNode) {
      // finding the different exercise variables
      JsonNode nameNode = objectNode.get("name");
      JsonNode setsNode = objectNode.get("sets");
      JsonNode repMinNode = objectNode.get("minimum reps");
      JsonNode repMaxNode = objectNode.get("maximum reps");
      JsonNode weightNode = objectNode.get("weight");
      // checking for valid node types
      boolean nameNodeisValid = nameNode instanceof TextNode;
      boolean setsNodeisValid = setsNode instanceof IntNode;
      boolean repMinNodeisValid = repMinNode instanceof IntNode;
      boolean repMaxNodeisValid = repMaxNode instanceof IntNode;
      boolean weightNodeisValid = weightNode instanceof IntNode;
      // returning an exercise if all nodes are valid
      return nameNodeisValid
              && setsNodeisValid
              && repMinNodeisValid
              && repMaxNodeisValid
              && weightNodeisValid
              ? new Exercise(nameNode.asText(),
              setsNode.asInt(),
              repMinNode.asInt(),
              repMaxNode.asInt(),
              weightNode.asInt()) : null;
    }
    return null;
  }
}
