package golja.rule;

import golja.State;
import golja.Board;

@FunctionalInterface
public interface AppliableRule
{
    State apply(int col, int row, Board board);
}
