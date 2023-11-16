package workoutplanner.fxutil;

import java.util.function.Function;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

/**
 * <h1>GridBuilder</h1>
 * GridBuilder is a helper class for creating a grid of cells for Workouts and
 * Exercises.
 * <p>
 * This class provides the functionality to create a grid layout for displaying
 * a list of items, such as Workouts or Exercises, in a scrollable view. The
 * grid is designed to accommodate multiple cells organized in rows and columns.
 * </p>
 *
 * @since 2.0.0
 * @author Michael Brusegard
 * @version 2.0.0
 */
public class GridBuilder {
  /**
   * Local scrollPane variable, used for containing the grid.
   */
  private final ScrollPane scrollPane;

  /**
   * Local int variable, used for knowing how many items to add to the grid.
   */
  private final int itemCount;

  /**
   * Local function variable, used for containing the items in the grid.
   */
  private final Function<Integer, VBox> createCell;
  /**
   * Local int variable, for gridPane width and height.
   */
  private static final int PREFSIZE = 600;
  /**
   * Local int variable, used to define row height of the grid.
   */
  private static final int ROWHEIGHT = 169;
  /**
   * Local int variable, used to define column width of the grid.
   */
  private static final int COLUMNWIDTH = 199;
  /**
   * Local int variable, define how may columns.
   */
  private static final int COLUMNS = 3;

  /**
   * Constructor for GridBuilder.
   *
   * @param inputScrollPane ScrollPane to add GridPane to add cells to.
   * @param inputItemCount  Count of items to add to the grid
   * @param inputCreateCell Function to create a cell for the grid.
   */
  public GridBuilder(final ScrollPane inputScrollPane, final int inputItemCount,
      final Function<Integer, VBox> inputCreateCell) {
    this.itemCount = inputItemCount;
    this.scrollPane = inputScrollPane;
    this.createCell = inputCreateCell;
    createGrid();
  }

  private void createGrid() {
    GridPane gridPane = new GridPane();
    gridPane.setGridLinesVisible(true);
    gridPane.setAlignment(javafx.geometry.Pos.CENTER);
    gridPane.setPrefWidth(PREFSIZE);
    gridPane.setPrefHeight(PREFSIZE);
    scrollPane.setContent(gridPane);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    initializeGrid(gridPane);
  }

  private void initializeGrid(final GridPane gridPane) {
    // Calculate the number of rows
    int rows = (itemCount + COLUMNS - 1) / COLUMNS;

    // Set column constraints
    for (int i = 0; i < COLUMNS; i++) {
      ColumnConstraints columnConstraints = new ColumnConstraints(COLUMNWIDTH,
          COLUMNWIDTH, COLUMNWIDTH, Priority.SOMETIMES, HPos.CENTER, false);
      gridPane.getColumnConstraints().add(columnConstraints);
    }

    // Set row constraints
    for (int i = 0; i < rows; i++) {
      RowConstraints rowConstraints = new RowConstraints(ROWHEIGHT, ROWHEIGHT,
          ROWHEIGHT, Priority.SOMETIMES, VPos.CENTER, false);
      gridPane.getRowConstraints().add(rowConstraints);
    }

    // Add cells with content to grid
    int currentRow = 0;
    int currentColumn = 0;
    for (int index = 0; index < itemCount; index++) {
      VBox cell = createCell.apply(index);
      gridPane.add(cell, currentColumn % COLUMNS, currentRow / COLUMNS);
      currentRow++;
      if (currentColumn < COLUMNS - 1) {
        currentColumn++;
      } else {
        currentColumn = 0;
      }
    }
  }
}
