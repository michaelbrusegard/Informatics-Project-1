package workoutplanner.rest;

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

import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<String> setCurrentWorkout(@PathVariable int workoutIndex) {
    ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid workout index.");
    // Validate
    if (ValidateEndpoints.validateWorkoutIndex(workoutIndex, user.getWorkouts().size())) {
      response = ResponseEntity.ok("Current workout set successfully.");
      user.setCurrentWorkout(workoutIndex);
    }

    logEndpoint("PUT /current-workout/" + workoutIndex + " | " + response.getStatusCode() + " -" + response.getBody());
    return response;
  }

  @PutMapping("/current-workout/exercise")
  public ResponseEntity<String> addExerciseToCurrentWorkout(@RequestBody Exercise exercise) {
    ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid exercise input.");
    // Validate
    if (ValidateEndpoints.validateExerciseInput(exercise, user.getExerciseList())) {
      response = ResponseEntity.ok("Exercise added successfully.");
      user.addExerciseToCurrentWorkout(exercise.name(), exercise.sets(), exercise.repMin(), exercise.repMax(),
          exercise.weight());
    }

    logEndpoint("PUT /current-workout/exercise" + " | " + response.getStatusCode() + " -" + response.getBody(),
        exercise);
    return response;
  }

  @PutMapping("/current-workout/exercise/{exerciseIndex}")
  public ResponseEntity<String> moveExerciseInCurrentWorkout(@PathVariable int exerciseIndex,
      @RequestParam boolean left) {
    ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid exercise index.");
    // Validate
    if (ValidateEndpoints.validateExerciseIndex(exerciseIndex, user.getCurrentWorkout().getExercises().size())) {
      response = ResponseEntity.ok("Exercise moved successfully.");
      user.moveExerciseInCurrentWorkout(exerciseIndex, left);
    }

    logEndpoint(
        "PUT /current-workout/exercise/" + exerciseIndex + "?left=" + left + " | " + response.getStatusCode() + " -"
            + response.getBody());
    return response;
  }

  @PutMapping("/current-workout/save")
  public ResponseEntity<String> saveCurrentWorkout(@RequestParam String name, @RequestParam String date) {
    ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid name or date.");
    // Validate
    if (ValidateEndpoints.validateSaveWorkoutInput(name, date)) {
      response = ResponseEntity.ok("Current workout saved successfully.");
      user.saveCurrentWorkout(name, date);
    }

    logEndpoint(
        "PUT /current-workout/save" + "?name=" + name + "&date=" + date + " | " + response.getStatusCode() + " -"
            + response.getBody());
    return response;
  }

  @DeleteMapping("/workout/{workoutIndex}")
  public ResponseEntity<String> removeWorkout(@PathVariable int workoutIndex) {
    ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid workout index.");
    // Validate
    if (ValidateEndpoints.validateWorkoutIndex(workoutIndex, user.getWorkouts().size()))
      response = ResponseEntity.ok("Workout removed successfully.");
    user.removeWorkout(workoutIndex);

    logEndpoint("DELETE /workout/" + workoutIndex + " | " + response.getStatusCode() + " -" + response.getBody());
    return response;
  }

  @DeleteMapping("/current-workout")
  public ResponseEntity<String> removeCurrentWorkout() {
    ResponseEntity<String> response = ResponseEntity.ok("Current workout removed successfully.");
    user.removeCurrentWorkout();

    logEndpoint("DELETE /current-workout" + " | " + response.getStatusCode() + " -" + response.getBody());
    return response;
  }

  @DeleteMapping("/current-workout/exercise/{exerciseIndex}")
  public ResponseEntity<String> removeExerciseFromCurrentWorkout(@PathVariable int exerciseIndex) {
    ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid exercise index.");
    // Validate
    if (ValidateEndpoints.validateExerciseIndex(exerciseIndex, user.getCurrentWorkout().getExercises().size())) {
      response = ResponseEntity.ok("Exercise removed successfully");
      user.removeExerciseFromCurrentWorkout(exerciseIndex);
    }

    logEndpoint("DELETE /current-workout/exercise/" + exerciseIndex + " | " + response.getStatusCode() + " -"
        + response.getBody());
    return response;
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
