package golja;

public interface AppliableRule
{
    boolean apply(int col, int row, Cell[][] board);
}
