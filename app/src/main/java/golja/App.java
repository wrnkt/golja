package golja;

import java.lang.InterruptedException;

public class App {
    public static void main(String[] args) {
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
