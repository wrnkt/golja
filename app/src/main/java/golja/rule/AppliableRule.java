package golja.rule;

import golja.model.board.Board;
import golja.model.cell.State;

@FunctionalInterface
public interface AppliableRule
{
    State apply(int col, int row, Board board);
}
