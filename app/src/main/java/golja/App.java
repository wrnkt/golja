package golja;

import java.lang.InterruptedException;

public class App {

    public void run() {
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

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
