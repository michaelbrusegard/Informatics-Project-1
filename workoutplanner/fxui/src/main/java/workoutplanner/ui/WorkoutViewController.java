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

public class WorkoutViewController extends Controller {

    @FXML
    private ScrollPane scrollPane;
    /**
     * Local int variable, used to define size of display-font for workout-name.
     */
    private static final int FONTSIZE = 20;
    /**
     * Local int variable, used to define the y-position of data in the cell.
     */
    private static final int LAYOUTY = 28;

    @FXML
    private void returnBack() {
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
            noWorkouts.setFont(new Font(FONTSIZE));
            container.getChildren().add(noWorkouts);
            container.setAlignment(Pos.CENTER);
            scrollPane.setContent(container);
        }
    }

    private Group createCell(int index) {
        Group cell = new Group();
        Text name = new Text(getMainController().getUser().getWorkouts().get(index).getName());
        name.setFont(new Font(FONTSIZE));
        Text date = new Text(getMainController().getUser().getWorkouts().get(index).getDateAsString());
        date.setLayoutY(LAYOUTY);
        // Define buttons
        Button viewButton = new Button("View");
        Button deleteButton = new Button("Delete");
        viewButton.setOnAction(event -> view(index));
        deleteButton.setOnAction(event -> delete(index));
        HBox buttonBox = new HBox(viewButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);
        buttonBox.setLayoutY(1.5 * LAYOUTY);
        cell.getChildren().addAll(name, date, buttonBox);
        return cell;
    }

    private void view(int index) {
        getMainController().getUser().setCurrentWorkout(index);
        getMainController().showFXML("Overview", index);
    }

    private void delete(int index) {
        if (UIUtils.showConfirmation("Delete Workout",
                "Are you sure you want to delete " + getMainController().getUser().getWorkouts().get(index).getName()
                        + "? "
                        + "All exercise data will be lost.")) {
            getMainController().getUser().getWorkouts().remove(index);
            init();
        }
    }
}
