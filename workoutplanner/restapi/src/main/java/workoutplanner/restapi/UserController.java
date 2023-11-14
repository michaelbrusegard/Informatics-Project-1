package workoutplanner.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import workoutplanner.core.Exercise;
import workoutplanner.core.User;

@RestController
@RequestMapping(UserController.WORKOUTPLANNER_SERVICE_PATH)
public class UserController {
        private final User user;
        private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
        private final ObjectMapper objectMapper = new ObjectMapper();
        protected static final String WORKOUTPLANNER_SERVICE_PATH = "user/";
        private final boolean logObjects = true;

        public UserController() {
                user = new User();
        }

        @GetMapping("/current-workout/saved")
        public boolean getCurrentWorkoutSaved() {
                boolean currentWorkoutSaved = user.getCurrentWorkoutSaved();
                logEndpoint("GET /current-workout/saved", currentWorkoutSaved);
                return currentWorkoutSaved;
        }

        @GetMapping("/current-workout/empty")
        public boolean getCurrentWorkoutEmpty() {
                boolean currentWorkoutEmpty = user.getCurrentWorkoutEmpty();
                logEndpoint("GET /current-workout/empty", currentWorkoutEmpty);
                return currentWorkoutEmpty;
        }

        @GetMapping("/current-workout/name")
        public String getCurrentWorkoutName() {
                String currentWorkoutName = user.getCurrentWorkoutName();
                logEndpoint("GET /current-workout/name", currentWorkoutName);
                return "\"" + currentWorkoutName + "\"";
        }

        @GetMapping("/current-workout/exercises")
        public List<Exercise> getCurrentWorkoutExercises() {
                List<Exercise> currentWorkoutExercises = user.getCurrentWorkoutExercises();
                logEndpoint("GET /current-workout/exercises", currentWorkoutExercises);
                return currentWorkoutExercises;
        }

        @GetMapping("/workout/names")
        public List<String> getWorkoutNames() {
                List<String> workoutNames = user.getWorkoutNames();
                logEndpoint("GET /workout/names", workoutNames);
                return workoutNames;
        }

        @GetMapping("/workout/dates")
        public List<String> getWorkoutDates() {
                List<String> workoutDates = user.getWorkoutDates();
                logEndpoint("GET /workout/dates", workoutDates);
                return workoutDates;
        }

        @GetMapping("/exercise-list")
        public List<String> getExerciseList() {
                List<String> exerciseList = user.getExerciseList();
                logEndpoint("GET /exercise-list", exerciseList);
                return exerciseList;
        }

        @PutMapping("/current-workout/{workoutIndex}")
        public ResponseEntity<String> setCurrentWorkout(
                        @PathVariable final int workoutIndex) {
                ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid workout index.");
                // Validate
                if (ValidateEndpoints.validateWorkoutIndex(workoutIndex,
                                user.getWorkoutNames().size())) {
                        response = ResponseEntity.ok("Current workout set successfully.");
                        user.setCurrentWorkout(workoutIndex);
                }
                logEndpoint("PUT /current-workout/" + workoutIndex + " | "
                                + response.getStatusCode() + " -" + response.getBody());
                return response;
        }

        @PutMapping("/current-workout/exercise")
        public ResponseEntity<String> addExerciseToCurrentWorkout(
                        @RequestBody final Exercise exercise) {
                ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid exercise input.");
                // Validate
                if (ValidateEndpoints.validateExerciseInput(exercise,
                                user.getExerciseList())) {
                        response = ResponseEntity.ok("Exercise added successfully.");
                        user.addExerciseToCurrentWorkout(exercise.name(), exercise.sets(),
                                        exercise.repMin(), exercise.repMax(), exercise.weight());
                }
                logEndpoint("PUT /current-workout/exercise" + " | "
                                + response.getStatusCode() + " -" + response.getBody(),
                                exercise);
                return response;
        }

        @PutMapping("/current-workout/exercise/{exerciseIndex}")
        public ResponseEntity<String> moveExerciseInCurrentWorkout(
                        @PathVariable final int exerciseIndex,
                        @RequestParam final boolean left) {
                ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid exercise index.");
                // Validate
                if (ValidateEndpoints.validateExerciseIndex(exerciseIndex,
                                user.getCurrentWorkoutExercises().size())) {
                        response = ResponseEntity.ok("Exercise moved successfully.");
                        user.moveExerciseInCurrentWorkout(exerciseIndex, left);
                }
                logEndpoint(
                                "PUT /current-workout/exercise/" + exerciseIndex + "?left=" + left
                                                + " | " + response.getStatusCode() + " -" + response.getBody());
                return response;
        }

        @PutMapping("/current-workout/save")
        public ResponseEntity<String> saveCurrentWorkout(
                        @RequestParam final String name, @RequestParam final String date) {
                ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid name or date.");
                // Validate
                if (ValidateEndpoints.validateSaveWorkoutInput(name, date)) {
                        response = ResponseEntity.ok("Current workout saved successfully.");
                        user.saveCurrentWorkout(name, date);
                }
                logEndpoint(
                                "PUT /current-workout/save" + "?name=" + name + "&date=" + date
                                                + " | " + response.getStatusCode() + " -" + response.getBody());
                return response;
        }

        @DeleteMapping("/workout/{workoutIndex}")
        public ResponseEntity<String> removeWorkout(
                        @PathVariable final int workoutIndex) {
                ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid workout index.");
                // Validate
                if (ValidateEndpoints.validateWorkoutIndex(workoutIndex,
                                user.getWorkoutNames().size()))
                        response = ResponseEntity.ok("Workout removed successfully.");
                user.removeWorkout(workoutIndex);
                logEndpoint("DELETE /workout/" + workoutIndex + " | "
                                + response.getStatusCode() + " -" + response.getBody());
                return response;
        }

        @DeleteMapping("/current-workout")
        public ResponseEntity<String> removeCurrentWorkout() {
                ResponseEntity<String> response = ResponseEntity.ok("Current workout removed successfully.");
                user.removeCurrentWorkout();
                logEndpoint("DELETE /current-workout" + " | " + response.getStatusCode()
                                + " -" + response.getBody());
                return response;
        }

        @DeleteMapping("/current-workout/exercise/{exerciseIndex}")
        public ResponseEntity<String> removeExerciseFromCurrentWorkout(
                        @PathVariable final int exerciseIndex) {
                ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid exercise index.");
                // Validate
                if (ValidateEndpoints.validateExerciseIndex(exerciseIndex,
                                user.getCurrentWorkoutExercises().size())) {
                        response = ResponseEntity.ok("Exercise removed successfully");
                        user.removeExerciseFromCurrentWorkout(exerciseIndex);
                }
                logEndpoint("DELETE /current-workout/exercise/" + exerciseIndex + " | "
                                + response.getStatusCode() + " -" + response.getBody());
                return response;
        }

        private void logEndpoint(final String endpoint) {
                LOGGER.log(Level.INFO, endpoint);
        }

        private void logEndpoint(final String endpoint, final Object content) {
                if (!logObjects) {
                        logEndpoint(endpoint);
                        return;
                }
                try {
                        String contentString = objectMapper.writerWithDefaultPrettyPrinter()
                                        .writeValueAsString(content);
                        LOGGER.log(Level.INFO, endpoint + "\n" + contentString);
                } catch (JsonProcessingException e) {
                        e.printStackTrace();
                }
        }
}
