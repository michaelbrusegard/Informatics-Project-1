package workoutplanner.restserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import workoutplanner.core.Exercise;
import workoutplanner.core.User;
import workoutplanner.core.Workout;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(UserController.WORKOUTPLANNER_SERVICE_PATH)
public class UserController {
  private final User user;

  private static final Logger logger = Logger.getLogger(UserController.class.getName());
  private ObjectMapper objectMapper = new ObjectMapper();

  protected static final String WORKOUTPLANNER_SERVICE_PATH = "user/";

  public UserController() {
    user = new User();
  }

  @GetMapping("/current-workout")
  public Workout getCurrentWorkout() {
    Workout currentWorkout = user.getCurrentWorkout();
    logEndpoint("GET /current-workout", currentWorkout);
    return currentWorkout;
  }

  @GetMapping("/workouts")
  public List<Workout> getWorkouts() {
    List<Workout> workouts = user.getWorkouts();
    logEndpoint("GET /workouts", workouts);
    return workouts;
  }

  @GetMapping("/exercise-list")
  public List<String> getExerciseList() {
    List<String> exerciseList = user.getExerciseList();
    logEndpoint("GET /exercise-list", exerciseList);
    return exerciseList;
  }

  @PutMapping("/current-workout/{workoutIndex}")
  public void setCurrentWorkout(@PathVariable int workoutIndex) {
    logEndpoint("PUT /current-workout/" + workoutIndex);
    user.setCurrentWorkout(workoutIndex);
  }

  @PutMapping("/current-workout/exercise")
  public void addExerciseToCurrentWorkout(@RequestBody Exercise exercise) {
    logEndpoint("PUT /current-workout/exercise", exercise);
    user.addExerciseToCurrentWorkout(exercise.name(), exercise.sets(), exercise.repMin(), exercise.repMax(),
        exercise.weight());
  }

  @PutMapping("/current-workout/exercise/{exerciseIndex}")
  public void moveExerciseInCurrentWorkout(@PathVariable int exerciseIndex, @RequestParam boolean left) {
    logEndpoint(
        "PUT /current-workout/exercise/" + exerciseIndex + "?left=" + left);
    user.moveExerciseInCurrentWorkout(exerciseIndex, left);
  }

  @PutMapping("/current-workout/save")
  public void saveCurrentWorkout(@RequestParam String name, @RequestParam String date) {
    logEndpoint("PUT /current-workout/save" + "?name=" + name);
    user.saveCurrentWorkout(name, date);
  }

  @DeleteMapping("/workout/{workoutIndex}")
  public void removeWorkout(@PathVariable int workoutIndex) {
    logEndpoint("DELETE /workout/" + workoutIndex);
    user.removeWorkout(workoutIndex);
  }

  @DeleteMapping("/current-workout")
  public void removeCurrentWorkout() {
    logEndpoint("DELETE /current-workout");
    user.removeCurrentWorkout();
  }

  @DeleteMapping("/current-workout/exercise/{exerciseIndex}")
  public void removeExerciseFromCurrentWorkout(@PathVariable int exerciseIndex) {
    logEndpoint("DELETE /current-workout/exercise/" + exerciseIndex);
    user.removeExerciseFromCurrentWorkout(exerciseIndex);
  }

  private void logEndpoint(String endpoint) {
    logger.log(Level.INFO, endpoint);
  }

  private void logEndpoint(String endpoint, Object content) {
    try {
      String contentString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(content);
      logger.log(Level.INFO, endpoint + "\n" + contentString);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}
