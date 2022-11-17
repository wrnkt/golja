import java.util.Arrays;

public class CellBoard
{
    private static final int MAX_ROWS = 20;
    private static final int MAX_COLS = 30;

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

    public static void main(String[] args)
    {
        Cell[][] defaultBoard = new Cell[MAX_ROWS][MAX_COLS];
        int i = 0;
        for (Cell[] row : defaultBoard)
        {
            if(i % 3 == 0)
                Arrays.fill(row, new Cell(State.DEAD));
            else
                Arrays.fill(row, new Cell(State.ALIVE));
            i++;
        }

        CellBoard board = new CellBoard(defaultBoard);

        board.printBoard();
    }
}
