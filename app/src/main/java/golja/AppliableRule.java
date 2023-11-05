package golja;

@FunctionalInterface
public interface AppliableRule
{
    State apply(int col, int row, Board board);
}
