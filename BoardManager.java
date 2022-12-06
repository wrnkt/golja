import java.util.function.*;
import java.lang.Thread;

public class BoardManager
{
    static int aliveCells = 0;

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
        return nextBoard;
    }

    private static boolean isInBoard(int col, int row, Cell[][] board)
    {
        if(row < 0 || row >= board[0].length)
            return false;

        if(col < 0 || col >= board.length)
            return false;

        return true;
    }

    public static int countAliveNeighbors(int col, int row, Cell[][] board)
    {
        int neighborCount = 0;

        for(int r = (row - 1); r <= (row + 1); r++)
        {
            for(int c = (col - 1); c <= (col + 1); c++)
            {
                if(!isInBoard(c, r, board))
                   continue;

                if(col == c && row == r)
                   continue;

                if(board[c][r].isAlive())
                {
                    neighborCount++;
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
}
