package workoutplanner.ui;

import java.io.IOException;
import java.util.List;

import workoutplanner.core.Workout;

public interface UserAccess {
  public Workout getCurrentWorkout() throws IOException;

  public List<Workout> getWorkouts() throws IOException;

  public void setCurrentWorkout(final int workoutIndex) throws IOException;

  public void removeWorkout(final int workoutIndex) throws IOException;

  public void removeCurrentWorkout() throws IOException;
}
