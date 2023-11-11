package workoutplanner.json.internal;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import workoutplanner.core.Exercise;
import workoutplanner.core.User;
import workoutplanner.core.Workout;

/**
 * <h1>WorkoutplannerModule</h1>
 * The Workoutplanner Module sets up the serializers and deserializers for
 * the classes used in the workoutplanner app.
 *
 * <p>
 * The WorkoutplannerModule class is a Jackson JSON module designed for
 * handling serialization and deserialization of Exercise, Workout, and User
 * classes. It extends the SimpleModule class and adds specific serializers and
 * deserializers for these classes to facilitate JSON processing.
 * </p>
 *
 * @since 2.0.0
 * @author Arman Ilkka Nemati
 * @version 1.1.0
 */
public class WorkoutplannerModule extends SimpleModule {

  /**
   * Constructs a new instance of the WorkoutplannerModule.
   *
   * <p>
   * This constructor initializes the WorkoutplannerModule with the necessary
   * serializers and deserializers for the Exercise, Workout, and User classes.
   * It adds serializers and deserializers for Exercise, Workout, and User
   * classes to handle their serialization and deserialization during the JSON
   * processing.
   * </p>
   */
  public WorkoutplannerModule() {
    super("Workoutplanner", Version.unknownVersion());
    // Add Exercise serializers and deserializers
    addSerializer(Exercise.class, new ExerciseSerializer());
    addDeserializer(Exercise.class, new ExerciseDeserializer());
    // Add Workout serializers and deserializers
    addSerializer(Workout.class, new WorkoutSerializer());
    addDeserializer(Workout.class, new WorkoutDeserializer());
    // Add User serializers and deserializers
    addSerializer(User.class, new UserSerializer());
    addDeserializer(User.class, new UserDeserializer());
  }
}
