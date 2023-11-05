package golja;

import java.util.function.*;
import java.lang.Thread;


public class BoardManager
{

    static final BooleanSupplier DEFAULT_LIFE_CHANCE = () -> Math.random() > 0.7;

    static AppliableRule DEFAULT_RULE = (c, r, b) -> {
        int aliveNeighbors = b.neighborsWithState(c, r, State.ALIVE);
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

    public static void update(Board board, AppliableRule rule)
    {
        board.update(rule);
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

                board.at(c, r).setState(state);
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
                deadBoard.at(c, r).setState(State.DEAD);
            }
        }
        return deadBoard;
    }

}
