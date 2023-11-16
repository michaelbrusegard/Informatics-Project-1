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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import workoutplanner.core.User;

/**
 * <h1>RestController</h1>
 * Controller class for handling HTTP requests related to user workouts.
 *
 * <p>
 * This class defines RESTful endpoints for managing user workouts, including
 * retrieving workout information, modifying the current workout, and managing
 * saved workouts.
 * </p>
 *
 * @since 2.0.0
 * @author Michael Brusegard
 * @version 1.0.0
 */
@RestController
@RequestMapping(UserController.WORKOUTPLANNER_SERVICE_PATH)
public class UserController {
        /**
         * Local User variable, used for handling the objects between front and
         * backend.
         */
        private final User user;

        /**
         * Local Logger variable, used for logging responses and exceptions.
         */
        private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

        /**
         * Local ObjectMapper variable, used for handling objects that are sent
         * between front and backend.
         */
        private final ObjectMapper objectMapper = new ObjectMapper();

        /**
         * Local String variable, used to determine the path in the system directory.
         */
        protected static final String WORKOUTPLANNER_SERVICE_PATH = "user/";

        /**
         * Local boolean variable, used for determining whether to log objects or not.
         */
        private final boolean logObjects;

        /**
         * Constructs a new instance of the UserController class.
         *
         * <p>
         * This constructor initializes a UserController object with default values.
         * It sets the logObjects flag to false and creates a new User object with
         * default configurations (e.g., remote access enabled).
         * </p>
         */
        public UserController() {
                logObjects = false;
                user = new User(true);
        }

        /**
         * Retrieves the saved state of the current workout.
         *
         * <p>
         * This method sends a HTTP GET request to the "/current-workout/saved"
         * endpoint to fetch the saved state of the current workout. It delegates
         * the request to the user object and logs the endpoint and result.
         * </p>
         *
         * @return True if the current workout is saved, false otherwise.
         */
        @GetMapping("/current-workout/saved")
        public boolean getCurrentWorkoutSaved() {
                boolean currentWorkoutSaved = user.getCurrentWorkoutSaved();
                logEndpoint("GET /current-workout/saved", currentWorkoutSaved);
                return currentWorkoutSaved;
        }

        /**
         * Retrieves the exercise count of the current workout.
         *
         * <p>
         * This method sends a HTTP GET request to the
         * "/current-workout/exercise-count" endpoint to fetch the count of exercises
         * in the current workout. It delegates the request to the user object and
         * logs the endpoint and result.
         * </p>
         *
         * @return The number of exercises in the current workout.
         */
        @GetMapping("/current-workout/exercise-count")
        public int getCurrentWorkoutExerciseCount() {
                int currentWorkoutExerciseCount = user.getCurrentWorkoutExerciseCount();
                logEndpoint("GET /current-workout/exercise-count",
                                currentWorkoutExerciseCount);
                return currentWorkoutExerciseCount;
        }

        /**
         * Retrieves the name of the current workout.
         *
         * <p>
         * This method sends a HTTP GET request to the "/current-workout/name"
         * endpoint to fetch the name of the current workout. It delegates the request
         * to the user object, wraps the result in double quotes, and logs the
         * endpoint and result.
         * </p>
         *
         * @return The name of the current workout.
         */
        @GetMapping("/current-workout/name")
        public String getCurrentWorkoutName() {
                String currentWorkoutName = user.getCurrentWorkoutName();
                logEndpoint("GET /current-workout/name", currentWorkoutName);
                return "\"" + currentWorkoutName + "\"";
        }

        /**
         * Retrieves a specific attribute of an exercise in the current workout.
         *
         * <p>
         * This method sends a HTTP GET request to the
         * "/current-workout/exercise/{exerciseIndex}" endpoint with the specified
         * attribute to fetch the corresponding attribute value of the exercise in the
         * current workout. It validates the input, delegates the request to the user
         * object, wraps the result in double quotes, and logs the endpoint and
         * result.
         * </p>
         *
         * @param exerciseIndex The index of the exercise in the current workout.
         * @param attribute     The attribute to retrieve (e.g., "name", "sets",
         *                      "repMin").
         * @return The value of the specified attribute for the given exercise.
         */
        @GetMapping("/current-workout/exercise/{exerciseIndex}")
        public String getCurrentWorkoutExercise(@PathVariable final int exerciseIndex,
                        @RequestParam final String attribute) {
                String currentWorkoutExerciseAttribute = ResponseEntity.badRequest()
                                .body("Invalid exercise index or attribute").toString();
                // Validate
                if (ValidateEndpoints.validateExerciseIndex(exerciseIndex,
                                user.getCurrentWorkoutExerciseCount())
                                && ValidateEndpoints.validateExerciseAttribute(attribute)) {
                        currentWorkoutExerciseAttribute = "\"" + user
                                        .getCurrentWorkoutExerciseAttribute(exerciseIndex,
                                                        attribute)
                                        + "\"";
                }
                logEndpoint("GET /current-workout/exercise/" + exerciseIndex
                                + "/?attribute=" + attribute, currentWorkoutExerciseAttribute);
                return currentWorkoutExerciseAttribute;
        }

