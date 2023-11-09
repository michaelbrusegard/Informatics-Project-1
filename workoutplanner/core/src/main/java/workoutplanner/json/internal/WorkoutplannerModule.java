package workoutplanner.json.internal;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.EnumSet;
import java.util.Set;
import workoutplanner.core.Exercise;
import workoutplanner.core.User;
import workoutplanner.core.Workout;
import workoutplanner.json.WorkoutplannerPersistence.WorkoutplannerComponents;

public class WorkoutplannerModule extends SimpleModule {

  public WorkoutplannerModule(final Set<WorkoutplannerComponents> components) {
    super("Workoutplanner", Version.unknownVersion());
    addSerializer(Exercise.class, new ExerciseSerializer());
    addDeserializer(Exercise.class, new ExerciseDeserializer());

    addSerializer(Workout.class, new WorkoutSerializer());
    addDeserializer(Workout.class, new WorkoutDeserializer());

    addSerializer(User.class, new UserSerializer());
    addDeserializer(User.class, new UserDeserializer());
  }

  public WorkoutplannerModule() {
    this(EnumSet.allOf(WorkoutplannerComponents.class));
  }
}
