package golja;

import java.lang.InterruptedException;
import golja.BoardManager;
import golja.CellGen;

public class App {
    public static void main(String[] args) {
      CellGen.PSEUDO_EVEN_CELL_SUPPLIER.get();
    /*
        try
        {
            BoardPrinter.animateBoard(
                    BoardManager.randomBoard(
                        BoardPrinter.DEFAULT_COLS,
                        BoardPrinter.DEFAULT_ROWS,
                        BoardManager.DEFAULT_LIFE_CHANCE
                    )
            );
        }
        catch (InterruptedException e)
        {
            System.out.println("Failed to animate");
        }
  */
    }
}
