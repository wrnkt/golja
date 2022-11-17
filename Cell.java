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

    private State cellState()
    {
        return cellState;
    }

    public boolean isAlive()
    {
        return cellState() == State.ALIVE ? true : false;
    }

    public static void main(String[] args)
    {
        Cell c = new Cell();
        System.out.println(c.cellState());
    }
}
