package workoutplanner.springboot.restserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import workoutplanner.core.Exercise;
import workoutplanner.core.User;
import workoutplanner.core.Workout;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(UserController.WORKOUTPLANNER_SERVICE_PATH)
public class UserController {
  private final User user;
  protected static final String WORKOUTPLANNER_SERVICE_PATH = "user/";

  public UserController() {
    user = new User();
  };

  @GetMapping("/current-workout")
  public Workout getCurrentWorkout() {
    return user.getCurrentWorkout();
  };

  @GetMapping("/workouts")
  public List<Workout> getWorkouts() {
    return user.getWorkouts();
  };

  @PutMapping("/current-workout/{workoutIndex}")
  public void setCurrentWorkout(@PathVariable int workoutIndex) {
    user.setCurrentWorkout(workoutIndex);
  };

  @PutMapping("/current-workout/exercise")
  public void addExerciseToCurrentWorkout(@RequestBody String exerciseJson) {
    Exercise exercise = Deserializer.deserializeExercise(exerciseJson);
    user.getCurrentWorkout().addExercise(exercise);
  };

  @PutMapping("/current-workout/exercise/{exerciseIndex}")
  public void moveExerciseInCurrentWorkout(@PathVariable int exerciseIndex, @RequestParam boolean left) {
    user.getCurrentWorkout().moveExercise(exerciseIndex, left);
  };

  @PutMapping("/current-workout/save")
  public void saveCurrentWorkout(@RequestParam String name) {
    user.getCurrentWorkout().setName(name);
    user.getCurrentWorkout().setDate(LocalDate.now());
    user.getCurrentWorkout().save();
  };

  @DeleteMapping("/workout/{workoutIndex}")
  public void removeWorkout(@PathVariable int workoutIndex) {
    user.removeWorkout(workoutIndex);
  };

  @DeleteMapping("/current-workout")
  public void removeCurrentWorkout() {
    user.removeCurrentWorkout();
  };

  @DeleteMapping("/current-workout/exercise/{exerciseIndex}")
  public void removeExerciseFromCurrentWorkout(@PathVariable int exerciseIndex) {
    user.getCurrentWorkout().removeExercise(exerciseIndex);
  };
}
