package workoutplanner.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h1>WorkoutPlannerServerApp</h1>
 * The main class for the WorkoutPlannerServerApp Spring Boot application.
 *
 * <p>
 * This class serves as the entry point for launching the
 * WorkoutPlannerServerApp. It is annotated with {@code @SpringBootApplication}
 * to enable Spring Boot auto-configuration and component scanning. The
 * {@code main} method initializes and starts the Spring Boot application using
 * the {@code SpringApplication} class.
 * </p>
 *
 * @since 2.0.0
 * @author Michael Brusegard
 * @version 1.0.0
 */
@SpringBootApplication
public class WorkoutPlannerServerApp {
  /**
   * The main method to start the WorkoutPlannerServerApp.
   *
   * <p>
   * This method is the entry point for launching the WorkoutPlannerServerApp.
   * It uses the SpringApplication class to run the application, initializing
   * and starting the Spring Boot application.
   * </p>
   *
   * @param args The command-line arguments passed to the application.
   */
  public static void main(final String[] args) {
    SpringApplication.run(WorkoutPlannerServerApp.class, args);
  }
}
