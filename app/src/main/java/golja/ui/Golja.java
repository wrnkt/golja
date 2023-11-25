package golja.ui;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import golja.model.board.Board;
import golja.model.board.BoardManager;
import golja.rule.Rules;
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

  // private Theme theme = Theme.defaultTheme();

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
    final int DEFAULT_MAX_GENERATIONS = 1000;
    final int DEFAULT_MS_DELAY = 90;

    int cols = 50;
    int rows = 50;

    BorderPane border = new BorderPane();
    border.setLeft(addVBox());

    Board gameBoard = BoardManager.constructStandardBoard(cols, rows);

    GamePane gamePane = new GamePane(cols, rows);

    border.setRight(new GamePane(cols, rows));

    Scene scene = new Scene(border);
    stage.setScene(scene);
    stage.setTitle("Golja");
    stage.show();

    try {
      BoardManager.updateBoard(gameBoard, gamePane, Rules.DEFAULT_RULE, DEFAULT_MS_DELAY, DEFAULT_MAX_GENERATIONS, updateGameState);
    } catch (Exception e) {
    }
  }

  public static BiConsumer<Board, GamePane> updateGameState = (board, gamePane) -> {
    gamePane.updateCells(board);
  };
  

  public static void main(String[] args)
  {
    launch();
  }
}

