import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

import java.lang.Thread;

public class CellBoard
{
    private static final int MAX_ROWS = 20;
    private static final int MAX_COLS = 30;

    private static int tickLength = 4;

    private Cell[][] board = new Cell[MAX_ROWS][MAX_COLS];

    private static char aliveChar = 'x';
    private static char deadChar = '.';


    public CellBoard(Cell[][] cellArray)
    {
        board = cellArray;
    }

    public static void printBoard(Cell[][] board)
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

    public static void printFrames(ArrayList<Cell[][]> frames)
    {
        int frameCount = 0;
        for(Cell[][] frame : frames)
        {
            clearTerm();
            System.out.println(String.format("Frame %d", frameCount));
            printBoard(frame);
            frameCount++;

            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {
                System.out.println(
                        String.format(
                            "[ERROR]: Failed to print frames.`"
                            ));
            }
        }
    }

    public static ArrayList<Cell[][]> createRandomizedFrameList(int length)
    {
        ArrayList<Cell[][]> frameList = new ArrayList<>();

        for(int i = 0; i < length; i++)
        {
            frameList.add(randomBoard(MAX_ROWS, MAX_COLS));
        }

        return frameList;
    }

    public static Cell[][] randomBoard(int maxRows, int maxCols)
    {
        Cell[][] randomBoard = new Cell[maxRows][maxCols];

        for(int c = 0; c < maxCols; c++)
        {
            for(int r = 0; r < maxRows; r++)
            {
                State state = Math.random() > 0.8 ? State.ALIVE : State.DEAD;
                randomBoard[r][c] = new Cell(state);
            }
        }

        return randomBoard;
    }

    public static void clearTerm()
    {
        System.out.print("\033[H\033[2J");
    }

    public static void main(String[] args)
    {
        ArrayList<Cell[][]> randomFrames1 = createRandomizedFrameList(100);
        printFrames(randomFrames1);

    }
}
