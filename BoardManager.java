import java.util.function.*;
import java.lang.Thread;

public class BoardManager
{
    static int aliveCells = 0;

    private static final int DEFAULT_ROWS = 40;
    private static final int DEFAULT_COLS = 140;

    private static final int DEFAULT_MAX_GENERATIONS = 1000;
    private static final int DEFAULT_MS_DELAY = 90;

    private static char aliveChar = 'x';
    private static char deadChar = '.';

    static final BooleanSupplier DEFAULT_LIFE_CHANCE = () -> Math.random() > 0.7;

    static Appliable DEFAULT_RULE = (c, r, b) -> {
        int aliveNeighbors = countAliveNeighbors(c, r, b);
        if(b[c][r].isAlive())
        {
            if(aliveNeighbors == 2 || aliveNeighbors == 3)
                return true;
            else
                return false;
        }
        else
        {
            if(aliveNeighbors == 3)
                return true;
            else
                return false;
        }
    };

    public static Cell[][] constructNextFrame(Cell[][] originalBoard, Appliable rule)
    {
        int numRows = originalBoard[0].length;
        int numColumns = originalBoard.length;;

        Cell[][] nextBoard = new Cell[numColumns][numRows];

        for(int row = 0; row < numRows; row++)
        {
            for(int col = 0; col < numColumns; col++)
            {
                nextBoard[col][row] = rule.apply(col, row, originalBoard) ?
                    new Cell(State.ALIVE) : new Cell(State.DEAD);
            }
        }

        System.out.println("\n");

        return nextBoard;
    }

    public static int countAliveNeighbors(int col, int row, Cell[][] board)
    {
        int neighborCount = 0;

        for(int r = (row - 1); r <= (row + 1); r++)
        {
            for(int c = (col - 1); c <= (col + 1); c++)
            {
                if(r >= 0 && r < board[0].length &&
                   c >= 0 && c < board.length &&
                   !(col == c && row == r)
                   )
                {
                    if(board[c][r].isAlive())
                    {
                        neighborCount++;
                    }
                }
            }
        }
        return neighborCount;
    }

    public static Cell[][] randomBoard(int columns, int rows, BooleanSupplier lifeChance)
    {
        Cell[][] randomBoard = new Cell[columns][rows];

        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                State state = lifeChance.getAsBoolean() ?
                    State.ALIVE :
                    State.DEAD;

                randomBoard[c][r] = new Cell(state);
            }
        }
        return randomBoard;
    }

    public static Cell[][] deadBoard(int columns, int rows)
    {
        Cell[][] deadBoard = new Cell[columns][rows];

        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                deadBoard[c][r] = new Cell(State.DEAD);
            }
        }
        return deadBoard;
    }

    public static void printBoard(Cell[][] board)
    {
        aliveCells = 0;
        for(int row = 0; row < board[0].length; row++)
        {
            for(int col = 0; col < board.length; col++)
            {
                if(board[col][row].isAlive())
                {
                    System.out.print(aliveChar);
                    aliveCells++;
                }
                else
                    System.out.print(deadChar);

            }
            System.out.println();
        }
    }

    public static void printBoardInfo(Cell[][] board)
    {
        System.out.println(String.format("%d/%d cells are alive.", aliveCells, (board[0].length*board.length)));
    }

    public static void animateBoard(Cell[][] board) throws InterruptedException
    {
        animateBoard(board, DEFAULT_RULE, DEFAULT_MS_DELAY, DEFAULT_MAX_GENERATIONS);
    }

    public static void animateBoard(Cell[][] board, Appliable rule) throws InterruptedException
    {
        animateBoard(board, rule, DEFAULT_MS_DELAY, DEFAULT_MAX_GENERATIONS);
    }

    public static void animateBoard(Cell[][] board, int msDelay) throws InterruptedException
    {
        animateBoard(board, DEFAULT_RULE, msDelay, DEFAULT_MAX_GENERATIONS);
    }

    public static void animateBoard(Cell[][] board, Appliable rule, int msDelay) throws InterruptedException
    {
        animateBoard(board, rule, msDelay, DEFAULT_MAX_GENERATIONS);
    }

    public static void animateBoard(Cell[][] board, Appliable rule, int msDelay, int maxGenerations) throws InterruptedException
    {
        int generation = 0;
        Cell[][] currentBoard = board;

        while(generation < maxGenerations)
        {
            clearTerm();
            printBoard(currentBoard);
            printBoardInfo(board);
            currentBoard = constructNextFrame(currentBoard, rule);
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
        Cell[][] emptyBoard = new Cell[DEFAULT_COLS][DEFAULT_ROWS];

        Cell[][] testBoard = randomBoard(DEFAULT_COLS, DEFAULT_ROWS, DEFAULT_LIFE_CHANCE);

        try {
            animateBoard(testBoard);
        } catch(InterruptedException e) {
            System.out.println("[LOG]: Board print failed.");
        }
        
    }
}
