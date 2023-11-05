package golja;

public class BoardPrinter
{
    public static final int DEFAULT_ROWS = 40;
    public static final int DEFAULT_COLS = 140;

    public static final int DEFAULT_MAX_GENERATIONS = 1000;
    public static final int DEFAULT_MS_DELAY = 90;

    private static char aliveChar = 'x';
    private static char deadChar = '.';

    private final static BoardManager boardManager = new BoardManager();


    public static void printBoard(Board board) {
        for(int row = 0; row < board.getHeight(); row++)
        {
            for(int col = 0; col < board.getWidth(); col++)
            {
                if(board.at(col,row).isAlive())
                {
                    System.out.print(aliveChar);
                }
                else
                    System.out.print(deadChar);

            }
            System.out.println();
        }
    }


    public static void printInfo(Board board)
    {
      System.out.println(
        String.format(
          "%d/%d cells are alive.",
          board.getStats().aliveCells(),
          board.getStats().totalCells()
      ));
    }

    public static void animateBoard(Board board) throws InterruptedException
    {
        animateBoard(
          board,
          boardManager.DEFAULT_RULE,
          DEFAULT_MS_DELAY,
          DEFAULT_MAX_GENERATIONS
        );
    }

    public static void animateBoard(
      Board board,
      AppliableRule rule
    ) throws InterruptedException
    {
        animateBoard(
          board,
          rule,
          DEFAULT_MS_DELAY,
          DEFAULT_MAX_GENERATIONS
        );
    }

    public static void animateBoard(
      Board board,
      int msDelay
    ) throws InterruptedException
    {
        animateBoard(
          board,
          boardManager.DEFAULT_RULE,
          msDelay,
          DEFAULT_MAX_GENERATIONS
        );
    }

    public static void animateBoard(
      Board board,
      AppliableRule rule,
      int msDelay
    ) throws InterruptedException
    {
        animateBoard(
          board,
          rule,
          msDelay,
          DEFAULT_MAX_GENERATIONS
        );
    }

    public static void animateBoard(
      Board board,
      AppliableRule rule,
      int msDelay,
      int maxGenerations
    ) throws InterruptedException
    {
        int generation = 0;
        Board currentBoard = board;

        while(generation < maxGenerations)
        {
            clearTerm();
            printBoard(currentBoard);
            printInfo(board);
            BoardManager.update(currentBoard, rule);
            generation++;
            Thread.sleep(msDelay);
        }
    }

    public static void clearTerm()
    {
        System.out.print("\033[H\033[2J");
    }
    
    public static void main(String[] args)
    {
        Board testBoard = BoardManager.randomBoard(DEFAULT_COLS, DEFAULT_ROWS, BoardManager.DEFAULT_LIFE_CHANCE);

        try {
            animateBoard(testBoard);
        } catch(InterruptedException e) {
            System.out.println("[LOG]: Board print failed.");
        }
    }
}
