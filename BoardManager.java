import java.util.function.*;
import java.lang.Thread;

public class BoardManager
{
    static int aliveCells = 0;

    private static final int MAX_ROWS = 40;
    private static final int MAX_COLS = 140;

    private static char aliveChar = 'x';
    private static char deadChar = '.';

    static Appliable defaultRule = (c, r, b) -> {
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

    public static Cell[][] constructNextFrame(Cell[][] originalBoard)
    {
        // I'm creating a whole new board each time so I dont really need
        // nextGenerationRef. 

        int numRows = originalBoard[0].length;
        int numColumns = originalBoard.length;;
        boolean[][] nextGenerationRef = new boolean[numColumns][numRows];

        Cell[][] nextBoard = new Cell[numColumns][numRows];

        for(int row = 0; row < originalBoard[0].length; row++)
        {
            for(int col = 0; col < originalBoard.length; col++)
            {
                nextGenerationRef[col][row] = defaultRule.apply(col, row, originalBoard);
                // could use a single boolean as a transfer value
                /*
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
                */
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
        BooleanSupplier lifeChance = () -> Math.random() > 0.7;

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

    public static void animateBoard(Cell[][] board, int msDelay, int maxGenerations) throws InterruptedException
    {
        int generation = 0;
        Cell[][] currentBoard = board;

        while(generation < maxGenerations)
        {
            clearTerm();
            printBoard(currentBoard);
            System.out.println(String.format("%d/%d cells are alive.", aliveCells, (board[0].length*board.length)));
            currentBoard = constructNextFrame(currentBoard);
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
        Cell[][] emptyBoard = new Cell[MAX_COLS][MAX_ROWS];

        Cell[][] testBoard = randomBoard(MAX_COLS, MAX_ROWS);

        try {
            animateBoard(testBoard, 90, 2000);
        } catch(InterruptedException e) {
            System.out.println("[LOG]: Board print failed.");
        }
        
    }
}
