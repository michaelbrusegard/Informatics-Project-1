package workoutplanner.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import workoutplanner.core.Exercise;
import workoutplanner.core.Workout;

public interface UserAccess {
  public Workout getCurrentWorkout() throws IOException;

  public List<Workout> getWorkouts() throws IOException;

  public void setCurrentWorkout(final int workoutIndex) throws IOException;

  public void addExerciseToCurrentWorkout(final Exercise exercise) throws IOException;

  public void removeExerciseFromCurrentWorkout(final int exerciseIndex) throws IOException;

  public void moveExerciseInCurrentWorkout(final int exerciseIndex, final boolean left) throws IOException;

  public void saveCurrentWorkout(final String name) throws IOException;

  public void removeWorkout(final int workoutIndex) throws IOException;

  public void removeCurrentWorkout() throws IOException;
}
