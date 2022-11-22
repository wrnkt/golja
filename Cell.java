enum State
{
    ALIVE, DEAD;
}

public class Cell
{
    private State cellState;

    public Cell()
    {
        cellState = State.DEAD;
    }

    public Cell(State state)
    {
        cellState = state;
    }

    private State getCellState()
    {
        return cellState;
    }
    public void setCellState()
    {
    }

    public boolean isAlive()
    {
        return getCellState() == State.ALIVE ? true : false;
    }

    public static void main(String[] args)
    {
        Cell c = new Cell();
        System.out.println(c.getCellState());
    }
}