        /**
         * Retrieves the list of workout names.
         *
         * <p>
         * This method sends a HTTP GET request to the "/workout/names" endpoint
         * to fetch the names of all available workouts. It delegates the request
         * to the user object, logs the endpoint and result, and returns the list
         * of workout names.
         * </p>
         *
         * @return A List containing the names of all available workouts.
         */
        @GetMapping("/workout/names")
        public List<String> getWorkoutNames() {
                List<String> workoutNames = user.getWorkoutNames();
                logEndpoint("GET /workout/names", workoutNames);
                return workoutNames;
        }

        /**
         * Retrieves the list of workout dates.
         *
         * <p>
         * This method sends a HTTP GET request to the "/workout/dates" endpoint
         * to fetch the dates of all available workouts. It delegates the request
         * to the user object, logs the endpoint and result, and returns the list
         * of workout dates.
         * </p>
         *
         * @return A List containing the dates of all available workouts.
         */
        @GetMapping("/workout/dates")
        public List<String> getWorkoutDates() {
                List<String> workoutDates = user.getWorkoutDates();
                logEndpoint("GET /workout/dates", workoutDates);
                return workoutDates;
        }

        /**
         * Retrieves the list of available exercises.
         *
         * <p>
         * This method sends a HTTP GET request to the "/exercise-list" endpoint
         * to fetch the list of available exercises. It delegates the request to
         * the user object, logs the endpoint and result, and returns the list
         * of exercises.
         * </p>
         *
         * @return A List of Strings representing the available exercises.
         */
        @GetMapping("/exercise-list")
        public List<String> getExerciseList() {
                List<String> exerciseList = user.getExerciseList();
                logEndpoint("GET /exercise-list", exerciseList);
                return exerciseList;
        }

        /**
         * Sets the current workout on the remote server.
         *
         * <p>
         * This method sends a HTTP PUT request to the
         * "/current-workout/{workoutIndex}" endpoint to set the current workout
         * based on the provided index. It validates the input, delegates the
         * request to the user object, logs the endpoint and result, and returns
         * an appropriate response entity.
         * </p>
         *
         * @param workoutIndex The index of the workout to set as the current workout.
         * @return ResponseEntity indicating the success or failure of the operation.
         */
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

        /**
         * Adds a new exercise to the current workout on the remote server.
         *
         * <p>
         * This method sends a HTTP PUT request to the "/current-workout/exercise"
         * endpoint with the specified parameters to add a new exercise to the
         * current workout. It validates the input, delegates the request to the
         * user object, logs the endpoint and result, and returns an appropriate
         * response entity.
         * </p>
         *
         * @param name   The name of the new exercise.
         * @param sets   The number of sets for the new exercise.
         * @param repMin The minimum number of repetitions for the new exercise.
         * @param repMax The maximum number of repetitions for the new exercise.
         * @param weight The weight for the new exercise.
         * @return ResponseEntity indicating the success or failure of the operation.
         */
        @PutMapping("/current-workout/exercise")
        public ResponseEntity<String> addExerciseToCurrentWorkout(
                        @RequestParam final String name, @RequestParam final int sets,
                        @RequestParam final int repMin, @RequestParam final int repMax,
                        @RequestParam final int weight) {
                ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid exercise input.");
                // Validate
                if (ValidateEndpoints.validateExerciseInput(name, sets, repMin,
                                repMax, weight, user.getExerciseList())) {
                        response = ResponseEntity.ok("Exercise added successfully.");
                        user.addExerciseToCurrentWorkout(name, sets, repMin, repMax, weight);
                }
                logEndpoint("PUT /current-workout/exercise" + " | "
                                + response.getStatusCode() + " -" + response.getBody());
                return response;
        }

        /**
         * Moves an exercise within the current workout on the remote server.
         *
         * <p>
         * This method sends a HTTP PUT request to the
         * "/current-workout/exercise/{exerciseIndex}?left={left}" endpoint to move
         * the specified exercise either to the left or right within the current
         * workout. It validates the input, delegates the request to the user object,
         * logs the endpoint and result, and returns an appropriate response entity.
         * </p>
         *
         * @param exerciseIndex The index of the exercise to be moved.
         * @param left          A boolean indicating the direction of movement
         *                      (true to go left, false to go right).
         * @return ResponseEntity indicating the success or failure of the operation.
         */
        @PutMapping("/current-workout/exercise/{exerciseIndex}")
        public ResponseEntity<String> moveExerciseInCurrentWorkout(
                        @PathVariable final int exerciseIndex,
                        @RequestParam final boolean left) {
                ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid exercise index.");
                // Validate
                if (ValidateEndpoints.validateExerciseIndex(exerciseIndex,
                                user.getCurrentWorkoutExerciseCount())) {
                        response = ResponseEntity.ok("Exercise moved successfully.");
                        user.moveExerciseInCurrentWorkout(exerciseIndex, left);
                }
                logEndpoint("PUT /current-workout/exercise/" + exerciseIndex + "?left="
                                + left + " | " + response.getStatusCode() + " -"
                                + response.getBody());
                return response;
        }

