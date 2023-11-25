package golja.ui;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardCell extends Pane
{
  final Rectangle rect;

  public BoardCell()
  {
    rect = new Rectangle();
    //rect.widthProperty().bind(this.widthProperty());
    //rect.heightProperty().bind(this.heightProperty());

    //rect.setFill(Color.AQUA);

    this.getChildren().add(rect);

    this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, null, null)));
  }

  public void setColor(Color color) {
    this.rect.setFill(color);
  }

}
