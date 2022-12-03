import java.util.function.*;
import java.lang.Thread;

public class BoardManager
{
    private static final int MAX_ROWS = 20;
    private static final int MAX_COLS = 30;

    private static char aliveChar = 'x';
    private static char deadChar = '.';

    public static Cell[][] deadBoard(int maxCols, int maxRows)
    {
        Cell[][] deadBoard = new Cell[maxCols][maxRows];

        for(int r = 0; r < maxRows; r++)
        {
            for(int c = 0; c < maxCols; c++)
            {
                deadBoard[c][r] = new Cell(State.DEAD);
            }
        }
        return deadBoard;
    }

    public static Cell[][] constructNextFrame(Cell[][] originalBoard)
    {
        int numRows = originalBoard[0].length;
        int numColumns = originalBoard.length;;
        boolean[][] nextGenerationRef = new boolean[numColumns][numRows];

        /*
        System.out.println("passed board values:");
        System.out.println(
                String.format(
                    "rows:%d\ncolumns:%d\n",
                    originalBoard[0].length,
                    originalBoard.length));

        System.out.println("reference array values:");
        System.out.println(
                String.format(
                    "rows:%d\ncolumns:%d\n",
                    nextGenerationRef[0].length,
                    nextGenerationRef.length));
        */

        Cell[][] nextBoard = new Cell[numColumns][numRows];

        for(int row = 0; row < originalBoard[0].length; row++)
        {
            for(int col = 0; col < originalBoard.length; col++)
            {
                int aliveNeighbors = countAliveNeighbors(col, row, originalBoard);
                if(originalBoard[col][row].isAlive())
                {
                    if(aliveNeighbors == 2 || aliveNeighbors == 3)
                    {
                        nextGenerationRef[col][row] = true;
                    }
                    else
                    {
                        nextGenerationRef[col][row] = false;
                    }
                }
                else
                {
                    if(aliveNeighbors == 3)
                    {
                        nextGenerationRef[col][row] = true;
                    }
                }
            }
        }

        for(int row = 0; row < originalBoard[0].length; row++)
        {
            for(int col = 0; col < originalBoard.length; col++)
            {
                if(nextGenerationRef[col][row])
                    nextBoard[col][row] = new Cell(State.ALIVE);
                else
                    nextBoard[col][row] = new Cell(State.DEAD);
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
                // System.out.println(String.format("(%d, %d)",c, r));
                if(r >= 0 && r < board[0].length)
                {
                    if(c >= 0 && c < board.length)
                    {
                        if(!(col == c && row == r))
                        {
                            // System.out.println(String.format("%s at (%d, %d)", board[c][r], c, r));
                            if(board[c][r].isAlive())
                            {
                                neighborCount++;
                            }
                        }
                    }
                }
            }
        }
        // System.out.println(neighborCount);
        return neighborCount;
    }

    public static Cell[][] randomBoard(int maxCols, int maxRows)
    {
        Cell[][] randomBoard = new Cell[maxCols][maxRows];
        BooleanSupplier lifeChance = () -> Math.random() > 0.8;

        for(int r = 0; r < maxRows; r++)
        {
            for(int c = 0; c < maxCols; c++)
            {
                State state = lifeChance.getAsBoolean() ?
                    State.ALIVE :
                    State.DEAD;

                randomBoard[c][r] = new Cell(state);
            }
        }
        return randomBoard;
    }

    public static void printBoard(Cell[][] board)
    {
        for(int row = 0; row < board[0].length; row++)
        {
            for(int col = 0; col < board.length; col++)
            {
                if(board[col][row].isAlive())
                    System.out.print(aliveChar);
                else
                    System.out.print(deadChar);

            }
            System.out.println();
        }
        /*
        System.out.println(
                String.format(
                    "rows:%d\ncolumns:%d\n",
                    board[0].length,
                    board.length));
        */
    }

    public static void clearTerm()
    {
        System.out.print("\033[H\033[2J");
    }

    public static void main(String[] args)
    {
        Cell[][] emptyBoard = new Cell[MAX_COLS][MAX_ROWS];

        Cell[][] testBoard = randomBoard(MAX_COLS, MAX_ROWS);

        // System.out.println(String.format("# rows: %d", testBoard[0].length));
        // System.out.println(String.format("# cols: %d", testBoard.length));
        // printBoard(testBoard);
        // printBoard(constructNextFrame(testBoard));
        // countAliveNeighbors(29, 0, testBoard);

        Cell[][] currentBoard = randomBoard(MAX_COLS,MAX_ROWS);
        while(true)
        {
            clearTerm();
            printBoard(currentBoard);
            currentBoard = constructNextFrame(currentBoard);
            try
            {
                Thread.sleep(500);
            }
            catch(InterruptedException e)
            {
            }
        }
        
    }
}
