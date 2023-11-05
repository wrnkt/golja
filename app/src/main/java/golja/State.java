package golja;

public enum State
{
    OBSTACLE,
    ALIVE,
    DEAD;

    public static final int size;
    static {
      size = values().length;
    }
}

