package golja;

public class Cell
{
    private State state;

    public Cell() {
        state = State.DEAD;
    }

    public Cell(State state) {
        this.state = state;
    }

    public State getState() { return state; }

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
