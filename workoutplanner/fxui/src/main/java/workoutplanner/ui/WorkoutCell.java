package workoutplanner.ui;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import workoutplanner.core.Workout;

/**
 * <h1>WorkoutCell.</h1>
 * The WorkoutCell record represents a cell for
 * displaying information about a Workout.
 * <p>
 *   This record encapsulates the Workout instance
 *   and provides methods to retrieve the content
 *   of the WorkoutCell for graphical display.
 * </p>
 *
 * @since 1.0.0
 * @author Arman Ilkka Nemati
 * @version 1.4.0
 *
 * @param workout The Workout instance to be displayed in the cell.
 */
public record WorkoutCell(Workout workout) {
  /**
   * Local int variable, used to configure font size.
   */
  private static final int FONTSIZE = 28;

  private Text getName() {
    Text name = new Text("Name: " + workout.getName());
    name.setFont(new Font(FONTSIZE));
    return name;
  }

  private Text getDate() {
    Text date = new Text("Date: " + workout.getDateAsString());
    date.setLayoutY(FONTSIZE);
    return date;
  }

  /**
   * Retrieves the content of the ExerciseCell.
   * <p>
   *   This method creates a Group that contains the
   *   name and date components of an ExerciseCell.
   *   It returns the Group, which can be used to
   *   display the content of the ExerciseCell.
   * </p>
   *
   * @return A Group containing the name and date components
   *         of the ExerciseCell.
   */
  public Group getCellContent() {
    Group group = new Group();
    group.getChildren().addAll(getName(), getDate());
    return group;
  }
}
