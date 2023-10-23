package workoutplanner.ui;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import workoutplanner.core.Exercise;

/**
 * <h1>ExerciseCell.</h1>
 * <p>
 * The ExerciseCell class represents a virtual container for
 * displaying information about an exercise.
 * It encapsulates the Exercise model and creates a digital representation
 * of the exercise information.
 * </p>
 *
 * @since 1.0.0
 * @author Erlend Løken Sæveraas
 * @version 2.0.0
 */
public class ExerciseCell {
  /**
   * Local exercise variable, used to gather data.
   */
  private final Exercise exercise;
  /**
   * Local group variable, used to group gathered data.
   */
  private Group group;
  /**
   * Local double variable, used to define the x-position of data in the cell.
   */
  private static final double XLAYOUT = -10.0;
  /**
   * Local int variable, used to define the y-position of data in the cell.
   */
  private static final int YLAYOUT = 30;
  /**
   * Local int variable, used to define the font size used in the cell.
   */
  private static final int FONTSIZE = 18;

  /**
   * Constructs an ExerciseCell with the provided Exercise instance.
   * <p>
   * This constructor initializes an ExerciseCell
   * with the given Exercise instance,
   * allowing the cell to display information related to the exercise.
   * It also triggers the creation of the data for the exercise cell.
   * </p>
   *
   * @param inputExercise The Exercise instance to be displayed in the cell.
   */
  public ExerciseCell(final Exercise inputExercise) {
    this.exercise = inputExercise;
    this.createFxCell();
  }

  private void createFxCell() {
    Group cellGroup = new Group();
    Text name = new Text(exercise.name() + ":");
    name.setLayoutX(XLAYOUT);
    Font font = new Font("Serif", FONTSIZE);
    name.setFont(font);
    Text sets = new Text("Sets: " + exercise.sets());
    Text reps = new Text("Reps: " + exercise.repMin()
        + " - " + exercise.repMax());
    Text weight = new Text("Weight: " + exercise.weight() + "kg");
    cellGroup.getChildren().addAll(name, sets, reps, weight);
    for (int index = 1; index < cellGroup.getChildren().size(); index++) {
      cellGroup.getChildren().get(index).setLayoutY(index * YLAYOUT);
      ((Text) cellGroup.getChildren().get(index)).setFont(font);
    }
    group = cellGroup;
  }

  /**
   * Retrieves the JavaFX Group representing the exercise cell.
   * <p>
   * This method returns the JavaFX Group that contains
   * the data for the exercise cell.
   * It allows external code to access
   * and display the data from the exercise cell.
   * </p>
   *
   * @return The Group containing the data of the exercise cell.
   */
  public Group getGroup() {
    return group;
  }
}
