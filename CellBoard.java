import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

import java.util.function.*;

import java.lang.Thread;

// TODO: change Cell[][] to Cell[]. represent board with 1D array for efficiency
// and easier access.

public class CellBoard
{
    private static final int MAX_ROWS = 30;
    private static final int MAX_COLS = 20;

    private static int tickLength = 4;

    private Cell[][] board = new Cell[MAX_COLS][MAX_ROWS];

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

        BooleanSupplier lifeChance = () -> Math.random() > 0.8;

        for(int c = 0; c < maxCols; c++)
        {
            for(int r = 0; r < maxRows; r++)
            {
                State state = lifeChance.getAsBoolean() ?
                    State.ALIVE :
                    State.DEAD;

                randomBoard[r][c] = new Cell(state);
            }
        }

        return randomBoard;
    }
    
    /**
     * Apply rules to current state and create the next generation.
     *
     */
    public static Cell[][] applyRulesConstructNextFrame(Cell [][] frame)
    {
        Cell[][] nextFrame = new Cell[MAX_ROWS][MAX_COLS];

        return nextFrame;
    }

    public static int countAliveNeighbors(int col, int row, Cell[][] frame)
    {
        int neighborCount = 0;

        for(int i = (col - 1); i <= (col + 1); i++)
        {
            for(int j = (row - 1); j <= (row + 1); j++)
            {
                // System.out.println(String.format("(%d, %d)",i, j));
                if(i >= 0 && i < frame[0].length)
                {
                    if(j >= 0 && j < frame.length)
                    {
                        if(!(col == i && row == j))
                        {
                            System.out.println(String.format("%s at (%d, %d)", frame[i][j], j, i));
                            if(frame[i][j].isAlive())
                            {
                                neighborCount++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(neighborCount);
        
        return neighborCount;
    }

    public static void clearTerm()
    {
        System.out.print("\033[H\033[2J");
    }

    public static void main(String[] args)
    {
        // ArrayList<Cell[][]> randomFrames1 = createRandomizedFrameList(100);
        // printFrames(randomFrames1);

        Cell[][] test = randomBoard(MAX_COLS, MAX_ROWS);
        printBoard(test);
        countAliveNeighbors(1, 1, test);
    }
}
