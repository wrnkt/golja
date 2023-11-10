package golja.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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

