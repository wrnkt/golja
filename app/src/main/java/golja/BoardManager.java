package golja;

import java.util.function.*;
import java.lang.Thread;


public class BoardManager
{
    static final BooleanSupplier DEFAULT_LIFE_CHANCE = () -> Math.random() > 0.7;

    static AppliableRule DEFAULT_RULE = (c, r, b) -> {
        int aliveNeighbors = countAliveNeighbors(c, r, b);
        if(b.at(c, r).isAlive())
        {
            if(aliveNeighbors == 2 || aliveNeighbors == 3)
                return State.ALIVE;
            else
                return State.DEAD;
        }
        else
        {
            if(aliveNeighbors == 3)
                return State.ALIVE;
            else
                return State.DEAD;
        }
    };

    public static Board constructNextFrame(Board originalBoard, AppliableRule rule)
    {
        int numRows = originalBoard.getHeight();
        int numColumns = originalBoard.getWidth();

        Board nextBoard = new Board(numColumns, numRows);

        for(int row = 0; row < numRows; row++)
        {
            for(int col = 0; col < numColumns; col++)
            {
                nextBoard.at(col, row).setCellState(rule.apply(col, row, originalBoard));
            }
        }
        return nextBoard;
    }


    public static int countAliveNeighbors(int col, int row, Board board)
    {
      int neighborCount = 0;

      for(int r = (row - 1); r <= (row + 1); r++)
      {
          for(int c = (col - 1); c <= (col + 1); c++)
          {
              if( !board.contains(c, r) ) continue;

              if( col == c && row == r ) continue;

              if( board.at(c, r).isAlive() ) neighborCount++;
          }
      }
      return neighborCount;

    }

    public static Board randomBoard(int columns, int rows, BooleanSupplier lifeChance)
    {
        Board board = new Board(columns, rows);

        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                State state = lifeChance.getAsBoolean() ?
                    State.ALIVE :
                    State.DEAD;

                board.at(c, r).setCellState(state);
            }
        }
        return board;
    }

    public static Board deadBoard(int columns, int rows)
    {
        Board deadBoard = new Board(columns, rows);

        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                deadBoard.at(c, r).setCellState(State.DEAD);
            }
        }
        return deadBoard;
    }

}
