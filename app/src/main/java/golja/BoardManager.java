package golja;

import java.util.function.*;
import java.util.List;


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

    public static Board constructStandardBoard(int cols, int rows) {
      return constructBoardWithStates(cols, rows, List.of(State.DEAD, State.ALIVE));
    }

    public static Board constructBoardWithStates(
      int cols,
      int rows,
      List<State> states
    ) {
      Board board = new Board(cols, rows);
      SpecifiedCellStateSupplier s = CellStateGen.SPECIFIED_EVEN_PROB_SUPPLIER;
      for(int r = 0; r < rows; ++r) {
        for(int c = 0; c < cols; ++c) {
          board.at(c, r).setState(s.get(states));
        }
      }
      return board;
    }

}
