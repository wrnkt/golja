public class BoardPrinter
{
    private static final int DEFAULT_ROWS = 40;
    private static final int DEFAULT_COLS = 140;

    private static final int DEFAULT_MAX_GENERATIONS = 1000;
    private static final int DEFAULT_MS_DELAY = 90;

    private static char aliveChar = 'x';
    private static char deadChar = '.';

    private final static BoardManager boardManager = new BoardManager();

    public static void printBoard(Cell[][] board)
    {
        boardManager.aliveCells = 0;
        for(int row = 0; row < board[0].length; row++)
        {
            for(int col = 0; col < board.length; col++)
            {
                if(board[col][row].isAlive())
                {
                    System.out.print(aliveChar);
                    boardManager.aliveCells++;
                }
                else
                    System.out.print(deadChar);

            }
            System.out.println();
        }
    }

    public static void printBoardInfo(Cell[][] board)
    {
        System.out.println(String.format("%d/%d cells are alive.", boardManager.aliveCells, (board[0].length*board.length)));
    }

    public static void animateBoard(Cell[][] board) throws InterruptedException
    {
        animateBoard(board, boardManager.DEFAULT_RULE, DEFAULT_MS_DELAY, DEFAULT_MAX_GENERATIONS);
    }

    public static void animateBoard(Cell[][] board, AppliableRule rule) throws InterruptedException
    {
        animateBoard(board, rule, DEFAULT_MS_DELAY, DEFAULT_MAX_GENERATIONS);
    }

    public static void animateBoard(Cell[][] board, int msDelay) throws InterruptedException
    {
        animateBoard(board, boardManager.DEFAULT_RULE, msDelay, DEFAULT_MAX_GENERATIONS);
    }

    public static void animateBoard(Cell[][] board, AppliableRule rule, int msDelay) throws InterruptedException
    {
        animateBoard(board, rule, msDelay, DEFAULT_MAX_GENERATIONS);
    }

    public static void animateBoard(Cell[][] board, AppliableRule rule, int msDelay, int maxGenerations) throws InterruptedException
    {
        int generation = 0;
        Cell[][] currentBoard = board;

        while(generation < maxGenerations)
        {
            clearTerm();
            printBoard(currentBoard);
            printBoardInfo(board);
            currentBoard = boardManager.constructNextFrame(currentBoard, rule);
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
        Cell[][] testBoard = boardManager.randomBoard(DEFAULT_COLS, DEFAULT_ROWS, boardManager.DEFAULT_LIFE_CHANCE);

        try {
            animateBoard(testBoard);
        } catch(InterruptedException e) {
            System.out.println("[LOG]: Board print failed.");
        }
    }
}
