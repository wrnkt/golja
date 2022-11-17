import java.util.Arrays;
import java.lang.Math;

public class CellBoard
{
    private static final int MAX_ROWS = 20;
    private static final int MAX_COLS = 30;

    private static int tickLength = 4;

    private Cell[][] board = new Cell[MAX_ROWS][MAX_COLS];

    private char aliveChar = 'x';
    private char deadChar = '.';


    public CellBoard(Cell[][] cellArray)
    {
        board = cellArray;
    }

    public void printBoard()
    {
        for(Cell[] row : board)
        {
            for(Cell c : row)
            {
                if(c.isAlive())
                    System.out.print(aliveChar);
                else
                    System.out.print(deadChar);
            }
            System.out.print("\n");
        }
    }

    public static Cell[][] randomBoard(int maxRows, int maxCols)
    {
        Cell[][] randomBoard = new Cell[maxRows][maxCols];

        for(int c = 0; c < maxCols; c++)
        {
            for(int r = 0; r < maxRows; r++)
            {
                State state = Math.random() > 0.5 ? State.ALIVE : State.DEAD;
                randomBoard[r][c] = new Cell(state);
            }
        }

        return randomBoard;
    }

    public static void main(String[] args)
    {
        Cell[][] defaultBoard = randomBoard(MAX_ROWS, MAX_COLS);

        CellBoard board = new CellBoard(defaultBoard);

        board.printBoard();
    }
}
