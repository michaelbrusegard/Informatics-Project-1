package workoutplanner.fxutil;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Function;

import javafx.geometry.HPos;
import javafx.geometry.VPos;

/**
 * <h1>GridBuilder</h1>
 * <p>
 * GridBuilder is a helper class for creating a grid of cells for Workouts and
 * Exercises.
 * </p>
 *
 * @since 2.0.0
 * @author Michael Brusegard
 * @version 2.0.0
 */
public class GridBuilder {
  private ScrollPane scrollPane;
  private List<?> items;
  private Function<Integer, VBox> createCell;
  /**
   * Local int variable, for gridPane width and height.
   */
  private static final int PREFSIZE = 600;
  /**
   * Local int variable, used to define row height of the grid.
   */
  private static final int ROWHEIGHT = 175;
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
   * @param scrollPane ScrollPane to add GridPane to add cells to.
   * @param items      List of items to add to the grid.
   * @param createCell Function to create a cell for the grid.
   */
  public GridBuilder(ScrollPane scrollPane, List<?> items, Function<Integer, VBox> createCell) {
    this.items = items;
    this.scrollPane = scrollPane;
    this.createCell = createCell;
    createGrid();
  }

  private void createGrid() {
    GridPane gridPane = new GridPane();
    gridPane.setGridLinesVisible(true);
    gridPane.setAlignment(javafx.geometry.Pos.CENTER);
    gridPane.setPrefWidth(PREFSIZE);
    gridPane.setPrefHeight(PREFSIZE);
    scrollPane.setContent(gridPane);
    initializeGrid(gridPane);
  }

  /**
   * Initializes the grid.
   */
  private void initializeGrid(GridPane gridPane) {
    // Calculate number of rows
    int rows = (items.size() + COLUMNS - 1) / COLUMNS;

    // Set column constraints
    for (int i = 0; i < COLUMNS; i++) {
      ColumnConstraints columnConstraints = new ColumnConstraints(COLUMNWIDTH, COLUMNWIDTH, COLUMNWIDTH,
          Priority.SOMETIMES, HPos.CENTER, false);
      gridPane.getColumnConstraints().add(columnConstraints);
    }

    // Set row constraints
    for (int i = 0; i < rows; i++) {
      RowConstraints rowConstraints = new RowConstraints(ROWHEIGHT, ROWHEIGHT, ROWHEIGHT, Priority.SOMETIMES,
          VPos.CENTER, false);
      gridPane.getRowConstraints().add(rowConstraints);
    }

    // Add cells with content to grid
    int currentRow = 0;
    int currentColumn = 0;
    for (int index = 0; index < items.size(); index++) {
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
