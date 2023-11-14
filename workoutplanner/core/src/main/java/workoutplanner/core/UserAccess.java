package workoutplanner.core;

import java.util.List;

public interface UserAccess {
  boolean getCurrentWorkoutSaved();

  int getCurrentWorkoutExerciseCount();

  String getCurrentWorkoutName();

  String getCurrentWorkoutExerciseAttribute(int exerciseIndex, String attribute);

  List<String> getWorkoutNames();

  List<String> getWorkoutDates();

  void setCurrentWorkout(int workoutIndex);

  void addExerciseToCurrentWorkout(String inputName,
      int sets,
      int repMin,
      int repMax,
      int weight);

  void removeExerciseFromCurrentWorkout(int exerciseIndex);

  void moveExerciseInCurrentWorkout(int exerciseIndex, boolean left);

  void saveCurrentWorkout(String name, String date);

  void removeWorkout(int workoutIndex);

  void removeCurrentWorkout();

  List<String> getExerciseList();
}