        /**
         * Saves the current workout on the remote server.
         *
         * <p>
         * This method sends a HTTP PUT request to the
         * "/current-workout/save?name={name}&date={date}" endpoint to save the
         * current workout with the specified name and date. It validates the input,
         * delegates the request to the user object, logs the endpoint and result,
         * and returns an appropriate response entity.
         * </p>
         *
         * @param name The name to be assigned to the current workout.
         * @param date The date to be assigned to the current workout.
         * @return ResponseEntity indicating the success or failure of the operation.
         */
        @PutMapping("/current-workout/save")
        public ResponseEntity<String> saveCurrentWorkout(
                        @RequestParam final String name, @RequestParam final String date) {
                ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid name or date.");
                // Validate
                if (ValidateEndpoints.validateSaveWorkoutInput(
                                name, date, user.getWorkoutNames())) {
                        response = ResponseEntity.ok("Current workout saved successfully.");
                        user.saveCurrentWorkout(name, date);
                }
                logEndpoint("PUT /current-workout/save" + "?name=" + name + "&date=" + date
                                + " | " + response.getStatusCode() + " -" + response.getBody());
                return response;
        }

        /**
         * Removes a workout from the remote server.
         *
         * <p>
         * This method sends a HTTP DELETE request to the "/workout/{workoutIndex}"
         * endpoint to remove the workout at the specified index. It validates the
         * input, delegates the request to the user object, logs the endpoint and
         * result, and returns an appropriate response entity.
         * </p>
         *
         * @param workoutIndex The index of the workout to be removed.
         * @return ResponseEntity indicating the success or failure of the operation.
         */
        @DeleteMapping("/workout/{workoutIndex}")
        public ResponseEntity<String> removeWorkout(
                        @PathVariable final int workoutIndex) {
                ResponseEntity<String> response = ResponseEntity.badRequest().body(
                                "Invalid workout index.");
                // Validate
                if (ValidateEndpoints.validateWorkoutIndex(workoutIndex,
                                user.getWorkoutNames().size())) {
                        response = ResponseEntity.ok("Workout removed successfully.");
                }
                user.removeWorkout(workoutIndex);
                logEndpoint("DELETE /workout/" + workoutIndex + " | "
                                + response.getStatusCode() + " -" + response.getBody());
                return response;
        }

        /**
         * Removes the current workout from the remote server.
         *
         * <p>
         * This method sends a HTTP DELETE request to the "/current-workout" endpoint
         * to remove the current workout. It delegates the request to the user object,
         * logs the endpoint and result, and returns an appropriate response entity.
         * </p>
         *
         * @return ResponseEntity indicating the success or failure of the operation.
         */
        @DeleteMapping("/current-workout")
        public ResponseEntity<String> removeCurrentWorkout() {
                ResponseEntity<String> response = ResponseEntity.ok("Current workout removed successfully.");
                user.removeCurrentWorkout();
                logEndpoint("DELETE /current-workout" + " | " + response.getStatusCode()
                                + " -" + response.getBody());
                return response;
        }

        /**
         * Removes an exercise from the current workout on the remote server.
         *
         * <p>
         * This method sends a HTTP DELETE request to the
         * "/current-workout/exercise/{exerciseIndex}" endpoint to remove the
         * specified exercise from the current workout. It validates the input,
         * delegates the request to the user object, logs the endpoint and result,
         * and returns an appropriate response entity.
         * </p>
         *
         * @param exerciseIndex The index of the exercise to be removed.
         * @return ResponseEntity indicating the success or failure of the operation.
         */
        @DeleteMapping("/current-workout/exercise/{exerciseIndex}")
        public ResponseEntity<String> removeExerciseFromCurrentWorkout(
                        @PathVariable final int exerciseIndex) {
                ResponseEntity<String> response = ResponseEntity.badRequest().body("Invalid exercise index.");
                // Validate
                if (ValidateEndpoints.validateExerciseIndex(exerciseIndex,
                                user.getCurrentWorkoutExerciseCount())) {
                        response = ResponseEntity.ok("Exercise removed successfully");
                        user.removeExerciseFromCurrentWorkout(exerciseIndex);
                }
                logEndpoint("DELETE /current-workout/exercise/" + exerciseIndex + " | "
                                + response.getStatusCode() + " -" + response.getBody());
                return response;
        }

        /**
         * Deletes unsaved workouts from the remote server.
         *
         * <p>
         * This method sends a HTTP DELETE request to the "/workouts/delete-unsaved"
         * endpoint to delete any unsaved workouts. It delegates the request to the
         * user object, logs the endpoint, and returns a ResponseEntity indicating
         * the success of the operation.
         * </p>
         *
         * @return ResponseEntity indicating the success of the operation.
         */
        @DeleteMapping("/workouts/delete-unsaved")
        public ResponseEntity<String> deleteUnsavedWorkouts() {
                ResponseEntity<String> response = ResponseEntity.ok("Unsaved workouts deleted successfully.");
                user.deleteUnsavedWorkouts();
                logEndpoint("DELETE /workouts/delete-unsaved");
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
                        LOGGER.log(Level.SEVERE, "Error processing JSON content for logging", e);
                }
        }
}
