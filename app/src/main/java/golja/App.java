package golja;

import java.io.IOException;

import golja.console.BoardPrinter;
import golja.model.board.BoardManager;
import golja.console.ConsoleApp;

public class App {
    public static void main(String[] args) {
        try {
            ConsoleApp.main(args);
        } catch (IOException e) {
            System.out.println(e);
        }
          //Golja.main(args);
          // try
          // {
          //     BoardPrinter.animateBoard(
          //       BoardManager.constructStandardBoard(
          //         BoardPrinter.DEFAULT_COLS,
          //         BoardPrinter.DEFAULT_ROWS
          //       )
          //     );
          // }
          // catch (InterruptedException e)
          // {
          //     System.out.println("Failed to animate");
          // }
    }
}
