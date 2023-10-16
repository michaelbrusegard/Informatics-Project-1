package workoutplanner.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import workoutplanner.fxutil.PageLoader;

/**
 * <h1>PlanGridHandler.</h1>
 * Manages the display of workouts in a scrollable grid layout.
 * <p>
 *   This class is responsible for managing the display of WorkoutCells
 *   within a scrollable grid layout. It provides methods for adding
 *   WorkoutCells to the layout and creating the grid to display them.
 * </p>
 *
 * @since 1.0.0
 * @author Arman Ilkka Nemati
 * @version 1.4.0
 */
public class PlanGridHandler {
  /**
   * Local List variable, used for filling in the Gridpane.
   */
  private final List<WorkoutCell> workoutCellList = new ArrayList<>();
  /**
   * Local ScrollPane variable, used for making the grid scrollable.
   */
  private final ScrollPane scrollPane;
  /**
   * Local Gridpane variable, used for making the grid.
   */
  private final GridPane gridPane;
  /**
   * Local int variable, used for defining the maximum amount
   * of columns the grid can have.
   */
  private static final int MAXCOLUMNCOUNT = 3;
  /**
   * Local int variable, used for defining the row height of the grid.
   */
  private static final int ROWHEIGHT = 150;
  /**
   * Local int variable, used for defining the column width of the grid.
   */
  private static final int COLUMNWIDTH = 250;

  /**
   * Constructs a PlanGridHandler for managing the
   * display of workouts in a ScrollPane.
   * <p>
   *   This constructor initializes a PlanGridHandler
   *   with the provided ScrollPane, creating an associated GridPane
   *   for managing the layout of workouts.
   *   It also configures the grid layout settings
   *   using the configureGrid() method.
   * </p>
   *
   * @param inputScrollPane The ScrollPane to be used for displaying workouts.
   */
  public PlanGridHandler(final ScrollPane inputScrollPane) {
    this.scrollPane = inputScrollPane;
    this.gridPane = new GridPane();
    configureGrid();
  }

  private void configureGrid() {
    gridPane.setGridLinesVisible(true);
    gridPane.setOnMouseClicked(this::clickNode);
    gridPane.setAlignment(Pos.CENTER);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    scrollPane.setContent(gridPane);
  }

  private void clickNode(final MouseEvent event)  {
    Node clickedNode = event.getPickResult().getIntersectedNode();
    if (clickedNode != gridPane) {
      Node parent = clickedNode.getParent();
      while (parent != gridPane) {
        clickedNode = parent;
        parent = clickedNode.getParent();
      }
      int colIndex = GridPane.getColumnIndex(clickedNode);
      int rowIndex = GridPane.getRowIndex(clickedNode);
      FXMLLoader loader = new FXMLLoader(getClass()
              .getResource("Overview.fxml"));
      OverviewController overviewController;
      try {
        overviewController = (OverviewController) PageLoader
                .pageLoader(loader, clickedNode);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      int index = colIndex;
      for (int i = 0; i < rowIndex; i++) {
        index += MAXCOLUMNCOUNT;
      }
      overviewController.init(workoutCellList.get(index).workout());
      overviewController.loadOverviewFromPlan();
    }
  }

  private void addRow() {
    gridPane.getRowConstraints().add(new RowConstraints(ROWHEIGHT,
            ROWHEIGHT, ROWHEIGHT, Priority.SOMETIMES, VPos.CENTER, false));
  }

  private void addColumn() {
    gridPane.getColumnConstraints().add(new ColumnConstraints(COLUMNWIDTH,
            COLUMNWIDTH, COLUMNWIDTH, Priority.SOMETIMES, HPos.CENTER, false));
  }

  /**
   * Adds a WorkoutCell to the list of workout cells for display.
   *
   * <p>
   *   This method adds the provided WorkoutCell to the list of
   *   workout cells that will be displayed within the PlanGridHandler.
   *   The workout cells represent individual workouts to be shown
   *   in the PlanView.
   * </p>
   *
   * @param workoutCell The WorkoutCell to be added to the list of
   *                    displayed workout cells.
   */
  public void addWorkoutCell(final WorkoutCell workoutCell) {
    workoutCellList.add(workoutCell);
  }

  /**
   * Creates and populates a grid layout with WorkoutCells.
   *
   * <p>
   *   This method is responsible for creating a grid layout
   *   within the GridPane to display WorkoutCells.
   *   It determines the number of columns based on the number of
   *   WorkoutCells to ensure proper distribution.
   *   It then adds each WorkoutCell's content to the grid
   *   at the specified row and column positions.
   * </p>
   */
  public void createGrid() {
    int row = 0;
    int col = 0;
    addRow();
    int colAmount;
    if (workoutCellList.size() <= 1) {
      colAmount = 1;
    } else if (workoutCellList.size() == 2) {
      colAmount = 2;
    } else {
      colAmount = MAXCOLUMNCOUNT;
    }
    for (int i = 0; i < colAmount; i++) {
      addColumn();
    }
    for (WorkoutCell w : workoutCellList) {
      gridPane.add(w.getCellContent(), col, row);
      col++;
      if (col == MAXCOLUMNCOUNT) {
        col = 0;
        row++;
        addRow();
      }
    }
  }
}
