enum State
{
    ALIVE, DEAD;
}

class Cell
{
    private State cellState = State.DEAD;

    public State cellState()
    {
        return cellState;
    }

    public static void main(String[] args)
    {
        Cell c = new Cell();
        System.out.println(c.cellState());
    }
}
