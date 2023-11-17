package golja.model.board;

import golja.model.cell.CellStateGen;
import golja.model.cell.State;
import golja.model.cell.SpecifiedCellStateSupplier;
import golja.rule.*;

import java.util.function.*;
import java.util.List;


public class BoardManager
{
    static final BooleanSupplier DEFAULT_LIFE_CHANCE = () -> Math.random() > 0.7;

    public static void updateBoard(
      Board board,
      AppliableRule rule,
      int msDelay,
      int maxGenerations,
      Consumer<Board> callback
    ) throws InterruptedException {
        int generation = 0;

        Board curBoard = new Board(board);

        while(generation < maxGenerations)
        {
            callback.accept(curBoard);
            curBoard.update(rule);
            generation++;
            Thread.sleep(msDelay);
        }

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
