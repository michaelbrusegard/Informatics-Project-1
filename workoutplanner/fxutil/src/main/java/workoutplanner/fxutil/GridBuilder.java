package workoutplanner.fxutil;

import javafx.scene.Group;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.util.List;
import java.util.function.Function;

import javafx.geometry.HPos;
import javafx.geometry.VPos;

public class GridBuilder {
  private GridPane gridPane;
  private List<?> items;
  private Function<Integer, Group> createCell;
  /**
   * Local int variable, used to define row height of the grid.
   */
  private static final int ROWHEIGHT = 149;
  /**
   * Local int variable, used to define column width of the grid.
   */
  private static final int COLUMNWIDTH = 199;
  /**
   * Local int variable, define how may columns.
   */
  private static final int COLUMNS = 3;

  public GridBuilder(GridPane gridPane, List<?> items, Function<Integer, Group> createCell) {
    this.items = items;
    this.gridPane = gridPane;
    this.createCell = createCell;
    initializeGrid();
  }

  private void initializeGrid() {
    int rows = (items.size() + COLUMNS - 1) / COLUMNS;

    for (int i = 0; i < COLUMNS; i++) {
      ColumnConstraints columnConstraints = new ColumnConstraints(COLUMNWIDTH, COLUMNWIDTH, COLUMNWIDTH,
          Priority.SOMETIMES, HPos.CENTER, false);
      gridPane.getColumnConstraints().add(columnConstraints);
    }

    for (int i = 0; i < rows; i++) {
      RowConstraints rowConstraints = new RowConstraints(ROWHEIGHT, ROWHEIGHT, ROWHEIGHT, Priority.SOMETIMES,
          VPos.CENTER, false);
      gridPane.getRowConstraints().add(rowConstraints);
    }

    int currentRow = 0;
    int currentColumn = 0;
    for (int index = 0; index < items.size(); index++) {
      Group cell = createCell.apply(index);
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
