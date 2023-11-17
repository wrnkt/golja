package golja;

import golja.console.BoardPrinter;
import golja.model.board.BoardManager;

public class App {
    public static void main(String[] args) {
      //Golja.main(args);
      try
      {
          BoardPrinter.animateBoard(
            BoardManager.constructStandardBoard(
              BoardPrinter.DEFAULT_COLS,
              BoardPrinter.DEFAULT_ROWS
            )
          );
      }
      catch (InterruptedException e)
      {
          System.out.println("Failed to animate");
      }
    }
}
