package workoutplanner.core;

import java.util.List;

public interface UserAccess {
  public Workout getCurrentWorkout();

  public List<Workout> getWorkouts();

  public void setCurrentWorkout(final int workoutIndex);

  public void addExerciseToCurrentWorkout(final String inputName,
      final int sets,
      final int repMin,
      final int repMax,
      final int weight);

  public void removeExerciseFromCurrentWorkout(final int exerciseIndex);

  public void moveExerciseInCurrentWorkout(final int exerciseIndex, final boolean left);

  public void saveCurrentWorkout(final String name, final String date);

  public void removeWorkout(final int workoutIndex);

  public void removeCurrentWorkout();

  public List<String> getExerciseList();
}
