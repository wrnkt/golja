package golja.ui;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Golja extends Application
{

  public VBox addVBox()
  {
    VBox vBox = new VBox();
    vBox.setPadding(new Insets(10));
    vBox.setSpacing(8);

    Text placeholderText = new Text("Settings");
    placeholderText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    vBox.getChildren().add(placeholderText);

    return vBox;
  }

  @Override
  public void start(Stage stage)
  {
    BorderPane border = new BorderPane();
    border.setLeft(addVBox());

    border.setRight(new GamePane());

    Scene scene = new Scene(border);
    stage.setScene(scene);
    stage.setTitle("Golja");
    stage.show();
  }

  public static void main(String[] args)
  {
    launch();
  }
}

