package golja.ui;

import golja.model.board.Board;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GamePane extends HBox
{
  final GridPane grid;

  public GamePane(final int cols, final int rows)
  {
    final VBox vBox = new VBox();

    grid = new GridPane();


    setupGrid(grid, 600, 600, cols, rows);
    setupVBox(vBox, grid);

    alignmentProperty().set(Pos.CENTER);
    getChildren().add(vBox);

  }

  public void updateCells(Board board) {
    for(int y = 0; y < board.getHeight(); ++y) {
      for(int x = 0; x < board.getWidth(); ++x) {
        Node n = this.gridPaneArray[x][y];
        BoardCell cell = (BoardCell) n;
        if( board.at(x, y).isAlive()) {
          cell.setColor(Color.AQUA);
        } else {
          cell.setColor(Color.BLACK);
        }
      }
    }
  }

  private void setupVBox(VBox vBox, GridPane grid)
  {
    vBox.alignmentProperty().set(Pos.CENTER);

    final NumberBinding binding = Bindings.min(widthProperty(), heightProperty());
    vBox.prefWidthProperty().bind(binding);
    vBox.prefHeightProperty().bind(binding);

    vBox.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
    vBox.setFillWidth(true);
    vBox.setVgrow(grid, Priority.ALWAYS);

    vBox.getChildren().add(grid);
  }

  private void setupGrid(
    GridPane grid,
    final int minX,
    final int minY,
    final int cols,
    final int rows
  ) {
    grid.setStyle("-fx-background-color:red");
    grid.setMinSize(minX, minY);

    applyGridColConstraints(grid, cols);
    applyGridRowConstraints(grid, rows);

    // for(int x = 0; x < cols; ++x) {
    //   for(int y = 0; y < rows; ++y) {
    //     BoardCell cell = new BoardCell();
    //     grid.add(cell, x, y);
    //   }
    // }
    initializeGridPaneArray(rows, cols);
  }

  private Node[][] gridPaneArray = null;

  private void initializeGridPaneArray(int rows, int cols)
    {
       this.gridPaneArray = new BoardCell[rows][cols];
       for(Node cell : this.grid.getChildren())
       {
          this.gridPaneArray[GridPane.getRowIndex(cell)][GridPane.getColumnIndex(cell)] = new BoardCell();
       }
    }

  private void applyGridColConstraints(final GridPane grid, final int cols)
  {
    for(int i = 0; i < cols; ++i) {
      final ColumnConstraints constraints = new ColumnConstraints(
        Control.USE_PREF_SIZE,
        Control.USE_COMPUTED_SIZE,
        Double.MAX_VALUE
      );
      constraints.setHgrow(Priority.SOMETIMES);
      grid.getColumnConstraints().add(constraints);
    }
  }

  private void applyGridRowConstraints(final GridPane grid, final int rows)
  {
    for(int i = 0; i < rows; ++i) {
      final RowConstraints constraints = new RowConstraints(
        Control.USE_PREF_SIZE,
        Control.USE_COMPUTED_SIZE,
        Double.MAX_VALUE
      );
      constraints.setVgrow(Priority.SOMETIMES);
      grid.getRowConstraints().add(constraints);
    }
  }

}
