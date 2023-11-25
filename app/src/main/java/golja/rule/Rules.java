package golja.rule;

import golja.model.cell.State;

public final class Rules {

    public static AppliableRule DEFAULT_RULE = (c, r, b) -> {
        int aliveNeighbors = b.neighborsWithState(c, r, State.ALIVE);
        if(b.at(c, r).isAlive())
        {
            if(aliveNeighbors == 2 || aliveNeighbors == 3)
                return State.ALIVE;
            else
                return State.DEAD;
        }
        else
        {
            if(aliveNeighbors == 3)
                return State.ALIVE;
            else
                return State.DEAD;
        }
    };

  
}
