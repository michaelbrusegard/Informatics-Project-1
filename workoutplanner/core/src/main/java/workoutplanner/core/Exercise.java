package workoutplanner.core;

/**
 * <h1>Exercise</h1>
 * Represents an exercise with associated details.
 *
 * <p>
 * An Exercise record encapsulates information about an exercise,
 * including its name,
 * the number of sets, the minimum and maximum repetitions,
 * and the weight used.
 * It provides a simple and immutable structure for representing
 * exercise data.
 * </p>
 *
 * @since 1.0.0
 * @author Arman Ilkka Nemati
 * @version 1.4.0
 *
 * @param name   The name of the exercise.
 * @param sets   The number of sets for the exercise.
 * @param repMin The minimum number of repetitions for each set.
 * @param repMax The maximum number of repetitions for each set.
 * @param weight The weight used for the exercise.
 */
public record Exercise(String name, int sets, int repMin,
    int repMax, int weight) {
}
