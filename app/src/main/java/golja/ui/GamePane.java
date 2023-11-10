package golja.ui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GamePane extends HBox
{
  public GamePane()
  {
    final VBox vBox = new VBox();

    vBox.alignmentProperty().set(Pos.CENTER);
    alignmentProperty().set(Pos.CENTER);

    final GridPane grid = new GridPane();
    grid.setStyle("-fx-background-color:red");

    final NumberBinding binding = Bindings.min(widthProperty(), heightProperty());

    grid.setMinSize(200, 200);

    vBox.prefWidthProperty().bind(binding);
    vBox.prefHeightProperty().bind(binding);
    vBox.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);

    vBox.setFillWidth(true);
    vBox.setVgrow(grid, Priority.ALWAYS);

    int cols = 10;
    int rows = 10;

    applyGridColConstraints(grid, cols);
    applyGridRowConstraints(grid, rows);

    for(int x = 0; x < cols; ++x) {
      for(int y = 0; y < rows; ++y) {
        Rectangle cell = new Rectangle();
        cell.setWidth(5);
        cell.setHeight(5);
        cell.setFill(Color.AQUA);
        grid.add(cell, x, y);
        // grid.setRowIndex(cell, x);
        // grid.setColumnIndex(cell, y);
      }
    }

    vBox.getChildren().add(grid);
    getChildren().add(vBox);

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
