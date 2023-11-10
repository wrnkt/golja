package golja.ui;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardCell extends Pane
{
  public BoardCell()
  {
    final Rectangle rect = new Rectangle();
    //rect.widthProperty().bind(this.widthProperty());
    //rect.heightProperty().bind(this.heightProperty());

    //rect.setFill(Color.AQUA);

    this.getChildren().add(rect);

    // this.setBorder(
    //   new Border(
    //     new BorderStroke(
    //       Color.BLACK, 
    //       BorderStrokeStyle.SOLID,
    //       CornerRadii.EMPTY,
    //       BorderWidths.FULL
    //     )
    //   )
    // );
    this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, null, null)));
  }

}
