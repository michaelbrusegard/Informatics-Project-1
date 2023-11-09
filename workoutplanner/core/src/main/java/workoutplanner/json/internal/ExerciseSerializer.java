package workoutplanner.json.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import workoutplanner.core.Exercise;

/**
 * <h1>ExerciseSerializer</h1>
 * Serializes an Exercise object to JSON format.
 *
 * <p>
 *   This class is responsible for converting an Exercise object into a JSON
 *   representation based on the specified format. The resulting JSON object
 *   contains the exercise's name, the number of sets, the minimum and maximum
 *   repetitions, and the weight used.
 * </p>
 *
 * @since 2.0.0
 * @author Arman Ilkka Nemati
 * @version 1.0.0
 */
public class ExerciseSerializer extends JsonSerializer<Exercise> {

  /**
   * Serializes an Exercise object to JSON format.
   *
   * <p>
   *   This method converts an Exercise object into a JSON representation based
   *   on the specified format. The resulting JSON object contains the
   *   exercise's name, the number of sets, the minimum and maximum repetitions,
   *   and the weight used.
   * </p>
   *
   * @param exercise The Exercise object to be serialized to JSON.
   * @param jsonGenerator The JsonGenerator to write the JSON output.
   * @param serializerProvider The SerializerProvider for additional
   *                           serialization options.
   * @throws IOException If an I/O error occurs during JSON serialization.
   */
  /*
   * format: { "name": "...", "sets": ..., "minimum reps": ...,
   * "maximum reps": ..., "weight": ...}
   */
  @Override
  public void serialize(final Exercise exercise,
                        final JsonGenerator jsonGenerator,
                        final SerializerProvider serializerProvider)
          throws IOException {
    jsonGenerator.writeStartObject();
    // check if the exercise's name was initialized
    if (exercise.name() != null) {
      // writes the name of the exercise to Json format as shown above
      jsonGenerator.writeStringField("name", exercise.name());
    }
    // check if the exercise's amount of sets was initialized
    if (exercise.sets() != 0) {
      // writes the amount of sets of the exercise to Json format as shown above
      jsonGenerator.writeNumberField("sets", exercise.sets());
    }
    // check if the exercise's minimum amount of reps was initialized
    if (exercise.repMin() != 0) {
      // writes the minimum amount of reps of the exercise to Json format as
      // shown above
      jsonGenerator.writeNumberField("repMin", exercise.repMin());
    }
    // check if the exercise's maximum amount of reps was initialized
    if (exercise.repMax() != 0) {
      // writes the maximum amount of reps of the exercise to Json format as
      // shown above
      jsonGenerator.writeNumberField("repMax", exercise.repMax());
    }
    // check if the exercise's weight was initialized
    if (exercise.weight() != 0) {
      // writes the weight of the exercise to Json format as shown above
      jsonGenerator.writeNumberField("weight", exercise.weight());
    }
    jsonGenerator.writeEndObject();
  }
}
