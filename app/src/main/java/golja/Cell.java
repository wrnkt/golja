package golja;


enum State
{
    OBSTACLE,
    ALIVE,
    DEAD;

    public static final int size;
    static {
      size = values().length;
    }
}

public class Cell
{
    private State state;

    public Cell() {
        state = State.DEAD;
    }

    public Cell(State state) {
        this.state = state;
    }

    private State getState() { return state; }

    public void setState(State state) {
      this.state = state;
    }

    public boolean isAlive() {
        return getState() == State.ALIVE ? true : false;
    }

    public String toString() {
        return getState().name();
    }

}
