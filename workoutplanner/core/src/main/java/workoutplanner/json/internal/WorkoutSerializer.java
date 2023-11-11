package workoutplanner.json.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import workoutplanner.core.Exercise;
import workoutplanner.core.Workout;

/**
 * <h1>WorkoutSerializer</h1>
 * Serializes a Workout object to JSON format.
 *
 * <p>
 * This class extends the JsonSerializer class to provide functionality for
 * serializing Workout objects into a JSON representation. The resulting JSON
 * object contains the workout's name, date, and a list of exercises.
 * </p>
 *
 * @since 2.0.0
 * @author Arman Ilkka Nemati
 * @version 1.0.0
 */
public class WorkoutSerializer extends JsonSerializer<Workout> {

  /**
   * Serializes a Workout object to JSON format.
   *
   * <p>
   * This method converts a Workout object into a JSON representation based on
   * the specified format. The resulting JSON object contains the workout's
   * name, date, and a list of exercises.
   * </p>
   *
   * @param workout            The Workout object to be serialized to JSON.
   * @param jsonGenerator      The JsonGenerator to write the JSON output.
   * @param serializerProvider The SerializerProvider for additional
   *                           serialization options.
   * @throws IOException If an I/O error occurs during JSON serialization.
   */
  /*
   * format: { "name": "...", "date": "...", "exercises": [ ... ] }
   */
  @Override
  public void serialize(final Workout workout,
      final JsonGenerator jsonGenerator,
      final SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeStartObject();
    // check if the workout has a name
    if (workout.getName() != null) {
      // writes the name of the workout to Json format as shown above
      jsonGenerator.writeStringField("name", workout.getName());
    }
    // check if the workout has a date
    // if (workout.getDateAsString() != null) {
    // // writes the date of the workout to Json format as shown above
    // jsonGenerator.writeStringField("date", workout.getDateAsString());
    // }
    // check if the workout contains exercises
    // if (workout.getExerciseCount() != 0) {
    // // writes the exercises of the workout to Json format as shown above
    // jsonGenerator.writeArrayFieldStart("exercises");
    // for (Exercise exercise : workout.getExercises()) {
    // jsonGenerator.writeObject(exercise);
    // }
    // jsonGenerator.writeEndArray();
    // }
    jsonGenerator.writeEndObject();
  }
}
