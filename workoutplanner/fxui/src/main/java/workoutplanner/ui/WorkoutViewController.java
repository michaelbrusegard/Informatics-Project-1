package workoutplanner.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import workoutplanner.fxutil.GridBuilder;
import workoutplanner.fxutil.UIUtils;

/**
 * <h1>WorkoutViewController</h1>
 * <p>
 * The WorkoutViewController class is responsible for listing all workouts.
 * </p>
 *
 * @since 2.0.0
 * @author Michael Brusegard
 * @version 2.0.0
 */
public class WorkoutViewController extends Controller {

    @FXML
    private ScrollPane scrollPane;
    /**
     * Local int variable, used to define size of display-font for workout-name.
     */
    private static final int FONTSIZE = 20;
    /**
     * Local int variable, used to define size of display-font for workout-name.
     */
    private static final String FONT_FAMILY = "SansSerif";
    /**
     * Local int variable, used to define the y-position of data in the cell.
     */
    private static final int LAYOUTY = 28;

    @FXML
    private void returnHome() {
        getMainController().showFXML("Home");
    }

    public void init() {
        // Clear scrollpane
        scrollPane.setContent(new Group());
        // Create grid if there are workouts
        if (getMainController().getUser().getWorkouts().size() > 0) {
            new GridBuilder(scrollPane,
                    getMainController().getUser().getWorkouts(), this::createCell);
        } else {
            VBox container = new VBox();
            Text noWorkouts = new Text("You don't have any workouts yet.");
            noWorkouts.setFont(new Font(FONT_FAMILY,FONTSIZE));
            container.getChildren().add(noWorkouts);
            container.setAlignment(Pos.CENTER);
            scrollPane.setContent(container);
        }
    }

    private VBox createCell(int workoutIndex) {
        String defaultButton = "-fx-pref-width: 80;"+
        "-fx-pref-height: 35;"+
        "-fx-background-insets: 2;"+
        "-fx-background-color:  white;"+
        "-fx-border-color:  #666666;"+
        "-fx-border-width: 2;"+
        "-fx-background-radius: 20;"+
        "-fx-border-radius: 10;";

        VBox cell = new VBox();
        Text name = new Text(getMainController().getUser().getWorkouts().get(workoutIndex).getName());
        name.setFont(new Font(FONT_FAMILY,FONTSIZE));
        Text date = new Text(getMainController().getUser().getWorkouts().get(workoutIndex).getDateAsString());
        date.setLayoutY(LAYOUTY);
        // Define buttons
        Button viewButton = new Button("View");
        viewButton.setStyle(defaultButton);
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle(defaultButton);
        viewButton.setOnAction(event -> view(workoutIndex));
        deleteButton.setOnAction(event -> delete(workoutIndex));
        HBox buttonBox = new HBox(viewButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);
        buttonBox.setLayoutY(1.5 * LAYOUTY);
        cell.getChildren().addAll(name, date, buttonBox);
        return cell;
    }

    private void view(int workoutIndex) {
        getMainController().getUser().setCurrentWorkout(workoutIndex);
        getMainController().showFXML("Overview", workoutIndex);
    }

    private void delete(int workoutIndex) {
        if (UIUtils.showConfirmation("Delete Workout",
                "Are you sure you want to delete "
                        + getMainController().getUser().getWorkouts().get(workoutIndex).getName()
                        + "? "
                        + "All workout data will be lost.")) {
            getMainController().getUser().removeWorkout(workoutIndex);
            init();
        }
    }
}
